package notizync.core.http;

/**
 * -- DESCRIPTION --
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 21.11.13 08:55
 */
public class HTTPGetNotesResponse
{
    public boolean success;
    public int count;

    public Note[] data;

    public class Note
    {
        public String title;
        public String content;
    }
}
