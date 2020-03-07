package com.example.moneyconverter.navigator

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.moneyconverter.MainActivity
import com.example.moneyconverter.R
import com.example.moneyconverter.settings.SettingsFragment
import com.example.moneyconverter.ui.HistoryFragment
import com.example.moneyconverter.ui.HomeFragment

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */
class UINavigator(private val activity: MainActivity) {

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun showHomeScreen() {
        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen, HomeFragment())
        }
    }

    fun showHistoryScreen() {
        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen, HistoryFragment())
        }
    }

    fun showSettingsScreen() {
        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen, SettingsFragment())
        }
    }
}