package com.wajahat.golootloandroidtest.photos.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wajahat.golootloandroidtest.util.AUTHOR
import com.wajahat.golootloandroidtest.util.DOWNLOAD_URL
import com.wajahat.golootloandroidtest.util.ID

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey
    @field:SerializedName(ID)
    val id: String,
    @field:SerializedName(AUTHOR)
    val author: String,
    @field:SerializedName(DOWNLOAD_URL)
    val url: String
)