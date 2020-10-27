package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ski.box.R;
import com.ski.box.adapter.RecordResultAdapter;
import com.ski.box.adapter.top.TopResultAdapter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.bean.road.RoadTitle;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 开奖号码
 */
public class LotteryRecordResultView extends LinearLayout {

    private RecyclerView rvLotteryResult;
    private RecordResultAdapter mAdapter;
    List<RecordBet.ListBean> mList = new ArrayList<>();
    private Context mContext;

    public LotteryRecordResultView(Context context) {
        this(context, null);
        init(context);
    }

    public LotteryRecordResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LotteryRecordResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = View.inflate(getContext(), R.layout.ski_record_lottery_result_num, this);
        rvLotteryResult = inflate.findViewById(R.id.rv_lottery_result);
        mContext = context;
    }


    public void setResult(RecordBet.ListBean bean) {
        int serId = LotteryUtil.getSerIdByLotteryId(bean.getTicketId());
        bean.setItemType(serId);
        mList.clear();
        mList.add(bean);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) rvLotteryResult.getLayoutParams();
        lp.width = getW(serId);
        rvLotteryResult.setLayoutParams(lp);
        mAdapter = new RecordResultAdapter(mContext);
        rvLotteryResult.setAdapter(mAdapter);
        rvLotteryResult.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mAdapter.setNewInstance(mList);
    }


    public static int getW(int serId) {
        int w  = 0;
        switch (serId) {
            case LotteryConstant.SER_ID_PK10:
                w = LayoutParams.WRAP_CONTENT;
//                w = ScreenUtils.dip2px(320);
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_PL35:
                w = ScreenUtils.dip2px(140);
                break;
            case LotteryConstant.SER_ID_LHC:
                w = ScreenUtils.dip2px(180);
                break;
            case LotteryConstant.SER_ID_11X5:
                w = ScreenUtils.dip2px(100);
                break;
            case LotteryConstant.SER_ID_K3:
                w = ScreenUtils.dip2px(100);
                break;
            case LotteryConstant.SER_ID_3D:
                w = ScreenUtils.dip2px(100);
                break;
            case LotteryConstant.SER_ID_KL8:
                w = ScreenUtils.dip2px(280);
                break;
        }
        return w;
    }

}
