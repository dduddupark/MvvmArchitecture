package com.example.suyeon.mvvmarchitecture.di.module;

import android.content.Context;

import com.example.suyeon.mvvmarchitecture.di.scope.ActivityScope;
import com.example.suyeon.mvvmarchitecture.util.ToastManager;
import com.example.suyeon.mvvmarchitecture.view.login.LoginActivity;
import com.example.suyeon.mvvmarchitecture.view.login.LoginBindingModule;
import com.example.suyeon.mvvmarchitecture.view.main.MainActivity;
import com.example.suyeon.mvvmarchitecture.view.main.MainBindingModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

//Component에 연결되어 의존성 객체를 생성합니다. 생성 후 Scope에 따라 관리도 합니다.
@Module
public abstract class ActivityBindingModule {

    @Singleton
    @Provides
    static ToastManager provideToastManager(Context context) {
        return new ToastManager(context);
    }

    @ActivityScope
    // ContributesAndroidInjector 어노테이션을 달고 반환타입을 통해 해당 Activity의 Subcomponent를 생성합니다.
    // modules에 Subcomponent와 연결할 Module을 정의합니다. 이 Module들이 실제 의존성 객체를 생성합니다.
    @ContributesAndroidInjector(modules = {MainBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {LoginBindingModule.class})
    abstract LoginActivity bindLoginActivity();

}
