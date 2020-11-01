package com.ski.box.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tom on 2020/10/23.
 */
public class HeaderUtil {
    public static Map<String,String> getHeader(String token,String authorization,String device, String lang) {
        //        if(!TextUtils.isEmpty(mToken)) {
//            builder.addHeader("token", mToken);
//        }
//        if(!TextUtils.isEmpty(mAuthorization)) {
//            builder.addHeader("authorization", mAuthorization);
//        }
//        builder.addHeader("device", "3");
        Map<String,String> map = new HashMap<>();
        if(!TextUtils.isEmpty(token)) {
            map.put("token",token);
        }
        if(!TextUtils.isEmpty(authorization)) {
            map.put("authorization",authorization);
        }
        if(!TextUtils.isEmpty(device)) {
            map.put("device",device);
        }
        if(!TextUtils.isEmpty(lang)) {
            map.put("lang",lang);
        }
        return map;
    }
}
