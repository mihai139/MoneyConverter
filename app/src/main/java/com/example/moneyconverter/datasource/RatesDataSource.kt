package com.example.moneyconverter.datasource

import com.example.moneyconverter.network.RatesConverterInterface


/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesDataSource(private val apiService: RatesConverterInterface) {

    fun fetchData() = apiService.getRatesConverter()
}
