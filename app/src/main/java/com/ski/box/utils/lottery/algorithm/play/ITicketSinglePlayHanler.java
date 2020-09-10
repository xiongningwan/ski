package com.ski.box.utils.lottery.algorithm.play;

import java.util.LinkedHashSet;
import java.util.List;

public abstract class ITicketSinglePlayHanler {
    public String mDelimiter = ",";
    public int[] OFFSETS_WUXIN = new int[]{0, 1, 2, 3, 4};
    public int[] ERXING_MULTI = new int[]{1, 3, 6, 10};
    public  int[] SANXING_MULTI = new int[]{1, 4, 10};
    public  int[] SIXING_MULTI = new int[]{1, 5};

    public abstract  String getStandSingleBetContent();

    public abstract String formatNums(String betNums);

    public abstract    int getNotCompliantNums();
    public abstract  int calculateBetNum(String betNums);
    /*去重*/
    public void removeDuplicate(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
    }

    public String getShowBetContent(List<String> objects,String mDelimiter) {
        String betContent = "";
        for (String s : objects) {
            betContent = betContent + s + mDelimiter;
        }
        int i = betContent.length();
        if (i > 0) {
            return betContent.substring(0, betContent.length() - 1);
        } else {
            return"";
        }
    }
}
