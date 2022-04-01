package org.skyfaced.mvp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatActivity

abstract class BaseActivity<VB : ViewBinding> : MvpAppCompatActivity(), BaseView {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding
            ?: throw IllegalStateException("You are trying to access the binding after onDestroy()?")

    protected abstract fun setupBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setupBinding(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showToast(message: String, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    override fun showToast(message: Int, duration: Int) {
        Toast.makeText(this, message, duration).show()
    }

    override fun showSnackbar(message: String, duration: Int) {
        Snackbar.make(binding.root, message, duration).show()
    }

    override fun showSnackbar(message: Int, duration: Int) {
        Snackbar.make(binding.root, message, duration).show()
    }
}