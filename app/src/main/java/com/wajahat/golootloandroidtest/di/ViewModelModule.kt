package com.wajahat.golootloandroidtest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahat.golootloandroidtest.photos.ui.PhotoDetailsViewModel
import com.wajahat.golootloandroidtest.photos.ui.PhotosListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotosListViewModel::class)
    abstract fun bindPhotosViewModel(viewModel: PhotosListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailsViewModel::class)
    abstract fun bindPhotoDetailsViewModel(viewModel: PhotoDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
