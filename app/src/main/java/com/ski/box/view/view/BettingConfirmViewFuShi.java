package com.ski.box.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.ski.box.R;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.ski.box.view.view.keyboard.KeyBoardBean;
import com.ski.box.view.view.keyboard.NumsKeyBoardView;
import com.yb.core.utils.LanguageUtil;

import java.util.List;

/**
 * @time:2019/9/16 11:04
 * @author:Jofear
 * @describe:投注确认
 **/
public class BettingConfirmViewFuShi extends LinearLayout {
    private RelativeLayout mLlTitle;
    private ImageView mTvClose;
    private View mViewLine1;
    private TextView mTvFushiPlayName;
    private TextView mTvFushiZushu;
    private TextView mTvFushiNums;
    private LinearLayout mLlMoney;
    private EditText mEditText;
    private TextView mTvGroupsPerOrder;
    private TextView mTvWinAmount;
    private CheckBox mCheckBoxCloseKuang;
    public TextView mBetConfirm;
    private NumsKeyBoardView numKeyBoard;
    private MkBetParamEntity.BetParamEntity betParamEntity = new MkBetParamEntity.BetParamEntity();
    StringBuffer stringBuffer = new StringBuffer();

    public BettingConfirmViewFuShi(Context context) {
        super(context);
        init(null, 0);
    }

    public BettingConfirmViewFuShi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BettingConfirmViewFuShi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        View.inflate(getContext(), R.layout.ski_betting_fushi_double_confirm_layout, this);
        mLlTitle = (RelativeLayout) findViewById(R.id.ll_title);
        mTvClose = (ImageView) findViewById(R.id.tv_close);
        mTvFushiPlayName = (TextView) findViewById(R.id.tv_fushi_play_name);
        mTvFushiZushu = (TextView) findViewById(R.id.tv_fushi_zushu);
        mTvFushiNums = (TextView) findViewById(R.id.tv_fushi_nums);
        mLlMoney = (LinearLayout) findViewById(R.id.ll_money);
        mEditText = (EditText) findViewById(R.id.et_item_amount);
        mTvGroupsPerOrder = (TextView) findViewById(R.id.tv_groups_per_order);
        mTvWinAmount = (TextView) findViewById(R.id.tv_win_amount);
        mCheckBoxCloseKuang = (CheckBox) findViewById(R.id.checkBox_close_kuang);
        mBetConfirm = findViewById(R.id.bet_confirm);
        numKeyBoard = findViewById(R.id.num_keyboard);
        mCheckBoxCloseKuang.setText(LanguageUtil.getText(mCheckBoxCloseKuang.getText().toString()));
        mEditText.setInputType(InputType.TYPE_NULL);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                mEditText.setSelection(s1.length());
                Long aLong = Long.valueOf(s1);
                betParamEntity.setBetAmount_d(aLong);
                calculateBlance();


            }
        });
        mBetConfirm.setOnClickListener(v -> {
            if (onConfirmBetListner != null) {
                numKeyBoard.collapse();
//                mBetConfirm.setClickable(false);
                onConfirmBetListner.confirmBet(mBetConfirm);
            }

        });
        mTvClose.setOnClickListener(v -> {
            if (onConfirmBetListner != null) {
                onConfirmBetListner.closeBetConfrimDaiglog();
            }
        });
        mCheckBoxCloseKuang.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {

                AlertConfigurationBean bean = new AlertConfigurationBean();
                bean.setTitle("以后停用“投注确认”弹框");
                bean.setContent("你可在页面上方的设置中心内再次打开");
                bean.setLeftButtonText("取消");
                bean.setRightButtonText("停用");
                new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
                    @Override
                    public void rightButton() {
                        /*保存*/
                        SettingManager.setBetConfirmDialog(!isChecked);

                    }

                    @Override
                    public void leftButton() {
                        mCheckBoxCloseKuang.setChecked(false);

                    }
                });
            } else {
                SettingManager.setBetConfirmDialog(!isChecked);
            }
        });


        mEditText.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                numKeyBoard.expand();
                mEditText.setBackground(getResources().getDrawable(R.drawable.ski_edtext_bottom_focuse));
            }
            return false;
        });
        /*监听键盘操作*/
        numKeyBoard.addOnKeyBoardChangeListener(new NumsKeyBoardView.KeyBoardChangeListener() {
            @Override
            public void open() {

            }

            @Override
            public void close() {
                mEditText.setBackground(getResources().getDrawable(R.drawable.ski_edtext_bottom_unfocuse));
                String s = mEditText.getText().toString();
                if (s.equals("0")) {
                    stringBuffer.replace(0, 1, "1");
                    mEditText.setText(stringBuffer.toString());

                }
            }
        });

        numKeyBoard.setOnItemClickListener((position, bean) -> {
            int type = bean.getType();
            String num = bean.getNum();

            if (type == KeyBoardBean.DISMISS) {
                numKeyBoard.collapse();

            } else if (type == KeyBoardBean.DELETE) {
                int length = stringBuffer.length();
                if (length - 1 >= 0) {
                    stringBuffer.deleteCharAt(length - 1);
                    String s = stringBuffer.toString();
                    if (s.equals("")) {
                        s = "0";
                        stringBuffer.append(s);
                    }
                    mEditText.setText(stringBuffer.toString());
                }
            } else if (type == KeyBoardBean.NUMBER) {
                int length = stringBuffer.length();
                if (length <= 6) {
                    String c = String.valueOf(stringBuffer.charAt(0));
                    if (c.equals("0")) {
                        stringBuffer.replace(0, 1, num);
                    } else {
                        stringBuffer.append(num);
                    }

                    String s = stringBuffer.toString();
                    mEditText.setText(s);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void configurationData(List<MkBetParamEntity.BetParamEntity> bet) {
        betParamEntity = bet.get(0);
        String playName = betParamEntity.getGroupName();
        String playItemName = betParamEntity.getContent();
        /*注数*/
        int quantity = betParamEntity.getBetCount();
        /*每组金额*/
        long betAmount_d = betParamEntity.getBetAmount_d();

        String quantitys = quantity + "";
        String s = LanguageUtil.getText("共") + quantitys + LanguageUtil.getText("组");
        int i = s.indexOf(quantitys + "");

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(s);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
        stringBuilder.setSpan(colorSpan, i, i + quantitys.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvFushiZushu.setText(stringBuilder);
        mTvFushiPlayName.setText(playName);
        // String replace = playItemName.replace(",", "|");
        mTvFushiNums.setText(playItemName);
        mEditText.setText(betAmount_d + "");
        stringBuffer.append(mEditText.getText());
    }


    private void calculateBlance() {
        long betAmount_d = betParamEntity.getBetAmount_d();
        int betCount = betParamEntity.getBetCount();
        /*赔率*/
        /*一共投注多少钱*/
        long totalMoney = betAmount_d * betCount;
        /*可赢金额*/
        String win = LotteryNoUtil.calculateWinMoney_d(betParamEntity);
        mTvWinAmount.setText(LanguageUtil.getText("可赢") + " " + win);

        String danshu = "1";
        String quantitys = betCount + "";
        String totalMoney_s = String.valueOf(totalMoney);


        String string = danshu + LanguageUtil.getText("注单") + quantitys + LanguageUtil.getText("组")
                + totalMoney_s + LanguageUtil.getText("元");
        int danshu_index = string.indexOf(danshu);
        int zushu_index = string.indexOf(quantitys);
        int money_inedx = string.lastIndexOf(totalMoney + "");


        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(string);
        ForegroundColorSpan colorSpan0 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));

        stringBuilder.setSpan(colorSpan0, danshu_index, danshu_index + danshu.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(colorSpan1, zushu_index, zushu_index + quantitys.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(colorSpan2, money_inedx, money_inedx + totalMoney_s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvGroupsPerOrder.setText(stringBuilder);
    }

    public void addOnConfirmBet(OnConfirmBetListner listner) {
        this.onConfirmBetListner = listner;
    }

    private OnConfirmBetListner onConfirmBetListner;

    public interface OnConfirmBetListner {
        void confirmBet(View view);

        void closeBetConfrimDaiglog();
    }
}
