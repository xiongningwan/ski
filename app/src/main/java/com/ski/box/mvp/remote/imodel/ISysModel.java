package com.ski.box.mvp.remote.imodel;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface ISysModel {
    Disposable getSysConfig(Consumer s, Consumer e);

    Disposable checkVersion(Consumer s, Consumer e, String version, String mobType);
}
