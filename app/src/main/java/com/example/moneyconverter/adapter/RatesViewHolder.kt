package com.example.moneyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.R

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

    fun bind(ratesList: Pair<String, Float>) {
        rateName?.text = ratesList.first
        rateValue?.text = ratesList.second.toString()
    }
}