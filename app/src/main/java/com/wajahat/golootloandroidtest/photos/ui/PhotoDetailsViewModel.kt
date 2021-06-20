package com.wajahat.golootloandroidtest.photos.ui

import androidx.lifecycle.ViewModel
import com.wajahat.golootloandroidtest.photos.data.PhotosRepository
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotoDetailsViewModel @Inject constructor(repository: PhotosRepository) : ViewModel() {

    lateinit var id: String

    val photo by lazy { repository.observePhoto(id) }
}