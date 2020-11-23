package com.ski.box.view.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.QuickSetMoneyBean;
import com.ski.box.bean.QuickSetMoneyBean2;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.utils.KeyBoardUtils;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.view.view.dialog.CustomBottomDialog;
import com.ski.box.view.view.keyboard.KeyBoardBean;
import com.ski.box.view.view.keyboard.NumsKeyBoardView;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ski.box.ConstantValue.EVENT_BET_BOTTOM_QUICK_DOUBLE_ET_CHANGE;
import static com.ski.box.ConstantValue.EVENT_TYPE_BET_NO_CHECK_UPDATE;
import static com.ski.box.ConstantValue.SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST;
import static com.ski.box.ConstantValue.SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;

/**
 * Created by tom on 2020/8/20.
 */
public class QuickMoneyView implements View.OnClickListener {
    private long mLastClickTime;
    private long mTimeInterval = 300L;
    private TextView tvSetting;
    private TextView tvUnit;
    private TextView firstQuick;
    private TextView secondQuick;
    private TextView thirdQuick;
    private TextView forthQuick;
    private TextView fiveQuick;
    private EditText etDoubleAmount;
    private NumsKeyBoardView numKeyBoard;
    private Context mContext;
    // 配置自定义键盘使用
    private StringBuffer stringBuffer;
    private List<TextView> textViews;
    private int doubleArg = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == doubleArg) {
                if (etDoubleAmount != null) {
                    etDoubleAmount.setSelection(0, etDoubleAmount.getText().toString().length());
                }
            }
        }
    };
    private int mColor1;
    private int mColor2;

    public QuickMoneyView(Context context, View rootView) {
        initView(context, rootView);
    }


    private void initView(Context context, View rootView) {
        mContext = context;
//        View rootView = View.inflate(context, R.layout.ski_quick_money_view, this);
        tvSetting = rootView.findViewById(R.id.quick_setting_amount);
        tvUnit = rootView.findViewById(R.id.tv_unit);
        firstQuick = rootView.findViewById(R.id.first_quick_amount);
        secondQuick = rootView.findViewById(R.id.second_quick_amount);
        thirdQuick = rootView.findViewById(R.id.third_quick_amount);
        forthQuick = rootView.findViewById(R.id.forth_quick_amount);
        fiveQuick = rootView.findViewById(R.id.five_quick_amount);
        etDoubleAmount = rootView.findViewById(R.id.quick_amount_show);
        numKeyBoard = rootView.findViewById(R.id.numkeyboard);

        etDoubleAmount.setHint(LanguageUtil.getText("输入金额"));
        if (LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            //  etDoubleAmount.setTypeface(ActivityUtil.getFontTNR());
        }

        tvSetting.setOnClickListener(this);
        tvUnit.setOnClickListener(this);
        firstQuick.setOnClickListener(this);
        secondQuick.setOnClickListener(this);
        thirdQuick.setOnClickListener(this);
        forthQuick.setOnClickListener(this);
        fiveQuick.setOnClickListener(this);

      //  KeyBoardUtils.dismissSystemkeyBoard((Activity) context, etDoubleAmount);
        setKeyBoardListener();
        setDoubleEtListener();
        initData();
    }


    private void initData() {
        // 配合自定义键盘使用
        stringBuffer = new StringBuffer();
        // 初始化默认快捷金额
        initQuickDouble();
        mColor1 = mContext.getResources().getColor(R.color.ski_color_B27496);
        mColor2 = mContext.getResources().getColor(R.color.ski_color_c3c9d2);
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < mTimeInterval) {
            // 快速点击事件
            return;
        }
        mLastClickTime = nowTime;
        int id = v.getId();
        if (id == R.id.quick_setting_amount) {
            showQuickAmountSet(); //弹出快捷金额设置框
        } else if (id == R.id.first_quick_amount || id == R.id.second_quick_amount || id == R.id.third_quick_amount || id == R.id.forth_quick_amount || id == R.id.five_quick_amount) {
            // 快捷金额 double
            setQuickDouble((TextView) v);
        }
    }

    private CustomBottomDialog tDialog;

    // 打开快捷编辑 double
    private void showQuickAmountSet() {
        /*弹出设置快捷金额弹框*/
        if (tDialog != null) {
            tDialog.dismiss();
        }
        QuickMoneySettingView2 doubleQuickMoneySettingView2 = new QuickMoneySettingView2(mContext);
        tDialog = new CustomBottomDialog(mContext, R.style.no_background, 0);
        tDialog.setContentView(doubleQuickMoneySettingView2);
        tDialog.show();
        tDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initQuickDouble4Value();
            }
        });
        doubleQuickMoneySettingView2.setTDialog(tDialog);
    }

    private void initQuickDouble() {
        // 设置double et
        String money = SPUtils.getString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, "");
        if (TextUtils.isEmpty(money)) {
            money = "0";
            tvUnit.setVisibility(View.GONE);
            etDoubleAmount.setHint(LanguageUtil.getText("输入金额"));
        } else {
            etDoubleAmount.setHint("");
            etDoubleAmount.setText(money);
            stringBuffer.append(money);
            tvUnit.setVisibility(View.VISIBLE);
            //  etDoubleAmount.setTextColor(mContext.getResources().getColor(R.color.ski_play_select_color));

        }
        DataCenter.getInstance().setQuiickMoney(Integer.parseInt(money));
        initQuickDouble4Value();
    }


    /**
     * 初始化double四个快捷金额的值
     */
    public void initQuickDouble4Value() {
        // quick double 4 个快捷金额
        textViews = new ArrayList<>();

        textViews.add(firstQuick);
        textViews.add(secondQuick);
        textViews.add(thirdQuick);
        textViews.add(forthQuick);
        textViews.add(fiveQuick);


        List<QuickSetMoneyBean2> mDatas = new ArrayList<>();
        String defaultMoney = SPUtils.getString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, "");
        if (defaultMoney.isEmpty()) {
            mDatas.add(new QuickSetMoneyBean2("10", false));
            mDatas.add(new QuickSetMoneyBean2("50", false));
            mDatas.add(new QuickSetMoneyBean2("100", false));
            mDatas.add(new QuickSetMoneyBean2("500", false));
            mDatas.add(new QuickSetMoneyBean2("1k", false));
            String s = new Gson().toJson(mDatas);
            SPUtils.putString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, s);
        } else {
            try {
                JSONArray jsonArray = new JSONArray(defaultMoney);
                for (int x = 0; x < jsonArray.length(); x++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    String money = jsonObject.getString("money");
                    boolean isSelected = jsonObject.getBoolean("isSelected");
                    mDatas.add(new QuickSetMoneyBean2(money, isSelected));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        for (int x = 0; x < textViews.size(); x++) {
            TextView textView = textViews.get(x);
            textView.setVisibility(View.GONE);
        }

        for (int x = 0; x < mDatas.size(); x++) {
            QuickSetMoneyBean2 quickSetMoneyBean = mDatas.get(x);
            TextView textView = textViews.get(x);
            String money = quickSetMoneyBean.getMoney();
            if (!TextUtils.isEmpty(money)) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(money);
            }
        }
    }

    // 给快捷金额赋值
    private void setQuickDouble(TextView v) {
        String money = v.getText().toString();
        if (money.contains("k")) {
            money = money.replace("k", "000");
        }
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(money);
        etDoubleAmount.setText(money);
        // showBubbleReminder(id);
        setEditQuick_double(money);
    }

    private void setEditQuick_double(String money) {

        try {
            if (TextUtils.isEmpty(money)) {
                money = "0";
            }
            int i = Integer.parseInt(money);
            DataCenter.getInstance().setQuiickMoney(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(money);
            money = m.replaceAll("").trim();
            DataCenter.getInstance().setQuiickMoney(Integer.parseInt(money));
        }
        /*更新 投注单数提醒*/
        if (TextUtils.isEmpty(money)) {
            money = "0";
            etDoubleAmount.setHint(LanguageUtil.getText("输入金额"));
            SPUtils.putString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, "");
        } else {
            etDoubleAmount.setHint("");
            etDoubleAmount.setText(money);
            SPUtils.putString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, money);
        }
        MkBetParamEntity entity = DataCenter.getInstance().getBetParamEntity();
        List<MkBetParamEntity.BetParamEntity> bet = entity.getBet();
//        double totalMoney = 0;
        for (int i = 0; i < bet.size(); i++) {
            MkBetParamEntity.BetParamEntity betParamEntity = bet.get(i);
            betParamEntity.setBetAmount_d(Long.parseLong(money));
//            int betCount = betParamEntity.getBetCount();
//            totalMoney += betCount * Long.parseLong(money);
        }
        BetStatus betStatus = LotteryNoUtil.getBetStatus(entity.getBet(), LOTTERY_PLAY_MODE_DOUBLE);
//        betStatus.setZhuShu(bet.size());
//        betStatus.setTotalAmount(totalMoney);
//        betStatus.setStatus(LotteryConstant.LOTTERY_PLAY_DAN);
        RxBus.get().post(EVENT_TYPE_BET_NO_CHECK_UPDATE, betStatus);
    }


    public EditText etDoubleAmount() {
        return etDoubleAmount;
    }

    //
    private void setKeyBoardListener() {
        /*监听键盘操作*/
        numKeyBoard.setOnItemClickListener(new NumsKeyBoardView.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, KeyBoardBean bean) {
                int type = bean.getType();
                String num = bean.getNum();
                if (type == KeyBoardBean.DISMISS) {
                    openCloseNumKeyBoard(false);
                    //   llDoubleAmount.setBackgroundResource(R.mipmap.ski_jine);
                } else if (type == KeyBoardBean.DELETE) {
                    int length = stringBuffer.length();
                    if (length - 1 >= 0) {
                        boolean b = selectionAll();
                        if (b) {
                            stringBuffer.delete(0, length);
                        } else {
                            stringBuffer.deleteCharAt(length - 1);
                        }
                        etDoubleAmount.setText(stringBuffer.toString());
                    }
                } else if (type == KeyBoardBean.NUMBER) {
                    int length = stringBuffer.length();
                    /*头0去掉*/
                    if (length == 0 && "0".equals(num)) {
                        return;
                    }
                    /*限制长度*/
                    int maxLength = 8;
                    if (length <= maxLength) {
                        boolean b = selectionAll();
                        if (b) {
                            stringBuffer.delete(0, length);
                            if ("0".equals(num)) {
                                stringBuffer.append("");
                            } else {
                                stringBuffer.append(num);
                            }
                            String s = stringBuffer.toString();
                            etDoubleAmount.setText(s);
                        } else {
                            stringBuffer.append(num);
                            String s = stringBuffer.toString();
                            etDoubleAmount.setText(s);
                        }
                    }
                }
            }
        });
        numKeyBoard.addOnKeyBoardChangeListener(new NumsKeyBoardView.KeyBoardChangeListener() {
            @Override
            public void open() {
//                if (llDoubleAmount != null) {
//                    llDoubleAmount.setBackground(getContext().getResources().getDrawable(R.drawable.ski_select_bet_edittext_green));
//                }
//
//                RxBus.get().post(EVENT_KEYBOARD_STATUE_CHANGE, "1");
            }

            @Override
            public void close() {
//                if (llDoubleAmount != null) {
//                    llDoubleAmount.setBackgroundResource(R.mipmap.ski_jine);
//                }
                boolean b = selectionAll();
                if (b) {
                    etDoubleAmount.setSelection(etDoubleAmount.getText().toString().length());
                }
//                RxBus.get().post(EVENT_KEYBOARD_STATUE_CHANGE, "0");
            }
        });
    }

    private boolean selectionAll() {
        int selectionStart = 0;
        int selectionEnd = 0;
        if (etDoubleAmount != null) {
            selectionStart = etDoubleAmount.getSelectionStart();
            selectionEnd = etDoubleAmount.getSelectionEnd();
        }
        if (selectionStart == 0 && selectionEnd > 0) {
            return true;
        } else {
            return false;
        }
    }


    /*双面盘*/
    private void openCloseNumKeyBoard(boolean open) {
        boolean expanded = numKeyBoard.isExpanded();
        if (open) {
            if (!expanded) {
                numKeyBoard.expand();
                //   saveBottmViewHegiht();
            }

        } else {
            if (expanded) {
                numKeyBoard.collapse();
                //    saveBottmViewHegiht();
            }
        }
    }

    String mTempStr;

    private void setDoubleEtListener() {
        etDoubleAmount.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    /*展开键盘*/
                  //  openCloseNumKeyBoard(true);
                    handler.sendEmptyMessageDelayed(doubleArg, 300);
                }
                return false;
            }
        });

        etDoubleAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString();
                if (!TextUtils.isEmpty(mTempStr) && mTempStr.equals(money)) {
                    return;
                }

                mTempStr = money;
//                boolean matches = money.matches("\\d+");
//                tvUnit.setVisibility(matches ? View.VISIBLE : View.GONE);
//                etDoubleAmount.setTextColor(matches ? mColor1 : mColor2);
                setEditQuick_double(money);
                RxBus.get().post(EVENT_BET_BOTTOM_QUICK_DOUBLE_ET_CHANGE, "et");
                etDoubleAmount.setSelection(money.length());

            }
        });
    }

    public void setX3() {
        String str = etDoubleAmount.getText().toString();
        int money = ActivityUtil.doBetMoneyWithK(str) * 3;
        if(String.valueOf(money).length() > 8) {
            return;
        }
        etDoubleAmount.setText(String.valueOf(money));
    }
}
