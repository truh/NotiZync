package notizync.core.api;

import notizync.core.conflict.IConflict;
import notizync.core.versionising.IVersionVector;

import java.io.Serializable;

/**
 *
 */
public interface INote extends Serializable {
    /**
     * @return title of this note
     */
    public INoteTitle getTitle();

    /**
     *
     * @return
     */
    public INoteContent getContent();

    /**
     *
     * @return
     */
    public IVersionVector getVersion();

    /**
     * When causality is not clear, a conflict object should be created and passed to the NotiRegistry
     * @param note conflicting note
     * @return conflict
     */
    public IConflict clash(INote note);
}
