package com.ski.box.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ski.box.BuildConfig;
import com.ski.box.SKISdkManger;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.LoginContract;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.ski.box.view.activity.LoginActivityOld;
import com.ski.box.view.activity.MainActivity;
import com.yb.core.base.RxPresenter;
import com.yb.core.net.HttpResult;
import com.yb.core.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private IUserModel mUserModel;

    public LoginPresenter(Context context) {
        super(context);
        mUserModel = new UserModel();
    }


    @Override
    public void doLogin(String memberAccount, String password) {
        Disposable disposable = mUserModel.login(new Consumer<String>() {
            @Override
            public void accept(String str) {
                mView.onLoginResult(str);
            }
        },memberAccount, password);
        addDisposable(disposable);
    }

}


