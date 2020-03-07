package com.example.moneyconverter.ui.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */

class RatesAdapter : RecyclerView.Adapter<RatesViewHolder>() {

    private val items = mutableListOf<Pair<String, Float>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RatesViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<Pair<String, Float>>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}