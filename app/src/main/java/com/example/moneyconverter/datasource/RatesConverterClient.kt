package com.example.moneyconverter.datasource

import com.example.moneyconverter.utils.AppUtils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        // used to get logs when performing network request
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // create retrofit instance
       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(client)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(RatesConverterInterface::class.java)

    }
}