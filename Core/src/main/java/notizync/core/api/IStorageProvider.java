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

import java.util.Set;

/**
 *
 */
public interface IStorageProvider
{
    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    public INote putNote(INote note) throws RuntimeException;

    /**
     * Removes a note from the storage.
     *
     * @param note note that should be removed
     * @return true, if the note was successfully removed; false, if there was an error or the note doesn't exist
     */
    public boolean removeNote(INote note);

    /**
     * @return set of notes stored by this StorageProvider
     */
    public Set<? extends INote> getNoteSet();

    /**
     * Writes changes to destination
     * Reads updates from source
     *
     * Might do nothing if StorageProvider works unbuffered.
     */
    public void sync();
}
