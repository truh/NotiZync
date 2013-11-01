package notizync.core.api;

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
}
