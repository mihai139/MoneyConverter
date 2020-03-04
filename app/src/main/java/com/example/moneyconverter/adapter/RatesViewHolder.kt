package com.example.moneyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.R
import com.example.moneyconverter.data.RateBase

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

class RatesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.rate_item, parent, false)) {
    private var rateName: TextView? = null
    private var rateValue: TextView? = null


    init {
        rateName = itemView.findViewById(R.id.rate_name_text_view)
        rateValue = itemView.findViewById(R.id.rate_value_text_view)
    }

    fun bind(rate: RateBase) {
        rateName?.text = rate.name
        rateValue?.text = rate.value.toString()
    }

}