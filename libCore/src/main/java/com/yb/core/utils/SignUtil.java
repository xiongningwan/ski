package com.yb.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {
    public static String sortHashMap(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        map.put("timestamp", System.currentTimeMillis() + "");
        List<Map.Entry<String, String>> infoIds =
                new ArrayList<Map.Entry<String, String>>(map.entrySet());
        //排序
        Collections.sort(infoIds, (o1, o2) -> {
            try {
                return o1.getKey().compareTo(o2.getKey());
            } catch (Exception e) {
                return 0;
            }
        });
        for (int i = infoIds.size() - 1; i >= 0; i--) {
            if (!"loginType".equalsIgnoreCase(infoIds.get(i).getKey())) {
                try {
                    stringBuilder.insert(0, infoIds.get(i).getKey() + "" + infoIds.get(i).getValue());
                } catch (Exception e) {
                    LogUtils.e(e.toString());
                }
            }
        }
        stringBuilder.append("abcdefghijklmnop");
        LogUtils.d(stringBuilder.toString());
        return crypt(stringBuilder.toString());
    }

    public static String crypt(String str) {
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" +
                            Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF &
                            hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}
