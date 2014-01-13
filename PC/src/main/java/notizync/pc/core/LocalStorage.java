package notizync.pc.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import notizync.core.api.INote;
import notizync.core.basics.BasicNote;
import notizync.core.basics.conflict.BasicNegotiator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * The local Storage.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class LocalStorage
{
    private Storage myStorage;
    private Gson json;

    private BufferedWriter bw;

    public LocalStorage()
    {
        this.json = new GsonBuilder().setPrettyPrinting().create();

        try
        {
            File f = new File(System.getProperty("user.home")+"/notizync_storage.json");

            if(f.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(f));

                String data = "", line = "";
                while((line = br.readLine()) != null)
                {
                    data += line;
                }
                this.myStorage = this.json.fromJson(data, Storage.class);

                br.close();
            }
            else
            {
                f.createNewFile();

                bw = new BufferedWriter(new FileWriter(f));

                Storage s = new Storage();
                s.createEmpty();

                this.myStorage = s;
                this.saveJson();
            }
        }
        catch(IOException e)
        {
            System.out.println("Fatal error");
        }
    }

    public INote[] getNotes()
    {
        return Arrays.copyOf(this.myStorage.notes.toArray(), this.myStorage.notes.size(), BasicNote[].class);
    }

    public boolean saveJson()
    {
        if(bw == null) return false;

        String json = this.json.toJson(this.myStorage);
        System.out.println(json);
        try
        {
            this.bw.write(json);
            this.bw.flush();

            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }

    public class Storage
    {
        public Settings settings;
        public ArrayList<BasicNote> notes;

        public void createEmpty()
        {
            this.settings = new Settings();
            this.notes = new ArrayList<BasicNote>();

            BasicNote n = new BasicNote("Notiz #1", "Notiztext", new java.util.Date().getTime());
            this.notes.add(n);
        }

        public class Settings
        {
            public int sync_interval = -1;
            public String sync_username = "";
            public String sync_password = "";
            public boolean sync_save = false;
        }
    }
}
