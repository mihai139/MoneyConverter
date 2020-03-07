package com.example.moneyconverter.Utils

import android.content.Context
import android.content.SharedPreferences
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

    object AppPreferences {
        private const val NAME = "SpinKotlin"
        private const val MODE = Context.MODE_PRIVATE
        private val REFRESH_RATE = Pair("refresh_rate", REFRESH_TIMESTAMP_IN_SECONDS)

        private lateinit var preferences: SharedPreferences

        // list of app specific preferences
        fun init(context: Context) {
            preferences = context.getSharedPreferences(NAME, MODE)
        }

        /**
         * SharedPreferences extension function, so we won't need to call edit() and apply()
         * ourselves on every SharedPreferences operation.
         */
        private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
            val editor = edit()
            operation(editor)
            editor.apply()
        }

        var refreshRate: Int
            // custom getter to get a preference of a desired type, with a predefined default value
            get() = preferences.getInt(REFRESH_RATE.first, REFRESH_RATE.second)

            // custom setter to save a preference back to preferences file
            set(value) = preferences.edit {
                it.putInt(REFRESH_RATE.first, value)
            }
    }
}