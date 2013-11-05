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
package notizync.core.conflict;

import notizync.core.api.INote;

import java.util.NoSuchElementException;

/**
 * A conflict occurs when there are two different notes with unclear causality
 */
public interface IConflict {
    /**
     * Problem solved?
     *
     * @return returns true if the problem is solved
     */
    public boolean isSolved();

    /**
     * Solves the problem with the given solution
     *
     * @param solution the note that survived the conflict
     *
     * @throws RuntimeException if solution already is defined
     * @throws NullPointerException if solution=null
     */
    public void solve(INote solution) throws RuntimeException, NullPointerException;

    /**
     * Local note
     *
     * @return local note
     */
    public INote getLocalNote();

    /**
     * Remote note
     *
     * @return remote note
     */
    public INote getRemoteNote();

    /**
     * @return solution, defined by solve(INote)
     *
     * @throws NoSuchElementException if there is no solution yet
     */
    public INote getSolution() throws NoSuchElementException;
}
