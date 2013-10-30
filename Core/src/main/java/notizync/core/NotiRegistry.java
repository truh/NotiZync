package notizync.core;

import java.util.HashSet;

/**
 *
 */
public final class NotiRegistry implements INotiRegistry {
    private HashSet <IStorageProvider> storageProviders;
    private HashSet <INoteDisplay> clients;

    public NotiRegistry() {

    }

    @Override
    public boolean addStorageProvider(IStorageProvider storageProvider) {
        return storageProviders.add(storageProvider);
    }

    @Override
    public boolean addNoteClient(INoteDisplay client) {
        return clients.add(client);
    }
}
