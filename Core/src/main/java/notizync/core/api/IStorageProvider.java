package notizync.core.api;

import java.util.Set;

/**
 *
 */
public interface IStorageProvider {

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    public INote putNote(INote note);

    /**
     * @return set of notes stored by this StorageProvider
     */
    public Set<? extends INote> getNoteSet();

    /**
     * Writes changes to destination
     * Reads updates from source
     *
     * Might do nothing if StorageProvider works unbuffered.
     */
    public void sync();
}
