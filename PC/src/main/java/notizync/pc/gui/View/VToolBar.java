package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CToolBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Main stuff
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 19.12.13 10:52
 */
public class VToolBar
{
    private CToolBar c;
    private VNoteList l;

    private Model m;
    private JButton bSettings;

    public VToolBar(Model m)
    {
        this.m =  m;
        this.c = new CToolBar(this.m, this);
    }

    public JToolBar createBar()
    {
        JToolBar bar = new JToolBar();
        bar.setBorder(new EmptyBorder(4,4,4,4));

        URL imageURL = VNoteDisplay.class.getResource("images/cog.png");
        this.bSettings = new JButton("Einstellungen");
        this.bSettings.setIcon(new ImageIcon(imageURL, "Einstellungen"));
        this.bSettings.addActionListener(this.c);
        this.bSettings.setToolTipText("Die Einstellungen des Programmes bearbeiten");

        bar.addSeparator(new Dimension(600, 25));
        bar.add(this.bSettings);

        return bar;
    }
}
