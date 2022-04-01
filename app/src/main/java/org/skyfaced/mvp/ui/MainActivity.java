package org.skyfaced.mvp.ui;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.skyfaced.mvp.databinding.ActivityMainBinding;
import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.instant.InstantServiceImpl;
import org.skyfaced.mvp.service.network.AppNetwork;
import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.service.network.WaifuRepositoryImpl;
import org.skyfaced.mvp.service.network.WaifuService;
import org.skyfaced.mvp.ui.base.BaseActivity;
import org.skyfaced.mvp.util.TextWatcherMediator;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;
import java.util.Random;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public final class MainActivity extends BaseActivity<ActivityMainBinding> implements MainView {
    @InjectPresenter
    MainPresenter presenter;

    private SimpleAdapter simpleAdapter;
    private WaifuAdapter waifuAdapter;

    @NonNull
    @Override
    protected ActivityMainBinding setupBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @ProvidePresenter
    protected MainPresenter setupPresenter() {
        WaifuService waifuService = AppNetwork.getInstance().waifuService;
        WaifuRepository waifuRepository = new WaifuRepositoryImpl(waifuService);
        InstantService instantService = new InstantServiceImpl();
        return new MainPresenter(waifuRepository, instantService);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupContent();
    }

    private void setupContent() {
        TextWatcher watcher = new TextWatcherMediator() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.changeText(charSequence.toString());
            }
        };
        getBinding().edt.addTextChangedListener(watcher);
//        getBinding().edt.removeTextChangedListener(watcher);

        getBinding().btnMessage.setOnClickListener(v -> presenter.onMessageClick());

        simpleAdapter = new SimpleAdapter(presenter::onItemClick);
        getBinding().recycler.setAdapter(simpleAdapter);
        updateSimpleRecycler(presenter.strings());

        getBinding().btnLoadWaifu.setOnClickListener(v -> {
            Integer index = new Random().nextInt(WaifuType.SFW.Category.values().length);
            WaifuType.SFW.Category category = WaifuType.SFW.Category.values()[index];
            presenter.onWaifuClick(new WaifuType.SFW(category));
        });

        waifuAdapter = new WaifuAdapter(presenter::onWaifuItemClick);
        getBinding().recyclerWaifu.setAdapter(waifuAdapter);

        getBinding().btnLoadWaifus.setOnClickListener(v -> {
            Integer index = new Random().nextInt(WaifuType.SFW.Category.values().length);
            WaifuType.SFW.Category category = WaifuType.SFW.Category.values()[index];
            presenter.onWaifusClick(new WaifuType.SFW(category));
        });
    }

    /************
     * ViewImpl *
     ************/
    @Override
    public void updateSimpleRecycler(List<String> items) {
        simpleAdapter.submitList(items);
    }

    @Override
    public void updateWaifu(ImageDto image) {
        Glide.with(this).load(image.getUrl()).into(getBinding().imgWaifu);
    }

    @Override
    public void updateWaifuRecycler(List<ImageDto> images) {
        waifuAdapter.submitList(images);
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
