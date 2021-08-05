package com.wajahat.socialmobiletestclient.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.databinding.DataBindingUtil
import com.wajahat.socialmobiletestclient.R
import com.wajahat.socialmobiletestclient.core.BaseFragment
import com.wajahat.socialmobiletestclient.data.ProfileHolder
import com.wajahat.socialmobiletestclient.databinding.FragmentLoginBinding
import com.wajahat.socialmobiletestclient.di.Injectable
import com.wajahat.socialmobiletestclient.di.injectViewModel
import com.wajahat.socialmobiletestclient.ui.throttlingClickListener
import com.wajahat.socialmobiletestclient.ui.toTrimmedString

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>(), Injectable {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        viewModel = injectViewModel(viewModelFactory)
        super.onViewCreated(view, savedInstanceState)
        binding.loginFormLayout.btnSubmit.throttlingClickListener { initLoginRequest() }
    }

    override fun onStart() {
        super.onStart()
        val profile = ProfileHolder.profile
        // We got the profile as the result from server application, update the UI now
        if (profile != null) {
            binding.loginFormLayout.root.visibility = View.GONE
            binding.profileInfoLayout.profile = profile
            binding.profileInfoLayout.root.visibility = View.VISIBLE
        } else {
            binding.loginFormLayout.root.visibility = View.VISIBLE
            binding.profileInfoLayout.profile = null
            binding.profileInfoLayout.root.visibility = View.GONE
        }
    }

    private fun initLoginRequest() {
        if (!validateData())
            return
        // Set the login credentials values so that they can be consumed by the IPC call
        viewModel.username.value = binding.loginFormLayout.textUsername.toTrimmedString()
        viewModel.password.value = binding.loginFormLayout.textPassword.toTrimmedString()

        // try to connect with the remote service to notify the server app about an incoming request
        viewModel.connectToRemoteService()
    }

    private fun validateData(): Boolean {
        if (binding.loginFormLayout.textUsername.toTrimmedString().isEmpty()
            || !Patterns.EMAIL_ADDRESS.matcher(binding.loginFormLayout.textUsername.toTrimmedString())
                .matches()
        ) {
            showToast(R.string.username_invalid_msg)
            return false
        }
        if (binding.loginFormLayout.textPassword.toTrimmedString().isEmpty()) {
            showToast(R.string.password_invalid_msg)
            return false
        }

        return true
    }

    override fun getViewId() = R.layout.fragment_login
    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentLoginBinding>(view)!!
}