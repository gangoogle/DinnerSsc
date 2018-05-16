package com.butter.yapple

import android.app.Application
import cn.jpush.android.api.JPushInterface

/**
 * Created by zgyi on 2018-01-09.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(false)
        JPushInterface.init(this)
    }
}