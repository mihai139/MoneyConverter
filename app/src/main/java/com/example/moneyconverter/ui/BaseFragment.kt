package com.example.moneyconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.moneyconverter.utils.AppUtils.logV
import io.reactivex.disposables.CompositeDisposable

/**
 * TODO: Comment
 *
 * @author Mihai Andrei on 3/7/20
 */
abstract class BaseFragment : Fragment() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        logV("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logV("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logV("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        logV("onDestroyView")
        super.onDestroyView()
    }

    override fun onStart() {
        logV("onStart")
        super.onStart()
    }

    override fun onResume() {
        logV("onResume")
        super.onResume()
    }

    @CallSuper
    override fun onPause() {
        logV("onPause")
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onStop() {
        logV("onStop")
        super.onStop()
    }

    override fun onDetach() {
        logV( "onDetach")
        super.onDetach()
    }
}