package com.ski.box.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ski.box.R;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.QuickSetMoneyBean2;
import com.ski.box.view.view.dialog.CustomBottomDialog;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST;


public class QuickMoneySettingView2 extends LinearLayout {
    private ImageView mClosePopSetquickamount;
    private RecyclerView recycleview;
    private TextView mMarkedWords;
    private TextView mRestoreDefault;
    private TextView mSaveQuickAmount;
    private SettingMoneyRecyclerAdapter2 mAdapter;
    private CustomBottomDialog mDailog;
    private List<String> copyDates;
    StringBuffer stringBuffer = new StringBuffer();
    private EditText currentEditText;

    public QuickMoneySettingView2(Context context) {
        super(context);
        initLayout(context);
    }


    public QuickMoneySettingView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public QuickMoneySettingView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        View.inflate(getContext(), R.layout.ski_double_dialog_etquick_money2, this);
        mClosePopSetquickamount = findViewById(R.id.close_pop_setquickamount);
        recycleview = findViewById(R.id.recycle_setting_quick_money);
        mMarkedWords = findViewById(R.id.marked_words);
        mRestoreDefault = findViewById(R.id.restore_default);
        mSaveQuickAmount = findViewById(R.id.save_quick_amount);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recycleview.setLayoutManager(gridLayoutManager);
        mAdapter = new SettingMoneyRecyclerAdapter2();
        recycleview.setAdapter(mAdapter);

        /*恢复默认值*/
        mRestoreDefault.setOnClickListener(v -> {
            SPUtils.putString(getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, "");
            mAdapter.setNewInstance(getData());
            saveSelectedList(getDefaultSelectedList());
            ToastUtil.showInfo("已恢复默认值设置");
        });
        mSaveQuickAmount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<QuickSetMoneyBean2> list = (List<QuickSetMoneyBean2>) mAdapter.getData();
                List<QuickSetMoneyBean2> selectedList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    QuickSetMoneyBean2 bean2 = list.get(i);
                    if (bean2.isSelected()) {
                        selectedList.add(bean2);
                    }
                }
                saveSelectedList(selectedList);
                if (mDailog != null) {
                    mDailog.dismiss();
                }
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                List<QuickSetMoneyBean2> list = (List<QuickSetMoneyBean2>) adapter.getData();
                QuickSetMoneyBean2 bean = list.get(position);
                int selectedCount = getSelectedCount(list);
                if(!bean.isSelected()) {
                    if (selectedCount > 4) {
                        ToastUtil.showWarning("最多添加5个");
                        return;
                    }
                } else {
                     if (selectedCount == 1) {
                        ToastUtil.showWarning("最少添加1个");
                        return;
                    }
                }

                if(bean.isSelected()) {
                    bean.setSelected(false);
                } else {
                    bean.setSelected(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void saveSelectedList(List<QuickSetMoneyBean2> selectedList) {
        Gson gson = new Gson();
        String s = gson.toJson(selectedList);
        SPUtils.putString(QuickMoneySettingView2.this.getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, s);
    }

    private int getSelectedCount(List<QuickSetMoneyBean2> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            QuickSetMoneyBean2 bean2 = list.get(i);
            if (bean2.isSelected()) {
                count++;
            }
        }
        return count;
    }


    private boolean hasChange() {
        boolean showDialog = false;
//        for (int x = 0; x < mDatas.size(); x++) {
//            QuickSetMoneyBean quickSetMoneyBean = mDatas.get(x);
//            String money = quickSetMoneyBean.getMoney();
//            if (copyDates == null) {
//                return false;
//            }
//            String s = copyDates.get(x);
//            if (!money.equals(s)) {
//                showDialog = true;
//                break;
//            }
//        }
        if (showDialog) {
            AlertConfigurationBean bean = new AlertConfigurationBean();
            bean.setContent("有修改的内容未保存，确定离开吗？");
            bean.setLeftButtonText("取消");
            bean.setRightButtonText("离开");
            new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
                @Override
                public void rightButton() {
                    if (mDailog != null) {
                        mDailog.dismiss();
                    }
                }

                @Override
                public void leftButton() {

                }
            });
        }
        return showDialog;
    }


    public void setTDialog(CustomBottomDialog tDialog) {
        this.mDailog = tDialog;
        /*关闭弹框*/
        mClosePopSetquickamount.setOnClickListener(v -> {
            boolean b = hasChange();
            if (!b) {
               /* View childAt = recycleview.getChildAt(0);
                EditText viewById = childAt.findViewById(R.id.set_amount);
                KeyBoardUtils.closeKeybord(viewById, getContext());*/
                mDailog.dismiss();
            }
        });
        mAdapter.setNewInstance(getData());
    }

    //    10、20、50、100、
//            200、500、1k、2k、
//            5k、10k、20k、50k
    public static class SettingMoneyRecyclerAdapter2 extends BaseQuickAdapter<QuickSetMoneyBean2, BaseViewHolder> {

        public SettingMoneyRecyclerAdapter2() {
            super(R.layout.ski_item_quick_chouma);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, QuickSetMoneyBean2 bean) {
            TextView tvChou = holder.getView(R.id.tv_ch);
            tvChou.setText(bean.getMoney());
            if (bean.isSelected()) {
                tvChou.setSelected(true);
            } else {
                tvChou.setSelected(false);
            }
        }
    }

    private List<QuickSetMoneyBean2> getData() {
        List<QuickSetMoneyBean2> list = new ArrayList<>();
        String[] mArr = {"10", "20", "50", "100", "200", "500", "1k", "2k", "5k", "10k", "20k", "50k"};
        List<QuickSetMoneyBean2> defaultList = getSelectedList();
        for (int i = 0; i < mArr.length; i++) {
            boolean isSelected = false;
            for(int j = 0; j < defaultList.size(); j++) {
                QuickSetMoneyBean2 bean2 =   defaultList.get(j);
                if(mArr[i].equals(bean2.getMoney())) {
                    isSelected = true;
                }
            }
            list.add(new QuickSetMoneyBean2(mArr[i], isSelected));
        }
        return list;
    }

    private List<QuickSetMoneyBean2> getSelectedList() {
        String json = SPUtils.getString(getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, "");
        Gson gson  = new Gson();
        List<QuickSetMoneyBean2> list = gson.fromJson(json, new TypeToken<List<QuickSetMoneyBean2>>() {
        }.getType());
        if(list == null) {
            list = getDefaultSelectedList();
        }
        return list;
    }

    private List<QuickSetMoneyBean2> getDefaultSelectedList() {
        String[] arr = {"10", "20", "50", "100", "200"};
        List<QuickSetMoneyBean2>  list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new QuickSetMoneyBean2(arr[i], true));
        }
        return list;
    }
}
