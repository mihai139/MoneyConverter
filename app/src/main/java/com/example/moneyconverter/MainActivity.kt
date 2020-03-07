package com.example.moneyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.moneyconverter.utils.AppUtils.logV

class MainActivity : AppCompatActivity() {

    private lateinit var uiNavigator: UINavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uiNavigator = UINavigator(this)
        uiNavigator.showHomeScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rates_converter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                uiNavigator.showHistoryScreen()
                true
            }
            R.id.settings -> {
                uiNavigator.showSettingsScreen()
                true
            }
            else -> {
                logV( "No action to be taken")
                super.onOptionsItemSelected(item)
            }
        }
    }
}
