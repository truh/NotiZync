package notizync.pc.gui.Controller;

import notizync.pc.core.Model;
import notizync.pc.gui.View.VSettings;
import notizync.pc.gui.View.VUserAdd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Also does something.
 *
 * @author Andreas Willinger
 * @version 0.1
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
    }
}
