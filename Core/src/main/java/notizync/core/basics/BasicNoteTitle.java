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

import notizync.core.api.INoteTitle;

/**
 * A basic immutable implementation for the INoteTitle interface
 */
public final class BasicNoteTitle implements INoteTitle {
    private String title;

    /**
     * @param title the Title
     */
    public BasicNoteTitle(String title) {
        this.title = title;
    }

    /**
     * Should return a readable representation of Title
     *
     * @return readable representation
     */
    @Override
    public String toString() {
        return this.title;
    }
}
