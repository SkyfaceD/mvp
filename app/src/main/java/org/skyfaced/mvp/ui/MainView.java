package org.skyfaced.mvp.ui;

import org.skyfaced.mvp.model.ImageDto;
import org.skyfaced.mvp.mvp.own.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void updateSimpleRecycler(List<String> items);

    void updateWaifu(ImageDto image);

    void updateWaifuRecycler(List<ImageDto> images);
}
