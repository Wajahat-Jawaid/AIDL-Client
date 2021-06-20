package com.wajahat.golootloandroidtest.photos.ui

import androidx.lifecycle.ViewModel
import com.wajahat.golootloandroidtest.di.CoroutineScopeIO
import com.wajahat.golootloandroidtest.photos.data.PhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotosListViewModel @Inject constructor(
    private val repository: PhotosRepository,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    var connectivityAvailable: Boolean = false

    val photos by lazy {
        repository.observePagedPhotos(
            connectivityAvailable, ioCoroutineScope
        )
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}