package com.wajahat.golootloandroidtest.photos.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos ORDER BY author DESC")
    fun getPagedPhotos(): DataSource.Factory<Int, Photo>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: String): LiveData<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: Photo)
}
