package com.example.randybiglow.tropical_fruits_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by RandyBiglow on 5/5/16.
 */
public class tasteTesting {
    @Test
    public void test(){
        assertEquals("COMMON_NAME",FruitDatabaseHelper.COL_COMMON_NAME);
        assertEquals("_id",FruitDatabaseHelper.COL_ID);
        assertEquals("REGION",FruitDatabaseHelper.COL_REGION);
        assertEquals("Fruits_new.db",FruitDatabaseHelper.DATABASE_NAME);
        assertNotNull(FruitDatabaseHelper.COL_ITEM_DESCRIPTION);
    }


}
