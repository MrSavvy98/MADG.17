package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateTaskActivityTest {
    @Rule
    public ActivityTestRule<UpdateTaskActivity> UpdateTaskActivityTestRule = new ActivityTestRule<UpdateTaskActivity>(UpdateTaskActivity.class);

    private UpdateTaskActivity update = null;

    @Before
    public void setUp() throws Exception {

        update = UpdateTaskActivityTestRule.getActivity();

    }

    @Test
    public void testTaskActivity(){

        View view = update.findViewById(R.id.testcase5);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        update = null;

    }
}