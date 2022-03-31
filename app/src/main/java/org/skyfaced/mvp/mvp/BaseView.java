package org.skyfaced.mvp.mvp;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

public interface BaseView extends View {
    void hideKeyboard();

    void showToast(@NonNull String message, Integer duration);

    void showToast(@NonNull @StringRes Integer message, Integer duration);

    default void showToast(@NonNull String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    default void showToast(@NonNull @StringRes Integer message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    void showSnackbar(@NonNull String message, Integer duration);

    void showSnackbar(@NonNull @StringRes Integer message, Integer duration);

    default void showSnackbar(@NonNull String message) {
        showSnackbar(message, Snackbar.LENGTH_SHORT);
    }

    default void showSnackbar(@NonNull @StringRes Integer message) {
        showSnackbar(message, Snackbar.LENGTH_SHORT);
    }

    default void showOnScreenLoader() {
    }

    default void hideOnScreenLoader() {
    }
}
