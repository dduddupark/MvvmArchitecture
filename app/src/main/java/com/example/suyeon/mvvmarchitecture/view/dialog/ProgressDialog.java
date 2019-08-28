package com.example.suyeon.mvvmarchitecture.view.dialog;

import android.content.Context;
import android.os.Bundle;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.base.BaseDialog;
import com.example.suyeon.mvvmarchitecture.databinding.DialogProgressBinding;
import com.example.suyeon.mvvmarchitecture.di.scope.ActivityContext;

import javax.inject.Inject;

public class ProgressDialog extends BaseDialog {

    DialogProgressBinding mBinding;

    @Inject
    public ProgressDialog(@ActivityContext Context context) {
        super(context);
    }

    @Override
    protected int layoutRes() {
        return R.layout.dialog_progress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = (DialogProgressBinding) getViewDataBinding();
    }

}
