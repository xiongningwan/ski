package com.ski.box.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.ski.box.R;

import java.util.Locale;

/**
 * Created by tom on 2020/10/29.
 */
public class ActivityUtil {
    //空布局
    public static View getEmptyView(Context context) {
        View notDataView = View.inflate(context, R.layout.ski_recycler_empty_view, null);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return notDataView;
    }

    // 返奖点百分比
    public static String getRebatePercent(int i, int base) {
        float f = (i - base) * 100f / 2000;
        String percent = String.format("%.2f", f) + "%";
        return percent;
    }
    //lang=zh/en/vi ;zh 标识简体中文，en标识英文 vi越南语
    public static String getDeviceLanguage(Context context) {
        Locale locale = Locale.getDefault();
        //>=24 is Android 7.0 or high
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        }
//        String language = locale.getLanguage() + "-" + locale.getCountry();
        String language = locale.getLanguage();
        return language;
    }
}
