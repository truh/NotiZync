package notizync.core.http;

import com.google.gson.Gson;
import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;
import notizync.core.api.IUpdateEventDistributor;
import notizync.core.basics.BasicNote;
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
 * @version 0.6
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
    private LinkedList<INote> noteSet;

    public HTTPStorageProvider(IUpdateEventDistributor eventDistributor,
                              INegotiator negotiator)
    {
        this.eventDistributor = eventDistributor;
        this.negotiator = negotiator;

        this.backend = HttpClients.createDefault();
        this.json = new Gson();
    }

    /**
     * Send a login request to the backend, containing username and password.
     *
     * @param username Plain-Text Username
     * @param password Plain-Text Password
     * @return true if the login was successful, false if not
     */
    public EResult doLogin(String username, String password)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("username", username));
        data.add(new BasicNameValuePair("password", password));

        String raw = this.sendPost(WebAPI.getAPI("IUser", "DoLogin"), data);
        if(raw == null) return EResult.k_RemoteDown;
        HTTPLoginResponse parsed = this.json.fromJson(raw, HTTPLoginResponse.class);

        if(parsed.success)
        {
            this.token = parsed.session_data.token;
            this.noteSet = new LinkedList<>();
            this.getNotes();

            return EResult.k_RemoteSuccess;
        }
        return EResult.k_RemoteInvalidLogin;
    }

    /**
     * Send a request to create a new user in the backend, using the supplied username and password.
     *
     * @param username The new user's name
     * @param password Their Password
     * @return true, if user was created successfully, false if not (user already exists/api failure)
     */
    public EResult doRegister(String username, String password)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("username", username));
        data.add(new BasicNameValuePair("password", password));

        String raw = this.sendPost(WebAPI.getAPI("IUser", "DoRegister"), data);
        if(raw == null) return EResult.k_RemoteDown;

        HTTPRegisterResponse parsed = this.json.fromJson(raw, HTTPRegisterResponse.class);

        if(parsed.success)
        {
            return EResult.k_RemoteSuccess;
        }
        else
        {
            return EResult.values()[parsed.error.code];
        }
    }

    /**
     * Retrieve all remotely stored notes for that user and cache it locally.
     * Use this with caution, everything under 10 seconds might become dangerous (might return a lot of data).
     *
     * @return true, if notes were retrieved successfully, false if not
     */
    public boolean getNotes()
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("token", this.token));

        String raw = this.sendPost(WebAPI.getAPI("INote", "GetNotes"), data);
        if(raw == null) return false;

        HTTPGetNotesResponse parsed = this.json.fromJson(raw, HTTPGetNotesResponse.class);

        if(!parsed.success) return false;

        HTTPGetNotesResponse.Note[] notes = parsed.data;

        this.noteSet.clear();
        for(int i = 0; i < notes.length; i++)
        {
            INote note = new BasicNote(notes[i].title, notes[i].content, notes[i].timestamp);
            this.noteSet.add(note);
        }
        return true;
    }

    /**
     * Adds or updates a note for storage.
     *
     * @param note note that should be stored
     * @return note that was stored before with the same title or null
     */
    @Override
    public EResult putNote(INote note)
    {
        // check if the note already exists
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("title", note.getTitle()));
        data.add(new BasicNameValuePair("timestamp", ""+note.getTimestamp()));
        data.add(new BasicNameValuePair("token", this.token));

        String raw = this.sendPost(WebAPI.getAPI("INote", "GetNoteState"), data);
        if(raw == null) return EResult.k_RemoteDown;

        HTTPNoteStateResponse parsed = this.json.fromJson(raw, HTTPNoteStateResponse.class);
        if(!parsed.success) return EResult.values()[parsed.error.code];

        if(parsed.state.exists)
        {
            if(parsed.state.local_is_newer)
            {
                return this.storeNote(note, true);
            }
            else
            {
                this.getNotes();

                return EResult.k_RemoteNoteIsNewer;
            }
        }
        else
        {
           return this.storeNote(note, false);
        }
    }

    /**
     * Store a new note or update an existing note on the API.
     *
     * @param note the note to update/store
     * @param update true, if the operation is an update one, false if not (store a new note)
     * @return an EResult Object
     */
    private EResult storeNote(INote note, boolean update)
    {
        String updateS =(update)?"1":"0";

        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("title", note.getTitle()));
        data.add(new BasicNameValuePair("content", note.getContent()));
        data.add(new BasicNameValuePair("timestamp", ""+note.getTimestamp()));
        data.add(new BasicNameValuePair("update", updateS));
        data.add(new BasicNameValuePair("token", this.token));

        String raw = this.sendPost(WebAPI.getAPI("INote", "StoreNote"), data);
        if(raw == null) return EResult.k_RemoteDown;

        HTTPStoreNoteResponse parsed = this.json.fromJson(raw, HTTPStoreNoteResponse.class);

        if(!parsed.success) return EResult.values()[parsed.error.code];

        if(update)
        {
            for(INote n:this.noteSet)
            {
                if(n.getTitle().equals(note.getTitle()))
                {
                    n.setContent(note.getContent());
                    break;
                }
            }

            return EResult.k_RemoteUpdateSuccess;
        }
        else
        {
            this.noteSet.add(note);
            return EResult.k_RemoteSuccess;
        }
    }

    /**
     * Remove a note from the remote note set.
     *
     * @param note note that should be removed
     * @return an EResult object
     */
    public EResult removeNote(INote note)
    {
        List<NameValuePair> data = new ArrayList<>();
        data.add(new BasicNameValuePair("title", note.getTitle()));
        data.add(new BasicNameValuePair("token", this.token));

        String raw = this.sendPost(WebAPI.getAPI("INote", "RemoveNote"), data);
        if(raw == null) return EResult.k_RemoteDown;

        HTTPStoreNoteResponse parsed = this.json.fromJson(raw, HTTPStoreNoteResponse.class);
        if(!parsed.success) return EResult.values()[parsed.error.code];

        for(INote n:this.noteSet)
        {
            if(n.getTitle().equals(note.getTitle()))
            {
                this.noteSet.remove(n);
                break;
            }
        }
        return EResult.k_RemoteSuccess;
    }

    /**
     * Send a post request to the specified Web API with the given fields.
     *
     * @param url the Web API to use, @see WebAPI.getApi for usage
     * @param data the data to send, must be a list containing NameValuePairs
     * @return the string returned from the API, null if there was an error
     */
    private String sendPost(String url, List<NameValuePair> data)
    {
        HttpPost request = new HttpPost(url);

        try
        {
            request.setEntity(new UrlEncodedFormEntity(data));
            HttpResponse response = this.backend.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "", raw = "";

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

    @Override
    public HashSet<INote> getNoteSet() {return null;}
    /**
     * @return set of notes stored by this StorageProvider
     */
    public List<INote> getNoteList()
    {
        return this.noteSet;
    }
    /**
     * As we invoke the different Sync-Methods on every Update, this Method is unused.
     */
    @Override
    public void sync() {}
}
