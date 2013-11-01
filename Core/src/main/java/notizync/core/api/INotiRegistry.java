package notizync.core.api;

/**
 *
 */
public interface INotiRegistry {
    /**
     *
     * @param storageProvider
     * @return
     */
    public boolean addStorageProvider(IStorageProvider storageProvider);

    /**
     *
     */
    public boolean addNoteUpdateListener(INoteUpdateListener noteUpdateListener);
}
