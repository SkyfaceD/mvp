package org.skyfaced.mvp.mvp.own;

public interface Presenter<T extends View> {
    void onAttach(T view);

    void onDetach();

    void onDestroy();
}
