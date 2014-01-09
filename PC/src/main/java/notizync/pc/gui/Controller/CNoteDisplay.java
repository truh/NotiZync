package notizync.pc.gui.Controller;

import notizync.pc.core.Model;
import notizync.pc.gui.View.VNoteDisplay;
import notizync.pc.gui.View.VNoteList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles Communication between the Model (data) and the GUI.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 09.01.14 09:41
 */
public class CNoteDisplay
        implements ActionListener, KeyListener
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
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton)e.getSource();

        if(button == this.v2.getSave())
        {
            if(this.m.setContent(this.v2.getHeader().getText(), this.v2.getContent().getText()))
            {
                this.noteChanged = false;
                this.toggleButtons();
            }
            else
            {
                System.out.println("Error");
            }
        }
        else if(button == this.v2.getCancel())
        {
            this.v2.getHeader().setText(this.v2.getHeader().getText());
            this.v2.getContent().setText(this.m.getNote(this.v2.getHeader().getText()));

            this.noteChanged = false;
            this.toggleButtons();
        }
        else
        {
            this.v2.getHeader().setText(button.getText());
            this.v2.getContent().setText(this.m.getNote(button.getText()));

            this.noteChanged = false;
            this.toggleButtons();
        }

    }

    @Override
    public void keyTyped(KeyEvent e)  {}

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e)
    {
        this.noteChanged = true;
        this.toggleButtons();
    }

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
}
