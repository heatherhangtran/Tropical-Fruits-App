package com.example.randybiglow.tropical_fruits_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RandyBiglow on 5/2/16.
 */
public class FruitDatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Fruits.db";

    public static final String SQL_CREATE_FRUIT_TABLE =
            "CREATE TABLE fruits ( id INTEGER PRIMARY KEY, common_name TEXT, region TEXT, season TEXT, medicinal TEXT )";
    public static final String SQL_DROP_FRUIT_TABLE = "DROP TABLE IF EXITS fruits";

    public FruitDatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_FRUIT_TABLE);
    }

    //Make sure onUpgrade gets called before onCreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(SQL_DROP_FRUIT_TABLE);
        onCreate(db);
    }

    //This is optional for the SQLiteOpenHelper.
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void insert (int id, String common_name, String region, String season, String medicinal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("common_name", common_name);
        values.put("region", region);
        values.put("season", season);
        values.put("medicinal", medicinal);

        db.insert("fruits", null, values);
    }

    public Fruits getFruits(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = new String[]{"id", "common_name", "region", "season", "medicinal"};

        String selection = "id = ?";

        String[] selectionArgs = new String[] {String.valueOf(id)};

        Cursor cursor = db.query("fruits", projection, selection, selectionArgs, null, null, null, null);

        cursor.moveToFirst();

        String common_name = cursor.getString(cursor.getColumnIndex("common_name"));
        String region = cursor.getString(cursor.getColumnIndex("region"));
        String season = cursor.getString(cursor.getColumnIndex("season"));
        String medicinal = cursor.getString(cursor.getColumnIndex("medicinal"));

        return new Fruits(id, common_name, region, season, medicinal);
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = "id = ?";

        String[] selectionArgs = new String[]{ String.valueOf(id)};

        db.delete("fruits", selection, selectionArgs);
    }
}
