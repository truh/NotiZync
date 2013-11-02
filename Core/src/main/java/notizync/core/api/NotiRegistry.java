package notizync.core.api;

import notizync.core.conflict.IConflict;

import java.util.HashSet;

/**
 *
 */
public final class NotiRegistry implements INotiRegistry {
    private HashSet <IStorageProvider> storageProviders;
    private HashSet <INoteUpdateListener> noteUpdateListeners;

    public NotiRegistry() {

    }

    @Override
    public boolean addStorageProvider(IStorageProvider storageProvider) {
        return storageProviders.add(storageProvider);
    }

    @Override
    public boolean addNoteUpdateListener(INoteUpdateListener noteUpdateListener) {
        return noteUpdateListeners.add(noteUpdateListener);
    }

    /**
     * Tries to solve the given conflict
     *
     * @param conflict the conflict that should be solved
     * @return solution or null if no solution could be found
     */
    @Override
    public INote negotiate(IConflict conflict) {
        return null;
    }
}
