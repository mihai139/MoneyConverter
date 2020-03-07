package com.example.moneyconverter.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moneyconverter.R
import com.example.moneyconverter.ui.BaseFragment

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */
class SettingsFragment() : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
}