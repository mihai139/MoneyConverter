package com.example.moneyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.Utils.AppUtils.tempRatesList
import com.example.moneyconverter.adapter.RatesAdapter
import com.example.moneyconverter.data.RateBase
import com.example.moneyconverter.network.RatesConverterClient
import com.example.moneyconverter.network.RatesConverterInterface
import com.example.moneyconverter.repository.RatesRepository
import com.example.moneyconverter.viewmodel.RatesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RatesViewModel
    private lateinit var repository: RatesRepository

    private lateinit var ratesList: MutableList<RateBase>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratesService: RatesConverterInterface = RatesConverterClient.getClient()
        repository = RatesRepository(ratesService)

        viewModel = getViewModel()
        viewModel.ratesInfo.observe(this, Observer{rateObject ->
            ratesList = rateObject.rate ?: tempRatesList
            setRatesRecyclerView()
        })

    }

    private fun setRatesRecyclerView() {
        rates_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = RatesAdapter(ratesList)
        }
    }

    private fun getViewModel(): RatesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
                @Suppress("UNCHECKED_CAST")
                return RatesViewModel(repository) as T
            }

        })[RatesViewModel::class.java]
    }
}
