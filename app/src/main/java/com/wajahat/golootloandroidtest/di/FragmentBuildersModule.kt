package com.wajahat.golootloandroidtest.di

import com.wajahat.golootloandroidtest.photos.ui.PhotoDetailsFragment
import com.wajahat.golootloandroidtest.photos.ui.PhotosListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePhotosListFragment(): PhotosListFragment

    @ContributesAndroidInjector
    abstract fun contributePhotoDetailsFragment(): PhotoDetailsFragment
}
