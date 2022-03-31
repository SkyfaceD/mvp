package org.skyfaced.mvp.ui;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.skyfaced.mvp.databinding.ActivityMainBinding;
import org.skyfaced.mvp.network.NetworkService;
import org.skyfaced.mvp.network.NetworkServiceImpl;
import org.skyfaced.mvp.ui.base.BaseMVPActivity;
import org.skyfaced.mvp.util.TextWatcherMediator;

import java.util.List;

public final class MainActivity extends BaseMVPActivity<ActivityMainBinding, MainPresenter, MainView> implements MainView {
    private MainAdapter adapter;

    @NonNull
    @Override
    protected MainPresenter setupPresenter() {
        NetworkService networkService = new NetworkServiceImpl();
        return new MainPresenter(networkService);
    }

    @NonNull
    @Override
    protected ActivityMainBinding setupBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupContent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onAttach(this);
    }

    private void setupContent() {
        TextWatcher watcher = new TextWatcherMediator() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().changeText(charSequence.toString());
            }
        };
        getBinding().edt.addTextChangedListener(watcher);
//        getBinding().edt.removeTextChangedListener(watcher);

        getBinding().btnMessage.setOnClickListener(v -> getPresenter().onMessageClick());

        adapter = new MainAdapter(getPresenter()::onItemClick);
        getBinding().recycler.setAdapter(adapter);
        updateRecycler(getPresenter().listOfStrings());
    }

    /************
     * ViewImpl *
     ************/
    @Override
    public void updateRecycler(List<String> items) {
        adapter.submitList(items);
    }

    @Override
    public void showOnScreenLoader() {
        getBinding().progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOnScreenLoader() {
        getBinding().progress.setVisibility(View.GONE);
    }
}
