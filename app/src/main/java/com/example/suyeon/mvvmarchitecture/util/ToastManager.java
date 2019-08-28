package com.example.suyeon.mvvmarchitecture.util;

import android.content.Context;
import android.widget.Toast;

import com.example.suyeon.mvvmarchitecture.di.scope.ActivityContext;

import timber.log.Timber;

/**
 * 토스트 매니저
 */
public class ToastManager {
    /**
     * Context
     */
    private Context mContext = null;
    /**
     * Toast
     */
    private Toast mToast = null;

    /**
     * 토스트 매니저
     *
     * @param context Context
     */
    public ToastManager(@ActivityContext Context context) {
        mContext = context;
    }

    /**
     * 토스트 보여주기
     *
     * @param toastText 보여 줄 문자열
     */
    public void showToast(String toastText) {
        try {
            mToast = Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT);
            mToast.show();
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    /**
     * 토스트 숨기기
     */
    public void hideToast() {
        try {
            if (mToast != null) {
                mToast.cancel();
            }
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}
