package notizync.pc;

import notizync.pc.core.Model;
import notizync.pc.gui.MainWindow;

/**
 *
 */
public final class Run
{
    public static void main(String [] args)
    {
        Model m = new Model();
        new MainWindow(m);
    }
}
