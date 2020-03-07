package com.example.moneyconverter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.R
import com.example.moneyconverter.Utils.AppUtils
import com.example.moneyconverter.adapter.RatesAdapter
import com.example.moneyconverter.network.RatesConverterClient
import com.example.moneyconverter.network.RatesConverterInterface
import com.example.moneyconverter.repository.RatesRepository
import com.example.moneyconverter.viewmodel.RatesViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_layout.*
import java.util.concurrent.TimeUnit

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */

class HomeFragment : BaseFragment() {
    private lateinit var repository: RatesRepository
    private lateinit var viewModel: RatesViewModel
    private lateinit var ratesDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ratesService: RatesConverterInterface = RatesConverterClient.getClient()
        repository = RatesRepository(ratesService)
        viewModel = getViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onResume() {
        super.onResume()

        ratesDisposable = Observable.interval(
            AppUtils.INITIAL_DELAY, AppUtils.REFRESH_TIMESTAMP,
            TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::callApi)
    }

    private fun setRatesRecyclerView(ratesList: List<String>) {
        rates_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
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
                { Log.e(AppUtils.TAG, "Error occured while getting the rate object: $it") }))
    }


    private fun getViewModel(): RatesViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
                @Suppress("UNCHECKED_CAST")
                return RatesViewModel(repository) as T
            }

        })[RatesViewModel::class.java]
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
        if (::ratesDisposable.isInitialized && !ratesDisposable.isDisposed) {
            ratesDisposable.dispose()
        }
    }
}