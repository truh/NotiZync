package notizync.core.basics;

import itc4j.Stamp;
import notizync.core.api.INote;
import notizync.core.api.INoteContent;
import notizync.core.api.INoteTitle;
import notizync.core.conflict.IConflict;
import notizync.core.versionising.IVersionVector;

import java.util.NoSuchElementException;

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

    /**
     * When causality is not clear, a conflict object should be created and passed to the NotiRegistry
     *
     * @param note conflicting note
     * @return conflict
     */
    @Override
    public IConflict clash(INote note) {



        return null;
    }
}
