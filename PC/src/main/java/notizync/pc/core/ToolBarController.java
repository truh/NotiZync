package notizync.pc.core;

import notizync.pc.gui.View.NoteAdd;
import notizync.pc.gui.View.ToolBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles all Actions which happen on the ToolBar.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 19.12.13 10:56
 */
public class ToolBarController
    implements ActionListener
{
    private ToolBar tb;

    public void setToolBar(ToolBar tb)
    {
        this.tb = tb;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.tb.getButton("new") == e.getSource())
        {
            NoteAdd add = new NoteAdd();
        }
        else if(this.tb.getButton("edit") == e.getSource())
        {
            System.out.println("edit was pressed");
        }
        else if(this.tb.getButton("delete") == e.getSource())
        {
            System.out.println("delete was pressed");
        }
        else if(this.tb.getButton("refresh") == e.getSource())
        {
            System.out.println("refresh was pressed");
        }
        else if(this.tb.getButton("settings") == e.getSource())
        {
            System.out.println("settings was pressed");
        }
    }
}
