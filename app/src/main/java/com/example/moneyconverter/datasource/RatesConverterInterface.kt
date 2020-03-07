package com.example.moneyconverter.datasource

import com.example.moneyconverter.model.RateObject
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
interface RatesConverterInterface {

    /**
     * Get the list of the rates from the API
     */
    @GET("/latest")
    fun getRatesConverter(): Observable<RateObject>
}