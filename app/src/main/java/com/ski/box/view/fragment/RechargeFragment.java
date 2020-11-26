package com.ski.box.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
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
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.mvp.contract.money.RechargeContract;
import com.ski.box.mvp.presenter.money.RechargePresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.KeyboardChangeListener;
import com.ski.box.utils.SoftHideKeyBoardUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil2;
import com.ski.box.view.activity.money.RechargeDetailActivity;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.CusTextView;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.spinner.NiceSpinner;
import com.ski.box.view.view.spinner.OnSpinnerItemSelectedListener;
import com.ski.box.view.view.spinner.SpinnerTextFormatter;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ToastUtil;


import java.util.List;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

public class RechargeFragment extends BaseMVPFragment<RechargeContract.Presenter> implements RechargeContract.View, View.OnClickListener{
    public static final String KEY_HAS_HEAD = "key_has_head";
    private HeaderView mHeadView;
    private Button mBtnSure;
    private LinearLayout mllBalance;
    private ImageView mIvBalance;
    private TextView mTvBalance;
    private TextView mTvNickName;
    private NiceSpinner mSpType;
    private ClearEditText mEtCardName;
    private ClearEditText mEtMoney;
    private CusTextView mTvNotice1;
    private CusTextView mTvTip;
    private ProgressDialog mLoading;
    private RotateAnimation rotate;
    private int mHasHead;
    private View mRootView;
    private KeyboardChangeListener mKeyboardChangeListener;

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
        mEtCardName = view.findViewById(R.id.et_card_name);
        mEtMoney = view.findViewById(R.id.et_money);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mllBalance = view.findViewById(R.id.ll_balance);
        mIvBalance = view.findViewById(R.id.iv_refresh_balance);
        mTvNotice1 = view.findViewById(R.id.tv_notice_1);
        mTvTip = view.findViewById(R.id.tv_tip);
        mRootView = view.findViewById(R.id.recharge_root);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_tab_lottery_recharge)), false);

        mBtnSure.setOnClickListener(this);
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
//                SoftHideKeyBoardUtil.assistActivity(requireActivity());
            } else {
                ImmersionBar.with(this).statusBarColor(R.color.ski_color_FAFAFA).statusBarDarkFont(true).init();
                SoftHideKeyBoardUtil2.assistActivity(requireActivity());
            }
        }

        User user = DataCenter.getInstance().getUser();
        mTvBalance.setText(user.getBalance());
        mTvNickName.setText(user.getAlias());
        createAnim();
        setRedTip();
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
        String cardName = mEtCardName.getText().toString().trim();
        if (TextUtils.isEmpty(inputMoney)) {
            ToastUtil.showError("请输入金额");
            return;
        }
        if (TextUtils.isEmpty(cardName)) {
            ToastUtil.showError("请输入真实姓名");
            return;
        }

        if (0 == mSpType.getSelectedIndex()) {
            ToastUtil.showError("选择充值方式");
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
            ToastUtil.showError(LanguageUtil.getText("该渠道充值范围为") + minD + "~" + maxD);
            return;
        }
        mBtnSure.setEnabled(false);
        mLoading.show();
        mPresenter.deposit(payType.getChannelCode(), cardName, inputMoney);
    }

    @Override
    public void onUserInfoResult(UserInfo userInfo) {
        DataCenter.getInstance().setUser(userInfo);
        mTvBalance.setText(userInfo.getBalance());
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
        setSpinner(list);
    }

    @Override
    public void onDepositSuccessResult(DepositBack bean) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
        Intent intent = new Intent(requireActivity(), RechargeDetailActivity.class);
        intent.putExtra(RechargeDetailActivity.KEY_DEPOSIT_BACK, bean);
        startActivity(intent);

        mSpType.setSelectedIndex(0);
        mEtMoney.setText("");
        mEtCardName.setText("");
        mEtMoney.setHint("请输入充值金额");
    }

    @Override
    public void onDepositFailResult(String s) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    private void setSpinner(List<PayType> list) {
        PayType payType2 = new PayType();
        payType2.setChannelName(LanguageUtil.getText("请选择"));
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
                if (0 == position) {
                    mEtMoney.setHint(LanguageUtil.getText("请输入充值金额"));
                } else {
                    if (payType != null) {
                        mEtMoney.setHint( LanguageUtil.getText("充值区间")+ "：" + payType.getMinAmt() + "~" + payType.getMaxAmt());
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


    private void setRedTip() {
//        String tip1 = getString(R.string.ski_money_recharge_notice1);
//        String tip2 = getString(R.string.ski_money_recharge_notice2);

//        ActivityUtil.setTipKeywordRed(requireActivity(), mTvNotice1, LanguageUtil.getText(tip1), LanguageUtil.getText("在线客服"));
//        ActivityUtil.setTipKeywordRed(requireActivity(), mTvNotice2, LanguageUtil.getText(tip2),  LanguageUtil.getText("24小时"));

        String tipTop = "<p><span style=\"color:#e74c3c\">温馨提示：<br />\n" +
                "为了您的资金能快速到账，请填入正确的充值人姓名，以免延迟到账或充值失败。</span></p>";
        String tipBottom = "<p>1.充值服务时间为<span style=\"color:#e74c3c\">24小时</span>。<br /><br />\n" +
                "2.充值成功后请及时查看资金进度查询。<br /><br />\n" +
                "3.若有问题请及时联系<span style=\"color:#e74c3c\">在线客服</span>。<br /><br />\n" +
                "4.如充值遇到银行充值页无法显示问题，请尝试使用<span style=\"color:#e74c3c\">网银推荐的浏览器</span></p>";
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            tipTop = "<p><span style=\"color:#e74c3c\">Lưu ý：<br />\n" +
                    "Để quá trình chuyển khoản được nhanh , vui lòng nhập đúng họ tên người nhận tránh tình trạng nạp tiền chậm hoặc nạp tiền thất bại  </span></p>\n";

            tipBottom = "<p>1.CSKH nạp tiền phục vụ <span style=\"color:#e74c3c\">24/24</span>。<br /><br />\n" +
                    "2.Sau khi nạp tiền thành công vui lòng kiểm tra ví tiền của bạn <br /><br />\n" +
                    "3.Nếu có vấn đề xin vui lòng liên hệ  <span style=\"color:#e74c3c\">CSKH</span>。<br /><br />\n" +
                    "4.Nếu trong quá trình nạp tiền phát sinh vấn đề , vui lòng sử dụng <span style=\"color:#e74c3c\">trình duyệt đề xuất chuyển khoản</span></p>";
        }

        Spanned sdpTop ;
        Spanned sdpBottom ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdpTop = Html.fromHtml(tipTop,Html.FROM_HTML_MODE_LEGACY);
        } else {
            sdpTop = Html.fromHtml(tipTop);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdpBottom = Html.fromHtml(tipBottom,Html.FROM_HTML_MODE_LEGACY);
        } else {
            sdpBottom = Html.fromHtml(tipBottom);
        }
        mTvTip.setText(sdpTop);
        mTvNotice1.setText(sdpBottom);
    }

}
