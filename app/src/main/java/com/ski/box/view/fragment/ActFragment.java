package com.ski.box.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.adapter.ActAdapter;
import com.ski.box.bean.ActBean;
import com.ski.box.mvp.contract.ActContract;
import com.ski.box.mvp.presenter.ActPresenter;
import com.ski.box.view.activity.AgentWebViewActivity;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.LanguageUtil;

import java.util.List;

public class ActFragment extends BaseMVPFragment<ActContract.Presenter> implements ActContract.View, View.OnClickListener {

    private HeaderView mHeadView;
    private RecyclerView mRv;
    private ActAdapter mAdapter;

    public ActFragment() {
    }

    public static ActFragment newInstance() {
        ActFragment fragment = new ActFragment();
        return fragment;
    }

    @Override
    protected ActContract.Presenter bindPresenter() {
        return new ActPresenter(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_act;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
       mHeadView =  view.findViewById(R.id.head_view);
       mRv =  view.findViewById(R.id.rv);
        mHeadView.setHeader(LanguageUtil.getText("优惠活动"), false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mRv.setAdapter(mAdapter = new ActAdapter(requireActivity()));

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<ActBean> list = (List<ActBean>) adapter.getData();
                ActBean actBean = list.get(position);
                AgentWebViewActivity.startAgentWebView(requireActivity(),
                        actBean.getActivityName(),
                        actBean.getTargetUrl());
            }
        });
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        mPresenter.getActList();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onActResult(List<ActBean> list) {
        mAdapter.setNewInstance(list);
    }
}
