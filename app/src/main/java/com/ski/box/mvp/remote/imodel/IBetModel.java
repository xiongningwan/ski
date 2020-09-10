package com.ski.box.mvp.remote.imodel;


import com.ski.box.bean.MkBetParamEntity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface IBetModel {
    /**
     * 获取所有彩种
     */
    Disposable getTickettypelist(Consumer s);


    /**
     * 双面盘投注
     */
    Disposable doubleBet(Consumer s, Consumer e, MkBetParamEntity entity);

    /**
     * 双面盘 追号投注
     */
    Disposable doubleTraceBet(Consumer s,Consumer e, MkBetParamEntity entity);
}
