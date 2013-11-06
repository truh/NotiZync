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
package notizync.core.basics;

import notizync.core.api.INote;
import notizync.core.conflict.IConflict;
import notizync.core.conflict.INegotiationRoutine;
import notizync.core.conflict.INegotiator;

import java.util.ArrayList;

/**
 *
 */
public final class BasicNegotiator implements INegotiator {
    private ArrayList<INegotiationRoutine> negotiationRoutines;

    /**
     *
     */
    public BasicNegotiator() {
        this.negotiationRoutines = new ArrayList<>();
    }

    /**
     * Adds a negotiation routine to the end of the complete negotiation routine
     *
     * @param negotiationRoutine the routine to be added
     * @return true if it could be added
     */
    @Override
    public boolean addNegotiationRoutine(INegotiationRoutine negotiationRoutine) {
        return this.negotiationRoutines.add(negotiationRoutine);
    }

    /**
     * Tries to solve the given conflict
     *
     * @param conflict the conflict that should be solved
     * @return solution or null if no solution could be found
     */
    @Override
    public INote negotiate(IConflict conflict) {
        INote solution = null;

        for(INegotiationRoutine negotiationRoutine : this.negotiationRoutines) {
            if(solution != null) {
                break;
            }
            solution = negotiationRoutine.negotiate(conflict);
        }

        return solution;
    }
}
