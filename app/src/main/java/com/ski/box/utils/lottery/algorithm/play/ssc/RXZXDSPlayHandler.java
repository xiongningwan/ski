package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.List;

public class RXZXDSPlayHandler extends ITicketSinglePlayHanler {
    private int rxNum;
    private StringBuffer stringBuffer = new StringBuffer();
    private int notFormNums;
    ArrayList<String> object;
    public RXZXDSPlayHandler(int rxNum) {
        this.rxNum = rxNum;
    }


    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            meetRules("");
            return 0;
        }
        List<String> list = meetRules(data[1]);
        String[] offsets = data[0].trim().split(mDelimiter);
        if (offsets.length < rxNum) {
            return 0;
        }

        if (this.rxNum == 2) {
            return list.size() * ERXING_MULTI[offsets.length - this.rxNum];
        } else {
            return this.rxNum == 3 ? list.size() * SANXING_MULTI[offsets.length - this.rxNum] : list.size();
        }

    }

    private List<String> meetRules(String s) {

        String[] split = s.split(mDelimiter);
        object= new ArrayList<>();
        out:
        for (int b = 0; b < split.length; b++) {
            String num = split[b];
            if (num.length() == rxNum) {
                ArrayList<Integer> integers = new ArrayList<>();
                for (int c = 0; c < num.length(); c++) {
                    char c1 = num.charAt(c);
                    try {
                        Integer integer = Integer.valueOf(String.valueOf(c1));
                        integers.add(integer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue out;

                    }
                }

                boolean meets = isMeets(integers);
                if (meets) {
                    object.add(num);
                }

            }

        }
        removeDuplicate(object);
        if (s.isEmpty()) {
            notFormNums = 0;
        } else {
            notFormNums = split.length - object.size();
        }

        return object;
    }

    @Override
    public String getStandSingleBetContent() {
        return getShowBetContent(object, mDelimiter);
    }

    @Override
    public String formatNums(String betNums) {
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(betNums);
        int bufferLen = stringBuffer.length();
        int i1 = stringBuffer.lastIndexOf(mDelimiter);
        if (i1 < 0) {
            if (bufferLen > rxNum) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            int lastIndex = bufferLen - 1;

            boolean b = lastIndex - i1 == rxNum + 1;
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
