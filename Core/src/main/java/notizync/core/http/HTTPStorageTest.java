package notizync.core.http;

import notizync.core.basics.BasicNote;
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
        BasicNote note = new BasicNote("Test", "Inhalt", 13838842);

        HTTPStorageProvider provider = new HTTPStorageProvider(null, new BasicNegotiator(), "abc", "123");
        provider.putNote(note);

        provider.getNotes();
    }
}
