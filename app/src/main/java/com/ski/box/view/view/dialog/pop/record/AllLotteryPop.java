package com.ski.box.view.view.dialog.pop.record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ski.box.R;
import com.ski.box.adapter.TopGameAdapter;
import com.ski.box.bean.DataCenter;
import com.ski.box.bean.TopGameBean;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.bean.lottery.LotterySer;
import com.zyyoona7.popup.BasePopup;

import java.util.ArrayList;
import java.util.List;

public class AllLotteryPop extends BasePopup<AllLotteryPop> implements View.OnClickListener {
    private final Context mContext;
    private RecyclerView mRvAll;
    private TopGameAdapter mAllLotteryAdapter;
    private LotteryChooseListener mListener;
    private TextView mBtnAll;
    protected List<TopGameBean> mTopGameList = new ArrayList<>();

    public static AllLotteryPop create(Context context) {
        return new AllLotteryPop(context);
    }

    protected AllLotteryPop(Context context) {
        setContext(context);
        mContext = context;
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.ski_pop_record_all_lottery, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
        setBackgroundDimEnable(false);
        // setNeedReMeasureWH(true);
    }

    @Override
    protected void initViews(View view, AllLotteryPop recordDatePop) {
        mRvAll = view.findViewById(R.id.recycler_all);
        mBtnAll = view.findViewById(R.id.btn_all);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mTopGameList.size() > 0 && mTopGameList.get(position).getTicketSeriesId() > 0) {
                    return 3;
                }
                return 1;
            }
        });
        mRvAll.setLayoutManager(gridLayoutManager);
        mAllLotteryAdapter = new TopGameAdapter();
        mRvAll.setAdapter(mAllLotteryAdapter);
        mBtnAll.setOnClickListener(this);
        initData();
    }

    private void initData() {
        mTopGameList.clear();
        List<LotterySer> lotterySers = DataCenter.getInstance().getLottery();
        List<TopGameBean> topGameBeans = convertTopData(lotterySers);
        mTopGameList.addAll(topGameBeans);
        mAllLotteryAdapter.setNewInstance(topGameBeans);
        mAllLotteryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<TopGameBean> list = adapter.getData();
                for (int i = 0; i < list.size(); i++) {
                    TopGameBean bean = list.get(i);
                    bean.setSelected(false);
                    if (position == i) {
                        bean.setSelected(true);
                        adapter.notifyDataSetChanged();
                        if (mListener != null) {
                            mListener.onLotteryChoose(bean);
                        }
                    }
                }
            }
        });
    }


    private List<TopGameBean> convertTopData(List<LotterySer> lotterySers) {
        List<TopGameBean> list = new ArrayList<>();
        if (lotterySers == null) {
            return new ArrayList<>();
        }
        for (LotterySer bean : lotterySers) {
            if (bean.getId() > 0) {
                TopGameBean topGameBean = new TopGameBean();
                topGameBean.setTicketSeriesId(bean.getId());
                topGameBean.setTicketSeriesName(bean.getName());
                topGameBean.setItemType(TopGameAdapter.TOP_GAME_TYPE_SERIES);
                list.add(topGameBean);
                for (LotteryBean typeListBean : bean.getList()) {
                    TopGameBean topGameBean1 = new TopGameBean();
                    topGameBean1.setTicketId(typeListBean.getTicketId());
                    topGameBean1.setTicketName(typeListBean.getTicketName());
                    topGameBean1.setMobileLogoPath(typeListBean.getPath());
                    topGameBean1.setItemType(TopGameAdapter.TOP_GAME_TYPE_TICKET);
                    list.add(topGameBean1);
                }
            }
        }
        return list;
    }

    public void setDateChooseListener(LotteryChooseListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            TopGameBean bean = new TopGameBean();
            bean.setTicketId(0);
            bean.setTicketName("全部彩种");
            mListener.onLotteryChoose(bean);
            clearStatus();
        }
    }

    private void clearStatus() {
        if (mAllLotteryAdapter != null) {
            List<TopGameBean> list = mAllLotteryAdapter.getData();
            for (int i = 0; i < list.size(); i++) {
                TopGameBean bean2 = list.get(i);
                bean2.setSelected(false);
                mAllLotteryAdapter.notifyDataSetChanged();
            }
        }
    }

    public interface LotteryChooseListener {
        void onLotteryChoose(TopGameBean bean);
    }
}
