package com.ski.box.view.activity.money;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.Bank;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.User;
import com.ski.box.mvp.contract.BankCardContract;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.presenter.BankCardPresenter;
import com.ski.box.mvp.presenter.money.RechargePresenter;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.LoadingDialog;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.List;


public class RechargeActivity extends BaseMVPActivity<RechargeContract.Presenter> implements RechargeContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private Button mBtnSure;
    private TextView mTvBalance;
    private TextView mTvNickName;
    private NiceSpinner mSpType;
    private ClearEditText mEtMoney;
    private TextView mTvWen1;
    private TextView mTvWen2;
    private LoadingDialog mLoading;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected RechargeContract.Presenter bindPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_money_recharge;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mTvBalance = findViewById(R.id.tv_balance_value);
        mTvNickName = findViewById(R.id.tv_nick_name);
        mSpType = findViewById(R.id.spinner_type);
        mEtMoney = findViewById(R.id.et_money);
        mTvWen1 = findViewById(R.id.tv_wen_1);
        mTvWen2 = findViewById(R.id.tv_wen_2);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(getString(R.string.ski_money_recharge), true);

        mBtnSure.setOnClickListener(this);
        mTvWen1.setOnClickListener(this);
        mTvWen2.setOnClickListener(this);
        mLoading = new LoadingDialog(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        User user = DataCenter.getInstance().getUser();
        mTvBalance.setText("￥" + user.getBalance());
        mTvNickName.setText(user.getAlias());
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getPayType(0);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
            goToDeposit();
        } else if (id == R.id.tv_wen_1) {
            //goToDeposit();
        } else if (id == R.id.tv_wen_2) {
           // goToDeposit();
        }
    }


    private void goToDeposit() {
        String inputMoney = mEtMoney.getText().toString().trim();
        if (TextUtils.isEmpty(inputMoney)) {
            ToastUtil.showError("请输入金额");
            return;
        }

        PayType payType = (PayType) mSpType.getSelectedItem();
        if (payType == null) {
            ToastUtil.showError("获取充值渠道失败");
            return;
        }
        double inputMoneyD = 0;
        double minD = 0;
        double maxD = 0;
        try {
            inputMoneyD = Double.parseDouble(inputMoney);
            minD = Double.parseDouble(payType.getMinAmt());
            maxD = Double.parseDouble(payType.getMaxAmt());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (inputMoneyD < minD || inputMoneyD > maxD) {
            ToastUtil.showError("该渠道充值范围为 minD~maxD");
            return;
        }
        mBtnSure.setEnabled(false);
        mLoading.show();
        mPresenter.deposit(payType.getChannelCode(),inputMoney);
    }

    @Override
    public void onTypeResult(List<PayType> list) {
        setSpinner(list);
    }

    @Override
    public void onDepositSuccessResult(DepositBack bean) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
        Intent intent = new Intent(this, RechargeDetailActivity.class);
        intent.putExtra(RechargeDetailActivity.KEY_DEPOSIT_BACK, bean);
        startActivity(intent);
    }

    @Override
    public void onDepositFailResult(String s) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    private void setSpinner(List<PayType> list) {
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<PayType>() {
            @Override
            public Spannable format(PayType bean) {
                return new SpannableString(bean.getChannelName());
            }
        };

        mSpType.setSpinnerTextFormatter(textFormatter);
        mSpType.setSelectedTextFormatter(textFormatter);
        mSpType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                PayType payType = (PayType) parent.getSelectedItem();
                if (payType != null) {
                    mEtMoney.setHint("充值区间："+ payType.getMinAmt()+ "~" + payType.getMaxAmt());
                }
            }
        });
        mSpType.attachDataSource(list);
    }
}
