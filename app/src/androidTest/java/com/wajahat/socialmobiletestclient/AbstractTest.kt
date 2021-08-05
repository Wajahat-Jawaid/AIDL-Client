package com.wajahat.socialmobiletestclient

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.wajahat.socialmobiletestclient.ui.MainActivity
import org.junit.After
import org.junit.Before

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
open class AbstractTest {

    private var mIdlingResource: IdlingResource? = null

    @Before
    fun registerIdlingResource() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity: MainActivity ->
            mIdlingResource = activity.getIdlingResource()
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @After
    fun unregisterIdlingResource() {
//        if (mIdlingResource != null) {
//            IdlingRegistry.getInstance().unregister(mIdlingResource);
//        }
    }
}