package com.example.suyeon.mvvmarchitecture.data.rest;

import java.io.IOException;

/**
 * NoConnectivityException
 */
public class NoConnectivityException extends IOException {

    public NoConnectivityException(String message) {
        super(message);
    }
}
