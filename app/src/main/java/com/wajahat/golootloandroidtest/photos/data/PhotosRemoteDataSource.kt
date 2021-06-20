package com.wajahat.golootloandroidtest.photos.data

import com.wajahat.golootloandroidtest.api.BaseDataSource
import com.wajahat.golootloandroidtest.api.PhotosService
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotosRemoteDataSource @Inject constructor(private val service: PhotosService) :
    BaseDataSource() {

    suspend fun getPhotos(page: Int, limit: Int) = getResult { service.getPhotos(page, limit) }
    suspend fun getPhoto(id: String) = getResult { service.getPhoto(id) }
}
