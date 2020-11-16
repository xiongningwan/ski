package com.yb.core.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;


public class ToastUtil {
    private static String mLastErrorText;
    private static long mLastErrorTime;
    private static boolean isForeground = true;

    private ToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param text
     */
    public static void showError(String text) {
        if (!isForeground) return;
        mLastErrorText = text;
        long now = System.currentTimeMillis();
        if (isRepeatText(text)) {
            if (now - mLastErrorTime > 3500) {
                mLastErrorTime = now;
                Toasty.error(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT, true).show();
            }
        } else {
            mLastErrorTime = now;
            Toasty.error(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT, true).show();
        }
    }

    public static void showError(@StringRes int message) {
        Context context = AppUtil.getContext();
        String text = context.getString(message);
        showError(text);
    }

    public static void showSuccess(String text) {
        Toasty.success(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT, true).show();
    }

    public static void showNormal(String text) {
        Toasty.normal(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT).show();
    }

    public static void showInfo(String text) {
        Toasty.info(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT, true).show();
    }

    public static void showWarning(String text) {
        Toasty.warning(AppUtil.getContext(), LanguageUtil.getText(text), Toast.LENGTH_SHORT, true).show();
    }

    public static void initToasty() {
        Toasty.Config.getInstance().allowQueue(false).apply();
    }

    private static boolean isRepeatText(String text) {
        if (!TextUtils.isEmpty(mLastErrorText) && mLastErrorText.equals(text)) {
            return true;
        }
        return false;
    }

    public static boolean isForeground() {
        return isForeground;
    }

    public static void setIsForeground(boolean isForeground) {
        ToastUtil.isForeground = isForeground;
    }
}
