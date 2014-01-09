package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteAdd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Add a new Note to the Datastore.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 07.11.13 10:45
 */
public class VNoteAdd
    extends JFrame
{
    private Model m;

    private JTextField txtTitle;
    private JTextArea txtContent;

    private JButton bSave;
    private JButton bCancel;

    public VNoteAdd(Model m, VNoteList l)
    {
        super("Neue Notiz anlegen");
        this.m = m;

        BorderLayout layout = new BorderLayout();
        layout.setVgap(4);
        this.setLayout(layout);

        CNoteAdd c = new CNoteAdd(this.m, l, this);

        this.setSize(new Dimension(480, 300));
        this.setResizable(false);

        this.txtTitle = new JTextField("Titel");
        this.txtContent = new JTextArea("Inhalt");

        this.txtTitle.setBorder(new EmptyBorder(4,4,4,4));
        this.txtContent.setBorder(new EmptyBorder(4,4,4,4));

        String imgLocation = "images/";
        this.bSave = new JButton("Speichern & schließen");
        this.bCancel = new JButton("Abbrechen");

        URL imageURL = VNoteDisplay.class.getResource(imgLocation+"disk.png");
        this.bSave.setIcon(new ImageIcon(imageURL, "Speichern"));
        this.bSave.addActionListener(c);

        URL imageURL2 = VNoteDisplay.class.getResource(imgLocation+"cancel.png");
        this.bCancel.setIcon(new ImageIcon(imageURL2, "Speichern"));
        this.bCancel.addActionListener(c);

        Container c1 = new Container();
        c1.setLayout(new GridLayout(1,2));
        c1.add(this.bSave);
        c1.add(this.bCancel);

        this.add(this.txtTitle, BorderLayout.NORTH);
        this.add(this.txtContent, BorderLayout.CENTER);
        this.add(c1, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public JTextField getHeader()
    {
        return this.txtTitle;
    }

    public JTextArea getContent()
    {
        return this.txtContent;
    }

    public JButton getSave()
    {
        return this.bSave;
    }

    public JButton getCancel()
    {
        return this.bCancel;
    }
}
