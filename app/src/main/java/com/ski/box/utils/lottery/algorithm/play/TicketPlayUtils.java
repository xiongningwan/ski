package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TicketPlayUtils {
    private static Map<Integer, Long> pk10MoneyMap = new HashMap();

    public TicketPlayUtils() {
    }

    public static String[] getOpenNums(String openNums) {
        String[] nums = null;
        if (openNums.contains(",")) {
            nums = openNums.split(",");
        } else if (openNums.trim().contains(" ")) {
            nums = openNums.split(" ");
        } else {
            nums = new String[openNums.trim().length()];

            for(int i = 0; i < openNums.trim().length(); ++i) {
                nums[i] = String.valueOf(openNums.trim().charAt(i));
            }
        }

        return nums;
    }

    public static String[] getOpenNums(String openNums, int[] offsets) {
        String[] nums = null;
        if (openNums.contains(",")) {
            nums = openNums.split(",");
        } else if (openNums.trim().contains(" ")) {
            nums = openNums.split(" ");
        } else {
            nums = new String[openNums.trim().length()];

            for(int i = 0; i < openNums.trim().length(); ++i) {
                nums[i] = String.valueOf(openNums.trim().charAt(i));
            }
        }

        if (nums == null) {
            return null;
        } else {
            String[] res = new String[offsets.length];

            for(int i = 0; i < offsets.length; ++i) {
                res[i] = nums[offsets[i]].trim();
            }

            return res;
        }
    }

    public static String getSortedNums(String nums) {
        char[] c = nums.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public static String[] getFixedNums(String[] nums) {
        if (nums != null && nums.length > 0) {
            Set<String> res = new HashSet();
            String[] var2 = nums;
            int var3 = nums.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String num = var2[var4];
                if (StringUtils.isNotEmpty(num)) {
                    res.add(num);
                }
            }

            return res.toArray(new String[0]);
        } else {
            return null;
        }
    }

    public static String[] getFixedAndSortedNums(String[] nums) {
        if (nums != null && nums.length > 0) {
            TreeSet<String> res = new TreeSet();
            String[] var2 = nums;
            int var3 = nums.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String num = var2[var4];
                if (StringUtils.isNotEmpty(num.trim())) {
                    res.add(num);
                }
            }

            return res.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }

    public static String[] stringtoArray(String num) {
        char[] charArray = num.toCharArray();
        String[] array = new String[charArray.length];

        for(int i = 0; i < array.length; ++i) {
            array[i] = String.valueOf(charArray[i]);
        }

        return array;
    }

    public static String getString(String[] numbers) {
        StringBuilder builder = new StringBuilder();
        String[] var2 = numbers;
        int var3 = numbers.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String s = var2[var4];
            builder.append(s);
        }

        return builder.toString();
    }


    static {
        pk10MoneyMap.put(18010101, 200000L);
        pk10MoneyMap.put(18020101, 1800000L);
        pk10MoneyMap.put(18020102, 1800000L);
        pk10MoneyMap.put(18030101, 14400000L);
        pk10MoneyMap.put(18030102, 14400000L);
        pk10MoneyMap.put(18040101, 100800000L);
        pk10MoneyMap.put(18040102, 100800000L);
        pk10MoneyMap.put(18050101, 604800000L);
        pk10MoneyMap.put(18050102, 604800000L);
        pk10MoneyMap.put(18060101, 200000L);
        pk10MoneyMap.put(18070101, 40000L);
        pk10MoneyMap.put(18170101, 765000L);
        pk10MoneyMap.put(18170102, 2040000L);
    }
}
