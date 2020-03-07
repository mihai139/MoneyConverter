package com.example.moneyconverter

import android.app.Application
import com.example.moneyconverter.Utils.AppUtils
import com.example.moneyconverter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */

class MoneyConverterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoneyConverterApplication)
            modules(listOf(appModule))
        }
        AppUtils.AppPreferences.init(this)
    }
}