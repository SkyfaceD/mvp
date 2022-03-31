package org.skyfaced.mvp.util;

import android.text.Editable;
import android.text.TextWatcher;

public final class AndroidUtil {
    public static TextWatcher onTextChangedWatcher(OnTextChangeListener listener) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onChanged(s.toString(), start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Ignore
            }
        };
    }

    public interface OnTextChangeListener {
        void onChanged(String text, Integer start, Integer before, Integer count);
    }
}