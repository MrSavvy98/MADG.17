package com.example.workfromhome;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LeaveTest {
    @Rule
    public ActivityTestRule<Leave> nActivityTestRule = new ActivityTestRule<Leave>(Leave.class);
    private Leave nActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ApplyLeave.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        nActivity = nActivityTestRule.getActivity();

    }

    @Test

    public void testLaunch(){

        View view = nActivity.findViewById(R.id.view2);
        assertNotNull(view);

    }

    @Test

    public void applyLeaveOnButtonClick(){

        assertNotNull(nActivity.findViewById(R.id.btn_add_leave));
        onView(withId(R.id.btn_add_leave)).perform(click());

        Activity applyLeave = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(applyLeave);
        applyLeave.finish();

    }

    @After
    public void tearDown() throws Exception {
        nActivity = null;
    }
}