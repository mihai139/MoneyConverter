package com.example.moneyconverter.navigator

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.moneyconverter.R
import com.example.moneyconverter.ui.SettingsFragment
import com.example.moneyconverter.ui.HistoryFragment
import com.example.moneyconverter.ui.HomeFragment

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */
class UINavigator(private val activity: FragmentActivity) {

    fun showHomeScreen(pair: Pair<String, Float>? = null) {

        val homeFragment = HomeFragment()
        if (pair != null) {
            val args = Bundle()
            args.putSerializable(BUNDLE_PAIR_OBJECT, pair)
            homeFragment.arguments = args
        }

        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen, homeFragment)
        }
    }

    fun showHistoryScreen() {
        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen, HistoryFragment())
        }
    }

    fun showSettingsScreen() {
        activity.supportFragmentManager.inTransaction {
            replace(R.id.ui_screen,
                SettingsFragment()
            )
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    companion object {
        const val BUNDLE_PAIR_OBJECT = "Bundle_pair_object"
    }
}