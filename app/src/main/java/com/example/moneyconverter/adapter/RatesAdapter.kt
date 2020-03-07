package com.example.moneyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

class RatesAdapter(private val ratesList: List<String>) : RecyclerView.Adapter<RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RatesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {

        val currencyName = ratesList[position].substringBefore("=")
        val currencyValue = ratesList[position].substringAfter("=").toFloat()

        val rateBase: Pair<String, Float> = Pair(currencyName, currencyValue)
        holder.bind(rateBase)
    }

    override fun getItemCount(): Int = ratesList.size

}