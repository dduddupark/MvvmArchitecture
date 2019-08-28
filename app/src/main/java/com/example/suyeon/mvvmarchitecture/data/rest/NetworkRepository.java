package com.example.suyeon.mvvmarchitecture.data.rest;

import com.example.suyeon.mvvmarchitecture.data.PreferenceHelper;

import javax.inject.Inject;

public class NetworkRepository {

    private final NetworkService mNetworkService;

    private final PreferenceHelper mPreferencesHelper;

    @Inject
    public NetworkRepository(NetworkService networkService, PreferenceHelper preferenceHelper) {
        mNetworkService = networkService;
        mPreferencesHelper = preferenceHelper;
    }

}
