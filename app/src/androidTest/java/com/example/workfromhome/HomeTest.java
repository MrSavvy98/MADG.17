package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeTest {

    @Rule
    public ActivityTestRule<Home> nActivityTestRule = new ActivityTestRule<Home>(Home.class);
    private Home nActivity = null;

    @Before
    public void setUp() throws Exception {

        nActivity = nActivityTestRule.getActivity();

    }

    @Test

    public void testLaunch(){

        View view = nActivity.findViewById(R.id.textView);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        nActivity = null;
    }
}