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

import notizync.core.api.INote;

/**
 * A basic immutable implementation for the INote interface
 */
public final class BasicNote implements INote
{
    private String noteTitle;
    private String noteContent;

    /**
     * Assembles a note from its components
     *
     * @param noteTitle title
     * @param noteContent content
     */
    public BasicNote(String noteTitle, String noteContent)
    {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    /**
     * set title of this note
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.noteTitle = title;
    }

    /**
     * set content of this note
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.noteContent = content;
    }

    /**
     * @return title of this note
     */
    @Override
    public String getTitle() {
        return this.noteTitle;
    }

    /**
     * @return content (text) of this note
     */
    @Override
    public String getContent() {
        return this.noteContent;
    }
}
