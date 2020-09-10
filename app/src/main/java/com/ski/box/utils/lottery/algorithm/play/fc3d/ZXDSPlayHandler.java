package com.ski.box.utils.lottery.algorithm.play.fc3d;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.List;

/**
 * 3D
 * 三星混合组选  6010203
 * 前二组选单式_3d 6020202
 * 后二组选单式_3d 6020402
 */
public class ZXDSPlayHandler extends ITicketSinglePlayHanler {
    private int offsets;
    private String betContent;
    private StringBuffer stringBuffer=new StringBuffer();
    private int notFormalNums;
    private ArrayList<String> objects;

    public ZXDSPlayHandler(int zxType) {
        this.offsets = zxType;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] strings = meetRules(betNums);
        return  strings.length;

    }

    private String[] meetRules(String betNums) {
        betContent = "";
        objects = new ArrayList<>();
        String[] split = betNums.split(mDelimiter);
        out:
        for (int j = 0; j < split.length; j++) {
            String num = split[j];
            if (num != null && num.length() == offsets) {

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
                    objects.add(num);

                }
            }

        }

         removeDuplicate(objects);
        if (betNums.isEmpty()) {
            notFormalNums = 0;
        } else {
            notFormalNums = split.length - objects.size();
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
        if (offsets == 3) {
            if (equalCount==offsets) {
                metts = false;
            }
        }else {
            if (equalCount>0) {
                metts = false;
            }
        }

        return metts;

    }

}
