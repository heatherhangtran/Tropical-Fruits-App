package com.example.randybiglow.tropical_fruits_app;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FruitDatabaseHelper helper = FruitDatabaseHelper.getInstance(Details.this);

        int id = getIntent().getIntExtra("id",-1);

        if(id >= 0){
            Cursor cursor = helper.getDescriptionById(id);
            String commonName = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_COMMON_NAME));
            String region = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_REGION));
            String season = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_SEASON));
            String medicinal = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_MEDICINAL));
            String description = cursor.getString(cursor.getColumnIndex(FruitDatabaseHelper.COL_ITEM_DESCRIPTION));
            TextView textView = (TextView)findViewById(R.id.detailsTextView);
            textView.setText(commonName + " originates from " + region + ". The peak season is " + season + ". One of it's medicinal benefits is " + medicinal + ". The fruit tastes like " + description + ".");
        }
    }
}
