package notizync.core.loremipsum;

import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Does not really provide storage but some kind of random noteSet
 * ignores conflicts
 */
public class LoremStorageProvider implements IStorageProvider {
    private HashSet<INote> noteSet;

    /**
     * Default constructor
     */
    public LoremStorageProvider() {
        this.noteSet = new HashSet<>();
        for(int i = 0; i < 20; i++) {
            LoremNote note = new LoremNote();
            this.noteSet.add(note);
        }
    }

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    @Override
    public INote putNote(INote note) {
        boolean exists = false;
        INote existing = null;

        //compares title of new note to existing ones
        for(Iterator<INote> iterator = this.noteSet.iterator();
            iterator.hasNext(); existing = iterator.next()) {
            if(existing.getTitle().toString().equals(note.getTitle().toString())) {
                exists = true;
                break;
            }
        }

        this.noteSet.add(note);

        return exists ? existing : null;
    }

    /**
     * Removes the given note
     *
     * @param note note to remove
     * @return false if the given note did not exists
     */
    @Override
    public boolean removeNote(INote note) {
        return this.noteSet.remove(note);
    }

    /**
     * @return set of notes stored by this StorageProvider
     */
    @Override
    public Set<INote> getNoteSet() {
        return new HashSet<>(this.noteSet);
    }

    /**
     * Writes changes to destination
     * Reads updates from source
     */
    @Override
    public void sync() {

    }
}
