package notizync.pc.gui.Controller;

import notizync.pc.core.Model;
import notizync.pc.gui.View.VNoteAdd;
import notizync.pc.gui.View.VNoteList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * -- DESCRIPTION --
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 09.01.14 11:12
 */
public class CNoteAdd implements ActionListener
{
    private Model m;
    private VNoteAdd v;
    private VNoteList l;

    public CNoteAdd(Model m, VNoteList l, VNoteAdd v)
    {
        this.m = m;
        this.v = v;
        this.l = l;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton source = (JButton)e.getSource();

        if(source == this.v.getSave())
        {
            if(this.m.putNote(this.v.getHeader().getText(), this.v.getContent().getText()))
            {
                this.l.update();

                this.v.setVisible(false);
                this.v.dispose();
            }
            else
            {
                System.out.println("error");
            }
        }
        else if(source == this.v.getCancel())
        {
            this.v.setVisible(false);
            this.v.dispose();
        }
        else
        {
            throw new NotImplementedException();
        }
    }
}
