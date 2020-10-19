package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfirmUpdateActivityTest {
    @Rule
    public ActivityTestRule<ConfirmUpdateActivity> ConfirmUpdateActivityTestRule = new ActivityTestRule<ConfirmUpdateActivity>(ConfirmUpdateActivity.class);

    private ConfirmUpdateActivity conf_update = null;

    @Before
    public void setUp() throws Exception {

        conf_update = ConfirmUpdateActivityTestRule.getActivity();

    }

    @Test
    public void testTaskActivity(){

        View view = conf_update.findViewById(R.id.testcase4);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        conf_update = null;

    }
}