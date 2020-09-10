package com.ski.box.view.fragment.road;

import android.os.Bundle;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.yb.core.base.BaseFragment;


/**
 * body fragment
 */
public class RoadBodyFragment extends BaseFragment {

    public RoadBodyFragment() {
    }

//    public static RoadBodyFragment newInstance(int lotteryId, RoadTitle roadTitle) {
//        RoadBodyFragment fragment = new RoadBodyFragment();
//        Bundle args = new Bundle();
//        args.putInt(KEY_LOTTERY_ID, lotteryId);
//        args.putParcelable(KEY_LOTTERY_ROAD_PLAY, roadTitle);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_body_road;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        Bundle bundle = this.getArguments();
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
    }


    @Override
    protected void loadData() {

    }































}
