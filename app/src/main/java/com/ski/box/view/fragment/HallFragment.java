package com.ski.box.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ski.box.R;
import com.ski.box.SKISdkManger;
import com.ski.box.adapter.HallAdapter;
import com.ski.box.adapter.ImageAdapter;
import com.ski.box.bean.BannerBean;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MemberDetailEntity;
import com.ski.box.bean.User;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotterySer;
import com.ski.box.mvp.contract.HallContract;
import com.ski.box.mvp.presenter.HallPresenter;
import com.ski.box.service.MQTTHelper;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.activity.BetActivity;
import com.ski.box.view.view.AutoScrollTextView;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_BET_ACTIVITY_FINISH;
import static com.ski.box.ConstantValue.EVENT_TYPE_BALANCE_SET;
import static com.ski.box.ConstantValue.EVENT_TYPE_USER_NAME_NICK_NAME;

public class HallFragment extends BaseMVPFragment<HallContract.Presenter> implements HallContract.View, View.OnClickListener {

    private Banner mBanner;
    private TextView mTvUserName;
    private TextView mTvAmount;
    private AutoScrollTextView mMarqueeText;
    private RecyclerView mRvLottery;
    private HallAdapter mHallAdapter;
    private List<LotterySer> mLotterySerList = new ArrayList<>();
    private TextView mTvSSC;
    private TextView mTvPk10;
    private TextView mTvK3;
    private TextView mTv11x5;
    private TextView mTvLhc;
    private TextView mTvKl8;
    private TextView mTv3D;
    private TextView mTvZJ;
    protected List<String> mAnnouncements = new ArrayList<>();

    public HallFragment() {
    }

    public static HallFragment newInstance() {
        HallFragment fragment = new HallFragment();
        return fragment;
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
        mTvUserName = view.findViewById(R.id.tv_user_name);
        mTvAmount = view.findViewById(R.id.tv_amount_value);
        mMarqueeText = view.findViewById(R.id.marqueeText);
        mTvSSC = view.findViewById(R.id.tv_ssc);
        mTvPk10 = view.findViewById(R.id.tv_pk10);
        mTvK3 = view.findViewById(R.id.tv_k3);
        mTv11x5 = view.findViewById(R.id.tv_11x5);
        mTvLhc = view.findViewById(R.id.tv_lhc);
        mTvKl8 = view.findViewById(R.id.tv_kl8);
        mTv3D = view.findViewById(R.id.tv_3d);
        mTvZJ = view.findViewById(R.id.tv_zj);
        mRvLottery = view.findViewById(R.id.rv_lottery);

        mMarqueeText.setOnClickListener(this);
        mTvSSC.setOnClickListener(this);
        mTvPk10.setOnClickListener(this);
        mTvK3.setOnClickListener(this);
        mTv11x5.setOnClickListener(this);
        mTvLhc.setOnClickListener(this);
        mTvKl8.setOnClickListener(this);
        mTv3D.setOnClickListener(this);
        mTvZJ.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRvLottery.setLayoutManager(gridLayoutManager);
        mHallAdapter = new HallAdapter();
        mRvLottery.setAdapter(mHallAdapter);

        mHallAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LotteryBean bean = (LotteryBean) adapter.getData().get(position);
                BetActivity.startBetActivity(getActivity(), bean.getTicketId(), bean.getTicketName());
            }
        });

        SKISdkManger.startAlarmService(AppUtil.getContext());

        initAnnouncement();
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        setBanner();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getAllLotteryResult();
        mPresenter.getSysConfig();
        //如果配置为空需要调用投注区间
        if (TextUtils.isEmpty(SettingManager.getDoubleBetAmountRange())) {
            mPresenter.getSelfProfile();
        }
     //   mPresenter.getBalance();
    }


    @Override
    protected HallContract.Presenter bindPresenter() {
        return new HallPresenter(mContext);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.marqueeText) {
        } else   if (id == R.id.tv_ssc) {
            setData(LotteryConstant.SER_ID_SSC);
        } else if (id == R.id.tv_pk10) {
            setData(LotteryConstant.SER_ID_PK10);
        } else if (id == R.id.tv_k3) {
            setData(LotteryConstant.SER_ID_K3);
        } else if (id == R.id.tv_11x5) {
            setData(LotteryConstant.SER_ID_11X5);
        } else if (id == R.id.tv_lhc) {
            setData(LotteryConstant.SER_ID_LHC);
        } else if (id == R.id.tv_kl8) {
            setData(LotteryConstant.SER_ID_KL8);
        } else if (id == R.id.tv_3d) {
            setData(LotteryConstant.SER_ID_3D);
        } else if (id == R.id.tv_zj) {
            // getLotteryList(LotteryConstant.SER_ID_3D);
            ToastUtil.showInfo("正在开发");
        }
    }

    private void setBanner() {
        List<BannerBean> list = new ArrayList<>();
        list.add(new BannerBean(1, "", "www.baidu.com", R.mipmap.img_banner_01));
        list.add(new BannerBean(1, "", "www.baidu.com", R.mipmap.img_banner_02));
        mBanner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new ImageAdapter(list))
                .setIndicator(new CircleIndicator(getActivity()));
    }

    @Override
    public void onAllLotteryResult(List<LotterySer> list) {
        mLotterySerList.clear();
        mLotterySerList.addAll(list);
        setData(LotteryConstant.SER_ID_SSC);
    }


    @Subscribe(tags = {@Tag(EVENT_TYPE_BALANCE_SET)})
    @Override
    public void onBalanceResult(MemberDetailEntity bean) {
        mTvAmount.setText("￥" + bean.getBalance());
        User user = DataCenter.getInstance().getUser();
        RxBus.get().post(EVENT_TYPE_USER_NAME_NICK_NAME, user);
    }

    @Subscribe(tags = {@Tag(EVENT_TYPE_USER_NAME_NICK_NAME)})
    public void onUserNameUpdate(User user) {
        mTvUserName.setText(user.getMemberAlias());
    }

    private void setData(int serId) {
        List<LotteryBean> list = null;
        for (LotterySer ser : mLotterySerList) {
            if (ser.getId() == serId) {
                list = ser.getList();
            }
        }
        if (list == null) {
            new ArrayList<>();
        }
        mHallAdapter.setNewInstance(list);
    }


    /**
     * 公告
     */
    protected void initAnnouncement() {
        for (int i = 0; i < 5; i++) {
            mAnnouncements.add("SK国际彩票新彩种，欢迎投注体验，随时充值...");
        }
        mMarqueeText.setList(mAnnouncements);
    }
}
