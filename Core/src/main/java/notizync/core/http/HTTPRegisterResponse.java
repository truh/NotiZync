package notizync.core.http;

/**
 * Object representation of the Json-based registration response.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class HTTPRegisterResponse
{
    public boolean success;

    public Error error;

    public class Error
    {
        public int code;
        public String message;
    }
}
