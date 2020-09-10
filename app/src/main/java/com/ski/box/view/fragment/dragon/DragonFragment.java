package com.ski.box.view.fragment.dragon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;



public class DragonFragment extends BaseMVPFragment<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener, OnRefreshListener {

    public static DragonFragment newInstance() {
        DragonFragment fragment = new DragonFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(KEY_FROM, from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_hall_fragment_long_dragon_layout;
    }

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            mFrom = bundle.getInt(KEY_FROM, 0);
//        }

    }

    @Override
    protected void initData(Bundle bundle) {
    }


    @Override
    protected void loadData() {
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }


}
