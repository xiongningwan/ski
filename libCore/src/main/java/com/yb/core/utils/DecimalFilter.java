package com.yb.core.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalFilter implements InputFilter {
    private static final String TAG = "DecimalFilter";
    Pattern mPattern;//正则

    public DecimalFilter(int lengthBeforeDot, int lengthAfterDot) {
        String pattern = "[0-9]{0," + lengthBeforeDot + "}+((\\.[0-9]{0," + lengthAfterDot + "})?)||(\\.)?";//小数
        Log.i(TAG, "pattern: " + pattern);
        mPattern = Pattern.compile(pattern);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        String formatedSource = source.subSequence(start, end).toString();
        String destPrefix = dest.subSequence(0, dstart).toString();
        String destSuffix = dest.subSequence(dend, dest.length()).toString();
        //算出变化后的值:
        String result = destPrefix + formatedSource + destSuffix;
        Log.i(TAG, "result: " + result);
        Matcher matcher = mPattern.matcher(result);
        if (!matcher.matches()) {
            String old = dest.subSequence(dstart, dend).toString();
            return old;//过滤,返回被替换前的原始值
        }
        return null;//不过滤
    }
}

