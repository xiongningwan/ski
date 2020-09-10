package com.ski.box.utils.lottery.algorithm.utils.string;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StringUtilTools extends StringUtils {

    public StringUtilTools() {
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static String toString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String[] toArrary(String str, int len) {
        if (str.length() < len) {
            return null;
        } else {
            char[] chars = str.toCharArray();
            List<String> list = new ArrayList();
            String temp = "";
            int index = 0;

            for (int i = 0; i < chars.length; ++i) {
                ++index;
                temp = temp + chars[i];
                if (len == index) {
                    list.add(temp);
                    index = 0;
                    temp = "";
                }
            }

            if (!isEmpty(temp)) {
                list.add(temp);
            }

            return list.toArray(new String[0]);
        }
    }
}