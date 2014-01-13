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

import java.util.ArrayList;

/**
 *
 */
public class LoremNoteContent
{
    private ArrayList<String> lines;

    /**
     * Default constructer
     * creates a content with 50 lorem ipsum words
     */
    public LoremNoteContent() {
        this(7, 30);
    }

    /**
     * creates a content with a variable number of words
     *
     * @param wordsPLine number of words in each line
     * @param lineCount number of lines
     */
    public LoremNoteContent(int wordsPLine, int lineCount) {
        //   wordsPLine, lineCount, skipWords
        this(wordsPLine, lineCount, (int)(Math.random() * 10000)%50);
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
     * All the lines as a String array
     *
     * @return all the lines
     */
    public String[] getLines() {
        String[] stringArray = new String[this.lines.size()];
        for(int i = 0; i<stringArray.length && i<this.lines.size(); i++) {
            stringArray[i] = this.lines.get(i);
        }
        return stringArray;
    }
}
