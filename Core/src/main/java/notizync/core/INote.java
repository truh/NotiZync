package notizync.core;

/**
 *
 */
public interface INote {
    /**
     * @return title of this note
     */
    public INoteTitel getTitel();

    /**
     *
     * @return
     */
    public INoteContent getContent();

    /**
     *
     * @return
     */
    public IVectorClock getVectorTime();
}
