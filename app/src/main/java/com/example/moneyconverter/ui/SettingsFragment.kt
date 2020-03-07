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
        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(this@SettingsFragment.context!!, android.R.layout.simple_spinner_item, REFRESH_TIMESTAMPS)

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        // Finally, data bind the spinner object with adapter
        refresh_rate_spinner.adapter = adapter

        // Set an on item selected listener for spinner object
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

                    // Initializing an ArrayAdapter
                    val adapter = ArrayAdapter(this@SettingsFragment.context!!, android.R.layout.simple_spinner_item, spinnerList)

                    // Set the drop down view resource
                    adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

                    // Finally, data bind the spinner object with adapter
                    refresh_currency_spinner.adapter = adapter
                },
                { logV( "Error occured while getting the rate object: $it") }))



        // Set an on item selected listener for spinner object
        refresh_currency_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
               // AppUtils.AppPreferences.refreshRate = parent.getItemAtPosition(position) as Int
                logV("Spinner selected : ${parent.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                logV("Nothing was selected")
            }
        }
    }
}