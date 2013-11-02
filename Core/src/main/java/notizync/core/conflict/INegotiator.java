package notizync.core.conflict;

/**
 *
 */
public interface INegotiator extends INegotiationRoutine {
    /**
     * Adds a negotiation routine to the end of the complete negotiation routine
     *
     * @param negotiationRoutine the reoutine to be added
     * @return true if it could be added
     */
    public boolean addNegotiationRoutine(INegotiationRoutine negotiationRoutine);
}
