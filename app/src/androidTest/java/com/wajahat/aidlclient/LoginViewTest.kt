package com.wajahat.aidlclient

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.wajahat.aidlclient.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@RunWith(AndroidJUnit4::class)
class LoginViewTest : AbstractTest() {

    // NOTE: Before running the test cases, change the startDestination in nav_main.xml
    // from splash_fragment to login_fragment. If the tests are run with splash_fragment
    // as to be the startDestination, btn_submit or any other views won't be found in that
    // fragment and hence the test will fail.
    // Though this technique is not used in production and that's usually handled by mocking
    // the fragment. But since my approach is clearly demonstrated here and also that will
    // take little extra time due to which I've kept it as with the ActivityTestRule
    // @get is necessary otherwise mActivityRule won't be found by the JVM
    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testButtonClickable() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_submit))
            .check(matches(ViewMatchers.isClickable()))
    }

    @Test
    fun testButtonText() {
        // Change the text to see the test case getting failed
        Espresso.onView(ViewMatchers.withId(R.id.btn_submit))
            .check(matches(ViewMatchers.withText("Sign in")))
    }
}