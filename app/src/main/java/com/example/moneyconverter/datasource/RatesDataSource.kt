package com.example.moneyconverter.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneyconverter.Utils.AppUtils.TAG
import com.example.moneyconverter.data.RateObject
import com.example.moneyconverter.network.RatesConverterInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/4/20
 */
class RatesDataSource(private val apiService: RatesConverterInterface, private val compositeDisposable: CompositeDisposable) {

    private val _ratesResponse = MutableLiveData<RateObject>()

    val ratesResponse : LiveData<RateObject>
        get() = _ratesResponse

    fun fetchData() {
        compositeDisposable.add(
            apiService.getRatesConverter()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Log.d("abab", "Successfully getting rates $it")
                        _ratesResponse.postValue(it)
                    },
                    { Log.e(TAG, "Error getting rates: ", it) }
                )
        )
    }
}
