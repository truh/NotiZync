package notizync.core.http;

/**
 * Result codes used in the HTTP API responses.
 *
 * @author Andreas Willinger
 * @version 0.1
 */
public enum EResult
{
    k_RemoteDown,
    k_RemoteDatabaseFailure,
    k_RemoteEmptyData,
    k_RemoteUserExists,
    k_RemoteInvalidLogin,
    k_RemoteSuccess
}
