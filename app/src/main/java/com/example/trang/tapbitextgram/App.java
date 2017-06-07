package com.example.trang.tapbitextgram;

import android.app.Application;
import android.content.Context;

/**
 * Created by Trang on 6/3/2017.
 */

public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        this.mContext = this;
        super.onCreate();
    }

    public static Context getmContext() {
        return mContext;
    }
}
