package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PK10
 * 任二直选单式 18040102
 * 任三直选单式 18040202
 */
public class RXDSPK10PlayHandler extends ITicketSinglePlayHanler {
    private int rxNum;
    private StringBuffer stringBuffer = new StringBuffer();
    private String[] tags;
    List<String> objects;
    private int notFormalNums;

    public RXDSPK10PlayHandler( int rxNum) {
        this.rxNum = rxNum;
    }




    @Override
    public int calculateBetNum(String betNums) {
        String[] splitNum = betNums.split("\\|");
        if (splitNum.length != 2) {
            meetRules("");
            return 0;
        }

        meetRules(splitNum[1]);
        String s = splitNum[0];
        if (s.isEmpty()) {
            return 0;
        }
        tags= s.split(mDelimiter);
        AtomicInteger counter = new AtomicInteger(0);
        for (String num : objects) {
            if (this.rxNum == tags.length) {
                counter.getAndIncrement();
            } else {
                counter.set(counter.get() + this.combin(tags.length, this.rxNum));
            }
        }

        return counter.get();
    }

    private void meetRules(String splitNum) {
        objects = new ArrayList<>();
        String[] split = splitNum.split(mDelimiter);
        List<String> strings = Arrays.asList(split);
        out:
        for (int j = 0; j < strings.size(); j++) {
            String s = strings.get(j);
            if (s != null && s.length() == rxNum*2) {
                List<Integer> integerNums = new ArrayList<>();
                int l = 0;
                /*判读每个号码是否符合标准*/
                for (int k = 0; k < rxNum; k++) {
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
                if (size == rxNum) {
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
        if (splitNum.isEmpty()) {
            notFormalNums = 0;
        } else {
            notFormalNums = strings.size() - objects.size();
        }


    }

    private int combin(int m, int n) {
        int molecule = m;
        int denominator = n;
        if (n == 2) {
            return m * (m - 1) / n;
        } else if (n != 3) {
            return 0;
        } else {
            for(int i = 1; i < n; ++i) {
                molecule *= m - i;
                denominator *= n - i;
            }

            return molecule / denominator;
        }
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
            if (bufferLen > rxNum * 2) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            boolean b = bufferLen - i1 ==(rxNum+1)*2 ;
            if (b) {
                stringBuffer.insert(i1+ rxNum* 2+1, mDelimiter);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public int getNotCompliantNums() {
        return notFormalNums;
    }
}
