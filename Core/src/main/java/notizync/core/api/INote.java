package notizync.core.api;

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
}
