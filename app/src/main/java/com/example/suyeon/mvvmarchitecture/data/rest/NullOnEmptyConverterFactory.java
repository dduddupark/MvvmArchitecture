package com.example.suyeon.mvvmarchitecture.data.rest;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Gson 빈값일 경우 관련 Factory
 */
public class NullOnEmptyConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return (Converter<ResponseBody, Object>) body -> {
            try {
                if (body.contentLength() == 0) {
                    return null;
                }
                return delegate.convert(body);
            } catch (IOException e) {
                Timber.e(e);
                return null;
            }
        };
    }
}