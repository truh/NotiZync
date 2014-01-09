package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteAdd;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Create a new Note added to the local/remote Datastore.
 *
 * @author Andreas Willinger
 * @version 1.0
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
        // required so that the textfields do not stick together
        layout.setVgap(4);
        this.setLayout(layout);

        // our controller/event listener
        CNoteAdd c = new CNoteAdd(this.m, l, this);

        this.setSize(new Dimension(480, 300));
        this.setResizable(false);

        this.txtTitle = new JTextField("");
        this.txtContent = new JTextArea("");

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

        // save & cancel
        Container c1 = new Container();
        c1.setLayout(new GridLayout(1,2));
        c1.add(this.bSave);
        c1.add(this.bCancel);

        this.add(this.txtTitle, BorderLayout.NORTH);
        this.add(this.txtContent, BorderLayout.CENTER);
        this.add(c1, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    // Getter Methods
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
