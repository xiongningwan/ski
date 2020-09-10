package com.ski.box.utils.lottery.algorithm.play.pk10;

import com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.TicketPlayUtils;
import com.ski.box.utils.lottery.algorithm.utils.string.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * PK10
 * 猜前二直选复式 18010201
 * 猜前三直选复式 18010301
 * 猜前四直选复式 18010401
 * 猜前五直选复式 18010501
 */
public class ZHXFSPK10PlayHandler implements ITicketPlayHandler {
    private int[] offsets;
    private String[] numArray;
    private List<String> numList;

    public ZHXFSPK10PlayHandler(int[] offsets, String[] numArray) {
        this.offsets = offsets;
        this.numArray = numArray;
        numList = Arrays.asList(this.numArray);
    }

    @Override
    public boolean validateBetNums(String betNums) {
        if (StringUtils.isEmpty(betNums)) {
            return false;
        } else {
            String[] nums = this.getBetNums(betNums);
            if (nums.length != this.offsets.length) {
                return false;
            } else {
                String[] as = nums;
                int j = nums.length;

                for (int i = 0; i < j; ++i) {
                    String num = as[i];
                    String[] subNums = num.split(" ");
                    String[] fixedSubNums = TicketPlayUtils.getFixedAndSortedNums(subNums);
                    if (subNums.length != fixedSubNums.length || fixedSubNums.length > this.numArray.length) {
                        return false;
                    }

                    String[] as1 = subNums;
                    int l = subNums.length;

                    for (int k = 0; k < l; ++k) {
                        String subNum = as1[k];
                        if (!numList.contains(subNum)) {
                            return false;
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

        for (int i = 0; i < nums.length; ++i) {
            res[i] = nums[i].trim();
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
            String[] as3;
            int l1;
            int l;
            String num;
            String[] as7;
            int l3;
            int l2;
            String num1;
            if (nums.length == 2) {
                l1 = (as3 = nums[0].split(" ")).length;

                for (l = 0; l < l1; ++l) {
                    num = as3[l];
                    l3 = (as7 = nums[1].split(" ")).length;

                    for (l2 = 0; l2 < l3; ++l2) {
                        num1 = as7[l2];
                        if (!num.trim().equals(num1.trim())) {
                            ++betNum;
                        }
                    }
                }
            } else {
                String[] as10;
                int j5;
                int k4;
                String num2;
                if (nums.length == 3) {
                    l1 = (as3 = nums[0].split(" ")).length;

                    for (l = 0; l < l1; ++l) {
                        num = as3[l];
                        l3 = (as7 = nums[1].split(" ")).length;

                        for (l2 = 0; l2 < l3; ++l2) {
                            num1 = as7[l2];
                            j5 = (as10 = nums[2].split(" ")).length;

                            for (k4 = 0; k4 < j5; ++k4) {
                                num2 = as10[k4];
                                if (!num.trim().equals(num1.trim()) && !num.trim().equals(num2.trim()) && !num1.trim().equals(num2.trim())) {
                                    ++betNum;
                                }
                            }
                        }
                    }
                } else {
                    String[] as12;
                    int j6;
                    int l5;
                    String num3;
                    if (nums.length == 4) {
                        l1 = (as3 = nums[0].split(" ")).length;

                        for (l = 0; l < l1; ++l) {
                            num = as3[l];
                            l3 = (as7 = nums[1].split(" ")).length;

                            for (l2 = 0; l2 < l3; ++l2) {
                                num1 = as7[l2];
                                j5 = (as10 = nums[2].split(" ")).length;

                                for (k4 = 0; k4 < j5; ++k4) {
                                    num2 = as10[k4];
                                    j6 = (as12 = nums[3].split(" ")).length;

                                    for (l5 = 0; l5 < j6; ++l5) {
                                        num3 = as12[l5];
                                        if (!num.trim().equals(num1.trim()) && !num.trim().equals(num2.trim()) && !num.trim().equals(num3.trim()) && !num1.trim().equals(num2.trim()) && !num1.trim().equals(num3.trim()) && !num2.trim().equals(num3.trim())) {
                                            ++betNum;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (nums.length == 5) {
                        l1 = (as3 = nums[0].split(" ")).length;

                        for (l = 0; l < l1; ++l) {
                            num = as3[l];
                            l3 = (as7 = nums[1].split(" ")).length;

                            for (l2 = 0; l2 < l3; ++l2) {
                                num1 = as7[l2];
                                j5 = (as10 = nums[2].split(" ")).length;

                                for (k4 = 0; k4 < j5; ++k4) {
                                    num2 = as10[k4];
                                    j6 = (as12 = nums[3].split(" ")).length;

                                    for (l5 = 0; l5 < j6; ++l5) {
                                        num3 = as12[l5];
                                        String[] as13;
                                        int l6 = (as13 = nums[4].split(" ")).length;

                                        for (int k6 = 0; k6 < l6; ++k6) {
                                            String num4 = as13[k6];
                                            if (!num.trim().equals(num1.trim()) && !num.trim().equals(num2.trim()) && !num.trim().equals(num3.trim()) && !num.trim().equals(num4.trim()) && !num1.trim().equals(num2.trim()) && !num1.trim().equals(num3.trim()) && !num1.trim().equals(num4.trim()) && !num2.trim().equals(num3.trim()) && !num2.trim().equals(num4.trim()) && !num3.trim().equals(num4.trim())) {
                                                ++betNum;
                                            }
                                        }
                                    }
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