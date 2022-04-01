package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.mvp.own.BasePresenter;
import org.skyfaced.mvp.service.instant.InstantService;
import org.skyfaced.mvp.service.network.WaifuRepository;
import org.skyfaced.mvp.util.WaifuType;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
        getView().showOnScreenLoader();
        waifuRepository.waifu(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<ImageDto>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@NonNull ImageDto image) {
                                getView().updateWaifu(image);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                getView().showSnackbar(e.getMessage());
                                getView().hideOnScreenLoader();
                            }

                            @Override
                            public void onComplete() {
                                getView().showToast("Вайфу загружена");
                                getView().hideOnScreenLoader();
                            }
                        }
                );
    }

    void onWaifuItemClick(ImageDto image) {
        getView().showSnackbar(image.getUrl());
    }

    void onWaifusClick(WaifuType type) {
        waifuRepository.waifus(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImageDto>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<ImageDto> images) {
                        getView().updateWaifuRecycler(images);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getView().showSnackbar(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().showToast("Вайфу загружены");
                    }
                });
    }
}
