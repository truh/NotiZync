package notizync.pc.core;

import notizync.core.http.HTTPStorageProvider;

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

    public Model()
    {
        this.ls = new LocalStorage();
        this.sp = new HTTPStorageProvider(null, null);

        if(!this.getSetting("sync_username").equals("") && !this.getSetting("sync_password").equals(""))
            this.spLoggedIn = this.sp.doLogin((String)this.getSetting("sync_username"), (String)this.getSetting("sync_password"));
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
    public boolean tryLogin(String username, String password)
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
    public boolean tryRegister(String username, String password)
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
    public boolean setContent(String title, String content)
    {

        return this.ls.setContent(title, content);
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
        return this.ls.putNote(title, content);
    }

    /**
     * Remove an already existing Note from our Storage.
     *
     * @param title Title of the Note to remove
     */
    public void removeNote(String[] title)
    {
        this.ls.removeNote(title);
    }

    /**
     * Get all currently stored Notes.
     *
     * @return A String Array containing the name of each Note.
     */
    public String[] getNoteList()
    {
        return this.ls.getNotes();
    }

    /**
     * Get the content of a Note.
     *
     * @param title Name of the Note to retrieve the contents for.
     * @return The content of the Note if it exists,otherwise null
     */
    public String getNote(String title)
    {
        return this.ls.getNote(title);
    }
}
