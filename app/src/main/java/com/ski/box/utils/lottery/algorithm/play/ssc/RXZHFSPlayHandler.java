package com.ski.box.utils.lottery.algorithm.play.ssc;

import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;
import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;

/**
 * 时时彩
 * 任二直选复式  1120101
 * 任三直选复式  1140101
 * 任四直选复式  1150101
 */
public class RXZHFSPlayHandler implements ITicketPlayHandler {
    private int rxNum;

    public RXZHFSPlayHandler(int rxNum) {
        this.rxNum = rxNum;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            int valLen = 0;
            String[] nums = betNums.trim().split(",");
            String[] var4 = nums;
            int var5 = nums.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                String num = var4[var6];
                if (!"-".equals(num)) {
                    String[] onum = num.split(" ");
                    String[] fixedOnum = TicketPlayUtils.getFixedAndSortedNums(onum);
                    if (onum.length != fixedOnum.length) {
                        return false;
                    }

                    String[] var10 = onum;
                    int var11 = onum.length;

                    for (int var12 = 0; var12 < var11; ++var12) {
                        String o = var10[var12];
                        if (!o.matches("\\d{1}")) {
                            return false;
                        }
                    }

                    ++valLen;
                }
            }

            return valLen >= this.rxNum && valLen <= 5;
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].replaceAll(" ", "");
        }

        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums == null) {
            return 0;
        } else {
            int betNum = 0;
            int i;
            int j;
            int k;
            switch (this.rxNum) {
                case 1:
                default:
                    break;
                case 2:
                    for (i = 0; i < nums.length; ++i) {
                        for (j = i + 1; j < nums.length; ++j) {
                            if (!"-".equals(nums[i]) && !"-".equals(nums[j])) {
                                betNum += nums[i].length() * nums[j].length();
                            } else {
                                betNum += 0;
                            }
                        }
                    }

                    return betNum;
                case 3:
                    for (i = 0; i < nums.length; ++i) {
                        for (j = i + 1; j < nums.length; ++j) {
                            for (k = j + 1; k < nums.length; ++k) {
                                if (!"-".equals(nums[i]) && !"-".equals(nums[j]) && !"-".equals(nums[k])) {
                                    betNum += nums[i].length() * nums[j].length() * nums[k].length();
                                } else {
                                    betNum += 0;
                                }
                            }
                        }
                    }

                    return betNum;
                case 4:
                    for (i = 0; i < nums.length; ++i) {
                        for (j = i + 1; j < nums.length; ++j) {
                            for (k = j + 1; k < nums.length; ++k) {
                                for (int l = k + 1; l < nums.length; ++l) {
                                    if (!"-".equals(nums[i]) && !"-".equals(nums[j]) && !"-".equals(nums[k]) && !"-".equals(nums[l])) {
                                        betNum += nums[i].length() * nums[j].length() * nums[k].length() * nums[l].length();
                                    } else {
                                        betNum += 0;
                                    }
                                }
                            }
                        }
                    }
            }

            return betNum;
        }
    }
}