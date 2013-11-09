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
package notizync.core.ftp;

import notizync.core.api.INote;
import notizync.core.api.IUpdateEventDistributor;
import notizync.core.api.IStorageProvider;
import notizync.core.conflict.IConflict;
import notizync.core.conflict.INegotiator;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of IStorageProvider based on the FTP protocol
 */
public final class FTPStorageProvider implements IStorageProvider {

    private IUpdateEventDistributor eventDistributor;
    private INegotiator negotiator;

    private IFTPLoginData loginData;
    private FTPClient ftpClient;

    private HashSet<INote> noteSet;

    /**
     *
     * @param eventDistributor distributor that should be informed
     * @param negotiator negotiator that should solve the conflicts
     * @param loginData login information
     */
    public FTPStorageProvider(IUpdateEventDistributor eventDistributor,
                              INegotiator negotiator,
                              IFTPLoginData loginData) {
        this.eventDistributor = eventDistributor;
        this.negotiator = negotiator;

        this.loginData = loginData;
        this.ftpClient = new FTPClient();
    }

    /**
     *
     * @param eventDistributor distributor that should be informed
     * @param negotiator negotiator that should solve the conflicts
     * @param loginData login information
     * @param ftpClientConfig configurations to apply to ftpClient
     */
    public FTPStorageProvider(IUpdateEventDistributor eventDistributor,
                              INegotiator negotiator,
                              IFTPLoginData loginData,
                              FTPClientConfig ftpClientConfig) {
        this(eventDistributor, negotiator, loginData);

        this.ftpClient.configure(ftpClientConfig);
    }

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    @Override
    public INote putNote(INote note) {
        boolean exists = false;
        INote existing = null;

        //compares title of new note to existing ones
        for(Iterator<INote> iterator = this.noteSet.iterator();
            iterator.hasNext(); existing = iterator.next()) {
            if(existing.getTitle().toString().equals(note.getTitle().toString())) {
                exists = true;
                break;
            }
        }

        if(exists) {
            // which of the note should be kept
            IConflict conflict = note.clash(existing);
            note = this.negotiator.negotiate(conflict);

            this.noteSet.remove(existing);
        }

        this.noteSet.add(note);

        return exists ? existing : null;
    }

    /**
     * Removes the given note
     *
     * @param note note to remove
     * @return false if the given note did not exists
     */
    @Override
    public boolean removeNote(INote note) {
        return false;  //TODO implement it
    }

    /**
     * @return set of notes stored by this StorageProvider
     */
    @Override
    public Set<INote> getNoteSet() {
        return new HashSet<>(this.noteSet);
    }

    /**
     * Writes changes to destination
     * Reads updates from source
     */
    @Override
    public void sync() {

    }
}
