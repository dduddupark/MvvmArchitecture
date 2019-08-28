package com.example.suyeon.mvvmarchitecture.data;


import android.content.Context;

import com.example.suyeon.mvvmarchitecture.data.model.LoginData;
import com.example.suyeon.mvvmarchitecture.util.SharedPreferencesCache;
import com.example.suyeon.mvvmarchitecture.util.StringUtil;
import com.example.suyeon.mvvmarchitecture.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;

public class PreferenceHelper {
    /**
     * SharedPreferences 이름
     */
    public static final String PREF_FILE_NAME = "order_help_app_pref_file";
    /**
     * 매장 코드
     */
    public static final String PREF_STR_CODE = "pref_str_code";
    /**
     * 자동 로그인 유무
     */
    public static final String PREF_KEY_AUTO_LOGIN = "pref_key_auto_login";

    /**
     * SharedPreferencesCache
     */
    private final SharedPreferencesCache mPref;

    private final Gson mGson;

    @Inject
    public PreferenceHelper(Context context) {
        mPref = new SharedPreferencesCache(context, PREF_FILE_NAME);
        mGson = Util.getGson();
    }

    public void putLoginData(LoginData loginData) {
        if (loginData != null) {
            String loginDataJson = mGson.toJson(loginData);
            mPref.put(PREF_STR_CODE, loginDataJson);
        } else {
            mPref.remove(PREF_STR_CODE);
        }
    }

    /**
     * 자동 로그인 여부 저장
     *
     * @param isAuto
     */
    public void putAutoLogin(boolean isAuto) {
        mPref.put(PREF_KEY_AUTO_LOGIN, isAuto);
    }

    /**
     * 자동 로그인 여부 가져오기
     *
     * @return 자동 로그인 여부
     */
    public boolean isAutoLogin() {
        return mPref.getBoolean(PREF_KEY_AUTO_LOGIN);
    }

    /**
     * 로그인 데이터 저장
     *
     * @return loginData LoginData
     */
    public LoginData getLoginData() {

        String loginDataJson = mPref.getString(PREF_STR_CODE);
        LoginData loginData = null;
        if (!StringUtil.isEmpty(loginDataJson)) {
            loginData = new Gson().fromJson(loginDataJson, new TypeToken<LoginData>() {
            }.getType());
        }

        return loginData;
    }

}
