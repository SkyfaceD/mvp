package org.skyfaced.mvp.ui.base;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;
import moxy.viewstate.strategy.alias.OneExecution;

public interface BaseView extends MvpView {
    @OneExecution
    void hideKeyboard();

    @OneExecution
    void showToast(@NonNull String message, Integer duration);

    @OneExecution
    void showToast(@NonNull @StringRes Integer message, Integer duration);

    @OneExecution
    default void showToast(@NonNull String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    @OneExecution
    default void showToast(@NonNull @StringRes Integer message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    @OneExecution
    void showSnackbar(@NonNull String message, Integer duration);

    @OneExecution
    void showSnackbar(@NonNull @StringRes Integer message, Integer duration);

    @OneExecution
    default void showSnackbar(@NonNull String message) {
        showSnackbar(message, Snackbar.LENGTH_SHORT);
    }

    @OneExecution
    default void showSnackbar(@NonNull @StringRes Integer message) {
        showSnackbar(message, Snackbar.LENGTH_SHORT);
    }

    @AddToEndSingle
    default void showOnScreenLoader() {
    }

    @AddToEndSingle
    default void hideOnScreenLoader() {
    }
}
