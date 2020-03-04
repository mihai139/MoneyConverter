package com.example.moneyconverter.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneyconverter.Utils.AppUtils.TAG
import com.example.moneyconverter.data.Rate
import com.example.moneyconverter.network.RatesConverterInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesDataSource(private val apiService: RatesConverterInterface, private val compositeDisposable: CompositeDisposable) {

    private val _ratesResponse = MutableLiveData<Rate>()

    val ratesResponse : LiveData<Rate>
        get() = _ratesResponse

    fun fetchData() {
        compositeDisposable.add(
            apiService.getRatesConverter()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Log.d(TAG, "Successfully getting rates")
                        _ratesResponse.postValue(it)
                    },
                    { Log.e(TAG, "Error getting rates: ", it) }
                )
        )
    }
}
