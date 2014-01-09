package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a List of all available Notes
 *
 * @author Andreas Willinger
 * @version 0.2
 */
public class VNoteList
    extends JPanel
{
    private Model m;
    private GridLayout layout;

    private CNoteDisplay c;

    public VNoteList(Model m, CNoteDisplay c)
    {
        this.m = m;
        this.c = c;

        this.layout = new GridLayout(0,1);
        this.setLayout(this.layout);

        String[] notes = this.m.getNoteList();

        for(int i = 0; i < notes.length; i++)
        {
            this.addButton(notes[i]);
        }
    }

    public void addButton(String text)
    {
        JButton b = new JButton(text);
        b.setPreferredSize(new Dimension(185, 30));
        b.addActionListener(this.c);
        this.layout.setRows(this.layout.getRows()+1);
        this.add(b);
    }
}
