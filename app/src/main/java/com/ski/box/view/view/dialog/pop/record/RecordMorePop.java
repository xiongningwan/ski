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
import com.zyyoona7.popup.BasePopup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecordMorePop extends BasePopup<RecordMorePop> implements View.OnClickListener {
    private static Context mContext;
    private DateAdapter mAdapter;
    private MoreChooseListener mListener;
/*    private TextView mTvType1;
    private TextView mTvType2;
    private TextView mTvType3;*/
    private RecyclerView mRvTypeChild;
/*    private TextView mTvCon1;
    private TextView mTvCon2;
    private TextView mTvCon3;
    private TextView mTvCon4;
    private TextView mBtnConfirm;
    private ImageView triangle;*/
    private float oldPosition;
    private View mSpace;
    /*0 是投注 1是追号*/
    private static int type = 0;

    public static RecordMorePop create(Context context, int i) {
        mContext = context;
        type = i;
        return new RecordMorePop(context);
    }

    protected RecordMorePop(Context context) {
        setContext(context);
    }

    @Override
    protected void initAttributes() {
//        setContentView(R.layout.ski_pop_record_more, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.ski_pop_record_more_second, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
        setBackgroundDimEnable(false);

        // setNeedReMeasureWH(true);
    }

    @Override
    protected void initViews(View view, RecordMorePop recordDatePop) {
      /*  mTvType1 = view.findViewById(R.id.tv_type_1);
        mTvType2 = view.findViewById(R.id.tv_type_2);
        mTvType3 = view.findViewById(R.id.tv_type_3);

        mTvCon1 = view.findViewById(R.id.tv_con_1);
        mTvCon2 = view.findViewById(R.id.tv_con_2);
        mTvCon3 = view.findViewById(R.id.tv_con_3);
        mTvCon4 = view.findViewById(R.id.tv_con_4);
        mBtnConfirm = view.findViewById(R.id.btn_confirm);
        triangle = findViewById(R.id.triangle);
        mTvType1.setOnClickListener(this);
        mTvType2.setOnClickListener(this);
        mTvType3.setOnClickListener(this);
        mTvCon1.setOnClickListener(this);
        mTvCon2.setOnClickListener(this);
        mTvCon3.setOnClickListener(this);
        mTvCon4.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);*/

        mSpace = findViewById(R.id.record_spacing);
        mRvTypeChild = view.findViewById(R.id.rv_type_child);
        mRvTypeChild.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        mAdapter = new DateAdapter();
        mRvTypeChild.setAdapter(mAdapter);
        mSpace.setOnClickListener(this);
//        mTvType1.setSelected(true);
//        move(1);
        initData();
    }

    private void initData() {
        if (type == 0) {
           /*投注*/
            mAdapter.setNewInstance(getBet());
        } else {
            /*追号*/
            mAdapter.setNewInstance(getChase());
        }

//        mAdapter.setNewData(getAll());
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
                    List<ConditionBean> data = getData();
                    for (ConditionBean bean: data) {
                        bean.setSelected(false);

                    }
                    bean.setSelected(true);
                    notifyDataSetChanged();
              /*      if (bean.isSelected()) {
                        bean.setSelected(false);
                        holder.itemView.setSelected(false);
                    } else {
                        bean.setSelected(true);
                        holder.itemView.setSelected(true);
                    }*/

                    mListener.onMoreChoose(bean);

                }
            });
        }
    }

    public void setMoreChooseListener(MoreChooseListener listener) {
        mListener = listener;
    }

    public interface MoreChooseListener {
        void onMoreChoose(ConditionBean bean);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.record_spacing) {
            dismiss();
        }

     /*   if (v.getId() == R.id.tv_type_1) {
            mAdapter.setNewData(getAll());
            mTvType1.setSelected(true);
            mTvType2.setSelected(false);
            mTvType3.setSelected(false);
            move(1);
        } else if (v.getId() == R.id.tv_type_2) {
            mAdapter.setNewData(getBet());
            mTvType1.setSelected(false);
            mTvType2.setSelected(true);
            mTvType3.setSelected(false);
            move(2);
        } else if (v.getId() == R.id.tv_type_3) {
            mAdapter.setNewData(getChase());
            mTvType1.setSelected(false);
            mTvType2.setSelected(false);
            mTvType3.setSelected(true);
            move(3);
        } else if (v.getId() == R.id.tv_con_1) {
            List<ConditionBean> list = mAdapter.getData();
            for (ConditionBean bean : list) {
                bean.setSelected(true);
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.tv_con_2) {
            List<ConditionBean> list = mAdapter.getData();
            for (ConditionBean bean : list) {
                bean.setSelected(false);
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.tv_con_3) {
            List<ConditionBean> list = mAdapter.getData();
            for (ConditionBean bean : list) {
                if ("待开奖".equals(bean.getName()) || "追号中".equals(bean.getName())) {
                    bean.setSelected(true);
                } else {
                    bean.setSelected(false);
                }
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.tv_con_4) {
            List<ConditionBean> list = mAdapter.getData();
            for (ConditionBean bean : list) {
                if ("待开奖".equals(bean.getName()) || "追号中".equals(bean.getName())) {
                    bean.setSelected(false);
                } else {
                    bean.setSelected(true);
                }
            }
            mAdapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.btn_confirm) {
            if (mListener != null) {
                List<ConditionBean> list = mAdapter.getData();
                StringBuilder sb = new StringBuilder();
                for (ConditionBean bean : list) {
                    if (bean.isSelected())
                        sb.append(bean.getStatus()).append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                mListener.onMoreChoose(sb.toString());
            }
        } else if (v.getId() == R.id.record_spacing) {
            dismiss();
        }*/
    }


    private List<ConditionBean> getAll() {
        //1：待开奖 2：未中奖 3：已中奖 4：追号中 5：已完成追号 6：不中停追 7.中奖停追 8.个人撤单 9.系统撤单 10.未结清 11.已结清；不传则查全部；多个状态逗号分隔
        //  private List<String> list_all = new ArrayList<>(Arrays.asList("待开奖", "未中奖", "已中奖", "追号中", "已完成追号", "不中停追", "中奖停追", "个人撤单", "系统撤单"));
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean("待开奖", "1", false));
        list.add(new ConditionBean("未中奖", "2", false));
        list.add(new ConditionBean("已中奖", "3", false));
        list.add(new ConditionBean("追号中", "4", false));
        list.add(new ConditionBean("已完成追号", "5", false));
        list.add(new ConditionBean("不中停追", "6", false));
        list.add(new ConditionBean("中奖停追", "7", false));
        list.add(new ConditionBean("个人撤单", "8", false));
        list.add(new ConditionBean("系统撤单", "9", false));
        return list;
    }

    private List<ConditionBean> getBet() {
        //1：待开奖 2：未中奖 3：已中奖 4：个人撤单 5：系统撤单
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean("全部状态", "", false));
        list.add(new ConditionBean("待开奖", "1", false));
        list.add(new ConditionBean("未中奖", "2", false));
        list.add(new ConditionBean("已中奖", "3", false));
        list.add(new ConditionBean("个人撤单", "4", false));
        list.add(new ConditionBean("系统撤单", "5", false));
        return list;
    }

    private List<ConditionBean> getChase() {
       /* //1：待开奖 2：未中奖 3：已中奖 4：追号中 5：已完成追号 6：不中停追 7.中奖停追 8.个人撤单 9.系统撤单 10.未结清 11.已结清；不传则查全部；多个状态逗号分隔
        //  private List<String> list_all = new ArrayList<>(Arrays.asList("待开奖", "未中奖", "已中奖", "追号中", "已完成追号", "不中停追", "中奖停追", "个人撤单", "系统撤单"));
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean("追号中", "4", false));
        list.add(new ConditionBean("已完成追号", "5", false));
        list.add(new ConditionBean("不中停追", "6", false));
        list.add(new ConditionBean("中奖停追", "7", false));
        list.add(new ConditionBean("个人撤单", "8", false));
        list.add(new ConditionBean("系统撤单", "9", false));*/

         //1：追号中 2：已完成 3：个人停追 4：系统停追
        List<ConditionBean> list = new ArrayList<>();
        list.add(new ConditionBean("全部注单", "", false));
        list.add(new ConditionBean("追号中", "1", false));
        list.add(new ConditionBean("已完成", "2", false));
        list.add(new ConditionBean("个人停追", "3", false));
        list.add(new ConditionBean("系统停追", "4", false));
        return list;
    }


/*    private void move(int position) {
        int w = ScreenUtils.getScreenWidth(mContext) - ScreenUtils.dip2px(0);
        float location = ScreenUtils.dip2px(100);
        float itemWidth = w / 3;
        float triangleW = ScreenUtils.dip2px(16);
        switch (position) {
            case 1:
                location = itemWidth / 2 - triangleW / 2 ;
                break;
            case 2:
                location = (float) (itemWidth * 1.5 - triangleW / 2 );
                break;
            case 3:
                location = (float) (itemWidth * 2.5 - triangleW / 2);
                break;
        }
        ObjectAnimator.ofFloat(triangle, "translationX", location).setDuration(300).start();
    }*/
}
