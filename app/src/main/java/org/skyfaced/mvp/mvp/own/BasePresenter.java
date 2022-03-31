package org.skyfaced.mvp.mvp.own;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BasePresenter<T extends View> implements Presenter<T> {
    private WeakReference<T> viewReference = null;
    private Disposable disposable = Disposable.empty();

    @NonNull
    protected T getView() {
        T view = viewReference.get();
        if (view == null)
            throw new IllegalArgumentException("You are trying to access the view after onDestroy()?");
        return viewReference.get();
    }

    @Override
    public void onAttach(T view) {
        if (view == null) throw new IllegalArgumentException("You are trying to attach nothing?");
        viewReference = new WeakReference<>(view);
    }

    @Override
    public void onDetach() {
        viewReference.clear();
        viewReference = null;
    }

    @Override
    public void onDestroy() {
        if (viewReference != null) onDetach();
    }
}