package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * 时时彩
 * 任二组选包胆 1120204
 */
public class RX2ZXBDPlayHandler implements ITicketPlayHandler {

    public RX2ZXBDPlayHandler() {

    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] data = betNums.split("\\|");
            if (data.length != 2) {
                return false;
            } else {
                String[] offsets = data[0].trim().split(",");
                String[] fixedOffsets = TicketPlayUtils.getFixedAndSortedNums(offsets);
                if (offsets.length == fixedOffsets.length && offsets.length >= 2) {
                    StringBuilder ofsb = new StringBuilder();
                    String[] var6 = fixedOffsets;
                    int var7 = fixedOffsets.length;

                    for (int var8 = 0; var8 < var7; ++var8) {
                        String offset = var6[var8];
                        if (offset.length() != 1) {
                            return false;
                        }

                        if (!offset.matches("\\d{1}")) {
                            return false;
                        }

                        if (Arrays.binarySearch(OFFSETS_WUXIN, Integer.valueOf(offset)) < 0) {
                            return false;
                        }

                        ofsb.append(offset).append(",");
                    }

                    if (!data[0].trim().equals(ofsb.substring(0, ofsb.length() - 1))) {
                        return false;
                    } else {
                        return data[1].trim().matches("\\d{1}");
                    }
                } else {
                    return false;
                }
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] data = betNums.split("\\|");
        return data.length != 2 ? null : data[1].trim().split("");
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] data = betNums.split("\\|");
        if (data.length != 2) {
            return 0;
        } else {
            String[] offsets = data[0].trim().split(",");
            int len = offsets.length;
            int betNum = len * (len - 1) / 2;
            return betNum * 9;
        }
    }
}

