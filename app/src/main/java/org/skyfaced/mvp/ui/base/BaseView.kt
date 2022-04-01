package org.skyfaced.mvp.ui.base

import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface BaseView : MvpView {
    @OneExecution
    fun hideKeyboard()

    @OneExecution
    fun showToast(message: String, duration: Int)

    @OneExecution
    fun showToast(@StringRes message: Int, duration: Int)

    @OneExecution
    fun showToast(message: String) {
        showToast(message, Toast.LENGTH_SHORT)
    }

    @OneExecution
    fun showToast(@StringRes message: Int) {
        showToast(message, Toast.LENGTH_SHORT)
    }

    @OneExecution
    fun showSnackbar(message: String, duration: Int)

    @OneExecution
    fun showSnackbar(@StringRes message: Int, duration: Int)

    @OneExecution
    fun showSnackbar(message: String) {
        showSnackbar(message, Snackbar.LENGTH_SHORT)
    }

    @OneExecution
    fun showSnackbar(@StringRes message: Int) {
        showSnackbar(message, Snackbar.LENGTH_SHORT)
    }

    @AddToEndSingle
    fun showOnScreenLoader() {
    }

    @AddToEndSingle
    fun hideOnScreenLoader() {
    }
}