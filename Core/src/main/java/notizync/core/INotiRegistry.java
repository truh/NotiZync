package notizync.core;

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
    public boolean addNoteClient(INoteDisplay client);
}
