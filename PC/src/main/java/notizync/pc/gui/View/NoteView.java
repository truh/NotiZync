package notizync.pc.gui.View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import notizync.core.loremipsum.*;

/**
 * Displays a Note in Detail (including Text)
 *
 * @author Andreas Willinger
 * @version 0.2
 * @since 07.11.13 10:44
 */
public class NoteView
    extends JPanel
{
    public NoteView()
    {
        this.setLayout(new MigLayout("insets 4"));

        JLabel header = new JLabel("Super fancy notice title");
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea content = new JTextArea();
        content.setPreferredSize(new Dimension(580, 480));
        content.setBorder(new EmptyBorder(4,4,4,4));
        content.setLineWrap(true);
        content.setText((new LoremIpsum()).getWords(1000));

        this.add(header, "wrap");
        this.add(new JScrollPane(content));
    }
}
