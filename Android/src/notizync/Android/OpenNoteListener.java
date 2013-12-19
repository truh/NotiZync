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

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import notizync.core.api.INote;
import notizync.core.api.IStorageProvider;

public class OpenNoteListener implements
        View.OnClickListener {
    private IStorageProvider storageProvider;

    private Activity activity;
    private SharedPreferences preferences;

    public OpenNoteListener(IStorageProvider storageProvider, Activity activity) {
        this.storageProvider = storageProvider;
        this.activity = activity;
        this.preferences = activity.getApplicationContext()
                .getSharedPreferences(NotiZyncActivity.PREFERENCES_NAME, activity.MODE_PRIVATE);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v instanceof TextView) {
            TextView textView = (TextView) v;
            CharSequence title = textView.getText();
            storageProvider.getNoteSet();
            for(INote note : storageProvider.getNoteSet()) {
                String noteTitle = note.getTitle().toString();
                if(title.equals(noteTitle)) {
                    StringBuilder sb = new StringBuilder();
                    for(String line : note.getContent().getLines()) {
                        sb.append(line);
                        sb.append('\n');
                    }
                    NotiZyncActivity.noteContent.setText(sb);
                    this.activity.setContentView(NotiZyncActivity.noteContent);
                    break;
                }
            }
        }
    }
}
