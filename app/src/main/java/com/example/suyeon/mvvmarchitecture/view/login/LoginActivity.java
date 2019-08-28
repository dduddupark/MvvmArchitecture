package com.example.suyeon.mvvmarchitecture.view.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.base.BaseActivity;
import com.example.suyeon.mvvmarchitecture.databinding.ActivityLoginBinding;
import com.example.suyeon.mvvmarchitecture.di.util.ViewModelFactory;
import com.example.suyeon.mvvmarchitecture.util.ToastManager;
import com.example.suyeon.mvvmarchitecture.view.dialog.DialogFactory;
import com.example.suyeon.mvvmarchitecture.view.dialog.ProgressDialog;
import com.example.suyeon.mvvmarchitecture.view.main.MainActivity;

import javax.inject.Inject;

import timber.log.Timber;

public class LoginActivity extends BaseActivity {

    /**
     * ViewModelFactory
     */
    @Inject
    ViewModelFactory mViewModelFactory;
    /**
     * ProgressDialog
     */
    @Inject
    ProgressDialog mProgressDialog;
    /**
     * ToastManager
     */
    @Inject
    ToastManager mToastManager;

    /**
     * DataBinding
     */
    private ActivityLoginBinding mBinding;
    /**
     * ViewModel
     */
    private LoginViewModel mLoginViewModel;
    /**
     * BaseDialog
     */
    private Dialog mBaseDialog;

    /**
     * LoginActivity 가져오기
     *
     * @param context Context
     * @return LoginActivity Intent
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 뷰 모델 초기화 및 관찰자 연결
        mLoginViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LoginViewModel.class);

        mBinding = (ActivityLoginBinding) getViewDataBinding();

        // 데이터 바인딩에 LifecycleOwner 연결하여 liveData 를 DataBinding 과 사용 할 수 있게 함
        mBinding.setLifecycleOwner(this);

        observableViewModel();
    }

    public void loginClick(View view) {

        Timber.d("loginClick");

        String strCode = mBinding.etStoreCode.getText().toString().trim();

        mLoginViewModel.connectLogin(strCode);
    }

    private void observableViewModel() {

        mLoginViewModel.getLogin().observe(this, data -> {
            Timber.d("getLogin");
            Timber.d("data =  " + mLoginViewModel.getLoginData());
            successLogin();
        });

        mLoginViewModel.getError().observe(this, errorMsg -> {
            Timber.d("error = " + errorMsg);
            mBaseDialog = DialogFactory.createDialog(LoginActivity.this
                    , getString(R.string.text_error)
                    , errorMsg
                    , getString(R.string.action_close)
                    , null);
            mBaseDialog.show();
        });

        mLoginViewModel.getLoading().observe(this, isLoading -> {
            Timber.d(mProgressDialog == null ? "mProgressDialog null" : "mProgressDialog not null");
            if (isLoading) {
                mProgressDialog.show();
            } else {
                mProgressDialog.dismiss();
            }
        });
    }

    private void successLogin() {
        mToastManager.showToast("로그인 성공");
        startActivity(MainActivity.getStartIntent(LoginActivity.this));
        finish();
    }

}
