package com.example.moneyconverter.repository

import androidx.lifecycle.LiveData
import com.example.moneyconverter.data.Rate
import com.example.moneyconverter.datasource.RatesDataSource
import com.example.moneyconverter.network.RatesConverterInterface
import io.reactivex.disposables.CompositeDisposable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesRepository(private val ratesService: RatesConverterInterface) {

    private lateinit var ratesDataSource: RatesDataSource

    fun fetchRatesDetails(compositeDisposable: CompositeDisposable) : LiveData<Rate> {
        ratesDataSource = RatesDataSource(ratesService, compositeDisposable)
        ratesDataSource.fetchData()
        return ratesDataSource.ratesResponse
    }
}
