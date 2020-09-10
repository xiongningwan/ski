package com.ski.box.mvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.ConstantValue;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.DateBean;
import com.ski.box.bean.ErrorBean;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryPlaySub;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.record.RecordRecent;
import com.ski.box.exception.CusConsumer;
import com.ski.box.mvp.contract.BetContract;
import com.ski.box.mvp.remote.BetModel;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.RecordModel;
import com.ski.box.mvp.remote.UserModel;
import com.ski.box.mvp.remote.imodel.IBetModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.ski.box.mvp.remote.imodel.IRecordModel;
import com.ski.box.mvp.remote.imodel.IUserModel;
import com.ski.box.utils.lottery.LimitRedUtil;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.utils.lottery.LotteryTimeUtil;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.fragment.bet.bottom.BetDanShiDialog;
import com.ski.box.view.fragment.bet.bottom.BetFuShiDialog;
import com.ski.box.view.view.BetBottomView;
import com.ski.box.view.view.dialog.LoadingDialog;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.StringUtils;
import com.yb.core.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Headers;

import static com.ski.box.ConstantValue.EVENT_CLEAN_XUAN_HAO_PAN;
import static com.ski.box.ConstantValue.EVENT_GET_UNSETTLE_LIST;
import static com.ski.box.ConstantValue.EVENT_UPDATE_RECENT_NO;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;


public class BetPresenter extends RxPresenter<BetContract.View> implements BetContract.Presenter {

    private final ILotteryModel mLotteryModel;
    private final IBetModel mBetModel;
    private final IRecordModel mRecordModel;
    private long mLastClickTime;
    private long mTimeInterval = 300L;
    private LoadingDialog mLoadingDialog;
    //    private LotteryDialog mTipDialog;
    private BetDanShiDialog mBetDanShiDialog; // 立即投注单式弹窗
    private BetFuShiDialog mBetFuShiDialog; // 立即投注复试弹窗
    public int mSaleStatus = 0;

    public BetPresenter(Context context) {
        super(context);
        mLotteryModel = new LotteryModel();
        mBetModel = new BetModel();
        mRecordModel = new RecordModel();
//        mTipDialog = new LotteryDialog();
        mLoadingDialog = new LoadingDialog(context);
    }

    @Override
    public void setSaleStatus(int saleStatus) {
        mSaleStatus = saleStatus;
    }


    @Override
    public void getGetHeadTicketInfo(String ids) {
        Disposable disposable2 = mLotteryModel.getGetHeadTicketInfo(new Consumer<List<TicketLotteryTimeBean>>() {
            @Override
            public void accept(List<TicketLotteryTimeBean> data) throws Exception {
                if (data != null && data.size() > 0) {
                    TicketLotteryTimeBean bean = data.get(0);
                    Disposable disposable = mLotteryModel.getServiceTime(new Consumer<retrofit2.Response<DateBean>>() {
                        @Override
                        public void accept(retrofit2.Response<DateBean> dateBeanResponse) throws Exception {
                            Headers headers = dateBeanResponse.headers();
                            String s = headers.get("date");
                            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                            Date date = format.parse(s);
                            long serverTime = date.getTime();
                            DataCenter.getInstance().setServerTime(serverTime);
                            for (TicketLotteryTimeBean bean : data) {
                                long endTime = bean.getEndTime() /** 1000*/;
//                                long startTime = DataCenter.getInstance().getServerTime();
                                long countDownTime = Math.abs(endTime - serverTime);
                                LotteryTimeUtil.addLotteryIds(bean.getTicketId());
                                DataCenter.getInstance().updateLotteryTime(bean.getTicketId(), bean.getPlanId(), countDownTime);
                                mView.onHeadTicketInfoResult(bean);
                            }
                        }
                    }, new BaseConsumer(false, false) {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            super.accept(throwable);
                        }
                    });
                    addDisposable(disposable);
                }
            }
        }, new BaseConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
            }
        }, ids);
        addDisposable(disposable2);
    }

    @Override
    public void getLastOpenResult(int lotteryId, int num) {
        try {
            Disposable disposable = mLotteryModel.getLotteryNumHistory(new Consumer<List<LotteryNumBean>>() {

                @Override
                public void accept(List<LotteryNumBean> list) throws Exception {
                    if (list == null || 0 == list.size()) {
                        return;
                    }
                    for (LotteryNumBean bean : list) {
                        bean.setItemType(LotteryUtil.getSerIdByLotteryId(lotteryId));
                    }
                    mView.onOpenResult(list);
                }
            }, new BaseConsumer(false, false) {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    super.accept(throwable);
                }
            }, String.valueOf(lotteryId), String.valueOf(num));
            addDisposable(disposable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showBetRightNow(BetBottomView bottomView) {
        if (checkSellingStatus()) return;
        View betView = bottomView.getBetView();
        if (checkBetNoCount(bottomView.getBetView())) return;
        // 判断有没有输入money
        if (checkInputAmount(bottomView.getAmount(), betView)) return;
        /*判断是否弹框*/
        boolean showDialog = SettingManager.isBetConfirmDialog();
        MkBetParamEntity betParamEntity = DataCenter.getInstance().getBetParamEntity();
        boolean b = betParamEntity.getPlayMode() == LOTTERY_PLAY_MODE_DOUBLE;

        String planId = DataCenter.getInstance().getPlanId();
        betParamEntity.setPlanNo(planId);
        if (b) {
            doubleBetNow(showDialog, bottomView);
        } else {
            /*标准盘*/
            //   if (exceedMinAmount(mContext, betView)) return;
            //    standardBetNow(showDialog, bottomView, betParamEntity);
        }
    }

    // 判断有没有生成数据
    private boolean checkBetNoCount(View view) {
        MkBetParamEntity betParamEntity = DataCenter.getInstance().getBetParamEntity();
        List<MkBetParamEntity.BetParamEntity> bets = betParamEntity.getBet();
        if (bets.size() == 0) {
//            mTipDialog.showLightReminder(mContext, view, "至少选中1注");
            ToastUtil.showInfo("至少选中1注");
            return true;
        }
        if (bets.size() == 1) {
            if (bets.get(0).getBetCount() == 0) {
                ToastUtil.showInfo("至少选中1注");
//                mTipDialog.showLightReminder(mContext, view, "至少选中1注");
                return true;
            }
        }
        return false;
    }

    // 判断有没有输入money
    private boolean checkInputAmount(long amount, View view) {
        if (amount <= 0) {
            ToastUtil.showInfo("请输入金额");
//            mTipDialog.showLightReminder(mContext, view, "请输入金额");
            return true;
        }
        return false;
    }

    // 检查彩种状态
    private boolean checkSellingStatus() {
        // 判读是否停售
        if (mSaleStatus != 0) {
            ToastUtil.showInfo("该彩种暂停销售,请稍后再次尝试");
            return true;
        }
        return false;
    }


    @Override
    public void doubleBetNow(boolean showDialog, BetBottomView bottomView) {
        /*是否弹框*/
        if (showDialog) {
            /*双面盘*/
            boolean single = DataCenter.getInstance().getBetParamEntity().getBet().get(0).isSingle();
            if (single) {
                /*弹框 单式*/
                mBetDanShiDialog = new BetDanShiDialog(mContext);
                mBetDanShiDialog.show();
            } else {
                /*弹框复试*/
                mBetFuShiDialog = new BetFuShiDialog(mContext);
                mBetFuShiDialog.show();
            }

        } else {
            /*判读奖期是否变更*/
            String planId = DataCenter.getInstance().getPlanId();
            /*双面盘投注*/
            MkBetParamEntity betParamEntity = DataCenter.getInstance().getBetParamEntity();
            String planNo = betParamEntity.getPlanNo();
            if (!planId.equals(planNo)) {
                /*奖期变更*/
                betParamEntity.setPlanNo(planId);
            }
            betParamEntity.setBetView(bottomView.getBetView());
            betSubmit(betParamEntity);
        }
    }


    /**
     * 投注追号
     *
     * @param entity
     */
    @Override
    public void betSubmit(MkBetParamEntity entity) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < mTimeInterval) {
            // 快速点击事件
            return;
        }
        mLastClickTime = nowTime;
        View view = entity.getBetView();
        if (view == null) {
            LogUtils.e("点击提交按钮view为空");
            return;
        }
        view.setEnabled(false);
        view.setClickable(false);
        // loading
        showLoading(entity);
        int buyMode = entity.getBuyMode();
        if (buyMode != 0) {
            /*追号*/
            mBetModel.doubleTraceBet(new Consumer<Object>() {
                @Override
                public void accept(Object s) throws Exception {
                    view.setEnabled(true);
                    view.setClickable(true);
                    showSuccessLoading();
                    closeDialog(entity);
                    RxBus.get().post(EVENT_GET_UNSETTLE_LIST, "EVENT_NEW_OPEN_RESULT_UPDATE");
                    // 清理选号和数据中心选中数据
                    clearBetNUMAndBetEntity();
//                    RxBus.get().post(EVENT_TYPE_BALANCE_UPDATE, "EVENT_TYPE_BALANCE_UPDATE");
                }
            }, new CusConsumer(false, false) {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    //  super.accept(throwable);
                    view.setEnabled(true);
                    view.setClickable(true);
                    dismissLoading();
                    limitRed(view, entity, throwable.getMessage());
                }
            }, entity);

            return;
        }

        /*投注*/
        Disposable disposable = mBetModel.doubleBet(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                //  String s = new Gson().toJson(o);
                view.setEnabled(true);
                view.setClickable(true);
                showSuccessLoading();
                closeDialog(entity);
                RxBus.get().post(EVENT_GET_UNSETTLE_LIST, "EVENT_NEW_OPEN_RESULT_UPDATE");
//                RxBus.get().post(EVENT_TYPE_BALANCE_UPDATE, "EVENT_TYPE_BALANCE_UPDATE");
                // 清理选号和数据中心选中数据
                clearBetNUMAndBetEntity();
            }

        }, new CusConsumer(false, false) {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // super.accept(throwable);
                view.setEnabled(true);
                view.setClickable(true);
                dismissLoading();
                limitRed(view, entity, throwable.getMessage());
            }
        }, entity);
        addDisposable(disposable);
    }

    private void clearBetNUMAndBetEntity() {
        //清空选好盘
        RxBus.get().post(EVENT_CLEAN_XUAN_HAO_PAN, "clean");
        DataCenter.getInstance().cleanBetData();
    }

    private void showLoading(MkBetParamEntity entity) {
        String msg;
        if (entity.getBuyMode() > 0) {
            msg = "追号提交中...";
        } else {
            msg = "投注提交中...";
        }
        if (mLoadingDialog != null) {
            mLoadingDialog.setReloading(msg);
            mLoadingDialog.show();
        }
    }

    private void showSuccessLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.setLoadingSuccess("提交成功");
            new Handler().postDelayed(() -> {
                dismissLoading();
            }, 500);
        }
    }

    private void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    private void closeDialog(MkBetParamEntity entity) {
        int from = entity.getFrom();
        if (from == ConstantValue.BET_FROM_BASKET) {
            //清空购彩篮数据
//            mBasketData.getBet().clear();
//            RxBus.get().post(EVENT_UPDATE_SHOP_NUM, 0);

        }
        //关闭弹框
        onDestroyDialog();
    }

    private void onDestroyDialog() {
//        if (mQuickBetDialog != null) {
//            mQuickBetDialog.dismiss();
//            mQuickBetDialog = null;
//        }
//        if (mShoppingDialog != null) {
//            mShoppingDialog.dismiss();
//            mShoppingDialog = null;
//        }
//        if (standardShoppingDialog != null) {
//            if (standardShoppingDialog.getmConfirmDialog() != null) {
//                standardShoppingDialog.getmConfirmDialog().dismiss();
//            }
//
//            standardShoppingDialog.dismiss();
//            standardShoppingDialog = null;
//        }
//        if (standardBetDiglog != null) {
//            if (standardBetDiglog.getBetDialog() != null) {
//                standardBetDiglog.getBetDialog().dismiss();
//
//            }
//
//        }
//        if (mChaseDialog != null) {
//            ChaseNumConfirmDialog chaseNumBetConfirmDailog = mChaseDialog.getChaseConfrimDialog();
//            if (chaseNumBetConfirmDailog != null) {
//                chaseNumBetConfirmDailog.getzDialog().dismiss();
//            }
//
//            mChaseDialog.dismiss();
//            mChaseDialog = null;
//        }
        if (mBetDanShiDialog != null) {
            mBetDanShiDialog.onDismiss(false);
            mBetDanShiDialog = null;
        }
        if (mBetFuShiDialog != null) {
            mBetFuShiDialog.onDismiss(false);
            mBetFuShiDialog = null;
        }
        /*关闭长龙追号框*/
//        if (longDragonBetDialog != null) {
//            longDragonBetDialog.dismiss();
//            longDragonBetDialog = null;
//        }
    }

    private void limitRed(View view, MkBetParamEntity entity, String exception) {
        if (!TextUtils.isEmpty(exception) && exception.contains("code") && exception.contains("message") && exception.contains("type")) {
            ErrorBean errorBean = new Gson().fromJson(exception, ErrorBean.class);
            if (10069 == errorBean.getCode()) {
                /*限红*/
                ArrayList<RedLimitBean> redLimitBeans = LimitRedUtil.isMeetBettingInterval(entity.getBet());
                if (null != redLimitBeans && view != null) {
                    if (0 == redLimitBeans.size()) {
                        try {
                            List<Long> playIds = new Gson().fromJson(errorBean.getMessage(), new TypeToken<List<Long>>() {
                            }.getType());
                            redLimitBeans = getRedLimitBeans_ByRemotePlayIds(playIds);
                            new LotteryDialog().showRedLimitBubbleLayout(view, redLimitBeans);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        new LotteryDialog().showRedLimitBubbleLayout(view, redLimitBeans);
                    }
                }

            } else if (10020 == errorBean.getCode()) {
                AlertConfigurationBean bean = new AlertConfigurationBean();
                bean.setContent("【温馨提示】" + "\n" + "您的余额不足,请前往充值");
                bean.setRightButtonText("确定");
                new LotteryDialog().showAlertDialog(mContext, bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
                    @Override
                    public void rightButton() {

                    }

                    @Override
                    public void leftButton() {
                    }
                });

            } else if (10006 == errorBean.getCode()) {
            }
        }
    }


    private ArrayList<RedLimitBean> getRedLimitBeans_ByRemotePlayIds(List<Long> playIds) {
        ArrayList<RedLimitBean> limitBeans = new ArrayList<>();
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        int mode = LOTTERY_PLAY_MODE_DOUBLE;
        List<LotteryPlayStart> lotteryPlayStarts = DataCenter.getInstance().getRemotePlay(lotteryId, mode);
        for (int x = 0; x < playIds.size(); x++) {
            long playId = playIds.get(x);
            for (int i = 0; i < lotteryPlayStarts.size(); i++) {
                LotteryPlayStart lotteryPlayStart = lotteryPlayStarts.get(i);
                List<LotteryPlaySub> lotteryPlaySubs = lotteryPlayStart.getSubPlays();
                for (int j = 0; j < lotteryPlaySubs.size(); j++) {
                    LotteryPlaySub lotteryPlaySub = lotteryPlaySubs.get(j);
                    List<LotteryPlayEnd> ends = lotteryPlaySub.getLotteryPlayEnds();
                    for (int k = 0; k < ends.size(); k++) {
                        LotteryPlayEnd lotteryPlayEnd = ends.get(k);
                        List<LotteryPlay> lotteryPlays = lotteryPlayEnd.getLotteryPlays();
                        for (int m = 0; m < lotteryPlays.size(); m++) {
                            LotteryPlay lotteryPlay = lotteryPlays.get(m);
                            if (lotteryPlay.getId() == playId) {
                                RedLimitBean redLimitBean = new RedLimitBean();
                                String playName = lotteryPlay.getTag();
                                String playItemName = lotteryPlay.getName();
                                String playItemId = String.valueOf(lotteryPlay.getId());
                                redLimitBean.setPlayItemId(playItemId);
                                redLimitBean.setPlayName(playName);
                                redLimitBean.setPlayItemName(playItemName);
                                limitBeans.add(redLimitBean);
                            }

                        }
                    }
                }
            }
        }
        return limitBeans;
    }


    @Override
    public void getRecentBetList(String memberId, String status) {
        Disposable disposable = mRecordModel.getBettingRecordTop(new Consumer<RecordRecent>() {
            @Override
            public void accept(RecordRecent bean) throws Exception {
                if ("0".equals(status) && bean != null) {
                    RxBus.get().post(EVENT_UPDATE_RECENT_NO, bean);
                }
            }
        }, memberId, status);
        addDisposable(disposable);
    }

}


