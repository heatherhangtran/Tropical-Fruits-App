package com.example.randybiglow.tropical_fruits_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HangTran on 5/2/16.
 */
public class FruitDatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = FruitDatabaseHelper.class.getCanonicalName();

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Fruits_new.db";
    public static final String FRUITS_LIST_TABLE_NAME = "FRUITS_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_COMMON_NAME = "COMMON_NAME";
    public static final String COL_REGION = "REGION";
    public static final String COL_SEASON = "SEASON";
    public static final String COL_MEDICINAL = "MEDICINAL";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String COL_IMAGE = "IMAGE";

    public static final String[] FRUITS_COLUMNS = {COL_ID, COL_COMMON_NAME, COL_REGION, COL_SEASON, COL_MEDICINAL, COL_ITEM_DESCRIPTION, COL_IMAGE};

    public static final String CREATE_FRUITS_LIST_TABLE =
            "CREATE TABLE " + FRUITS_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_COMMON_NAME + " TEXT, " +
                    COL_REGION + " TEXT, " +
                    COL_SEASON + " TEXT, " +
                    COL_MEDICINAL + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT, " +
                    COL_IMAGE + " TEXT )";

    //Applying Singleton to get individual detail pages for each item.
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
        //Seeding database
        addFruits(db, new Fruits( 1, "Jackfruit", "Asia", "Summer", "antibacterial", "yellow pieces of heaven", R.drawable.jackfruit));
        addFruits(db, new Fruits( 2, "Mangosteen", "Asia", "Fall", "diuretic", "purple exterior with white flesh", R.drawable.mangosteen));
        addFruits(db, new Fruits( 3, "Cherimoya", "North America", "Spring", "anti-inflammatory", "sweet custard", R.drawable.cherimoya));
        addFruits(db, new Fruits( 4, "Lychee", "Asia", "Winter", "anti-acid", "crispy grapes", R.drawable.lychee));
        addFruits(db, new Fruits( 5, "Pitaya", "Asia", "Fall", "anti-aging", "mild kiwi", R.drawable.pitaya));
        addFruits(db, new Fruits( 6, "Waterapple", "Asia", "Spring", "anti-diabetes", "tangy apple", R.drawable.waterapples));
        addFruits(db, new Fruits( 7, "Breadfruit", "Africa", "Summer", "antioxidants", "spongy", R.drawable.breadfruit));
        addFruits(db, new Fruits( 8, "Soursop", "South America", "Spring", "antimicrobial", "sweet and tangy custard", R.drawable.soursop));
        addFruits(db, new Fruits( 9, "Redcurrant", "Europe", "Summer", "anti-coagulant", "bright red goodness", R.drawable.redcurrant));
        addFruits(db, new Fruits( 10, "Finger Lime", "Australia", "Winter", "antioxidant", "beautiful sour beings", R.drawable.fingerlime));
        addFruits(db, new Fruits( 11, "Persimmons", "North America", "Fall", "anti-inflammatory", "cinnamon crispy apples", R.drawable.persimmon));
        addFruits(db, new Fruits( 12, "Ackee", "North America", "Summer", "cough suppressant", "spongy and savory", R.drawable.ackee));
        addFruits(db, new Fruits( 13, "Guava", "South America", "Winter", "pain reliever", "fragrant bursts of firework on the tongue", R.drawable.guava));
        addFruits(db, new Fruits( 14, "Miracle Fruit", "Africa", "Spring", "antioxidant", "mildly sweet, but causes sour food taste sweet", R.drawable.miracle_fruit));
        addFruits(db, new Fruits( 15, "Ganga", "North America", "Spring", "anything", "buds, lots of buds", R.drawable.buds));
    }

    //Make sure onUpgrade gets called before onCreate.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FRUITS_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    //Methods for adding data to database.
    void addFruits(SQLiteDatabase db, Fruits fruits) {
//        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_COMMON_NAME, fruits.getName());
        values.put(COL_REGION, fruits.getRegion());
        values.put(COL_SEASON, fruits.getSeason());
        values.put(COL_MEDICINAL, fruits.getMedicinal());
        values.put(COL_ITEM_DESCRIPTION, fruits.getDescription());
        values.put(COL_IMAGE, fruits.getImage());

        db.insert(FRUITS_LIST_TABLE_NAME, null, values);
//        db.close();
    }

    //Allows the main page of the app to have a default list.
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

    //This allows for the Detail Page to populate information.
    public Cursor getDescriptionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FRUITS_LIST_TABLE_NAME,
                new String[]{COL_COMMON_NAME, COL_REGION, COL_SEASON, COL_MEDICINAL, COL_ITEM_DESCRIPTION, COL_IMAGE},
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

    //Allows the cursor to be searchable through three different criteria.
    public Cursor searchFruits(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.query(FRUITS_LIST_TABLE_NAME,
                FRUITS_COLUMNS,
                COL_COMMON_NAME + " LIKE ? OR " + COL_REGION + " LIKE ? OR " + COL_SEASON + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%" , "%" + query + "%"},
                null,
                null,
                null,
                null);

        return mCursor;
    }
}