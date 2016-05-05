package com.example.randybiglow.tropical_fruits_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RandyBiglow on 5/2/16.
 */
public class FruitDatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = FruitDatabaseHelper.class.getCanonicalName();

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Fruits_new.db";
    public static final String FRUITS_LIST_TABLE_NAME = "FRUITS_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_COMMON_NAME = "COMMON_NAME";
    public static final String COL_REGION = "REGION";
    public static final String COL_SEASON = "SEASON";
    public static final String COL_MEDICINAL = "MEDICINAL";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";

    public static final String[] FRUITS_COLUMNS = {COL_ID, COL_COMMON_NAME, COL_REGION, COL_SEASON, COL_MEDICINAL, COL_ITEM_DESCRIPTION};

    public static final String CREATE_FRUITS_LIST_TABLE =
            "CREATE TABLE " + FRUITS_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_COMMON_NAME + " TEXT, " +
                    COL_REGION + " TEXT, " +
                    COL_SEASON + " TEXT, " +
                    COL_MEDICINAL + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT )";

    private static FruitDatabaseHelper mInstance;
    
    public static FruitDatabaseHelper getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new FruitDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public FruitDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FRUITS_LIST_TABLE);
    }

    //Make sure onUpgrade gets called before onCreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FRUITS_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public Cursor getFruitsList() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FRUITS_LIST_TABLE_NAME,
                FRUITS_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor getDescriptionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FRUITS_LIST_TABLE_NAME,
                new String[]{COL_COMMON_NAME, COL_REGION, COL_SEASON, COL_MEDICINAL, COL_ITEM_DESCRIPTION},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor searchFruits(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.query(FRUITS_LIST_TABLE_NAME,
                FRUITS_COLUMNS,
                COL_COMMON_NAME + " LIKE ? or " + COL_REGION + " = ? or " + COL_SEASON + " LIKE ?",
                new String[]{"%" + query + "%", query , query},
                null,
                null,
                null,
                null);

        return mCursor;
    }
}