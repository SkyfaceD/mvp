package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.mvp.own.BasePresenter;
import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.util.WaifuType;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import timber.log.Timber;

public final class MainPresenter extends BasePresenter<MainView> {
    private final WaifuRepository waifuRepository;
    private final InstantService instantService;

    public MainPresenter(WaifuRepository waifuRepository, InstantService instantService) {
        this.waifuRepository = waifuRepository;
        this.instantService = instantService;
    }

    void changeText(String text) {
        Timber.d(text);
    }

    void onItemClick(String item) {
        getView().showSnackbar("Hello from presenter\n" + item);
    }

    void onMessageClick() {
        Integer random = new Random().nextInt();
        if (random % 2 == 0) {
            getView().showSnackbar(random + " from snack");
        } else {
            getView().showToast(random + " from toast");
        }
    }

    List<String> strings() {
        List<String> items = instantService.listOfStrings();
        Collections.shuffle(items);
        return items;
    }

    void onWaifuClick(WaifuType type) {
        getView().updateWaifu(waifuRepository.waifu(type));
    }

    void onWaifuItemClick(ImageDto image) {
        getView().showSnackbar(image.getUrl());
    }

    void onWaifusClick(WaifuType type) {
        getView().updateWaifuRecycler(waifuRepository.waifus(type));
    }
}
