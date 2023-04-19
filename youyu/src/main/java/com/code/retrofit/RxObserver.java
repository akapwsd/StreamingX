package com.code.retrofit;

import com.code.bean.BaseBean;

import java.io.InterruptedIOException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class RxObserver<T> implements Observer {
    public abstract void Success(T t, String msg);

    public abstract void error(int code, String error);

    private Disposable d;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(@NonNull Object o) {
        if (o instanceof BaseBean) {
            BaseBean<T> t = (BaseBean<T>) o;
            int code = t.getCode();
            if (code == 200) {
                Success(t.getData(), t.getMsg());
            } else {
                error(code, t.getMsg());
            }
        }
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
