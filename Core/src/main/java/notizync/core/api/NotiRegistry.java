/*
    Copyright 2013 Andreas Willinger, Andreas Vogt, Matthias El-Far, Jakob Klepp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
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
