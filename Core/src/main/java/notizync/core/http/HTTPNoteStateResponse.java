package notizync.core.http;

/**
 * This is an object representation of the response received from the backend.
 *
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public class HTTPNoteStateResponse
{
    public boolean success;
    public State state;
    public Error error;

    public class State
    {
        public boolean exists;
        public boolean local_is_newer;
        public boolean local_is_older;
    }
    public class Error
    {
        public int code;
        public String message;
    }
}
