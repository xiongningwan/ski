package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 时时彩
 * 五星直选单式 1010102
 * 前四直选单式 1020102
 * 后四直选单式 1020302
 * 前三直选单式 1030102
 * 中三直选单式 1040102
 * 后三直选单式 1050102
 * 前二直选单式 1060102
 * 后二直选单式 1070102
 */
public class ZHXDSPlayHandler extends ITicketSinglePlayHanler {
    private int[] offsets;
    private StringBuffer stringBuffer = new StringBuffer();
    private int notFormNums;
    private ArrayList<String> objects;

    public ZHXDSPlayHandler(int[] offsets) {
        this.offsets = offsets;
    }





    @Override
    public int calculateBetNum(String betNums) {
        String[] strings = meetRules(betNums);
        return  strings.length;
    }

    private String[]  meetRules(String betNums) {

       objects = new ArrayList<>();
        String[] split = betNums.split(mDelimiter);
        List<String> strings = Arrays.asList(split);
        out:
        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == offsets.length) {
                for (int k = 0; k < s.length(); k++) {
                    try {
                        Integer.valueOf(String.valueOf(s.charAt(k)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        continue out;
                    }
                }

                objects.add(s);
            }

        }

        removeDuplicate(objects);

        String[] res = new String[objects.size()];
        for (int j = 0; j < objects.size(); j++) {
            String s = objects.get(j);
            res[j] = s;
        }
        if (betNums.isEmpty()) {
            notFormNums = 0;
        } else {
            notFormNums = split.length - objects.size();
        }

        return res;

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
            if (bufferLen > offsets.length) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            int lastIndex = bufferLen - 1;

            boolean b = lastIndex - i1 ==offsets.length + 1;
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
