package com.example.randybiglow.tropical_fruits_app;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    FruitDatabaseHelper dbHelper;
    SimpleCursorAdapter adapter;
    ListView listView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);



        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        listView = (ListView)findViewById(R.id.mainListView);
        dbHelper = FruitDatabaseHelper.getInstance(MainActivity.this);
        final Cursor cursor = dbHelper.getFruitsList();
        adapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cursor, new String[]{FruitDatabaseHelper.COL_COMMON_NAME}, new int[]{android.R.id.text1}, 0);
        listView.setAdapter(adapter);
        handleIntent(getIntent());

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

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = dbHelper.searchFruits(query);
            adapter.swapCursor(cursor);
            adapter.notifyDataSetChanged();
        }
    }


}
