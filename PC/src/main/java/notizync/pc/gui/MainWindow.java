package notizync.pc.gui;

import javax.swing.*;

/**
 * -- DESCRIPTION --
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
        this.setSize(600, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        MenuBar bar = new MenuBar();
        this.setJMenuBar(bar.createBar());
        this.setVisible(true);
    }
}
