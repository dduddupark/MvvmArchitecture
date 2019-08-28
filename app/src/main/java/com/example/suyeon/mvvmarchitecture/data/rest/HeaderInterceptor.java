package com.example.suyeon.mvvmarchitecture.data.rest;

import android.os.Build;

import com.example.suyeon.mvvmarchitecture.data.PreferenceHelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.inject.Inject;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Retrofit 통신 헤더 Interceptor
 */
public class HeaderInterceptor implements Interceptor {

    /**
     * PreferencesHelper
     */
    PreferenceHelper mPreferencesHelper;

    @Inject
    public HeaderInterceptor(PreferenceHelper preferenceHelper) {
        mPreferencesHelper = preferenceHelper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Headers headers = original.headers();
        if (headers.size() == 0) {
            Request.Builder builder = original.newBuilder();

            builder.header("Content-Type", "application/json")
                    .method(original.method(), original.body());

            Request request = builder.build();

            Timber.d("header = " + request.toString());

            return chain.proceed(request);
        }
        return chain.proceed(original);

    }
}
