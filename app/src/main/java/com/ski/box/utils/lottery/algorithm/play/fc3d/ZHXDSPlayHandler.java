package com.ski.box.utils.lottery.algorithm.play.fc3d;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3D  直选单式
 * 三星直选单式 6010102
 * 前二直选单式_3d 6020102
 * 后二直选单式_3d 6020302
 */
public class ZHXDSPlayHandler extends ITicketSinglePlayHanler {
    private int[] offsets;
    private StringBuffer stringBuffer = new StringBuffer();
    private int notFormalNums;
    private ArrayList<String> objects;

    public ZHXDSPlayHandler(int[] offsets) {
        this.offsets = offsets;
    }





    @Override
    public int calculateBetNum(String betNums) {
        String[] strings = meetRules(betNums);
        return  strings.length;
    }

    private String[] meetRules(String betNums) {
        objects = new ArrayList<>();
        String[] split = betNums.split(mDelimiter);
        List<String> strings = Arrays.asList(split);
        out:
        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == offsets.length) {
                for (int k = 0; k < s.length(); k++) {
                    char c = s.charAt(k);
                    try {
                        Integer integer = Integer.valueOf(String.valueOf(c));
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue out;
                    }
                }

                objects.add(s);
            }
        }
        removeDuplicate(objects);
        String[] res = new String[objects.size()];
        if (betNums.isEmpty()) {
            notFormalNums = 0;
        } else {
            notFormalNums = strings.size() - objects.size();
        }

        notFormalNums = strings.size() - objects.size();
        for (int j = 0; j < objects.size(); j++) {
            String s = objects.get(j);
            res[j] = s;
        }
        return res;
    }

    @Override
    public String getStandSingleBetContent() {
        String content = getShowBetContent(objects, mDelimiter);
        return content;

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
        return notFormalNums;
    }


}
