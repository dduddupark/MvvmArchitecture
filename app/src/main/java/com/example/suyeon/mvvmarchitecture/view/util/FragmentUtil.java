package com.example.suyeon.mvvmarchitecture.view.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Fragment 관리 유틸
 */
public class FragmentUtil {

    /**
     * Fragment 추가
     *
     * @param contentFrame Fragment를 추가할 영역
     * @param addFragment  추가 할 Fragment
     * @param manager      Fragment 관리자
     */
    public static void addFragment(int contentFrame, Fragment addFragment, FragmentManager manager) {
        Fragment fragment = manager.findFragmentById(contentFrame);

        if (fragment == null) {
            manager.beginTransaction()
                    .add(contentFrame, addFragment)
                    .commit();

        } else {
            manager.beginTransaction()
                    .replace(contentFrame, addFragment)
                    .commit();
        }
    }

    /**
     * Fragment 추가
     *
     * @param contentFrame Fragment를 추가할 영역
     * @param addFragment  추가 할 Fragment
     * @param manager      Fragment 관리자
     */
    public static void addBackStackFragment(int contentFrame, Fragment addFragment, FragmentManager manager) {
        Fragment fragment = manager.findFragmentById(contentFrame);
        if (fragment == null) {
            manager.beginTransaction()
                    .add(contentFrame, addFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            manager.beginTransaction()
                    .replace(contentFrame, addFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * Fragment 가져오기
     *
     * @param contentFrame Fragment를 확인 할 영역
     * @param manager      Fragment 관리자
     * @return Fragment
     */
    public static Fragment getFragment(int contentFrame, FragmentManager manager) {
        return manager.findFragmentById(contentFrame);
    }
}
