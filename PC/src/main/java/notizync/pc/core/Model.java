package notizync.pc.core;

import notizync.core.api.INote;
import notizync.core.basics.BasicNote;

import java.util.*;

/**
 * Interface between our GUI and the backend (both local storage and remote).
 *
 * @author Andreas Willinger
 * @version 1.0
 */
public class Model
{
    private LinkedList<INote> myNotes = new LinkedList<INote>();
    private LocalStorage ls;

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
            INote note = new BasicNote("Notiz #"+i, text, new java.util.Date().getTime());
            this.myNotes.add(note);
        }

        ls = new LocalStorage();
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
        INote note = null;

        for(INote n:this.myNotes)
        {
            if(n.getTitle().equals(title))
            {
                note = n;
                break;
            }
        }

        if(note == null) return false;

        note.setContent(content);

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
        boolean exists = false;
        for(INote n:this.myNotes)
        {
            if(n.getTitle().equals(title))
            {
                exists = true;
                break;
            }
        }

        if(exists) return false;
        INote note = new BasicNote(title, content, new java.util.Date().getTime());

        this.myNotes.add(note);

        return true;
    }

    /**
     * Remove an already existing Note from our Storage.
     *
     * @param title Title of the Note to remove
     */
    public void removeNote(String[] title)
    {
        Iterator<INote> it;

        for(int i = 0; i < title.length; i++)
        {
            it = this.myNotes.iterator();
            while(it.hasNext())
            {
                INote n = it.next();

                if(n.getTitle().equals(title[i]))
                {
                    this.myNotes.remove(n);
                    break;
                }
            }
        }
    }

    /**
     * Get all currently stored Notes.
     *
     * @return A String Array containing the name of each Note.
     */
    public String[] getNoteList()
    {
        /*String[] notes = new String[this.myNotes.size()];
        int i = 0;

        for(INote n:this.myNotes)
        {
            notes[i] = n.getTitle();
            i++;
        }  */


        INote[] notes = this.ls.getNotes();
        String[] titles= new String[notes.length];
        int i = 0;

        for(INote n:notes)
        {
            titles[i] = n.getTitle();
            i++;
        }

        // sort it
        Arrays.sort(titles);

        return titles;
    }

    /**
     * Get the content of a Note.
     *
     * @param title Name of the Note to retrieve the contents for.
     * @return The content of the Note if it exists,otherwise null
     */
    public String getNote(String title)
    {
        String content = "";

        for(INote n:this.myNotes)
        {
            if(n.getTitle().equals(title))
            {
                content = n.getContent();
                break;
            }
        }

        return content;
    }
}
