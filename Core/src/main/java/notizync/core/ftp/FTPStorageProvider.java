package notizync.core.ftp;

import notizync.core.api.INote;
import notizync.core.api.INotiRegistry;
import notizync.core.api.IStorageProvider;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.util.Set;

/**
 * An implementation of IStorageProvider based on the FTP protocol
 */
public final class FTPStorageProvider implements IStorageProvider {

    private INotiRegistry notiRegistry;
    private FTPClient ftpClient;
    private String remoteDirectory;

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
        return null;
    }

    /**
     * @return set of notes stored by this StorageProvider
     */
    @Override
    public Set<? extends INote> getNoteSet() {
        return null;
    }

    /**
     * Writes changes to destination
     * Reads updates from source
     */
    @Override
    public void sync() {

    }
}
