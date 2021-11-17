package com.example.android.egynews.utils

import android.util.Log

fun Any.debug(any: Any) {
    Log.d(this::class.java.simpleName, any.toString())
}