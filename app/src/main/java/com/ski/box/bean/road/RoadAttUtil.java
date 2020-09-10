package com.ski.box.bean.road;


import com.ski.box.bean.lottery.LotteryUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 属性
 */
public class RoadAttUtil {
    public static final String[] jiaXiao = new String[]{"牛", "马", "羊", "猪", "狗", "鸡"};
    public static final String[] yeXiao = new String[]{"鼠", "虎", "龙", "蛇", "兔", "猴"};
    public static final String[] tianXiao = new String[]{"牛", "兔", "龙", "马", "猴", "猪"};
    public static final String[] qianXiao = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇"};

    /*只有大小*/
    private static Object[] bigOrSmall(String number, int devision) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            Integer num = Integer.valueOf(number);
            if (num >= devision) {
                name = "大";
                bp = RoadBean.Con.BANKER;
            } else {
                name = "小";
                bp = RoadBean.Con.PLAYER;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }

    /*只有大小*/
    private static Object[] bigOrSmallHe(String number, int devision) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            Integer num = Integer.valueOf(number);
            if (num > devision) {
                name = "大";
                bp = RoadBean.Con.BANKER;
            } else if(num < devision) {
                name = "小";
                bp = RoadBean.Con.PLAYER;
            }else{
                name = "和";
                bp = RoadBean.Con.TIE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }

    private static Object[] doubleSingleHe(String n,int devision) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int nInt = Integer.parseInt(n);
            if(nInt!=devision) {
                if (1 == nInt % 2) {
                    name = "单";
                    bp = RoadBean.Con.BANKER;
                } else {
                    name = "双";
                    bp = RoadBean.Con.PLAYER;
                }
                attArr[0] = name;
                attArr[1] = bp;
            }else{
                attArr[0] = "和";
                attArr[1] = RoadBean.Con.TIE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }



    private static Object[] road_arr_11x5_qiu_daXiao(String n) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            int num = Integer.parseInt(n);
            if (num == 11) {
                name = "和";
                bp = RoadBean.Con.TIE;
            } else if (num >= 6) {
                name = "大";
                bp = RoadBean.Con.BANKER;
            } else {
                name = "小";
                bp = RoadBean.Con.PLAYER;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }


    /*判斷单双*/
    private static Object[] doubleSingle(String n) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int nInt = Integer.parseInt(n);
            if (1 == nInt % 2) {
                name = "单";
                bp = RoadBean.Con.BANKER;
            } else {
                name = "双";
                bp = RoadBean.Con.PLAYER;
            }
            attArr[0] = name;
            attArr[1] = bp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }

    private static Object[] doubleSingle(String n,int value) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int nInt = Integer.parseInt(n);
            if (value== nInt){
                name = "和";
                bp = RoadBean.Con.TIE;
            }else{
                if (1 == nInt % 2) {
                    name = "单";
                    bp = RoadBean.Con.BANKER;
                } else {
                    name = "双";
                    bp = RoadBean.Con.PLAYER;
                }
            }
            attArr[0] = name;
            attArr[1] = bp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }



    /*判斷质和*/
    private static Object[] zhiHe(String n) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int i = Integer.parseInt(n);
            name = isPrime(i);
            bp = "质".equals(name) ? RoadBean.Con.BANKER : RoadBean.Con.PLAYER;
            attArr[0] = name;
            attArr[1] = bp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }

    /**
     * 判断质数和数
     */
    public static String isPrime(int n) {
        if (n == 1) {
            return "质";
        }
        if (n == 0) {
            return "合";
        }
        boolean flag = true;
        for (int i = 2; i <= n - 1; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return "质";
        } else {
            return "合";
        }
    }

    /*只有龙虎*/
    private static Object[] longHuHe(String first, String second) {

        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            Integer integer = Integer.valueOf(first);
            Integer integer1 = Integer.valueOf(second);
            if (integer > integer1) {
                name = "龙";
                bp = RoadBean.Con.BANKER;
            } else if (integer < integer1) {
                name = "虎";
                bp = RoadBean.Con.PLAYER;
            } else {
                name = "和";
                bp = RoadBean.Con.TIE;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }

    private static Object[] qianHouDuo(String[] nums) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        int qinduoCount = 0;
        int houDuoConut = 0;
        try {
            for (int x = 0; x < nums.length; x++) {
                String num = nums[x];
                Integer integer = Integer.valueOf(num);
                if (integer >= 41) {
                    houDuoConut = houDuoConut + 1;
                } else {
                    qinduoCount = qinduoCount + 1;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (qinduoCount > houDuoConut) {
            name = "前";
            bp = RoadBean.Con.BANKER;
        } else if (qinduoCount < houDuoConut) {
            name = "后";
            bp = RoadBean.Con.PLAYER;
        } else {
            name = "和";
            bp = RoadBean.Con.TIE;
        }

        attArr[0] = name;
        attArr[1] = bp;

        return attArr;
    }

    private static Object[] danShuangDuo(String[] nums) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        int singleCount = 0;
        int doubleConut = 0;
        for (int x = 0; x < nums.length; x++) {
            String num = nums[x];
            Integer integer = Integer.valueOf(num);
            boolean b = integer % 2 == 1 ? true : false;
            if (b) {
                singleCount = singleCount + 1;
            } else {
                doubleConut = doubleConut + 1;
            }


        }
        if (singleCount > doubleConut) {
            name = "单";
            bp = RoadBean.Con.BANKER;
        } else if (singleCount < doubleConut) {
            name = "双";
            bp = RoadBean.Con.PLAYER;
        } else {
            name = "和";
            bp = RoadBean.Con.TIE;
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }
    /*只用于六合彩 大 小 和*/
    private static Object[] lhc_bigSmall(String number, int devision, int he) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            Integer num = Integer.valueOf(number);
            if (num == he) {
                name = "和";
                bp = RoadBean.Con.TIE;
            } else if (num >= devision) {
                name = "大";
                bp = RoadBean.Con.BANKER;
            } else {
                name = "小";
                bp = RoadBean.Con.PLAYER;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }
    /*只用于 六合彩 单双 和*/
    private static Object[] lhc_doubleSingle(String n,int he) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int nInt = Integer.parseInt(n);
            if (nInt == he) {
                name = "和";
                bp = RoadBean.Con.TIE;
            }
            else if (1 == nInt % 2) {
                name = "单";
                bp = RoadBean.Con.BANKER;
            } else {
                name = "双";
                bp = RoadBean.Con.PLAYER;
            }
            attArr[0] = name;
            attArr[1] = bp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }
    /*只用于 六合彩 合大小*/
    private static Object[] lhc_he_bigOrSmall(String number,int devision,int he) {
        String name;
        int bp;
        Object[] attArr = {"", 0};
        try {
            int integer = Integer.valueOf(number);
            if (integer == he) {
                name = "和";
                bp = RoadBean.Con.TIE;
            } else {
                int length = number.length();
                int heNums = 0;
                if (length == 2) {
                    int intShi = Integer.valueOf(number.substring(0,1));
                    int intGe = Integer.valueOf(number.substring(1,2));
                    heNums = intShi + intGe;
                }else{
                    heNums=Integer.valueOf(number);
                }
                if (heNums >= devision) {
                    name = "大";
                    bp = RoadBean.Con.BANKER;
                } else {
                    name = "小";
                    bp = RoadBean.Con.PLAYER;
                }
            }

            attArr[0] = name;
            attArr[1] = bp;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attArr;
    }

    private static Object[] lhc_he_doubleSigle(String number, int he) {
        String name = null;
        int bp = 0;
        Object[] attArr = {"", 0};
        try {
            Integer integer = Integer.valueOf(number);
            if (integer == he) {
                name = "和";
                bp = RoadBean.Con.TIE;
            } else {
                int shiwei = (int) number.charAt(0);
                int gewei = (int) number.charAt(1);
                int heshu = shiwei + gewei;
                if (1 == heshu % 2) {
                    name = "单";
                    bp = RoadBean.Con.BANKER;
                } else {
                    name = "双";
                    bp = RoadBean.Con.PLAYER;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        attArr[0] = name;
        attArr[1] = bp;
        return attArr;
    }






    public static Object[] road_arr_11x5(String[] arr, int total, String titleKey) {
        String name = "";
        int bp = 0;
        Object[] attArr = {"", 0};
        switch (titleKey) {
            case "总和大小":
                if (total > 30) {
                    name = "大";
                    bp = RoadBean.Con.BANKER;
                } else if (total < 30) {
                    name = "小";
                    bp = RoadBean.Con.PLAYER;
                } else {
                    name = "和";
                    bp = RoadBean.Con.TIE;
                }
                attArr[0] = name;
                attArr[1] = bp;
                break;
            case "总和单双":
                if (1 == total % 2) {
                    name = "单";
                    bp = RoadBean.Con.BANKER;
                } else {
                    name = "双";
                    bp = RoadBean.Con.PLAYER;
                }
                attArr[0] = name;
                attArr[1] = bp;
                break;
            case "总和龙虎":
                try {
                    if (Integer.parseInt(arr[0]) > Integer.parseInt(arr[4])) {
                        name = "龙";
                        bp = RoadBean.Con.BANKER;
                    } else {
                        name = "虎";
                        bp = RoadBean.Con.PLAYER;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attArr[0] = name;
                attArr[1] = bp;
                break;

            case "第一球大小":
                attArr = road_arr_11x5_qiu_daXiao(arr[0]);
                break;
            case "第二球大小":
                attArr = road_arr_11x5_qiu_daXiao(arr[1]);
                break;
            case "第三球大小":
                attArr = road_arr_11x5_qiu_daXiao(arr[2]);
                break;
            case "第四球大小":
                attArr = road_arr_11x5_qiu_daXiao(arr[3]);
                break;
            case "第五球大小":
                attArr = road_arr_11x5_qiu_daXiao(arr[4]);
                break;
            case "第一球单双":
                attArr = doubleSingle(arr[0],11);
                break;
            case "第二球单双":
                attArr = doubleSingle(arr[1],11);
                break;
            case "第三球单双":
                attArr = doubleSingle(arr[2],11);
                break;
            case "第四球单双":
                attArr = doubleSingle(arr[3],11);
                break;
            case "第五球单双":
                attArr = doubleSingle(arr[4],11);
                break;
        }

        return attArr;
    }

    private static Object[] lhc_tiandi_qianhou_jiaye(String xiao) {
        int bp = 0;
        Object[] attArr = {"", 0};
        if ("前".equals(xiao) || "天".equals(xiao) || "家".equals(xiao)) {
            bp = RoadBean.Con.BANKER;
        } else if ("和".equals(xiao)) {
            bp = RoadBean.Con.TIE;
        } else {
            bp = RoadBean.Con.PLAYER;
        }
        attArr[0] = xiao;
        attArr[1] = bp;
        return  attArr;
    }

    public static Object[] road_arr_pk10(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};
        String name = "";
        int bp = 0;
        switch (titleKey) {
            case "冠亚和大小":
                String i = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
                attArr = bigOrSmall(i, 12);
                break;
            case "冠亚和单双":
                String s = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
                attArr = doubleSingle(s);
                break;

            case "冠军大小":
                attArr = bigOrSmall(arr[0], 6);
                break;
            case "冠军单双":
                attArr = doubleSingle(arr[0]);
                break;
            case "冠军龙虎":
                attArr = longHuHe(arr[0], arr[9]);
                break;
            case "亚军大小":
                attArr = bigOrSmall(arr[1], 6);
                break;
            case "亚军单双":
                attArr = doubleSingle(arr[1]);
                break;
            case "亚军龙虎":
                attArr = longHuHe(arr[1], arr[8]);
                break;

            case "第三名大小":
                attArr = bigOrSmall(arr[2], 6);
                break;
            case "第三名单双":
                attArr = doubleSingle(arr[2]);
                break;
            case "第三名龙虎":

                attArr = longHuHe(arr[2], arr[7]);
                break;

            case "第四名大小":
                attArr = bigOrSmall(arr[3], 6);
                break;
            case "第四名单双":
                attArr = doubleSingle(arr[3]);
                break;
            case "第四名龙虎":
                attArr = longHuHe(arr[3], arr[6]);

                break;

            case "第五名大小":
                attArr = bigOrSmall(arr[4], 6);
                break;
            case "第五名单双":
                attArr = doubleSingle(arr[4]);
                break;
            case "第五名龙虎":
                attArr = longHuHe(arr[4], arr[5]);
                break;

            case "第六名大小":
                attArr = bigOrSmall(arr[5], 6);
                break;
            case "第六名单双":
                attArr = doubleSingle(arr[5]);
                break;
            case "第七名大小":
                attArr = bigOrSmall(arr[6], 6);
                break;
            case "第七名单双":
                attArr = doubleSingle(arr[6]);
                break;
            case "第八名大小":
                attArr = bigOrSmall(arr[7], 6);
                break;
            case "第八名单双":
                attArr = doubleSingle(arr[7]);
                break;
            case "第九名大小":
                attArr = bigOrSmall(arr[8], 6);
                break;
            case "第九名单双":
                attArr = doubleSingle(arr[8]);
                break;
            case "第十名大小":
                attArr = bigOrSmall(arr[9], 6);
                break;
            case "第十名单双":
                attArr = doubleSingle(arr[9]);
                break;

            default:
                break;
        }


        return attArr;
    }

    public static Object[] road_arr_ssc(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};

        switch (titleKey) {
            case "总和大小":
                attArr = bigOrSmall(total + "", 23);
                break;
            case "总和单双":
                attArr = doubleSingle(total + "");

                break;
            case "总和龙虎":
                attArr = longHuHe(arr[0], arr[4]);
                break;
            case "第一球大小":
                attArr = bigOrSmall(arr[0], 5);
                break;
            case "第一球单双":
                attArr = doubleSingle(arr[0]);
                break;
            case "第一球质合":
                attArr = zhiHe(arr[0]);
                break;
            case "第二球大小":
                attArr = bigOrSmall(arr[1], 5);
                break;
            case "第二球单双":
                attArr = doubleSingle(arr[1]);
                break;
            case "第二球质合":
                attArr = zhiHe(arr[1]);
                break;
            case "第三球大小":
                attArr = bigOrSmall(arr[2], 5);
                break;
            case "第三球单双":
                attArr = doubleSingle(arr[2]);
                break;
            case "第三球质合":
                attArr = zhiHe(arr[2]);
                break;
            case "第四球大小":
                attArr = bigOrSmall(arr[3], 5);
                break;
            case "第四球单双":
                attArr = doubleSingle(arr[3]);
                break;
            case "第四球质合":
                attArr = zhiHe(arr[3]);
                break;
            case "第五球大小":
                attArr = bigOrSmall(arr[4], 5);
                break;
            case "第五球单双":
                attArr = doubleSingle(arr[4]);
                break;
            case "第五球质合":
                attArr = zhiHe(arr[4]);
                break;
            default:
                break;

        }

        return attArr;
    }


    public static Object[] road_arr_k3(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};
        boolean b = arr[0].equals(arr[1]) ? true : false;
        boolean b1 = arr[1].equals(arr[2]) ? true : false;
        if (b && b1) {
            attArr = new Object[]{"围", RoadBean.Con.WEI};
        } else {
            attArr = bigOrSmall(total + "", 11);

        }


        return attArr;
    }

    public static Object[] road_arr_3d(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};
        switch (titleKey) {
            case "百位大小":
                attArr = bigOrSmall(arr[0], 5);
                break;
            case "百位单双":
                attArr = doubleSingle(arr[0]);
                break;
            case "百位质合":
                attArr = zhiHe(arr[0]);
                break;
            case "十位大小":
                attArr = bigOrSmall(arr[1], 5);
                break;
            case "十位单双":
                attArr = doubleSingle(arr[1]);
                break;
            case "十位质合":
                attArr = zhiHe(arr[1]);
                break;
            case "个位大小":
                attArr = bigOrSmall(arr[2], 5);
                break;
            case "个位单双":
                attArr = doubleSingle(arr[2]);
                break;
            case "个位质合":
                attArr = zhiHe(arr[2]);
                break;
            case "百十和数单双":
                attArr = doubleSingle(String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1])));
                break;
            case "百十和数尾大小":
                String baishi = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
                attArr = bigOrSmall(baishi.substring(baishi.length() - 1), 5);
                break;
            case "百十和数尾质合":
                String baishi_1 = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]));
                attArr = zhiHe(baishi_1.substring(baishi_1.length() - 1));
                break;

            case "百个和数单双":
                attArr = doubleSingle(String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[2])));
                break;
            case "百个和数尾大小":
                String baige = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[2]));
                attArr = bigOrSmall(baige.substring(baige.length() - 1), 5);
                break;
            case "百个和数尾质合":
                String baige_1 = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[2]));
                attArr = zhiHe(baige_1.substring(baige_1.length() - 1));
                break;

            case "十个和数单双":
                attArr = doubleSingle(String.valueOf(Integer.valueOf(arr[1]) + Integer.valueOf(arr[2])));
                break;
            case "十个和数尾大小":
                String shige = String.valueOf(Integer.valueOf(arr[1]) + Integer.valueOf(arr[2]));
                attArr = bigOrSmall(shige.substring(shige.length() - 1), 5);
                break;
            case "十个和数尾质合":
                String shige_1 = String.valueOf(Integer.valueOf(arr[1]) + Integer.valueOf(arr[2]));
                attArr = zhiHe(shige_1.substring(shige_1.length() - 1));
                break;

            case "百十个和数大小":
                attArr = bigOrSmall(String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]) + Integer.valueOf(arr[2])), 14);
                break;
            case "百十个和数单双":
                attArr = doubleSingle(String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]) + Integer.valueOf(arr[2])));
                break;
            case "百十个和数尾大小":
                String baishige = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]) + Integer.valueOf(arr[2]));
                attArr = bigOrSmall(baishige.substring(baishige.length() - 1), 5);
                break;
            case "百十个和数尾质合":
                String baishige_1 = String.valueOf(Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]) + Integer.valueOf(arr[2]));
                attArr = zhiHe(baishige_1.substring(baishige_1.length() - 1));
                break;

            default:
                break;
        }


        return attArr;
    }

    public static Object[] road_arr_klc(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};
        switch (titleKey) {
            case "大小":
                attArr = bigOrSmallHe(total + "", 810);
                break;
            case "单双":
                attArr = doubleSingleHe(total + "",810);
                break;
            case "前后多":
                attArr = qianHouDuo(arr);
                break;
            case "单双多":
                attArr = danShuangDuo(arr);
                break;
        }
        return attArr;
    }


    public static Object[] road_arr_lhc(String[] arr, int total, String titleKey) {
        Object[] attArr = {"", 0};

        switch (titleKey) {
            case "特码大小":
                attArr = lhc_bigSmall(arr[6], 25,49);
                break;
            case "特码单双":
                attArr = lhc_doubleSingle(arr[6],49);
                break;
            case "特码合大小":
                attArr = lhc_he_bigOrSmall(arr[6], 7, 49);
                break;
            case "特码合单双":
                attArr = lhc_he_doubleSigle(arr[6], 49);
                break;
            case "特码天地肖":
                if (Integer.parseInt(arr[6])==49) {
                    attArr =lhc_tiandi_qianhou_jiaye("和");
                }else {
                    String lhcsx = LotteryUtil.getLHCSX(Integer.parseInt(arr[6]));
                    for (int x = 0; x < tianXiao.length; x++) {
                        boolean equals = tianXiao[x].equals(lhcsx);
                        if (equals) {
                            lhcsx = "true";
                            break;
                        }
                    }
                    attArr = "true".equals(lhcsx) ? lhc_tiandi_qianhou_jiaye("天") : lhc_tiandi_qianhou_jiaye("地");
                }
                break;
            case "特码前后肖":
                if (Integer.parseInt(arr[6])==49) {
                    attArr =lhc_tiandi_qianhou_jiaye("和");
                }else {
                    String qinahou = LotteryUtil.getLHCSX(Integer.parseInt(arr[6]));
                    for (int x = 0; x < qianXiao.length; x++) {
                        boolean equals = qianXiao[x].equals(qinahou);
                        if (equals) {
                            qinahou = "true";
                            break;
                        }
                    }
                    attArr = "true".equals(qinahou) ? lhc_tiandi_qianhou_jiaye("前") : lhc_tiandi_qianhou_jiaye("后");
                }
                break;
            case "特码家野肖":
                if (Integer.parseInt(arr[6])==49) {
                    attArr =lhc_tiandi_qianhou_jiaye("和");
                }else{
                    String jiaye = LotteryUtil.getLHCSX(Integer.parseInt(arr[6]));
                    for (int x = 0; x < jiaXiao.length; x++) {
                        boolean equals = jiaXiao[x].equals(jiaye);
                        if (equals) {
                            jiaye = "true";
                            break;
                        }
                    }
                    attArr = "true".equals(jiaye) ? lhc_tiandi_qianhou_jiaye("家") : lhc_tiandi_qianhou_jiaye("野");
                }
                break;
            case "特码尾大小":
                String s1 = String.valueOf(arr[6].charAt(1));
                attArr = lhc_bigSmall(s1, 5, 49);
                break;

            case "正码一大小":
                attArr = lhc_bigSmall(arr[0], 25, 49);
                break;
            case "正码一单双":
                attArr= lhc_doubleSingle(arr[0], 49);
                break;
            case "正码一合大小":
                attArr = lhc_he_bigOrSmall(arr[0], 7, 49);
                break;
            case "正码一合单双":
                attArr = lhc_he_doubleSigle(arr[0], 49);
                break;
            case "正码一尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[0].charAt(1)), 5, 49);
                break;



            case "正码二大小":
                attArr = lhc_bigSmall(arr[1], 25, 49);
                break;
            case "正码二单双":
                attArr= lhc_doubleSingle(arr[1], 49);
                break;
            case "正码二合大小":
                attArr = lhc_he_bigOrSmall(arr[1], 7, 49);

                break;
            case "正码二合单双":
                attArr = lhc_he_doubleSigle(arr[1], 49);
                break;
            case "正码二尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[1].charAt(1)), 5, 49);
                break;


            case "正码三大小":
                attArr = lhc_bigSmall(arr[2], 25, 49);
                break;
            case "正码三单双":
                attArr=  lhc_doubleSingle(arr[2], 49);
                break;
            case "正码三合大小":
                attArr = lhc_he_bigOrSmall(arr[2], 7, 49);

                break;
            case "正码三合单双":
                attArr = lhc_he_doubleSigle(arr[2], 49);
                break;
            case "正码三尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[2].charAt(1)), 5, 49);
                break;


            case "正码四大小":
                attArr = lhc_bigSmall(arr[3], 25, 49);
                break;
            case "正码四单双":
                attArr=  lhc_doubleSingle(arr[3], 49);
                break;
            case "正码四合大小":
                attArr = lhc_he_bigOrSmall(arr[3], 7, 49);

                break;
            case "正码四合单双":
                attArr = lhc_he_doubleSigle(arr[3], 49);
                break;
            case "正码四尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[3].charAt(1)), 5, 49);
                break;


            case "正码五大小":
                attArr = lhc_bigSmall(arr[4], 25, 49);
                break;
            case "正码五单双":
                attArr=  lhc_doubleSingle(arr[4], 49);
                break;
            case "正码五合大小":
                attArr = lhc_he_bigOrSmall(arr[4], 7, 49);

                break;
            case "正码五合单双":
                attArr = lhc_he_doubleSigle(arr[4], 49);
                break;
            case "正码五尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[4].charAt(1)), 5, 49);
                break;



            case "正码六大小":
                attArr = lhc_bigSmall(arr[5], 25, 49);
                break;
            case "正码六单双":
                attArr= lhc_doubleSingle(arr[5], 49);
                break;
            case "正码六合大小":
                attArr = lhc_he_bigOrSmall(arr[5], 7, 49);

                break;
            case "正码六合单双":
                attArr = lhc_he_doubleSigle(arr[5], 49);
                break;
            case "正码六尾大小":
                attArr = lhc_bigSmall(String.valueOf(arr[5].charAt(1)), 5, 49);
                break;
            case "总和大小":
                attArr =lhc_bigSmall(total+"", 176, 175);
                break;
            case "总和单双":
                attArr = lhc_he_doubleSigle(total+"", 175);
                break;
            case "总肖单双":
                List<String> objects = new ArrayList<>();
                for (int x = 0; x < arr.length; x++) {
                    String shengxiao = LotteryUtil.getLHCSX(Integer.valueOf(arr[x]));
                    objects.add(shengxiao);
                }
                HashSet<String> hashSet = new HashSet<>();
                 hashSet.addAll(objects);
                int size = hashSet.size();
                attArr = lhc_doubleSingle(size + "", 49);
                break;
            default:
                break;
        }
        return attArr;
    }


}
