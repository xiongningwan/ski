package com.yb.core.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by tom on 2020/11/7.
 */
public class LanguageUtil {
    public static final String KEY_SP_LANGUAGE = "sp_language";
    private static String mLanguage;
    private static Map<String, String> mLMap = new HashMap<>();
    public static final String ZH = "zh";
    public static final String VI = "vi";

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

    public static String getLanguage() {
        return mLanguage;
    }

    public static void initLanguage(Context context) {
        mLanguage = SPUtils.getString(context, KEY_SP_LANGUAGE);
        if (TextUtils.isEmpty(mLanguage)) {
            mLanguage = getDeviceLanguage(context);
        }
        String json = "";
        switch (mLanguage) {
            case VI:
                json = AssetsReader.getJson(context, "json" + File.separator + "language" + File.separator + "zh-vi.json");
                break;
        }
        if (!TextUtils.isEmpty(json)) {
            mLMap = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {
            }.getType());
        }
    }

    public static void languageSwitch(Context context, String lang) {
        SPUtils.putString(context, KEY_SP_LANGUAGE, lang);
        mLanguage = lang;
        initLanguage(context);
    }

    public static String getText(String s_zh) {
        String Str;
        if(!ZH.equals(s_zh)) {
            Str = mLMap.get(s_zh);
            if (TextUtils.isEmpty(Str)) {
                Str = s_zh;
            }
        } else {
            Str = s_zh;
        }
        return Str;
    }
}
