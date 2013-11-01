package notizync.core.api;

/**
 *
 */
public interface INoteUpdateListener {
    /**
     * Informs the listener about a change in a note.
     *
     * @param noteUpdate new version of the note
     */
    public void noteUpdate(INoteUpdateEvent noteUpdate);
}
