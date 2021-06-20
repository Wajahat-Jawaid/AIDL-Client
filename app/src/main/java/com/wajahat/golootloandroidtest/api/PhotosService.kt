package com.wajahat.golootloandroidtest.api

import com.wajahat.golootloandroidtest.photos.data.Photo
import com.wajahat.golootloandroidtest.util.ID
import com.wajahat.golootloandroidtest.util.LIMIT
import com.wajahat.golootloandroidtest.util.PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
interface PhotosService {

    companion object {
        const val BASE_URL = "https://picsum.photos/"
    }

    @GET("${BASE_URL}v2/list")
    suspend fun getPhotos(
        @Query(PAGE) page: Int,
        @Query(LIMIT) limit: Int
    ): Response<List<Photo>>

    @GET("${BASE_URL}id/{id}/info")
    suspend fun getPhoto(@Path(ID) id: String): Response<Photo>
}