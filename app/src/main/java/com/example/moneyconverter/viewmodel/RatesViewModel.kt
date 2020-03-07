package com.example.moneyconverter.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moneyconverter.model.RateObject
import com.example.moneyconverter.datasource.RatesRepository
import io.reactivex.Observable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesViewModel(private val ratesRepository: RatesRepository) : ViewModel() {

    val ratesInfo: Observable<RateObject> by lazy {
        ratesRepository.fetchRatesDetails()
    }
}