package com.example.workfromhome;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssignTaskActivityTest {
    @Rule
    public ActivityTestRule<AssignTaskActivity> AssignTasksActivityTestRule = new ActivityTestRule<AssignTaskActivity>(AssignTaskActivity.class);

    private AssignTaskActivity assigntask_act = null;

    @Before
    public void setUp() throws Exception {

        assigntask_act = AssignTasksActivityTestRule.getActivity();

    }

    @Test
    public void testTaskActivity(){

        View view = assigntask_act.findViewById(R.id.assignwork1);

        assertNotNull(view);
    }



    @After
    public void tearDown() throws Exception {

        assigntask_act = null;

    }
}