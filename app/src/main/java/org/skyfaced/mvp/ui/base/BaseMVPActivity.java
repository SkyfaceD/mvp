package org.skyfaced.mvp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.snackbar.Snackbar;

import org.skyfaced.mvp.mvp.own.BaseView;
import org.skyfaced.mvp.mvp.own.Presenter;
import org.skyfaced.mvp.mvp.own.View;

public abstract class BaseMVPActivity<
        VB extends ViewBinding,
        P extends Presenter<V>,
        V extends View> extends BaseActivity<VB> implements BaseView {
    private P presenter = null;

    @NonNull
    protected P getPresenter() {
        if (presenter == null)
            throw new IllegalArgumentException("You are trying to access the binding after onDestroy()?");
        return presenter;
    }

    @NonNull
    protected abstract P setupPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = setupPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttach((V) this);
    }

    @Override
    protected void onStop() {
        presenter.onDetach();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

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