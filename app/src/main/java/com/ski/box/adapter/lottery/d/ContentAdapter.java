package com.ski.box.adapter.lottery.d;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.BetStatus;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryPlayEnd;
import com.ski.box.bean.lottery.LotteryPlayStart;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.utils.AnimationUtil;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.yb.core.utils.StringUtils;
import com.zyyoona7.popup.EasyPopup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_TYPE_BET_NO_CHECK_UPDATE;
import static com.ski.box.bean.lottery.BetLayoutType.DIVIDER_TYPE_BLOCK;
import static com.ski.box.bean.lottery.BetLayoutType.DIVIDER_TYPE_LINE;
import static com.ski.box.bean.lottery.LotteryConstant.LOTTERY_PLAY_MODE_DOUBLE;


public class ContentAdapter extends BaseQuickAdapter<LotteryPlayEnd, BaseViewHolder> {
    //显示遗漏
    public boolean missShow;
    //显示冷热
    public boolean hotCold;
    //清空
    public boolean isClear;
    private int mSerId;
    private int mLotteryId;
    private int mPlayPosition = 0;
    private int mPlaySubPosition = 0;
    private List<ContentItemAdapter> mItemAdapters;
    private LotteryPlayStart mLotteryPlay;
    private EasyPopup mEasyPopup;

    public ContentAdapter(int lotteryId, LotteryPlayStart lotteryPlay) {
        super(R.layout.ski_play_content_item);
        mItemAdapters = new ArrayList<>();
        mSerId = LotteryUtil.getSerIdByLotteryId(lotteryId);
        mLotteryId = lotteryId;
        mLotteryPlay = lotteryPlay;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable LotteryPlayEnd play) {
        RecyclerView rvItem = holder.getView(R.id.rv_item);
        TextView tvTagTitle = holder.getView(R.id.tv_tag_title);
        ImageView ivArrow = holder.getView(R.id.tv_tag_arrow);
        View vBlock = holder.getView(R.id.view_divider_block);
        View vLine = holder.getView(R.id.view_divider_line);

        if (!TextUtils.isEmpty(play.getTag())) {
            if (play.isHideTag()) {
                tvTagTitle.setVisibility(View.GONE);
                tvTagTitle.setText("");
            } else {
                tvTagTitle.setVisibility(View.VISIBLE);
                tvTagTitle.setText(LanguageUtil.getText(play.getTag()));
            }
        } else {
            tvTagTitle.setVisibility(View.GONE);
            tvTagTitle.setText("");
        }

        // 具体玩法
        List<LotteryPlay> list = play.getLotteryPlays();
        rvItem.setLayoutManager(new GridLayoutManager(getContext(), play.getSpanCount() == 0 ? 1 : play.getSpanCount()));
        ContentItemAdapter adapter = new ContentItemAdapter();
        adapter.hotCold = hotCold;
        adapter.missShow = missShow;
        adapter.isClear = isClear;
        addItemAdapter(adapter);
        rvItem.setAdapter(adapter);
        adapter.setNewInstance(list);

        if (DIVIDER_TYPE_LINE == play.getDivider()) {
            vBlock.setVisibility(View.GONE);
            vLine.setVisibility(View.VISIBLE);
        } else if (DIVIDER_TYPE_BLOCK == play.getDivider()) {
            vBlock.setVisibility(View.VISIBLE);
            vLine.setVisibility(View.GONE);
        } else {
            vBlock.setVisibility(View.GONE);
            vLine.setVisibility(View.GONE);
        }

        if (play.isCanExpand()) {
           // ivArrow.setVisibility(View.VISIBLE);
            tvTagTitle.setOnClickListener(v -> {
                if (View.VISIBLE == rvItem.getVisibility()) {
                    AnimationUtil.fadeOut(rvItem);
                    AnimationUtil.toExpand(ivArrow);
                } else {
                    AnimationUtil.fadeIn(rvItem);
                    AnimationUtil.toCollapse(ivArrow);
                }
            });
        } else {
          //  ivArrow.setVisibility(View.GONE);
            if (StringUtils.isEmpty(play.getTag())) {
                tvTagTitle.setVisibility(View.GONE);
            }
        }

    }


    /*当前玩法一级栏目位置*/
    public void setPlayPosition(int position) {
        this.mPlayPosition = position;
    }

    /*当前玩法二级栏目位置*/
    public void setPlaySubPosition(int position) {
        this.mPlaySubPosition = position;
    }

    public void addItemAdapter(ContentItemAdapter adapter) {
        if (!mItemAdapters.contains(adapter)) {
            mItemAdapters.add(adapter);
            adapter.setClickListener(() -> {
                isClear = false;
                // 计算注数
                return calculateBets("self");
            });
        }
    }

    public boolean calculateBets(String s) {
        // 没有选中的玩法
        // 单注
        MkBetParamEntity entity = LotteryNoUtil.calculateBets_d(mLotteryPlay, mPlayPosition);
        entity.setTicketId(DataCenter.getInstance().getCurLotteryId());
        entity.setPlanNo(DataCenter.getInstance().getPlanId());
        entity.setPlayMode(LOTTERY_PLAY_MODE_DOUBLE);
        DataCenter.getInstance().setBetParamEntity(entity);
        BetStatus betStatus = LotteryNoUtil.getBetStatus(entity.getBet(), LOTTERY_PLAY_MODE_DOUBLE);
        RxBus.get().post(EVENT_TYPE_BET_NO_CHECK_UPDATE, betStatus);
        return entity.isClickEnable();
    }

}
