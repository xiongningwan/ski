package com.ski.box.view.activity.group;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.R;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.presenter.group.GroupAddPresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;


public class GroupAddActivity extends BaseMVPActivity<GroupAddContract.Presenter> implements GroupAddContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private NiceSpinner mSpBackRate;
    private ClearEditText mEtName;
    private ClearEditText mEtPwd;
    private ClearEditText mEtPwdConfirm;
    private Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected GroupAddContract.Presenter bindPresenter() {
        return new GroupAddPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_group_add;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtName = findViewById(R.id.et_name);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwdConfirm = findViewById(R.id.et_pwd_confirm);
        mSpBackRate = findViewById(R.id.spinner_back_rate);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_group_add), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
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
            doCreate();
        }
    }


    private void doCreate() {
        RebateKV bean = (RebateKV)mSpBackRate.getSelectedItem();
        if(bean == null) {
            ToastUtil.showInfo("请先选择奖金返点");
            return;
        }
        String name = mEtName.getText().toString().trim();
        String pwd = mEtPwd.getText().toString().trim();
        String pwdConfirm = mEtPwdConfirm.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)|| TextUtils.isEmpty(pwdConfirm)) {
            ToastUtil.showInfo("输入框不能为空");
            return;
        }
        if(!pwd.equals(pwdConfirm)) {
            ToastUtil.showInfo("两次输入密码不一致");
            return;
        }
        pwd = MD5Util.md5Password(pwd);
        mPresenter.agentCreate(name,pwd,bean.getRebate());
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
        ToastUtil.showSuccess("创建成功！");
    }

    @Override
    public void onAddFailResult(String s) {
        ToastUtil.showSuccess("创建失败！");
    }




    private void setSpinner(List<RebateKV> list){
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<RebateKV>() {
            @Override
            public Spannable format(RebateKV bean) {
                return new SpannableString(bean.getRebate() + "-" + bean.getPercent());
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
