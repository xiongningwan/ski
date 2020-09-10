package com.ski.box.view.activity.old;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.view.view.BaseDialog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SingleDialog2 {
    private final Context mContext;
    private final BaseDialog mBaseDialog;
    private static ParamsAdapter mAdapter;
    RecyclerView rv;

    public SingleDialog2(@NonNull Context context, List<EnviromentBean> list) {
        mContext = context;
        mBaseDialog = new BaseDialog(context, R.style.Theme_AppCompat_Dialog);
        mBaseDialog.setContentView(R.layout.ski_dialog_single);
        mBaseDialog.setCancelable(true);
        mBaseDialog.setCanceledOnTouchOutside(true);
        TextView tvTitle = mBaseDialog.findViewById(R.id.tv_title);
        tvTitle.setText("请选择环境");
        rv = mBaseDialog.findViewById(R.id.rv_login_parms);
        rv.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new ParamsAdapter(list);
        rv.setAdapter(mAdapter);
//        mAdapter.setNewData(list);
    }

    public void show() {
        if (mBaseDialog != null && !mBaseDialog.isShowing()) {
            mBaseDialog.show();
        }
    }

    public void dismiss() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
        }
    }

    public ParamsAdapter getmAdapter() {
        return mAdapter;
    }

   public static class ParamsAdapter extends BaseQuickAdapter {

        public ParamsAdapter(List<EnviromentBean> list) {
            super(R.layout.ski_dialog_single_item, list);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, @Nullable Object o) {
            EnviromentBean bean = (EnviromentBean) o;
            holder.setText(R.id.tv_name, bean.getName());
        }
    }

}
