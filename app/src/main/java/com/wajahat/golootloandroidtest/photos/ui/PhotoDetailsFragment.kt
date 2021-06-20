package com.wajahat.golootloandroidtest.photos.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.wajahat.golootloandroidtest.MainActivity
import com.wajahat.golootloandroidtest.R
import com.wajahat.golootloandroidtest.base.BaseFragment
import com.wajahat.golootloandroidtest.data.Result
import com.wajahat.golootloandroidtest.databinding.FragmentPhotoDetailsBinding
import com.wajahat.golootloandroidtest.di.Injectable
import com.wajahat.golootloandroidtest.di.injectViewModel
import com.wajahat.golootloandroidtest.photos.data.Photo
import com.wajahat.golootloandroidtest.ui.save
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotoDetailsFragment : BaseFragment<FragmentPhotoDetailsBinding>(), Injectable {

    private val args: PhotoDetailsFragmentArgs by navArgs()
    private lateinit var mPhoto: Photo

    private lateinit var binding: FragmentPhotoDetailsBinding
    private lateinit var viewModel: PhotoDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        viewModel = injectViewModel(viewModelFactory)
        super.onViewCreated(view, savedInstanceState)

        viewModel.id = args.id
        setHasOptionsMenu(true)
        loadPhoto()
    }

    override fun onResume() {
        super.onResume()
        // Toolbar controls
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.title = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_download -> {
                // Check if photo object hasn't been initialized with the photo fetched from API
                if (!::mPhoto.isInitialized) {
                    showToast(getString(R.string.image_download_in_progress))
                    return false
                }
                // Validate the Marshmallow permissions and then proceed to saving
                validateAndProceedExternalStoragePermission(mPhoto.url)
                return true
            }
            android.R.id.home -> {
                navController.navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadPhoto() {
        viewModel.photo.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let { bindView(binding, it, args.likesCount, args.commentsCount) }
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(binding.coordinatorLayout, result.message!!, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun validateAndProceedExternalStoragePermission(imageUrl: String) {
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            } != PackageManager.PERMISSION_GRANTED) {
            requestExternalStoragePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        } else {
            saveImageToExternalStorage(imageUrl)
        }
    }

    private val requestExternalStoragePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                saveImageToExternalStorage(mPhoto.url)
            } else {
                showToast(R.string.grant_permission_image)
            }
        }

    private fun saveImageToExternalStorage(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    GlobalScope.launch {
                        resource.save(requireContext())
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun bindView(
        binding: FragmentPhotoDetailsBinding,
        p: Photo,
        likes: Int,
        comments: Int
    ) {
        binding.apply {
            photo = p
            likesCount = likes
            commentsCount = comments
            mPhoto = p
        }
    }

    override fun getViewId() = R.layout.fragment_photo_details
    override fun injectBinding(view: View) =
        DataBindingUtil.bind<FragmentPhotoDetailsBinding>(view)!!
}