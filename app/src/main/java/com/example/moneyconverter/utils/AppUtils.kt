package com.example.moneyconverter.utils

import android.util.Log

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

object AppUtils {

    const val BASE_URL: String = "https://api.exchangeratesapi.io"
    const val INITIAL_DELAY = 100L
    const val REFRESH_TIMESTAMP_IN_SECONDS = 3
    val REFRESH_TIMESTAMPS = arrayOf(3, 5, 15)

    fun Any.logV(message: String, tag: String? = null) = Log.v(buildTag(this::class.java.name, tag), message)

    private fun buildTag(className: String?, tag: String?): String = when {
        !className.isNullOrEmpty() -> className
        else -> tag ?: "com.example.money.converter"
    }
}