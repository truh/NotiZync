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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 *
 */
public class NotiZyncActivity extends Activity {
    private ButtonListener buttonListener;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonListener = new ButtonListener(this);

        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean superResult = super.onCreateOptionsMenu(menu);
        //adding menu
        getMenuInflater().inflate(R.menu.main, menu);

        // MenuButton
        MenuItem configButton = menu.getItem(R.id.configButton);
        //ActionListener
        configButton.setOnMenuItemClickListener(this.buttonListener);

        return superResult;
    }
}
