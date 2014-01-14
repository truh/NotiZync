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
    private JButton bLogin, bReset, bCreate, bSave, bCancel;
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
        layout.setHgap(2);
        p2.setLayout(layout);
        p2.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 120));

        JLabel lblInterval = new JLabel("Auto-Synchronisation durchführen alle (minuten): ");
        lblInterval.setBorder(new EmptyBorder(4,4,4,4));

        this.tInterval = new JTextField(""+this.m.getSetting("sync_interval"));

        JLabel lblUser = new JLabel("Benutzername");
        lblUser.setBorder(new EmptyBorder(4,4,4,4));

        this.tUser = new JTextField((String)this.m.getSetting("sync_username"));
        this.tUser.setBorder(new EmptyBorder(4,4,4,4));

        JLabel lblPassword = new JLabel("Passwort");
        lblPassword.setBorder(new EmptyBorder(4,4,4,4));

        this.tPassword = new JPasswordField((String)this.m.getSetting("sync_password"));
        this.tPassword.setBorder(new EmptyBorder(4,4,4,4));

        this.bLogin = new JButton("Anmelden");
        if(this.m.isLoggedIn())
        {
            this.bLogin.setText("Angemeldet");
            this.bLogin.setEnabled(false);
        }
        this.bLogin.addActionListener(this.c);
        this.bReset = new JButton("Zurücksetzen");
        this.bReset.addActionListener(this.c);

        this.autoLogin = new JCheckBox("Anmeldedaten verschlüsselt speichern");
        this.autoLogin.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 25));
        if((boolean)this.m.getSetting("sync_save")) this.autoLogin.setSelected(true);

        this.bCreate = new JButton();
        this.bCreate.setText("<HTML><FONT color=\"#000099\">Noch kein Account? Jetzt einen anlegen!</FONT></HTML>");
        this.bCreate.setHorizontalAlignment(SwingConstants.LEFT);
        this.bCreate.setBorderPainted(false);
        this.bCreate.setOpaque(false);
        this.bCreate.setBackground(Color.WHITE);
        this.bCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.bCreate.setPreferredSize(new Dimension(((int) this.getSize().getWidth())-8, 25));
        this.bCreate.setBorder(new EmptyBorder(4,4,4,4));
        this.bCreate.addActionListener(this.c);

        p2.add(lblInterval);
        p2.add(this.tInterval);
        p2.add(lblUser);
        p2.add(this.tUser);
        p2.add(lblPassword);
        p2.add(this.tPassword);
        p2.add(this.bLogin);
        p2.add(this.bReset);

        p1.add(header);
        p1.add(p2);
        p1.add(this.autoLogin);
        p1.add(this.bCreate);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1,2));
        this.bSave = new JButton("Speichern");
        this.bSave.addActionListener(this.c);
        this.bCancel = new JButton("Abbrechen");
        this.bCancel.addActionListener(this.c);

        p3.add(this.bSave);
        p3.add(this.bCancel);

        this.add(p1, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    // getter methods
    public JTextField getIntervalField()
    {
        return this.tInterval;
    }
    public JTextField getUserField()
    {
        return this.tUser;
    }
    public JPasswordField getPasswordField()
    {
        return this.tPassword;
    }
    public JButton getLoginButton()
    {
        return this.bLogin;
    }
    public JButton getResetButton()
    {
        return this.bReset;
    }
    public JButton getCreateButton()
    {
        return this.bCreate;
    }
    public JButton getSaveButton()
    {
        return this.bSave;
    }

    public JButton getCancelButton()
    {
        return this.bCancel;
    }
    public JCheckBox getAutoCheckbox()
    {
        return this.autoLogin;
    }

}
