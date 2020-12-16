package com.ski.box.bean.lottery;

public interface BetLayoutType {
    int BET_LAYOUT_TYPE_RECTANGLE = 0;// 方形
    int BET_LAYOUT_TYPE_CIRCLE = 1;// 球形
    int BET_LAYOUT_TYPE_CIRCLE_LHC = 2;// 球形六合彩
    int BET_LAYOUT_TYPE_RECTANGLE_HLC = 3;// 六合彩   长方形 类似红波 绿波  正码
    int BET_LAYOUT_TYPE_RECTANGLE_SUM = 4;// 方型和值
    int BET_LAYOUT_TYPE_RECTANGLE_K3 = 5;// 方型快三 双面盘
    int BET_LAYOUT_TYPE_F1_JJS = 6;// F1竞技赛
    // 标准盘快三类型1 三同号单选
    int BET_LAYOUT_TYPE_RECTANGLE_K3_S_1 = 5_1;
    // 标准盘快三类型2  三不同号单选 三不同号复选 三连号单选  半顺单选 杂六单选 二同号单选单式 二同号单选复试和不同号 二同号复选 二不同号复选  二不同号单选 才一个号
    int BET_LAYOUT_TYPE_RECTANGLE_K3_S_2 = 5_2;
    // 标准盘快三类型3  三连号通选  半顺通选 杂六通选
    int BET_LAYOUT_TYPE_RECTANGLE_K3_S_3 = 5_3;
    // 标准盘快三类型4  三同号通选
    int BET_LAYOUT_TYPE_RECTANGLE_K3_S_4 = 5_4;
    int BET_LAYOUT_TYPE_ENTER = 6;  //标准盘输入
    int BET_LAYOUT_TYPE_CIRCLE_KLC = 7;// 欢乐彩球型
    int BET_LAYOUT_TYPE_CIRCLE_DIVID_KLC = 7_1;// 快乐彩分割线
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_1 = 8;// 六合彩   长方形 类似 特码头尾数 特和形态 特尾形态
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_2 = 9;// 六合彩    长方形金 木水火土
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_3 = 10;// 六合彩   正方形 特肖核销等
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_TIPS = 11;// 六合彩  友情提示
    int BET_LAYOUT_TYPE_FILTER = 12;// 单式 ssc 任选 玩法 里面的 筛选功能item
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_4 = 13;// 特码形态
    int BET_LAYOUT_TYPE_SHENG_XIAO_CIRCLE = 14;// 球形
    /*有标题 标题在上面 天地 前后 家野*/
    int BET_LAYOUT_TYPE_RECTANGLE_HLC_5 = 15;











    int DIVIDER_TYPE_LINE = 0;
    int DIVIDER_TYPE_BLOCK = 1;
    int DIVIDER_TYPE_NONE = 2;


    int COLOR_TYPE_RED = 1;
    int COLOR_TYPE_BLUE = 2;
    int COLOR_TYPE_GRAY = 3;
}
