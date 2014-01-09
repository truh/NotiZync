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
    private VToolBar tb;

    public CToolBar(Model m, VToolBar tb)
    {
        this.m = m;
        this.tb = tb;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
          System.out.println("settings was pressed");
    }
}
