package notizync.core.api;

import java.util.Set;

/**
 *
 */
public interface IStorageProvider {

    /**
     *
     * @param note
     * @return
     */
    public INote putNote(INote note);

    /**
     *
     * @return
     */
    public Set<? extends INote> getNoteSet();

    /**
     *
     */
    public void sync();

    /**
     *
     * @return
     */
    public int getUniqueID();
}
