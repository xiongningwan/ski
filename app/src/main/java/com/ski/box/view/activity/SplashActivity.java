package com.ski.box.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.widget.ImageView;

import com.ski.box.BuildConfig;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.HeaderUtil;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.net.RetrofitHelper;
import com.yb.core.utils.SPUtils;

import java.util.Locale;

public class SplashActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View {
    private final static String KEY_IS_INSTALL = "key_splash_is_install";
    private ImageView mIvSplash;

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_splash;
    }

    @Override
    protected void initViews() {
         mIvSplash =   findViewById(R.id.iv_splash);
    }

    @Override
    protected void initData(Bundle bundle) {
        boolean isInstall = SPUtils.getBoolean(this, KEY_IS_INSTALL, false);
        if(!isInstall) {
            SPUtils.putBoolean(this, KEY_IS_INSTALL, true);
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        } else {
            if (!TextUtils.isEmpty(SPUtils.getString(this, LoginActivity.KEY_TOKEN)) && !TextUtils.isEmpty(SPUtils.getString(this, LoginActivity.KEY_AUTHORIZATION))) {
                String token = SPUtils.getString(this, LoginActivity.KEY_TOKEN);
                String authorization = SPUtils.getString(this, LoginActivity.KEY_AUTHORIZATION);

                RetrofitHelper.getInstance().init(ConstantValue.BASE_HOST, BuildConfig.DEBUG,
                        HeaderUtil.getHeader(token, authorization, ConstantValue.DEVICE, ActivityUtil.getDeviceLanguage(this)));
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        }
    }





}
