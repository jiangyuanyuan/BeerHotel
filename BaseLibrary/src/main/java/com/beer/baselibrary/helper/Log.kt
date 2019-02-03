package com.beer.baselibrary.helper

import android.util.Log
import com.beer.baselibrary.BuildConfig



fun <T : Any> T.logd(message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(this.javaClass.simpleName, message)
    }
}

fun <T : Any> T.loge(message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(this.javaClass.simpleName, message)
    }
}

fun <T : Any> T.logi(message: String) {
    if (BuildConfig.DEBUG) {
        Log.i(this.javaClass.simpleName, message)
    }
}

fun <T : Any> T.logv(message: String) {
    if (BuildConfig.DEBUG) {
        Log.v(this.javaClass.simpleName, message)
    }
}

fun <T : Any> T.logw(message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(this.javaClass.simpleName, message)
    }
}

fun <T : Any> T.logwtf(message: String) {
    if (BuildConfig.DEBUG) {
        Log.wtf(this.javaClass.simpleName, message)
    }
}