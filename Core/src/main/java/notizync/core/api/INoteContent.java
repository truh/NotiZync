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
package notizync.core.api;

import java.io.Serializable;

/**
 *
 */
public interface INoteContent extends Serializable {
    /**
     * The note content might be organised in multiple lines.
     *
     * @return number of lines the note has, 0 if not a single line otherwise
     *         at least 1
     */
    public int getLineCount();

    /**
     * Will return the line at given index
     *
     * @param index given index
     * @return line at given index
     *
     * @throws IndexOutOfBoundsException if index < 0 || index >= lineCount
     */
    public String getLineAt(int index) throws IndexOutOfBoundsException;

    /**
     * All the lines as a String array
     *
     * @return all the lines
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
