package com.example.moneyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.data.RateBase

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

class RatesAdapter(val list: List<RateBase>) : RecyclerView.Adapter<RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RatesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val rateBase: RateBase = list[position]
        holder.bind(rateBase)
    }

    override fun getItemCount(): Int = list.size

}