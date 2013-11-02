package notizync.core.basics;

import notizync.core.api.INote;
import notizync.core.api.INoteContent;
import notizync.core.api.INoteTitle;
import notizync.core.versionising.IVersionVector;

/**
 * A basic immutable implementation for the INote interface
 */
public final class BasicNote implements INote {
    private BasicNoteTitle noteTitle;
    private BasicNoteContent noteContent;
    private IVersionVector version;

    /**
     * @param noteTitle
     * @param noteContent
     * @param version
     */
    public BasicNote(BasicNoteTitle noteTitle, BasicNoteContent noteContent, IVersionVector version) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.version = version;
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
    public IVersionVector getVersion() {
        return this.version;
    }
}
