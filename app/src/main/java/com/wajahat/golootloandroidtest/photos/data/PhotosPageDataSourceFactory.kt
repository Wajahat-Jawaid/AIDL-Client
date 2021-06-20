package com.wajahat.golootloandroidtest.photos.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class PhotosPageDataSourceFactory @Inject constructor(
    private val dataSource: PhotosRemoteDataSource,
    private val dao: PhotoDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Photo>() {

    private val liveData = MutableLiveData<PhotosPageDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val source = PhotosPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}