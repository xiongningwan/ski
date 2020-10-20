package com.ski.box.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.TicketLotteryTimeBean;
import com.ski.box.utils.AnimationUtil;
import com.ski.box.view.fragment.bet.top.TopHistoryFragment;
import com.ski.box.view.view.BetBottomView;
import com.yb.core.utils.FragmentUtils;
import com.yb.core.utils.LogUtils;

import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BET_ACTIVITY_FINISH;
import static com.ski.box.ConstantValue.EVENT_BET_TOP_CHOSE_LOTTERY_DIALOG_START_OPEN;
import static com.ski.box.ConstantValue.EVENT_GET_UNSETTLE_LIST;
import static com.ski.box.ConstantValue.EVENT_OPEN_RESULT_UPDATE;
import static com.ski.box.ConstantValue.EVENT_REQUEST_BET_SUBMIT;
import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_SET;
import static com.ski.box.ConstantValue.EVENT_TYPE_BET_RiGHT_NOW_CLICK;
import static com.ski.box.ConstantValue.EVENT_TYPE_CHANGE_OPEN_RESULT_HISTORY;
import static com.ski.box.ConstantValue.EVENT_TYPE_CLOSE_RESULT_HISTORY;
import static com.ski.box.ConstantValue.EVENT_TYPE_QUICK_BET_CLICK;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;

public class BetActivity extends BaseBetActivity {
    protected String mPlanId;
    private UpdateResultRunnable mUpdateResultRunnable;
    private List<LotteryNumBean> mLotteryNumList;
    private TopHistoryFragment mHistoryDialog;

    public static void startBetActivity(Context context, int lotteryId, String lotterName) {
        Intent intent = new Intent(context, BetActivity.class);
        intent.putExtra(KEY_LOTTERY_ID, lotteryId);
        intent.putExtra(KEY_LOTTERY_NAME, lotterName);
        context.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        mHandler.removeCallbacks(mUpdateResultRunnable);
        mHandler.removeCallbacks(mLoadTopLotteryRunnable);
        mHandler.removeCallbacks(mInitFragmentRunnable);
        mHandler.removeCallbacks(mUnSettleRunnable);
    }


    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        RxBus.get().register(this);
        super.initViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mUpdateResultRunnable = new UpdateResultRunnable();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getGetHeadTicketInfo(String.valueOf(mLotteryId));
        mPresenter.getLastOpenResult(mLotteryId, 100);
        RxBus.get().post(EVENT_GET_UNSETTLE_LIST, "推送未结算注单");
    }

    @Override
    public void onHeadTicketInfoResult(TicketLotteryTimeBean dataBean) {
        mPlanId = dataBean.getPlanId();
        mViewTop.setPlanData(dataBean);
        DataCenter.getInstance().setPlanId(dataBean.getPlanId());
        mPresenter.setSaleStatus(dataBean.getSale());
    }

    @Override
    public void onOpenResult(List<LotteryNumBean> list) {
        mLotteryNumList = list;
        mHandler.postDelayed(mUpdateResultRunnable, 200);
    }


    private class UpdateResultRunnable implements Runnable {

        @Override
        public void run() {
            if (mLotteryNumList != null) {
                mViewTop.setOpenResult(mLotteryNumList);
                DataCenter.getInstance().setLotteryHistory(mLotteryId, mLotteryNumList);
            }
        }
    }

    @Subscribe(tags = {@Tag(EVENT_BET_ACTIVITY_FINISH)})
    public void closeActivity(String s) {
        finish();
    }


    @Subscribe(tags = {@Tag(EVENT_TYPE_CHANGE_OPEN_RESULT_HISTORY)})
    public void onVerticalDrawerChange(String s) {
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen()) {
                mDrawerLayout.closeDrawer();
                if (mViewTop != null && mViewTop.getIvDrop() != null) {
                    AnimationUtil.toCollapse(mViewTop.getIvDrop());
                }
            } else {
                if (mViewTop != null && mViewTop.getIvDrop() != null) {
                    AnimationUtil.toExpand(mViewTop.getIvDrop());
                }
                if (mHistoryDialog != null) {
                    FragmentUtils.remove(mHistoryDialog);
                }
                mDrawerLayout.openDrawerView();
                mHistoryDialog = TopHistoryFragment.newInstance();
                FragmentUtils.add(getSupportFragmentManager(), mHistoryDialog, R.id.dialog_frame_layout, false);
            }
        }
    }

    @Subscribe(tags = {@Tag(EVENT_BET_TOP_CHOSE_LOTTERY_DIALOG_START_OPEN)})
    public void choseLotteryDialogStartOpen(String s) {
        if (mPresenter != null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switchTopGameList();
                    closeVerticalDrawer("");
                    //关闭开奖弹框
                }
            }, 500);
        }
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_CLOSE_RESULT_HISTORY)})
    public void closeVerticalDrawer(String s) {
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen()) {
                mDrawerLayout.closeDrawer();
                if (mViewTop != null && mViewTop.getIvDrop() != null) {
                    AnimationUtil.toCollapse(mViewTop.getIvDrop());
                }
            }
        }
    }

    // 立即投注弹窗展示
    @Subscribe(tags = {@Tag(EVENT_TYPE_BET_RiGHT_NOW_CLICK)})
    public void showBetRightNow(BetBottomView bottomView) {
        mPresenter.showBetRightNow(bottomView);
    }

    // 投注
    @Subscribe(tags = {@Tag(EVENT_REQUEST_BET_SUBMIT)})
    public void betSubmit(MkBetParamEntity entity) {
        if (LOTTERY_PLAY_MODE_DOUBLE == entity.getPlayMode()) {
            mPresenter.betSubmit(entity);
        }
    }

    //路子图快速投注
    @Subscribe(tags = {@Tag(EVENT_TYPE_QUICK_BET_CLICK)})
    public void showQuickBet(String title) {
        mPresenter.showQuickBet(mLotteryId, mPlanId, ConstantValue.SUBMIT_MODE_BET, title);
    }

    // 推送最新的开奖结果
    @Subscribe(tags = {@Tag(EVENT_OPEN_RESULT_UPDATE)})
    public void onMQTTReceive(LotteryNumBean bean) {
        if (mLotteryId == bean.getTicketId()) {
            // 开奖结果更新 获取100期历史结果'
            mPresenter.getLastOpenResult(mLotteryId, 100);
        }
    }

    @Subscribe(tags = {@Tag(EVENT_GET_UNSETTLE_LIST)})
    public void loadRecentRecordList(String s) {
        mHandler.postDelayed(mUnSettleRunnable,5000);
    }

}
