package com.example.moneyconverter.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
data class RateBase(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("value")
    @Expose
    val value: Double
)