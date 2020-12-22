package com.ski.box.view.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.mvp.contract.RecordBetContract;
import com.ski.box.mvp.presenter.RecordBetPresenter;
import com.ski.box.utils.DecimalSetUtils;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.LotteryResultView;
import com.ski.box.view.view.dialog.CancelDialog;
import com.yb.core.base.BaseMVPActivity;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ToastUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_RECORD_CANCEL_SUCCESS;

public class RecordDetailActivity extends BaseMVPActivity<RecordBetContract.Presenter> implements RecordBetContract.View, View.OnClickListener {
    public static final String KEY_RECORD_BEAN = "key_record_bean";
    private HeaderView mHeadView;
    private TextView mTvName;
    private TextView mTvPeriod;
    private LotteryResultView mLotteryResultView;
    private TextView mTvOpen;
    private TextView mTvNo;
    private Button mBtnCopy;
    private TextView mTvTime;
    private TextView mTvPlay;
//    private TextView mTvContent;
    private TextView tvPlayItemName;
    private ImageView ivPlayItemName;
    private ImageView ivPlayItemName2;
    private ImageView ivPlayItemName3;
    private TextView mTvBetMoney;
    private TextView mTvRate;
    private TextView mTvBetWin;
    private TextView mTvStatus;
    private Button mBtnCancel;
    private RecordBet.ListBean mBean;
    private CancelDialog mCancelDialog;

    @Override
    protected RecordBetContract.Presenter bindPresenter() {
        return new RecordBetPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_record_detail;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mTvName = findViewById(R.id.tv_name);
        mTvPeriod = findViewById(R.id.tv_period);
        mLotteryResultView = findViewById(R.id.lottery_result_view);
        mTvOpen = findViewById(R.id.tv_open);
        mTvNo = findViewById(R.id.tv_value_no);
        mBtnCopy = findViewById(R.id.btn_copy);
        mTvTime = findViewById(R.id.tv_value_time);
        mTvPlay = findViewById(R.id.tv_value_play);
//        mTvContent = findViewById(R.id.tv_value_content);
        tvPlayItemName = findViewById(R.id.tv_item_play_name);
        ivPlayItemName = findViewById(R.id.iv_play_item_name);
        ivPlayItemName2 = findViewById(R.id.iv_play_item_name_2);
        ivPlayItemName3 = findViewById(R.id.iv_play_item_name_3);
        mTvBetMoney = findViewById(R.id.tv_value_bet_money);
        mTvRate = findViewById(R.id.tv_value_rate);
        mTvBetWin = findViewById(R.id.tv_value_bet_win);
        mTvStatus = findViewById(R.id.tv_value_status);
        mBtnCancel = findViewById(R.id.btn_cancel);
        String title = LanguageUtil.getText("注单详情");
        mHeadView.setHeader(title, true);

        mBtnCopy.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        RecordBet.ListBean bean = getIntent().getParcelableExtra(KEY_RECORD_BEAN);
        setData(bean);
    }

    private void setData(RecordBet.ListBean bean) {
        mBean = bean;
        mTvName.setText(bean.getTicketName());
        mTvPeriod.setText(bean.getTicketPlanNo() + LanguageUtil.getText("期"));
        mTvNo.setText(bean.getOrderId());
        mTvTime.setText(bean.getBetTime());
        mTvPlay.setText(bean.getPlayName());
//        mTvContent.setText(bean.getBetContent());
        setBetContent(bean.getBetContent(), bean.getSeriesId());
        mTvBetMoney.setText(String.valueOf(bean.getBetMoney()));
        mTvBetWin.setText(String.valueOf(bean.getWinAmount()));
        mTvStatus.setText(bean.getBetStatusDes());

        if (0 == bean.getWinAmount()) {
            mTvBetWin.setText("- / -");
        } else {
            String winAmount = DecimalSetUtils.setMoneySaveFour(bean.getWinAmount() + "");
            mTvBetWin.setText(winAmount);
            mTvBetWin.setTextColor(ContextCompat.getColor(this, R.color.ski_win_money_red));
        }

        if (bean.isCanCancel()) {
            mBtnCancel.setVisibility(View.VISIBLE);
        } else {
            mBtnCancel.setVisibility(View.GONE);
        }

        int betStatus = bean.getBetStatus();
        if (betStatus == 5) {
            mTvStatus.setTextColor(ContextCompat.getColor(this, R.color.ski_white));
            mTvStatus.setBackgroundResource(R.drawable.ski_bg_record_detail_status_5);
        } else {
            mTvStatus.setTextColor(ContextCompat.getColor(this, R.color.ski_color_797979));
            mTvStatus.setBackgroundResource(R.drawable.ski_bg_record_detail_status);
        }

        /*赔率*/
        String odds = bean.getOdd();
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        HashMap<String, String> hashMap = new Gson().fromJson(odds, type);
        String one = hashMap.get("1");
        /*判断最后一个数字是不是0*/
        one = endIsZero(one);

        String two = hashMap.get("2");
        if (two != null) {
            two = endIsZero(two);
            one = one + "\n" + two;
        }

        mTvRate.setText(one);


        //
        if (TextUtils.isEmpty(bean.getTicketResult())) {
            mTvOpen.setVisibility(View.VISIBLE);
            mLotteryResultView.setVisibility(View.GONE);
            mTvOpen.setText(LanguageUtil.getText("等待开奖中") + "......");
        } else {
            mTvOpen.setVisibility(View.GONE);
            mLotteryResultView.setVisibility(View.VISIBLE);
            List<LotteryNumBean> list = new ArrayList<>();
            String code = bean.getTicketResult();
            code = code.replace(",", " ");
            LotteryNumBean lotteryNumBean = new LotteryNumBean();
            lotteryNumBean.setCode(code);
            int serId = LotteryUtil.getSerIdByLotteryId(bean.getTicketId());
            lotteryNumBean.setItemType(serId);
            list.add(lotteryNumBean);
            mLotteryResultView.setResult(list, 2);
        }
    }

    private void setBetContent(String playItemName, int serId) {
        tvPlayItemName.setText(playItemName);
        ivPlayItemName.setImageResource(0);
        ivPlayItemName2.setImageResource(0);
        ivPlayItemName3.setImageResource(0);
        int currentSerId = serId;
        if (LotteryConstant.SER_ID_F1_JJS == currentSerId || LotteryConstant.SER_ID_F1_CCL == currentSerId) {
            if (TextUtils.isDigitsOnly(playItemName)) {
                setItemNameVisible( true, false, false);
                ivPlayItemName.setImageResource(ConfigurationUiUtils.getF1JJSIcon(playItemName));
            } else {
                setItemNameVisible( false, false, false);
            }
        } else if (LotteryConstant.SER_ID_F1_SW == currentSerId) {
            if (TextUtils.isDigitsOnly(playItemName)) {
                List<Integer> iconList = getSwIcons(playItemName);
                if (1 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    setItemNameVisible( true, false, false);
                } else if (2 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    ivPlayItemName2.setImageResource(iconList.get(1));
                    setItemNameVisible( true, true, false);
                } else if (3 == iconList.size()) {
                    ivPlayItemName.setImageResource(iconList.get(0));
                    ivPlayItemName2.setImageResource(iconList.get(1));
                    ivPlayItemName3.setImageResource(iconList.get(2));
                    setItemNameVisible( true, true, true);
                }
            } else {
                setItemNameVisible( false, false, false);
            }
        } else {
            setItemNameVisible( false, false, false);
        }
    }

    private void setItemNameVisible(boolean ivName1, boolean ivName2, boolean ivName3) {
        if (ivName1) {
            ivPlayItemName.setVisibility(View.VISIBLE);
            tvPlayItemName.setVisibility(View.GONE);
        } else {
            ivPlayItemName.setVisibility(View.GONE);
            tvPlayItemName.setVisibility(View.VISIBLE);
        }

        if (ivName2) {
            ivPlayItemName2.setVisibility(View.VISIBLE);
        } else {
            ivPlayItemName2.setVisibility(View.GONE);
        }

        if (ivName3) {
            ivPlayItemName3.setVisibility(View.VISIBLE);
        } else {
            ivPlayItemName3.setVisibility(View.GONE);
        }
    }

    private List<Integer> getSwIcons(String playCode) {
        List<Integer> icons = new ArrayList<>();
        for (int i = 0; i < playCode.length(); i++) {
            icons.add(ConfigurationUiUtils.getF1JJSIcon(String.valueOf(playCode.charAt(i))));
        }
        return icons;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_copy) {
            copyText(mTvNo.getText().toString());
        } else if (id == R.id.btn_cancel) {
            if(mBean != null) {
                showBetCancelDialog(mBean);
            }
        }
    }


    private void copyText(String text) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        ToastUtil.showInfo("复制成功");
    }


    private String endIsZero(String one) {
        String c = one.substring(one.length() - 1);
        if ("0".equals(c)) {
            String substring = one.substring(0, one.length() - 1);
            return substring;
        } else {
            return one;
        }
    }

    @Override
    public void onSuccessful(Object o) {

    }

    @Override
    public void onError(Throwable o) {

    }

    @Override
    public void getMoneyTypeSuccess(List<FrontTradeTypesBean> beans) {

    }

    @Override
    public void onCancelSuccess() {
        ToastUtil.showSuccess("撤单成功");
        RxBus.get().post(EVENT_RECORD_CANCEL_SUCCESS,"");
    }

    @Override
    public void onCancelFail(String s) {
    }

    /**
     * 投注撤单
     *
     * @param bean
     */
    private void showBetCancelDialog(RecordBet.ListBean bean) {
        mCancelDialog = new CancelDialog(this, new CancelDialog.OnClickconfirmListener() {
            @Override
            public void confirm() {
                /** 撤单 **/
                mPresenter.showCancelDialog(bean);
            }

            @Override
            public void cancel() {

            }
        });
        mCancelDialog.show();
    }
}
