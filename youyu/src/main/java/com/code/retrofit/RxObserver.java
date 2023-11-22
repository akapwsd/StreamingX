package com.code.retrofit;

import java.io.IOException;
import java.io.InterruptedIOException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;

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
        if (e instanceof InterruptedIOException) {
            error(-1, "http connect fail InterruptedIOException");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            try {
                int code = httpException.code();
                byte[] s = httpException.response().errorBody().bytes();
                String errorData = new String(s);
                error(code, errorData);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            error(-2, "UNKNOWN ERROR");
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
