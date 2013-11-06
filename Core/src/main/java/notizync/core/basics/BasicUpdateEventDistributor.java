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
package notizync.core.basics;

import notizync.core.api.INoteUpdateEvent;
import notizync.core.api.INoteUpdateListener;
import notizync.core.api.IStorageProvider;
import notizync.core.api.IUpdateEventDistributor;
import notizync.core.conflict.IConflict;

import java.util.HashSet;

/**
 *
 */
public final class BasicUpdateEventDistributor implements IUpdateEventDistributor {
    private HashSet <INoteUpdateListener> noteUpdateListeners;

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
     * Informs all the added update listeners
     *
     * @param noteUpdate new version of the note
     */
    @Override
    public void noteUpdate(INoteUpdateEvent noteUpdate) {
        for(INoteUpdateListener updateListener : this.noteUpdateListeners) {
            updateListener.noteUpdate(noteUpdate);
        }
    }
}
