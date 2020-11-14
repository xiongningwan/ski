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
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.TimeUtils;
import com.zyyoona7.popup.BasePopup;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordDate2Pop extends BasePopup<RecordDate2Pop> implements View.OnClickListener {
    private RecyclerView mRvDate;
    private DateAdapter mDateAdapter;
    private DateChooseListener mDateChooseListener;
    private View mSpace;

    public static RecordDate2Pop create(Context context) {
        return new RecordDate2Pop(context);
    }

    protected RecordDate2Pop(Context context) {
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
    protected void initViews(View view, RecordDate2Pop recordDatePop) {
        mRvDate = view.findViewById(R.id.rv_record_date);
        mSpace = findViewById(R.id.record_spacing);
        mRvDate.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        mDateAdapter = new DateAdapter();
        mRvDate.setAdapter(mDateAdapter);
        mSpace.setOnClickListener(this);
        initData();
    }

    private void initData() {
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean(LanguageUtil.getText("今天"), TimeUtils.getBeginStringOfToday(), TimeUtils.getEndStringOfToday(), true));
        list.add(new ConditionBean(LanguageUtil.getText("昨天"), TimeUtils.getBeginStringOfYesterday(1), TimeUtils.getEndStringOfYesterday(1), false));
        list.add(new ConditionBean(LanguageUtil.getText("前天"), TimeUtils.getBeginStringOfYesterday(2), TimeUtils.getEndStringOfYesterday(2), false));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("M月d日");
        try {
            Date date1 = sdf.parse(TimeUtils.getBeginStringOfYesterday(3));
            Date date2 = sdf.parse(TimeUtils.getBeginStringOfYesterday(4));
            Date date3 = sdf.parse(TimeUtils.getBeginStringOfYesterday(5));
            Date date4 = sdf.parse(TimeUtils.getBeginStringOfYesterday(6));
            String day4 = sdf2.format(date1);
            String day5 = sdf2.format(date2);
            String day6 = sdf2.format(date3);
            String day7 = sdf2.format(date4);
            // Ngày11tháng10
            if("vi".equals(LanguageUtil.getLanguage())) {
                Locale locale = new Locale("vi");
                SimpleDateFormat sdf3 = new SimpleDateFormat("MMM-dd", locale);
//                SimpleDateFormat sdf3 = new SimpleDateFormat("MMMMM-dd", locale);

                 day4 =  sdf3.format(date1);
                 day5 = sdf3.format(date2);
                 day6 = sdf3.format(date3);
                 day7 = sdf3.format(date4);
            }
            list.add(new ConditionBean(day4, TimeUtils.getBeginStringOfYesterday(3), TimeUtils.getEndStringOfYesterday(3), false));
            list.add(new ConditionBean(day5, TimeUtils.getBeginStringOfYesterday(4), TimeUtils.getEndStringOfYesterday(4), false));
            list.add(new ConditionBean(day6, TimeUtils.getBeginStringOfYesterday(5), TimeUtils.getEndStringOfYesterday(5), false));
            list.add(new ConditionBean(day7, TimeUtils.getBeginStringOfYesterday(6), TimeUtils.getEndStringOfYesterday(6), false));

        } catch (Exception e) {
            e.printStackTrace();
        }


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
