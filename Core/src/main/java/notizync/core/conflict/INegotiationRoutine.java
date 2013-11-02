package notizync.core.conflict;

import notizync.core.api.INote;

/**
 *
 */
public interface INegotiationRoutine {
    /**
     * Tries to solve the given conflict
     * @param conflict the conflict that should be solved
     * @return solution or null if no solution could be found
     */
    public INote negotiate(IConflict conflict);
}
