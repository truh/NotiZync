package notizync.pc.gui.Controller;

import notizync.core.http.EResult;
import notizync.pc.core.Model;
import notizync.pc.gui.View.VNoteAdd;
import notizync.pc.gui.View.VNoteList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface which interact between our VNoteAdd Window and the Model.
 * Automatically updates the JList once an Item was added.
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class CNoteAdd implements ActionListener
{
    private Model m;
    private VNoteAdd v;
    private VNoteList l;

    public CNoteAdd(Model m, VNoteList l, VNoteAdd v)
    {
        this.m = m;
        this.v = v;
        this.l = l;
    }

    @Override
    /**
     * A Button was clicked
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton source = (JButton)e.getSource();

        if(source == this.v.getSave())
        {
            if(this.v.getHeader().getText().equals("") || this.v.getContent().getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Bitte füllen Sie alle Felder aus!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                // store was successful, update the JList
                EResult result = this.m.putNote(this.v.getHeader().getText(), this.v.getContent().getText());
                if(result == EResult.k_Success)
                {
                    this.l.update();

                    this.v.setVisible(false);
                    this.v.dispose();
                }
                // most likely a dupe
                else
                {
                    System.out.println(result);
                    JOptionPane.showMessageDialog(null, "Die Notiz konnte leider nicht gespeichert werden!\nBitte überprüfen Sie, ob nicht bereits eine Notiz mit dem selben Namen existiert.", "Fehler beim Speichern", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if(source == this.v.getCancel())
        {
            this.v.setVisible(false);
            this.v.dispose();
        }
        else
        {
            // this shouldn't normally happen
            throw new NotImplementedException();
        }
    }
}
