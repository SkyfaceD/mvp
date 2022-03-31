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
import org.skyfaced.mvp.ui.base.BaseMVPActivity;
import org.skyfaced.mvp.util.TextWatcherMediator;
import org.skyfaced.mvp.util.WaifuType;

import java.util.List;
import java.util.Random;

public final class MainActivity extends BaseMVPActivity<ActivityMainBinding, MainPresenter, MainView> implements MainView {
    private SimpleAdapter simpleAdapter;
    private WaifuAdapter waifuAdapter;

    @NonNull
    @Override
    protected MainPresenter setupPresenter() {
        WaifuService waifuService = AppNetwork.getInstance().waifuService;
        WaifuRepository waifuRepository = new WaifuRepositoryImpl(waifuService);
        InstantService instantService = new InstantServiceImpl();
        return new MainPresenter(waifuRepository, instantService);
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

        simpleAdapter = new SimpleAdapter(getPresenter()::onItemClick);
        getBinding().recycler.setAdapter(simpleAdapter);
        updateSimpleRecycler(getPresenter().strings());

        getBinding().btnLoadWaifu.setOnClickListener(v -> {
            Integer index = new Random().nextInt(WaifuType.SFW.Category.values().length);
            WaifuType.SFW.Category category = WaifuType.SFW.Category.values()[index];
            getPresenter().onWaifuClick(new WaifuType.SFW(category));
        });

        waifuAdapter = new WaifuAdapter(getPresenter()::onWaifuItemClick);
        getBinding().recyclerWaifu.setAdapter(waifuAdapter);

        getBinding().btnLoadWaifus.setOnClickListener(v -> {
            Integer index = new Random().nextInt(WaifuType.SFW.Category.values().length);
            WaifuType.SFW.Category category = WaifuType.SFW.Category.values()[index];
            getPresenter().onWaifusClick(new WaifuType.SFW(category));
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
