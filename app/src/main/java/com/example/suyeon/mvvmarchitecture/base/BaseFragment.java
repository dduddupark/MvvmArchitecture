package com.example.suyeon.mvvmarchitecture.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.suyeon.mvvmarchitecture.di.util.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    /**
     * AppCompatActivity
     */
    private AppCompatActivity activity;
    /**
     * DataBinding
     */
    private ViewDataBinding mBinding;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    /**
     * 선택 된 Fragment
     *
     * @param isOnStart onStart life cycle 에서 호출 여부
     */
    public abstract void onSelectedFragment(boolean isOnStart);

    /**
     * Activity Return
     * @return
     */
    public AppCompatActivity getBaseActivity() {
        return activity;
    }

    /**
     * DataBinding Return
     * @return
     */
    public ViewDataBinding getViewDataBinding() {
        return mBinding;
    }
}