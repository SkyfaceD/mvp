package org.skyfaced.mvp.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    private VB binding = null;

    @NonNull
    protected VB getBinding() {
        if (binding == null)
            throw new IllegalArgumentException("You are trying to access the binding after onDestroy()?");
        return binding;
    }

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

    @NonNull
    protected abstract VB setupBinding(LayoutInflater inflater);
}