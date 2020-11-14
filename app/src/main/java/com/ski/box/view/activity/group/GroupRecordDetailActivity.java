package com.ski.box.view.activity.group;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.FrontTradeTypesBean;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.group.GroupBetData;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.contract.RecordBetContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.mvp.presenter.RecordBetPresenter;
import com.ski.box.utils.DecimalSetUtils;
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

public class GroupRecordDetailActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {
    public static final String KEY_RECORD_BEAN = "key_record_bean";
    private HeaderView mHeadView;
    private TextView mTvName;
    private TextView mTvPeriod;
    private LotteryResultView mLotteryResultView;
    private TextView mTvOpen;
    private TextView mTvUserName;
    private TextView mTvRebate;
    private TextView mTvNo;
    private Button mBtnCopy;
    private TextView mTvTime;
    private TextView mTvPlay;
    private TextView mTvContent;
    private TextView mTvBetMoney;
    private TextView mTvRate;
    private TextView mTvBetWin;
    private TextView mTvStatus;
    private Button mBtnCancel;

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_group_record_detail;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mTvName = findViewById(R.id.tv_name);
        mTvPeriod = findViewById(R.id.tv_period);
        mLotteryResultView = findViewById(R.id.lottery_result_view);
        mTvOpen = findViewById(R.id.tv_open);
        mTvUserName = findViewById(R.id.tv_value_user_name);
        mTvRebate = findViewById(R.id.tv_value_rebate);
        mTvNo = findViewById(R.id.tv_value_no);
        mBtnCopy = findViewById(R.id.btn_copy);
        mTvTime = findViewById(R.id.tv_value_time);
        mTvPlay = findViewById(R.id.tv_value_play);
        mTvContent = findViewById(R.id.tv_value_content);
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
        GroupBetData.ListBean bean = getIntent().getParcelableExtra(KEY_RECORD_BEAN);
        setData(bean);
    }

    private void setData(GroupBetData.ListBean bean) {
        mTvName.setText(bean.getTicketName());
        mTvName.setText(bean.getTicketName());
        mTvPeriod.setText(bean.getIssueNo());
        mTvNo.setText(String.valueOf(bean.getOrderId()));
        mTvUserName.setText(bean.getMemberAccount());
        mTvRebate.setText(bean.getRebate());
        mTvTime.setText(bean.getBetTime());
        mTvPlay.setText(bean.getPlayName());
        mTvContent.setText(bean.getBetContent());
        mTvBetMoney.setText(String.valueOf(bean.getBetAmt()));
        mTvBetWin.setText(String.valueOf(bean.getWinAmt()));
        mTvStatus.setText(bean.getStatus());

        if ("0.0000".equals(bean.getWinAmt())) {
            mTvBetWin.setText("- / -");

            mTvStatus.setTextColor(ContextCompat.getColor(this, R.color.ski_color_797979));
            mTvStatus.setBackgroundResource(R.drawable.ski_bg_record_detail_status);
        } else {
            String winAmount = DecimalSetUtils.setMoneySaveFour(bean.getWinAmt() + "");
            mTvBetWin.setText(winAmount);
            mTvBetWin.setTextColor(ContextCompat.getColor(this, R.color.ski_win_money_red));

            mTvStatus.setTextColor(ContextCompat.getColor(this, R.color.ski_white));
            mTvStatus.setBackgroundResource(R.drawable.ski_bg_record_detail_status_5);
        }


        /*赔率*/
        String odds = bean.getOdds();
        if(!TextUtils.isEmpty(odds)) {
            odds = odds.replace("[", "");
            odds = odds.replace("]", "");
            mTvRate.setText(odds);
        }


        //
        if (TextUtils.isEmpty(bean.getOpenResult())) {
            mTvOpen.setVisibility(View.VISIBLE);
            mLotteryResultView.setVisibility(View.GONE);
        } else {
            mTvOpen.setVisibility(View.GONE);
            mLotteryResultView.setVisibility(View.VISIBLE);
            List<LotteryNumBean> list = new ArrayList<>();
            String code = bean.getOpenResult();
            code = code.replace(",", " ");
            LotteryNumBean lotteryNumBean = new LotteryNumBean();
            lotteryNumBean.setCode(code);
            int serId = LotteryUtil.getSerIdByLotteryId(bean.getTicketId());
            lotteryNumBean.setItemType(serId);
            list.add(lotteryNumBean);
            mLotteryResultView.setResult(list, 2);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_copy) {
            copyText(mTvNo.getText().toString());
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



}
