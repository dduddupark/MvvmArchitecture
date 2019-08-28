package com.example.suyeon.mvvmarchitecture.di.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

// 한번 의존성이 생성되고 난 후에는 기존 인스턴스를 그대로 사용
@Singleton

/*
 * AAC 의 ViewModelProvider.Factory 를 상속하는 클래스를 제작.
 * 생성자로는 Map<Class<*>, Provider<ViewModel>> 를 받는데, 이 생성자는 미리 @IntoMap 와 @ViewModelKey 를 부착하여
 * Module 에 제공된 ViewModel 클래스의 클래스 객체와 그 ViewModel 의 생성자를 제공하는 Provider 객체를 각각 key, value로서 받는다.
 * 따라서, Map에는 MainViewModel.class.java 라는 키에 Provider<MainViewModel> 가 제공됨
 * 이 Provider 는 해당 ViewModel 에 대한 생성자를 제공할 수 있는 기능을 가지고 있기 때문에,
 * 실제로 ViewModel 의 생성자가 수십개 이상 있어도 그 생성자가 Dagger 에 의해 제공된다면 실제로는 의존성만 가져오면 됨
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}