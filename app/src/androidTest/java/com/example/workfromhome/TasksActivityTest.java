package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class TasksActivityTest {
    @Rule
    public ActivityTestRule<TasksActivity> TasksActivityTestRule = new ActivityTestRule<TasksActivity>(TasksActivity.class);

    private TasksActivity task_act = null;

    @Before
    public void setUp() throws Exception {

        task_act = TasksActivityTestRule.getActivity();

    }

    @Test
    public void testTaskActivity(){

        View view = task_act.findViewById(R.id.testcase1);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        task_act = null;

    }
}