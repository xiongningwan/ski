package com.ski.box.adapter.lottery.d;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.lottery.LotteryPlay;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.ski.box.utils.lottery.SettingManager;
import com.ski.box.view.view.ShadowLayout;
import com.yb.core.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_CIRCLE;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_CIRCLE_LHC;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE_HLC;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE_K3;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE_SUM;


/**
 * 双面盘选号盘布局 多Item
 */
public class ContentItemAdapter extends BaseMultiItemQuickAdapter<LotteryPlay, BaseViewHolder> {
    //显示遗漏
    public boolean missShow;
    //显示冷热
    public boolean hotCold;
    //清空
    public boolean isClear;

    Map<String, View> mViews;
    Map<String, LotteryPlay> mPlays;
    private ContentClickListener mContentClickListener;

    public ContentItemAdapter() {
        addItemType(BET_LAYOUT_TYPE_RECTANGLE, R.layout.ski_play_content_item_type_rectangle);
        addItemType(BET_LAYOUT_TYPE_CIRCLE, R.layout.ski_item_spherical_view_number);
        addItemType(BET_LAYOUT_TYPE_CIRCLE_LHC, R.layout.ski_item_spherical_view_number);
        addItemType(BET_LAYOUT_TYPE_RECTANGLE_HLC, R.layout.ski_play_content_item_type_rectangle);
        addItemType(BET_LAYOUT_TYPE_RECTANGLE_SUM, R.layout.ski_item_hz_button_panel);
        addItemType(BET_LAYOUT_TYPE_RECTANGLE_K3, R.layout.ski_hall_item_dice_kuaisan);
        mViews = new HashMap<>();
        mPlays = new HashMap<>();
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, @Nullable LotteryPlay play) {
        switch (holder.getItemViewType()) {
            /*方型*/
            case BET_LAYOUT_TYPE_RECTANGLE:
                rectangleLayout(holder, play, 0);
                break;
            /*球型*/
            case BET_LAYOUT_TYPE_CIRCLE:
                circleLayout(holder, play, 0);
                break;
            /*球型六合彩*/
            case BET_LAYOUT_TYPE_CIRCLE_LHC:
                circleLayout(holder, play, 1);
                break;
            /*方型六合彩*/
            case BET_LAYOUT_TYPE_RECTANGLE_HLC:
                rectangleLayout(holder, play, 1);
                break;
            /*方型和值*/
            case BET_LAYOUT_TYPE_RECTANGLE_SUM:
                rectangleSumLayout(holder, play);
                break;
            /*方型快三*/
            case BET_LAYOUT_TYPE_RECTANGLE_K3:
                rectangleK3Layout(holder, play);
                break;
        }
    }


    /**
     * 互斥选中
     *
     * @param play
     */
    private void exclusiveSelected(LotteryPlay play) {
        if ("特码合肖".equals(play.getTag()) || "正肖".equals(play.getTag()) || "二连肖".equals(play.getTag()) || "三连肖".equals(play.getTag())
                || "四连肖".equals(play.getTag()) || "五连肖".equals(play.getTag()) || "一肖".equals(play.getTag())||"正特一肖".equals(play.getTag())
                ||"龙虎和".equals(play.getTag())) {
            return;
        }
        if (LotteryUtil.isNumeric(play.getName())) {
            return;
        }
        /*通过Map的key找值*/
        if (LotteryUtil.getDoubleSideMutelIteMap().containsKey(play.getName())) {
            String value = LotteryUtil.getDoubleSideMutelIteMap().get(play.getName());
            if (mViews.get(value) != null) {
                mViews.get(value).setSelected(false);
                if (mPlays.get(value) != null) {
                    mPlays.get(value).setSelected(false);
                }
            }
        }
        /*通过Map的Value找值*/
        if (LotteryUtil.getDoubleSideMutelIteMap().containsValue(play.getName())) {
            Set<String> set = LotteryUtil.getDoubleSideMutelIteMap().keySet();
            for (String key : set) {
                if (LotteryUtil.getDoubleSideMutelIteMap().get(key).equals(play.getName())) {
                    if (mViews.get(key) != null) {
                        mViews.get(key).setSelected(false);
                        if (mPlays.get(key) != null) {
                            mPlays.get(key).setSelected(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * 方型
     *
     * @param holder
     * @param play
     * @param type   0 普通 1六合彩
     */
    private void rectangleLayout(@NotNull BaseViewHolder holder, @Nullable LotteryPlay play, int type) {
        ShadowLayout slLayout = holder.getView(R.id.sl_select_num);
        LinearLayout llSelectNum = holder.getView(R.id.ll_select_num);
        TextView tvName = holder.getView(R.id.tvName);
        TextView tvOdds = holder.getView(R.id.tvValue);
        TextView coldHotMissing = holder.getView(R.id.tv_cold_hot_missing);
        setView2List(play, llSelectNum);
        tvName.setText(play.getName());
//        tvOdds.setText(getOdds(play.getCode(), play.getOdds()));
        tvOdds.setText(play.getOdds());
        if (LotteryUtil.isNumeric(play.getCode())) {
            llSelectNum.setBackgroundResource(type == 1 ? LotteryUtil.getLHCSquareBackgroudResource(Integer.valueOf(play.getCode()))
                    : R.drawable.ski_bet_content_btn_item_selector);
        } else {
            llSelectNum.setBackgroundResource(R.drawable.ski_bet_content_btn_item_selector);
        }
        if (0 == type) {
            if ("特码合肖".equals(play.getTag())) {
                tvName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                tvOdds.setVisibility(View.GONE);
            }
        }
        /*冷热遗漏*/
        coldHotShow(coldHotMissing, play);
        clear(play, llSelectNum);
        llSelectNum.setSelected(play.isSelected());
        llSelectNum.setOnClickListener(v -> {
            if (play.isSelected()) {
                play.setSelected(false);
            } else {
                play.setSelected(true);
            }
            llSelectNum.setSelected(play.isSelected());
            exclusiveSelected(play); // 互斥
            slLayout.setmShadowColor(Color.parseColor(play.isSelected() ? "#5a82f8" : "#999999"));
            if (mContentClickListener != null) {
                mContentClickListener.onItemClick();
            }
        });
    }

    /*多赔率取值,Code相等数字越多赔率越大*/
    private String getOdds(String code, String odds) {
        if (odds.contains("&")) {
            String[] oddList = odds.split("&");
            int max = 0;
            // 临时计数用的数组大于等于z
            int[] cnt = new int[10];
            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);
                max = (++cnt[c - 48] > max) ? cnt[c] : max;
            }
            if (oddList.length >= max) {
                String odd = oddList[oddList.length - max];
                if (odd.contains("=")) {
                    //odds = odd.split("=")[1];
                    odds = odd.replace(max + "=", "");
                }
            }
            return odds;
        } else {
            return odds;
        }
    }

    /**
     * 球型
     *
     * @param holder
     * @param play
     * @param type   0 普通 1六合彩
     */
    private void circleLayout(@NotNull BaseViewHolder holder, @Nullable LotteryPlay play, int type) {
        TextView tvSelectNum = holder.getView(R.id.tv_select_num);
        TextView coldHotMissing = holder.getView(R.id.tv_cold_hot_missing);
        tvSelectNum.setText(play.getName());
        tvSelectNum.setBackgroundResource(type == 1 ? LotteryUtil.getLHCBallBackgroundResource(Integer.valueOf(play.getCode()))
                : R.drawable.ski_bet_d_content_btn_item_ball_selector);
        /*冷热遗漏*/
        coldHotShow(coldHotMissing, play);
        clear(play, tvSelectNum);
        tvSelectNum.setSelected(play.isSelected());
        tvSelectNum.setOnClickListener(v -> {
            /*六合彩自选不中特殊判断*/
            // check 选中个数和赔率
            boolean clickSuccess = true;
            if (play.isSelected()) {
                play.setSelected(false);
            } else {
                play.setSelected(true);
            }
            tvSelectNum.setSelected(play.isSelected());
            if (mContentClickListener != null) {
                clickSuccess = mContentClickListener.onItemClick();
            }
            /*不能选中时,还原*/
            if (!clickSuccess && isClear) {
                play.setSelected(false);
                tvSelectNum.setSelected(false);
            }
        });
    }

    /**
     * 方型和值
     *
     * @param holder
     * @param play
     */
    private void rectangleSumLayout(@NotNull BaseViewHolder holder, @Nullable LotteryPlay play) {
        TextView cbBall = holder.getView(R.id.cbBall);
        TextView tvRebate = holder.getView(R.id.tvRebate);
        ConstraintLayout llParent = holder.getView(R.id.llParent);
        LinearLayout llNum = holder.getView(R.id.llNum);
//        List<String> chineseList = play.getChineseList();
//        llNum.setVisibility(numList == null && chineseList == null ? View.GONE : View.VISIBLE);
//        llNum.setVisibility(play.getNeedAuxiliaryState() == 1 ? View.VISIBLE : View.GONE);
        if (play.isSelected()) {
            llParent.setBackgroundResource(R.drawable.ski_bg_ff6daced_6dp);
            cbBall.setTextColor(ContextCompat.getColor(cbBall.getContext(), R.color.ski_color_ffffff));
            tvRebate.setTextColor(ContextCompat.getColor(cbBall.getContext(), R.color.ski_color_ffffff));
//            if (play.isWaveLine()) {
//                if (numList != null) {
//                    addNumView(numList, llNum);
//                }
//            } else {
//                updateUiData(data, true, llNum);
//            }
        } else {
            llParent.setBackgroundResource(R.drawable.ski_bg_white_6dp);
            cbBall.setTextColor(ContextCompat.getColor(cbBall.getContext(), R.color.ski_color_555555));
            tvRebate.setTextColor(ContextCompat.getColor(cbBall.getContext(), R.color.ski_red));
//            if (data.isWaveLine()) {
//                if (numList != null) {
//                    addNumView(numList, llNum);
//                }
//            } else {
//                updateUiData(data, false, llNum);
//                updateUiData(data, check, llNum);
//            }
        }
        cbBall.setText(play.getCode());
    }

    /**
     * 方型快三
     *
     * @param holder
     * @param play
     */
    private void rectangleK3Layout(@NotNull BaseViewHolder holder, @Nullable LotteryPlay play) {
        ConstraintLayout llContent = holder.getView(R.id.cl_content_kuaisan);
        String playItemOdds = play.getOdds();
        String playTag = play.getTag();
        String playCode = play.getCode();
        //---------------------------------------------------------------------
        TextView tvOne = holder.getView(R.id.tvOne);
        TextView tvTwo = holder.getView(R.id.tvTwo);
        TextView tvThree = holder.getView(R.id.tvThree);
        TextView playOdds = holder.getView(R.id.tv_play_odds);
        TextView tvNum = holder.getView(R.id.tv_num);
        TextView coldHotMissing = holder.getView(R.id.tv_cold_hot_missing);
        /*冷热遗漏*/
        coldHotShow(coldHotMissing, play);
        playOdds.setText(playItemOdds);
        /**三军**/
        if ("三军".equals(playTag)) {
            tvTwo.setText("");
            int icon = getIcons(playCode).get(0);
            setVisibility(tvOne, tvTwo, tvThree, View.GONE, View.GONE);
            tvTwo.setBackgroundResource(icon);
            /**总和大小**/
        } else if ("总和大小".equalsIgnoreCase(playTag)) {
            tvTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
            setVisibility(tvOne, tvTwo, tvThree, View.GONE, View.GONE);
            tvTwo.setText(play.getName());
          //  tvTwo.setTextColor(Color.parseColor(play.isSelected() ? "#ffffff" : "#7076a0"));
            setLayoutParams(llContent, true);
            /**围骰**/
        } else if ("围骰".equalsIgnoreCase(playTag)) {
            tvTwo.setText("");
            List<Integer> icons = getIcons(playCode);
            setVisibility(tvOne, tvTwo, tvThree, View.VISIBLE, View.VISIBLE);
            tvOne.setBackgroundResource(icons.get(0));
            tvTwo.setBackgroundResource(icons.get(1));
            tvThree.setBackgroundResource(icons.get(2));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) llContent.getLayoutParams();
            layoutParams.leftMargin = ScreenUtils.dip2px(1);
            layoutParams.rightMargin = ScreenUtils.dip2px(1);
            llContent.setLayoutParams(layoutParams);

            /**全骰**/
        } else if ("全骰".equalsIgnoreCase(playTag)) {
            tvTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
            setVisibility(tvOne, tvTwo, tvThree, View.GONE, View.GONE);
            tvTwo.setText(play.getName());
            setLayoutParams(llContent, "全骰".equals(playTag));
            /**点数**/
        } else if (!"点数".equalsIgnoreCase(playTag)) {
            if ("长牌".equalsIgnoreCase(playTag) || "短牌".equalsIgnoreCase(playTag)) {
                tvTwo.setText("");
                List<Integer> icons = getIcons(playCode);
                setVisibility(tvOne, tvTwo, tvThree, View.VISIBLE, View.GONE);
                tvOne.setBackgroundResource(icons.get(0));
                tvTwo.setBackgroundResource(icons.get(1));
                tvOne.setPadding(10,10,5,10);
                tvTwo.setPadding(5,10,10,10);
                /**鱼虾蟹**/
            } else if ("鱼虾蟹".equalsIgnoreCase(playTag)) {
                tvTwo.setBackgroundColor(Color.argb(0, 0, 0, 0));
                setVisibility(tvOne, tvTwo, tvThree, View.GONE, View.GONE);
                tvTwo.setText("");
                tvNum.setVisibility(View.VISIBLE);
//                tvNum.setText(play.getName());
                tvNum.setText(getYuXiaXieNameToNo(play));
                tvTwo.setBackgroundResource(ConfigurationUiUtils.kuaiSanMap.get(playCode));
            }
        }
        clear(play, llContent);
        llContent.setSelected(play.isSelected());
        llContent.setOnClickListener(v -> {
            if (play.isSelected()) {
                play.setSelected(false);
            } else {
                play.setSelected(true);
            }
            llContent.setSelected(play.isSelected());
            if (mContentClickListener != null) {
                mContentClickListener.onItemClick();
            }
        });
    }

    /*快三特殊样式*/
    private void setVisibility(TextView tvOne, TextView tvTwo, TextView tvThree, int v1, int v3) {
        tvOne.setVisibility(v1);
        tvTwo.setVisibility(View.VISIBLE);
        tvThree.setVisibility(v3);
    }

    /**
     * 获取快三的Icon
     *
     * @param playCode
     * @return
     */
    private List<Integer> getIcons(String playCode) {
        List<Integer> icons = new ArrayList<>();
        for (int i = 0; i < playCode.length(); i++) {
            icons.add(ConfigurationUiUtils.kuaiSanMap.get(String.valueOf(playCode.charAt(i))));
        }
        return icons;
    }

    /*快三特殊样式*/
    private void setLayoutParams(ConstraintLayout llContent, boolean isShort) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) llContent.getLayoutParams();
        layoutParams.height = ScreenUtils.dip2px(isShort ? 50 : 80);
        layoutParams.setMargins(7, 7, 7, 10);
        llContent.setLayoutParams(layoutParams);
    }

    private String getYuXiaXieNameToNo(LotteryPlay play) {
        String name = "";
        switch (play.getCode()) {
            case "yu":
                name = "1";
                break;
            case "xia":
                name = "2";
                break;
            case "hulu":
                name = "3";
                break;
            case "jinxian":
                name = "4";
                break;
            case "xie":
                name = "5";
                break;
            case "ji":
                name = "6";
                break;
        }
        return name;
    }


    /*冷热遗漏颜色*/
    private void setColdHotMissTextColor(TextView textView, String color) {
        textView.setTextColor(Color.parseColor("1".equals(color) ? "#e3253a" : "2".equals(color) ? "#7c88a8" : "#367af6"));
    }

    private void setView2List(LotteryPlay play, View view) {
        if (!mViews.containsKey(view)) {
            mViews.put(play.getName(), view);
            if (!mPlays.containsKey(play)) {
                mPlays.put(play.getName(), play);
            }
        }
    }

    /*冷热遗漏显示*/
    private void coldHotShow(TextView textView, LotteryPlay play) {
        if (missShow) {
            textView.setText(play.getMiss() + "");
        } else if (hotCold) {
            textView.setText(getColdHotValue(play));
        }
        textView.setVisibility(missShow || hotCold ? View.VISIBLE : View.INVISIBLE);
    }

    /*获取冷热的值*/
    private String getColdHotValue(LotteryPlay play) {
        String periods = SettingManager.getColdHotStatisticsPeriods();
        String coldHot = play.getColdHot_100();
        switch (periods) {
            case "20":
                coldHot = play.getColdHot_20();
                break;
            case "50":
                coldHot = play.getColdHot_50();
                break;
        }
        return coldHot + "";
    }

    /*清空*/
    private void clear(LotteryPlay play, View view) {
        if (isClear) {
            play.setSelected(false);
            view.setSelected(false);
        }
    }

    public interface ContentClickListener {
        boolean onItemClick();
    }

    public void setClickListener(ContentClickListener listener) {
        mContentClickListener = listener;
    }

}
