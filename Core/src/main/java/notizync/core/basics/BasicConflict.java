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
