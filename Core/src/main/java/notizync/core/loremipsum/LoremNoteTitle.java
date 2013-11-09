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

import notizync.core.api.INoteTitle;

/**
 * Lorem Ipsum based title
 */
public class LoremNoteTitle implements INoteTitle {
    private String title;

    /**
     * Default constructer
     * creates a title with 4 lorem ipsum words
     */
    public LoremNoteTitle() {
        //   wordCount
        this(4);
    }

    /**
     * creates a title with a variable number of words
     *
     * @param wordCount number of words
     */
    public LoremNoteTitle(int wordCount) {
        //   wordCount, skipWords
        this(wordCount, (int)(Math.random() * 10000));
    }

    /**
     * creates a title with a variable number of words
     * and variable start position
     *
     * @param wordCount number of words
     * @param skipWords word to start from
     */
    public LoremNoteTitle(int wordCount, int skipWords) {
        LoremIpsum loremIpsum = new LoremIpsum();

        this.title = loremIpsum.getWords(skipWords, wordCount);
    }

    /**
     * Should return a readable representation of Title
     *
     * @return readable representation
     */
    @Override
    public String toString() {
        return title;
    }
}
