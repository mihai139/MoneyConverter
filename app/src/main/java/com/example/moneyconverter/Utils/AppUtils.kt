package com.example.moneyconverter.Utils

import com.example.moneyconverter.data.RateBase

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

object AppUtils {

    const val BASE_URL: String = "https://api.exchangeratesapi.io"
    const val INITIAL_DELAY = 100L
    const val REFRESH_TIMESTAMP = 3000L

    val tempRatesList = mutableListOf<RateBase>(
        RateBase("CAD", 1.4857),
        RateBase("HKD", 8.6471),
        RateBase("ISK", 142.9),
        RateBase("PHP", 56.467),
        RateBase("DKK", 7.473),
        RateBase("RON", 4.8087),
        RateBase("USD", 1.1117),
        RateBase("BGN", 1.9558))
}