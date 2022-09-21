package com.daclink.drew.sp22.cst438_project01_starter;

import static org.junit.Assert.*;

import android.widget.Button;

import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputEditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;
    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testButtons(){
        Button button = mainActivity.findViewById(R.id.login_btn);
        Button registerBtn = mainActivity.findViewById(R.id.registration_btn);

        assertNotNull(button);
        assertNotNull(registerBtn);
    }
    @Test
    public void testEditText(){
        TextInputEditText userName = mainActivity.findViewById(R.id.username);
        TextInputEditText passWord = mainActivity.findViewById(R.id.password);

        assertNotNull(userName);
        assertNotNull(passWord);
    }
    @After
    public void tearDown() throws Exception {
    }
}