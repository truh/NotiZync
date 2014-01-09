package notizync.pc.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * This Class handles all data exchanged between the GUI and the Backend.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class Model
{
    private HashMap<String, String> myNotes = new HashMap<String, String>();

    public Model()
    {
        String text;
        Random r = new Random();

        for(int i = 1; i <= 100; i++)
        {
            text = "";
            for(int j = 1; j <= 200; j++)
            {
                text += (char)r.nextInt('z'-'A')+'A';
            }

            this.myNotes.put("Notiz #"+i, text);
        }
    }

    public boolean setContent(String title, String content)
    {
        if(!this.myNotes.containsKey(title)) return false;

        this.myNotes.remove(title);
        this.myNotes.put(title, content);

        return true;
    }

    public boolean putNote(String title, String content)
    {
        if(this.myNotes.containsKey(title)) return false;

        this.myNotes.put(title, content);

        return true;
    }

    public String[] getNoteList()
    {
        Set keys = this.myNotes.keySet();
        Iterator it = keys.iterator();

        String[] notes = new String[this.myNotes.size()];
        int i = 0;

        while(it.hasNext())
        {
            notes[i] = (String)it.next();
            i++;
        }
        return notes;
    }

    public String getNote(String title)
    {
        return (String)this.myNotes.get(title);
    }
}
