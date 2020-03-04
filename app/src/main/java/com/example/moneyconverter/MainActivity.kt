package com.example.moneyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.moneyconverter.network.RatesConverterClient
import com.example.moneyconverter.network.RatesConverterInterface
import com.example.moneyconverter.repository.RatesRepository
import com.example.moneyconverter.viewmodel.RatesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RatesViewModel
    private lateinit var repository: RatesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratesService: RatesConverterInterface = RatesConverterClient.getClient()
        repository = RatesRepository(ratesService)

        viewModel = getViewModel()
        viewModel.ratesInfo.observe(this, Observer{

        })
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
