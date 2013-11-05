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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * A basic immutable implementation for the INoteContent interface
 */
public final class BasicNoteContent implements INoteContent {
    private ArrayList<String> lines;
    /**
     *
     * @param lines
     */
    public BasicNoteContent(String [] lines) {
        this.lines = new ArrayList<>();
        Collections.addAll(this.lines, lines);
    }

    /**
     * @return
     */
    @Override
    public int getLineCount() {
        return this.lines.size();
    }

    /**
     * @param index
     * @return
     */
    @Override
    public String getLineAt(int index) {
        return this.lines.get(index);
    }

    /**
     * @return
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

        Iterator <String>iterator = this.lines.iterator();
        if(iterator.hasNext()) {
            sb.append(iterator.next());
        }

        //noinspection StatementWithEmptyBody
        for(; iterator.hasNext(); sb.append(iterator.next()));

        return sb.toString();
    }
}
