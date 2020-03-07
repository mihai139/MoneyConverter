package com.example.moneyconverter.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moneyconverter.R
import com.example.moneyconverter.navigator.UINavigator

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
}