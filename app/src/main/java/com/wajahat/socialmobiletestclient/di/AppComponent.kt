package com.wajahat.socialmobiletestclient.di

import android.app.Application
import com.wajahat.socialmobiletestclient.core.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}
