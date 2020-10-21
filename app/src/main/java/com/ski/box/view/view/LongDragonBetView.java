package com.ski.box.view.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
import com.ski.box.bean.LongDragonPushInfoEntity;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.bean.lottery.LotteryConstant;
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
 * 长龙
 */
public class LongDragonBetView extends LinearLayout implements View.OnClickListener {

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
    private ImageView ivDelete;

    private boolean isSingle;
    private int mInPutMoney;
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
//    private TextView tvZhuihao;
    //    private long countDownTime;
    private int ticketId;
    private MkBetParamEntity betEntity;

    private LotteryDialog mTipDialog = new LotteryDialog();
    private Typeface pingFangType;
    private CustomConlationLayout relayout;

    public LongDragonBetView(Context context) {
        super(context);
        initView();
    }

    public LongDragonBetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LongDragonBetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        pingFangType = Typeface.createFromAsset(getContext().getAssets(), "fonts/pingfangscregular.ttf");
        View inflate = View.inflate(getContext(), R.layout.ski_long_dragon_bet_dailog, this);
        recyclerView = inflate.findViewById(R.id.recyc_fast_dailog);
        relayout = inflate.findViewById(R.id.constraint);
        ivTouzhuDragonDown = inflate.findViewById(R.id.iv_touzhu_dragon_down);
        tvTicketNameFast = inflate.findViewById(R.id.tv_ticketName_fast);
        tvPeriodFast = inflate.findViewById(R.id.tv_period_fast);
        tvPalynameFast = inflate.findViewById(R.id.tv_palyname_fast);
        etInputMoneyFast = inflate.findViewById(R.id.et_input_money_fast);
        balanceFast = inflate.findViewById(R.id.balance_fast);
        tvBetMoney = inflate.findViewById(R.id.tv_bet_money_fast);
        tvAvailableMoney = inflate.findViewById(R.id.tv_available_money);
        tvBetConfirm = inflate.findViewById(R.id.tv_bet_confirm);
        tvAvailableBalance = inflate.findViewById(R.id.tv_available_balance);
        clBetLayout = inflate.findViewById(R.id.cl_bet);
        tvCountTime = inflate.findViewById(R.id.tv_fast_betting_count_down);
//        tvZhuihao = inflate.findViewById(R.id.tv_zhuihao);
         ivDelete = inflate.findViewById(R.id.iv_delete);
        TextView tvTitle = inflate.findViewById(R.id.tv_title);
        TextView tvBalance = inflate.findViewById(R.id.tv_balance);

        tvTitle.setTypeface(pingFangType);
        tvTicketNameFast.setTypeface(pingFangType);
        tvPeriodFast.setTypeface(pingFangType);
        tvCountTime.setTypeface(pingFangType);
        tvPalynameFast.setTypeface(pingFangType);
        etInputMoneyFast.setTypeface(pingFangType);
        tvBalance.setTypeface(pingFangType);
        balanceFast.setTypeface(pingFangType);
        tvBetMoney.setTypeface(pingFangType);
        tvAvailableMoney.setTypeface(pingFangType);
//        tvZhuihao.setTypeface(pingFangType);
        tvBetConfirm.setTypeface(pingFangType);
        tvAvailableBalance.setTypeface(pingFangType);


        tv00 = inflate.findViewById(R.id.tv_00);
        tv01 = inflate.findViewById(R.id.tv_01);
        tv02 = inflate.findViewById(R.id.tv_02);
        tv03 = inflate.findViewById(R.id.tv_03);
        tv04 = inflate.findViewById(R.id.tv_04);
        tv05 = inflate.findViewById(R.id.tv_05);
        tv06 = inflate.findViewById(R.id.tv_06);
        tv07 = inflate.findViewById(R.id.tv_07);
        tv08 = inflate.findViewById(R.id.tv_08);
        tv09 = inflate.findViewById(R.id.tv_09);
        rlDown = inflate.findViewById(R.id.rlDown);
        tvDelete = inflate.findViewById(R.id.tv_delete);
        view01 = inflate.findViewById(R.id.view_01);
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
        ivDelete.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
//        tvZhuihao.setOnClickListener(this);
        clBetLayout.setOnClickListener(this);
        ivTouzhuDragonDown.setOnClickListener(this);
        fastDialogAdapter = new FastDialogAdapter();
        recyclerView.setAdapter(fastDialogAdapter);
        fastDialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                BallBean smallBean = bigSmallBeans.get(position);
                boolean isCheck = !smallBean.isCheck();
                if (isSingle) {
                    /**是单选**/
                    if (isCheck) {
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
                    smallBean.setCheck(isCheck);
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

        tvAvailableBalance.setVisibility(mType == 1 ? VISIBLE : GONE);
        tvAvailableBalance.setText("可用余额：" + DataCenter.getInstance().getBalance());

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
                    mInPutMoney = Integer.parseInt(editable.toString());
                    etInputMoneyFast.setSelection(editable.toString().length());
                    calculation();
                    if (mInPutMoney!=0) {
                        clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_pub_bg_setting_trend));
                        tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_white ));
                    }else{
                        clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_record_quick_bet_normal));
                        tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_color_bebebe));
                    }
                } else {
                    mInPutMoney = 0;
                    calculation();
                    clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_record_quick_bet_normal));
                    tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_color_bebebe));
                }
            }
        });

        disableShowSoftInput(etInputMoneyFast);
        etInputMoneyFast.setOnTouchListener(new OnTouchListener() {
            //按住和松开的标识
            int touch_flag = 0;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touch_flag++;
                if (touch_flag == 2) {
                    touch_flag = 0;
                    //自己的业务
                    int visibility = view01.getVisibility();
                    if (visibility != View.VISIBLE) {
                        view01.setVisibility(VISIBLE);



                    }

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String s = etInputMoneyFast.getText().toString();
                        if (null != s && !s.isEmpty()) {
                            etInputMoneyFast.setSelection(0, s.length());

                        }
                    }
                }, 300);
                return false;
            }
        });
        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view01.setVisibility(GONE);
            }
        });
        relayout.addOnTouchListener(new CustomConlationLayout.OnTouchYListener() {
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
    }

    public void confirmBet() {
        boolean isCanBet = isCanBet();
        if (!isCanBet) {
            mTipDialog.showLightReminder(getContext(), clBetLayout, "至少选中1注");
            return;
        }
        DataCenter.getInstance().setBallBeanList(bigSmallBeans);
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
                mkBetParamEntity.setGroupName(tvPalynameFast.getText().toString());
                mkBetParamEntity.setContent(ballBean.getPlayItemName());
                mkBetParamEntity.setBetAmount_d(ballBean.getBetAmount_d());
                mkBetParamEntity.setPlayOdds(ballBean.getPlayItemOdds());
                if (TextUtils.isEmpty(ballBean.getPlayItemCode())) {
                    return;
                }
                bet.add(mkBetParamEntity);
            }
        }
        betEntity.setPlayMode(LOTTERY_PLAY_MODE_DOUBLE);
        betEntity.setBet(bet);
        betEntity.setBetView(clBetLayout);
        ArrayList<RedLimitBean> interval = LimitRedUtil.isMeetBettingInterval(betEntity.getBet());
        if (interval.size() > 0) {
            new LotteryDialog().showRedLimitBubbleLayout(clBetLayout, interval);
        } else {
            RxBus.get().post(EVENT_REQUEST_BET_SUBMIT, betEntity);

        }
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

//    public void confirmChaseNum() {
//        boolean isCanBet = isCanBet();
//        if (!isCanBet) {
//            mTipDialog.showLightReminder(getContext(), tvZhuihao, "至少选中1注");
//            return;
//        }
//
//        betEntity = new MkBetParamEntity();
//        betEntity.setPlanNo(planId);
//        betEntity.setTicketId(ticketId);
//        List<MkBetParamEntity.BetParamEntity> bet = DataCenter.getInstance().getBetParamEntity().getBet();
//        bet.clear();
//        List<BallBean> ballBeanList = DataCenter.getInstance().getBallBeanList();
//        for (int i = 0; i < ballBeanList.size(); i++) {
//            BallBean ballBean = ballBeanList.get(i);
//            boolean check = ballBean.isCheck();
//            if (check) {
//                MkBetParamEntity.BetParamEntity mkBetParamEntity = new MkBetParamEntity.BetParamEntity();
//                mkBetParamEntity.setPlayId(ballBean.getPlayItemId());
//                mkBetParamEntity.setBetNum(ballBean.getPlayItemCode());
//                mkBetParamEntity.setBetCount(1);
//                mkBetParamEntity.setBetAmount_d(ballBean.getBetAmount_d());
//                mkBetParamEntity.setContent(ballBean.getPlayItemName());
//                mkBetParamEntity.setGroupName(tvPalynameFast.getText().toString());
//                mkBetParamEntity.setSingle(true);
//                if (TextUtils.isEmpty(ballBean.getPlayItemCode())) {
//                    return;
//                }
//                bet.add(mkBetParamEntity);
//            }
//        }
//        betEntity.setBet(bet);
//        ArrayList<RedLimitBean> interval = LoLimitRedUtils.isMeetBettingInterval(betEntity.getBet());
//        if (interval.size() > 0) {
//            new LotteryDialog().showRedLimitBubbleLayout(tvZhuihao, interval);
//        } else {
//            RxBus.get().post(EVENT_TYPE_CHASE_NO_CLICK, this);
//            if (onDismissListener != null) {
//                onDismissListener.onDismiss();
//            }
//        }
//
//    }
    private String oldPlanId;

    /**
     * 刷新数据
     */
    public void freshData(LongDragonPushInfoEntity entity) {
        ticketId = entity.getTicketId().intValue();
        oldPlanId = DataCenter.getInstance().getLotteryPlanId(ticketId);
        String menuTitle = entity.getPlayName();
        tvTicketNameFast.setText(entity.getTicketName());
        Integer curLotteryId = entity.getSeriesId().intValue();
        if (LotteryConstant.SER_ID_SSC == curLotteryId) {
            menuTitle = "总和龙虎".equalsIgnoreCase(menuTitle) ? "龙虎和" : menuTitle;
        }
        tvPalynameFast.setText(menuTitle);
        if (DataCenter.getInstance().getBalance() != null) {
            setBalanceStr(DataCenter.getInstance().getBalance().getBalance());
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
        etInputMoneyFast.setText("");
    }

    private boolean allSelect() {
        int selectionStart = etInputMoneyFast.getSelectionStart();
        int selectionEnd = etInputMoneyFast.getSelectionEnd();
        if (selectionStart == 0 && selectionEnd > 0) {

            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        String string = etInputMoneyFast.getText().toString();
        if (id == R.id.tv_00) {
            String s = etInputMoneyFast.getText().toString();
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("0");
                return;
            }
//            if (!s.isEmpty()) {
                etInputMoneyFast.setText(string + "0");

//            }
        } else if (id == R.id.tv_01) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("1");
                return;
            }

            etInputMoneyFast.setText(string + "1");
        } else if (id == R.id.tv_02) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("2");
                return;
            }
            etInputMoneyFast.setText(string + "2");
        } else if (id == R.id.tv_03) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("3");
                return;
            }
            etInputMoneyFast.setText(string + "3");
        } else if (id == R.id.tv_04) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("4");
                return;
            }
            etInputMoneyFast.setText(string + "4");
        } else if (id == R.id.tv_05) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("5");
                return;
            }
            etInputMoneyFast.setText(string + "5");
        } else if (id == R.id.tv_06) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("6");
                return;
            }
            etInputMoneyFast.setText(string + "6");
        } else if (id == R.id.tv_07) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("7");
                return;
            }
            etInputMoneyFast.setText(string + "7");
        } else if (id == R.id.tv_08) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("8");
                return;
            }
            etInputMoneyFast.setText(string + "8");
        } else if (id == R.id.tv_09) {
            boolean b = allSelect();
            if (b) {
                etInputMoneyFast.setText("9");
                return;
            }
            etInputMoneyFast.setText(string + "9");
        } else if (id == R.id.iv_touzhu_dragon_down) {
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }

        } else if (id == R.id.tv_delete) {
            if (string != null && string.length() > 0) {
                int selectionStart = etInputMoneyFast.getSelectionStart();
                if (selectionStart == 0) {
                    etInputMoneyFast.setText("");
                } else {
                    String substring = string.substring(0, string.length() - 1);
                    etInputMoneyFast.setText(substring);
                }


            }
        } else if (id == R.id.rlDown) {
            view01.setVisibility(GONE);
        }
//        else if (id == R.id.tv_zhuihao) {
//            /*追号*/
//            try {
//                String s = etInputMoneyFast.getText().toString();
//                Integer integer = Integer.valueOf(s);
//
//                confirmChaseNum();
//
//
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                boolean isCanBet = isCanBet();
//                if (!isCanBet) {
//                    mTipDialog.showLightReminder(view.getContext(), view, "至少选中1注");
//                    return;
//                }
//                mTipDialog.showLightReminder(view.getContext(), view, "请输入金额");
//            }
//        }
        else if (id == R.id.cl_bet) {
            /*投注*/
            try {
                String s = etInputMoneyFast.getText().toString();
                Integer integer = Integer.valueOf(s);
                if (!TextUtils.isEmpty(oldPlanId) && !oldPlanId.equalsIgnoreCase(DataCenter.getInstance().getLotteryPlanId(ticketId)) ) {
                    boolean isCanBet = isCanBet();
                    if (!isCanBet) {
                        mTipDialog.showLightReminder(view.getContext(), clBetLayout, "至少选中1注");
                        return;
                    }
                    showPeroidChangeDailog(planId);
                } else {
                    confirmBet();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                boolean isCanBet = isCanBet();
                if (!isCanBet) {
                    mTipDialog.showLightReminder(view.getContext(), clBetLayout, "至少选中1注");
                    return;
                }
                mTipDialog.showLightReminder(view.getContext(), clBetLayout, "请输入金额");
            }

        } else if (id == R.id.iv_delete) {
            etInputMoneyFast.setText("");

        }


        String str = etInputMoneyFast.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            etInputMoneyFast.setSelection(str.length());
            ivDelete.setVisibility(VISIBLE);
        } else {
            ivDelete.setVisibility(GONE);
        }

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
        protected void convert(@NotNull BaseViewHolder holder,final  @org.jetbrains.annotations.Nullable BallBean smallBean) {
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
//                mLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), R.drawable.ski_pub_bg_setting_trend));
                mLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), R.drawable.ski_quick_bet_back_green));
                checkBox.setTextColor(ContextCompat.getColor(AppUtil.getContext(),R.color.ski_white));
            }else{
                mLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(), R.mipmap.ski_ic_fast_bet_normal));
                checkBox.setTextColor(ContextCompat.getColor(AppUtil.getContext(),R.color.ski_color_bebebe));
            }
        }
    }


    /**
     * 计算单数 投注金额等
     */
    String playItemOdds = "";

    private void calculation() {

        boolean canBet = isCanBet();
        String str = etInputMoneyFast.getText().toString();
        boolean isEmpty = !TextUtils.isEmpty(str);
        boolean b = canBet && isEmpty && mInPutMoney!=0;
        if(b) {

        }
        if (b){
            clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_pub_bg_setting_trend));
            tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_white ));

        }else{
            clBetLayout.setBackground(ContextCompat.getDrawable(AppUtil.getContext(),R.drawable.ski_record_quick_bet_normal));
            tvBetConfirm.setTextColor(ContextCompat.getColor(AppUtil.getContext(),  R.color.ski_color_bebebe));
        }

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
                orderMoney = danshu * mInPutMoney;
                smallBean.setBetAmount_d(mInPutMoney);
                /**计算每单赢多少钱**/
                playItemOdds = smallBean.getPlayItemOdds();
                if (TextUtils.isEmpty(playItemOdds)) {
                    playItemOdds = "0";
                }
                Float aFloat = Float.valueOf(playItemOdds);
                float v = aFloat * mInPutMoney;
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
        tvAvailableMoney.setText(Html.fromHtml("可赢金额: <font color='#ff9307'>" + format + "元</font>"));
        tvBetMoney.setText(Html.fromHtml("<font color='#ff9307'>" + danshu + "</font>单" + ",<font color='#ff9307'>" + orderMoney + "</font>元"));
     /*   tvBetMoney.setVisibility(VISIBLE);
        tvAvailableMoney.setVisibility(VISIBLE);*/
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
        if (str == null) {
            return false;
        }
        return str.matches("\\d+");
    }


    private class AlarmBroadReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            long time = DataCenter.getInstance().getLotteryTime(ticketId);
            String newPlanId = DataCenter.getInstance().getLotteryPlanId(ticketId);
            if (tvCountTime != null) {
                String[] timeArr = LotteryNoUtil.getTime(time);
                tvCountTime.setText(timeArr[0] + ":" + timeArr[1] + ":" + timeArr[2]);
            }
            if (tvPeriodFast != null && !TextUtils.isEmpty(newPlanId) && !newPlanId.equalsIgnoreCase(planId)) {
                planId = newPlanId;
            }
            tvPeriodFast.setText(String.format("%s期", planId));
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
        bean.setSpannableString(spannableString);
        bean.setLeftButtonText("放弃投注");
        bean.setRightButtonText("继续投注");
        new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
            @Override
            public void rightButton() {
                confirmBet();
            }

            @Override
            public void leftButton() {

            }
        });
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getTicketName() {
        return tvTicketNameFast.getText().toString();
    }

    public MkBetParamEntity getBetEntity() {
        return betEntity;
    }

    public OnDismissListener onDismissListener;

    public interface OnDismissListener {
        void onDismiss();
    }

    public void setOnDismissListener(OnDismissListener listner) {
        onDismissListener = listner;
    }





}
