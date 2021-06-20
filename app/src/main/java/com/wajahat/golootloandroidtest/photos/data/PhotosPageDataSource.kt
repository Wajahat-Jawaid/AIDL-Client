package com.wajahat.golootloandroidtest.photos.data

import androidx.paging.PageKeyedDataSource
import com.wajahat.golootloandroidtest.data.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 * Data source to handle the pagination using Jetpack's pagination library
 */
class PhotosPageDataSource(
    private val dataSource: PhotosRemoteDataSource,
    private val dao: PhotoDao,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<Photo>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = dataSource.getPhotos(page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!
                // update the local DB
                dao.insertAll(results)
                // return the original remote API callback
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        // TODO network error handling
    }
}