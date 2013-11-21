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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * A basic immutable implementation for the INoteContent interface
 */
public final class BasicNoteContent extends AbstractNoteContent {
    private ArrayList<String> lines;

    /**
     * Does not do much
     */
    private BasicNoteContent() {
        this.lines = new ArrayList<>();
    }

    /**
     * Creates a NoteContent from a String array
     * the String array should contain the lines of text
     *
     * @param lines lines of text
     */
    public BasicNoteContent(String [] lines) {
        this();
        Collections.addAll(this.lines, lines);
    }

    /**
     * Creates a NoteContent from a Collection of Strings
     *
     * @param lines lines of text
     */
    public BasicNoteContent(Collection<String> lines) {
        this();
        this.lines.addAll(lines);
    }

    /**
     * All the lines as a String array
     *
     * @return all the lines
     */
    @Override
    public String[] getLines() {
        Object[] tmp = this.lines.toArray();
        return Arrays.copyOf(tmp, tmp.length, String[].class);
    }
}
