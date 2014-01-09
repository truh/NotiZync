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
    private JButton bNew;
    private JButton bEdit;
    private JButton bDelete;
    private JButton bRefresh;
    private JButton bSettings;

    public VToolBar(Model m, VNoteList l)
    {
        this.m =  m;
        this.c = new CToolBar(this.m, l, this);
    }

    public JToolBar createBar()
    {
        JToolBar bar = new JToolBar();
        bar.setBorder(new EmptyBorder(4,4,4,4));

        this.bNew = this.createButton("add.png", "NEW", "", "Neue Notiz anlegen");
        this.bDelete = this.createButton("delete.png", "DELETE", "", "Notiz(en) löschen");
        this.bRefresh = this.createButton("refresh.png", "REFRESH", "", "Synchronisation starten");
        this.bSettings = this.createButton("cog.png", "SETTINGS", "", "Einstellungen");

        bar.add(this.bNew);
        bar.add(this.bDelete);
        bar.add(this.bRefresh);
        bar.addSeparator(new Dimension(100, 25));
        bar.add(this.bSettings);

        return bar;
    }

    private JButton createButton(String image, String action, String tooltip, String text)
    {
        //Look for the image.
        String imgLocation = "images/"
                + image;
        URL imageURL = VToolBar.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(action);
        button.setToolTipText(tooltip);
        button.addActionListener(this.c);

        button.setIcon(new ImageIcon(imageURL, text));
        button.setText(text);

        return button;
    }

    public JButton getButton(String button)
    {
        switch(button)
        {
            case "new":
                return this.bNew;
            case "delete":
                return this.bDelete;
            case "refresh":
                return this.bRefresh;
            case "settings":
                return this.bSettings;
            default:
                return new JButton();
        }
    }
}
