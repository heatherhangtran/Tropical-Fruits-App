package com.example.randybiglow.tropical_fruits_app;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    FruitDatabaseHelper dbHelper;
    ListView listView;
    CursorAdapter adapter;
    Cursor cursor, cursor1, cursor2;

    //SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = null; //Ask instructors as to why this is needed (derived from demo).

        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        listView = (ListView)findViewById(R.id.mainListView);
        dbHelper = FruitDatabaseHelper.getInstance(MainActivity.this);
        cursor = dbHelper.getFruitsList();

        handleIntent(getIntent());

        //Switching from SimpleCursorAdapter to CursorAdapter.
        adapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cursor, new String[]{FruitDatabaseHelper.COL_COMMON_NAME}, new int[]{android.R.id.text1}, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Details.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(FruitDatabaseHelper.COL_ID)));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            cursor = dbHelper.searchFruits(query);
            //cursor = dbHelper.searchRegion(query);

            listView = (ListView)findViewById(R.id.mainListView);
            if(adapter == null) {
                adapter = new SimpleCursorAdapter(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{FruitDatabaseHelper.COL_COMMON_NAME},
                        new int[]{android.R.id.text1},
                        0
                );
                listView.setAdapter((adapter));

            }else {
                adapter.swapCursor(cursor);
            }
        }

    }

}
