package com.example.suyeon.mvvmarchitecture.view.main.sub;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.suyeon.mvvmarchitecture.data.PreferenceHelper;
import com.example.suyeon.mvvmarchitecture.data.rest.NetworkRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SubViewModel extends ViewModel {
    /**
     * Network
     */
    private final NetworkRepository mNetworkRepository;
    /**
     * 에러 데이터
     */
    private final MutableLiveData<String> mError = new MutableLiveData<>();
    /**
     * 로딩 데이터
     */
    private final MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    /**
     * PreferenceHelper
     */
    private PreferenceHelper mPreferenceHelper;
    /**
     * CompositeDisposable
     */
    private CompositeDisposable mDisposable;

    @Inject
    public SubViewModel(NetworkRepository networkRepository, PreferenceHelper preferenceHelper) {
        mNetworkRepository = networkRepository;
        mPreferenceHelper = preferenceHelper;
        mDisposable = new CompositeDisposable();
    }

    /**
     * 에러메세지
     *
     * @return LiveData<String>
     */
    public LiveData<String> getError() {
        return mError;
    }

    /**
     * 로딩바 유무
     *
     * @return LiveData<Boolean>
     */
    public LiveData<Boolean> getLoading() {
        return mLoading;
    }


    public void connect() {

        //TODO RxJava
    }

    /**
     * CompositeDisposable Clear
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
