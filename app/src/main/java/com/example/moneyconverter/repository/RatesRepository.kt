package com.example.moneyconverter.repository

import com.example.moneyconverter.data.RateObject
import com.example.moneyconverter.datasource.RatesDataSource
import com.example.moneyconverter.network.RatesConverterClient
import com.example.moneyconverter.network.RatesConverterInterface
import io.reactivex.Observable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesRepository {

    private lateinit var ratesDataSource: RatesDataSource

    private val ratesService: RatesConverterInterface = RatesConverterClient.getClient()

    fun fetchRatesDetails() : Observable<RateObject> {
        ratesDataSource = RatesDataSource(ratesService)
        return ratesDataSource.fetchData()
    }
}
