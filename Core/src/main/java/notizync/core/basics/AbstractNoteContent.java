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

import notizync.core.api.INoteContent;

/**
 * Makes it very simple to implement a note content
 * just override the getLines() methode
 */
public abstract class AbstractNoteContent implements INoteContent {
    /**
     * The note content might be organised in multiple lines.
     *
     * @return number of lines the note has, 0 if not a single line otherwise
     *         at least 1
     */
    @Override
    public int getLineCount() {
        return this.getLines().length;
    }

    /**
     * Will return the line at given index
     *
     * @param index given index
     * @return line at given index
     * @throws IndexOutOfBoundsException if index < 0 || index >= lineCount
     */
    @Override
    public String getLineAt(int index) throws IndexOutOfBoundsException {
        return this.getLines()[index];
    }

    /**
     * All the lines as a String array
     *
     * OVERRIDE this for implementation
     *
     * @return all the lines
     */
    @Override
    public abstract String[] getLines();

    /**
     * Should return a readable representation of Content
     *
     * @return readable representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String [] lines = getLines();

        if(lines.length > 0) {
            sb.append(lines[0]);
        }

        for(int i = 1; i < lines.length; i++) {
            sb.append(lines[i]);
        }

        return sb.toString();
    }
}
