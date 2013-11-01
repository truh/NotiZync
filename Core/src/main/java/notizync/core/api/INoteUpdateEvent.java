package notizync.core.api;

/**
 *
 */
public interface INoteUpdateEvent {
    /**
     * The Note associated with this event
     * @return
     */
    public INote getNote();
}
