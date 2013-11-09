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
package notizync.core.loremipsum;

import notizync.core.api.INoteContent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class LoremNoteContent implements INoteContent {
    private ArrayList<String> lines;

    /**
     * Default constructer
     * creates a content with 50 lorem ipsum words
     */
    public LoremNoteContent() {
        this(20, 10);
    }

    /**
     * creates a content with a variable number of words
     *
     * @param wordsPLine number of words in each line
     * @param lineCount number of lines
     */
    public LoremNoteContent(int wordsPLine, int lineCount) {
        //   wordsPLine, lineCount, skipWords
        this(wordsPLine, lineCount, (int)(Math.random() * 10000));
    }

    /**
     * creates a title with a variable number of words
     * and variable start position
     *
     * @param wordsPLine number of words in each line
     * @param lineCount number of lines
     * @param skipWords word to start from
     */
    public LoremNoteContent(int wordsPLine, int lineCount, int skipWords) {
        lines = new ArrayList<>(lineCount);

        LoremIpsum loremIpsum = new LoremIpsum();

        for(int i = 0; i < lineCount; i++) {
            String line = loremIpsum.getWords(skipWords, wordsPLine);
            this.lines.add(line);
        }
    }
    /**
     * The note content might be organised in multiple lines.
     *
     * @return number of lines the note has, 0 if not a single line otherwise
     *         at least 1
     */
    @Override
    public int getLineCount() {
        return this.lines.size();
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
        return this.lines.get(index);
    }

    /**
     * All the lines as a String array
     *
     * @return all the lines
     */
    @Override
    public String[] getLines() {
        return (String[])this.lines.toArray();
    }

    /**
     * Should return a readable representation of Content
     *
     * @return readable representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator<String> iterator = this.lines.iterator();
        if(iterator.hasNext()) {
            sb.append(iterator.next());
        }

        //noinspection StatementWithEmptyBody
        for(; iterator.hasNext(); sb.append(iterator.next()).append('\n'));

        return sb.toString();
    }
}
