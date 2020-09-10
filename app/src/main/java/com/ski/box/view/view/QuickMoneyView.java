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
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.utils.KeyBoardUtils;
import com.ski.box.view.view.dialog.CustomBottomDialog;
import com.ski.box.view.view.keyboard.KeyBoardBean;
import com.ski.box.view.view.keyboard.NumsKeyBoardView;
import com.yb.core.utils.SPUtils;
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

/**
 * Created by tom on 2020/8/20.
 */
public class QuickMoneyView extends ConstraintLayout implements View.OnClickListener {
    private long mLastClickTime;
    private long mTimeInterval = 300L;
    private TextView tvSetting;
    private TextView tvUnit;
    private TextView firstQuick;
    private TextView secondQuick;
    private TextView thirdQuick;
    private TextView forthQuick;
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

    public QuickMoneyView(Context context) {
        super(context);
        initView(context);
    }

    public QuickMoneyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QuickMoneyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public QuickMoneyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View rootView = View.inflate(context, R.layout.ski_quick_money_view, this);
        tvSetting = rootView.findViewById(R.id.quick_setting_amount);
        tvUnit = rootView.findViewById(R.id.tv_unit);
        firstQuick = rootView.findViewById(R.id.first_quick_amount);
        secondQuick = rootView.findViewById(R.id.second_quick_amount);
        thirdQuick = rootView.findViewById(R.id.third_quick_amount);
        forthQuick = rootView.findViewById(R.id.forth_quick_amount);
        etDoubleAmount = rootView.findViewById(R.id.quick_amount_show);
        numKeyBoard = rootView.findViewById(R.id.numkeyboard);

        tvSetting.setOnClickListener(this);
        tvUnit.setOnClickListener(this);
        firstQuick.setOnClickListener(this);
        secondQuick.setOnClickListener(this);
        thirdQuick.setOnClickListener(this);
        forthQuick.setOnClickListener(this);

        KeyBoardUtils.dismissSystemkeyBoard((Activity) getContext(), etDoubleAmount);
        setKeyBoardListener();
        setDoubleEtListener();
        initData();
    }


    private void initData() {
        // 配合自定义键盘使用
        stringBuffer = new StringBuffer();
        // 初始化默认快捷金额
        initQuickDouble();
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
        } else if (id == R.id.first_quick_amount || id == R.id.second_quick_amount || id == R.id.third_quick_amount || id == R.id.forth_quick_amount) {
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
        QuickMoneySettingView doubleQuickMoneySettingView = new QuickMoneySettingView(getContext());
        tDialog = new CustomBottomDialog(getContext(), R.style.no_background, 0);
        tDialog.setContentView(doubleQuickMoneySettingView);
        tDialog.show();
        tDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initQuickDouble4Value();
            }
        });

        doubleQuickMoneySettingView.configurationData(tDialog);
    }

    private void initQuickDouble() {
        // 设置double et
        String money = SPUtils.getString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, "");
        if (TextUtils.isEmpty(money)) {
            money = "0";
            tvUnit.setVisibility(GONE);
            etDoubleAmount.setHint("输入金额");
        } else {
            etDoubleAmount.setHint("");
            etDoubleAmount.setText(money);
            stringBuffer.append(money);
            tvUnit.setVisibility(VISIBLE);
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


        List<QuickSetMoneyBean> mDatas = new ArrayList<>();
        String defaultMoney = SPUtils.getString(getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, "");
        if (defaultMoney.isEmpty()) {
            mDatas.add(new QuickSetMoneyBean("10", false));
            mDatas.add(new QuickSetMoneyBean("50", false));
            mDatas.add(new QuickSetMoneyBean("100", false));
            mDatas.add(new QuickSetMoneyBean("500", false));
            String s = new Gson().toJson(mDatas);
            SPUtils.putString(getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, s);
        } else {
            try {
                JSONArray jsonArray = new JSONArray(defaultMoney);
                for (int x = 0; x < jsonArray.length(); x++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    String money = jsonObject.getString("money");
                    boolean red = jsonObject.getBoolean("red");
                    mDatas.add(new QuickSetMoneyBean(money, red));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        int quickNums = 4;

        for (int x = 0; x < mDatas.size(); x++) {
            QuickSetMoneyBean quickSetMoneyBean = mDatas.get(x);
            TextView textView = textViews.get(x);
            String money = quickSetMoneyBean.getMoney();
            if (money != null && !money.isEmpty()) {
                textView.setVisibility(VISIBLE);
                textView.setText(money);
            } else {
                quickNums--;
                textView.setVisibility(GONE);
            }
        }

        for (int x = 0; x < textViews.size(); x++) {
            TextView textView = textViews.get(x);
            int visibility = textView.getVisibility();
            /*如果显示*/
            String s = textView.getText().toString();
            if (View.VISIBLE == visibility) {
                for (int y = x + 1; y < textViews.size(); y++) {
                    TextView nexView = textViews.get(y);
                    String s1 = nexView.getText().toString();
                    if (s.equals(s1)) {
                        nexView.setVisibility(GONE);
                    }
                }
            }
        }

        if (quickNums == 0) {
            /*全部隐藏*/
            firstQuick.setVisibility(GONE);
            secondQuick.setVisibility(GONE);
            thirdQuick.setVisibility(GONE);
            forthQuick.setVisibility(GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvSetting.getLayoutParams();

            layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.ski_dp_10);
            layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.quick_amount_show);
            layoutParams.addRule(RelativeLayout.RIGHT_OF, 0);
            tvSetting.setLayoutParams(layoutParams);
            Drawable drawable = getResources().getDrawable(R.drawable.ski_back_tv_kuaijie_right);
            tvSetting.setBackground(drawable);
        } else {
//            int visibility = llQuickMoney.getVisibility();
//            if (visibility == View.GONE) {
//                llQuickMoney.setVisibility(VISIBLE);
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.skiDoubleReset);
//                tvSetting.setLayoutParams(layoutParams);
//            }
        }
    }

    // 给快捷金额赋值
    private void setQuickDouble(TextView v) {
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(v.getText());
        etDoubleAmount.setText(v.getText());
        // showBubbleReminder(id);
        String money = etDoubleAmount.getText().toString();
        setEditQuick_double(money);
    }

    private void setEditQuick_double(String money) {

        try {
            int i = Integer.parseInt(money);
            DataCenter.getInstance().setQuiickMoney(i);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(money);
            money= m.replaceAll("").trim();
            DataCenter.getInstance().setQuiickMoney(Integer.parseInt(money));
        }
        /*更新 投注单数提醒*/
        if (TextUtils.isEmpty(money)) {
            money = "0";
            etDoubleAmount.setHint("输入金额");
            SPUtils.putString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, "");
        } else {
            etDoubleAmount.setHint("");
            etDoubleAmount.setText(money);
            SPUtils.putString(mContext, SP_DOUBLE_QUICK_MONEY_DEFAULT_SELECTED, money);
        }
        List<MkBetParamEntity.BetParamEntity> bet = DataCenter.getInstance().getBetParamEntity().getBet();
        int totalMoney = 0;
        for (int i = 0; i < bet.size(); i++) {
            MkBetParamEntity.BetParamEntity betParamEntity = bet.get(i);
            betParamEntity.setBetAmount_d(Integer.parseInt(money));
            int betCount = betParamEntity.getBetCount();
            totalMoney += betCount * Integer.parseInt(money);
        }
        BetStatus betStatus = new BetStatus();
        betStatus.setZhuShu(bet.size());
        betStatus.setTotalAmount(totalMoney);
        betStatus.setStatus(LotteryConstant.LOTTERY_PLAY_DAN);
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
                    if (length <= 6) {
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
        etDoubleAmount.setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    /*展开键盘*/
                    openCloseNumKeyBoard(true);
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
                boolean matches = money.matches("\\d+");
                tvUnit.setVisibility(matches ? VISIBLE : GONE);
                etDoubleAmount.setTextColor(matches ? mContext.getResources().getColor(R.color.ski_color_B27496) : mContext.getResources().getColor(R.color.ski_color_c3c9d2));
                setEditQuick_double(money);
                RxBus.get().post(EVENT_BET_BOTTOM_QUICK_DOUBLE_ET_CHANGE, "et");
                etDoubleAmount.setSelection(money.length());

            }
        });
    }
}
