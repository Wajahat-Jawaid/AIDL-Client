package com.wajahat.socialmobiletestclient.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wajahat.socialmobiletestclient.R
import com.wajahat.socialmobiletestclient.core.BaseFragment
import com.wajahat.socialmobiletestclient.databinding.FragmentSplashBinding

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        injectBinding(view)
        super.onViewCreated(view, savedInstanceState)
        // For fake loading
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(R.id.action_splash_fragment_to_login_fragment)
        }, FAKE_LOADING_DURATION)
    }

    companion object {
        private const val FAKE_LOADING_DURATION = 1400L
    }

    override fun getViewId() = R.layout.fragment_splash
    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentSplashBinding>(view)!!
}