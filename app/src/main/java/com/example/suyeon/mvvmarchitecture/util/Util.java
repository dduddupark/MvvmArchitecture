package com.example.suyeon.mvvmarchitecture.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {

    /**
     * Gson을 가져온다.
     *
     * @return Gson
     */
    public static final Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(
                        new EmptyCheckTypeAdapterFactory())
                .setLenient()
                .create();
    }
}
