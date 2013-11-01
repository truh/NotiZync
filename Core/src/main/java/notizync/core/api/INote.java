package notizync.core.api;

import itc4j.Stamp;

/**
 *
 */
public interface INote {
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
    public Stamp getStamp();
}
