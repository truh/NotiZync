package notizync.pc.gui.View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a List of all available Notes
 *
 * @author Andreas Willinger
 * @version 0.2
 * @since 07.11.13 10:43
 */
public class NoteList
    extends JPanel
{
    public NoteList()
    {
        this.setLayout(new MigLayout("insets 2"));

        for(int i = 0; i < 100; i++)
        {
            JButton b = new JButton("Notiz #"+(i+1));
            b.setPreferredSize(new Dimension(185, 30));
            this.add(b, "wrap");
        }

        this.setPreferredSize(new Dimension(205, (int)Math.ceil(100*35)));
    }
}
