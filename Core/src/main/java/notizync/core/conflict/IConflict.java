package notizync.core.conflict;

import notizync.core.api.INote;

import java.util.NoSuchElementException;

/**
 * A conflict occurs when there are two different notes with unclear causality
 */
public interface IConflict {
    /**
     * @return returns true if the problem is solved
     */
    public boolean isSolved();

    /**
     * @param solution the note that survived the conflict
     * @return returns true if the conflict was already solved before
     * throws RuntimeException if solution already is defined
     */
    public void solve(INote solution) throws RuntimeException;

    /**
     *
     * @return
     */
    public INote getLocalNote();

    /**
     *
     * @return
     */
    public INote getRemoteNote();

    /**
     *
     * @return
     * throws NoSuchElementException if there is no solution yet
     */
    public INote getSolution() throws NoSuchElementException;
}
