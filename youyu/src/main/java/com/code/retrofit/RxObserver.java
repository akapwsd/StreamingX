package com.code.retrofit;

import java.io.InterruptedIOException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class RxObserver implements Observer {
    public abstract void Success(Object t);

    public abstract void error(int code, String error);

    private Disposable d;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(@NonNull Object o) {
        Success(o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof InterruptedIOException) {
            error(400, "http connect fail");
        } else {
            error(400, e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    public Disposable getDisposable() {
        return d;
    }

    public void CloseRequest() {
        if (d != null) {
            d.dispose();
        }
    }
}
