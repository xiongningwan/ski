package com.ski.box.utils;

import android.content.Context;
import android.view.View;

import com.ski.box.R;

/**
 * Created by tom on 2020/10/29.
 */
public class ActivityUtil {
    //空布局
    public static View getEmptyView(Context context) {
        View notDataView = View.inflate(context, R.layout.ski_recycler_empty_view,  null);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return notDataView;
    }
}