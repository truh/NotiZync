package notizync.pc.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import notizync.core.api.INote;
import notizync.core.basics.BasicNote;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Manage the local Storage of all Notes currently available in the Application.
 *
 * @author Andreas Willinger
 * @version 1.2
 */
public class LocalStorage
{
    private Storage myStorage;
    private Gson json;

    private File f;

    public LocalStorage()
    {
        this.json = new GsonBuilder().setPrettyPrinting().create();

        try
        {
            this.f = new File(System.getProperty("user.home")+"/notizync_storage.json");
            if(!this.f.exists()) f.createNewFile();

            BufferedReader br = new BufferedReader(new FileReader(this.f));

            String data = "";
            String line = "";
            while((line = br.readLine()) != null)
            {
                 data += line;
            }
            br.close();

            if(data.length() < 3)
            {
                initFile();
            }
            else
            {
                this.myStorage = this.json.fromJson(data, Storage.class);
            }
        }
        catch(IOException e)
        {
            System.out.println("Fatal error");
        }
    }

    /**
     * Update the locally stored Setting value.
     *
     * @param name name of the setting
     * @param value new value
     * @param write true: directly write on harddisk, false: only chagne in memory
     * @return true on success, false on failure (Invalid setting, JSON failure)
     */
    public boolean updateSetting(String name, Object value, boolean write)
    {
        switch(name)
        {
            case "sync_interval":
                this.myStorage.settings.sync_interval = (int)value;
                break;
            case "sync_username":
                this.myStorage.settings.sync_username = (String)value;
                break;
            case "sync_password":
                this.myStorage.settings.sync_password = (String)value;
                break;
            case "sync_save":
                this.myStorage.settings.sync_save = (boolean)value;
                break;
            default:
                return false;
        }

        if(write)
        {
            if(!this.myStorage.settings.sync_save)
            {
                String oldPassword = this.myStorage.settings.sync_password;
                String oldUsername = this.myStorage.settings.sync_username;

                this.myStorage.settings.sync_password = "";
                this.myStorage.settings.sync_username = "";
                boolean json = this.saveJson();

                this.myStorage.settings.sync_password = oldPassword;
                this.myStorage.settings.sync_password = oldUsername;

                return json;
            }
            else
            {
                return this.saveJson();
            }
        }
        return true;
    }

    /**
     * Return the current value of a Setting.
     *
     * @param name name of the setting
     * @return An Object or false, if the setting was not found
     */
    public Object getSetting(String name)
    {
        switch(name)
        {
            case "sync_interval":
                return this.myStorage.settings.sync_interval;
            case "sync_username":
                return this.myStorage.settings.sync_username;
            case "sync_password":
                return this.myStorage.settings.sync_password;
            case "sync_save":
                return this.myStorage.settings.sync_save;
            default:
                return false;
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
        INote note = null;

        for(INote n:this.myStorage.notes)
        {
            if(n.getTitle().equals(title))
            {
                note = n;
                break;
            }
        }

        if(note == null) return false;

        note.setContent(content);
        note.setTimestamp(new java.util.Date().getTime());

        return this.saveJson();
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
        for(INote n:this.myStorage.notes)
        {
            if(n.getTitle().equals(title))
            {
                exists = true;
                break;
            }
        }

        if(exists) return false;
        BasicNote note = new BasicNote(title, content, new java.util.Date().getTime());

        this.myStorage.notes.add(note);

        return this.saveJson();
    }

    /**
     * Remove an already existing Note from our Storage.
     *
     * @param title Title of the Note to remove
     */
    public void removeNote(String[] title)
    {
        Iterator<BasicNote> it;

        for(int i = 0; i < title.length; i++)
        {
            it = this.myStorage.notes.iterator();
            while(it.hasNext())
            {
                INote n = it.next();

                if(n.getTitle().equals(title[i]))
                {
                    this.myStorage.notes.remove(n);
                    break;
                }
            }
        }

        this.saveJson();
    }

    /**
     * Get all currently stored Notes.
     *
     * @return A String Array containing the name of each Note.
     */
    public String[] getNotes()
    {
        String[] notes = new String[this.myStorage.notes.size()];
        int i = 0;

        for(INote n:this.myStorage.notes)
        {
            notes[i] = n.getTitle();
            i++;
        }

        // sort it
        Arrays.sort(notes);
        return notes;
    }

    public ArrayList getNoteList()
    {
        return this.myStorage.notes;
    }

    /**
     * Get the content of a Note.
     *
     * @param title Name of the Note to retrieve the contents for.
     * @return The content of the Note if it exists,otherwise null
     */
    public String getNote(String title)
    {
        String content = null;

        for(INote n:this.myStorage.notes)
        {
            if(n.getTitle().equals(title))
            {
                content = n.getContent();
                break;
            }
        }

        return content;
    }

    /**
     * If a new Local Storage is created or an existing File is empty, create the basic structure of the File.
     * DO NOT call this if the target is already a valid JSON file!
     */
    private void initFile()
    {
        Storage s = new Storage();
        s.createEmpty();

        this.myStorage = s;
        this.saveJson();
    }

    /**
     * Save the currently cached Storage Object into a JSON file.
     *
     * @return true on success, false on failure (missing Permissions, Disk error..)
     */
    private boolean saveJson()
    {
        String json = this.json.toJson(this.myStorage);

        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.f));
            bw.write(json);
            bw.close();

            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }

    public class Storage
    {
        public Settings settings;
        public ArrayList<BasicNote> notes;

        public void createEmpty()
        {
            this.settings = new Settings();
            this.notes = new ArrayList<BasicNote>();
        }

        public class Settings
        {
            public int sync_interval = -1;
            public String sync_username = "";
            public String sync_password = "";
            public boolean sync_save = false;
        }
    }
}
