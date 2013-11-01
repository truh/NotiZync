package notizync.core.api;

/**
 *
 */
public interface INoteContent {
    /**
     *
     * @return
     */
    public int getLineCount();

    /**
     *
     * @param index
     * @return
     */
    public String getLineAt(int index);

    /**
     *
     * @return
     */
    public String[] getLines();

    /**
     *
     * @return
     */
    @Override
    public String toString();
}
