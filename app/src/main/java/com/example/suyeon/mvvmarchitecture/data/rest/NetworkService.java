package com.example.suyeon.mvvmarchitecture.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("...")
    Observable<Void> getTodo();
}
