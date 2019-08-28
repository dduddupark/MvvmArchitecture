package com.example.suyeon.mvvmarchitecture.view.login;

import android.content.Context;

import com.example.suyeon.mvvmarchitecture.di.scope.ActivityContext;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginBindingModule {

    @Binds
    @ActivityContext
    abstract Context provideContext(LoginActivity loginActivity);
}
