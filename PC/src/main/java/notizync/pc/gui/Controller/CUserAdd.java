package notizync.pc.gui.Controller;

import notizync.core.http.EResult;
import notizync.pc.core.Model;
import notizync.pc.gui.View.VUserAdd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface between our GUI and the backend.
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class CUserAdd
    implements ActionListener
{
    private Model m;
    private VUserAdd v;

    public CUserAdd(Model m, VUserAdd v)
    {
        this.m = m;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.v.getCancelButton())
        {
            this.v.setVisible(false);
            this.v.dispose();
        }
        else if(e.getSource() == this.v.getCreateButton())
        {
            String username = this.v.getUserField().getText();
            String password1 = String.valueOf(this.v.getPassword1().getPassword());
            String password2 = String.valueOf(this.v.getPassword2().getPassword());

            if(username.equals("") || password1.equals("") || password2.equals(""))
            {
                this.v.showErrorMessage("Bitte füllen Sie alle Felder aus!");
            }
            else
            {
                if(!password1.equals(password2))
                {
                    this.v.showErrorMessage("Die Passwörter stimmen nicht überein!");
                }
                else
                {
                   EResult success = this.m.tryRegister(username, password1);

                   switch(success)
                   {
                       case k_RemoteDatabaseFailure:
                       case k_RemoteDown:
                           this.v.showErrorMessage("Das Anlegen Ihres Accounts ist fehlgeschlagen!\nDie Kommunikation mit dem Registrierungs-Server war nicht erfolgreich.\nBitte versuchen Sie es später noch einmal.");
                           break;
                       case k_RemoteUserExists:
                           this.v.showErrorMessage("Das Anlegen Ihres Accounts ist fehlgeschlagen!\nDer von Ihnen eingegebene Benutzername ist bereits in Verwendung.");
                           break;
                       case k_RemoteSuccess:
                           this.v.showInfoMessage("Das Anlegen Ihres Accounts war erfolgreich!\nSie können sich nun mit den von Ihnen angegebenen Daten einloggen.");

                           this.v.setVisible(false);
                           this.v.dispose();
                           break;
                   }
                }
            }
        }
    }
}
