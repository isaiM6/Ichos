package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//UserDao test
@RunWith(AndroidJUnit4.class)
public class UserDAO_Test {
    @Test
    public void UserInsertTest(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO testingDB = Room.databaseBuilder(appContext, UserDb.class, UserDb.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getPersonDAO();

        User testUser = new User("userTest", "passwordTest", "CNN");

        testingDB.deleteUser(testUser);
        testingDB.insertUser(testUser);
        testingDB.insertUser(testUser);

        User testUser2 = testingDB.getUser(testUser.getUsername());
        assertEquals(testUser, testUser2);

        testUser2.setPassword("veryExtremelyHardPassword@1233445");

        testingDB.updateUser(testUser2);
        User extraUserTest = testingDB.getUser(testUser.getUsername());
        assertNotEquals(testUser, extraUserTest);
        assertEquals(testUser2, extraUserTest);

        assertEquals("com.daclink.drew.sp22.cst438_project01_starter", appContext.getPackageName());
    }

}
