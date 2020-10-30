package com.ski.box.view.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.adapter.GroupInviteUrlAdapter;
import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.mvp.contract.group.GroupInviteUrlContract;
import com.ski.box.mvp.presenter.group.GroupInviteUrlPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.CancelDialog;
import com.ski.box.view.view.dialog.group.DeleteDialog;
import com.ski.box.view.view.dialog.group.InviteUrlDialog;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class GroupInviteUrlActivity extends BaseMVPActivity<GroupInviteUrlContract.Presenter> implements GroupInviteUrlContract.View, View.OnClickListener,
        OnRefreshListener , OnLoadMoreListener {
    private HeaderView mHeadView;
    private Button mBtnSure;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRv;
    private int mPageSize = 10;
    private int mPageNo = 1;
    private int mTotalPage = 1;
    private GroupInviteUrlAdapter mAdapter;
    private ArrayList<RebateKV> mRebateKVList = new ArrayList<>();
    private InviteUrlDialog mInviteUrlDialog;
    private DeleteDialog mDeleteDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected GroupInviteUrlContract.Presenter bindPresenter() {
        return new GroupInviteUrlPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_group_invite_url;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRv = findViewById(R.id.recycler_view);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_group_invite_url), true);

        mBtnSure.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new GroupInviteUrlAdapter(this);
        mAdapter.setEmptyView(ActivityUtil.getEmptyView(this));
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<InviteUrl> list = adapter.getData();
                InviteUrl bean = list.get(position);
                int id = view.getId();
                if (id == R.id.iv_detail) {
                    showInviteUrlDialog(bean);
                } else if (id == R.id.iv_copy) {
                    copyInviteUrl(bean);
                } else if (id == R.id.iv_delete) {
                    showDeleteDialog(bean);
                }
            }
        });
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getRebateScope();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_sure) {
            Intent intent = new Intent(this, GroupInviteUrlAddActivity.class);
            intent.putParcelableArrayListExtra(GroupInviteUrlAddActivity.KEY_REBATE_KV_LIST, mRebateKVList);
            startActivity(intent);
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageNo = 1;
        mPresenter.getInviteUrlList(mPageSize, mPageNo);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (mPageNo > mTotalPage) {
            mRefreshLayout.finishLoadMore();
            ToastUtil.showInfo("已经是最后一页了");
            return;
        }
        mPageNo++;
        mPresenter.getInviteUrlList(mPageSize, mPageNo);
    }


    @Override
    public void onRebateScopeResult(List<RebateKV> list) {
        mRebateKVList.clear();
        mRebateKVList.addAll(list);
        mAdapter.setReBateList(list);
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onRebateScopeFailResult(String s) {

    }

    @Override
    public void onSuccessResult(InviteData o) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();

        mAdapter.setUseEmpty(true);
        InviteData bean = (InviteData) o;
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

    }

    @Override
    public void onDeleteResult() {
        ToastUtil.showSuccess("删除成功!");
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onDeleteResult(String s) {
        mRefreshLayout.autoRefresh();
    }

    @Subscribe(tags = {@Tag(ConstantValue.EVENT_GROUP_INVITE_URL_ADD_SUCCESS)})
    public void onAddSuccess(String s) {
        mRefreshLayout.autoRefresh();
    }

    private void showInviteUrlDialog(InviteUrl bean) {
        if(mInviteUrlDialog == null) {
            mInviteUrlDialog = new InviteUrlDialog(this);
        }
        mInviteUrlDialog.setData(bean);
        mInviteUrlDialog.show();
    }

    private void copyInviteUrl(InviteUrl bean) {
        String text = bean.getInviteUrl();
        AppUtil.copy(text, this);
        ToastUtil.showSuccess("复制成功!");
    }

    private void showDeleteDialog(InviteUrl bean) {
        mDeleteDialog = new DeleteDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDeleteDialog != null) {
                    mDeleteDialog.dismiss();
                }
                if(mPresenter != null) {
                    mPresenter.inviteDelete(bean.getCode());
                }
            }
        });
        mDeleteDialog.setContent("是否要删除该推广链接？");
        mDeleteDialog.show();
    }

}
