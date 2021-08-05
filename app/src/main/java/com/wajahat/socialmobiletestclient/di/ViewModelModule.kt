package com.wajahat.socialmobiletestclient.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahat.socialmobiletestclient.ui.login.LoginViewModel
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
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
