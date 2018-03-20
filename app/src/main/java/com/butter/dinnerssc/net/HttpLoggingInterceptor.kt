package com.butter.dinnerssc.net

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by zgyi on 2017-12-28.
 */
open class HttpLoggingInterceptor : HttpLoggingInterceptor.Logger {

    override fun log(message: String?) {
        Log.i("yzg", "okhttp->$message")
    }

}