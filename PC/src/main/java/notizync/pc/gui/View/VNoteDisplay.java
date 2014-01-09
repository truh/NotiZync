package notizync.pc.gui.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;

/**
 * Create a JLabel and JTextArea to display the title and content of the Note.
 * Additionally add a JButton Bar to edit the note content.
 *
 * Please note that due to the specification, the title of the Note can not be changed!
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class VNoteDisplay
    extends JPanel
{
    private CNoteDisplay c;

    private JLabel lblHeader;
    private JTextArea txtContent;

    private JButton bSave;
    private JButton bCancel;

    public VNoteDisplay(CNoteDisplay c)
    {
        this.c = c;

        // structure:
        // [title]
        //    |
        // [content]
        //    |
        // [editing area]
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(4,4,8,4));

        // note title
        this.lblHeader = new JLabel("Keine Notiz ausgewählt");
        this.lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        this.lblHeader.setBorder(new EmptyBorder(4,4,4,4));

        // note content
        this.txtContent = new JTextArea();
        this.txtContent.setBorder(new EmptyBorder(4,4,4,4));
        this.txtContent.setLineWrap(true);
        this.txtContent.setText("Bitte wählen Sie entweder eine bestehende Notiz links aus, oder erzeugen Sie eine neue.");

        // by default, the editing buttons are disabled
        // once the text changes, they get enabled automatically
        this.txtContent.getDocument().addDocumentListener(this.c);

        String imgLocation = "images/";
        this.bSave = new JButton("Speichern");
        this.bCancel = new JButton("Abbrechen");

        URL imageURL = VNoteDisplay.class.getResource(imgLocation+"disk.png");
        this.bSave.setIcon(new ImageIcon(imageURL, "Speichern"));
        this.bSave.addActionListener(this.c);
        this.bSave.setEnabled(false);

        URL imageURL2 = VNoteDisplay.class.getResource(imgLocation+"cancel.png");
        this.bCancel.setIcon(new ImageIcon(imageURL2, "Abbrechen"));
        this.bCancel.addActionListener(this.c);
        this.bCancel.setEnabled(false);

        // add the general stuff
        this.add(this.lblHeader, BorderLayout.NORTH);
        this.add(new JScrollPane(this.txtContent), BorderLayout.CENTER);

        Container c1 = new Container();
        c1.setLayout(new GridLayout(1,2));
        c1.add(this.bSave);
        c1.add(this.bCancel);

        this.add(c1, BorderLayout.SOUTH);
    }

    // Getter Methods
    public JLabel getHeader()
    {
        return this.lblHeader;
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
