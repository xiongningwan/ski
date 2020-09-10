package com.ski.box.utils.lottery.algorithm.play.elevenfive;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 11选五
 * 任选单式一中一  4050201
 * 任选单式二中二  4050202
 * 任选单式三中三   4050203
 * 任选单式四中四  4050202
 * 任选单式五中五  4050205
 * 任选单式六中五  4050206
 * 任选单式七中五  4050207
 * 任选单式八中五   4050208
 */
public class RXDS115PlayHandler extends ITicketSinglePlayHanler {
    private int dsNum;
    private StringBuffer stringBuffer = new StringBuffer();
    private int notFormalNums;
    ArrayList<String> objects;
    public RXDS115PlayHandler(int dsNum) {
        this.dsNum = dsNum;
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

        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == dsNum * 2) {
                List<Integer> integerNums = new ArrayList<>();
                int l = 0;
                /*判读每个号码是否符合标准*/
                boolean meetFirst = true;
                for (int k = 0; k < dsNum; k++) {
                    try {
                        Integer num = Integer.valueOf(s.substring(l, l + 2));
                        if (num > 0 && num < 12) {
                            integerNums.add(num);
                        } else {
                            meetFirst = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        meetFirst = false;
                        break;
                    }
                    l = l + 2;
                }

                if (meetFirst) {
                    boolean meets = isMeets(integerNums);
                    if (meets) {
                        objects.add(s);
                    }
                }
            }
        }
        removeDuplicate(objects);
        String[] res = new String[objects.size()];
        for (int j = 0; j < objects.size(); j++) {
            String s = objects.get(j);
            res[j] = s;
        }
        if (betNums.isEmpty()) {
            notFormalNums = 0;
        } else {
            notFormalNums = strings.size() - objects.size();
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
            if (bufferLen > dsNum * 2) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            boolean b = bufferLen - i1 == (dsNum + 1) * 2;
            if (b) {
                stringBuffer.insert(i1 + dsNum * 2 + 1, mDelimiter);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public int getNotCompliantNums() {
        return notFormalNums;
    }

    private boolean isMeets(List<Integer> integerNums) {
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
        if (equalCount > 0) {
            metts = false;
        }
        return metts;

    }
}
