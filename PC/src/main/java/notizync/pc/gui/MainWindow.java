package notizync.pc.gui;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;
import notizync.pc.gui.View.VNoteDisplay;
import notizync.pc.gui.View.VNoteList;
import notizync.pc.gui.View.VToolBar;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Main Window, contains: A MenuBar, VNoteList and a VNoteDisplay
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 07.11.13 10:34
 */
public class MainWindow extends JFrame
{
    private Model m;

    public MainWindow(Model m)
    {
        super("NotiZync");
        this.setSize(800, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.m = m;

        CNoteDisplay c = new CNoteDisplay();
        Container c1 = new Container();
        c1.setLayout(new BorderLayout());

        VNoteDisplay view = new VNoteDisplay(this.m, c);
        VNoteList list = new VNoteList(this.m, c);
        JScrollPane pane = new JScrollPane(list.generateList());
        VToolBar tool = new VToolBar(this.m);

        c.setObject(this.m, list, view);

        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.getVerticalScrollBar().setUnitIncrement(200);

        c1.add(pane, BorderLayout.CENTER);
        c1.add(list.generateButtons(), BorderLayout.SOUTH);

        this.add(c1, BorderLayout.WEST);
        this.add(view, BorderLayout.CENTER);
        this.add(tool.createBar(), BorderLayout.NORTH);
        this.setVisible(true);
    }
}
