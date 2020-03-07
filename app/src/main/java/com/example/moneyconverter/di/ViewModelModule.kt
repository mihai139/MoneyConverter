package com.example.moneyconverter.di

import com.example.moneyconverter.repository.RatesRepository
import com.example.moneyconverter.viewmodel.RatesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */

val appModule : Module = module {
    single { RatesRepository() }

    viewModel {
        RatesViewModel(get())
    }
}