package com.ski.box.view.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.adapter.top.TopResultAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 动态改变头部的开奖号码
 */
public class LotteryResultView extends LinearLayout {

    private RecyclerView rvLotteryResult;
    private TopResultAdapter mAdapter;

    public LotteryResultView(Context context) {
        this(context, null);
        init(context);
    }

    public LotteryResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LotteryResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = View.inflate(getContext(), R.layout.ski_lottery_result_num, this);
        rvLotteryResult = inflate.findViewById(R.id.rv_lottery_result);
        mAdapter = new TopResultAdapter(context);
        rvLotteryResult.setLayoutManager(new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvLotteryResult.setAdapter(mAdapter);
    }


    public void setResult(List<LotteryNumBean> list) {
        if(list.size() > 1) {
            mAdapter.setNewInstance(list.subList(0,1));
        } else {
            mAdapter.setNewInstance(list);
        }
    }

    public void setResult(List<LotteryNumBean> list, int mode) {
        mAdapter.setMode(mode);
        if(list.size() > 1) {
            mAdapter.setNewInstance(list.subList(0,1));
        } else {
            mAdapter.setNewInstance(list);
        }
    }


}
