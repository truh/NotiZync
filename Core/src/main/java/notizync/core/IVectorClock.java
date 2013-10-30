package notizync.core;

import java.util.Map;

/**
 *
 */
public interface IVectorClock extends Comparable <IVectorClock> {

    /**
     *
     * @param vectorClock
     * @return
     */
    @Override
    public int compareTo(IVectorClock vectorClock) throws RuntimeException;

    public interface IClockEntry extends Comparable <IClockEntry> {
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
         * @param clockEntry
         * @return
         */
        @Override
        public int compareTo(IClockEntry clockEntry);
    }
}
