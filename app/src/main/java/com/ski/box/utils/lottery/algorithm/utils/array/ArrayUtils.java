package com.ski.box.utils.lottery.algorithm.utils.array;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public ArrayUtils() {
    }

    public static String join(String[] ary, String separator, int start, int end) {
        if (start >= 0 && end >= 0 && end <= ary.length) {
            if (ary.length != 0 && end - start > 0) {
                StringBuilder builder = new StringBuilder();

                for(int i = start; i < end; ++i) {
                    builder.append(ary[i]).append(separator);
                }

                return builder.substring(0, builder.length() - separator.length());
            } else {
                return "";
            }
        } else {
            throw new IndexOutOfBoundsException("ary size:" + ary.length + ", end is " + end);
        }
    }


    public static String[] a(String value) {
        return value.split(",");
    }


    public static boolean isExist(String[] array, String value) {
        List<String> list = Arrays.asList(array);
        return list.contains(value);
    }
}
