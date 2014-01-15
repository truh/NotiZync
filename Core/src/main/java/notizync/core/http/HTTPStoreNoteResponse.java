package notizync.core.http;

/**
 * Object representation of the Note-API responses.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 14.11.13 11:33
 */
public class HTTPStoreNoteResponse
{
    public boolean success;
    public long timestamp;

    public Error error;

    public class Error
    {
        public int code;
        public String message;
    }
}
