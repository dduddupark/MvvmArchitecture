package com.example.suyeon.mvvmarchitecture.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.suyeon.mvvmarchitecture.R;

public abstract class BaseDialog extends Dialog {

    /**
     * DataBinding
     */
    private ViewDataBinding mBinding;

    public BaseDialog(Context context) {
        super(context);

        Window window = getWindow();
        if (window != null) {   //투명하게
            window.setBackgroundDrawableResource(R.color.transparent_000000_00);
        }
    }

    /**
     * xml return
     * @return
     */
    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutRes(), null, false);
        setContentView(mBinding.getRoot());
    }

    /**
     * DataBinding Return
     * @return
     */
    public ViewDataBinding getViewDataBinding() {
        return mBinding;
    }
}
