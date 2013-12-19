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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import notizync.core.api.INote;
import notizync.core.api.INoteTitle;
import notizync.core.api.IStorageProvider;
import notizync.core.loremipsum.LoremStorageProvider;

/**
 * Main Activity of NotiZync for Android
 */
public class NotiZyncActivity extends Activity {
    public static final String TAG = "NotiZyncActivity";
    public static final String PREFERENCES_NAME = "NotiZync.pref";

    public static View noteContainer;
    public static TextView noteContent;

    // this is the controller that populates the list with data.
    private NoteListAdapter listAdapter;

    private OpenConfigMenuListener openConfigMenuListener;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Activity created");

        openConfigMenuListener = new OpenConfigMenuListener(this);

        setContentView(R.layout.main);

        //
        noteContainer = this.findViewById(R.layout.note);
        noteContent = (TextView) this.findViewById(R.id.textView_NoteBody);

        // gather the data to be used by the array
        collectListData();
        // set up the list adapter to be used by the ListView
        setupListAdapter();
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     *         if you return false it will not be shown.
     *
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean superResult = super.onCreateOptionsMenu(menu);
        //adding menu
        getMenuInflater().inflate(R.menu.main, menu);

        // MenuButton
        MenuItem configButton = menu.findItem(R.id.configButton);
        //ActionListener
        configButton.setOnMenuItemClickListener(this.openConfigMenuListener);

        return superResult;
    }

    private void registerButtonListener() {
        //get buttons
        Button configSave = (Button) findViewById(R.id.buttonConfigSave);
        Button configCancel = (Button) findViewById(R.id.buttonConfigCancel);

        //register listener
        configSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "buttonConfigSave clicked");
                //save state
                //return to main view
                NotiZyncActivity.this.setContentView(R.layout.main);
            }
        });
        configCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "buttonConfigCancel clicked");
                //return to main view
                NotiZyncActivity.this.setContentView(R.layout.main);
            }
        });
    }

    /**
     * This is where we create and connect the adapter to this activity as well
     * as the data.
     */
    private void setupListAdapter() {
        ListView listView = (ListView) findViewById(R.id.listViewNotes);
        if(listView == null) Log.i(TAG, "setupListAdapter listView == null");

        // "this" is the containing activity.
        // "android.R.layout.simple_list_item_1" is a predefined item that is
        // already set up to work with a list adapter.
        // "android.R.id.text1" is a predefined item that is
        // already set up to work with a list adapter and show one text element.
        // "spirits" is the data array we are using in this example.
        /*listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spirits); */
        // listen to an ItemClick event
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                INote note = (INote) listAdapter.getItem(position);
                if(note == null) Log.i(TAG, "onItemClickListener note == null");
                INoteTitle title = note.getTitle();
                if(note == null) Log.i(TAG, "onItemClickListener title == null");
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position + ": " + title.toString(), Toast.LENGTH_SHORT)
                        .show();
            }
        };
        listView.setOnItemClickListener(onItemClickListener);
        // listen to an ItemLongClick event
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                INote note = (INote) listAdapter.getItem(position);
                if(note == null) Log.i(TAG, "onItemLongClickListener note == null");
                INoteTitle title = note.getTitle();
                if(note == null) Log.i(TAG, "onItemLongClickListener title == null");
                Toast.makeText(getApplicationContext(),
                        "LongClick ListItem Number " + position + ": " + title.toString(), Toast.LENGTH_LONG)
                        .show();
                // to use with or without ItemClick event
                // don't use the event again?
                return false;
            }
        };
        listView.setOnItemLongClickListener(onItemLongClickListener);
        // connecting the list adapter to this ListActivity
        listView.setAdapter(listAdapter);
    }

    /**
     * This is where we would get data from a database or other location to plug
     * into the list adapter. For this example the ListAdapter is an
     * ArrayAdapter and the array is hard-coded. ListAdapter is an abstract
     * class and there are many classes that extend it to provide connection
     * with various data holding formats.
     */
    private void collectListData() {
        IStorageProvider storageProvider = new LoremStorageProvider(); //TODO more usefull storageprovider
        View.OnClickListener openNoteListener = new OpenNoteListener(storageProvider, this);
        this.listAdapter = new NoteListAdapter(
                storageProvider,
                openNoteListener
        );
    }
}
