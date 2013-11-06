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
 * TODO maybe split it into multiple interfaces for both adding storage provider and update listener
 */
public final class NotiRegistry implements IUpdateEventDistributor {
    private HashSet <IStorageProvider> storageProviders;
    private HashSet <INoteUpdateListener> noteUpdateListeners;

    public NotiRegistry() {

    }
    /**
     * Register a note update listener
     *
     * all the note update listeners should be informed when a update occurs
     *
     * @param noteUpdateListener the update listener that should be registered
     * @return was registering successful?
     */
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
