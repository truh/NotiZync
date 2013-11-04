package notizync.core.basics;

import notizync.core.api.INote;
import notizync.core.conflict.IConflict;

import java.util.NoSuchElementException;

/**
 *
 */
public final class BasicConflict implements IConflict {
    private boolean solved = false;
    private INote solution = null;
    @Override
    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public void solve(INote solution) throws RuntimeException {
        if(isSolved()) {
            throw new RuntimeException("Already solved!");
        } else
        if(solution == null) {
            throw new NullPointerException("INote solution must not be null!");
        } else {
            this.solution = solution;
            this.solved = true;
        }
    }

    @Override
    public INote getLocalNote() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public INote getRemoteNote() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public INote getSolution() throws NoSuchElementException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
