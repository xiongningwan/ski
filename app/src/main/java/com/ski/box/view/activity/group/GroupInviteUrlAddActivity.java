package com.ski.box.view.activity.group;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.bean.money.PayType;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.contract.group.GroupInviteUrlAddContract;
import com.ski.box.mvp.presenter.group.GroupAddPresenter;
import com.ski.box.mvp.presenter.group.GroupInviteUrlAddPresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.spinner.NiceSpinner;
import com.ski.box.view.view.spinner.OnSpinnerItemSelectedListener;
import com.ski.box.view.view.spinner.SpinnerTextFormatter;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;


import java.util.ArrayList;
import java.util.List;


public class GroupInviteUrlAddActivity extends BaseMVPActivity<GroupInviteUrlAddContract.Presenter> implements GroupInviteUrlAddContract.View, View.OnClickListener {
    public static final String KEY_REBATE_KV_LIST = "key_rebate_kv_list";
    private HeaderView mHeadView;
    private NiceSpinner mSpBackRate;
    private ClearEditText mEtMsg;
    private Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected GroupInviteUrlAddContract.Presenter bindPresenter() {
        return new GroupInviteUrlAddPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_group_invite_url_add;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtMsg = findViewById(R.id.et_msg);
        mSpBackRate = findViewById(R.id.spinner_back_rate);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_group_invite_url_add)), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        ArrayList<RebateKV> rebateKVList = getIntent().getParcelableArrayListExtra(KEY_REBATE_KV_LIST);
        if (rebateKVList != null && rebateKVList.size() > 0) {
            setSpinner(rebateKVList);
        } else {
            mPresenter.getRebateScope();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
            doCreate();
        }
    }


    private void doCreate() {
        if (0 == mSpBackRate.getSelectedIndex()) {
            ToastUtil.showError("请选择奖金返点");
            return;
        }
        RebateKV bean = (RebateKV) mSpBackRate.getSelectedItem();
        if (bean == null) {
            ToastUtil.showInfo("请选择奖金返点");
            return;
        }
        String inviteWord = mEtMsg.getText().toString().trim();
        if (TextUtils.isEmpty(inviteWord)) {
            ToastUtil.showInfo("输入框不能为空");
            return;
        }
        mPresenter.inviteCreate(inviteWord, bean.getRebate());
    }


    @Override
    public void onSuccessResult(List<RebateKV> list) {
        setSpinner(list);
    }

    @Override
    public void onFailResult(String s) {
    }

    @Override
    public void onAddSuccessResult() {
        ToastUtil.showSuccess("创建邀请链接成功");
        RxBus.get().post(ConstantValue.EVENT_GROUP_INVITE_URL_ADD_SUCCESS, "");
    }

    @Override
    public void onAddFailResult(String s) {
    }


    private void setSpinner(List<RebateKV> list) {
        RebateKV rebateKV = new RebateKV();
        rebateKV.setPercent(LanguageUtil.getText("请选择"));
        list.add(0, rebateKV);
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<RebateKV>() {
            @Override
            public Spannable format(RebateKV bean) {
                if (0 == bean.getRebate()) {
                    return new SpannableString(bean.getPercent());
                } else {
                    return new SpannableString(bean.getRebate() + "-" + bean.getPercent());
                }
            }
        };

        mSpBackRate.setSpinnerTextFormatter(textFormatter);
        mSpBackRate.setSelectedTextFormatter(textFormatter);
        mSpBackRate.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // Bank bank = (Bank) parent.getSelectedItem();
            }
        });
        mSpBackRate.attachDataSource(list);
    }
}
