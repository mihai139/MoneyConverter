package com.example.moneyconverter

import android.app.Application
import com.example.moneyconverter.Utils.AppUtils

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */

class MoneyConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppUtils.AppPreferences.init(this)
    }
}