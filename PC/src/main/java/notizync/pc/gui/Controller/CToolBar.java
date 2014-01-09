package notizync.pc.gui.Controller;

import notizync.pc.core.Model;
import notizync.pc.gui.View.VNoteAdd;
import notizync.pc.gui.View.VNoteList;
import notizync.pc.gui.View.VToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles all Actions which happen on the VToolBar.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 19.12.13 10:56
 */
public class CToolBar
    implements ActionListener
{
    private Model m;
    private VNoteList l;
    private VToolBar tb;

    public CToolBar(Model m, VNoteList l, VToolBar tb)
    {
        this.m = m;
        this.l = l;
        this.tb = tb;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.tb.getButton("new") == e.getSource())
        {
            VNoteAdd add = new VNoteAdd(this.m, this.l);
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
