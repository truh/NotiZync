package notizync.core.versionising;

import java.io.Serializable;

/**
 *
 */
public interface IVersionVector extends Serializable {
    /**
     * Compares two vector clocks
     *
     * @param vectorClock instance to compare to
     * @return
     */
    public IVectorComparison compareTo(IVersionVector vectorClock);
}
