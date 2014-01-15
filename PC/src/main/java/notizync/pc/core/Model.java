package notizync.pc.core;

import notizync.core.api.INote;
import notizync.core.api.IRemoteUpdateListener;
import notizync.core.basics.BasicNote;
import notizync.core.http.EResult;
import notizync.core.http.HTTPStorageProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Interface between our GUI and the backend (both local storage and remote).
 *
 * @author Andreas Willinger
 * @version 1.1
 */
public class Model
{
    private LocalStorage ls;

    private HTTPStorageProvider sp;
    private boolean spLoggedIn = false;

    private java.util.Timer timer;
    private List<IRemoteUpdateListener> listeners;

    public Model()
    {
        this.ls = new LocalStorage();
        this.sp = new HTTPStorageProvider(null, null);

        this.listeners = new LinkedList<IRemoteUpdateListener>();

        if(!this.getSetting("sync_username").equals("") && !this.getSetting("sync_password").equals(""))
        {
            EResult result = this.sp.doLogin((String)this.getSetting("sync_username"), (String)this.getSetting("sync_password"));

            if(result == EResult.k_RemoteSuccess)
            {
                this.spLoggedIn = true;
                this.setupTimer((int) this.getSetting("sync_interval"));
            }
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
        return this.ls.updateSetting(name, value, write);
    }

    /**
     * Return the current value of a Setting.
     *
     * @param name name of the setting
     * @return An Object or false, if the setting was not found
     */
    public Object getSetting(String name)
    {
        return this.ls.getSetting(name);
    }

    /**
     * Determine whether or not the User is currently logged in into the remote StorageProvider.
     *
     * @return true, if logged in, false if not
     */
    public boolean isLoggedIn()
    {
        return this.spLoggedIn;
    }

    /**
     * Set the login state of the remote StorageProvider.
     * Only call this after being successfully logged in!
     *
     * @param value true to set to logged in, false to 'log out'
     */
    public void setLoggedIn(boolean value)
    {
        this.spLoggedIn = value;
    }

    /**
     * Attempt to login into the remote StorageProvider, if successful, directly save the session token.
     *
     * @param username the user to login as
     * @param password the user's password
     * @return true, if login was successful, false if not
     */
    public EResult tryLogin(String username, String password)
    {
        return this.sp.doLogin(username, password);
    }

    /**
     * Attempt to register a new user in the remote StorageProvider.
     *
     * @param username the user to create
     * @param password the user's password
     * @return true, if registration was successful, false if not
     */
    public EResult tryRegister(String username, String password)
    {
        return this.sp.doRegister(username, password);
    }

    /**
     * Set the Text of a Note.
     *
     * @param title The Note to edit
     * @param content The new content
     * @return true, if the update was successful, false if the note doesn't exist
     */
    public EResult setContent(String title, String content)
    {
        if(this.spLoggedIn)
        {
            EResult remote = this.sp.putNote(new BasicNote(title, content, new java.util.Date().getTime()));

            if(remote != EResult.k_RemoteUpdateSuccess) return remote;
        }
        return this.ls.setContent(title, content)?EResult.k_Success:EResult.k_LocalFailure;
    }

    /**
     * Add a new Note to our storage.
     *
     * @param title Title of the Note.
     * @param content Content
     * @return true, if saving was successful, false a note with the same name already exists.
     */
    public EResult putNote(String title, String content)
    {
        if(this.spLoggedIn)
        {
            EResult remote = this.sp.putNote(new BasicNote(title, content, new java.util.Date().getTime()));

            if(remote != EResult.k_RemoteSuccess) return remote;
        }
        return this.ls.putNote(title, content)?EResult.k_Success:EResult.k_LocalFailure;
    }

    /**
     * Remove an already existing Note from our Storage.
     *
     * @param title Title of the Note to remove
     */
    public void removeNote(String[] title)
    {
        if(this.spLoggedIn)
        {
            for(String t:title)
            {
                System.out.println(this.sp.removeNote(new BasicNote(t, "", 0)));
            }
        }
        this.ls.removeNote(title);
    }

    /**
     * Get all currently stored Notes.
     *
     * @return A String Array containing the name of each Note.
     */
    public String[] getNoteList()
    {
        if(this.spLoggedIn)
        {
            List<INote> remote = this.sp.getNoteList();
            String[] notes = new String[remote.size()];

            int i = 0;
            for(INote n:remote)
            {
                notes[i] = n.getTitle();
                i++;

                if(this.ls.getNote(n.getTitle()) == null)
                    this.ls.putNote(n.getTitle(), n.getContent());
                else
                    this.ls.setContent(n.getTitle(), n.getContent());
            }

            return notes;
        }
        else
        {
            return this.ls.getNotes();
        }
    }

    /**
     * Get the content of a Note.
     *
     * @param title Name of the Note to retrieve the contents for.
     * @return The content of the Note if it exists,otherwise null
     */
    public String getNote(String title)
    {
        if(this.spLoggedIn)
        {
            List<INote> notes = this.sp.getNoteList();
            String content ="";

            for(INote n:notes)
            {
                if(n.getTitle().equals(title))
                {
                    content = n.getContent();
                    break;
                }
            }
            return content;
        }
        else
        {
            return this.ls.getNote(title);
        }
    }

    /**
     * Setup an update timer with the given Interval.
     *
     * @param interval the interval to run at, in minutes
     */
    public void setupTimer(int interval)
    {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                sp.getNotes();

                for(IRemoteUpdateListener l:listeners)
                {
                    l.update();
                }
            }
        }, 0, interval*60000);
    }

    /**
     * Kill all currently active Timer tasks.
     */
    public void killTimer()
    {
        if(this.timer != null)
        {
            this.timer.cancel();
            this.timer = null;
        }
    }

    /**
     * Re-setup the Timer
     */
    public void updateTimer()
    {
        this.killTimer();
        this.setupTimer((int)getSetting("sync_interval"));
    }

    public void addUpdateListener(IRemoteUpdateListener listener)
    {
        this.listeners.add(listener);
    }

    public void removeUpdateListener(IRemoteUpdateListener listener)
    {
        this.listeners.remove(listener);
    }
}
