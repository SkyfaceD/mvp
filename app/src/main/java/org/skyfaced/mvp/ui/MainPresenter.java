package org.skyfaced.mvp.ui;

import android.util.Log;

import org.skyfaced.mvp.mvp.BasePresenter;
import org.skyfaced.mvp.network.NetworkService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class MainPresenter extends BasePresenter<MainView> {
    private final NetworkService networkService;

    public MainPresenter(NetworkService networkService) {
        this.networkService = networkService;
        asyncListOfStrings();
    }

    void changeText(String text) {
        Log.d("TAG", text);
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

    List<String> listOfStrings() {
        List<String> items = networkService.listOfStrings();
        Collections.shuffle(items);
        return items;
    }

    private void asyncListOfStrings() {
    }
}
