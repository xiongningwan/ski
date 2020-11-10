package com.ski.box.view.activity.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.R;
import com.ski.box.bean.PersonProfileEntity;
import com.ski.box.bean.SelfProfileEntity;
import com.ski.box.mvp.contract.my.GameSetContract;
import com.ski.box.mvp.presenter.my.GameSetPresenter;
import com.ski.box.utils.LanguageUtil;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.view.HeaderView;
import com.ski.box.view.view.TestedGridItemDecoration;
import com.yb.core.base.BaseMVPActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class GameSetActivity extends BaseMVPActivity<GameSetContract.Presenter> implements GameSetContract.View, View.OnClickListener {
    private HeaderView mHeadView;
    private Switch mSwBetSure;
    private Switch mSwSound;
    private RecyclerView mRvRange;
    private DoubleBetRangAdapter mRangAdapter;
    private LinearLayout llAmountRange;
    private TextView tvAmountRange;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected GameSetContract.Presenter bindPresenter() {
        return new GameSetPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_game_set;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        mSwBetSure = findViewById(R.id.sw_bet_sure);
        mSwSound = findViewById(R.id.sw_sound);
        mRvRange = findViewById(R.id.rv_range);
        llAmountRange = findViewById(R.id.ll_double_bet_amount_range);
        tvAmountRange = findViewById(R.id.tv_double_bet_amount_range);
        mHeadView.setHeader(LanguageUtil.getText(getString(R.string.ski_my_game_set)), true);
    }

    @Override
    protected void initData(Bundle bundle) {
        mRangAdapter = new DoubleBetRangAdapter();
        mRvRange.setLayoutManager(new GridLayoutManager(this, 3));
        mRvRange.addItemDecoration(new TestedGridItemDecoration(this, R.dimen.ski_dp_3, android.R.color.transparent));
        mRvRange.setNestedScrollingEnabled(false);
        mRvRange.setAdapter(mRangAdapter);
        initConfig();
    }

    @Override
    protected void processLogic() {
        super.processLogic();
        mPresenter.getSelfProfile();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }


    @Override
    public void onDelayResult(SelfProfileEntity selfProfile) {
        boolean delayed = selfProfile.isDelayed();
        String delayedTime = selfProfile.getDelayedTime();
        //如果有延时说明
        if (delayed && !TextUtils.isEmpty(delayedTime)) {
            llAmountRange.setVisibility(View.VISIBLE);
            tvAmountRange.setText(delayedTime);
        } else {
            llAmountRange.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRangListResult(List<SelfProfileEntity.DoubleBetRangeListBean> list) {
        mRangAdapter.setNewInstance(list);
    }

    @Override
    public void onSaveResult(Object o) {
        mPresenter.getSelfProfile();
    }


    class DoubleBetRangAdapter extends BaseQuickAdapter<SelfProfileEntity.DoubleBetRangeListBean, BaseViewHolder> {

        public DoubleBetRangAdapter() {
            super(R.layout.ski_item_setting_bet);
            addChildClickViewIds(R.id.rl_to_detail);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable SelfProfileEntity.DoubleBetRangeListBean bean) {
            String rangStr = LotteryNoUtil.getRangeStr(bean.getDoublePlayBetMin(), bean.getDoublePlayBetMax());
            TextView tvTishi = holder.getView(R.id.tv_tishi);
//            ImageView ivChose = holder.getView(R.id.iv_chose);
            TextView tvName = holder.getView(R.id.bt_name);
            tvName.setText(rangStr);
            if (TextUtils.isEmpty(rangStr)) {
                tvTishi.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
                tvTishi.setText(rangStr);
            } else {
                tvTishi.setVisibility(View.GONE);
                tvName.setVisibility(View.VISIBLE);
                if (bean.isCurrent()) {
                    tvName.setSelected(true);
                    tvName.setEnabled(true);
//                    ivChose.setVisibility(View.VISIBLE);
                } else {
                    tvName.setSelected(false);
                    tvName.setEnabled(true);
//                    ivChose.setVisibility(View.GONE);
                    if (bean.isLock()) {
                        tvName.setEnabled(false);
                    }
                }
            }
        }
    }

    private void initConfig() {
        mSwBetSure.setChecked(SettingManager.isBetConfirmDialog());
        mSwSound.setChecked(SettingManager.isBettingCountdownTone());
        mSwBetSure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingManager.setBetConfirmDialog(b);
            }
        });
        mSwSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingManager.setBettingCountdownTone(b);
            }
        });

        mRangAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //此处有坑？？？？频繁点击 时会 抛出越界异常显示 position = -1
                if (position < 0) {
                    return;
                }
                SelfProfileEntity.DoubleBetRangeListBean bean = (SelfProfileEntity.DoubleBetRangeListBean) adapter.getItem(position);
                int nextConfigId = bean.getConfigId();
                String range = LotteryNoUtil.getRangeStr(bean.getDoublePlayBetMin(), bean.getDoublePlayBetMax());
                PersonProfileEntity personProfileEntity = new PersonProfileEntity();
                personProfileEntity.setNextConfigId(String.valueOf(nextConfigId));
                mPresenter.saveSelfProfile(range, personProfileEntity);
            }
        });
    }
}
