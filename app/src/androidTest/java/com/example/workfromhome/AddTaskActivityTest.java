package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTaskActivityTest {
    @Rule
    public ActivityTestRule<AddTaskActivity> AddTasksActivityTestRule = new ActivityTestRule<AddTaskActivity>(AddTaskActivity.class);

    private AddTaskActivity addtask_act = null;

    @Before
    public void setUp() throws Exception {

        addtask_act = AddTasksActivityTestRule.getActivity();

    }

    @Test
    public void testTaskActivity(){

        View view = addtask_act.findViewById(R.id.testcase2);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        addtask_act = null;

    }
}