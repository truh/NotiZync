package notizync.core.api;

import java.io.Serializable;

/**
 *
 */
public interface INoteTitle extends Serializable {
    /**
     * Should return a readable representation of Title
     *
     * @return readable representation
     */
    @Override
    public String toString();
}
