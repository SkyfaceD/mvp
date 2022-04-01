package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.ui.base.BaseView;

import java.util.List;

import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface MainView extends BaseView {
    @AddToEndSingle
    void updateSimpleRecycler(List<String> items);

    @AddToEndSingle
    void updateWaifu(ImageDto image);

    @AddToEndSingle
    void updateWaifuRecycler(List<ImageDto> images);
}
