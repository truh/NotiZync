package notizync.core;

import java.util.HashSet;

/**
 *
 */
public final class NotiRegistry implements INotiRegistry {
    private HashSet <IStorageProvider> storageProviders;

    public NotiRegistry() {

    }

    public boolean addStorageProvider(IStorageProvider storageProvider) {
        return storageProviders.add(storageProvider);
    }
}
