package notizync.pc.core;

/**
 * Interface between our GUI and the backend (both local storage and remote).
 *
 * @author Andreas Willinger
 * @version 1.1
 */
public class Model
{
    private LocalStorage ls;

    public enum EResult
    {
        _,
        k_Unknown,
        k_Success,
        k_RemoteDown,
        k_RemoteNotLoggedIn,
        k_RemoteSessionExpired,
        k_RemoteInvalidUserPassword,
        k_RemoteNoteExists,
        k_RemoteInvalidNote,
        k_RemoteDatabaseFailure,
        k_RemoteSuccess,
        k_LocalPermissionsError,
        k_LocalConcurrentException,
        k_LocalGeneralError,
        k_LocalSuccess
    }

    public Model()
    {
        ls = new LocalStorage();
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
