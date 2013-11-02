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
