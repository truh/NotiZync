package notizync.pc.gui.View;

import javax.swing.*;

/**
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 07.11.13 10:44
 */
public class MenuBar
{
    public JMenuBar createBar()
    {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu note = new JMenu("Notiz");
        JMenu sync = new JMenu("Synchronisation");
        JMenu info = new JMenu("Hilfe");

        bar.add(file);
        bar.add(note);
        bar.add(sync);
        bar.add(info);

        JMenuItem close = new JMenuItem("Beenden");
        file.add(close);

        JMenuItem create = new JMenuItem("Neue Notiz");
        JMenuItem edit = new JMenuItem("Notiz bearbeiten");
        JMenuItem delete = new JMenuItem("Ausgewählte Notiz(en) löschen");
        JMenuItem reload = new JMenuItem("Notizen aktualisieren");
        JMenuItem clear = new JMenuItem("Alle Notizen löschen");

        note.add(create);
        note.add(edit);
        note.add(delete);
        note.add(reload);
        note.addSeparator();
        note.add(clear);

        JMenuItem manage = new JMenuItem("Storage Provider verwalten..");
        JMenuItem sync_start = new JMenuItem("Synchronisation manuell starten");

        sync.add(manage);
        sync.addSeparator();
        sync.add(sync_start);

        JMenuItem about = new JMenuItem("Über..");
        info.add(about);

        return bar;
    }
}
