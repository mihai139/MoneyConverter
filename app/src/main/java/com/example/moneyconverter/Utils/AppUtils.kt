package com.example.moneyconverter.Utils

import android.util.Log

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

object AppUtils {

    const val BASE_URL: String = "https://api.exchangeratesapi.io"
    const val INITIAL_DELAY = 100L
    const val REFRESH_TIMESTAMP = 3000L
    const val TAG = "MoneyConverter"

    fun Any.logV(message: String, tag: String? = null) = Log.v(buildTag(this::class.java.name, tag), message)

    private fun buildTag(className: String?, tag: String?): String = when {
        !className.isNullOrEmpty() -> className
        else -> tag ?: "com.example.money.converter"
    }
}