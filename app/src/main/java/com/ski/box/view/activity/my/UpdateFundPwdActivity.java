package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.UpdateFundPwdContract;
import com.ski.box.mvp.contract.UpdateLoginPwdContract;
import com.ski.box.mvp.presenter.UpdateFundPwdPresenter;
import com.ski.box.mvp.presenter.UpdateLoginPwdPresenter;
import com.ski.box.utils.ValidateUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_FUND_PWD_UPDATE;


public class UpdateFundPwdActivity extends BaseMVPActivity<UpdateFundPwdContract.Presenter> implements UpdateFundPwdContract.View, View.OnClickListener {
    HeaderView mHeadView;
    ClearEditText mEtOld;
    ClearEditText mEtNew;
    ClearEditText mEtConfirm;
    Button mBtnSure;
    private User mUser;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected UpdateFundPwdContract.Presenter bindPresenter() {
        return new UpdateFundPwdPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_update_fund_pwd;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtOld = findViewById(R.id.et_old);
        mEtNew = findViewById(R.id.et_new);
        mEtConfirm = findViewById(R.id.et_new_confirm);
        mBtnSure = findViewById(R.id.btn_sure);

        String title = "";
         mUser = DataCenter.getInstance().getUser();
        if(0 == mUser.getHavefundPwd()) {
            title = getString(R.string.ski_my_money_pwd_set);
            mEtOld.setVisibility(View.GONE);
        } else {
            title = getString(R.string.ski_my_money_pwd_update);
        }
        mHeadView.setHeader(title, true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
//        mEtOld.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//        mEtOld.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
//        mEtNew.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//        mEtNew.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
//        mEtConfirm.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//        mEtConfirm.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        setEtListener();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_sure) {
            doUpdate();
        }
    }

    private void doUpdate() {
        String pwdOld = mEtOld.getText().toString().trim();
        String pwdNew = mEtNew.getText().toString().trim();
        String pwdConfirm = mEtConfirm.getText().toString().trim();
        if(0 == mUser.getHavefundPwd()) {
            if (TextUtils.isEmpty(pwdNew)|| TextUtils.isEmpty(pwdConfirm)) {
                ToastUtil.showWarning("输入框不能为空");
                return;
            }
        } else {
            if (TextUtils.isEmpty(pwdOld) ||  TextUtils.isEmpty(pwdNew)|| TextUtils.isEmpty(pwdConfirm)) {
                ToastUtil.showWarning("输入框不能为空");
                return;
            }
        }

        if (!pwdNew.equals(pwdConfirm)) {
            ToastUtil.showWarning("新密码两次输入不一致");
            return;
        }
        if (!ValidateUtil.validatePwd_new(pwdNew)) {
            String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
            ToastUtil.showInfo(err);
            return;
        }
        if(0 == mUser.getHavefundPwd()) {
        } else {
            pwdOld = MD5Util.md5Password(pwdOld);
        }

        pwdNew = MD5Util.md5Password(pwdNew);
        mPresenter.updateFundPwd(pwdOld,pwdNew);
    }

    @Override
    public void onSuccessResult() {
        ToastUtil.showSuccess("修改成功!");
        mUser.setHavefundPwd(1);
        RxBus.get().post(EVENT_FUND_PWD_UPDATE,"");
        finish();
    }

    @Override
    public void onFailResult(String s) {
        ToastUtil.showError("修改失败!");
    }



    private void setEtListener() {
        mEtNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                if (!ValidateUtil.validatePwd_new(password)) {
                    String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
                    mEtNew.setError(err);
                }
            }
        });
        mEtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                if (!ValidateUtil.validatePwd_new(password)) {
                    String err = "密码必须为6-16位包含英文与数字组合，区分大小写";
                    mEtConfirm.setError(err);
                }
            }
        });
    }
}
