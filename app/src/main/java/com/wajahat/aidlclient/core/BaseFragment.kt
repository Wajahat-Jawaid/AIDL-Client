package com.wajahat.aidlclient.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.wajahat.aidlclient.di.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
abstract class BaseFragment<out VDB> : Fragment() {

    // ViewModelFactory instance which every viewModel will register into
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getViewId(), container, false)
        injectBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = resources.displayMetrics
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
    }

    fun showToast(@StringRes text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    /** Abstract methods */
    abstract fun getViewId(): Int
    abstract fun injectBinding(view: View): VDB
}