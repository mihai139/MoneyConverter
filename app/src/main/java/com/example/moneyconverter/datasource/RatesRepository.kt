package com.example.moneyconverter.datasource

import com.example.moneyconverter.model.RateObject
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
