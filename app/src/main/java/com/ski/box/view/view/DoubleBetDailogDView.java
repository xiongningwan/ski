package com.ski.box.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.adapter.BettingDoubleConfirmAdapter;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.ski.box.view.view.keyboard.KeyBoardBean;
import com.ski.box.view.view.keyboard.NumsKeyBoardView;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;
import com.yb.core.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ski.box.ConstantValue.EVENT_CLEAN_XUAN_HAO_PAN;
import static com.ski.box.ConstantValue.EVENT_CLEAN_XUAN_HAO_PAN_SECTION;


/**
 * @time:2019/9/16 11:04
 * @describe:投注确认
 **/
public class DoubleBetDailogDView extends LinearLayout {
    private long mLastClickTime;
    private long mTimeInterval = 1000L;

    RecyclerView mBettingDataRecyclerView;
    public TextView betConfirm;
    TextView bettingInfo;
    CheckBox checkboxDanDhiKuang;
    RelativeLayout rlBottom;
    RelativeLayout rlhead;
    public NumsKeyBoardView numKeyboard;


    private EditText mEditText;
    BettingDoubleConfirmAdapter mBettingDataAdapter;


    private BettingConfirmListener bettingConfirmListener;
    /*键盘用*/
    private StringBuffer stringBuffer;
    private List<MkBetParamEntity.BetParamEntity> mDatas;


    public interface BettingConfirmListener {
        void onClosed();//隐藏

        void onConfirmBetting(View view);
    }

    public DoubleBetDailogDView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoubleBetDailogDView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoubleBetDailogDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        View.inflate(getContext(), R.layout.ski_bet_double_d_dailog, this);
        /*初始化*/
        mBettingDataRecyclerView = findViewById(R.id.rv_comfirm_list);
        betConfirm = findViewById(R.id.bet_confirm);
        bettingInfo = findViewById(R.id.betting_info);
        checkboxDanDhiKuang = findViewById(R.id.checkbox_danshi_close_kuang);
        rlBottom = findViewById(R.id.rl_bottom);
        rlhead = findViewById(R.id.rl_head);
        numKeyboard = findViewById(R.id.num_keyboard);
        ImageView windowClosed = findViewById(R.id.iv_window_closed);
        TextView windowClear = findViewById(R.id.tv_window_clear);
        /*设置 关闭弹框 清空弹框 点击投注 监听*/
        windowClosed.setOnClickListener(this::onClick);
        windowClear.setOnClickListener(this::onClick);
        betConfirm.setOnClickListener(this::onClick);

        checkboxDanDhiKuang.setText(LanguageUtil.getText(checkboxDanDhiKuang.getText().toString()));

        /*配置recycleview*/
        mBettingDataAdapter = new BettingDoubleConfirmAdapter(getContext());
        mBettingDataRecyclerView.setAdapter(mBettingDataAdapter);
        mBettingDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(getContext().getResources().getDrawable(R.drawable.ski_shape_all_lottery_line)));
        mBettingDataRecyclerView.addItemDecoration(divider);
        mBettingDataRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stringBuffer = new StringBuffer();
        /*加载监听*/
        initListener();
        mBettingDataAdapter.setOnEditTextViewClickListener(position -> {
            BettingDoubleConfirmAdapter.DoubleConfirmViewHolder viewHolder = (BettingDoubleConfirmAdapter.DoubleConfirmViewHolder) mBettingDataRecyclerView.findViewHolderForAdapterPosition(position);
            if (viewHolder != null) {
                mEditText = viewHolder.mEditText;
                stringBuffer.delete(0, stringBuffer.length());
                String string = mEditText.getText().toString();
                stringBuffer.append(string);
                numKeyboard.expand();
            }

        });
        /*监听键盘操作*/
        numKeyboard.setOnItemClickListener((position, bean) -> {
            int type = bean.getType();
            String num = bean.getNum();

            if (type == KeyBoardBean.DISMISS) {
                itemHasZero(1);
                numKeyboard.collapse();

            } else if (type == KeyBoardBean.DELETE) {
                int length = stringBuffer.length();
                if (length - 1 >= 0) {
                    stringBuffer.deleteCharAt(length - 1);
                    mEditText.setText(stringBuffer.toString());
                }
            } else if (type == KeyBoardBean.NUMBER) {
                int length = stringBuffer.length();
                if (length <= 6) {
                    if (length == 0) {
                        if ("0".equals(num)) {
                            //  new LotteryDialog().showCenterRemind(getContext(),"请输入有效数字");
                            ToastUtil.showInfo("请输入有效数字");
                            return;
                        }
                    }
                    stringBuffer.append(num);
                    String s = stringBuffer.toString();
                    mEditText.setText(s);
                }
            }
        });
        /**弹框出现时,设置最新的奖期号码**/
        DataCenter.getInstance().getBetParamEntity().setPlanNo(DataCenter.getInstance().getPlanId());
    }

    public int getHeadHeight() {
        return ScreenUtils.dip2px(58);
    }

    public int getBottomHeight() {
        return ScreenUtils.dip2px(120);
    }

    public int geItemHeight() {
        if (mBettingDataAdapter != null) {
            int itemHeight = mBettingDataAdapter.getItemHeight();
            return itemHeight;
        } else {
            return 0;
        }

    }

    public void itemHasZero(int type) {

        boolean needNotify = false;
        if (mDatas == null) {
            return;
        }
        for (int x = 0; x < mDatas.size(); x++) {
            MkBetParamEntity.BetParamEntity doubleConfirmBean = mDatas.get(x);
            long playItemAmount = doubleConfirmBean.getBetAmount_d();
            if (playItemAmount == 0) {
                doubleConfirmBean.setBetAmount_d(1);
                needNotify = true;
            }
        }
        if (needNotify) {
            mBettingDataAdapter.notifyDataSetChanged();
            calculateBalance();
        }

        if (type == 0) {
            /*投注*/
//            betConfirm.setClickable(false);
            bettingConfirmListener.onConfirmBetting(betConfirm);
        }
    }

    public void setData(MkBetParamEntity betParamEntity, BettingConfirmListener bettingConfirmListener) {
        this.bettingConfirmListener = bettingConfirmListener;
        mDatas = betParamEntity.getBet();
        /*刷新数据*/
        mBettingDataAdapter.refreshDatas(mDatas);
        /*计算余额*/
        calculateBalance();
    }

    private void initListener() {

        mBettingDataAdapter.setOnItemClickListener(new BettingDoubleConfirmAdapter.ItemClickListener() {
            @Override
            public void onItemRemoveClick(MkBetParamEntity.BetParamEntity betParamEntity) {
                ArrayList<Long> cleanPlayId = new ArrayList<>();
                cleanPlayId.add(Long.valueOf(betParamEntity.getPlayId()));
                /*Rxbus  通知页面 刷新数据*/
                RxBus.get().post(EVENT_CLEAN_XUAN_HAO_PAN_SECTION, cleanPlayId);

                /*计算余额*/
                calculateBalance();
                mBettingDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void changeMoney() {
                /*计算余额*/
                calculateBalance();
            }
        });

        checkboxDanDhiKuang.setOnCheckedChangeListener((buttonView, isChecked) -> {

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
                        checkboxDanDhiKuang.setChecked(false);

                    }
                });
            } else {
                SettingManager.setBetConfirmDialog(!isChecked);
            }
        });
        /*当最后一个的时候关闭弹框*/
        mBettingDataAdapter.setOnCloseDialogListener(new BettingDoubleConfirmAdapter.OnCloseDialogListener() {
            @Override
            public void closeDialog() {
                bettingConfirmListener.onClosed();
            }
        });

    }

    /**
     * 实时计算余额
     */
    @SuppressLint("DefaultLocale")
    private void calculateBalance() {

        /*投注总金额*/
        long totalBalan = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            MkBetParamEntity.BetParamEntity betParamEntity = mDatas.get(i);

            long betCount = betParamEntity.getBetCount();
            long betAmount_d = betParamEntity.getBetAmount_d();

            totalBalan += betCount * betAmount_d;
        }

        String str = new BigDecimal(String.valueOf(totalBalan)).toString();

        String danshu = mDatas.size() + "";
        String balances = str + "";
        String info = danshu + " " + LanguageUtil.getText("注单") + " , " + balances;
        int i = info.indexOf(danshu);
        int i1 = info.lastIndexOf(balances);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(getResources().getColor(R.color.ski_color_ff9307));
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(info);
        stringBuilder.setSpan(colorSpan, i, i + danshu.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(colorSpan1, i1, i1 + balances.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        bettingInfo.setText(stringBuilder);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_window_closed) {
            if (bettingConfirmListener != null) {
                bettingConfirmListener.onClosed();
            }
        } else if (id == R.id.tv_window_clear) {
            AlertConfigurationBean bean = new AlertConfigurationBean();
            bean.setDialogHeight(100);

            bean.setContent("确定清空所有内容吗？");
            bean.setLeftButtonText("取消");
            bean.setRightButtonText("清空");
            new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
                @Override
                public void rightButton() {

                    mDatas.clear();
                    mBettingDataAdapter.notifyDataSetChanged();
                    /*Rx bus  通知页面清空数据 刷新页面*/
                    RxBus.get().post(EVENT_CLEAN_XUAN_HAO_PAN, "clen");
                    bettingConfirmListener.onClosed();
                }

                @Override
                public void leftButton() {

                }
            });


        } else if (id == R.id.bet_confirm) {
            /*判读奖期是否变更*/
            String planId = DataCenter.getInstance().getPlanId();
            /*双面盘投注*/
            MkBetParamEntity betParamEntity = DataCenter.getInstance().getBetParamEntity();
            String planNo = betParamEntity.getPlanNo();
            if (!planId.equals(planNo)) {
                /*弹框提醒*/
                /*奖期变更*/
                long nowTime = System.currentTimeMillis();
                if (nowTime - mLastClickTime < mTimeInterval) {
                    // 快速点击事件
                    return;
                }
                mLastClickTime = nowTime;
                showPeroidChangeDailog(planId);
            } else if (bettingConfirmListener != null) {
                /*避免弹出限红后 键盘卡住*/
                boolean expanded = numKeyboard.isExpanded();
                if (expanded) {
                    numKeyboard.collapse();
                }
                itemHasZero(0);

            }
        }
    }

    private void showPeroidChangeDailog(String planId) {
        String ticketName = DataCenter.getInstance().getCurLotteryName();
        String s1 = LanguageUtil.getText("，期数变化")
                + "\n"
                + LanguageUtil.getText("当前已进入");
        String s2 = LanguageUtil.getText("期");
        String s3 = "放弃投注";
        String s4 = "继续投注";
        String s = ticketName + s1 + planId + s2;
        AlertConfigurationBean bean = new AlertConfigurationBean();
        bean.setContent(s);
        bean.setLeftButtonText(s3);
        bean.setRightButtonText(s4);
        new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
            @Override
            public void rightButton() {
                DataCenter.getInstance().getBetParamEntity().setPlanNo(planId);
                /*避免弹出限红后 键盘卡住*/
                boolean expanded = numKeyboard.isExpanded();
                if (expanded) {
                    numKeyboard.collapse();
                }
                itemHasZero(0);
            }

            @Override
            public void leftButton() {

            }
        });
    }
}
