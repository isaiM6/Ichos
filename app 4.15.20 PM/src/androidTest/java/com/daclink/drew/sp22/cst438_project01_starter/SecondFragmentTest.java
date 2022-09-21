package com.daclink.drew.sp22.cst438_project01_starter;

import static org.junit.Assert.assertNotNull;

import android.widget.Button;

import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputEditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SecondFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;
    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testButtonss(){
        Button search = mainActivity.findViewById(R.id.search_btn);
        Button sourceBtn = mainActivity.findViewById(R.id.newsSourceBtn);

        assertNotNull(search);
        assertNotNull(sourceBtn);
    }
//    @Test
//    public void testEditTextt(){
////        TextInputEditText newsSource = mainActivity.findViewById(R.id.newsSourceEditText);
//        TextInputEditText search = mainActivity.findViewById(R.id.search);
//
////        assertNotNull(newsSource);
//        assertNotNull(search);
//    }

    @After
    public void tearDown() throws Exception {
    }
}