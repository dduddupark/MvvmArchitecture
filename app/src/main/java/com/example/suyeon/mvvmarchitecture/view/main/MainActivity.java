package com.example.suyeon.mvvmarchitecture.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.base.BaseActivity;
import com.example.suyeon.mvvmarchitecture.base.BaseFragment;
import com.example.suyeon.mvvmarchitecture.databinding.ActivityMainBinding;
import com.example.suyeon.mvvmarchitecture.view.main.sub.SubFragment;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    /**
     * 뷰타입 - ONE
     */
    private static final int VIEW_TYPE_ONE = 0;
    /**
     * 뷰타입 - TWO
     */
    private static final int VIEW_TYPE_TWO = 1;

    /**
     * 데이터 바인딩
     */
    private ActivityMainBinding mBinding;

    /**
     * MainActivity 가져오기
     *
     * @param context Context
     * @return MainActivity Intent
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = (ActivityMainBinding) getViewDataBinding();
        mBinding.setLifecycleOwner(this);

        initView();
    }

    /**
     * 뷰 세팅
     */
    private void initView() {

        mBinding.vpContent.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mBinding.vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((PagerAdapter) mBinding.vpContent.getAdapter()).getItem(position).onSelectedFragment(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setOnClick(View view) {

        int viewType = VIEW_TYPE_ONE;

        switch (view.getId()) {
            case R.id.btn_0:
                viewType = VIEW_TYPE_ONE;
                break;
            case R.id.btn_1:
                viewType = VIEW_TYPE_TWO;
                break;
        }

        setCurrentItem(viewType);
    }

    /**
     * 뷰페이저 보여질 화면
     *
     * @param position 보여질 화면 위치
     */
    private void setCurrentItem(int position) {
        try {
            mBinding.vpContent.setCurrentItem(position, false);
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    /**
     * 메인 PagerAdapter
     */
    private class PagerAdapter extends FragmentStatePagerAdapter {

        private int fragmentCount = 2;
        private BaseFragment[] mFragments = new BaseFragment[fragmentCount];

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {

            if (mFragments[position] == null) {

                mFragments[position] = SubFragment.newInstance(String.valueOf(position));
            }

            return mFragments[position];
        }

        @Override
        public int getCount() {
            return fragmentCount;
        }
    }
}
