package com.ski.box.view.view.dialog.zd;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Author:zx on 2019/9/2520:47
 */

public class BindViewHolder extends RecyclerView.ViewHolder {

    private View bindView;
    private SparseArray<View> views;
    private ZDialog dialog;

    public BindViewHolder(@NonNull View itemView) {
        super(itemView);
        this.bindView = itemView;
        this.views = new SparseArray<>();
    }

    public BindViewHolder(View view, ZDialog dialog) {
        super(view);
        this.bindView = view;
        this.dialog = dialog;
        views = new SparseArray<>();
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (null == view) {
            view = bindView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BindViewHolder addOnClickListener(@IdRes int viewId) {
        final View view = getView(viewId);
        if (null != view) {
            if (view.isClickable()) {
                view.setClickable(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != dialog.getOnViewClickListener()) {
                        dialog.getOnViewClickListener().onViewClick(BindViewHolder.this, view, dialog);
                    }
                }
            });
        }
        return this;
    }

    public BindViewHolder setText(@IdRes int viewId, CharSequence content) {
        if (getView(viewId) instanceof TextView) {
            TextView view = getView(viewId);
            view.setVisibility(View.VISIBLE);
            view.setText(content);
        } else if (getView(viewId) instanceof TextView) {
            Button view = getView(viewId);
            view.setVisibility(View.VISIBLE);
            view.setText(content);
        }else if(getView(viewId) instanceof EditText){
            EditText view = getView(viewId);
            view.setVisibility(View.VISIBLE);
            view.setText(content);
        }
        return this;
    }
}

