/*
    Copyright 2013 Andreas Willinger, Andreas Vogt, Matthias El-Far, Jakob Klepp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package notizync.core.basics;

import itc4j.Stamp;
import notizync.core.api.INote;
import notizync.core.api.INoteContent;
import notizync.core.api.INoteTitle;
import notizync.core.basics.conflict.BasicConflict;
import notizync.core.conflict.IConflict;

/**
 * A basic immutable implementation for the INote interface
 */
public final class BasicNote extends AbstractNote {
    private BasicNoteTitle noteTitle;
    private BasicNoteContent noteContent;
    private Stamp stamp;

    /**
     * Assembles a note from its components
     *
     * @param noteTitle title
     * @param noteContent content
     * @param stamp stamp
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
     * @return content (text) of this note
     */
    @Override
    public INoteContent getContent() {
        return this.noteContent;
    }
}
