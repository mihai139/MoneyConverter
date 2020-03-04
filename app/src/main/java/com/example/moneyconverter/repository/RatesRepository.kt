package com.example.moneyconverter.repository

import com.example.moneyconverter.data.RateObject
import com.example.moneyconverter.datasource.RatesDataSource
import com.example.moneyconverter.network.RatesConverterInterface
import io.reactivex.Observable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesRepository(private val ratesService: RatesConverterInterface) {

    private lateinit var ratesDataSource: RatesDataSource

    fun fetchRatesDetails() : Observable<RateObject> {
        ratesDataSource = RatesDataSource(ratesService)
        return ratesDataSource.fetchData()
    }
}
