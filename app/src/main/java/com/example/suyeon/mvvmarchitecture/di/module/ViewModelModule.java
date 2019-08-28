package com.example.suyeon.mvvmarchitecture.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.suyeon.mvvmarchitecture.di.util.ViewModelFactory;
import com.example.suyeon.mvvmarchitecture.di.util.ViewModelKey;
import com.example.suyeon.mvvmarchitecture.view.login.LoginViewModel;
import com.example.suyeon.mvvmarchitecture.view.main.sub.SubFragment;
import com.example.suyeon.mvvmarchitecture.view.main.sub.SubViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    /**
     * Map<Class<?>, Provider<ViewModel>>
     */

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindListViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SubViewModel.class)
    abstract ViewModel bindSubViewModel(SubViewModel subViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
