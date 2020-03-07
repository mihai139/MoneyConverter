package com.example.moneyconverter.datasource

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesDataSource(private val apiService: RatesConverterInterface) {

    fun fetchData() = apiService.getRatesConverter()
}
