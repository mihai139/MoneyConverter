package com.example.moneyconverter.network

import com.example.moneyconverter.data.RateObject
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
interface RatesConverterInterface {

    /**
     * Get the list of the pots from the API
     */
    @GET("/latest")
    fun getRatesConverter(): Observable<RateObject>
}