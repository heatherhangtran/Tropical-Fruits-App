package com.example.randybiglow.tropical_fruits_app;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by RandyBiglow on 5/7/16.
 */
public class TasteTesting {
    @Test
    public void test(){
        //Testing Database
        assertEquals("COMMON_NAME", FruitDatabaseHelper.COL_COMMON_NAME);
        assertEquals("_id", FruitDatabaseHelper.COL_ID);
        assertEquals("REGION", FruitDatabaseHelper.COL_REGION);
        assertEquals("FruitsDatabaseName.db", FruitDatabaseHelper.DATABASE_NAME);
        assertNotNull(FruitDatabaseHelper.COL_ITEM_DESCRIPTION);
        //My favorite test: assertNotNull!

        assertNotNull(MainActivity.class);
        //Experimented with Front-end testing, but only came up with the existence of the MainActivity class.
        //This line of test is pointless.
    }
}
