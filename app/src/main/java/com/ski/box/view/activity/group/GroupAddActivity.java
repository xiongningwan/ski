package com.ski.box.view.activity.group;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.R;
import com.ski.box.bean.group.RebateKV;
import com.ski.box.mvp.contract.group.GroupAddContract;
import com.ski.box.mvp.presenter.group.GroupAddPresenter;
import com.ski.box.utils.ValidateUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.spinner.NiceSpinner;
import com.ski.box.view.view.spinner.OnSpinnerItemSelectedListener;
import com.ski.box.view.view.spinner.SpinnerTextFormatter;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;


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
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_group_add)), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
//        setEtListener();
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
        if(0 == mSpBackRate.getSelectedIndex()) {
            ToastUtil.showError("请选择奖金返点");
            return;
        }
        RebateKV bean = (RebateKV)mSpBackRate.getSelectedItem();
        if(bean == null) {
            ToastUtil.showInfo("请选择奖金返点");
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
            ToastUtil.showInfo("两次输入的密码不一致");
            return;
        }

        if(name.length() < 6 || name.length() > 16) {
            String err = "账号必须为6-16位";
            ToastUtil.showInfo(err);
            return;
        }
        if(pwd.length() < 6 || pwd.length() > 16) {
            String err = "密码必须为6-16位";
            ToastUtil.showInfo(err);
            return;
        }

//        if (!ValidateUtil.validatePwd_new(name)) {
//            String err = "账号必须为6-16位包含英文与数字组合，区分大小写";
//            ToastUtil.showInfo(err);
//            return;
//        }
//        if (!ValidateUtil.validatePwd_new(pwd)) {
//            String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
//            ToastUtil.showInfo(err);
//            return;
//        }
//        if (!ValidateUtil.validatePwd_new(pwdConfirm)) {
//            String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
//            ToastUtil.showInfo(err);
//            return;
//        }
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
        mEtName.setText("");
        mEtPwd.setText("");
        mEtPwdConfirm.setText("");
        ToastUtil.showSuccess("创建成功");
    }

    @Override
    public void onAddFailResult(String s) {

    }


    private void setSpinner(List<RebateKV> list){
        RebateKV rebateKV = new RebateKV();
        rebateKV.setPercent(LanguageUtil.getText("请选择"));
        list.add(0,rebateKV);
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<RebateKV>() {
            @Override
            public Spannable format(RebateKV bean) {
                if(0 == bean.getRebate()) {
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


//    private void setEtListener() {
//        mEtName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String member = editable.toString().trim();
//                if (!ValidateUtil.validatePwd_new(member) && !TextUtils.isEmpty(member)) {
//                    String err = "账号必须为6-16位包含英文与数字组合，区分大小写";
//                    mEtName.setError(err);
//                }
//            }
//        });
//
//        mEtPwd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String password = editable.toString();
//                if (!ValidateUtil.validatePwd_new(password) && !TextUtils.isEmpty(password)) {
//                    String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
//                    mEtPwd.setError(err);
//                }
//            }
//        });
//        mEtPwdConfirm.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String password = editable.toString();
//                if (!ValidateUtil.validatePwd_new(password) && !TextUtils.isEmpty(password)) {
//                    String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
//                    mEtPwdConfirm.setError(err);
//                }
//            }
//        });
//    }
}
