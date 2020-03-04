package com.example.moneyconverter.network

import com.example.moneyconverter.Utils.AppUtils.BASE_URL
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

object RatesConverterClient {

    fun getClient() : RatesConverterInterface {

       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(RatesConverterInterface::class.java)

    }
}