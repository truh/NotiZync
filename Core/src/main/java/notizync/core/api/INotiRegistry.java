package notizync.core.api;

import notizync.core.conflict.INegotiationRoutine;

/**
 *
 */
public interface INotiRegistry extends INegotiationRoutine {
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
