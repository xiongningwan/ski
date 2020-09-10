package com.ski.box.utils.lottery.algorithm.play.ssc;


import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 时时彩
 * 前三混合组选 1030203
 * 后三混合组选 1050203
 * 前二组选单式 1060202
 * 后二组选单式 1070202
 */
public class ZXDSPlayHandler extends ITicketSinglePlayHanler {
    /**
     * offsets==3 前三 中三 后三
     * offsets==2 前二 后二
     *
     */
    private int offsets;
    private StringBuffer stringBuffer=new StringBuffer();
    private int notFormNums;
    ArrayList<String> objects;
    public ZXDSPlayHandler( int zxType) {
        this.offsets = zxType;

    }





    @Override
    public int calculateBetNum(String betNums) {
        String[] strings = meetRules(betNums);
        return strings.length;
    }

    private String[] meetRules(String betNums) {
     objects = new ArrayList<>();
        String[] split = betNums.split(mDelimiter);
        List<String> strings = Arrays.asList(split);
        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == offsets) {
                List<Integer> integerNums = new ArrayList<>();
                for (int k = 0; k < s.length(); k++) {
                    char c = s.charAt(k);
                    integerNums.add(Integer.valueOf(c));
                }

                if (offsets == 2) {
                    /*前后二 组选单式*/
                    boolean zuliu = qianerHouer(integerNums);
                    if (zuliu) {
                        objects.add(s);
                    }

                } else  if (offsets == 3){
                    boolean zuliu = qianZhongHouSan(integerNums);
                    if (zuliu) {
                        objects.add(s);
                    }
                }
            }
        }

        removeDuplicate(objects);
        if (betNums.isEmpty()) {
            notFormNums = 0;
        } else {
            notFormNums = split.length - objects.size();
        }

        String[] res = new String[objects.size()];
        for (int j = 0; j < objects.size(); j++) {
            String s = objects.get(j);
            res[j] = s;
        }

        return res;
    }

    private boolean qianerHouer(List<Integer> integerNums) {
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
        if (equalCount>0) {
            metts = false;
        }
        return metts;

    }

    private boolean qianZhongHouSan(List<Integer> integerNums) {
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
        if (equalCount ==3) {
            metts = false;
        }

        return metts;

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

            boolean b = lastIndex - i1 ==offsets + 1;
            if (b) {
                stringBuffer.insert(lastIndex, mDelimiter);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public int getNotCompliantNums() {
        return notFormNums;
    }
}
