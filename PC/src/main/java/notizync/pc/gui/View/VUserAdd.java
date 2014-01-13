package notizync.pc.gui.View;

import notizync.pc.core.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Create something.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class VUserAdd
    extends JFrame
{
    private Model m;

    // fields
    private JTextField tUser;
    private JPasswordField tPassword1, tPassword2;
    private JButton bReset, bCreate;

    public VUserAdd(Model m)
    {
        super("Benutzer anlegen");
        this.m = m;

        this.setSize(460, 320);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        JPanel p1 = new JPanel();
        GridLayout layout = new GridLayout(4,2);
        layout.setVgap(8);
        p1.setLayout(layout);
        p1.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 120));
        //p1.setBorder(new EmptyBorder(4,4,4,4));

        // header
        JLabel header = new JLabel("Benutzer anlegen");
        header.setFont(new Font("Tahoma", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 35));
        header.setBorder(new EmptyBorder(4,4,4,4));

        JLabel lblUser = new JLabel("Benutzername: ");
        lblUser.setBorder(new EmptyBorder(4,4,4,4));

        tUser = new JTextField("");

        JLabel lblPassword1 = new JLabel("Passwort:");
        lblPassword1.setBorder(new EmptyBorder(4,4,4,4));

        tPassword1 = new JPasswordField("");
        tPassword1.setBorder(new EmptyBorder(4,4,4,4));

        JLabel lblPassword2 = new JLabel("Passwort wiederholen:");
        lblPassword2.setBorder(new EmptyBorder(4,4,4,4));

        tPassword2 = new JPasswordField("");
        tPassword2.setBorder(new EmptyBorder(4,4,4,4));

        bCreate = new JButton("Erstellen");
        bReset = new JButton("Zurücksetzen");

        p1.add(lblUser);
        p1.add(tUser);
        p1.add(lblPassword1);
        p1.add(tPassword1);
        p1.add(lblPassword2);
        p1.add(tPassword2);
        p1.add(bCreate);
        p1.add(bReset);

        this.add(header, BorderLayout.NORTH);
        this.add(p1, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
