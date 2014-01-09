package notizync.pc.gui.View;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

import notizync.core.loremipsum.*;
import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;

/**
 * Displays a Note in Detail (including Text)
 *
 * @author Andreas Willinger
 * @version 0.2
 * @since 07.11.13 10:44
 */
public class VNoteDisplay
    extends JPanel
{
    private Model m;
    private CNoteDisplay c;

    private JLabel lblHeader;
    private JTextArea txtContent;

    private JButton bSave;
    private JButton bCancel;

    public VNoteDisplay(Model m, CNoteDisplay c)
    {
        this.m = m;
        this.c = c;

        this.setLayout(new MigLayout("insets 4"));

        this.lblHeader = new JLabel("Super fancy notice title");
        this.lblHeader.setFont(new Font("Arial", Font.BOLD, 18));

        this.txtContent = new JTextArea();
        this.txtContent.setPreferredSize(new Dimension(580, 480));
        this.txtContent.setBorder(new EmptyBorder(4, 4, 4, 4));
        this.txtContent.setLineWrap(true);
        this.txtContent.setText((new LoremIpsum()).getWords(1000));
        this.txtContent.addKeyListener(this.c);

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

        this.add(this.lblHeader, "wrap");
        this.add(new JScrollPane(this.txtContent), "wrap");

        Container c1 = new Container();
        c1.setLayout(new FlowLayout());
        c1.add(this.bSave);
        c1.add(this.bCancel);
        this.add(c1);
    }

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
