package notizync.pc.gui;

import net.miginfocom.swing.MigLayout;
import notizync.pc.core.ToolBarController;
import notizync.pc.gui.View.NoteList;
import notizync.pc.gui.View.NoteView;
import notizync.pc.gui.View.ToolBar;

import javax.swing.*;

/**
 * Main Window, contains: A MenuBar, NoteList and a NoteView
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 07.11.13 10:34
 */
public class MainWindow extends JFrame
{
    public MainWindow()
    {
        super("NotiZync");
        this.setSize(800, 480);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new MigLayout("insets 0"));

        NoteList list = new NoteList();
        NoteView view = new NoteView();
        JScrollPane pane = new JScrollPane(list);
        ToolBarController c1 = new ToolBarController();
        ToolBar tool = new ToolBar(c1);
        c1.setToolBar(tool);

        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.getVerticalScrollBar().setUnitIncrement(200);

        this.add(tool.createBar(), "wrap");
        this.add(pane);
        this.add(view);

        this.setVisible(true);
    }
}
