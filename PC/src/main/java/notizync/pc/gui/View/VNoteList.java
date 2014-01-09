package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CNoteDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Both the list of currently stored Notes and the create, delete as well as the settings button are built together here.
 * It uses the Model supplied by the main Window to get the most recent data.
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class VNoteList
    extends JPanel
{
    private Model m;
    private CNoteDisplay c;

    private JList<String> lNotes;
    private JButton bNew;
    private JButton bDelete;
    private JButton bSettings;

    public VNoteList(Model m, CNoteDisplay c)
    {
        this.m = m;
        this.c = c;
    }

    /**
     * Create a new Instance of JList and save it locally, then return to the caller.
     * After instantiation the JList will contain all currently stored Notes.
     * Only call this upon the start of the Program!
     *
     * @return A JList Object reference
     */
    public JList generateList()
    {
        this.lNotes = new JList<String>(this.m.getNoteList());
        this.lNotes.addListSelectionListener(this.c);
        this.lNotes.setFixedCellWidth(180);
        this.lNotes.setBorder(new EmptyBorder(2,2,2,2));

        return lNotes;
    }

    /**
     * Generate the JButton Bar displayed below the JList, which contains:
     * - the create button
     * - delete button
     * - and settings button
     * Additionally saves the references to the buttons locally.
     *
     * @return A Container Object containing all JButtons
     */
    public Container generateButtons()
    {
        // this holds all our JButtons in same line & size
        Container c1 = new Container();
        c1.setLayout(new GridLayout(1,2));

        String imgLocation = "images/";

        this.bNew = new JButton("");
        this.bDelete= new JButton("");
        this.bSettings = new JButton("");

        URL imageURL1 = VNoteDisplay.class.getResource(imgLocation+"add.png");
        this.bNew.setIcon(new ImageIcon(imageURL1, "Neue Notiz"));
        this.bNew.addActionListener(this.c);
        this.bNew.setToolTipText("Neue Notiz anlegen");

        URL imageURL2 = VNoteDisplay.class.getResource(imgLocation+"delete.png");
        this.bDelete.setIcon(new ImageIcon(imageURL2, "Notiz(en) löschen"));
        this.bDelete.addActionListener(this.c);
        this.bDelete.setToolTipText("Aktuell ausgewählt(e) Notiz(en) löschen");

        URL imageURL3 = VNoteDisplay.class.getResource(imgLocation+"cog.png");
        this.bSettings.setIcon(new ImageIcon(imageURL3, "Einstellungen"));
        this.bSettings.addActionListener(this.c);
        this.bSettings.setToolTipText("Einstellungen bearbeiten");

        // build it together
        c1.add(this.bNew);
        c1.add(this.bDelete);
        c1.add(this.bSettings);

        return c1;
    }

    /**
     * Update the currently stored data in our JList.
     * This will fail if the Application has not been fully initialized yet.
     */
    public void update()
    {
        this.lNotes.setListData(this.m.getNoteList());
    }

    // Getter Methods
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

    public JButton getSettingsButton()
    {
        return this.bSettings;
    }
}
