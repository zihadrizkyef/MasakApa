package com.zref.core.extension

import com.zref.core.BuildConfig

fun Throwable.debugPrintStackTrace() {
    if (BuildConfig.DEBUG) {
        this.printStackTrace()
    }
}