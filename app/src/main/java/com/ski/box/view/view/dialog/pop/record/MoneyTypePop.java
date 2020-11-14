package com.ski.box.view.view.dialog.pop.record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.ConditionBean;
import com.ski.box.bean.FrontTradeTypesBean;
import com.yb.core.utils.LanguageUtil;
import com.zyyoona7.popup.BasePopup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MoneyTypePop extends BasePopup<MoneyTypePop> implements View.OnClickListener {
    private Context mContext;
    private RecyclerView mRvDate;
    private MoneyAdapter mMoneyAdapter;
    private MoneyTypeChooseListener mListener;
    List<ConditionBean> mConList = new ArrayList<>();
    private View mSpace;
    List<ConditionBean> mList = new ArrayList<>();

    public static MoneyTypePop create(Context context) {
        return new MoneyTypePop(context);
    }

    protected MoneyTypePop(Context context) {
        setContext(context);
        mContext = context;
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.ski_pop_record_date, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
        setBackgroundDimEnable(false);
    }

    @Override
    protected void initViews(View view, MoneyTypePop recordDatePop) {
        mRvDate = view.findViewById(R.id.rv_record_date);
        mSpace = findViewById(R.id.record_spacing);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (mConList.size() > 0 && mConList.get(position).getItemType() == 1) {
//                    return 3;
//                }
//                return 1;
//            }
//        });
        mRvDate.setLayoutManager(gridLayoutManager);
        mMoneyAdapter = new MoneyAdapter();
        mRvDate.setAdapter(mMoneyAdapter);
        mSpace.setOnClickListener(this);
        initData();
    }

    private void initData() {
//        List<ConditionBean> list = new ArrayList<>();
//        list.add(new ConditionBean("账变类型", MoneyAdapter.TYPE_TITLE, "", false));
//        list.add(new ConditionBean("全部账变", MoneyAdapter.TYPE_CONTENT, "", true));
//        list.add(new ConditionBean("正账变", MoneyAdapter.TYPE_TITLE, "", false));
//        list.add(new ConditionBean("转入", MoneyAdapter.TYPE_CONTENT, "1", false));
//        list.add(new ConditionBean("派奖", MoneyAdapter.TYPE_CONTENT, "2", false));
//        list.add(new ConditionBean("撤单退款", MoneyAdapter.TYPE_CONTENT, "3", false));
//        list.add(new ConditionBean("负账变", MoneyAdapter.TYPE_TITLE, "", false));
//        list.add(new ConditionBean("转出", MoneyAdapter.TYPE_CONTENT, "4", false));
//        list.add(new ConditionBean("投注扣款", MoneyAdapter.TYPE_CONTENT, "5", false));
//        list.add(new ConditionBean("撤销派奖", MoneyAdapter.TYPE_CONTENT, "6", false));


//        list.add(new ConditionBean("奖金超限扣款", MoneyAdapter.TYPE_CONTENT, "7", false));

        mConList.clear();
        mConList.addAll(mList);
        mMoneyAdapter.setNewInstance(mList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.record_spacing) {
            dismiss();
        }
    }

    public void setMoneyType(List<FrontTradeTypesBean> typesBeans) {
        mList.add(new ConditionBean(LanguageUtil.getText("全部账变"), MoneyAdapter.TYPE_CONTENT, "", true));
        for(FrontTradeTypesBean bean : typesBeans) {
            mList.add(new ConditionBean(bean.getName(), MoneyAdapter.TYPE_CONTENT, String.valueOf(bean.getTradeType()), false));
        }
    }

    class MoneyAdapter extends BaseMultiItemQuickAdapter<ConditionBean, BaseViewHolder> {
        public static final int TYPE_TITLE = 1;
        public static final int TYPE_CONTENT = 2;

        public MoneyAdapter() {
            addItemType(TYPE_TITLE, R.layout.ski_item_trend_game_type);
            addItemType(TYPE_CONTENT, R.layout.ski_item_trend_game);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, ConditionBean bean) {
            switch (holder.getItemViewType()) {
                case TYPE_TITLE:
                    holder.setText(R.id.item_trend_game_type, bean.getName());
                    break;
                case TYPE_CONTENT:
                    setContent(holder, bean);
                    break;
            }
        }

        private void setContent(BaseViewHolder holder, ConditionBean bean) {
            holder.setText(R.id.item_trend_game, bean.getName());
            if (bean.isSelected()) {
                holder.itemView.setSelected(true);
            } else {
                holder.itemView.setSelected(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<ConditionBean> list = getData();
                    for (ConditionBean con : list) {
                        con.setSelected(false);
                    }
                    bean.setSelected(true);
                    notifyDataSetChanged();
                    if (mListener != null) {
                        mListener.onMoneyChoose(bean);
                    }
                }
            });
        }
    }


    public void setMoneyTypeChooseListener(MoneyTypeChooseListener listener) {
        mListener = listener;
    }

    public interface MoneyTypeChooseListener {
        void onMoneyChoose(ConditionBean bean);
    }
}
