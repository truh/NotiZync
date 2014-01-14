package notizync.core.http;

/**
 * Object representation of the Json-based login response.
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 14.11.13 11:14
 */
public class HTTPLoginResponse
{
    public boolean success;
    public SessionData session_data;

    public static class SessionData
    {
        public String token;
    }
}
