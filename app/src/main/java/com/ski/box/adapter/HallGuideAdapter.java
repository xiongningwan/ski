package com.ski.box.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.lottery.LotterySer;

import org.jetbrains.annotations.NotNull;

import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_11X5;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_3D;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_CCL;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_JJS;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_F1_SW;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_K3;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_KL8;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_LHC;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PK10;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_PL35;
import static com.ski.box.bean.lottery.LotteryConstant.SER_ID_SSC;

/**
 * Created by tom on 2020/8/6.
 */
public class HallGuideAdapter extends BaseQuickAdapter<LotterySer, BaseViewHolder> {

    public HallGuideAdapter() {
        super(R.layout.ski_item_hall_type_lottery_guide);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, LotterySer bean) {
        TextView tvSer = holder.getView(R.id.tv_ser);
        tvSer.setText(bean.getName());
        int res = R.mipmap.bg_hall_d_ssc;
        switch (bean.getId()){
            case SER_ID_SSC:
                 res = R.mipmap.bg_hall_d_ssc;
                break;
            case SER_ID_PK10:
                res = R.mipmap.bg_hall_d_pk10;
                break;
            case SER_ID_K3:
                res = R.mipmap.bg_hall_d_k3;
                break;
            case SER_ID_11X5:
                res = R.mipmap.bg_hall_d_11x5;
                break;
            case SER_ID_LHC:
                res = R.mipmap.bg_hall_d_lhc;
                break;
            case SER_ID_KL8:
                res = R.mipmap.bg_hall_d_kl8;
                break;
            case SER_ID_3D:
                res = R.mipmap.bg_hall_d_3d;
                break;
            case SER_ID_PL35:
                res = R.mipmap.bg_hall_d_p3p5;
                break;
            case SER_ID_F1_JJS:
                res = R.mipmap.bg_hall_d_p3p5;
                break;
            case SER_ID_F1_CCL:
                res = R.mipmap.bg_hall_d_p3p5;
                break;
            case SER_ID_F1_SW:
                res = R.mipmap.bg_hall_d_p3p5;
                break;
        }
        tvSer.setBackgroundResource(res);
    }


}
