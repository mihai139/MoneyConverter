package com.example.moneyconverter.data


import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates
)