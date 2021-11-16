package com.example.android.egynews.utils

import android.util.Log

class Logger{
    companion object {
        fun debug(any: Any?) {
            Log.d(this::class.java.simpleName, any.toString())
        }

        fun debugError(any: Any?,throwable: Throwable) {
            Log.e(this::class.java.simpleName, any.toString(),throwable)
        }
    }
}