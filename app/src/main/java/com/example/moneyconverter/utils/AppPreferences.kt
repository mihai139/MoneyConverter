package com.example.moneyconverter.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */


object AppPreferences {
    private const val NAME = "RatesConverter"
    private const val MODE = Context.MODE_PRIVATE
    private val REFRESH_RATE = Pair("refresh_rate", AppUtils.REFRESH_TIMESTAMP_IN_SECONDS)

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