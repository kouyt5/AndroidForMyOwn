package com.example.permission;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainAcivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule=new
            ActivityTestRule<>(MainActivity.class);

    @Test
    public void editTest(){
        onView(withId(R.id.main_edit_text))
                .perform(
                        clearText(),
                        replaceText("chencong"),
                        closeSoftKeyboard()
                )
                .check(matches(withText("chencong")));
    }
}
