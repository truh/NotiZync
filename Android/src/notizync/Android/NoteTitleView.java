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
import android.util.Log;
import android.widget.Button;
import notizync.core.api.INote;

/**
 *
 */
public class NoteTitleView extends Button {
    public static final String TAG = "NoteTitleView";
    private int chop = 100;
    private INote note;
    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public NoteTitleView(Context context, INote note) {
        super(context);
    }

    /**
     * Wrapped note
     * @return the note
     */
    public INote getNote() {
        return note;
    }

    /**
     *
     */
    public void setNote(INote note) {
        this.note = note;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = "";
        if(note != null) {
            text = this.note.getTitle().toString();

            if(text.length() > chop) {
                text = text.subSequence(0, chop - 4).toString() + " ...";
            }

            Log.i(TAG, text);
        }
        this.setText(text);
        super.onDraw(canvas);
    }
}
