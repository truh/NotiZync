package notizync.pc.gui.Controller;

import notizync.pc.core.Model;
import notizync.pc.gui.View.VSettings;
import notizync.pc.gui.View.VUserAdd;

import javax.swing.*;
import java.awt.event.*;

/**
 * Communication between the Settings Dialogue and the Backend.
 *
 * @author Andreas Willinger
 * @version 0.3
 */
public class CSettings
    implements ActionListener
{
    private VSettings v;
    private Model m;

    public CSettings(Model m, VSettings v)
    {
        this.m = m;
        this.v = v;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.v.getCreateButton())
        {
            new VUserAdd(this.m);
        }
        else if(e.getSource() == this.v.getResetButton())
        {
            this.v.getUserField().setText("");
            this.v.getPasswordField().setText("");
            this.v.getIntervalField().setText("5");
            this.v.getAutoCheckbox().setSelected(false);
        }
        else if(e.getSource() == this.v.getLoginButton())
        {
            if(this.m.tryLogin(this.v.getUserField().getText(), String.valueOf(this.v.getPasswordField().getPassword())))
            {
                javax.swing.JOptionPane.showMessageDialog(this.v, "Anmeldung erfolgreich!\nSie können nun die Synchronisationsfunktionen des Programmes verwenden.", "Anmeldung", JOptionPane.INFORMATION_MESSAGE);
                this.v.getLoginButton().setText("Angemeldet");
                this.v.getLoginButton().setEnabled(false);
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(this.v, "Anmeldung fehlgeschlagen!\nBitte überprüfen Sie die eingegebenen Daten und versuchen Sie es noch einmal!", "Anmeldung", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == this.v.getSaveButton())
        {
            boolean success = false;
            success = this.m.updateSetting("sync_interval", Integer.parseInt(this.v.getIntervalField().getText()), true);

            success = this.m.updateSetting("sync_save", this.v.getAutoCheckbox().isSelected(), true);
            if(!this.v.getAutoCheckbox().isSelected() && (boolean)this.m.getSetting("sync_save"))
            {
                success = this.m.updateSetting("sync_username", "", true);
                success = this.m.updateSetting("sync_password", "", true);
            }
            success = this.m.updateSetting("sync_username", this.v.getUserField().getText(), this.v.getAutoCheckbox().isSelected());
            success = this.m.updateSetting("sync_password", String.valueOf(this.v.getPasswordField().getPassword()), this.v.getAutoCheckbox().isSelected());

            if(!success)
            {
                System.out.println("** DEBUG: Saving failed");
            }
            else
            {
                this.v.setVisible(false);
                this.v.dispose();
            }
        }
        else if(e.getSource() == this.v.getCancelButton())
        {
            this.v.setVisible(false);
            this.v.dispose();
        }
    }
}
