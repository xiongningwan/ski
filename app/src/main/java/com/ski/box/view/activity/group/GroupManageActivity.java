package com.ski.box.view.activity.group;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.R;
import com.ski.box.adapter.GroupInviteUrlAdapter;
import com.ski.box.adapter.GroupManageAdapter;
import com.ski.box.bean.group.GroupMemberData;
import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.contract.group.GroupManageContract;
import com.ski.box.mvp.presenter.group.GroupAddPresenter;
import com.ski.box.mvp.presenter.group.GroupManagePresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;


public class GroupManageActivity extends BaseMVPActivity<GroupManageContract.Presenter> implements GroupManageContract.View, View.OnClickListener, OnRefreshListener, OnLoadMoreListener {
    private HeaderView mHeadView;
    private ClearEditText mEtAccount;
    private Button mBtnSure;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRv;
    private int mPageSize = 10;
    private int mPageNo = 1;
    private int mTotalPage = 1;
    private GroupManageAdapter mAdapter;
    private String mAccount;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected GroupManageContract.Presenter bindPresenter() {
        return new GroupManagePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_group_manage;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtAccount = findViewById(R.id.et_account);
        mBtnSure = findViewById(R.id.btn_sure);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRv = findViewById(R.id.recycler_view);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_group_manage)), true);

        mBtnSure.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new GroupManageAdapter(this);
        mAdapter.setEmptyView(ActivityUtil.getEmptyView(this));
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_sure) {
            mAccount = mEtAccount.getText().toString().trim();
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNo = 1;
        mPresenter.getTeamMemberList(mAccount,mPageSize, mPageNo);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mPageNo > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mPageNo++;

        mPresenter.getTeamMemberList(mAccount,mPageSize, mPageNo);
    }

    @Override
    public void onSuccessResult(GroupMemberData bean) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        mAdapter.setUseEmpty(true);
        mTotalPage = bean.getTotalPage();

        int currentPage = bean.getCurrentPage();
        if (currentPage > 1) {
            // 加载更多
            mAdapter.addData(bean.getList());
        } else {
            mAdapter.setNewInstance(bean.getList());
        }
    }

    @Override
    public void onFailResult(String s) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }



}
