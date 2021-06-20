package com.wajahat.golootloandroidtest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
abstract class BaseFragment<out VDB> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Handle fragment arguments
        if (arguments != null)
            handleArguments(requireArguments())
    }

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

    open fun handleArguments(arguments: Bundle) {}

    fun showToast(@StringRes text: Int) {
        showToast(getString(text))
    }

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showSnack(msg: String) {
        Snackbar.make(view!!, msg, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnack(@StringRes msg: Int) {
        showSnack(getString(msg))
    }

    /** Abstract methods */
    abstract fun getViewId(): Int
    abstract fun injectBinding(view: View): VDB
}