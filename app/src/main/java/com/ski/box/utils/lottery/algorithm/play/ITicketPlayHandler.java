package com.ski.box.utils.lottery.algorithm.play;


import com.ski.box.utils.lottery.algorithm.utils.exception.GlobalServiceException;

public interface ITicketPlayHandler {
    int[] OFFSETS_WUXIN = new int[]{0, 1, 2, 3, 4};
    int[] OFFSETS_HOUSI = new int[]{1, 2, 3, 4};
    int[] OFFSETS_QIANSAN = new int[]{0, 1, 2};
    int[] OFFSETS_QIANSI = new int[]{0, 1, 2, 3};
    int[] OFFSETS_ZHONGWEI = new int[]{2};
    int[] OFFSETS_ZHONGSAN = new int[]{1, 2, 3};
    int[] OFFSETS_HOUSAN = new int[]{2, 3, 4};
    int[] OFFSETS_QIANER = new int[]{0, 1};
    int[] OFFSETS_ERSAN = new int[]{1, 2};
    int[] OFFSETS_HOUER = new int[]{3, 4};
    int[] OFFSETS_QIANWU = new int[]{0, 1, 2, 3, 4};
    int[] OFFSETS_PK10_TOP5 = new int[]{0, 1, 2, 3, 4};
    int[] OFFSETS_PK10_LAST5 = new int[]{5, 6, 7, 8, 9};
    int[] ERXING_ZHHZ = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    int[] ERXING_ZXHZ = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 4, 4, 3, 3, 2, 2, 1, 1};
    int[] ERXING_MULTI = new int[]{1, 3, 6, 10};
    int[] SANXING_ZHHZ = new int[]{1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 63, 69, 73, 75, 75, 73, 69, 63, 55, 45, 36, 28, 21, 15, 10, 6, 3, 1};
    int[] SANXING_ZXHZ = new int[]{1, 2, 2, 4, 5, 6, 8, 10, 11, 13, 14, 14, 15, 15, 14, 14, 13, 11, 10, 8, 6, 5, 4, 2, 2, 1};
    int[] SANXING_MULTI = new int[]{1, 4, 10};
    int[] SIXING_MULTI = new int[]{1, 5};
    String[] BET_DXDS = new String[]{"0", "1", "2", "3"};
    String[] SUOHA_BET_INDEX = new String[]{"0", "1", "2", "3", "4", "5", "6"};
    String[] BJL_BET_IDNEX = new String[]{"0", "1", "2", "3", "4", "5"};
    String[] NIUNIU_BET_INDEX = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    String[] OFFSETS_KUAI3_2BTHDX = new String[]{"12", "13", "14", "15", "16", "23", "24", "25", "26", "34", "35", "36", "45", "46", "56"};
    String[] OFFSETS_KUAI3_3TH = new String[]{"111", "222", "333", "444", "555", "666"};
    String[] OFFSETS_KUAI3_3BTH = new String[]{"123", "124", "125", "126", "134", "135", "136", "145", "146", "156", "234", "235", "236", "245", "246", "256", "345", "346", "356", "456"};
    String[] OFFSETS_KUAI3_3BTHFX = new String[]{"1", "2", "3", "4", "5", "6"};
    String[] OFFSETS_KUAI3_BS = new String[]{"124", "125", "126", "134", "145", "156", "235", "236", "245", "256", "346", "356"};
    String[] OFFSETS_KUAI3_Z6 = new String[]{"135", "136", "146", "246"};
    String[] OFFSETS_KUAI3_SLH = new String[]{"123", "234", "345", "456"};
    String[] OFFSETS_KUAI3_2THDXDS = new String[]{"112", "113", "114", "115", "116", "221", "223", "224", "225", "226", "331", "332", "334", "335", "336", "441", "442", "443", "445", "446", "551", "552", "553", "554", "556", "661", "662", "663", "664", "665"};
    String[] OFFSETS_KUAI3_2THDXFS = new String[]{"11", "22", "33", "44", "55", "66"};
    String[] NUMS_SSC = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] NUMS_115 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
    String[] NUMS_115_QW_CZW = new String[]{"03", "04", "05", "06", "07", "08", "09"};
    String[] NUMS_115_QW_DDS = new String[]{"00", "01", "02", "03", "04", "05"};
    String[] NUMS_FC3D = NUMS_SSC;
    String[] NUMS_PK10_old = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    String[] NUMS_PK10 = new String[]{"01","02","03","04","05","06","07","08","09","10"};
    String[] WEI_SHU_NUMBER = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] DING_WEI_DAN_OR_XUANHAO = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49"};
    String[] SHENG_XIAO = new String[]{"shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"};
    String[] SHENG_XIAO_CODE = new String[]{"shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"};
    String[] LIAN_WEI = new String[]{"lingwei", "yiwei", "erwei", "sanwei", "siwei", "wuwei", "liuwei", "qiwei", "bawei", "jiuwei"};
    String[] LIAN_WEI_CODE = new String[]{"lingwei", "yiwei", "erwei", "sanwei", "siwei", "wuwei", "liuwei", "qiwei", "bawei", "jiuwei"};
    String[] DAN_SHUANG = new String[]{"dan", "shuang"};
    String[] ZONG_XIAO = new String[]{"erxiao", "sanxiao", "sixiao", "wuxiao", "liuxiao", "qixiao"};


    String[] getBetNums(String betNums);

    int calculateBetNum(String betNums) throws GlobalServiceException;

    boolean validateBetNums(String betNums);



}
