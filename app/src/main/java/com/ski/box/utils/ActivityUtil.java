package com.ski.box.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.ski.box.ConstantValue;
import com.ski.box.R;
import com.ski.box.bean.DataCenter;
import com.ski.box.view.activity.AgentWebViewActivity;
import com.ski.box.view.view.CusTextView;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LanguageUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tom on 2020/10/29.
 */
public class ActivityUtil {
    private static DecimalFormat mDecimalFormat;
    private static Typeface mFontTNR;

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



    // 头像
    public static int getHeadByProfile(String profile) {
        int iconRes = R.mipmap.ski_icon_head_1;
        if (TextUtils.isEmpty(profile)) {
            return iconRes;
        }
        switch (profile) {
            case "1":
                iconRes = R.mipmap.ski_icon_head_1;
                break;
            case "2":
                iconRes = R.mipmap.ski_icon_head_2;
                break;
            case "3":
                iconRes = R.mipmap.ski_icon_head_3;
                break;
            case "4":
                iconRes = R.mipmap.ski_icon_head_4;
                break;
            case "5":
                iconRes = R.mipmap.ski_icon_head_5;
                break;
            case "6":
                iconRes = R.mipmap.ski_icon_head_6;
                break;
            case "7":
                iconRes = R.mipmap.ski_icon_head_7;
                break;
            case "8":
                iconRes = R.mipmap.ski_icon_head_8;
                break;
            case "9":
                iconRes = R.mipmap.ski_icon_head_9;
                break;
            case "10":
                iconRes = R.mipmap.ski_icon_head_10;
                break;
            case "11":
                iconRes = R.mipmap.ski_icon_head_11;
                break;
            case "12":
                iconRes = R.mipmap.ski_icon_head_12;
                break;
            case "13":
                iconRes = R.mipmap.ski_icon_head_13;
                break;
            case "14":
                iconRes = R.mipmap.ski_icon_head_14;
                break;
            case "15":
                iconRes = R.mipmap.ski_icon_head_15;
                break;
            case "16":
                iconRes = R.mipmap.ski_icon_head_16;
                break;
            case "17":
                iconRes = R.mipmap.ski_icon_head_17;
                break;
            case "18":
                iconRes = R.mipmap.ski_icon_head_18;
                break;
            case "19":
                iconRes = R.mipmap.ski_icon_head_19;
                break;
            case "20":
                iconRes = R.mipmap.ski_icon_head_20;
                break;
        }
        return iconRes;
    }

    // 设置关键字红色
    public static void setTipKeywordRed(Context context, CusTextView tv, String tip1, String keyword) {
        if (!TextUtils.isEmpty(tip1) && tip1.contains(keyword)) {
            int index = tip1.indexOf(keyword);
            int length = keyword.length();

            SpannableStringBuilder ssb = new SpannableStringBuilder(tip1);
            ForegroundColorSpan red = new ForegroundColorSpan(context.getResources().getColor(R.color.ski_color_tip_red));
            ssb.setSpan(red, index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(ssb);
        }
    }
    public static void setTipKeywordRed(Context context, TextView tv, String tip1, String keyword, String keyword2) {
        if (!TextUtils.isEmpty(tip1) && tip1.contains(keyword) && tip1.contains(keyword2)) {
            int index = tip1.indexOf(keyword);
            int length = keyword.length();
            int index2 = tip1.indexOf(keyword2);
            int length2 = keyword2.length();

            SpannableStringBuilder ssb = new SpannableStringBuilder(tip1);
            ForegroundColorSpan red = new ForegroundColorSpan(context.getResources().getColor(R.color.ski_color_tip_red));
            ForegroundColorSpan red2 = new ForegroundColorSpan(context.getResources().getColor(R.color.ski_color_tip_red));
            ssb.setSpan(red, index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(red2, index2, index2 + length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(ssb);
        }
    }

    public static String formatBonus(double b) {
        if (mDecimalFormat == null) {
            mDecimalFormat = new DecimalFormat("#0.####");
            mDecimalFormat.setRoundingMode(RoundingMode.UP);
        }
        String bonu = mDecimalFormat.format(b);
        return bonu;
    }
    public static String formatBonus2(double b) {
        return  DecimalSetUtils.setMoneySaveFour(String.valueOf(b));
    }


    public static Typeface getFontTNR() {
        if(mFontTNR == null) {
            AssetManager mgr = AppUtil.getContext().getAssets();
            mFontTNR = Typeface.createFromAsset(mgr, "fonts/Times-New-Roman.ttf");
//            mFontTNR = Typeface.createFromAsset(mgr, "fonts/element-icons.535877f5.woff");
        }
       return mFontTNR;
    }

    public static int doBetMoneyWithK(String money) {
        if(TextUtils.isEmpty(money)) {
            money = "0";
        }
        if(money.contains("k")) {
            money = money.replace("k","000");
        }
        int moneyInt = 0;
        try {
             moneyInt = Integer.parseInt(money);
        } catch (Exception e){
            e.printStackTrace();
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(money);
            money = m.replaceAll("").trim();
            DataCenter.getInstance().setQuiickMoney(Integer.parseInt(money));
        }

        return moneyInt;
    }


    public static void startPromoAct(Context context) {
        String lang = "CN";
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            lang = "VN";
        }
        String url = ConstantValue.PROMO_URL + "?token=" + DataCenter.getInstance().getToken()
                + "&auth=" + DataCenter.getInstance().getAuthorization()
                + "&header=false&footer=false&lang=vi-" + lang;
        AgentWebViewActivity.startAgentWebView(context, LanguageUtil.getText("活动优惠"), url);
    }
}
