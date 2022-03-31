package org.skyfaced.mvp.mvp;

public interface Presenter<T extends View> {
    void onAttach(T view);

    void onDetach();

    void onDestroy();
}
