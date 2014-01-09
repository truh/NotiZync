package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Displays a List of all available Notes
 *
 * @author Andreas Willinger
 * @version 0.2
 */
public class VNoteList
    extends JPanel
{
    private Model m;
    private CNoteDisplay c;

    private JList<String> lNotes;
    private JButton bNew;
    private JButton bDelete;

    public VNoteList(Model m, CNoteDisplay c)
    {
        this.m = m;
        this.c = c;
    }

    public JList generateList()
    {
        this.lNotes = new JList<String>(this.m.getNoteList());
        this.lNotes.addListSelectionListener(this.c);
        this.lNotes.setFixedCellWidth(180);
        this.lNotes.setBorder(new EmptyBorder(2,2,2,2));

        return lNotes;
    }

    public Container generateButtons()
    {
        Container c1 = new Container();
        c1.setLayout(new GridLayout(1,2));

        String imgLocation = "images/";
        this.bNew = new JButton("");
        this.bDelete= new JButton("");

        URL imageURL1 = VNoteDisplay.class.getResource(imgLocation+"add.png");
        this.bNew.setIcon(new ImageIcon(imageURL1, "Neue Notiz"));
        this.bNew.addActionListener(this.c);
        this.bNew.setToolTipText("Ein Fenster zum erzeugen einer neuen Notiz aufrufen");

        URL imageURL2 = VNoteDisplay.class.getResource(imgLocation+"delete.png");
        this.bDelete.setIcon(new ImageIcon(imageURL2, "Notiz(en) löschen"));
        this.bDelete.addActionListener(this.c);
        this.bDelete.setToolTipText("Aktuell ausgewählt(e) Notiz(en) löschen");

        c1.add(this.bNew);
        c1.add(this.bDelete);
        return c1;
    }

    public void update()
    {
        this.lNotes.setListData(this.m.getNoteList());
    }

    public JList getList()
    {
        return this.lNotes;
    }

    public JButton getNewButton()
    {
        return this.bNew;
    }

    public JButton getDeleteButton()
    {
        return this.bDelete;
    }
}
