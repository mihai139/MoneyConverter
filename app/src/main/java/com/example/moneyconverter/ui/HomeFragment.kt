package com.example.moneyconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyconverter.R
import com.example.moneyconverter.utils.AppUtils.INITIAL_DELAY
import com.example.moneyconverter.utils.AppUtils.logV
import com.example.moneyconverter.ui.rates.RatesAdapter
import com.example.moneyconverter.UINavigator.Companion.BUNDLE_PAIR_OBJECT
import com.example.moneyconverter.utils.AppPreferences
import com.example.moneyconverter.viewmodel.RatesViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_layout.*
import org.koin.android.viewmodel.ext.android.getViewModel
import java.util.concurrent.TimeUnit

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */

class HomeFragment : BaseFragment() {
    private lateinit var ratesDisposable: Disposable

    private val ratesAdapter by lazy { RatesAdapter() }
    private val pair: Pair<String, Float>? by lazy { arguments?.getSerializable(BUNDLE_PAIR_OBJECT) as Pair<String, Float>? }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rates_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = ratesAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        ratesDisposable = Observable.interval(
            INITIAL_DELAY, (AppPreferences.refreshRate * 1000L),
            TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::callApi)
    }

    private fun callApi(long: Long) {
        compositeDisposable.add(getViewModel<RatesViewModel>().ratesInfo
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe  (
                {
                    val ratesList = it.rates.toString().substringAfter("(").substringBefore(")").split(',').toList()
                    val pairList = mutableListOf<Pair<String, Float>>()

                    ratesList.forEach {  s ->
                        val currencyValue = String.format("%.4f", if (pair == null) s.substringAfter("=").toFloat()
                        else s.substringAfter("=").toFloat() / pair!!.second)
                        pairList.add(Pair(s.substringBefore("="), currencyValue.toFloat()))
                    }

                    ratesAdapter.setItems(pairList)

                    val baseCurrency = if (pair == null) it.base else pair?.first
                    header_text_view?.text = getString(R.string.currencies_based_on, baseCurrency)

                    val textCurrentTimestamp = getString(R.string.current_timestamp, System.currentTimeMillis().toString())
                    timestamp_text_view?.text = textCurrentTimestamp
                },
                { logV( "Error occured while getting the rate object: $it") }))
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
        if (::ratesDisposable.isInitialized && !ratesDisposable.isDisposed) {
            ratesDisposable.dispose()
        }
    }
}