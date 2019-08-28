package com.example.suyeon.mvvmarchitecture.data.rest;

import android.content.Context;

import com.example.suyeon.mvvmarchitecture.R;
import com.example.suyeon.mvvmarchitecture.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 네트워크 연결 상태 Interceptor
 */
public class NetworkCheckInterceptor implements Interceptor {

    private Context mContext;

    public NetworkCheckInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (NetworkUtil.isConnected(mContext)) {
            return chain.proceed(original);
        } else {
            throw new NoConnectivityException(mContext.getString(R.string.text_network_error));
        }
    }

}
