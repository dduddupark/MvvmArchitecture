package com.example.suyeon.mvvmarchitecture.view.main.sub;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.base.BaseFragment;
import com.example.suyeon.mvvmarchitecture.data.Const;
import com.example.suyeon.mvvmarchitecture.databinding.FragmentSubBinding;
import com.example.suyeon.mvvmarchitecture.di.util.ViewModelFactory;
import com.example.suyeon.mvvmarchitecture.view.dialog.DialogFactory;
import com.example.suyeon.mvvmarchitecture.view.dialog.ProgressDialog;

import javax.inject.Inject;

public class SubFragment extends BaseFragment {

    /**
     * ProgressDialog
     */
    @Inject
    ProgressDialog mProgressDialog;
    /**
     * ViewModelFactory
     */
    @Inject
    ViewModelFactory mFactory;
    /**
     * BaseDialog
     */
    private Dialog mBaseDialog;
    /**
     * DataBinding
     */
    private FragmentSubBinding mBinding;
    /**
     * SubViewModel
     */
    private SubViewModel mViewModel;

    /**
     * SubFragment 생성자
     *
     * @return BaseFragment
     */
    public static BaseFragment newInstance(String pageNum) {
        BaseFragment fragment = new SubFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Const.FRAGMENT_PAGE_NUM, pageNum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_sub;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBinding = (FragmentSubBinding) getViewDataBinding();
        mBinding.setLifecycleOwner(this);
        initView();


        mViewModel = ViewModelProviders.of(this, mFactory).get(SubViewModel.class);
        mViewModel.connect();

        observableViewModel();
    }

    /**
     * 뷰 세팅
     */
    private void initView() {

        String pageNum = getArguments().getString(Const.FRAGMENT_PAGE_NUM);

        mBinding.tvPageNum.setText(pageNum);
    }

    /**
     * 데이터 구독
     */
    private void observableViewModel() {

        mViewModel.getError().observe(this, errorMsg -> {
            mBaseDialog = DialogFactory.createDialog(getActivity()
                    , getString(R.string.text_error)
                    , errorMsg
                    , getString(R.string.action_close)
                    , null);
            mBaseDialog.show();
        });

        mViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading) {
                mProgressDialog.show();
            } else {
                mProgressDialog.dismiss();
            }
        });
    }


    /**
     * 프래그먼트 선택
     *
     * @param isOnStart onStart life cycle 에서 호출 여부
     */
    @Override
    public void onSelectedFragment(boolean isOnStart) {

    }
}
