package com.ski.box.view.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.BallBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.user.User;
import com.ski.box.service.AlarmService;
import com.ski.box.utils.MyImageSpan;
import com.ski.box.utils.lottery.LimitRedUtil;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.StringUtils;
import com.zyyoona7.popup.EasyPopup;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_REQUEST_BET_SUBMIT;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;


/**
 * @CreateDate: 2020/3/3 16:07
 * @Description: 路子图 与长龙 投注 确认
 */
public class RoadDragonQuickBetView extends LinearLayout implements View.OnClickListener {

    private TextView tvTicketNameFast;
    private TextView tvPeriodFast;
    private TextView tvPalynameFast;
    private RecyclerView recyclerView;
    private EditText etInputMoneyFast;
    private TextView balanceFast;
    private TextView tvBetMoney;
    private TextView tvAvailableMoney;
    private TextView tvBetConfirm;
    private ConstraintLayout clBetLayout;
    private TextView tvAvailableBalance;
    private TextView tvGoToTouzhu;
    private TextView tvCountTime;
    public ImageView ivTouzhuDragonDown;

    private TextView tv00;
    private TextView tv01;
    private TextView tv02;
    private TextView tv03;
    private TextView tv04;
    private TextView tv05;
    private TextView tv06;
    private TextView tv07;
    private TextView tv08;
    private TextView tv09;
    private RelativeLayout rlDown;
    private RelativeLayout tvDelete;
    private LinearLayout view01;

    private boolean isSingle;
    private int inPutMoney;
    private List<BallBean> bigSmallBeans;
    private FastDialogAdapter fastDialogAdapter;
    private int orderMoney = 0;
    private EasyPopup easyPopup;
    private GridLayoutManager gridLayoutManager;
    private AlarmBroadReceive alarmBroadReceive;
    private String planId;

    /**
     * 0 长龙提醒 1首页长龙 2路子图
     */
    private int mType = 0;

    private LotteryDialog mTipDialog = new LotteryDialog();
    private CustomConlationLayout constraint;
    private TextView tvTitle;
    private ImageView ivDelete;

    public RoadDragonQuickBetView(Context context) {
        super(context);
        initView();
    }

    public RoadDragonQuickBetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoadDragonQuickBetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.ski_layout_diglog_quickbet, this);
        recyclerView = view.findViewById(R.id.recyc_fast_dailog);
        constraint = view.findViewById(R.id.constraint);
        ivTouzhuDragonDown = view.findViewById(R.id.iv_touzhu_dragon_down);
        tvTicketNameFast = view.findViewById(R.id.tv_ticketName_fast);
        tvPeriodFast = view.findViewById(R.id.tv_period_fast);
        tvPalynameFast = view.findViewById(R.id.tv_palyname_fast);
        etInputMoneyFast = view.findViewById(R.id.et_input_money_fast);
        balanceFast = view.findViewById(R.id.balance_fast);
        tvBetMoney = view.findViewById(R.id.tv_bet_money_fast);
        tvAvailableMoney = view.findViewById(R.id.tv_available_money);
        tvBetConfirm = view.findViewById(R.id.tv_bet_confirm);
        tvAvailableBalance = view.findViewById(R.id.tv_available_balance);
        clBetLayout = view.findViewById(R.id.cl_bet);
        tvGoToTouzhu = view.findViewById(R.id.tv_go_to_touzhu);
        tvCountTime = view.findViewById(R.id.tv_fast_betting_count_down);
        tvTitle = view.findViewById(R.id.tv_title);
        ivDelete = view.findViewById(R.id.iv_delete);

        tv00 = view.findViewById(R.id.tv_00);
        tv01 = view.findViewById(R.id.tv_01);
        tv02 = view.findViewById(R.id.tv_02);
        tv03 = view.findViewById(R.id.tv_03);
        tv04 = view.findViewById(R.id.tv_04);
        tv05 = view.findViewById(R.id.tv_05);
        tv06 = view.findViewById(R.id.tv_06);
        tv07 = view.findViewById(R.id.tv_07);
        tv08 = view.findViewById(R.id.tv_08);
        tv09 = view.findViewById(R.id.tv_09);
        rlDown = view.findViewById(R.id.rlDown);
        tvDelete = view.findViewById(R.id.tv_delete);
        view01 = view.findViewById(R.id.view_01);
        view01.setVisibility(VISIBLE);
        tv00.setOnClickListener(this);
        tv01.setOnClickListener(this);
        tv02.setOnClickListener(this);
        tv03.setOnClickListener(this);
        tv04.setOnClickListener(this);
        tv05.setOnClickListener(this);
        tv06.setOnClickListener(this);
        tv07.setOnClickListener(this);
        tv08.setOnClickListener(this);
        tv09.setOnClickListener(this);
        rlDown.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        ivTouzhuDragonDown.setOnClickListener(this);


        fastDialogAdapter = new FastDialogAdapter();
        recyclerView.setAdapter(fastDialogAdapter);
        fastDialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                BallBean smallBean = bigSmallBeans.get(position);
                boolean check = !smallBean.isCheck();
                if (isSingle) {
                    /**是单选**/
                    if (check) {
                        for (int x = 0; x < bigSmallBeans.size(); x++) {
                            BallBean smallBean1 = bigSmallBeans.get(x);
                            if (position == x) {
                                smallBean1.setCheck(true);
                            } else {
                                smallBean1.setCheck(false);
                            }
                        }
                        fastDialogAdapter.notifyDataSetChanged();
                    }else{
                        smallBean.setCheck(false);
                        fastDialogAdapter.notifyDataSetChanged();
                    }
                } else {
                    /**多选**/
                    smallBean.setCheck(check);
                }
                fastDialogAdapter.notifyDataSetChanged();
                calculation();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AlarmService.ALARM_ACTION + DataCenter.getInstance().getToken());
        if (alarmBroadReceive == null) {
            alarmBroadReceive = new AlarmBroadReceive();
            getContext().registerReceiver(alarmBroadReceive, intentFilter);
        }

        tvGoToTouzhu.setVisibility(mType == 1 ? VISIBLE : GONE);
        tvAvailableBalance.setVisibility(mType == 1 ? VISIBLE : GONE);
        User user = DataCenter.getInstance().getUser();
        tvAvailableBalance.setText("可用余额：" + user.getBalance());
//        initCirclePop();
        etInputMoneyFast.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean b = canParseInt(editable.toString());
                if (b) {
                    inPutMoney = Integer.parseInt(editable.toString());
                    calculation();
                    changeBg();
                } else {
                    tvBetMoney.setVisibility(INVISIBLE);
                    tvAvailableMoney.setVisibility(INVISIBLE);
                }
            }
        });

        disableShowSoftInput(etInputMoneyFast);
        etInputMoneyFast.setOnTouchListener(new OnTouchListener() {
            //按住和松开的标识
            int touch_flag = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touch_flag++;
                if (touch_flag == 2) {
                    touch_flag = 0;
                    //自己的业务
                    view01.setVisibility(VISIBLE);
                }
                return false;
            }
        });

        clBetLayout.setOnClickListener(v -> {
            if (inPutMoney != 0) {
                if (isCanBet()) {
                    DataCenter.getInstance().setBallBeanList(bigSmallBeans);
                    if (!TextUtils.isEmpty(oldPlanId) && !oldPlanId.equalsIgnoreCase(planId) ) {
                        showPeroidChangeDailog(planId); }else{ confirmBet(); }
                }else{
                    mTipDialog.showLightReminder(getContext(),clBetLayout,"至少选中1注");
                }
            } else {
                if (!isCanBet()){
                    mTipDialog.showLightReminder(getContext(),clBetLayout,"至少选中1注");
                    return;
                }
                mTipDialog.showLightReminder(getContext(),clBetLayout,"请输入投注金额");
            }
        });

        constraint.addOnTouchListener(new CustomConlationLayout.OnTouchYListener() {
            @Override
            public void touchY(float x, float y) {
                int top1 = etInputMoneyFast.getTop();
                int bottom1 = etInputMoneyFast.getBottom();
                int top = view01.getTop();
                int bottom = view01.getBottom();
                int top2 = recyclerView.getTop();
                int bottom2 = recyclerView.getBottom();
                if (y < top || y > bottom) {
                    if (y < top1 ||y > bottom1) {
                        if (y < top2 || y > bottom2) {
                            view01.setVisibility(GONE);
                        }

                    }
                }
            }
        });
        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view01.setVisibility(GONE);
            }
        });
    }

    public View getBetConfirm() {
        return clBetLayout;
    }

    public void confirmBet() {
        int ticketId = DataCenter.getInstance().getCurLotteryId();
        List<BallBean> ballBeanList = DataCenter.getInstance().getBallBeanList();
        MkBetParamEntity betEntity = new MkBetParamEntity();
        List<MkBetParamEntity.BetParamEntity> bet = new ArrayList<>();
        betEntity.setTicketId(ticketId);
        betEntity.setPlanNo(planId);
        for (int i = 0; i < ballBeanList.size(); i++) {
            BallBean ballBean = ballBeanList.get(i);
            boolean check = ballBean.isCheck();
            if (check) {
                MkBetParamEntity.BetParamEntity mkBetParamEntity = new MkBetParamEntity.BetParamEntity();
                mkBetParamEntity.setPlayId(ballBean.getPlayItemId());
                mkBetParamEntity.setBetNum(ballBean.getPlayItemCode());
                mkBetParamEntity.setBetCount(1);
                mkBetParamEntity.setContent(ballBean.getPlayItemName());
                mkBetParamEntity.setBetAmount_d(ballBean.getBetAmount_d());
                mkBetParamEntity.setGroupName(tvPalynameFast.getText().toString());
                mkBetParamEntity.setPlayOdds(ballBean.getPlayItemOdds());
                if (TextUtils.isEmpty(ballBean.getPlayItemCode())) {
                    return;
                }
                bet.add(mkBetParamEntity);
            }
        }
        betEntity.setPlayMode(LOTTERY_PLAY_MODE_DOUBLE);
        betEntity.setBet(bet);
        ArrayList<RedLimitBean> meets = LimitRedUtil.isMeetBettingInterval(bet);
        if (meets.size() > 0) {
            view01.setVisibility(GONE);
            new LotteryDialog().showRedLimitBubbleLayout(clBetLayout, meets);
        }else {
            betEntity.setBetView(clBetLayout);
            RxBus.get().post(EVENT_REQUEST_BET_SUBMIT, betEntity);
        }
    }
   private String oldPlanId ;
    /**
     * 刷新数据
     */
    public void freshData(String title) {
        String menuTitle = title;
         oldPlanId = DataCenter.getInstance().getPlanId();
        tvTicketNameFast.setText(DataCenter.getInstance().getCurLotteryName());
        int curLotteryId = DataCenter.getInstance().getLotterySeriesId();
        if (LotteryConstant.SER_ID_SSC ==curLotteryId) {
            menuTitle = "总和龙虎".equalsIgnoreCase(menuTitle) ? "龙虎和" : menuTitle;
        }
        tvPalynameFast.setText(menuTitle);
        User user = DataCenter.getInstance().getUser();
        if (user.getBalance() != null) {
            setBalanceStr(user.getBalance());
        }
        /**动态设置布局个数 是单选还是多选**/
        bigSmallBeans = DataCenter.getInstance().getBallBeanList();
        if (bigSmallBeans != null) {
            int size = bigSmallBeans.size();
            if (size > 2) {
                /**长度大于2 多选**/
                isSingle = false;
                gridLayoutManager = new GridLayoutManager(getContext(), 3);

            } else {
                /**长度小小于等于2 单选**/
                gridLayoutManager = new GridLayoutManager(getContext(), 2);
                isSingle = true;
            }
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        fastDialogAdapter.setNewInstance(bigSmallBeans);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        String string = etInputMoneyFast.getText().toString();
        if (id == R.id.tv_00) {
            etInputMoneyFast.setText(string + "0");
        } else if (id == R.id.tv_01) {
            etInputMoneyFast.setText(string + "1");
        } else if (id == R.id.tv_02) {
            etInputMoneyFast.setText(string + "2");
        } else if (id == R.id.tv_03) {
            etInputMoneyFast.setText(string + "3");
        } else if (id == R.id.tv_04) {
            etInputMoneyFast.setText(string + "4");
        } else if (id == R.id.tv_05) {
            etInputMoneyFast.setText(string + "5");
        } else if (id == R.id.tv_06) {
            etInputMoneyFast.setText(string + "6");
        } else if (id == R.id.tv_07) {
            etInputMoneyFast.setText(string + "7");
        } else if (id == R.id.tv_08) {
            etInputMoneyFast.setText(string + "8");
        } else if (id == R.id.tv_09) {
            etInputMoneyFast.setText(string + "9");
        } else if (id == R.id.tv_delete) {
            if (string != null && string.length() > 0) {
                String substring = string.substring(0, string.length() - 1);
                etInputMoneyFast.setText(substring);
            }
        } else if (id == R.id.rlDown) {
            view01.setVisibility(GONE);
        } else if (id == R.id.iv_delete) {
            etInputMoneyFast.setText("");

        } else if (id == R.id.iv_touzhu_dragon_down) {
            if(mCloseListener !=null ) {
                mCloseListener.closeDialog();
            }
            return;
        }

        String str = etInputMoneyFast.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            etInputMoneyFast.setSelection(str.length());

            ivDelete.setVisibility(VISIBLE);

        }else{
            inPutMoney=0;
            ivDelete.setVisibility(GONE);
        }
        changeBg();
    }


    private void setBalanceStr(String balance) {

        SpannableString spannableString = StringUtils.changTVsize(balance);
        balanceFast.setText(spannableString);
    }


    private class FastDialogAdapter extends BaseQuickAdapter<BallBean, BaseViewHolder> {
        private Typeface pingFangType;

        public FastDialogAdapter() {
            super(R.layout.ski_item_fast_dailog);
            pingFangType = Typeface.createFromAsset(AppUtil.getContext().getAssets(), "fonts/pingfangscregular.ttf");
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @org.jetbrains.annotations.Nullable BallBean smallBean) {
            LinearLayout mLayout = holder.getView(R.id.ll_fast_tou_zhu);
            TextView checkBox = holder.getView(R.id.cb_first_touzhu_dragon);
            TextView tvOdds = holder.getView(R.id.tv_odds);
            checkBox.setTypeface(pingFangType);
            tvOdds.setTypeface(pingFangType);
            String peiLv = smallBean.getPlayItemOdds();
            String name = smallBean.getPlayItemName();
            boolean check = smallBean.isCheck();
            checkBox.setText(name);
            tvOdds.setText(peiLv);
            tvOdds.setTextColor(Color.parseColor(check ? "#ffffff" : "#7c88a8"));
            if (check){
                mLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), R.drawable.ski_quick_bet_back_green));
                checkBox.setTextColor(ContextCompat.getColor(AppUtil.getContext(),R.color.ski_white));
            }else{
                mLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), R.mipmap.ski_ic_fast_bet_normal));
                checkBox.setTextColor(ContextCompat.getColor(AppUtil.getContext(),R.color.ski_color_bebebe));
            }
        }
    }

    private void changeBg() {
        if (isCanBet() && inPutMoney!=0){
            clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_pub_bg_setting_trend));
            tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_white ));

        }else{
            clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_record_quick_bet_normal));
            tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_color_bebebe));
        }
    }


    /**
     * 计算单数 投注金额等
     */
    String playItemOdds="";
    private void calculation() {
        boolean canBet = isCanBet();
        String str = etInputMoneyFast.getText().toString();
        boolean isEmpty = !TextUtils.isEmpty(str) ;
        boolean b = canBet && isEmpty && inPutMoney!=0;
        clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), b ? R.drawable.ski_pub_bg_setting_trend : R.drawable.ski_record_quick_bet_normal));
        tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(), b ? R.color.ski_white : R.color.ski_color_bebebe));

        /**单数*/
        int danshu = 0;
        /**一共多少钱*/

        float winMoney = 0;
        if (bigSmallBeans == null) {
            return;
        }

        for (int x = 0; x < this.bigSmallBeans.size(); x++) {
            BallBean smallBean = this.bigSmallBeans.get(x);
            boolean check = smallBean.isCheck();
            if (check) {
                /**计算共几单**/
                danshu = danshu + 1;
                /**计算共投入多少钱**/
                orderMoney = danshu * inPutMoney;
                smallBean.setBetAmount_d(inPutMoney);
                /**计算每单赢多少钱**/
                 playItemOdds = smallBean.getPlayItemOdds();
                if (TextUtils.isEmpty(playItemOdds)) {
                    playItemOdds = "0";
                }
                Float aFloat = Float.valueOf(playItemOdds);
                float v = aFloat * inPutMoney;
                winMoney = winMoney + v;
            }

        }
        float v = winMoney - orderMoney;
        String[] split = playItemOdds.split("\\.");
        int length = split.length;
        DecimalFormat decimalFormat = null;
        decimalFormat = new DecimalFormat("##0.00");
        if (length == 2) {
            int length1 = split[1].length();
            if (length1 == 2) {
                decimalFormat = new DecimalFormat("##0.00");
            } else if (length1 == 3) {
                decimalFormat = new DecimalFormat("##0.000");
            }
        }

        String format = decimalFormat.format(v);
        boolean isCheck = isCanBet();
        if (inPutMoney==0 || !isCheck){
            format="0";
            danshu=0;
            orderMoney=0;
        }
        tvAvailableMoney.setText(Html.fromHtml("可赢金额: <font color='#ff9307'>" + format + "元</font>"));
        tvBetMoney.setText(Html.fromHtml("<font color='#ff9307'>" + danshu + "</font>单" + ",<font color='#ff9307'>" + orderMoney + "</font>元"));
        tvBetMoney.setVisibility(VISIBLE);
        tvAvailableMoney.setVisibility(VISIBLE);
    }

    private boolean isCanBet() {
        boolean isCanBet = false;
        for (int i = 0; i < bigSmallBeans.size(); i++) {
            BallBean ballBean = bigSmallBeans.get(i);
            boolean check = ballBean.isCheck();
            if (check) {
                isCanBet = true;
            }
        }
        return isCanBet;
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public static void disableShowSoftInput(EditText editText) {
        Class<EditText> cls = EditText.class;
        Method method;
        try {
            method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            method.setAccessible(true);
            method.invoke(editText, false);
        } catch (Exception e) {

        }

    }

    public boolean canParseInt(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * 加载各种弹框布局
     */
//    private void initCirclePop() {
//        if (easyPopup == null) {
//            easyPopup = EasyPopup.create()
//                    .setContentView(getContext(), R.layout.ski_item_toast_fast_bet)
//                    .setAnimationStyle(R.style.BottomPopAnim)
//                    .setFocusAndOutsideEnable(true)
//                    .apply();
//        }
//    }


    private class AlarmBroadReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int id = DataCenter.getInstance().getCurLotteryId();
            long countDownTime = DataCenter.getInstance().getLotteryTime(id);
            String newPlanId = DataCenter.getInstance().getPlanId();
            if (countDownTime >= 0) {
                if (tvCountTime != null) {
                    String[] time = LotteryNoUtil.getTime(countDownTime);
                    tvCountTime.setText(time[0] + ":" + time[1] + ":" + time[2]);
                }
                if (tvPeriodFast != null && !TextUtils.isEmpty(newPlanId) && !newPlanId.equalsIgnoreCase(planId)) {
                    tvPeriodFast.setText(String.format("%s期", newPlanId));
                    planId = newPlanId;
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (alarmBroadReceive != null) {
            getContext().unregisterReceiver(alarmBroadReceive);
        }
    }
    public void showPeroidChangeDailog(String planId) {
        String s1 = tvTicketNameFast.getText().toString();
        String s = "icon" + "  " + s1 + ",期数变化\n" + "当前已进入" + planId + "期";
        SpannableString spannableString = new SpannableString(s);
        Drawable drawable = getContext().getResources().getDrawable(R.mipmap.ski_dialog_tishi_icon);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        MyImageSpan myImageSpan = new MyImageSpan(drawable);
        spannableString.setSpan(myImageSpan, 0, "icon".length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        AlertConfigurationBean bean = new AlertConfigurationBean();
        bean.setSpannableString(spannableString);bean.setLeftButtonText("放弃投注");bean.setRightButtonText("继续投注");
        new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
            @Override
            public void rightButton() { confirmBet(); }
            @Override
            public void leftButton() { }});
    }

    OnCloseListener mCloseListener;

    public void setCloseListener(OnCloseListener closeListener) {
        mCloseListener = closeListener;
    }
            public interface OnCloseListener {
                void closeDialog();
            }
}
