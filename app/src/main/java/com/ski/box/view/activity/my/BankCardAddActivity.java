package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.user.Bank;
import com.ski.box.mvp.contract.my.BankCardAddContract;
import com.ski.box.mvp.presenter.my.BankCardAddPresenter;
import com.ski.box.view.view.spinner.NiceSpinner;
import com.ski.box.view.view.spinner.OnSpinnerItemSelectedListener;
import com.ski.box.view.view.spinner.SpinnerTextFormatter;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.ToastUtil;


import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BIND_BANK_CARD_SUCCESS;


public class BankCardAddActivity extends BaseMVPActivity<BankCardAddContract.Presenter> implements BankCardAddContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private NiceSpinner mSpinnerBankName;
    private ClearEditText mEtPoint;
    private ClearEditText mEtOwnerName;
    private ClearEditText mEtBankNo;
    private ClearEditText mEtBankNoConfirm;
    //    private ClearEditText mEtFundPwd;
    private Button mBtnSure;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected BankCardAddContract.Presenter bindPresenter() {
        return new BankCardAddPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_bank_card_add;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).keyboardEnable(true).init();
        mHeadView = findViewById(R.id.head_view);
        mSpinnerBankName = findViewById(R.id.spinner_bank_name);
        mEtPoint = findViewById(R.id.et_point);
        mEtOwnerName = findViewById(R.id.et_owner_name);
        mEtBankNo = findViewById(R.id.et_bank_no);
        mEtBankNoConfirm = findViewById(R.id.et_bank_no_confirm);
//        mEtFundPwd = findViewById(R.id.et_fund_pwd);
        mBtnSure = findViewById(R.id.btn_sure);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_my_bank_card_add)), true);

        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getBankList();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_sure) {
            doBind();
        }
    }


    private void doBind() {
        if (0 == mSpinnerBankName.getSelectedIndex()) {
            ToastUtil.showError(LanguageUtil.getText("请选择银行"));
            return;
        }
        Bank bank = (Bank) mSpinnerBankName.getSelectedItem();
        if (bank == null) {
            ToastUtil.showError(LanguageUtil.getText("请选择银行"));
            return;
        }
        String point = mEtPoint.getText().toString().trim();
        String ownerName = mEtOwnerName.getText().toString().trim();
        String bankNo = mEtBankNo.getText().toString().trim();
        String bankNoConfirm = mEtBankNoConfirm.getText().toString().trim();
//        String funPwd = mEtFundPwd.getText().toString().trim();
        if (TextUtils.isEmpty(point) || TextUtils.isEmpty(ownerName) || TextUtils.isEmpty(bankNo) || TextUtils.isEmpty(bankNoConfirm)) {
            ToastUtil.showInfo(LanguageUtil.getText("输入框不能为空"));
            return;
        }
        if (!bankNo.equals(bankNoConfirm)) {
            ToastUtil.showInfo(LanguageUtil.getText("两次输入银行卡号不一致"));
            return;
        }
        mBtnSure.setEnabled(false);
        mBtnSure.setClickable(false);
        mPresenter.bindBank(String.valueOf(bank.getBankCode()), bank.getBankName(), point, ownerName, bankNo, bankNoConfirm);
    }


    @Override
    public void onSuccessResult(List<Bank> list) {
        setSpinner(list);
    }

    @Override
    public void onFailResult(String s) {
    }

    @Override
    public void onBindSuccessResult() {
        mBtnSure.setEnabled(true);
        mBtnSure.setClickable(true);
        RxBus.get().post(EVENT_BIND_BANK_CARD_SUCCESS, "");
        ToastUtil.showSuccess(LanguageUtil.getText("绑定成功!"));
        finish();
    }

    @Override
    public void onBindFailResult(String s) {
        mBtnSure.setEnabled(true);
        mBtnSure.setClickable(true);
    }


    private void setSpinner(List<Bank> list) {
        Bank bank = new Bank();
        bank.setBankName(LanguageUtil.getText("请选择"));
        list.add(0, bank);

        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<Bank>() {
            @Override
            public Spannable format(Bank bank) {
                return new SpannableString(bank.getBankName());
            }
        };

        mSpinnerBankName.setSpinnerTextFormatter(textFormatter);
        mSpinnerBankName.setSelectedTextFormatter(textFormatter);
        mSpinnerBankName.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // Bank bank = (Bank) parent.getSelectedItem();
            }
        });
        mSpinnerBankName.attachDataSource(list);
    }
}
