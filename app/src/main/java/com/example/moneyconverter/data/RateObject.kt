package com.example.moneyconverter.data


import com.google.gson.annotations.SerializedName
import java.util.*

data class RateObject(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("rates")
    val rates: Rates
)