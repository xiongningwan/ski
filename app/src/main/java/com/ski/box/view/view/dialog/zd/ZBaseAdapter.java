package com.ski.box.view.view.dialog.zd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Author:zx on 2019/9/2520:38
 */
public abstract class ZBaseAdapter<T> extends RecyclerView.Adapter<BindViewHolder> {

    private final int layoutRes;
    private List<T> datas;
    private ZDialog dialog;
    private OnAdapterItemClickListener adapterItemClickListener;

    protected abstract void onBind(BindViewHolder holder, int position, T t);

    public ZBaseAdapter(@LayoutRes int layoutRes, List<T> datas) {
        this.layoutRes = layoutRes;
        this.datas = datas;
    }

    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    @Override
    public void onBindViewHolder(final BindViewHolder holder, final int position) {
        onBind(holder, position, datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterItemClickListener.onItemClick(holder, position, datas.get(position), dialog);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (null != datas) {
            return datas.size();
        } else {
            return 0;
        }
    }

    public void setZDialog(ZDialog zDialog) {
        this.dialog = zDialog;
    }

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    public interface OnAdapterItemClickListener<T> {
        void onItemClick(BindViewHolder holder, int positon, T t, ZDialog zDialog);
    }
}

