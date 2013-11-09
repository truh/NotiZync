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
package notizync.Android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;

import java.util.HashSet;

/**
 *
 */
public final class NoteContainer extends View {
    private IStorageProvider storageProvider;
    private HashSet<NoteButtonView> noteButtons;
    private Context context;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public NoteContainer(Context context, IStorageProvider storageProvider) {
        super(context);
        this.context = context;
        this.storageProvider = storageProvider;
    }

    private void updateNoteButtons() {
        //remove noteButtons if the wrapped note got removed
        for(NoteButtonView noteButton: this.noteButtons) {
            if(!this.storageProvider.getNoteSet().contains(noteButton.getNote())) {
                this.noteButtons.remove(noteButton);
            }
        }
        //add note button if one got added
        for(INote note: this.storageProvider.getNoteSet()) {
            boolean buttonExists = false;
            for(NoteButtonView noteButton: this.noteButtons) {
                if(noteButton.getNote() == note) {
                    buttonExists = true;
                    break;
                }
            }
            if(!buttonExists) {
                noteButtons.add(new NoteButtonView(this.context, note));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.updateNoteButtons();
        super.onDraw(canvas);
    }
}
