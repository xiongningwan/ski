package com.ski.box.utils.lottery;



import com.ski.box.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigurationUiUtils {
    public static HashMap<String, Integer> kuaiSanMap = new HashMap<>();
    public static HashMap<String, Integer> pk10checkNumsColor = new HashMap<>();
    public static HashMap<String, Integer> pk10bgMap = new HashMap<>();
    public static HashMap<String, Integer> hlcbgMap = new HashMap<>();
    public static HashMap<String, Integer> hlcbg6Map = new HashMap<>();

    public static final List<Integer> red = new ArrayList<>(Arrays.asList(1, 2, 7, 8, 12, 13, 18, 19, 23, 24, 29, 30, 34, 35, 40, 45, 46));
    public static final List<Integer> green = new ArrayList<>(Arrays.asList(5, 6, 11, 16, 17, 21, 22, 27, 28, 32, 33, 38, 39, 43, 44, 49));
    public static final List<Integer> blue = new ArrayList<>(Arrays.asList(3, 4, 9, 10, 14, 15, 20, 25, 26, 31, 36, 37, 41, 42, 47, 48));

    static {
        kuaiSanMap.put("1", R.mipmap.touzi1);
        kuaiSanMap.put("2", R.mipmap.touzi2);
        kuaiSanMap.put("3", R.mipmap.touzi3);
        kuaiSanMap.put("4", R.mipmap.touzi4);
        kuaiSanMap.put("5", R.mipmap.touzi5);
        kuaiSanMap.put("6", R.mipmap.touzi6);
        kuaiSanMap.put("yu", R.drawable.ski_bg_k3_yxx_yu_selector);
        kuaiSanMap.put("xia", R.drawable.ski_bg_k3_yxx_xia_selector);
        kuaiSanMap.put("hulu", R.drawable.ski_bg_k3_yxx_hulu_selector);
        kuaiSanMap.put("jinxian", R.drawable.ski_bg_k3_yxx_jinxian_selector);
        kuaiSanMap.put("xie", R.drawable.ski_bg_k3_yxx_xie_selector);
        kuaiSanMap.put("ji", R.drawable.ski_bg_k3_yxx_ji_selector);

        /*pk10选中颜色*/
        pk10checkNumsColor.put("1", R.color.ski_pk10_1);
        pk10checkNumsColor.put("2", R.color.ski_pk10_2);
        pk10checkNumsColor.put("3", R.color.ski_pk10_3);
        pk10checkNumsColor.put("4", R.color.ski_pk10_4);
        pk10checkNumsColor.put("5", R.color.ski_pk10_5);
        pk10checkNumsColor.put("6", R.color.ski_pk10_6);
        pk10checkNumsColor.put("7", R.color.ski_pk10_7);
        pk10checkNumsColor.put("8", R.color.ski_pk10_8);
        pk10checkNumsColor.put("9", R.color.ski_pk10_9);
        pk10checkNumsColor.put("10", R.color.ski_pk10_10);

        pk10bgMap.put("1", R.drawable.ski_bet_top_result_pk10_bg_1);
        pk10bgMap.put("2", R.drawable.ski_bet_top_result_pk10_bg_2);
        pk10bgMap.put("3", R.drawable.ski_bet_top_result_pk10_bg_3);
        pk10bgMap.put("4", R.drawable.ski_bet_top_result_pk10_bg_4);
        pk10bgMap.put("5", R.drawable.ski_bet_top_result_pk10_bg_5);
        pk10bgMap.put("6", R.drawable.ski_bet_top_result_pk10_bg_6);
        pk10bgMap.put("7", R.drawable.ski_bet_top_result_pk10_bg_7);
        pk10bgMap.put("8", R.drawable.ski_bet_top_result_pk10_bg_8);
        pk10bgMap.put("9", R.drawable.ski_bet_top_result_pk10_bg_9);
        pk10bgMap.put("10", R.drawable.ski_bet_top_result_pk10_bg_10);
    }

    //六合彩颜色
    public static Integer getLHCBg(int code) {
        if (red.contains(code)) {
            return R.mipmap.ski_ball_red_28;
        } else if (green.contains(code)) {
            return R.mipmap.ski_ball_green_28;
        } else if (blue.contains(code)) {
            return R.mipmap.ski_ball_blue_28;
        } else {
            return R.mipmap.ski_ball_red_28;
        }
    }

}
