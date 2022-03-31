package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.mvp.own.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void updateRecycler(List<String> items);
}
