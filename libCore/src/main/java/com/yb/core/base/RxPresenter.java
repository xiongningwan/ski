package com.yb.core.base;
import android.content.Context;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected Context mContext;
    protected T mView;
    protected CompositeDisposable mDisposable;
//    protected final BaseModel mModel;


    public RxPresenter(Context context) {
        mContext = context;
//        createMode();
    }

//    public abstract void createMode();

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected void addDisposable(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
