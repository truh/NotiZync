package notizync.core.basics;

import itc4j.Stamp;
import notizync.core.api.INote;
import notizync.core.api.INoteContent;
import notizync.core.api.INoteTitle;

/**
 * A basic immutable implementation for the INote interface
 */
public final class BasicNote implements INote {
    private BasicNoteTitle noteTitle;
    private BasicNoteContent noteContent;
    private Stamp stamp;

    /**
     * @param noteTitle
     * @param noteContent
     * @param stamp
     */
    public BasicNote(BasicNoteTitle noteTitle, BasicNoteContent noteContent, Stamp stamp) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.stamp = stamp;
    }

    /**
     * @return title of this note
     */
    @Override
    public INoteTitle getTitle() {
        return this.noteTitle;
    }

    /**
     * @return
     */
    @Override
    public INoteContent getContent() {
        return this.noteContent;
    }

    /**
     * @return
     */
    @Override
    public Stamp getStamp() {
        return this.stamp;
    }
}
