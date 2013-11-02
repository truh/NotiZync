package notizync.core.basics;

import notizync.core.api.INoteTitle;

/**
 * A basic immutable implementation for the INoteTitle interface
 */
public final class BasicNoteTitle implements INoteTitle {
    private String title;

    /**
     * @param title the Title
     */
    public BasicNoteTitle(String title) {
        this.title = title;
    }

    /**
     * Should return a readable representation of Title
     *
     * @return readable representation
     */
    @Override
    public String toString() {
        return this.title;
    }
}
