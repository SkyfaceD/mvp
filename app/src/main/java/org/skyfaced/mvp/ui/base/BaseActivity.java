package org.skyfaced.mvp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.snackbar.Snackbar;

import moxy.MvpAppCompatActivity;

public abstract class BaseActivity<VB extends ViewBinding> extends MvpAppCompatActivity implements BaseView {
    private VB binding = null;

    @NonNull
    protected VB getBinding() {
        if (binding == null)
            throw new IllegalArgumentException("You are trying to access the binding after onDestroy()?");
        return binding;
    }

    @NonNull
    protected abstract VB setupBinding(LayoutInflater inflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = setupBinding(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    /************
     * ViewImpl *
     ************/
    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void showToast(@NonNull String message, Integer duration) {
        Toast.makeText(this, message, duration).show();
    }

    @Override
    public void showToast(@NonNull Integer message, Integer duration) {
        Toast.makeText(this, message, duration).show();
    }

    @Override
    public void showSnackbar(@NonNull String message, Integer duration) {
        Snackbar.make(getBinding().getRoot(), message, duration).show();
    }

    @Override
    public void showSnackbar(@NonNull Integer message, Integer duration) {
        Snackbar.make(getBinding().getRoot(), message, duration).show();
    }
}