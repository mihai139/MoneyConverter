package com.example.moneyconverter

import android.app.Application
import com.example.moneyconverter.di.viewModelModule
import com.example.moneyconverter.utils.AppPreferences
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
            modules(listOf(viewModelModule))
        }

        AppPreferences.init(this)
    }
}