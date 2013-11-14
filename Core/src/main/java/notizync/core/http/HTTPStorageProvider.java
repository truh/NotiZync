package notizync.core.http;

import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;
import notizync.core.api.IUpdateEventDistributor;
import notizync.core.conflict.IConflict;
import notizync.core.conflict.INegotiator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of IStorageProvider based on the HTTP Protocol and an Web-API.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 14.11.13 08:45
 */
public class HTTPStorageProvider
    implements IStorageProvider
{
    private IUpdateEventDistributor eventDistributor;
    private INegotiator negotiator;

    private String username;
    private String password;

    private HttpURLConnection backend;

    private HashSet<INote> noteSet;

    /**
     *
     * @param eventDistributor
     * @param negotiator
     * @param username
     * @param password
     */
    public HTTPStorageProvider(IUpdateEventDistributor eventDistributor,
                              INegotiator negotiator,
                              String username,
                              String password)
    {
        this.eventDistributor = eventDistributor;
        this.negotiator = negotiator;

        this.username = username;
        this.password = password;

        try {
            this.backend = (HttpURLConnection) new URL("").openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    @Override
    public INote putNote(INote note)
        throws HttpStoreException
    {
        boolean exists = false;
        INote existing = null;

        //compares title of new note to existing ones
        for(Iterator<INote> iterator = this.noteSet.iterator();
            iterator.hasNext(); existing = iterator.next())
        {
            if(existing.getTitle().toString().equals(note.getTitle().toString()))
            {
                exists = true;
                break;
            }
        }

        if(exists)
        {
            // which of the note should be kept
            IConflict conflict = note.clash(existing);
            note = this.negotiator.negotiate(conflict);

            this.noteSet.remove(existing);
            if(!this.updateNote(note)) throw new HttpStoreException();
        }
        else
        {
            if(!this.storeNote(note)) throw new HttpStoreException();
        }

        this.noteSet.add(note);

        return exists ? existing : null;
    }

    public boolean updateNote(INote note)
    {
        return false;
    }

    public boolean storeNote(INote note)
    {
        return false;
    }

    public boolean removeNote(INote note)
    {
        return false;
    }

    /**
     * @return set of notes stored by this StorageProvider
     */
    @Override
    public Set<INote> getNoteSet()
    {
        return new HashSet<>(this.noteSet);
    }

    /**
     * As we invoke the different Sync-Methods on every Update, this Method is unused.
     */
    @Override
    public void sync() {}
}
