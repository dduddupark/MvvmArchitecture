package com.example.suyeon.mvvmarchitecture.util;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.example.suyeon.mvvmarchitecture.data.Const;

import java.util.List;

import timber.log.Timber;

/**
 * 문자열 유틸
 */
public class StringUtil {

    /**
     * 문자열 빈값 확인
     *
     * @param s 확인할 문자
     * @return 빈값 확인 결과
     */
    public static boolean isEmpty(String s) {
        boolean isEmpty = false;

        if (TextUtils.isEmpty(s) || s.trim().length() == 0 || "null".equalsIgnoreCase(s)) {
            isEmpty = true;
        }

        return isEmpty;
    }
}
