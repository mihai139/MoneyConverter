package com.example.moneyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.Utils.AppUtils.INITIAL_DELAY
import com.example.moneyconverter.Utils.AppUtils.REFRESH_TIMESTAMP
import com.example.moneyconverter.Utils.AppUtils.TAG
import com.example.moneyconverter.adapter.RatesAdapter
import com.example.moneyconverter.network.RatesConverterClient
import com.example.moneyconverter.network.RatesConverterInterface
import com.example.moneyconverter.repository.RatesRepository
import com.example.moneyconverter.viewmodel.RatesViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RatesViewModel
    private lateinit var repository: RatesRepository
    private val compositeDisposable = CompositeDisposable()
    private lateinit var ratesDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratesService: RatesConverterInterface = RatesConverterClient.getClient()
        repository = RatesRepository(ratesService)
        viewModel = getViewModel()
    }

    override fun onResume() {
        super.onResume()

        ratesDisposable = Observable.interval(INITIAL_DELAY, REFRESH_TIMESTAMP,
            TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::callApi)
    }

    private fun setRatesRecyclerView(ratesList: List<String>) {
        rates_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = RatesAdapter(ratesList)
        }
    }

    private fun callApi(long: Long) {
        compositeDisposable.add(viewModel.ratesInfo
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe  (
                {
                    val ratesList = it.rates.toString().substringAfter("(").substringBefore(")").split(',').toList()
                    setRatesRecyclerView(ratesList)

                    header_text_view?.text = getString(R.string.currencies_based_on, it.base)

                    val textCurrentTimestamp = getString(R.string.current_timestamp, System.currentTimeMillis().toString())
                    timestamp_text_view?.text = textCurrentTimestamp
                },
                { Log.e(TAG, "Error occured while getting the rate object: $it") }))
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
        if (::ratesDisposable.isInitialized && !ratesDisposable.isDisposed) {
            ratesDisposable.dispose()
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
