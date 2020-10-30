package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ski.box.R;
import com.ski.box.adapter.RecordResultAdapter;
import com.ski.box.adapter.top.TopResultAdapter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.RecordResultBean;
import com.ski.box.bean.group.GroupBetData;
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
    List<RecordResultBean> mList = new ArrayList<>();
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



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;

    }

    public void setResult(int ticketId, String code) {
        RecordResultBean bean = new RecordResultBean();
        int serId = LotteryUtil.getSerIdByLotteryId(ticketId);
        bean.setItemType(serId);
        bean.setCode(code);
        mList.clear();
        mList.add(bean);
        mAdapter = new RecordResultAdapter(mContext);
        rvLotteryResult.setAdapter(mAdapter);
        rvLotteryResult.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setNewInstance(mList);
    }
}
