package com.ski.box.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.HallContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.mvp.presenter.HallPresenter;
import com.yb.core.base.BaseMVPFragment;

public class RechargeFragment extends BaseMVPFragment<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {

    public RechargeFragment() {
    }

    public static RechargeFragment newInstance() {
        RechargeFragment fragment = new RechargeFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_recharge;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(mContext);
    }


    @Override
    public void onClick(View view) {

    }
}
