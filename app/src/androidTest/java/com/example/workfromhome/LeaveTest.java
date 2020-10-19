package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaveTest {
    @Rule
    public ActivityTestRule<Leave> nActivityTestRule = new ActivityTestRule<Leave>(Leave.class);
    private Leave nActivity = null;

    @Before
    public void setUp() throws Exception {

        nActivity = nActivityTestRule.getActivity();

    }

    @Test

    public void testLaunch(){

        View view = nActivity.findViewById(R.id.view2);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        nActivity = null;
    }
}