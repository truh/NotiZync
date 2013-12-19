package notizync.core.http;

import notizync.core.basics.BasicNote;
import notizync.core.basics.BasicNoteContent;
import notizync.core.basics.BasicNoteTitle;
import notizync.core.basics.conflict.BasicNegotiator;

/**
 * -- DESCRIPTION --
 *
 * @author Andreas Willinger
 * @version 0.2
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

        HTTPStorageProvider provider = new HTTPStorageProvider(null, new BasicNegotiator(), "abc", "123");
        provider.putNote(note);

        provider.getNotes();
    }
}
