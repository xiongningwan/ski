package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 时时彩
 * 前三组三单式 1030205
 * 前三组六单式 1030206
 * 中三组三单式 1040205
 * 中三组六单式 1040206
 * 后三组三单式 1050205
 * 后三组六单式 1050206
 */
public class ZXZ3Z6DSPlayHandler extends ITicketSinglePlayHanler {

    private int type;
    private StringBuffer stringBuffer = new StringBuffer();
    private int offsets = 3;
    private int notFormatmNums;
    private ArrayList<String> objects;

    public ZXZ3Z6DSPlayHandler(int count) {
        this.type = count;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] strings = meetRules(betNums);
        if(strings == null) {
            return 0;
        }
        return  strings.length;

    }

    private String[] meetRules(String betNums) {
       objects = new ArrayList<>();
        String[] split = betNums.split(mDelimiter);
        List<String> strings = Arrays.asList(split);
        out:
        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == offsets) {
                /*组六*/
                List<Integer> integerNums = new ArrayList<>();
                for (int k = 0; k < s.length(); k++) {
                    try {
                        Integer integer = Integer.valueOf(String.valueOf(s.charAt(k)));
                        integerNums.add(integer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue out;
                    }
                }

                if (type == 3) {
                    /*组三*/
                    boolean zuliu = isZuSan(integerNums);
                    if (zuliu) {
                        objects.add(s);
                    }

                } else if (type == 6) {
                    boolean zuliu = isZuliu(integerNums);
                    if (zuliu) {
                        objects.add(s);
                    }

                }


            }

        }
        removeDuplicate(objects);
        if (betNums.isEmpty()) {
            notFormatmNums = 0;
        } else {
            notFormatmNums = split.length - objects.size();
        }
        String[] res = new String[objects.size()];
        for (int j = 0; j < objects.size(); j++) {
            String s = objects.get(j);
            res[j] = s;
        }


        String[] fixedNums = TicketPlayUtils.getFixedNums(res);
        return fixedNums;
    }

    @Override
    public String getStandSingleBetContent() {
        return getShowBetContent(objects, mDelimiter);
    }
    @Override
    public String formatNums(String betNums) {
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(betNums);
        int bufferLen = stringBuffer.length();
        int i1 = stringBuffer.lastIndexOf(mDelimiter);
        if (i1 < 0) {
            if (bufferLen > offsets) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            int lastIndex = bufferLen - 1;

            boolean b = lastIndex - i1 == offsets + 1;
            if (b) {
                stringBuffer.insert(lastIndex, mDelimiter);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public int getNotCompliantNums() {
        return notFormatmNums;
    }

    private boolean isZuliu(List<Integer> integerNums) {
        /*判断号码之间是否相等*/
        boolean metts = true;
        int size = integerNums.size();
        for (int m = 0; m < integerNums.size(); m++) {
            Integer integer = integerNums.get(m);
            for (int n = m + 1; n < integerNums.size(); n++) {
                Integer integer2 = integerNums.get(n);
                if (integer.equals(integer2)) {
                    metts = false;
                    continue;
                }
            }
        }
        return metts;
    }

    private boolean isZuSan(List<Integer> integerNums) {
        /*判断号码之间是否相等*/
        boolean metts = true;
        int equalCount = 0;
        for (int m = 0; m < integerNums.size(); m++) {
            Integer integer = integerNums.get(m);
            for (int n = m + 1; n < integerNums.size(); n++) {
                Integer integer2 = integerNums.get(n);
                if (integer.equals(integer2)) {
                    equalCount++;
                }
            }
        }
        if (equalCount != 1) {
            metts = false;
        }
        return metts;

    }
}
