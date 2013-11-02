package notizync.core.ftp;

import notizync.core.api.INote;
import notizync.core.api.INotiRegistry;
import notizync.core.api.IStorageProvider;
import notizync.core.conflict.IConflict;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of IStorageProvider based on the FTP protocol
 */
public final class FTPStorageProvider implements IStorageProvider {

    private INotiRegistry notiRegistry;
    private FTPClient ftpClient;
    private String remoteDirectory;
    private HashSet<INote> noteSet;

    /**
     *
     * @param ftpClient a FTPClient instance that should be used for storage
     * @param remoteDirectory the path on the remote server where files should be stored
     */
    public FTPStorageProvider(INotiRegistry notiRegistry, FTPClient ftpClient, String remoteDirectory) {
        this(notiRegistry, ftpClient, remoteDirectory, null);
    }

    /**
     *
     * @param ftpClient a FTPClient instance that should be used for storage
     * @param remoteDirectory the path on the remote server where files should be stored
     * @param ftpClientConfig configurations to apply to ftpClient
     */
    public FTPStorageProvider(INotiRegistry notiRegistry, FTPClient ftpClient, String remoteDirectory, FTPClientConfig ftpClientConfig) {
        if (ftpClient == null) {
            throw new NullPointerException("FTPStorageProvider#FTPStorageProvider(,,) ftpClient must not be null!");
        }
        if(ftpClientConfig != null) {
            ftpClient.configure(ftpClientConfig);
        }

        this.notiRegistry = notiRegistry;
        this.ftpClient = ftpClient;
        this.remoteDirectory = remoteDirectory;
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
        for(Iterator<INote> iterator = this.noteSet.iterator(); iterator.hasNext(); existing = iterator.next()) {
            if(existing.getTitle().toString().equals(note.getTitle().toString())) {
                exists = true;
                break;
            }
        }

        if(exists) {
            // which of the note should be kept
            IConflict conflict = note.clash(existing);
            note = this.notiRegistry.negotiate(conflict);

            this.noteSet.remove(existing);
        }

        this.noteSet.add(note);

        return exists ? existing : null;
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
