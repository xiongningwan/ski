package com.ski.box.view.view.dialog.pop.record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.ConditionBean;
import com.yb.core.utils.TimeUtils;
import com.zyyoona7.popup.BasePopup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecordDatePop extends BasePopup<RecordDatePop> implements View.OnClickListener {
    private RecyclerView mRvDate;
    private DateAdapter mDateAdapter;
    private DateChooseListener mDateChooseListener;
    private View mSpace;


    public static RecordDatePop create(Context context) {
        return new RecordDatePop(context);
    }

    protected RecordDatePop(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.ski_pop_record_date, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
        setBackgroundDimEnable(false);
        // setNeedReMeasureWH(true);
    }



    @Override
    protected void initViews(View view, RecordDatePop recordDatePop) {
        mRvDate = view.findViewById(R.id.rv_record_date);
        mSpace = findViewById(R.id.record_spacing);
        mRvDate.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        mDateAdapter = new DateAdapter();
        mRvDate.setAdapter(mDateAdapter);
        mSpace.setOnClickListener(this);
        initBetData();

    }



    private void initBetData() {
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean("今天", TimeUtils.getBeginStringOfToday(), TimeUtils.getEndStringOfToday(), "0", true));
        list.add(new ConditionBean("昨天", TimeUtils.getBeginStringOfYesterday(1), TimeUtils.getEndStringOfYesterday(1), "0", false));
        list.add(new ConditionBean("近三日", TimeUtils.getLatelyStringOfN(3), TimeUtils.getEndStringOfToday(), "0", false));
        list.add(new ConditionBean("近七日", TimeUtils.getLatelyStringOfN(7), TimeUtils.getEndStringOfToday(), "0", false));
        list.add(new ConditionBean("近15日(低频)", TimeUtils.getLatelyStringOfN(15), TimeUtils.getEndStringOfToday(), "1", false));

        mDateAdapter.setNewInstance(list);
    }

    @Override
    public void onClick(View v) {
          if (v.getId() == R.id.record_spacing) {
            dismiss();
        }
    }

    class DateAdapter extends BaseQuickAdapter<ConditionBean, BaseViewHolder> {

        public DateAdapter() {
            super(R.layout.ski_item_trend_game);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, ConditionBean bean) {
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
                    if (mDateChooseListener != null) {
                        mDateChooseListener.onDateChoose(bean);
                    }
                }
            });
        }
    }

    public void setDateChooseListener(DateChooseListener listener) {
        mDateChooseListener = listener;
    }

    public interface DateChooseListener {
        void onDateChoose(ConditionBean bean);
    }
}
