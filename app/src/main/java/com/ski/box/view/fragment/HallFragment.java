package com.ski.box.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.adapter.HallAdapter;
import com.ski.box.adapter.HallGuideAdapter;
import com.ski.box.adapter.ImageAdapter;
import com.ski.box.bean.ActBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.user.User;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.HallContract;
import com.ski.box.mvp.presenter.HallPresenter;
import com.ski.box.service.MQTTHelper;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.activity.AgentWebViewActivity;
import com.ski.box.view.activity.BetActivity;
import com.ski.box.view.activity.ContainerActivity;
import com.ski.box.view.activity.MainActivity;
import com.ski.box.view.activity.my.NoticeActivity;
import com.ski.box.view.fragment.record.RecordBetFragment;
import com.ski.box.view.view.AutoScrollTextView;
import com.ski.box.view.view.HallTabLayout;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.AppUtil;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_UPDATE;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;
import static com.ski.box.ConstantValue.EVENT_UPDATE_HEAD_SUCCESS;

public class HallFragment extends BaseMVPFragment<HallContract.Presenter> implements HallContract.View, View.OnClickListener {

    private Banner mBanner;
    private CircleImageView mIvUserHead;
    private TextView mTvUserName;
    private ImageView mIvService;
    private TextView mTvAmount;
    private AutoScrollTextView mMarqueeText;
    private RecyclerView mRvLotteryGuid;
    private RecyclerView mRvLotteryList;
    private HallAdapter mHallAdapter;
    private HallGuideAdapter mHallGuideAdapter;
    private List<LotterySer> mLotterySerList = new ArrayList<>();

    public HallFragment() {
    }

    public static HallFragment newInstance() {
        HallFragment fragment = new HallFragment();
        return fragment;
    }

    @Override
    protected HallContract.Presenter bindPresenter() {
        return new HallPresenter(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MQTTHelper.getInstance().onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_hall;
    }

    @Override
    protected void initView(View view) {
        ImmersionBar.with(this).statusBarColor(R.color.ski_color_FAFAFA).statusBarDarkFont(true).init();
        RxBus.get().register(this);
        mBanner = view.findViewById(R.id.banner);
        mIvService = view.findViewById(R.id.iv_service);
        mIvUserHead = view.findViewById(R.id.iv_user);
        mTvUserName = view.findViewById(R.id.tv_user_name);
        mTvAmount = view.findViewById(R.id.tv_amount_value);
        mMarqueeText = view.findViewById(R.id.marqueeText);
        mRvLotteryGuid = view.findViewById(R.id.rv_lottery_guide);
        mRvLotteryList = view.findViewById(R.id.rv_lottery_list);
        TextView tvLabelNickName = view.findViewById(R.id.tv_nick_name_label);
        TextView tvLabelAmount = view.findViewById(R.id.tv_label_amount);
        tvLabelNickName.setText(LanguageUtil.getText("昵称") + ": ");
        tvLabelAmount.setText(LanguageUtil.getText("余额") + ": ");

        LinearLayout llRecord = view.findViewById(R.id.ll_record);
        LinearLayout llAct = view.findViewById(R.id.ll_activity);
        LinearLayout llRecharge = view.findViewById(R.id.ll_recharge);
        LinearLayout llWithdraw = view.findViewById(R.id.ll_withdraw);

        mMarqueeText.setOnClickListener(this);
        llRecord.setOnClickListener(this);
        llAct.setOnClickListener(this);
        llRecharge.setOnClickListener(this);
        llWithdraw.setOnClickListener(this);
        mIvService.setOnClickListener(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRvLotteryGuid.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mRvLotteryGuid.setAdapter(mHallGuideAdapter = new HallGuideAdapter());
        mHallGuideAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LotterySer bean = (LotterySer) adapter.getData().get(position);
                setLotteryList(bean.getId());
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        mRvLotteryList.setLayoutManager(gridLayoutManager);
        mHallAdapter = new HallAdapter();
        mRvLotteryList.setAdapter(mHallAdapter);
        mHallAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LotteryBean bean = (LotteryBean) adapter.getData().get(position);
                BetActivity.startBetActivity(getActivity(), bean.getTicketId(), bean.getTicketName());
            }
        });

        SKISdkManger.startAlarmService(AppUtil.getContext());
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getAllLotteryResult();
        mPresenter.getSysConfig();
        //如果配置为空需要调用投注区间
//        if (TextUtils.isEmpty(SettingManager.getDoubleBetAmountRange())) {
//            mPresenter.getSelfProfile();
//        }
        mPresenter.getSelfProfile();
        mPresenter.getNoticeList(1, 10);
        mPresenter.getActList();
        //   mPresenter.getBalance();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_service) {
            AgentWebViewActivity.startAgentWebView(requireActivity(), LanguageUtil.getText("客服中心"), ConstantValue.SERVICE_URL);
        } else if (id == R.id.marqueeText) {
            startActivity(new Intent(requireActivity(), NoticeActivity.class));
        } else if (id == R.id.ll_record) {// 注单
            Intent intent = new Intent(requireActivity(), ContainerActivity.class);
            intent.putExtra(ContainerActivity.KEY_CLASS, RecordBetFragment.class.getSimpleName());
            startActivity(intent);
        } else if (id == R.id.ll_activity) { // 活动
            ((MainActivity) requireActivity()).gotoPage(HallTabLayout.TAB_INDEX_1);
        } else if (id == R.id.ll_recharge) { // 充值
            ContainerActivity.startAct(requireActivity(), RechargeFragment.class.getSimpleName());
           // ((MainActivity) requireActivity()).gotoPage(HallTabLayout.TAB_INDEX_1);
        } else if (id == R.id.ll_withdraw) { // 提现
            ContainerActivity.startAct(requireActivity(), WithdrawFragment.class.getSimpleName());
          //  ((MainActivity) requireActivity()).gotoPage(HallTabLayout.TAB_INDEX_2);
        }
    }


    @Override
    public void onAllLotteryResult(List<LotterySer> list) {
        mLotterySerList.clear();
        mLotterySerList.addAll(list);
        int serId = setLotteryGuid();
        setLotteryList(serId);
    }

    @Override
    public void onNoticeListResult(List<String> list) {
        mMarqueeText.setList(list);
    }

    @Override
    public void onActResult(List<ActBean> list) {
        mBanner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new ImageAdapter(list))
                .setIndicator(new CircleIndicator(getActivity()))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                     //   ActBean actBean = (ActBean) data;
                    //    AgentWebViewActivity.startAgentWebView(requireActivity(), LanguageUtil.getText(actBean.getActivityName()), actBean.getTargetUrl());
                    }
                });
    }


    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_UPDATE)})
    public void onBalanceUpdate(String balanceStr) {
        mTvAmount.setText(balanceStr);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mTvUserName.setText(user.getAlias());
        mIvUserHead.setImageResource(ActivityUtil.getHeadByProfile(user.getProfile()));
    }

    @Subscribe(tags = {@Tag(EVENT_UPDATE_HEAD_SUCCESS)})
    public void onUserHeadUpdate(String s) {
        User user = DataCenter.getInstance().getUser();
        mIvUserHead.setImageResource(ActivityUtil.getHeadByProfile(user.getProfile()));
    }

    private int setLotteryGuid() {
        LotterySer ser = null;
        for (int i = 0; i < mLotterySerList.size(); i++) {
            if (0 == i) {
                ser = mLotterySerList.get(i);
            }
        }
        if (ser == null) {
            ser = new LotterySer();
        }
        mHallGuideAdapter.setNewInstance(mLotterySerList);
        return ser.getId();
    }


    private void setLotteryList(int serId) {
        List<LotteryBean> list = null;
        for (LotterySer ser : mLotterySerList) {
            if (ser.getId() == serId) {
                list = ser.getList();
            }
        }
        if (list == null) {
            new ArrayList<>();
        }
        mHallGuideAdapter.setNewInstance(mLotterySerList);
        mHallAdapter.setNewInstance(list);
    }

}
