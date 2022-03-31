package org.skyfaced.mvp.util;

import android.text.Editable;
import android.text.TextWatcher;

public interface TextWatcherMediator extends TextWatcher {
    @Override
    default void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    default void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    default void afterTextChanged(Editable editable) {
    }
}


