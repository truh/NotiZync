package notizync.core.http;

import com.google.gson.Gson;
import notizync.core.basics.BasicNote;
import notizync.core.basics.BasicNoteContent;
import notizync.core.basics.BasicNoteTitle;

/**
 * -- DESCRIPTION --
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 14.11.13 10:16
 */
public class HTTPStorageTest
{
    public static void main(String[] args)
    {
        BasicNoteTitle title = new BasicNoteTitle("Test");
        BasicNoteContent content = new BasicNoteContent(
            new String[]{"Test", "test"  }
        );
        BasicNote note = new BasicNote(title, content);

        Gson gson =  new Gson();
        String json = (gson.toJson(note));

        BasicNote note2 = gson.fromJson(json, BasicNote.class);


        /*HTTPStorageProvider provider = new HTTPStorageProvider(null, new BasicNegotiator(), "abc", "123");
        provider.putNote(note);    */
    }
}
