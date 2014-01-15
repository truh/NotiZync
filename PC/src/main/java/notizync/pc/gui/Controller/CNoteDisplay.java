package notizync.pc.gui.Controller;

import notizync.core.http.EResult;
import notizync.pc.core.Model;
import notizync.pc.gui.View.VNoteAdd;
import notizync.pc.gui.View.VNoteDisplay;
import notizync.pc.gui.View.VNoteList;
import notizync.pc.gui.View.VSettings;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Once a Line has been selected on our JList, update the contents in our main area.
 * Also update our Note if data has been changed.
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class CNoteDisplay
        implements ActionListener, ListSelectionListener, DocumentListener
{
    private Model m;

    private VNoteList v1;
    private VNoteDisplay v2;

    private boolean noteChanged = false;

    public void setObject(Model m, VNoteList v1, VNoteDisplay v2)
    {
        this.m = m;
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    /**
     * A Button has been clicked.
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton)e.getSource();

        if(button == this.v2.getSave())
        {
            // the update of our note was successful, so disable the editing area again
            EResult result = this.m.setContent(this.v2.getHeader().getText(), this.v2.getContent().getText());
            if(result == EResult.k_Success)
            {
                this.noteChanged = false;
                this.toggleButtons();
            }
            else
            {
                System.out.println(result);
                JOptionPane.showMessageDialog(null, "Die Notiz konnte leider nicht gespeichert werden!\nBitte überprüfen Sie, ob die Notiz überhaupt existiert und versuchen Sie es noch einmal.", "Fehler beim Speichern", JOptionPane.ERROR_MESSAGE);
            }
        }
        // reset all changes and disable  editing area
        else if(button == this.v2.getCancel())
        {
            this.v2.getHeader().setText(this.v2.getHeader().getText());
            this.v2.getContent().setText(this.m.getNote(this.v2.getHeader().getText()));

            this.noteChanged = false;
            this.toggleButtons();
        }
        else if(button == this.v1.getNewButton())
        {
            new VNoteAdd(this.m, this.v1);
        }
        else if(button == this.v1.getDeleteButton())
        {
            List<String> selected = this.v1.getList().getSelectedValuesList();

            // nothing was selected, stop.
            if(selected.size() == 0) return;

            int selection = JOptionPane.showConfirmDialog(null, "Sind Sie sicher, dass Sie die ausgewählte(n) Element(e) löschen wollen?", "Löschbestätigung", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(selection == 0)
            {
                this.m.removeNote(Arrays.copyOf(selected.toArray(), selected.size(), String[].class));
                this.v1.update();
            }
        }
        else if(button == this.v1.getSettingsButton())
        {
            new VSettings(this.m);
        }
    }

    /**
     * Disable/Enable the editing area.
     */
    private void toggleButtons()
    {
        if(this.noteChanged)
        {
            this.v2.getSave().setEnabled(true);
            this.v2.getCancel().setEnabled(true);
        }
        else
        {
            this.v2.getSave().setEnabled(false);
            this.v2.getCancel().setEnabled(false);
        }
    }

    @Override
    // an item has been selected in our JList, so update the Content panel.
    // also revert all changes which have not been saved.
    public void valueChanged(ListSelectionEvent e)
    {
        JList list = (JList)e.getSource();

        this.v2.getHeader().setText((String)list.getSelectedValue());
        this.v2.getContent().setText(this.m.getNote((String) list.getSelectedValue()));

        this.noteChanged = false;
        this.toggleButtons();
    }

    @Override
    // following two methods: text in our content pane was changed
    public void insertUpdate(DocumentEvent e)
    {
        this.noteChanged = true;
        this.toggleButtons();
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        this.noteChanged = true;
        this.toggleButtons();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}
}
