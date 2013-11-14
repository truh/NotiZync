package notizync.core.http;

/**
 * -- DESCRIPTION --
 *
 * @author Andreas Willinger
 * @version 0.1
 * @since 14.11.13 10:29
 */
public class WebAPI
{
    private static final String baseURL = "http://api.f-o-g.eu/NotiZync/%s/%s/v0001/?format=json";

    public static String getAPI(String iface, String method)
    {
        return String.format(baseURL, iface, method);
    }
}
