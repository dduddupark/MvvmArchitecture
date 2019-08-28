package com.example.suyeon.mvvmarchitecture.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    /**
     * DataBinding
     */
    private ViewDataBinding mBinding;

    /**
     * xml return
     * @return
     */
    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, layoutRes());
    }

    /**
     * DataBinding Return
     * @return
     */
    public ViewDataBinding getViewDataBinding() {
        return mBinding;
    }

}