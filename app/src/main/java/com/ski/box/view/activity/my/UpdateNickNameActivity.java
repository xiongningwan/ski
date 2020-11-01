package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.UpdateAliasContract;
import com.ski.box.mvp.presenter.UpdateAliasPresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.CleanLeakUtils;
import com.yb.core.utils.ToastUtil;

import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;


public class UpdateNickNameActivity extends BaseMVPActivity<UpdateAliasContract.Presenter> implements UpdateAliasContract.View, View.OnClickListener {
    HeaderView mHeadView;
    ClearEditText mEtNickname;
    Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected UpdateAliasContract.Presenter bindPresenter() {
        return new UpdateAliasPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_update_nickname;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mEtNickname = findViewById(R.id.et_nickname);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_my_nick_name), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        User user = DataCenter.getInstance().getUser();
        if(!TextUtils.isEmpty(user.getAlias())) {
            mEtNickname.setText(user.getAlias());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_sure) {
            doUpdate();
        }
    }

    private void doUpdate() {
        String modifyContent = mEtNickname.getText().toString().trim();
        if (TextUtils.isEmpty(modifyContent)) {
            ToastUtil.showWarning("请输入修改内容");
            return;
        }
        if (modifyContent.length() < 2 || modifyContent.length() > 10) {
            ToastUtil.showWarning("会员昵称长度2~10位，可以输入任意字符");
            return;
        }

        mPresenter.updateAlias(modifyContent);
    }

    @Override
    public void onSuccessResult() {
        String modifyContent = mEtNickname.getText().toString().trim();
        DataCenter.getInstance().getUser().setAlias(modifyContent);
        ToastUtil.showSuccess("修改成功!");
        RxBus.get().post(EVENT_TYPE_USER_NAME_NICK_NAME, "");
        finish();

    }

    @Override
    public void onFailResult(String s) {
        ToastUtil.showError("修改失败!");
    }
}
