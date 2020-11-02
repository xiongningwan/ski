package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.adapter.UpdateHeadAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.HeadBean;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.my.BindPhoneContract;
import com.ski.box.mvp.contract.my.UpdateHeadContract;
import com.ski.box.mvp.presenter.my.BindPhonePresenter;
import com.ski.box.mvp.presenter.my.UpdateHeadPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.EasyCountDownTimer;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ski.box.ConstantValue.EVENT_BIND_PHONE_SUCCESS;
import static com.ski.box.ConstantValue.EVENT_UPDATE_HEAD_SUCCESS;


public class UpdateHeadActivity extends BaseMVPActivity<UpdateHeadContract.Presenter> implements UpdateHeadContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private CircleImageView mIvHead;
    private RecyclerView mRv;
    private Button mBtnSure;
    private UpdateHeadAdapter mAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected UpdateHeadContract.Presenter bindPresenter() {
        return new UpdateHeadPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_update_head;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mIvHead = findViewById(R.id.iv_head);
        mRv = findViewById(R.id.rv);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_update_head), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter = new UpdateHeadAdapter(this);
        mRv.setLayoutManager(new GridLayoutManager(this, 5));
        mRv.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                List<HeadBean> headBeans = adapter.getData();
                HeadBean headBean = headBeans.get(position);
                for (HeadBean headBean1 : headBeans) {
                        headBean1.setSelected(false);
                }
                if (view.getId() == R.id.iv_head) {
                    headBean.setSelected(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.setNewInstance(getHeadData());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
             doUpdate();
        }
    }
    String profile = "";
    private void doUpdate() {

        List<HeadBean> headBeans = mAdapter.getData();
        for (HeadBean headBean1 : headBeans) {
            if(headBean1.isSelected()) {
                profile = headBean1.getProfile();
            }
        }
        if(TextUtils.isEmpty(profile)) {
            ToastUtil.showError("请先选择");
            return;
        }
        mPresenter.memberUpdateProfile(profile);
    }


    @Override
    public void onSuccessResult() {
        mIvHead.setImageResource(ActivityUtil.getHeadByProfile(profile));
        DataCenter.getInstance().getUser().setProfile(profile);
        ToastUtil.showSuccess("修改成功!");
        RxBus.get().post(EVENT_UPDATE_HEAD_SUCCESS, "");
    }

    @Override
    public void onFailResult(String s) {
    }


    private List<HeadBean> getHeadData() {
        List<HeadBean> list = new ArrayList<>();
        list.add(new HeadBean("1", R.mipmap.ski_icon_head_1));
        list.add(new HeadBean("2", R.mipmap.ski_icon_head_2));
        list.add(new HeadBean("3", R.mipmap.ski_icon_head_3));
        list.add(new HeadBean("4", R.mipmap.ski_icon_head_4));
        list.add(new HeadBean("5", R.mipmap.ski_icon_head_5));
        list.add(new HeadBean("6", R.mipmap.ski_icon_head_6));
        list.add(new HeadBean("7", R.mipmap.ski_icon_head_7));
        list.add(new HeadBean("8", R.mipmap.ski_icon_head_8));
        list.add(new HeadBean("9", R.mipmap.ski_icon_head_9));
        list.add(new HeadBean("10", R.mipmap.ski_icon_head_10));
        list.add(new HeadBean("11", R.mipmap.ski_icon_head_11));
        list.add(new HeadBean("12", R.mipmap.ski_icon_head_12));
        list.add(new HeadBean("13", R.mipmap.ski_icon_head_13));
        list.add(new HeadBean("14", R.mipmap.ski_icon_head_14));
        list.add(new HeadBean("15", R.mipmap.ski_icon_head_15));
        list.add(new HeadBean("16", R.mipmap.ski_icon_head_16));
        list.add(new HeadBean("17", R.mipmap.ski_icon_head_17));
        list.add(new HeadBean("18", R.mipmap.ski_icon_head_18));
        list.add(new HeadBean("19", R.mipmap.ski_icon_head_19));
        list.add(new HeadBean("20", R.mipmap.ski_icon_head_20));

        User user = DataCenter.getInstance().getUser();
        for (HeadBean headBean : list) {
            if (headBean.getProfile().equals(user.getProfile())) {
                headBean.setSelected(true);
                mIvHead.setImageResource(headBean.getRes());
            }
        }
        return list;
    }

}
