package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;

/**
 * PK10
 * 任二直选复式 18040101
 * 任三直选复式 18040201
 */
public class RXFSPK10PlayHandler implements ITicketPlayHandler {
    private int rxNum;
    private static String[] betNumArray = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};

    public RXFSPK10PlayHandler(int rxNum) {
        this.rxNum = rxNum;
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isBlank(betNums)) {
            return false;
        } else {
            String[] nums = betNums.trim().split(",");
            if (10 != nums.length) {
                return false;
            } else {
                String[] var3 = betNumArray;
                int var4 = var3.length;
                Arrays.sort(NUMS_PK10);
                for(int var5 = 0; var5 < var4; ++var5) {
                    String number = var3[var5];
                    if (!StringUtils.equals("-", number)) {
                        String[] bets = number.split(" ");
                        String[] var8 = bets;
                        int var9 = bets.length;

                        for(int var10 = 0; var10 < var9; ++var10) {
                            String bet = var8[var10];
                            if (Arrays.binarySearch(NUMS_PK10, bet.trim()) < 0) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        }
    }

    @Override
    public String[] getBetNums(String betNums) {
        String[] nums = betNums.trim().split(",");
        String[] res = new String[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            if (StringUtils.isBlank(nums[i])) {
                res[i] = "-";
            } else {
                res[i] = StringUtils.trim(nums[i]);
            }
        }

        return res;
    }

    @Override
    public int calculateBetNum(String betNums) {
        String[] nums = this.getBetNums(betNums);
        if (nums != null && nums.length >= 1) {
            int betNum = 0;
            String[][] arr = new String[nums.length][];

            int line;
            for(line = 0; line < nums.length; ++line) {
                arr[line] = nums[line].split(" ");
            }

            line = arr.length;
            int i;
            int j;
            int k;
            int m;
            if (this.rxNum == 2) {
                for(i = 0; i < line; ++i) {
                    for(j = i + 1; j < line; ++j) {
                        if (!"-".equals(arr[i][0]) && !"-".equals(arr[j][0])) {
                            for(k = 0; k < arr[i].length; ++k) {
                                for(m = 0; m < arr[j].length; ++m) {
                                    if (!arr[i][k].equals(arr[j][m])) {
                                        ++betNum;
                                    }
                                }
                            }
                        } else {
                            betNum += 0;
                        }
                    }
                }
            }

            if (this.rxNum == 3) {
                for(i = 0; i < line; ++i) {
                    for(j = i + 1; j < line; ++j) {
                        for(k = j + 1; k < line; ++k) {
                            if (!"-".equals(arr[i][0]) && !"-".equals(arr[j][0]) && !"-".equals(arr[k][0])) {
                                for(m = 0; m < arr[i].length; ++m) {
                                    for(int n = 0; n < arr[j].length; ++n) {
                                        for(int x = 0; x < arr[k].length; ++x) {
                                            if (!arr[i][m].equals(arr[j][n]) && !arr[i][m].equals(arr[k][x]) && !arr[j][n].equals(arr[k][x])) {
                                                ++betNum;
                                            }
                                        }
                                    }
                                }
                            } else {
                                betNum += 0;
                            }
                        }
                    }
                }
            }

            return betNum;
        } else {
            return 0;
        }
    }

 }