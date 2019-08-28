package com.example.suyeon.mvvmarchitecture.base;

import com.example.suyeon.mvvmarchitecture.BuildConfig;
import com.example.suyeon.mvvmarchitecture.di.component.DaggerApplicationComponent;
import com.example.suyeon.mvvmarchitecture.util.FileLoggingTree;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

//DaggerApplication를 상속
public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) { //로그 설정
            Timber.plant(new FileLoggingTree(this));
        }
    }

    //ApplicationComponent에서 정의한 Builder를 활용하여 Component와 연결합니다.
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        return DaggerApplicationComponent.builder().application(this).build();
    }
}
