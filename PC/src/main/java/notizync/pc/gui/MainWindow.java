package notizync.pc.gui;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;
import notizync.pc.gui.View.VNoteDisplay;
import notizync.pc.gui.View.VNoteList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The visual starting point of the NotiZync PC version.
 * All Components (Display & Listing of Notes and Managing) are built together here.
 *
 * It also initialises the CNoteDisplay Controller, which handles the entire main Window.
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class MainWindow extends JFrame
{
    private Model m;

    public MainWindow(Model m)
    {
        super("NotiZync");
        this.setSize(800, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.m = m;

        CNoteDisplay c = new CNoteDisplay();

        // this holds our list of notes and the new, delete & settings button
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setBorder(new EmptyBorder(8,4,8,4));

        // note content & editing
        VNoteDisplay view = new VNoteDisplay(c);
        // list of notes
        VNoteList list = new VNoteList(this.m, c);
        JScrollPane pane = new JScrollPane(list.generateList());

        // initialize our controller
        c.setObject(this.m, list, view);

        // no horizontal scollbar
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.getVerticalScrollBar().setUnitIncrement(200);

        // add everything together
        p1.add(pane, BorderLayout.CENTER);
        p1.add(list.generateButtons(), BorderLayout.SOUTH);

        this.add(p1, BorderLayout.WEST);
        this.add(view, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
