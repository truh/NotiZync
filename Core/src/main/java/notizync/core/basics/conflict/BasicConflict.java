/*
    Copyright 2013 Andreas Willinger, Andreas Vogt, Matthias El-Far, Jakob Klepp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package notizync.core.basics.conflict;

import notizync.core.api.INote;
import notizync.core.conflict.IConflict;

import java.util.NoSuchElementException;

/**
 * A conflict occurs when there are two different notes with unclear causality
 */
public final class BasicConflict implements IConflict {
    private boolean solved = false;
    private INote solution = null;

    private INote local;
    private INote remote;

    /**
     * Creates a basic conflict from two notes
     *
     * @param local local note
     * @param remote remote note
     */
    public BasicConflict(INote local, INote remote) {
        this.local = local;
        this.remote = remote;
    }

    /**
     * Problem solved?
     *
     * @return returns true if the problem is solved
     */
    @Override
    public boolean isSolved() {
        return this.solved;
    }

    /**
     * Solves the problem with the given solution
     *
     * @param solution the note that survived the conflict
     *
     * @throws RuntimeException if solution already is defined
     * @throws NullPointerException if solution=null
     */
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

    /**
     * Local note
     *
     * @return local note
     */
    @Override
    public INote getLocalNote() {
        return this.local;
    }

    /**
     * Remote note
     *
     * @return remote note
     */
    @Override
    public INote getRemoteNote() {
        return this.remote;
    }

    /**
     * @return solution, defined by solve(INote)
     *
     * @throws NoSuchElementException if there is no solution yet
     */
    @Override
    public INote getSolution() throws NoSuchElementException {
        if(!isSolved()) {
            throw new NoSuchElementException("solution not defined (yet)!");
        }
        return this.solution;
    }
}
