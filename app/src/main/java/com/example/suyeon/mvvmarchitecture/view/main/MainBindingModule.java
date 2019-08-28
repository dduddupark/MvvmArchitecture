package com.example.suyeon.mvvmarchitecture.view.main;

import android.content.Context;

import com.example.suyeon.mvvmarchitecture.di.scope.ActivityContext;
import com.example.suyeon.mvvmarchitecture.di.scope.FragmentScope;
import com.example.suyeon.mvvmarchitecture.view.main.sub.SubFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainBindingModule {

    @Binds
    @ActivityContext
    abstract Context provideContext(MainActivity mainActivity);

    @FragmentScope
    // ContributesAndroidInjector로 FragmentSubcomponent를 생성합니다.
    @ContributesAndroidInjector
    abstract SubFragment provideSubFragment();
}
