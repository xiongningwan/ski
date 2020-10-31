package com.ski.box.view.activity.money;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.contract.money.WithdrawContract;
import com.ski.box.mvp.presenter.money.RechargePresenter;
import com.ski.box.mvp.presenter.money.WithdrawPresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.LoadingDialog;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;


public class WithdrawActivity extends BaseMVPActivity<WithdrawContract.Presenter> implements WithdrawContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private Button mBtnSure;
    private LinearLayout mllBalance;
    private ImageView mIvBalance;
    private TextView mTvBalance;
    private TextView mTvNickName;
    private NiceSpinner mSpType;
    private ClearEditText mEtWithdrawMoney;
    private ClearEditText mEtMoneyPwd;
    private TextView mTvWen1;
    private TextView mTvWen2;
    private LoadingDialog mLoading;
    RotateAnimation rotate;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected WithdrawContract.Presenter bindPresenter() {
        return new WithdrawPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_money_withdraw;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mTvBalance = findViewById(R.id.tv_balance_value);
        mTvNickName = findViewById(R.id.tv_nick_name);
        mSpType = findViewById(R.id.spinner_type);
        mEtWithdrawMoney = findViewById(R.id.et_withdraw_money);
        mEtMoneyPwd = findViewById(R.id.et_money_pwd);
        mTvWen1 = findViewById(R.id.tv_wen_1);
        mTvWen2 = findViewById(R.id.tv_wen_2);
        mBtnSure = findViewById(R.id.btn_sure);
        mllBalance = findViewById(R.id.ll_balance);
        mIvBalance = findViewById(R.id.iv_refresh_balance);
        mHeadView.setHeader(getString(R.string.ski_money_withdraw), true);

        mBtnSure.setOnClickListener(this);
        mTvWen1.setOnClickListener(this);
        mTvWen2.setOnClickListener(this);
        mllBalance.setOnClickListener(this);
        mLoading = new LoadingDialog(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        User user = DataCenter.getInstance().getUser();
        mTvBalance.setText("￥" + user.getBalance());
        mTvNickName.setText(user.getAlias());
        createAnim();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getBankCardList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
            goToDeposit();
        } else if (id == R.id.ll_balance) {
            refreshBalance();
        } else if (id == R.id.tv_wen_1) {
            //goToDeposit();
        } else if (id == R.id.tv_wen_2) {
            // goToDeposit();
        }
    }

    private void refreshBalance() {
        mllBalance.setEnabled(false);
        mIvBalance.setAnimation(rotate);
        mPresenter.getBalance();
    }

    private void createAnim() {
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(1000);//设置动画持续周期
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotate.setStartOffset(10);//执行前的等待时间
    }


    private void goToDeposit() {
        String inputMoney = mEtWithdrawMoney.getText().toString().trim();
        if (TextUtils.isEmpty(inputMoney)) {
            ToastUtil.showError("请输入金额");
            return;
        }

        String pwd = mEtMoneyPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showError("请输入资金密码");
            return;
        }

        BankCard bankCard = (BankCard) mSpType.getSelectedItem();
        if (bankCard == null) {
            ToastUtil.showError("获取收款银行卡失败");
            return;
        }
        mBtnSure.setEnabled(false);
        mLoading.show();
        pwd = MD5Util.md5Password(pwd);
        mPresenter.withdraw(bankCard.getCardNo(), inputMoney, pwd);
    }

    @Override
    public void onUserInfoResult(UserInfo userInfo) {
        DataCenter.getInstance().setUser(userInfo);
        mTvBalance.setText("￥" + userInfo.getBalance());
        mllBalance.setEnabled(true);
        mIvBalance.clearAnimation();
    }

    @Override
    public void onUserInfoFailResult(String s) {
        mllBalance.setEnabled(true);
        mIvBalance.clearAnimation();
    }

    @Override
    public void onTypeResult(List<BankCard> list) {
        setSpinner(list);
    }

    @Override
    public void onSuccessResult() {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    @Override
    public void onFailResult(String s) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }


    private void setSpinner(List<BankCard> list) {
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<BankCard>() {
            @Override
            public Spannable format(BankCard bean) {
                return new SpannableString(bean.getBankName());
            }
        };

        mSpType.setSpinnerTextFormatter(textFormatter);
        mSpType.setSelectedTextFormatter(textFormatter);
        mSpType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
            }
        });
        mSpType.attachDataSource(list);
    }


}