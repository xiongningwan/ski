package com.ski.box.view.activity.money;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.money.DepositBack;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.contract.money.RechargeDetailContract;
import com.ski.box.mvp.presenter.money.RechargeDetailPresenter;
import com.ski.box.mvp.presenter.money.RechargePresenter;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;
import com.yzq.zxinglibrary.encode.CodeCreator;


public class RechargeDetailActivity extends BaseMVPActivity<RechargeDetailContract.Presenter> implements RechargeDetailContract.View, View.OnClickListener {
    public static final String KEY_DEPOSIT_BACK = "KEY_DEPOSIT_BACK";
    private HeaderView mHeadView;
    private TextView mTvNo;
    private TextView mTvMoney;
    private TextView mTvBank;
    private TextView mTvBankNo;
    private TextView mTvOwnerName;
    private Button mBtnCopyNo;
    private Button mBtnCopyMoney;
    private Button mBtnCopyBank;
    private Button mBtnCopyBankNo;
    private Button mBtnCopyOwnerName;
    private Button mBtnCancel;
    private Button mBtnSure;
    private ImageView mIvQrCode;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected RechargeDetailContract.Presenter bindPresenter() {
        return new RechargeDetailPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_money_recharge_detail;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = findViewById(R.id.head_view);
        mTvNo = findViewById(R.id.tv_value_no);
        mTvMoney = findViewById(R.id.tv_value_money);
        mTvBank = findViewById(R.id.tv_value_bank);
        mTvBankNo = findViewById(R.id.tv_value_bank_no);
        mTvOwnerName = findViewById(R.id.tv_value_owner_name);
        mBtnCopyNo = findViewById(R.id.btn_copy_no);
        mBtnCopyMoney = findViewById(R.id.btn_copy_money);
        mBtnCopyBank = findViewById(R.id.btn_copy_bank);
        mBtnCopyBankNo = findViewById(R.id.btn_copy_bank_no);
        mBtnCopyOwnerName = findViewById(R.id.btn_copy_owner_name);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnSure = findViewById(R.id.btn_sure);
        mIvQrCode = findViewById(R.id.iv_qr_code);
        mHeadView.setHeader(getString(R.string.ski_money_recharge), true);

        mBtnCopyNo.setOnClickListener(this);
        mBtnCopyMoney.setOnClickListener(this);
        mBtnCopyBank.setOnClickListener(this);
        mBtnCopyBankNo.setOnClickListener(this);
        mBtnCopyOwnerName.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle bundle) {
        DepositBack bean = getIntent().getParcelableExtra(RechargeDetailActivity.KEY_DEPOSIT_BACK);
        mTvNo.setText(bean.getOrderId());
        mTvMoney.setText(bean.getAmt());
        mTvBank.setText(bean.getBankName());
        mTvBankNo.setText(bean.getPlatformCardNo());
        mTvOwnerName.setText(bean.getPlatformCardName());

        //生成二维码
        createQr(bean);
    }

    private void createQr(DepositBack bean) {
        if(!TextUtils.isEmpty(bean.getQuickCode())) {
            int w = ScreenUtils.dip2px(150);
            Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
            Bitmap bitmap = CodeCreator.createQRCode(bean.getQuickCode(), w, w, logo);
            mIvQrCode.setImageBitmap(bitmap);
        } else {
            mIvQrCode.setVisibility(View.GONE);
        }
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_copy_no) {
            AppUtil.copy(mTvNo.getText().toString().trim(), this);
            ToastUtil.showSuccess("订单号_复制成功!");
        } else if (id == R.id.btn_copy_money) {
            AppUtil.copy(mTvMoney.getText().toString().trim(), this);
            ToastUtil.showSuccess("充值金额_复制成功!");
        } else if (id == R.id.btn_copy_bank) {
            AppUtil.copy(mTvBank.getText().toString().trim(), this);
            ToastUtil.showSuccess("银行_复制成功!");
        } else if (id == R.id.btn_copy_bank_no) {
            AppUtil.copy(mTvBankNo.getText().toString().trim(), this);
            ToastUtil.showSuccess("账号_复制成功!");
        } else if (id == R.id.btn_copy_owner_name) {
            AppUtil.copy(mTvOwnerName.getText().toString().trim(), this);
            ToastUtil.showSuccess("账户_复制成功!");
        } else if (id == R.id.btn_cancel) {
            finish();
        } else if (id == R.id.btn_sure) {
            finish();
        }
    }


}
