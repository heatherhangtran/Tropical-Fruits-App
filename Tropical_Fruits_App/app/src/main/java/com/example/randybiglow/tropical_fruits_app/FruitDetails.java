package com.example.randybiglow.tropical_fruits_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FruitDetails extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);

        mTextView = (TextView) (findViewById(R.id.fruitTextView));

        Intent fruitIntent = getIntent();
        String eachFruit = fruitIntent.getStringExtra("name");
        mTextView.setText(eachFruit);

    }


}
