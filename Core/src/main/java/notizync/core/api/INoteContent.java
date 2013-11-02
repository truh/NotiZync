package notizync.core.api;

import java.io.Serializable;

/**
 *
 */
public interface INoteContent extends Serializable {
    /**
     *
     * @return
     */
    public int getLineCount();

    /**
     *
     * @param index
     * @return
     */
    public String getLineAt(int index);

    /**
     *
     * @return
     */
    public String[] getLines();

    /**
     * Should return a readable representation of Content
     *
     * @return readable representation
     */
    @Override
    public String toString();
}
