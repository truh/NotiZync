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
import android.view.MenuItem;

/**
 * Listening for Button clicks
 */
public class OpenConfigMenuListener implements MenuItem.OnMenuItemClickListener {
    private Activity activity;
    private SharedPreferences preferences;

    public OpenConfigMenuListener(Activity activity) {
        this.activity = activity;
        this.preferences = activity.getApplicationContext()
                .getSharedPreferences(NotiZyncActivity.PREFERENCES_NAME, activity.MODE_PRIVATE);

    }

    /**
     * Called when a menu item has been invoked.  This is the first code
     * that is executed; if it returns true, no other callbacks will be
     * executed.
     *
     * @param item The menu item that was invoked.
     * @return Return true to consume this click and prevent others from
     *         executing.
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        this.activity.setContentView(R.layout.config);
        return true;
    }
}
