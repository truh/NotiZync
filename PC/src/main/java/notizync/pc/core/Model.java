package notizync.pc.core;

import java.util.*;

/**
 * Interface between our GUI and the backend (both local storage and remote).
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class Model
{
    private HashMap<String, String> myNotes = new HashMap<String,String>();

    public Model()
    {
        // replace the following
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

    /**
     * Set the Text of a Note.
     *
     * @param title The Note to edit
     * @param content The new content
     * @return true, if the update was successful, false if the note doesn't exist
     */
    public boolean setContent(String title, String content)
    {
        if(!this.myNotes.containsKey(title)) return false;

        this.myNotes.remove(title);
        this.myNotes.put(title, content);

        return true;
    }

    /**
     * Add a new Note to our storage.
     *
     * @param title Title of the Note.
     * @param content Content
     * @return true, if saving was successful, false a note with the same name already exists.
     */
    public boolean putNote(String title, String content)
    {
        if(this.myNotes.containsKey(title)) return false;

        this.myNotes.put(title, content);

        return true;
    }

    /**
     * Remove an already existing Note from our Storage.
     *
     * @param title Title of the Note to remove
     */
    public void removeNote(String[] title)
    {
        for(String item:title)
        {
            this.myNotes.remove(item);
        }
    }

    /**
     * Get all currently stored Notes.
     *
     * @return A String Array containing the name of each Note.
     */
    public String[] getNoteList()
    {
        // we only need the names, not content
        Set keys = this.myNotes.keySet();
        Iterator it = keys.iterator();

        String[] notes = new String[this.myNotes.size()];
        int i = 0;

        while(it.hasNext())
        {
            notes[i] = (String)it.next();
            i++;
        }

        // sort it
        Arrays.sort(notes);

        return notes;
    }

    /**
     * Get the content of a Note.
     *
     * @param title Name of the Note to retrieve the contents for.
     * @return The content of the Note if it exists,otherwise null
     */
    public String getNote(String title)
    {
        return this.myNotes.get(title);
    }
}
