package com.ski.box.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.adapter.FragmentAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.UpdateUtil;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.ski.box.view.fragment.HallFragment;
import com.ski.box.view.fragment.PersonalFragment;
import com.ski.box.view.fragment.RechargeFragment;
import com.ski.box.view.fragment.WithdrawFragment;
import com.ski.box.view.view.HallTabLayout;
import com.ski.box.view.view.NoScrollViewPager;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.ski.box.ConstantValue.EVENT_TOKEN_DISABLE;
import static com.ski.box.service.AlarmService.ALARM_ACTION;


public class MainActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View {
    private NoScrollViewPager mViewPager;
    private HallTabLayout mTabView;
    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    private Disposable mSubscribe;

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_main;
    }

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected void initViews() {
        RxBus.get().register(this);
        mViewPager = findViewById(R.id.viewPager);
        mTabView = findViewById(R.id.llTab);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HallFragment.newInstance());
        fragmentList.add(RechargeFragment.newInstance(0));
        fragmentList.add(WithdrawFragment.newInstance(0));
        fragmentList.add(PersonalFragment.newInstance());
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager, fragmentList);
        mViewPager.setAdapter(adapter);
        // 预加载数量
        mViewPager.setOffscreenPageLimit(3);
        // viewpager 设置滚动监听
       // mViewPager.addOnPageChangeListener(this);
        mTabView.bindViewPager(mViewPager);
        mTabView.switchTab(HallTabLayout.TAB_INDEX_0);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        UpdateUtil.checkVersion(this, false);
        startAlarmService();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UpdateUtil.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }


    @Subscribe(tags = {@Tag(EVENT_TOKEN_DISABLE)})
    public void tokenDisable(String s) {
        saveSetSp_token_authorization("","");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void saveSetSp_token_authorization(String token, String authorization) {
        SPUtils.putString(this, LoginActivity.KEY_TOKEN, token);
        SPUtils.putString(this,  LoginActivity.KEY_AUTHORIZATION, authorization);
    }


    @Override
    public void onBackPressed() {
        closeApp();
    }

    private void closeApp() {
        // 判断时间间隔
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            ToastUtil.showInfo("再按一次返回键退出程序");
        } else {
            // 清除登录后用户数据
           // DataCenter.getInstance().clearUser();
            // 退出
            finish();
        }
    }

    public void gotoPage(int page) {
        mTabView.switchTab(page);
    }

    @Subscribe(tags = {@Tag("KeyBoard_show_dismiss")})
    public void onKeyboardUpdate(String s) {
        if("show".equals(s)) {
            mTabView.setVisibility(View.GONE);
        } else {
            mTabView.setVisibility(View.VISIBLE);
        }
    }

    public void startAlarmService() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) {
            mSubscribe.dispose();
        }
        mSubscribe = Observable.interval(0, 1, TimeUnit.SECONDS).flatMap(new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long integer) throws Exception {
                DataCenter.getInstance().cdServerTime();
                LotteryTimeUtil.countTime();
                Intent intent = new Intent(ALARM_ACTION + DataCenter.getInstance().getToken());
                AppUtil.getContext().sendBroadcast(intent);
                return Observable.just(1L);
            }
        }).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                    }
                });

        addDisposable(mSubscribe);
    }
}