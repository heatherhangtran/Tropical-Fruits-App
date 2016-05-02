package com.example.randybiglow.tropical_fruits_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

//        listFruits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Intent intent = new Intent (MainActivity.this, FruitDetails.class);
//            }
//        });
    }
}
