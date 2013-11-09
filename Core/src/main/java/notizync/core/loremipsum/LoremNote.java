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
package notizync.core.loremipsum;

import itc4j.Stamp;
import notizync.core.api.INote;
import notizync.core.api.INoteContent;
import notizync.core.api.INoteTitle;
import notizync.core.conflict.IConflict;

/**
 *
 */
public class LoremNote implements INote {
    /**
     * @return title of this note
     */
    @Override
    public INoteTitle getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * @return content (text) of this note
     */
    @Override
    public INoteContent getContent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * A Interval Tree Clock stamp, it is used for versioning so that always
     * the newest version of a note will be showed.
     *
     * @return stamp
     */
    @Override
    public Stamp getStamp() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * When causality is not clear, a conflict object should be created and passed to the BasicUpdateEventDistributor
     *
     * @param note conflicting note
     * @return conflict
     */
    @Override
    public IConflict clash(INote note) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
