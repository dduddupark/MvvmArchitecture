package com.example.suyeon.mvvmarchitecture.util;

import android.content.Context;
import android.os.Environment;

import timber.log.Timber;

public class FileLoggingTree extends Timber.DebugTree {

    private static final String TAG = FileLoggingTree.class.getSimpleName();

    private static final String DIRECTORY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/YapWorkLogs";

    private Context mContext;

    public FileLoggingTree(Context context) {
        mContext = context;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        super.log(priority, tag, message, t);
    }


}
