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
import com.ski.box.bean.WithdrawRange;
import com.ski.box.bean.user.BankCard;
import com.ski.box.bean.user.User;
import com.ski.box.bean.user.UserInfo;
import com.ski.box.mvp.contract.money.WithdrawContract;
import com.ski.box.mvp.presenter.money.WithdrawPresenter;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.SoftHideKeyBoardUtil2;
import com.ski.box.view.activity.my.UpdateFundPwdActivity;
import com.ski.box.view.view.ClearEditText;
import com.ski.box.view.view.CusTextView;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.spinner.NiceSpinner;
import com.ski.box.view.view.spinner.OnSpinnerItemSelectedListener;
import com.ski.box.view.view.spinner.SpinnerTextFormatter;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.MD5Util;
import com.yb.core.utils.ToastUtil;

import java.util.List;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;


public class WithdrawFragment extends BaseMVPFragment<WithdrawContract.Presenter> implements WithdrawContract.View, View.OnClickListener {
    public static final String KEY_HAS_HEAD = "key_has_head";
    private HeaderView mHeadView;
    private Button mBtnSure;
    private LinearLayout mllBalance;
    private ImageView mIvBalance;
    private TextView mTvBalance;
//    private TextView mTvNickName;
    private CusTextView mTvNotice1;
    private NiceSpinner mSpType;
    private ClearEditText mEtWithdrawMoney;
    private ClearEditText mEtMoneyPwd;
    private ProgressDialog mLoading;
    private WithdrawRange mWithdrawRange;
    private RotateAnimation rotate;
    private int mHasHead;

    public WithdrawFragment() {
    }

    public static WithdrawFragment newInstance(int i) {
        WithdrawFragment fragment = new WithdrawFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_HAS_HEAD, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null) {
            mPresenter.getBankCardList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected WithdrawContract.Presenter bindPresenter() {
        return new WithdrawPresenter(requireActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_money_withdraw;
    }

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        mHeadView = view.findViewById(R.id.head_view);
        mTvBalance = view.findViewById(R.id.tv_balance_value);
//        mTvNickName = view.findViewById(R.id.tv_nick_name);
        mSpType = view.findViewById(R.id.spinner_type);
        mEtWithdrawMoney = view.findViewById(R.id.et_withdraw_money);
        mEtMoneyPwd = view.findViewById(R.id.et_money_pwd);
        mBtnSure = view.findViewById(R.id.btn_sure);
        mllBalance = view.findViewById(R.id.ll_balance);
        mIvBalance = view.findViewById(R.id.iv_refresh_balance);
        mTvNotice1 = view.findViewById(R.id.tv_notice_1);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_money_withdraw)), false);

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
//        mTvNickName.setText(user.getAlias());
        createAnim();
        setRedTip();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getBankCardList();
        mPresenter.getWithdrawRange();
    }

    @Override
    protected void loadData() {

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
        if (0 == DataCenter.getInstance().getUser().getHavefundPwd()) {
            ToastUtil.showInfo("请先设置资金密码");
            startActivity(new Intent(requireActivity(), UpdateFundPwdActivity.class));
            return;
        }

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

        if (0 == mSpType.getSelectedIndex()) {
            ToastUtil.showError("请选择银行卡");
            return;
        }

        BankCard bankCard = (BankCard) mSpType.getSelectedItem();
        if (bankCard == null) {
            ToastUtil.showError("获取提现方式失败");
            return;
        }

        if (mWithdrawRange == null) {
            ToastUtil.showError("获取提现区间失败");
            return;
        }
        double inputMoney_D = Double.parseDouble(inputMoney);

        if (inputMoney_D > mWithdrawRange.getMaxAmt().doubleValue() || inputMoney_D < mWithdrawRange.getMinAmt().doubleValue()) {
            String str = LanguageUtil.getText("提现区间");
            ToastUtil.showError( str + mWithdrawRange.getMinAmt().toPlainString() + "~" + mWithdrawRange.getMaxAmt().toPlainString());
            return;
        }
        mBtnSure.setEnabled(false);
        mLoading.show();
        pwd = MD5Util.md5Password(pwd);
        mPresenter.withdraw(bankCard.getCardId(), inputMoney, pwd);
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
    public void onRangeResult(WithdrawRange withdrawRange) {
        mWithdrawRange = withdrawRange;
        mEtWithdrawMoney.setHint("" + mWithdrawRange.getMinAmt().toPlainString() + "~" + mWithdrawRange.getMaxAmt().toPlainString());
    }

    @Override
    public void onTypeResult(List<BankCard> list) {

        setSpinner(list);
    }

    @Override
    public void onSuccessResult() {
        mEtMoneyPwd.setText("");
        mEtWithdrawMoney.setText("");
        mSpType.setSelectedIndex(0);
        ToastUtil.showInfo("提现成功");
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    @Override
    public void onFailResult(String s) {
        mBtnSure.setEnabled(true);
        mLoading.dismiss();
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mTvBalance.setText(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
//        mTvNickName.setText(user.getAlias());
    }


    private void setSpinner(List<BankCard> list) {
        BankCard bankCard = new BankCard();
        bankCard.setBankName(LanguageUtil.getText("请选择"));
        list.add(0, bankCard);
        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<BankCard>() {
            @Override
            public Spannable format(BankCard bean) {
                return new SpannableString(bean.getBankName() + " " + bean.getCardNo());
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


    private void setRedTip() {
        String tipTop = "<p style=\"text-align: justify;\">1.提现服务时间为<span style=\"color:#e74c3c\">24小时</span>。<br />\n" +
                "2.同姓名同卡号等同账号，每日提现次数上限为<span style=\"color:#e74c3c\">20次</span>。<br /><br />\n" +
                "3.提现需进行充值金额满<span style=\"color:#e74c3c\">100%的投注</span>，若未满足消费流水将无法成功提现。<br /><br />\n" +
                "&nbsp; &nbsp;(例，<span style=\"color:#e74c3c\">充值100金币</span>，需要<span style=\"color:#e74c3c\">投注100金币</span>后方能进行提现。)<br /><br />\n" +
                "4.银行卡请务必正确填写开户银行和银行卡号码、持卡人姓名。<br /><br />\n" +
                "5.当您提现申请完成后，我们将为您提供1分钟快速到账的提款服务。</p>";
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            tipTop = "<p>1.CSKH phục vụ qu&yacute; kh&aacute;ch r&uacute;t tiền <span style=\"color:#e74c3c\">24/24</span> .<br />\n" +
                    "<br />\n" +
                    "2.Một thẻ ng&acirc;n h&agrave;ng tr&ugrave;ng với t&ecirc;n đ&atilde; đăng k&iacute; mỗi ng&agrave;y đều c&oacute; thể r&uacute;t tối đa<span style=\"color:#e74c3c\"> 20</span> lần .&nbsp;<br />\n" +
                    "<br />\n" +
                    "3.Điều kiện để r&uacute;t tiền cần đặt cược đủ số tiền đ&atilde; nạp ,nếu kh&ocirc;ng đủ sẽ kh&ocirc;ng thể r&uacute;t tiền . <br />(v&iacute; dụ : <span style=\"color:#e74c3c\">nạp 100k </span>, đặt cược đủ <span style=\"color:#e74c3c\">100k</span> sau đ&oacute; y&ecirc;u cầu r&uacute;t tiền sẽ th&ocirc;ng qua ).<br />\n" +
                    "<br />\n" +
                    "4.Thẻ ng&acirc;n h&agrave;ng bắt buộc tr&ugrave;ng với t&ecirc;n chủ thẻ đ&atilde; đăng k&iacute; v&agrave; sử dụng t&ecirc;n thật để r&uacute;t tiền.<br />\n" +
                    "<br />\n" +
                    "5.Sau khi y&ecirc;u cầu r&uacute;t tiền ho&agrave;n th&agrave;nh , tiền sẽ đến t&agrave;i khoản của hội vi&ecirc;n trong v&ograve;ng 1 ph&uacute;t.&nbsp;</p>";
        }
        Spanned sdpTop ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdpTop = Html.fromHtml(tipTop,Html.FROM_HTML_MODE_LEGACY);
        } else {
            sdpTop = Html.fromHtml(tipTop);
        }

        mTvNotice1.setText(sdpTop);
    }

}
