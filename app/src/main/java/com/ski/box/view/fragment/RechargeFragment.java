package com.ski.box.view.fragment;

import android.app.ProgressDialog;
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
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.money.DepositBack;
import com.ski.box.bean.money.PayType;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.HallContract;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.mvp.presenter.HallPresenter;
import com.ski.box.mvp.presenter.money.RechargePresenter;
import com.ski.box.view.activity.money.RechargeDetailActivity;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.dialog.LoadingDialog;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.ToastUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.PUT;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

public class RechargeFragment extends BaseMVPFragment<RechargeContract.Presenter> implements RechargeContract.View, View.OnClickListener {
    public static final String KEY_HAS_HEAD = "key_has_head";
    private HeaderView mHeadView;
    private Button mBtnSure;
    private LinearLayout mllBalance;
    private ImageView mIvBalance;
    private TextView mTvBalance;
    private TextView mTvNickName;
    private NiceSpinner mSpType;
    private ClearEditText mEtMoney;
    private TextView mTvWen1;
    private TextView mTvWen2;
    private ProgressDialog mLoading;
    private RotateAnimation rotate;
    private int mHasHead;
    private List<PayType> mTypeList = new ArrayList<>();

    public RechargeFragment() {
    }

    public static RechargeFragment newInstance(int i) {
        RechargeFragment fragment = new RechargeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_HAS_HEAD, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected RechargeContract.Presenter bindPresenter() {
        return new RechargePresenter(requireActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_recharge;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mHeadView = view.findViewById(R.id.head_view);
        mTvBalance = view.findViewById(R.id.tv_balance_value);
        mTvNickName = view.findViewById(R.id.tv_nick_name);
        mSpType = view.findViewById(R.id.spinner_type);
        mEtMoney = view.findViewById(R.id.et_money);
        mTvWen1 = view.findViewById(R.id.tv_wen_1);
        mTvWen2 = view.findViewById(R.id.tv_wen_2);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mllBalance = view.findViewById(R.id.ll_balance);
        mIvBalance = view.findViewById(R.id.iv_refresh_balance);
        mHeadView.setHeader(getString(R.string.ski_tab_lottery_recharge), false);

        mBtnSure.setOnClickListener(this);
        mTvWen1.setOnClickListener(this);
        mTvWen2.setOnClickListener(this);
        mllBalance.setOnClickListener(this);
        mLoading = new ProgressDialog(requireActivity());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mHasHead = bundle.getInt(KEY_HAS_HEAD, 0);
            if (1 == mHasHead) {
                mHeadView.setVisibility(View.GONE);
            }
        }

        User user = DataCenter.getInstance().getUser();
        mTvBalance.setText("￥" + user.getBalance());
        mTvNickName.setText(user.getAlias());
        createAnim();
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        mPresenter.getPayType(0);
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
        String inputMoney = mEtMoney.getText().toString().trim();
        if (TextUtils.isEmpty(inputMoney)) {
            ToastUtil.showError("请输入金额");
            return;
        }

        if (0 == mSpType.getSelectedIndex()) {
            ToastUtil.showError("请选择渠道");
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
            ToastUtil.showError("该渠道充值范围为" + minD + "~" + maxD);
            return;
        }
        mBtnSure.setEnabled(false);
        mLoading.show();
        mPresenter.deposit(payType.getChannelCode(), inputMoney);
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
    public void onTypeResult(List<PayType> list) {
        mTypeList.clear();
        mTypeList.addAll(list);
        setSpinner(list);
    }

    @Override
    public void onDepositSuccessResult(DepositBack bean) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
        Intent intent = new Intent(requireActivity(), RechargeDetailActivity.class);
        intent.putExtra(RechargeDetailActivity.KEY_DEPOSIT_BACK, bean);
        startActivity(intent);

        if (mSpType != null && mTypeList != null) {
            setSpinner(mTypeList);
        }
        mEtMoney.setText("");
        mEtMoney.setHint("请输入充值金额");
    }

    @Override
    public void onDepositFailResult(String s) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    private void setSpinner(List<PayType> list) {
        PayType payType2 = new PayType();
        payType2.setChannelName("请选择");
        list.add(0, payType2);

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
                if(0 == position) {
                    mEtMoney.setHint("请输入充值金额");
                } else {
                    if (payType != null) {
                        mEtMoney.setHint("充值区间：" + payType.getMinAmt() + "~" + payType.getMaxAmt());
                    }
                }

            }
        });
        mSpType.attachDataSource(list);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mTvBalance.setText(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mTvNickName.setText(user.getAlias());
    }

}
