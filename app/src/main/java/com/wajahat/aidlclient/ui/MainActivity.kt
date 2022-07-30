package com.wajahat.aidlclient.ui

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.IdlingResource
import com.wajahat.aidlclient.R
import com.wajahat.aidlclient.ui.idlingresource.SimpleIdlingResource
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    // To handle the espresso test cases
    private lateinit var mIdlingResource: SimpleIdlingResource
    private var resultHandled = false

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        setResultHandledIdleState()
    }

    /**
     * Only called from test, creates and returns a new [SimpleIdlingResource].
     */
    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        if (!::mIdlingResource.isInitialized) {
            mIdlingResource =
                SimpleIdlingResource(javaClass.simpleName + "-resultHandledIdlingResource")
        }
        if (resultHandled) {
            mIdlingResource.setIdleState(true)
        } else {
            mIdlingResource.reset()
        }
        return mIdlingResource
    }

    /**
     * Set the result handled idle state for the IdlingResource
     */
    private fun setResultHandledIdleState() {
        resultHandled = true
        if (::mIdlingResource.isInitialized) {
            mIdlingResource.setIdleState(true)
        }
    }
}
