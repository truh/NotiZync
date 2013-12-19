package notizync.pc.gui.View;

import net.miginfocom.swing.MigLayout;

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

        MenuBar bar = new MenuBar();
        NoteList list = new NoteList();
        NoteView view = new NoteView();
        JScrollPane pane = new JScrollPane(list);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.getVerticalScrollBar().setUnitIncrement(200);

        this.setJMenuBar(bar.createBar());
        this.add(pane);
        this.add(view);
        this.setVisible(true);
    }
}
