package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.UpdateAliasContract;
import com.ski.box.mvp.contract.UpdateLoginPwdContract;
import com.ski.box.mvp.presenter.UpdateAliasPresenter;
import com.ski.box.mvp.presenter.UpdateLoginPwdPresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;


public class UpdateLoginPwdActivity extends BaseMVPActivity<UpdateLoginPwdContract.Presenter> implements UpdateLoginPwdContract.View, View.OnClickListener {
    HeaderView mHeadView;
    ClearEditText mEtOld;
    ClearEditText mEtNew;
    ClearEditText mEtConfirm;
    Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected UpdateLoginPwdContract.Presenter bindPresenter() {
        return new UpdateLoginPwdPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_update_login_pwd;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtOld = findViewById(R.id.et_old);
        mEtNew = findViewById(R.id.et_new);
        mEtConfirm = findViewById(R.id.et_new_confirm);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_login_pwd), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
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
        if (TextUtils.isEmpty(pwdOld) ||  TextUtils.isEmpty(pwdNew)|| TextUtils.isEmpty(pwdConfirm)) {
            ToastUtil.showWarning("输入框不能为空");
            return;
        }

        if (!pwdNew.equals(pwdConfirm)) {
            ToastUtil.showWarning("新密码两次输入不一致");
            return;
        }

        if (pwdNew.length() > 20 || pwdNew.length() < 6) {
            ToastUtil.showWarning("密码长度必须为6到20个字符");
            return;
        }
        pwdOld = MD5Util.md5Password(pwdOld);
        pwdNew = MD5Util.md5Password(pwdNew);
        mPresenter.updateLoginPwd(pwdOld,pwdNew);
    }

    @Override
    public void onSuccessResult() {
        ToastUtil.showSuccess("修改成功!");
        finish();
    }

    @Override
    public void onFailResult(String s) {
        ToastUtil.showError("修改失败!");
    }
}
