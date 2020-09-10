package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.mvp.contract.EmptyContract;
import com.yb.core.base.RxPresenter;

public class EmptyPresenter extends RxPresenter<EmptyContract.View> implements EmptyContract.Presenter {

    public EmptyPresenter(Context context) {
        super(context);
    }



}


