package com.example.randybiglow.tropical_fruits_app;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    //Save space for the classes for this app.
    LinkedList<String> mStringList;
    ArrayAdapter<String> mAdapter;

    //EditText mEditText;
    //Button mButton;
    //Above are stretch goals.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adds a list of tropical fruits.
        //Once all of the requirements are done, I will add an option to add.
        mStringList = new LinkedList<>();
        mStringList.add("Jackfruit");
        mStringList.add("Mangosteen");
        mStringList.add("Cherimoya");
        mStringList.add("Lychee");
        mStringList.add("Pitaya");
        mStringList.add("Waterapple");

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStringList);

        ListView listFruits = (ListView) (findViewById(R.id.mainListView));
        listFruits.setAdapter((mAdapter));

        handleIntent(getIntent());

        listFruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FruitDetails.class);
                intent.putExtra("name", mStringList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fruits_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "Searching for " + query, Toast.LENGTH_SHORT).show();

        }
    }
}
