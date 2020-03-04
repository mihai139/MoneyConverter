package com.example.moneyconverter.data


import com.google.gson.annotations.SerializedName

data class RateObject(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rate")
    val rate: MutableList<RateBase>?
)