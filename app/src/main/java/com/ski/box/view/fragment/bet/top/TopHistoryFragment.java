package com.ski.box.view.fragment.bet.top;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.adapter.top.TopHistoryAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.yb.core.base.BaseMVPFragment;
import com.yb.core.utils.ScreenUtils;

import java.util.List;
import java.util.Objects;

import static com.ski.box.ConstantValue.EVENT_TYPE_CLOSE_RESULT_HISTORY;

/**
 * 顶部开奖记录
 */
public class TopHistoryFragment extends BaseMVPFragment<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {

    private RecyclerView mRvHistory;
    private TextView mIvClose;
    private TopHistoryAdapter mAdapter;
    private DividerItemDecoration mDivider;

    public TopHistoryFragment() {
    }

    public static TopHistoryFragment newInstance() {
        TopHistoryFragment fragment = new TopHistoryFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_top_history;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mRvHistory = view.findViewById(R.id.rv_history);
        mIvClose = view.findViewById(R.id.tv_close);
        mIvClose.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRvHistory.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mDivider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        mRvHistory.addItemDecoration(mDivider);
        mAdapter = new TopHistoryAdapter(requireActivity());
        mRvHistory.setAdapter(mAdapter);
    }

    //这个是一个懒加载
    @Override
    protected void loadData() {
        int lotteryId = DataCenter.getInstance().getCurLotteryId();
        List<LotteryNumBean> list = DataCenter.getInstance().getLotteryHistory(lotteryId);
        if (list != null && list.size() > 1) {
            List<LotteryNumBean> subList = list.subList(1, list.size());
            mAdapter.setNewInstance(subList);
        }
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(mContext);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_close) {
            RxBus.get().post(EVENT_TYPE_CLOSE_RESULT_HISTORY, "");
        }
    }
}
