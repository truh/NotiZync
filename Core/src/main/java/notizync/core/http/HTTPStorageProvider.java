package notizync.core.http;

import com.google.gson.Gson;
import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;
import notizync.core.api.IUpdateEventDistributor;
import notizync.core.conflict.IConflict;
import notizync.core.conflict.INegotiator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

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

    private CloseableHttpClient backend;
    private Gson json;

    private String token;
    private HashSet<INote> noteSet;

    public HTTPStorageProvider(IUpdateEventDistributor eventDistributor,
                              INegotiator negotiator,
                              String username,
                              String password)
            throws HTTPStoreException
    {
        this.eventDistributor = eventDistributor;
        this.negotiator = negotiator;

        this.backend = HttpClients.createDefault();
        this.json = new Gson();

        if(!this.doLogin(username, password)) throw new HTTPStoreException("Logon failed");
    }

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    @Override
    public INote putNote(INote note)
            throws HTTPStoreException
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
            if(!this.removeNote(note)) throw new HTTPStoreException("Transport error while removing the Note!\nEither the API is down or refused to communicate with us.");
        }
        else
        {
            if(!this.storeNote(note)) throw new HTTPStoreException("Transport error while storing the Note!\nEither the API is down or refused to communicate with us.");
        }

        this.noteSet.add(note);

        return exists ? existing : null;
    }

    /**
     * Send a login request to the backend, containing username and password.
     *
     * @param username Plain-Text Username
     * @param password Plain-Text Password
     * @return true if the login was successful, false if not
     */
    private boolean doLogin(String username, String password)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("username", username));
        data.add(new BasicNameValuePair("password", password));

        String raw = this.sendPost(WebAPI.getAPI("IUser", "OAuth"), data);
        if(raw == null) return false;

        HTTPLoginResponse parsed = this.json.fromJson(raw, HTTPLoginResponse.class);

        if(parsed.success)
        {
            this.token = parsed.session_data.token;
            return true;
        }
        return false;
    }

    public boolean storeNote(INote note)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("title", note.getTitle().toString()));
        data.add(new BasicNameValuePair("content", note.getContent().toString()));
        data.add(new BasicNameValuePair("stamp", note.getStamp().toString()));

        String raw = this.sendPost(WebAPI.getAPI("INote", "StoreNote"), data);
        if(raw == null) return false;

        HTTPNoteResponse parsed = this.json.fromJson(raw, HTTPNoteResponse.class);

        if(parsed.success) return true;
        return false;
    }

    public boolean removeNote(INote note)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("title", note.getTitle().toString()));

        String raw = this.sendPost(WebAPI.getAPI("INote", "RemoveNote"), data);
        if(raw == null) return false;

        HTTPNoteResponse parsed = this.json.fromJson(raw, HTTPNoteResponse.class);

        if(parsed.success) return true;
        return false;
    }

    private String sendPost(String url, List<NameValuePair> data)
    {
        HttpPost request = new HttpPost(url);

        try
        {
            request.setEntity(new UrlEncodedFormEntity(data));
            HttpResponse response = this.backend.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            String raw = "";

            while((line = reader.readLine()) != null)
            {
                raw += line;
            }

            return raw;
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        catch (ClientProtocolException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
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
