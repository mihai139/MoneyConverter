package com.example.moneyconverter.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.moneyconverter.R
import com.example.moneyconverter.Utils.AppUtils
import com.example.moneyconverter.Utils.AppUtils.REFRESH_TIMESTAMPS
import com.example.moneyconverter.Utils.AppUtils.logV
import com.example.moneyconverter.navigator.UINavigator
import com.example.moneyconverter.viewmodel.RatesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.viewmodel.ext.android.getViewModel

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */
class SettingsFragment : BaseFragment() {

    private lateinit var uiNavigator: UINavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiNavigator = UINavigator(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRefreshRateSpinner()
        configureCurrencySpinner()
        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_BACK) {
                uiNavigator.showHomeScreen()
                true
            } else {
                false
            }
        }
    }

    private fun configureRefreshRateSpinner() {
        val adapter = ArrayAdapter(this@SettingsFragment.context!!, android.R.layout.simple_spinner_item, REFRESH_TIMESTAMPS)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        refresh_rate_spinner.adapter = adapter
        refresh_rate_spinner.setSelection(0, false)

        refresh_rate_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                AppUtils.AppPreferences.refreshRate = parent.getItemAtPosition(position) as Int
                logV("Spinner selected : ${parent.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                logV("Nothing was selected")
            }
        }
    }

    private fun configureCurrencySpinner() {

        compositeDisposable.add(getViewModel<RatesViewModel>().ratesInfo
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe  (
                {
                    val ratesList = it.rates.toString().substringAfter("(").substringBefore(")").split(',').toList()
                    val spinnerList: MutableList<String> = mutableListOf()

                    ratesList.forEachIndexed { index, s ->
                        spinnerList.add(index, s.substringBefore("="))
                    }

                    val adapter = ArrayAdapter(this@SettingsFragment.context!!, android.R.layout.simple_spinner_item, spinnerList)
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

                    refresh_currency_spinner.adapter = adapter
                    refresh_currency_spinner.setSelection(0, false)

                    refresh_currency_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                            logV("Spinner selected : ${parent.getItemAtPosition(position)}")

                            ratesList.forEachIndexed { index, s ->
                                if (parent.getItemAtPosition(position).toString() == s.substringBefore("=")) {
                                    logV("onItemSelected")
                                    val pair = Pair(s.substringBefore("="), s.substringAfter("=").toFloat())
                                    uiNavigator.showHomeScreen(pair)
                                }
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>){
                            logV("Nothing was selected")
                        }
                    }
                },
                { logV( "Error occured while getting the rate object: $it") }))
    }
}