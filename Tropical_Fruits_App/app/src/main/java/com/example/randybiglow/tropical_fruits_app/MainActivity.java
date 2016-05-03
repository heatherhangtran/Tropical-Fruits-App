package com.example.randybiglow.tropical_fruits_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FruitDatabaseHelper db = new FruitDatabaseHelper(this);
//There has to be a way to clean out the database every time I restart the app or run a new instance of the app.
//        if (db.getFruits(1) != null && db.getFruits(2) != null && db.getFruits(3) != null && db.getFruits(4) != null
//                && db.getFruits(5) != null && db.getFruits(6) != null && db.getFruits(7) != null) {
//
//            db.delete(1);
//            db.delete(2);
//            db.delete(3);
//            db.delete(4);
//            db.delete(5);
//            db.delete(6);
//            db.delete(7);//This is a hacky way to clean the database for now.
//        }//Every time I run it, it is not creating a new database each time so I need to figure out what is going on with my Database.

        db.insert(1, "Jackfruit", "India", "Summer", "Antibacterial");
        db.insert(2, "Mangosteen", "Thailand", "Fall", "Relieves Eczema");
        db.insert(3, "Cherimoya", "Ecuador", "Spring", "Anti-inflammatory");
        db.insert(4, "Lychee", "Southern China", "Winter", "Reduce Acne");
        db.insert(5, "Pitaya", "Malaysia", "Summer", "Reduce Constipation");
        db.insert(6, "Waterapple", "Samoa", "Spring", "Anti-diabetic");
        db.insert(7, "Ganga", "Ancient China", "Summer", "Anything");

        Fruits retrieveFruits = db.getFruits(7);
        ((TextView)findViewById(R.id.mainTextView)).setText(retrieveFruits.toString());
    }
}
