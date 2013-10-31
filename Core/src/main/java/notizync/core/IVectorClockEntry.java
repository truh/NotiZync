package notizync.core;

/**
 * A IVectorClockEntry represents a version number in combination
 */
public interface IVectorClockEntry extends Comparable <IVectorClockEntry> {
    /**
     *
     * @return
     */
    public int getUniqueID();

    /**
     * The version number, should increment on every change.
     *
     * @return version number
     */
    public int getVersionNumber();

    /**
     * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT
     * represented by this <tt>Date</tt> object.
     *
     * @return  the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *          represented by this date.
     */
    public long getTime();

    /**
     * Compares this VectorClockEntry with the specified for order. Returns a
     * negative integer, zero, or a positive integer as this VectorClockEntry
     * is less recent equal recent or more recent than the specified.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * @param clockEntry The VectorClockEntry to be compared
     *                   The specified VectorClockEntry should give the same
     *                   result for <tt>getUniqueID()</tt>
     * @return a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws RuntimeException if <tt>getUniqueID()</tt> is not the same for
     *          the specified clockEntry
     */
    @Override
    public int compareTo(IVectorClockEntry clockEntry);
}