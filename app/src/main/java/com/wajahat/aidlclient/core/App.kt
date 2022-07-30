package com.wajahat.aidlclient.core

import android.app.Activity
import android.app.Application
import com.wajahat.aidlclient.BuildConfig
import com.wajahat.aidlclient.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}