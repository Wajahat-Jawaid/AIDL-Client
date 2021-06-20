package com.wajahat.golootloandroidtest.photos.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wajahat.golootloandroidtest.data.resultLiveData
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Singleton
class PhotosRepository @Inject constructor(
    private val dao: PhotoDao,
    private val remoteDataSource: PhotosRemoteDataSource
) {

    /* If there is the internet, get the data from the remote API. Otherwise get the ones
    * stored in Room DB if there are
    */
    fun observePagedPhotos(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable) observeRemotePagedPhotos(coroutineScope)
        else observeLocalPagedPhotos()

    /* Get local photos */
    private fun observeLocalPagedPhotos(): LiveData<PagedList<Photo>> {
        return LivePagedListBuilder(
            dao.getPagedPhotos(),
            PhotosPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    /* Get photos from remote API */
    private fun observeRemotePagedPhotos(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Photo>> {
        val dataSourceFactory = PhotosPageDataSourceFactory(
            remoteDataSource,
            dao, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            PhotosPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    /* Get the one from the local DB (i.e. Room). Meanwhile, trigger the network call to fetch
     * the updated data and then update the local DB with the updated data fetched from
     * remote API */
    fun observePhoto(id: String) = resultLiveData(
        databaseQuery = { dao.getPhoto(id) },
        networkCall = { remoteDataSource.getPhoto(id) },
        saveCallResult = { dao.insert(it) })
        .distinctUntilChanged()
}