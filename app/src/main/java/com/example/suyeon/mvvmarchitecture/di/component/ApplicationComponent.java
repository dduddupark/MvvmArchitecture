package com.example.suyeon.mvvmarchitecture.di.component;

import android.app.Application;

import com.example.suyeon.mvvmarchitecture.di.module.ActivityBindingModule;
import com.example.suyeon.mvvmarchitecture.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

// Scope
@Singleton

// 연결할 Module을 정의
@Component(modules = {ApplicationModule.class
        , AndroidSupportInjectionModule.class
        , ActivityBindingModule.class
        , AndroidInjectionModule.class
})

// Application과의 연결을 도울 AndroidInjector를 상속받고, 제네릭으로 DaggerApplication 클래스를 정의합니다.
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(DaggerApplication application);

    // Application과의 연결을 도울 Builder를 정의합니다.
    @Component.Builder
    interface Builder {

        //Builder 내의 메소드에 추가하거나 컴포넌트 팩토리내의 파라미터로 추가하여 객체를 컴포넌트가 가지고 있는 특정 키에 바인딩 하게 됩니다.
        //빌더나 팩토리에게 Application 인스턴스를 넘겨줌으로써 컴포넌트가 이 인스턴스들을 관리하게 되고 요청시 이 인스턴스들을 넘겨주게 됩니다.
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}