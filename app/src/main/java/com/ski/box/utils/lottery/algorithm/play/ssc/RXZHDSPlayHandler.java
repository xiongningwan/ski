package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketSinglePlayHanler;

import java.util.ArrayList;
import java.util.List;

/**
 * 时时彩
 * 任二直选单式   1120102
 * 任三直选单式   1140102
 * 任四直选单式   1150102
 */
public class RXZHDSPlayHandler extends ITicketSinglePlayHanler {
    private int rxNum;
    private StringBuffer stringBuffer=new StringBuffer();
    private int notFormNums;
    ArrayList<String> objects;
    public RXZHDSPlayHandler( int rxNum) {
        this.rxNum = rxNum;
    }




    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            meetRules("");
            return 0;
        }
        List<String> objects = meetRules(data[1]);
        String[] offsets = data[0].trim().split(mDelimiter);
          if (offsets.length<rxNum){
              return 0;
          }
            if (this.rxNum == 3) {
                return objects.size() * SANXING_MULTI[offsets.length - this.rxNum];
            } else if (this.rxNum == 2) {
                return objects.size() * ERXING_MULTI[offsets.length - this.rxNum];
            } else {
                return this.rxNum == 4 ? objects.size() * SIXING_MULTI[offsets.length - this.rxNum] : objects.size();
            }

    }

    private List<String> meetRules(String splitNum) {
        String[] split = splitNum.trim().split(mDelimiter);
       objects = new ArrayList<>();
        for (int b = 0; b < split.length; b++) {
            String num = split[b];
            if (num.length() == rxNum) {
                ArrayList<Integer> integers = new ArrayList<>();
                boolean isMeet=true;
                for (int c = 0; c < num.length(); c++) {
                    try {
                        Integer integer = Integer.valueOf(String.valueOf(num.charAt(c)));
                        integers.add(integer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        isMeet = false;
                        break;

                    }
                }
                if (isMeet) {
                    objects.add(num);
                }


            }

        }
        removeDuplicate(objects);
        if (splitNum.isEmpty()) {
            notFormNums = 0;
        } else {
            notFormNums = split.length - objects.size();
        }

        return objects;
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
            if (bufferLen > rxNum) {
                stringBuffer.insert(bufferLen - 1, mDelimiter);
            }
        } else {
            int lastIndex = bufferLen - 1;

            boolean b = lastIndex - i1 ==rxNum + 1;
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
