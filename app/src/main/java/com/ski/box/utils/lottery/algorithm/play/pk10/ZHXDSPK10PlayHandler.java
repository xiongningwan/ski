package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * pk10
 * 猜前二直选单式 18010202
 * 猜前三直选单式 18010302
 * 猜前四直选单式  18010402
 * 猜前五直选单式 18010502
 */
public class ZHXDSPK10PlayHandler extends ITicketSinglePlayHanler {
    private int[] offsets;
    private StringBuffer stringBuffer = new StringBuffer();
    private int notFormNums;
    private ArrayList<String> objects;

    public ZHXDSPK10PlayHandler( int[] offsets) {
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
            if (s != null && s.length() == offsets.length*2) {
                List<Integer> integerNums = new ArrayList<>();
                int l = 0;
                /*判读每个号码是否符合标准*/
                for (int k = 0; k < offsets.length; k++) {
                    Integer num = null;
                    try {
                        num = Integer.valueOf(s.substring(l, l+2));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        continue out;

                    }

                    if (num > 0 && num < 11) {
                        integerNums.add(num);
                    } else {
                        break;
                    }
                    l=l + 2;
                }

                /*判断号码之间是否相等*/
                boolean metts = true;
                int size = integerNums.size();
                if (size == offsets.length) {
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
                }else {
                    metts = false;
                }
                if (metts) {
                    objects.add(s);

                }


            }

        }



        removeDuplicate(objects);
        boolean empty = betNums.isEmpty();
        if (empty) {
            notFormNums = 0;
        } else {
            notFormNums = strings.size() - objects.size();
        }

        String[] res = new String[objects.size()];
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
            if (bufferLen > offsets.length * 2) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            boolean b = bufferLen - i1 ==(offsets.length+1)*2 ;
            if (b) {
                stringBuffer.insert(i1+ offsets.length * 2+1, mDelimiter);
            }
        }
        return stringBuffer.toString();
    }



    @Override
    public int getNotCompliantNums() {
        return notFormNums;
    }
}
