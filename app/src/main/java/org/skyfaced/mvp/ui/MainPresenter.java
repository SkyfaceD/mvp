package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.util.WaifuType;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import timber.log.Timber;

@InjectViewState
public final class MainPresenter extends MvpPresenter<MainView> {
    private final CompositeDisposable disposable = new CompositeDisposable();

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
        getViewState().showSnackbar("Hello from presenter\n" + item);
    }

    void onMessageClick() {
        Integer random = new Random().nextInt();
        if (random % 2 == 0) {
            getViewState().showSnackbar(random + " from snack");
        } else {
            getViewState().showToast(random + " from toast");
        }
    }

    List<String> strings() {
        List<String> items = instantService.listOfStrings();
        Collections.shuffle(items);
        return items;
    }

    void onWaifuClick(WaifuType type) {
        Disposable d = waifuRepository.waifu(type)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        image -> getViewState().updateWaifu(image),
                        cause -> getViewState().showSnackbar(cause.getMessage()),
                        () -> getViewState().showToast("Вайфу загружена")
                );
        disposable.add(d);
    }

    void onWaifuItemClick(ImageDto image) {
        getViewState().showSnackbar(image.getUrl());
    }

    void onWaifusClick(WaifuType type) {
        getViewState().showOnScreenLoader();
        Disposable d = waifuRepository.waifus(type)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        images -> getViewState().updateWaifuRecycler(images),
                        cause -> {
                            getViewState().showSnackbar(cause.getMessage());
                            getViewState().hideOnScreenLoader();
                        },
                        () -> {
                            getViewState().showToast("Вайфу загружены");
                            getViewState().hideOnScreenLoader();
                        }
                );
        disposable.add(d);
    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }
}
