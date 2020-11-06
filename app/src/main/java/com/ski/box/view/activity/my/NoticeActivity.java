package com.ski.box.view.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.NoticeAdapter;
import com.ski.box.bean.NoticeData;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.mvp.contract.my.NoticeContract;
import com.ski.box.mvp.presenter.my.NoticePresenter;
import com.ski.box.view.activity.RecordDetailActivity;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import java.util.List;


public class NoticeActivity extends BaseMVPActivity<NoticeContract.Presenter> implements NoticeContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private HeaderView mHeadView;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecord;
    private NoticeAdapter mAdapter;
    private int mPageSize = 10;
    private int mPageNo = 1;
    private int mTotalPage = 1;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected NoticeContract.Presenter bindPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_notice;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mRefreshLayout =findViewById(R.id.refreshLayout);
        mRvRecord = findViewById(R.id.recycler_view);
        mHeadView.setHeader(getString(R.string.ski_sys_notice), true);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new NoticeAdapter();
        mRvRecord.setLayoutManager(new LinearLayoutManager(this));
        mRvRecord.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<NoticeData.ListBean> list = (List<NoticeData.ListBean>) adapter.getData();
                NoticeData.ListBean bean = list.get(position);
                gotoDetail(bean);
            }
        });
    }

    private void gotoDetail(NoticeData.ListBean bean) {
        Intent intent = new Intent(this, NoticeDetailActivity.class);
        intent.putExtra(NoticeDetailActivity.KEY_RECORD_BEAN, bean);
        startActivity(intent);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
//        if (id == R.id.btn_sure) {
//            goToBind();
//        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNo = 1;
        mPresenter.getNoticeList(mPageNo, mPageSize);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mPageNo > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mPageNo++;
        mPresenter.getNoticeList(mPageNo, mPageSize);
    }


    @Override
    public void onNoticeResult(NoticeData bean) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        mAdapter.setUseEmpty(true);
        mTotalPage = bean.getTotalPage();
        int currentPage = bean.getCurrentPage();
        if (currentPage > 1) {
//            加载更多
            mAdapter.addData(bean.getList());
        } else {
            mAdapter.setList(bean.getList());
        }
    }

    @Override
    public void onNoticeFailResult() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }
}
