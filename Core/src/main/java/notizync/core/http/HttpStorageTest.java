package notizync.core.http;

import notizync.core.basics.conflict.BasicNegotiator;

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
        HTTPStorageProvider provider = new HTTPStorageProvider(null, new BasicNegotiator(), "abc", "123");
    }
}
