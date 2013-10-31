package notizync.core;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * A interface describing a vector clock
 *
 * A vector clock is a system for ordering events in a distributed system
 */
public interface IVectorClock extends Comparable <IVectorClock> {

    /**
     *
     * @param vectorClock
     * @return
     */
    @Override
    public int compareTo(IVectorClock vectorClock) throws RuntimeException;

    public interface IVectorClockEntry extends Comparable <IVectorClockEntry> {
        /**
         *
         * @return
         */
        public int getUniqueID();

        /**
         *
         * @return
         */
        public int getVersionNumber();

        /**
         *
         * @return
         */
        public long getTime();

        /**
         *
         * @param clockEntry
         * @return
         */
        @Override
        public int compareTo(IVectorClockEntry clockEntry);
    }
}
