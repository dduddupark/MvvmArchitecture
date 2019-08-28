package com.example.suyeon.mvvmarchitecture.view.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.suyeon.mvvmarchitecture.data.PreferenceHelper;
import com.example.suyeon.mvvmarchitecture.data.model.LoginData;
import com.example.suyeon.mvvmarchitecture.data.rest.NetworkRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LoginViewModel extends ViewModel {

    /**
     * Network
     */
    private final NetworkRepository mNetworkRepository;
    /**
     * 로그인 데이터
     */
    private final MutableLiveData<LoginData> mLoginData = new MutableLiveData<>();
    /**
     * 에러 데이터
     */
    private final MutableLiveData<String> mError = new MutableLiveData<>();

    //MutableLiveData란 변경할 수 있는 LiveData 형입니다.
    //일반적인 LiveData형은 변경할 수 없고 오로지 데이터의 변경값만을 소비하는데 반해
    //MutableLiveData는 데이터를 UI Thread와 Background Thread에서 선택적으로 바꿀 수 있습니다.
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

    /**
     * 생성자
     *
     * @param networkRepository
     * @param preferenceHelper
     */
    @Inject
    public LoginViewModel(NetworkRepository networkRepository, PreferenceHelper preferenceHelper) {
        mNetworkRepository = networkRepository;
        mPreferenceHelper = preferenceHelper;
        mDisposable = new CompositeDisposable();
    }

    public LiveData<LoginData> getLogin() {
        return mLoginData;
    }

    public LiveData<String> getError() {
        return mError;
    }

    public LiveData<Boolean> getLoading() {
        return mLoading;
    }

    /**
     * 로그인 요청
     *
     * @param storeCode
     */
    public void connectLogin(final String storeCode) {
        mLoading.setValue(true);

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(Object object) {
                        LoginData loginData = new LoginData(storeCode, storeCode + "점");

                        Timber.d("saveLoginData");
                        setAutoLogin(true);
                        saveLoginData(loginData);
                        mLoginData.setValue(loginData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoading.setValue(false);
                        mError.setValue(e.toString());

                        Timber.e("error = " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                        mLoading.setValue(false);
                    }
                });
    }

    /**
     * ViewModel은 액티비티 스코프가 완전히 종료되는 시점에 종료가 되고, 이때 onCleared() 함수가 호출됩니다.
     * 이 콜백은 ViewModel 클래스의 유일한 함수이며 ViewModel의 리소스를 해제하기에 적합한 곳입니다.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }

    }

    /**
     * 로그인 데이터 저장하기
     *
     * @param loginData
     */
    public void saveLoginData(LoginData loginData) {
        mPreferenceHelper.putLoginData(loginData);
    }

    /**
     * 로그인 데이터 가져오기
     *
     * @return
     */
    public LoginData getLoginData() {
        return mPreferenceHelper.getLoginData();
    }

    /**
     * 자동 로그인 여부
     *
     * @param isSuccess
     */
    public void setAutoLogin(boolean isSuccess) {
        mPreferenceHelper.putAutoLogin(isSuccess);
    }
}
