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
