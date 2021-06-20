package com.wajahat.golootloandroidtest.photos.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wajahat.golootloandroidtest.MainActivity
import com.wajahat.golootloandroidtest.R
import com.wajahat.golootloandroidtest.base.BaseFragment
import com.wajahat.golootloandroidtest.databinding.FragmentPhotosListBinding
import com.wajahat.golootloandroidtest.di.Injectable
import com.wajahat.golootloandroidtest.di.injectViewModel
import com.wajahat.golootloandroidtest.ui.hide
import com.wajahat.golootloandroidtest.util.ConnectivityUtil

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotosListFragment : BaseFragment<FragmentPhotosListBinding>(), Injectable {

    private val adapter: PhotosListAdapter by lazy { PhotosListAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var binding: FragmentPhotosListBinding
    private lateinit var viewModel: PhotosListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        viewModel = injectViewModel(viewModelFactory)
        super.onViewCreated(view, savedInstanceState)

        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(requireContext())
        linearLayoutManager = LinearLayoutManager(activity)
        setLayoutManager()
        binding.recyclerView.adapter = adapter

        loadPhotos(adapter)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as MainActivity).supportActionBar?.setTitle(R.string.app_name)
    }

    private fun loadPhotos(adapter: PhotosListAdapter) {
        viewModel.photos.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerView

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.scrollToPosition(scrollPosition)
    }

    override fun getViewId() = R.layout.fragment_photos_list
    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentPhotosListBinding>(view)!!
}