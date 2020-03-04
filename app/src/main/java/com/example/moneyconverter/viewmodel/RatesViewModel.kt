package com.example.moneyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyconverter.data.Rate
import com.example.moneyconverter.repository.RatesRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesViewModel(private val ratesRepository: RatesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val ratesInfo: LiveData<Rate> by lazy {
        ratesRepository.fetchRatesDetails(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}