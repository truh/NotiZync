package notizync.pc.gui.View;

import notizync.pc.core.Model;
import notizync.pc.gui.Controller.CSettings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Manage all Settings of the Program.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class VSettings
    extends JFrame
{
    private Model m;
    private CSettings c;

    // fields
    private JTextField tInterval;
    private JTextField tUser;
    private JPasswordField tPassword;
    private JButton bLogin, bReset, bCreate;
    private JCheckBox autoLogin;

    public VSettings(Model m)
    {
        super("NotiZync Einstellungen");
        this.m = m;
        this.c = new CSettings(this.m, this);

        this.setSize(600, 320);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        JPanel p1 = new JPanel();

        // header
        JLabel header = new JLabel("Synchronisation");
        header.setFont(new Font("Tahoma", Font.BOLD, 15));
        header.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 35));
        header.setBorder(new EmptyBorder(4,4,4,4));

        // main window
        JPanel p2 = new JPanel();
        GridLayout layout = new GridLayout(4,2);
        layout.setVgap(8);
        p2.setLayout(layout);
        p2.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 120));
        p2.setBorder(new EmptyBorder(4,4,4,4));

        JLabel lblInterval = new JLabel("Auto-Synchronisation durchführen alle (minuten): ");
        lblInterval.setBorder(new EmptyBorder(4,4,4,4));

        tInterval = new JTextField(5);
        tInterval.setPreferredSize(new Dimension(80, 30));

        JLabel lblUser = new JLabel("Benutzername");
        lblUser.setBorder(new EmptyBorder(4,4,4,4));

        tUser = new JTextField("");
        tUser.setBorder(new EmptyBorder(4,4,4,4));

        JLabel lblPassword = new JLabel("Passwort");
        lblPassword.setBorder(new EmptyBorder(4,4,4,4));

        tPassword = new JPasswordField("");
        tPassword.setBorder(new EmptyBorder(4,4,4,4));

        bLogin = new JButton("Anmelden");
        bReset = new JButton("Zurücksetzen");

        autoLogin = new JCheckBox("Anmeldedaten verschlüsselt speichern");
        autoLogin.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 25));

        bCreate = new JButton();
        bCreate.setText("<HTML><FONT color=\"#000099\">Noch kein Account? Jetzt einen anlegen!</FONT></HTML>");
        bCreate.setHorizontalAlignment(SwingConstants.LEFT);
        bCreate.setBorderPainted(false);
        bCreate.setOpaque(false);
        bCreate.setBackground(Color.WHITE);
        bCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bCreate.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 25));
        bCreate.setBorder(new EmptyBorder(4,4,4,4));
        bCreate.addActionListener(this.c);

        p2.add(lblInterval);
        p2.add(tInterval);
        p2.add(lblUser);
        p2.add(tUser);
        p2.add(lblPassword);
        p2.add(tPassword);
        p2.add(bLogin);
        p2.add(bReset);

        p1.add(header);
        p1.add(p2);
        p1.add(autoLogin);
        p1.add(bCreate);

        JPanel p3 = new JPanel();
        p3.add(new JButton("Speichern"));
        p3.add(new JButton("Abbrechen"));

        this.add(p1, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    // getter methods
    public JButton getCreateButton()
    {
        return this.bCreate;
    }
}
