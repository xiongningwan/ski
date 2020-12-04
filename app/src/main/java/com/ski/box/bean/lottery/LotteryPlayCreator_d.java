package com.ski.box.bean.lottery;

import com.google.gson.Gson;
import com.yb.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_CIRCLE;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE;
import static com.ski.box.bean.lottery.BetLayoutType.BET_LAYOUT_TYPE_RECTANGLE_K3;
import static com.ski.box.bean.lottery.BetLayoutType.DIVIDER_TYPE_BLOCK;
import static com.ski.box.bean.lottery.BetLayoutType.DIVIDER_TYPE_LINE;
import static com.ski.box.bean.lottery.BetLayoutType.DIVIDER_TYPE_NONE;
import static com.ski.box.bean.lottery.MethodIdInterface.S_ColdHot;
import static com.ski.box.bean.lottery.MethodIdInterface.S_ColdHot_Omit;
import static com.ski.box.bean.lottery.MethodIdInterface.S_ColdHot_Omit_Odds_One;
import static com.ski.box.bean.lottery.MethodIdInterface.S_ColdHot_Omit_Odds_Two;
import static com.ski.box.bean.lottery.MethodIdInterface.S_Odds;
import static com.ski.box.bean.lottery.MethodIdInterface.S_Omit;


/**
 * 双面玩法生成器
 */
public class LotteryPlayCreator_d {

    public static void main(String[] args) {
        // 创建玩法json
        Gson gson = new Gson();
//        List<LotteryPlayStart> list = createPlay_pk10();
//        List<LotteryPlayStart> list = createPlay_ssc();
//        List<LotteryPlayStart> list = createPlay_lhc();
//        List<LotteryPlayStart> list = createPlay_11x5();
//        List<LotteryPlayStart> list = createPlay_k3();
//        List<LotteryPlayStart> list = createPlay_kl8();
        List<LotteryPlayStart> list = createPlay_3d();
//        List<LotteryPlayStart> list = getStandard(LotteryConstant.SER_ID_SSC, gson);
        String json = gson.toJson(list);
        System.out.println(json);
    }

    private static List<LotteryPlayStart> createPlay_pk10() {
        //  bjpk10
        List<LotteryPlayStart> lotteryPlayStartList = new ArrayList<>();
        LotteryPlayStart playStart1 = new LotteryPlayStart("双面", "liangmian");
        List<LotteryPlaySub> playSubs1 = new ArrayList<>();
        playStart1.setSubPlays(playSubs1);

        LotteryPlaySub playSub1 = new LotteryPlaySub("双面_副", "", "liangmian", S_ColdHot);
        List<LotteryPlayEnd> playEndList1 = new ArrayList<>();
        playSub1.setLotteryPlayEnds(playEndList1);

        LotteryPlayEnd playEnd1 = new LotteryPlayEnd();
        playEnd1.setTag("冠亚军和");
        playEnd1.setRemoteCode("guanyajunhe");
        playEnd1.setSpanCount(4);
        playEnd1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList1 = new ArrayList<>();
        playEnd1.setLotteryPlays(lotteryPlayList1);

        lotteryPlayList1.add(new LotteryPlay(545, "冠亚和大小", "大", "da", 545, "guanyajunheda", "da"));
        lotteryPlayList1.add(new LotteryPlay(546, "冠亚和大小", "小", "xiao", 546, "guanyajunhexiao", "xiao"));
        lotteryPlayList1.add(new LotteryPlay(547, "冠亚和单双", "单", "dan", 547, "guanyajunhedan", "dan"));
        lotteryPlayList1.add(new LotteryPlay(548, "冠亚和单双", "双", "shuang", 548, "guanyajunheshuang", "shuang"));

        //--
        LotteryPlayEnd playEnd2 = new LotteryPlayEnd();
        playEnd2.setTag("冠军");
        playEnd2.setRemoteCode("guanjun");
        playEnd2.setSpanCount(4);
        playEnd2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2 = new ArrayList<>();
        playEnd2.setLotteryPlays(lotteryPlayList2);
        lotteryPlayList2.add(new LotteryPlay(549, "冠军大小", "大", "da", 549, "diyiqiudaxiao", "da"));
        lotteryPlayList2.add(new LotteryPlay(550, "冠军大小", "小", "xiao", 550, "diyiqiudaxiao", "xiao"));
        lotteryPlayList2.add(new LotteryPlay(551, "冠军单双", "单", "dan", 551, "diyiqiudanshuang", "dan"));
        lotteryPlayList2.add(new LotteryPlay(552, "冠军单双", "双", "shuang", 552, "diyiqiudanshuang", "shuang"));
        lotteryPlayList2.add(new LotteryPlay(553, "冠军龙虎", "龙", "long", 553, "diyiqiulonghu", "long"));
        lotteryPlayList2.add(new LotteryPlay(554, "冠军龙虎", "虎", "hu", 554, "diyiqiulonghu", "hu"));

        //--
        LotteryPlayEnd playEnd3 = new LotteryPlayEnd();
        playEnd3.setTag("亚军");
        playEnd3.setRemoteCode("yajun");
        playEnd3.setSpanCount(4);
        playEnd3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3 = new ArrayList<>();
        playEnd3.setLotteryPlays(lotteryPlayList3);
        lotteryPlayList3.add(new LotteryPlay(555, "亚军大小", "大", "da", 555, "dierqiudaxiao", "da"));
        lotteryPlayList3.add(new LotteryPlay(556, "亚军大小", "小", "xiao", 556, "dierqiudaxiao", "xiao"));
        lotteryPlayList3.add(new LotteryPlay(557, "亚军单双", "单", "dan", 557, "dierqiudanshuang", "dan"));
        lotteryPlayList3.add(new LotteryPlay(558, "亚军单双", "双", "shuang", 558, "dierqiudanshuang", "shuang"));
        lotteryPlayList3.add(new LotteryPlay(559, "亚军龙虎", "龙", "long", 559, "dierqiulonghu", "long"));
        lotteryPlayList3.add(new LotteryPlay(560, "亚军龙虎", "虎", "hu", 560, "dierqiulonghu", "hu"));

        //--
        LotteryPlayEnd playEnd4 = new LotteryPlayEnd();
        playEnd4.setTag("第三名");
        playEnd4.setRemoteCode("disanming");
        playEnd4.setSpanCount(4);
        playEnd4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4 = new ArrayList<>();
        playEnd4.setLotteryPlays(lotteryPlayList4);
        lotteryPlayList4.add(new LotteryPlay(561, "第三名大小", "大", "da", 561, "disanqiudaxiao", "da"));
        lotteryPlayList4.add(new LotteryPlay(562, "第三名大小", "小", "xiao", 562, "disanqiudaxiao", "xiao"));
        lotteryPlayList4.add(new LotteryPlay(563, "第三名单双", "单", "dan", 563, "disanqiudanshuang", "dan"));
        lotteryPlayList4.add(new LotteryPlay(564, "第三名单双", "双", "shuang", 564, "disanqiudanshuang", "shuang"));
        lotteryPlayList4.add(new LotteryPlay(565, "第三名龙虎", "龙", "long", 565, "disanqiulonghu", "long"));
        lotteryPlayList4.add(new LotteryPlay(566, "第三名龙虎", "虎", "hu", 566, "disanqiulonghu", "hu"));

        //--
        LotteryPlayEnd playEnd5 = new LotteryPlayEnd();
        playEnd5.setTag("第四名");
        playEnd5.setRemoteCode("disiming");
        playEnd5.setSpanCount(4);
        playEnd5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5 = new ArrayList<>();
        playEnd5.setLotteryPlays(lotteryPlayList5);
        lotteryPlayList5.add(new LotteryPlay(567, "第四名大小", "大", "da", 567, "disiqiudaxiao", "da"));
        lotteryPlayList5.add(new LotteryPlay(568, "第四名大小", "小", "xiao", 568, "disiqiudaxiao", "xiao"));
        lotteryPlayList5.add(new LotteryPlay(569, "第四名单双", "单", "dan", 569, "disiqiudanshuang", "dan"));
        lotteryPlayList5.add(new LotteryPlay(570, "第四名单双", "双", "shuang", 570, "disiqiudanshuang", "shuang"));
        lotteryPlayList5.add(new LotteryPlay(571, "第四名龙虎", "龙", "long", 571, "disiqiulonghu", "long"));
        lotteryPlayList5.add(new LotteryPlay(572, "第四名龙虎", "虎", "hu", 572, "disiqiulonghu", "hu"));

        //--
        LotteryPlayEnd playEnd6 = new LotteryPlayEnd();
        playEnd6.setTag("第五名");
        playEnd6.setRemoteCode("diwuming");
        playEnd6.setSpanCount(4);
        playEnd6.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList6 = new ArrayList<>();
        playEnd6.setLotteryPlays(lotteryPlayList6);
        lotteryPlayList6.add(new LotteryPlay(573, "第五名大小", "大", "da", 573, "diwuqiudaxiao", "da"));
        lotteryPlayList6.add(new LotteryPlay(574, "第五名大小", "小", "xiao", 574, "diwuqiudaxiao", "xiao"));
        lotteryPlayList6.add(new LotteryPlay(575, "第五名单双", "单", "dan", 575, "diwuqiudanshuang", "dan"));
        lotteryPlayList6.add(new LotteryPlay(576, "第五名单双", "双", "shuang", 576, "diwuqiudanshuang", "shuang"));
        lotteryPlayList6.add(new LotteryPlay(577, "第五名龙虎", "龙", "long", 577, "diwuqiulonghu", "long"));
        lotteryPlayList6.add(new LotteryPlay(578, "第五名龙虎", "虎", "hu", 578, "diwuqiulonghu", "hu"));

        //--
        LotteryPlayEnd playEnd7 = new LotteryPlayEnd();
        playEnd7.setTag("第六名");
        playEnd7.setRemoteCode("diliuming");
        playEnd7.setSpanCount(4);
        playEnd7.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList7 = new ArrayList<>();
        playEnd7.setLotteryPlays(lotteryPlayList7);
        lotteryPlayList7.add(new LotteryPlay(679, "第六名大小", "大", "da", 679, "diliuqiudaxiao", "da"));
        lotteryPlayList7.add(new LotteryPlay(680, "第六名大小", "小", "xiao", 680, "diliuqiudaxiao", "xiao"));
        lotteryPlayList7.add(new LotteryPlay(859, "第六名单双", "单", "dan", 859, "diliuqiudanshuang", "dan"));
        lotteryPlayList7.add(new LotteryPlay(860, "第六名单双", "双", "shuang", 860, "diliuqiudanshuang", "shuang"));

        //--
        LotteryPlayEnd playEnd8 = new LotteryPlayEnd();
        playEnd8.setTag("第七名");
        playEnd8.setRemoteCode("diqiming");
        playEnd8.setSpanCount(4);
        playEnd8.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8 = new ArrayList<>();
        playEnd8.setLotteryPlays(lotteryPlayList8);
        lotteryPlayList8.add(new LotteryPlay(861, "第七名大小", "大", "da", 861, "diqiqiudaxiao", "da"));
        lotteryPlayList8.add(new LotteryPlay(862, "第七名大小", "小", "xiao", 862, "diqiqiudaxiao", "xiao"));
        lotteryPlayList8.add(new LotteryPlay(863, "第七名单双", "单", "dan", 863, "diqiqiudanshuang", "dan"));
        lotteryPlayList8.add(new LotteryPlay(864, "第七名单双", "双", "shuang", 864, "diqiqiudanshuang", "shuang"));

        //--
        LotteryPlayEnd playEnd9 = new LotteryPlayEnd();
        playEnd9.setTag("第八名");
        playEnd9.setRemoteCode("dibaming");
        playEnd9.setSpanCount(4);
        playEnd9.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList9 = new ArrayList<>();
        playEnd9.setLotteryPlays(lotteryPlayList9);
        lotteryPlayList9.add(new LotteryPlay(865, "第八名大小", "大", "da", 865, "dibaqiudaxiao", "da"));
        lotteryPlayList9.add(new LotteryPlay(866, "第八名大小", "小", "xiao", 866, "dibaqiudaxiao", "xiao"));
        lotteryPlayList9.add(new LotteryPlay(867, "第八名单双", "单", "dan", 867, "dibaqiudanshuang", "dan"));
        lotteryPlayList9.add(new LotteryPlay(868, "第八名单双", "双", "shuang", 868, "dibaqiudanshuang", "shuang"));

        //--
        LotteryPlayEnd playEnd10 = new LotteryPlayEnd();
        playEnd10.setTag("第九名");
        playEnd10.setRemoteCode("dijiuming");
        playEnd10.setSpanCount(4);
        playEnd10.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList10 = new ArrayList<>();
        playEnd10.setLotteryPlays(lotteryPlayList10);
        lotteryPlayList10.add(new LotteryPlay(869, "第九名大小", "大", "da", 869, "dijiuqiudaxiao", "da"));
        lotteryPlayList10.add(new LotteryPlay(870, "第九名大小", "小", "xiao", 870, "dijiuqiudaxiao", "xiao"));
        lotteryPlayList10.add(new LotteryPlay(871, "第九名单双", "单", "dan", 871, "dijiuqiudanshuang", "dan"));
        lotteryPlayList10.add(new LotteryPlay(872, "第九名单双", "双", "shuang", 872, "dijiuqiudanshuang", "shuang"));
        //--
        LotteryPlayEnd playEnd11 = new LotteryPlayEnd();
        playEnd11.setTag("第十名");
        playEnd11.setRemoteCode("dishiming");
        playEnd11.setSpanCount(4);
        playEnd11.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList11 = new ArrayList<>();
        playEnd11.setLotteryPlays(lotteryPlayList11);
        lotteryPlayList11.add(new LotteryPlay(873, "第十名大小", "大", "da", 873, "dishiqiudaxiao", "da"));
        lotteryPlayList11.add(new LotteryPlay(874, "第十名大小", "小", "xiao", 874, "dishiqiudaxiao", "xiao"));
        lotteryPlayList11.add(new LotteryPlay(875, "第十名单双", "单", "dan", 875, "dishiqiudanshuang", "dan"));
        lotteryPlayList11.add(new LotteryPlay(876, "第十名单双", "双", "shuang", 876, "dishiqiudanshuang", "shuang"));

        //--
        LotteryPlayStart playStart2 = new LotteryPlayStart("选号", "danhao");
        List<LotteryPlaySub> playSubs2 = new ArrayList<>();
        playStart2.setSubPlays(playSubs2);

        LotteryPlaySub playSub2 = new LotteryPlaySub("选号_副", "", "danhao", S_ColdHot_Omit);
        List<LotteryPlayEnd> playEndList2 = new ArrayList<>();
        playSub2.setLotteryPlayEnds(playEndList2);

        LotteryPlayEnd playEnd2_1 = new LotteryPlayEnd();
        playEnd2_1.setTag("冠军");
        playEnd2_1.setRemoteCode("guanjun");
        playEnd2_1.setSpanCount(4);
        playEnd2_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_1 = new ArrayList<>();
        playEnd2_1.setLotteryPlays(lotteryPlayList2_1);
        lotteryPlayList2_1.add(new LotteryPlay(579, "冠军定位胆", "1", "1", "", "guanjundingweidan", "1"));
        lotteryPlayList2_1.add(new LotteryPlay(580, "冠军定位胆", "2", "2", "", "guanjundingweidan", "2"));
        lotteryPlayList2_1.add(new LotteryPlay(581, "冠军定位胆", "3", "3", "", "guanjundingweidan", "3"));
        lotteryPlayList2_1.add(new LotteryPlay(582, "冠军定位胆", "4", "4", "", "guanjundingweidan", "4"));
        lotteryPlayList2_1.add(new LotteryPlay(583, "冠军定位胆", "5", "5", "", "guanjundingweidan", "5"));
        lotteryPlayList2_1.add(new LotteryPlay(584, "冠军定位胆", "6", "6", "", "guanjundingweidan", "6"));
        lotteryPlayList2_1.add(new LotteryPlay(585, "冠军定位胆", "7", "7", "", "guanjundingweidan", "7"));
        lotteryPlayList2_1.add(new LotteryPlay(586, "冠军定位胆", "8", "8", "", "guanjundingweidan", "8"));
        lotteryPlayList2_1.add(new LotteryPlay(587, "冠军定位胆", "9", "9", "", "guanjundingweidan", "9"));
        lotteryPlayList2_1.add(new LotteryPlay(588, "冠军定位胆", "10", "10", "", "guanjundingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_2 = new LotteryPlayEnd();
        playEnd2_2.setTag("亚军");
        playEnd2_2.setRemoteCode("yajun");
        playEnd2_2.setSpanCount(4);
        playEnd2_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_2 = new ArrayList<>();
        playEnd2_2.setLotteryPlays(lotteryPlayList2_2);
        lotteryPlayList2_2.add(new LotteryPlay(589, "亚军定位胆", "1", "1", "", "yajundingweidan", "1"));
        lotteryPlayList2_2.add(new LotteryPlay(590, "亚军定位胆", "2", "2", "", "yajundingweidan", "2"));
        lotteryPlayList2_2.add(new LotteryPlay(591, "亚军定位胆", "3", "3", "", "yajundingweidan", "3"));
        lotteryPlayList2_2.add(new LotteryPlay(592, "亚军定位胆", "4", "4", "", "yajundingweidan", "4"));
        lotteryPlayList2_2.add(new LotteryPlay(593, "亚军定位胆", "5", "5", "", "yajundingweidan", "5"));
        lotteryPlayList2_2.add(new LotteryPlay(594, "亚军定位胆", "6", "6", "", "yajundingweidan", "6"));
        lotteryPlayList2_2.add(new LotteryPlay(595, "亚军定位胆", "7", "7", "", "yajundingweidan", "7"));
        lotteryPlayList2_2.add(new LotteryPlay(596, "亚军定位胆", "8", "8", "", "yajundingweidan", "8"));
        lotteryPlayList2_2.add(new LotteryPlay(597, "亚军定位胆", "9", "9", "", "yajundingweidan", "9"));
        lotteryPlayList2_2.add(new LotteryPlay(598, "亚军定位胆", "10", "10", "", "yajundingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_3 = new LotteryPlayEnd();
        playEnd2_3.setTag("第三名");
        playEnd2_3.setRemoteCode("disanming");
        playEnd2_3.setSpanCount(4);
        playEnd2_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_3 = new ArrayList<>();
        playEnd2_3.setLotteryPlays(lotteryPlayList2_3);
        lotteryPlayList2_3.add(new LotteryPlay(599, "第三名定位胆", "1", "1", "", "disanmingdingweidan", "1"));
        lotteryPlayList2_3.add(new LotteryPlay(600, "第三名定位胆", "2", "2", "", "disanmingdingweidan", "2"));
        lotteryPlayList2_3.add(new LotteryPlay(601, "第三名定位胆", "3", "3", "", "disanmingdingweidan", "3"));
        lotteryPlayList2_3.add(new LotteryPlay(602, "第三名定位胆", "4", "4", "", "disanmingdingweidan", "4"));
        lotteryPlayList2_3.add(new LotteryPlay(603, "第三名定位胆", "5", "5", "", "disanmingdingweidan", "5"));
        lotteryPlayList2_3.add(new LotteryPlay(604, "第三名定位胆", "6", "6", "", "disanmingdingweidan", "6"));
        lotteryPlayList2_3.add(new LotteryPlay(605, "第三名定位胆", "7", "7", "", "disanmingdingweidan", "7"));
        lotteryPlayList2_3.add(new LotteryPlay(606, "第三名定位胆", "8", "8", "", "disanmingdingweidan", "8"));
        lotteryPlayList2_3.add(new LotteryPlay(607, "第三名定位胆", "9", "9", "", "disanmingdingweidan", "9"));
        lotteryPlayList2_3.add(new LotteryPlay(608, "第三名定位胆", "10", "10", "", "disanmingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_4 = new LotteryPlayEnd();
        playEnd2_4.setTag("第四名");
        playEnd2_4.setRemoteCode("disiming");
        playEnd2_4.setSpanCount(4);
        playEnd2_4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_4 = new ArrayList<>();
        playEnd2_4.setLotteryPlays(lotteryPlayList2_4);
        lotteryPlayList2_4.add(new LotteryPlay(609, "第四名定位胆", "1", "1", "", "disimingdingweidan", "1"));
        lotteryPlayList2_4.add(new LotteryPlay(610, "第四名定位胆", "2", "2", "", "disimingdingweidan", "2"));
        lotteryPlayList2_4.add(new LotteryPlay(611, "第四名定位胆", "3", "3", "", "disimingdingweidan", "3"));
        lotteryPlayList2_4.add(new LotteryPlay(612, "第四名定位胆", "4", "4", "", "disimingdingweidan", "4"));
        lotteryPlayList2_4.add(new LotteryPlay(613, "第四名定位胆", "5", "5", "", "disimingdingweidan", "5"));
        lotteryPlayList2_4.add(new LotteryPlay(614, "第四名定位胆", "6", "6", "", "disimingdingweidan", "6"));
        lotteryPlayList2_4.add(new LotteryPlay(615, "第四名定位胆", "7", "7", "", "disimingdingweidan", "7"));
        lotteryPlayList2_4.add(new LotteryPlay(616, "第四名定位胆", "8", "8", "", "disimingdingweidan", "8"));
        lotteryPlayList2_4.add(new LotteryPlay(617, "第四名定位胆", "9", "9", "", "disimingdingweidan", "9"));
        lotteryPlayList2_4.add(new LotteryPlay(618, "第四名定位胆", "10", "10", "", "disimingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_5 = new LotteryPlayEnd();
        playEnd2_5.setTag("第五名");
        playEnd2_5.setRemoteCode("diwuming");
        playEnd2_5.setSpanCount(4);
        playEnd2_5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_5 = new ArrayList<>();
        playEnd2_5.setLotteryPlays(lotteryPlayList2_5);
        lotteryPlayList2_5.add(new LotteryPlay(619, "第五名定位胆", "1", "1", "", "diwumingdingweidan", "1"));
        lotteryPlayList2_5.add(new LotteryPlay(620, "第五名定位胆", "2", "2", "", "diwumingdingweidan", "2"));
        lotteryPlayList2_5.add(new LotteryPlay(621, "第五名定位胆", "3", "3", "", "diwumingdingweidan", "3"));
        lotteryPlayList2_5.add(new LotteryPlay(622, "第五名定位胆", "4", "4", "", "diwumingdingweidan", "4"));
        lotteryPlayList2_5.add(new LotteryPlay(623, "第五名定位胆", "5", "5", "", "diwumingdingweidan", "5"));
        lotteryPlayList2_5.add(new LotteryPlay(624, "第五名定位胆", "6", "6", "", "diwumingdingweidan", "6"));
        lotteryPlayList2_5.add(new LotteryPlay(625, "第五名定位胆", "7", "7", "", "diwumingdingweidan", "7"));
        lotteryPlayList2_5.add(new LotteryPlay(626, "第五名定位胆", "8", "8", "", "diwumingdingweidan", "8"));
        lotteryPlayList2_5.add(new LotteryPlay(627, "第五名定位胆", "9", "9", "", "diwumingdingweidan", "9"));
        lotteryPlayList2_5.add(new LotteryPlay(628, "第五名定位胆", "10", "10", "", "diwumingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_6 = new LotteryPlayEnd();
        playEnd2_6.setTag("第六名");
        playEnd2_6.setRemoteCode("diliuming");
        playEnd2_6.setSpanCount(4);
        playEnd2_6.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_6 = new ArrayList<>();
        playEnd2_6.setLotteryPlays(lotteryPlayList2_6);
        lotteryPlayList2_6.add(new LotteryPlay(629, "第六名定位胆", "1", "1", "", "diliumingdingweidan", "1"));
        lotteryPlayList2_6.add(new LotteryPlay(630, "第六名定位胆", "2", "2", "", "diliumingdingweidan", "2"));
        lotteryPlayList2_6.add(new LotteryPlay(631, "第六名定位胆", "3", "3", "", "diliumingdingweidan", "3"));
        lotteryPlayList2_6.add(new LotteryPlay(632, "第六名定位胆", "4", "4", "", "diliumingdingweidan", "4"));
        lotteryPlayList2_6.add(new LotteryPlay(633, "第六名定位胆", "5", "5", "", "diliumingdingweidan", "5"));
        lotteryPlayList2_6.add(new LotteryPlay(634, "第六名定位胆", "6", "6", "", "diliumingdingweidan", "6"));
        lotteryPlayList2_6.add(new LotteryPlay(635, "第六名定位胆", "7", "7", "", "diliumingdingweidan", "7"));
        lotteryPlayList2_6.add(new LotteryPlay(636, "第六名定位胆", "8", "8", "", "diliumingdingweidan", "8"));
        lotteryPlayList2_6.add(new LotteryPlay(637, "第六名定位胆", "9", "9", "", "diliumingdingweidan", "9"));
        lotteryPlayList2_6.add(new LotteryPlay(638, "第六名定位胆", "10", "10", "", "diliumingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_7 = new LotteryPlayEnd();
        playEnd2_7.setTag("第七名");
        playEnd2_7.setRemoteCode("diqiming");
        playEnd2_7.setSpanCount(4);
        playEnd2_7.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_7 = new ArrayList<>();
        playEnd2_7.setLotteryPlays(lotteryPlayList2_7);
        lotteryPlayList2_7.add(new LotteryPlay(639, "第七名定位胆", "1", "1", "", "diqimingdingweidan", "1"));
        lotteryPlayList2_7.add(new LotteryPlay(640, "第七名定位胆", "2", "2", "", "diqimingdingweidan", "2"));
        lotteryPlayList2_7.add(new LotteryPlay(641, "第七名定位胆", "3", "3", "", "diqimingdingweidan", "3"));
        lotteryPlayList2_7.add(new LotteryPlay(642, "第七名定位胆", "4", "4", "", "diqimingdingweidan", "4"));
        lotteryPlayList2_7.add(new LotteryPlay(643, "第七名定位胆", "5", "5", "", "diqimingdingweidan", "5"));
        lotteryPlayList2_7.add(new LotteryPlay(644, "第七名定位胆", "6", "6", "", "diqimingdingweidan", "6"));
        lotteryPlayList2_7.add(new LotteryPlay(645, "第七名定位胆", "7", "7", "", "diqimingdingweidan", "7"));
        lotteryPlayList2_7.add(new LotteryPlay(646, "第七名定位胆", "8", "8", "", "diqimingdingweidan", "8"));
        lotteryPlayList2_7.add(new LotteryPlay(647, "第七名定位胆", "9", "9", "", "diqimingdingweidan", "9"));
        lotteryPlayList2_7.add(new LotteryPlay(648, "第七名定位胆", "10", "10", "", "diqimingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_8 = new LotteryPlayEnd();
        playEnd2_8.setTag("第八名");
        playEnd2_8.setRemoteCode("dibaming");
        playEnd2_8.setSpanCount(4);
        playEnd2_8.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_8 = new ArrayList<>();
        playEnd2_8.setLotteryPlays(lotteryPlayList2_8);
        lotteryPlayList2_8.add(new LotteryPlay(649, "第八名定位胆", "1", "1", "", "dibamingdingweidan", "1"));
        lotteryPlayList2_8.add(new LotteryPlay(650, "第八名定位胆", "2", "2", "", "dibamingdingweidan", "2"));
        lotteryPlayList2_8.add(new LotteryPlay(651, "第八名定位胆", "3", "3", "", "dibamingdingweidan", "3"));
        lotteryPlayList2_8.add(new LotteryPlay(652, "第八名定位胆", "4", "4", "", "dibamingdingweidan", "4"));
        lotteryPlayList2_8.add(new LotteryPlay(653, "第八名定位胆", "5", "5", "", "dibamingdingweidan", "5"));
        lotteryPlayList2_8.add(new LotteryPlay(654, "第八名定位胆", "6", "6", "", "dibamingdingweidan", "6"));
        lotteryPlayList2_8.add(new LotteryPlay(655, "第八名定位胆", "7", "7", "", "dibamingdingweidan", "7"));
        lotteryPlayList2_8.add(new LotteryPlay(656, "第八名定位胆", "8", "8", "", "dibamingdingweidan", "8"));
        lotteryPlayList2_8.add(new LotteryPlay(657, "第八名定位胆", "9", "9", "", "dibamingdingweidan", "9"));
        lotteryPlayList2_8.add(new LotteryPlay(658, "第八名定位胆", "10", "10", "", "dibamingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_9 = new LotteryPlayEnd();
        playEnd2_9.setTag("第九名");
        playEnd2_9.setRemoteCode("dijiuming");
        playEnd2_9.setSpanCount(4);
        playEnd2_9.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_9 = new ArrayList<>();
        playEnd2_9.setLotteryPlays(lotteryPlayList2_9);
        lotteryPlayList2_9.add(new LotteryPlay(659, "第九名定位胆", "1", "1", "", "dijiumingdingweidan", "1"));
        lotteryPlayList2_9.add(new LotteryPlay(660, "第九名定位胆", "2", "2", "", "dijiumingdingweidan", "2"));
        lotteryPlayList2_9.add(new LotteryPlay(661, "第九名定位胆", "3", "3", "", "dijiumingdingweidan", "3"));
        lotteryPlayList2_9.add(new LotteryPlay(662, "第九名定位胆", "4", "4", "", "dijiumingdingweidan", "4"));
        lotteryPlayList2_9.add(new LotteryPlay(663, "第九名定位胆", "5", "5", "", "dijiumingdingweidan", "5"));
        lotteryPlayList2_9.add(new LotteryPlay(664, "第九名定位胆", "6", "6", "", "dijiumingdingweidan", "6"));
        lotteryPlayList2_9.add(new LotteryPlay(665, "第九名定位胆", "7", "7", "", "dijiumingdingweidan", "7"));
        lotteryPlayList2_9.add(new LotteryPlay(666, "第九名定位胆", "8", "8", "", "dijiumingdingweidan", "8"));
        lotteryPlayList2_9.add(new LotteryPlay(667, "第九名定位胆", "9", "9", "", "dijiumingdingweidan", "9"));
        lotteryPlayList2_9.add(new LotteryPlay(668, "第九名定位胆", "10", "10", "", "dijiumingdingweidan", "10"));

        //--
        LotteryPlayEnd playEnd2_10 = new LotteryPlayEnd();
        playEnd2_10.setTag("第十名");
        playEnd2_10.setRemoteCode("dishiming");
        playEnd2_10.setSpanCount(4);
        playEnd2_10.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList2_10 = new ArrayList<>();
        playEnd2_10.setLotteryPlays(lotteryPlayList2_10);
        lotteryPlayList2_10.add(new LotteryPlay(669, "第十名定位胆", "1", "1", "", "dishimingdingweidan", "1"));
        lotteryPlayList2_10.add(new LotteryPlay(670, "第十名定位胆", "2", "2", "", "dishimingdingweidan", "2"));
        lotteryPlayList2_10.add(new LotteryPlay(671, "第十名定位胆", "3", "3", "", "dishimingdingweidan", "3"));
        lotteryPlayList2_10.add(new LotteryPlay(672, "第十名定位胆", "4", "4", "", "dishimingdingweidan", "4"));
        lotteryPlayList2_10.add(new LotteryPlay(673, "第十名定位胆", "5", "5", "", "dishimingdingweidan", "5"));
        lotteryPlayList2_10.add(new LotteryPlay(674, "第十名定位胆", "6", "6", "", "dishimingdingweidan", "6"));
        lotteryPlayList2_10.add(new LotteryPlay(675, "第十名定位胆", "7", "7", "", "dishimingdingweidan", "7"));
        lotteryPlayList2_10.add(new LotteryPlay(676, "第十名定位胆", "8", "8", "", "dishimingdingweidan", "8"));
        lotteryPlayList2_10.add(new LotteryPlay(677, "第十名定位胆", "9", "9", "", "dishimingdingweidan", "9"));
        lotteryPlayList2_10.add(new LotteryPlay(678, "第十名定位胆", "10", "10", "", "dishimingdingweidan", "10"));


        //--
        LotteryPlayStart playStart3 = new LotteryPlayStart("冠亚和值", "guanyahezhi");
        List<LotteryPlaySub> playSubs3 = new ArrayList<>();
        playStart3.setSubPlays(playSubs3);

        LotteryPlaySub playSub3 = new LotteryPlaySub("冠亚和值_副", "", "guanyahezhi", "");
        List<LotteryPlayEnd> playEndList3 = new ArrayList<>();
        playSub3.setLotteryPlayEnds(playEndList3);

        LotteryPlayEnd playEnd3_1 = new LotteryPlayEnd();
        playEnd3_1.setTag("冠亚军和");
        playEnd3_1.setRemoteCode("guanyajunhe");
        playEnd3_1.setSpanCount(4);
        playEnd3_1.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList3_1 = new ArrayList<>();
        playEnd3_1.setLotteryPlays(lotteryPlayList3_1);
        lotteryPlayList3_1.add(new LotteryPlay(681, "冠亚和值", "3", "3", "", "guanyahezhisan", "3"));
        lotteryPlayList3_1.add(new LotteryPlay(682, "冠亚和值", "4", "4", "", "guanyahezhisi", "4"));
        lotteryPlayList3_1.add(new LotteryPlay(683, "冠亚和值", "5", "5", "", "guanyahezhiwu", "5"));
        lotteryPlayList3_1.add(new LotteryPlay(684, "冠亚和值", "6", "6", "", "guanyahezhiliu", "6"));
        lotteryPlayList3_1.add(new LotteryPlay(685, "冠亚和值", "7", "7", "", "guanyahezhiqi", "7"));
        lotteryPlayList3_1.add(new LotteryPlay(686, "冠亚和值", "8", "8", "", "guanyahezhiba", "8"));
        lotteryPlayList3_1.add(new LotteryPlay(687, "冠亚和值", "9", "9", "", "guanyahezhijiu", "9"));
        lotteryPlayList3_1.add(new LotteryPlay(688, "冠亚和值", "10", "10", "", "guanyahezhishi", "10"));
        lotteryPlayList3_1.add(new LotteryPlay(689, "冠亚和值", "11", "11", "", "guanyahezhishiyi", "11"));
        lotteryPlayList3_1.add(new LotteryPlay(690, "冠亚和值", "12", "12", "", "guanyahezhishier", "12"));
        lotteryPlayList3_1.add(new LotteryPlay(691, "冠亚和值", "13", "13", "", "guanyahezhishisan", "13"));
        lotteryPlayList3_1.add(new LotteryPlay(692, "冠亚和值", "14", "14", "", "guanyahezhishisi", "14"));
        lotteryPlayList3_1.add(new LotteryPlay(693, "冠亚和值", "15", "15", "", "guanyahezhishiwu", "15"));
        lotteryPlayList3_1.add(new LotteryPlay(694, "冠亚和值", "16", "16", "", "guanyahezhishiliu", "16"));
        lotteryPlayList3_1.add(new LotteryPlay(695, "冠亚和值", "17", "17", "", "guanyahezhishiqi", "17"));
        lotteryPlayList3_1.add(new LotteryPlay(696, "冠亚和值", "18", "18", "", "guanyahezhishiba", "18"));
        lotteryPlayList3_1.add(new LotteryPlay(697, "冠亚和值", "19", "19", "", "guanyahezhishijiu", "19"));


        playEndList1.add(playEnd1);
        playEndList1.add(playEnd2);
        playEndList1.add(playEnd3);
        playEndList1.add(playEnd4);
        playEndList1.add(playEnd5);
        playEndList1.add(playEnd6);
        playEndList1.add(playEnd7);
        playEndList1.add(playEnd8);
        playEndList1.add(playEnd9);
        playEndList1.add(playEnd10);
        playEndList1.add(playEnd11);
        playEndList2.add(playEnd2_1);
        playEndList2.add(playEnd2_2);
        playEndList2.add(playEnd2_3);
        playEndList2.add(playEnd2_4);
        playEndList2.add(playEnd2_5);
        playEndList2.add(playEnd2_6);
        playEndList2.add(playEnd2_7);
        playEndList2.add(playEnd2_8);
        playEndList2.add(playEnd2_9);
        playEndList2.add(playEnd2_10);
        playEndList3.add(playEnd3_1);

        playSubs1.add(playSub1);
        playSubs2.add(playSub2);
        playSubs3.add(playSub3);


        lotteryPlayStartList.add(playStart1);
        lotteryPlayStartList.add(playStart2);
        lotteryPlayStartList.add(playStart3);

        return lotteryPlayStartList;
    }

    private static List<LotteryPlayStart> createPlay_ssc() {
        //  ssc
        List<LotteryPlayStart> lotteryPlayStartList = new ArrayList<>();
        LotteryPlayStart playStart1 = new LotteryPlayStart("双面", "shuangmian");
        List<LotteryPlaySub> playSubs1 = new ArrayList<>();
        playStart1.setSubPlays(playSubs1);

        LotteryPlaySub playSub1 = new LotteryPlaySub("双面_副", "", "shuangmian", S_ColdHot);
        List<LotteryPlayEnd> playEndList1 = new ArrayList<>();
        playSub1.setLotteryPlayEnds(playEndList1);

        LotteryPlayEnd playEnd1 = new LotteryPlayEnd();
        playEnd1.setTag("总和龙虎和");
        playEnd1.setRemoteCode("zonghelonghuhe");
        playEnd1.setSpanCount(4);
        playEnd1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList1 = new ArrayList<>();
        playEnd1.setLotteryPlays(lotteryPlayList1);

        lotteryPlayList1.add(new LotteryPlay(303, "总和大小", "大", "da", 303, "zonghedaxiao", "da"));
        lotteryPlayList1.add(new LotteryPlay(304, "总和大小", "小", "xiao", 304, "zonghedaxiao", "xiao"));
        lotteryPlayList1.add(new LotteryPlay(305, "总和单双", "单", "dan", 305, "zonghedanshuang", "dan"));
        lotteryPlayList1.add(new LotteryPlay(306, "总和单双", "双", "shuang", 306, "zonghedanshuang", "shuang"));
        lotteryPlayList1.add(new LotteryPlay(307, "龙虎和", "龙", "long", 307, "long", "long"));
        lotteryPlayList1.add(new LotteryPlay(308, "龙虎和", "虎", "hu", 308, "hu", "hu"));
        lotteryPlayList1.add(new LotteryPlay(309, "龙虎和", "和", "he", 309, "he", "he"));
        //--
        LotteryPlayEnd playEnd2 = new LotteryPlayEnd();
        playEnd2.setTag("第一球");
        playEnd2.setRemoteCode("diyiqiu");
        playEnd2.setSpanCount(4);
        playEnd2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2 = new ArrayList<>();
        playEnd2.setLotteryPlays(lotteryPlayList2);
        lotteryPlayList2.add(new LotteryPlay(310, "第一球大小", "大", "da", 310, "diyiqiudaxiao", "da"));
        lotteryPlayList2.add(new LotteryPlay(311, "第一球大小", "小", "xiao", 311, "diyiqiudaxiao", "xiao"));
        lotteryPlayList2.add(new LotteryPlay(312, "第一球单双", "单", "dan", 312, "diyiqiudanshuang", "dan"));
        lotteryPlayList2.add(new LotteryPlay(313, "第一球单双", "双", "shuang", 313, "diyiqiudanshuang", "shuang"));
        lotteryPlayList2.add(new LotteryPlay(314, "第一球质合", "质", "zhi", 314, "diyiqiuzhihe", "zhi"));
        lotteryPlayList2.add(new LotteryPlay(315, "第一球质合", "合", "hen", 315, "diyiqiuzhihe", "hen"));

        //--
        LotteryPlayEnd playEnd3 = new LotteryPlayEnd();
        playEnd3.setTag("第二球");
        playEnd3.setRemoteCode("dierqiu");
        playEnd3.setSpanCount(4);
        playEnd3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3 = new ArrayList<>();
        playEnd3.setLotteryPlays(lotteryPlayList3);
        lotteryPlayList3.add(new LotteryPlay(326, "第二球大小", "大", "da", 326, "dierqiudaxiao", "da"));
        lotteryPlayList3.add(new LotteryPlay(327, "第二球大小", "小", "xiao", 327, "dierqiudaxiao", "xiao"));
        lotteryPlayList3.add(new LotteryPlay(328, "第二球单双", "单", "dan", 328, "dierqiudanshuang", "dan"));
        lotteryPlayList3.add(new LotteryPlay(329, "第二球单双", "双", "shuang", 329, "dierqiudanshuang", "shuang"));
        lotteryPlayList3.add(new LotteryPlay(330, "第二球质合", "质", "zhi", 330, "dierqiuzhihe", "zhi"));
        lotteryPlayList3.add(new LotteryPlay(331, "第二球质合", "合", "hen", 331, "dierqiuzhihe", "hen"));

        //--
        LotteryPlayEnd playEnd4 = new LotteryPlayEnd();
        playEnd4.setTag("第三球");
        playEnd4.setRemoteCode("disanqiu");
        playEnd4.setSpanCount(4);
        playEnd4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4 = new ArrayList<>();
        playEnd4.setLotteryPlays(lotteryPlayList4);
        lotteryPlayList4.add(new LotteryPlay(342, "第三球大小", "大", "da", 342, "disanqiudaxiao", "da"));
        lotteryPlayList4.add(new LotteryPlay(343, "第三球大小", "小", "xiao", 343, "disanqiudaxiao", "xiao"));
        lotteryPlayList4.add(new LotteryPlay(344, "第三球单双", "单", "dan", 344, "disanqiudanshuang", "dan"));
        lotteryPlayList4.add(new LotteryPlay(345, "第三球单双", "双", "shuang", 345, "disanqiudanshuang", "shuang"));
        lotteryPlayList4.add(new LotteryPlay(346, "第三球质合", "质", "zhi", 346, "disanqiuzhihe", "zhi"));
        lotteryPlayList4.add(new LotteryPlay(347, "第三球质合", "合", "hen", 347, "disanqiuzhihe", "hen"));

        //--
        LotteryPlayEnd playEnd5 = new LotteryPlayEnd();
        playEnd5.setTag("第四球");
        playEnd5.setRemoteCode("disiqiu");
        playEnd5.setSpanCount(4);
        playEnd5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5 = new ArrayList<>();
        playEnd5.setLotteryPlays(lotteryPlayList5);
        lotteryPlayList5.add(new LotteryPlay(358, "第四球大小", "大", "da", 358, "disiqiudaxiao", "da"));
        lotteryPlayList5.add(new LotteryPlay(359, "第四球大小", "小", "xiao", 359, "disiqiudaxiao", "xiao"));
        lotteryPlayList5.add(new LotteryPlay(360, "第四球单双", "单", "dan", 360, "disiqiudanshuang", "dan"));
        lotteryPlayList5.add(new LotteryPlay(361, "第四球单双", "双", "shuang", 361, "disiqiudanshuang", "shuang"));
        lotteryPlayList5.add(new LotteryPlay(362, "第四球质合", "质", "zhi", 362, "disiqiuzhihe", "zhi"));
        lotteryPlayList5.add(new LotteryPlay(363, "第四球质合", "合", "hen", 363, "disiqiuzhihe", "hen"));

        //--
        LotteryPlayEnd playEnd6 = new LotteryPlayEnd();
        playEnd6.setTag("第五球");
        playEnd6.setRemoteCode("diwuqiu");
        playEnd6.setSpanCount(4);
        playEnd6.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList6 = new ArrayList<>();
        playEnd6.setLotteryPlays(lotteryPlayList6);
        lotteryPlayList6.add(new LotteryPlay(374, "第五球大小", "大", "da", 374, "diwuqiudaxiao", "da"));
        lotteryPlayList6.add(new LotteryPlay(375, "第五球大小", "小", "xiao", 375, "diwuqiudaxiao", "xiao"));
        lotteryPlayList6.add(new LotteryPlay(376, "第五球单双", "单", "dan", 376, "diwuqiudanshuang", "dan"));
        lotteryPlayList6.add(new LotteryPlay(377, "第五球单双", "双", "shuang", 377, "diwuqiudanshuang", "shuang"));
        lotteryPlayList6.add(new LotteryPlay(378, "第五球质合", "质", "zhi", 378, "diwuqiuzhihe", "zhi"));
        lotteryPlayList6.add(new LotteryPlay(379, "第五球质合", "合", "hen", 379, "diwuqiuzhihe", "hen"));

        //--
        LotteryPlayStart playStart2 = new LotteryPlayStart("选号", "xuanhao");
        List<LotteryPlaySub> playSubs2 = new ArrayList<>();
        playStart2.setSubPlays(playSubs2);

        LotteryPlaySub playSub2 = new LotteryPlaySub("选号_副", "", "xuanhao", S_ColdHot_Omit);
        List<LotteryPlayEnd> playEndList2 = new ArrayList<>();
        playSub2.setLotteryPlayEnds(playEndList2);

        LotteryPlayEnd playEnd2_1 = new LotteryPlayEnd();
        playEnd2_1.setTag("第一球");
        playEnd2_1.setRemoteCode("diyiqiu");
        playEnd2_1.setSpanCount(4);
        playEnd2_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_1 = new ArrayList<>();
        playEnd2_1.setLotteryPlays(lotteryPlayList2_1);
        lotteryPlayList2_1.add(new LotteryPlay(316, "第一球定位胆", "0", "0", "", "diyiqiudingweidan", "0"));
        lotteryPlayList2_1.add(new LotteryPlay(317, "第一球定位胆", "1", "1", "", "diyiqiudingweidan", "1"));
        lotteryPlayList2_1.add(new LotteryPlay(318, "第一球定位胆", "2", "2", "", "diyiqiudingweidan", "2"));
        lotteryPlayList2_1.add(new LotteryPlay(319, "第一球定位胆", "3", "3", "", "diyiqiudingweidan", "3"));
        lotteryPlayList2_1.add(new LotteryPlay(320, "第一球定位胆", "4", "4", "", "diyiqiudingweidan", "4"));
        lotteryPlayList2_1.add(new LotteryPlay(321, "第一球定位胆", "5", "5", "", "diyiqiudingweidan", "5"));
        lotteryPlayList2_1.add(new LotteryPlay(322, "第一球定位胆", "6", "6", "", "diyiqiudingweidan", "6"));
        lotteryPlayList2_1.add(new LotteryPlay(323, "第一球定位胆", "7", "7", "", "diyiqiudingweidan", "7"));
        lotteryPlayList2_1.add(new LotteryPlay(324, "第一球定位胆", "8", "8", "", "diyiqiudingweidan", "8"));
        lotteryPlayList2_1.add(new LotteryPlay(325, "第一球定位胆", "9", "9", "", "diyiqiudingweidan", "9"));

        //--
        LotteryPlayEnd playEnd2_2 = new LotteryPlayEnd();
        playEnd2_2.setTag("第二球");
        playEnd2_2.setRemoteCode("dierqiu");
        playEnd2_2.setSpanCount(4);
        playEnd2_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_2 = new ArrayList<>();
        playEnd2_2.setLotteryPlays(lotteryPlayList2_2);
        lotteryPlayList2_2.add(new LotteryPlay(332, "第二球定位胆", "0", "0", "", "dierqiudingweidan", "0"));
        lotteryPlayList2_2.add(new LotteryPlay(333, "第二球定位胆", "1", "1", "", "dierqiudingweidan", "1"));
        lotteryPlayList2_2.add(new LotteryPlay(334, "第二球定位胆", "2", "2", "", "dierqiudingweidan", "2"));
        lotteryPlayList2_2.add(new LotteryPlay(335, "第二球定位胆", "3", "3", "", "dierqiudingweidan", "3"));
        lotteryPlayList2_2.add(new LotteryPlay(336, "第二球定位胆", "4", "4", "", "dierqiudingweidan", "4"));
        lotteryPlayList2_2.add(new LotteryPlay(337, "第二球定位胆", "5", "5", "", "dierqiudingweidan", "5"));
        lotteryPlayList2_2.add(new LotteryPlay(338, "第二球定位胆", "6", "6", "", "dierqiudingweidan", "6"));
        lotteryPlayList2_2.add(new LotteryPlay(339, "第二球定位胆", "7", "7", "", "dierqiudingweidan", "7"));
        lotteryPlayList2_2.add(new LotteryPlay(340, "第二球定位胆", "8", "8", "", "dierqiudingweidan", "8"));
        lotteryPlayList2_2.add(new LotteryPlay(341, "第二球定位胆", "9", "9", "", "dierqiudingweidan", "9"));

        //--
        LotteryPlayEnd playEnd2_3 = new LotteryPlayEnd();
        playEnd2_3.setTag("第三球");
        playEnd2_3.setRemoteCode("disanqiu");
        playEnd2_3.setSpanCount(4);
        playEnd2_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_3 = new ArrayList<>();
        playEnd2_3.setLotteryPlays(lotteryPlayList2_3);
        lotteryPlayList2_3.add(new LotteryPlay(348, "第三球定位胆", "0", "0", "", "disanqiudingweidan", "0"));
        lotteryPlayList2_3.add(new LotteryPlay(349, "第三球定位胆", "1", "1", "", "disanqiudingweidan", "1"));
        lotteryPlayList2_3.add(new LotteryPlay(350, "第三球定位胆", "2", "2", "", "disanqiudingweidan", "2"));
        lotteryPlayList2_3.add(new LotteryPlay(351, "第三球定位胆", "3", "3", "", "disanqiudingweidan", "3"));
        lotteryPlayList2_3.add(new LotteryPlay(352, "第三球定位胆", "4", "4", "", "disanqiudingweidan", "4"));
        lotteryPlayList2_3.add(new LotteryPlay(353, "第三球定位胆", "5", "5", "", "disanqiudingweidan", "5"));
        lotteryPlayList2_3.add(new LotteryPlay(354, "第三球定位胆", "6", "6", "", "disanqiudingweidan", "6"));
        lotteryPlayList2_3.add(new LotteryPlay(355, "第三球定位胆", "7", "7", "", "disanqiudingweidan", "7"));
        lotteryPlayList2_3.add(new LotteryPlay(356, "第三球定位胆", "8", "8", "", "disanqiudingweidan", "8"));
        lotteryPlayList2_3.add(new LotteryPlay(357, "第三球定位胆", "9", "9", "", "disanqiudingweidan", "9"));

        //--
        LotteryPlayEnd playEnd2_4 = new LotteryPlayEnd();
        playEnd2_4.setTag("第四球");
        playEnd2_4.setRemoteCode("disiqiu");
        playEnd2_4.setSpanCount(4);
        playEnd2_4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_4 = new ArrayList<>();
        playEnd2_4.setLotteryPlays(lotteryPlayList2_4);
        lotteryPlayList2_4.add(new LotteryPlay(364, "第四球定位胆", "0", "0", "", "disiqiudingweidan", "0"));
        lotteryPlayList2_4.add(new LotteryPlay(365, "第四球定位胆", "1", "1", "", "disiqiudingweidan", "1"));
        lotteryPlayList2_4.add(new LotteryPlay(366, "第四球定位胆", "2", "2", "", "disiqiudingweidan", "2"));
        lotteryPlayList2_4.add(new LotteryPlay(367, "第四球定位胆", "3", "3", "", "disiqiudingweidan", "3"));
        lotteryPlayList2_4.add(new LotteryPlay(368, "第四球定位胆", "4", "4", "", "disiqiudingweidan", "4"));
        lotteryPlayList2_4.add(new LotteryPlay(369, "第四球定位胆", "5", "5", "", "disiqiudingweidan", "5"));
        lotteryPlayList2_4.add(new LotteryPlay(370, "第四球定位胆", "6", "6", "", "disiqiudingweidan", "6"));
        lotteryPlayList2_4.add(new LotteryPlay(371, "第四球定位胆", "7", "7", "", "disiqiudingweidan", "7"));
        lotteryPlayList2_4.add(new LotteryPlay(372, "第四球定位胆", "8", "8", "", "disiqiudingweidan", "8"));
        lotteryPlayList2_4.add(new LotteryPlay(373, "第四球定位胆", "9", "9", "", "disiqiudingweidan", "9"));

        //--
        LotteryPlayEnd playEnd2_5 = new LotteryPlayEnd();
        playEnd2_5.setTag("第五球");
        playEnd2_5.setRemoteCode("diwuqiu");
        playEnd2_5.setSpanCount(4);
        playEnd2_5.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList2_5 = new ArrayList<>();
        playEnd2_5.setLotteryPlays(lotteryPlayList2_5);
        lotteryPlayList2_5.add(new LotteryPlay(380, "第五球定位胆", "0", "0", "", "diwuqiudingweidan", "0"));
        lotteryPlayList2_5.add(new LotteryPlay(381, "第五球定位胆", "1", "1", "", "diwuqiudingweidan", "1"));
        lotteryPlayList2_5.add(new LotteryPlay(382, "第五球定位胆", "2", "2", "", "diwuqiudingweidan", "2"));
        lotteryPlayList2_5.add(new LotteryPlay(383, "第五球定位胆", "3", "3", "", "diwuqiudingweidan", "3"));
        lotteryPlayList2_5.add(new LotteryPlay(384, "第五球定位胆", "4", "4", "", "diwuqiudingweidan", "4"));
        lotteryPlayList2_5.add(new LotteryPlay(385, "第五球定位胆", "5", "5", "", "diwuqiudingweidan", "5"));
        lotteryPlayList2_5.add(new LotteryPlay(386, "第五球定位胆", "6", "6", "", "diwuqiudingweidan", "6"));
        lotteryPlayList2_5.add(new LotteryPlay(387, "第五球定位胆", "7", "7", "", "diwuqiudingweidan", "7"));
        lotteryPlayList2_5.add(new LotteryPlay(388, "第五球定位胆", "8", "8", "", "diwuqiudingweidan", "8"));
        lotteryPlayList2_5.add(new LotteryPlay(389, "第五球定位胆", "9", "9", "", "diwuqiudingweidan", "9"));

        //--
        LotteryPlayStart playStart3 = new LotteryPlayStart("前中后三", "qianzhonghousan");
        List<LotteryPlaySub> playSubs3 = new ArrayList<>();
        playStart3.setSubPlays(playSubs3);

        LotteryPlaySub playSub3 = new LotteryPlaySub("前中后三_副", "", "qianzhonghousan", S_ColdHot_Omit);
        List<LotteryPlayEnd> playEndList3 = new ArrayList<>();
        playSub3.setLotteryPlayEnds(playEndList3);

        LotteryPlayEnd playEnd3_1 = new LotteryPlayEnd();
        playEnd3_1.setTag("前三");
        playEnd3_1.setRemoteCode("qiansan");
        playEnd3_1.setSpanCount(4);
        playEnd3_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_1 = new ArrayList<>();
        playEnd3_1.setLotteryPlays(lotteryPlayList3_1);
        lotteryPlayList3_1.add(new LotteryPlay(288, "前三豹子", "豹子", "baozi", "", "qiansanbaozi", "baozi"));
        lotteryPlayList3_1.add(new LotteryPlay(289, "前三顺子", "顺子", "shunzi", "", "qiansanshunzi", "shunzi"));
        lotteryPlayList3_1.add(new LotteryPlay(290, "前三对子", "对子", "duizi", "", "qiansanduizi", "duizi"));
        lotteryPlayList3_1.add(new LotteryPlay(291, "前三半顺", "半顺", "banshun", "", "qiansanbanshun", "banshun"));
        lotteryPlayList3_1.add(new LotteryPlay(292, "前三杂六", "杂六", "zaliu", "", "qiansanzaliu", "zaliu"));

        LotteryPlayEnd playEnd3_2 = new LotteryPlayEnd();
        playEnd3_2.setTag("中三");
        playEnd3_2.setRemoteCode("zhongsan");
        playEnd3_2.setSpanCount(4);
        playEnd3_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_2 = new ArrayList<>();
        playEnd3_2.setLotteryPlays(lotteryPlayList3_2);
        lotteryPlayList3_2.add(new LotteryPlay(293, "中三豹子", "豹子", "baozi", "", "zhongsanbaozi", "baozi"));
        lotteryPlayList3_2.add(new LotteryPlay(294, "中三顺子", "顺子", "shunzi", "", "zhongsanshunzi", "shunzi"));
        lotteryPlayList3_2.add(new LotteryPlay(295, "中三对子", "对子", "duizi", "", "zhongsanduizi", "duizi"));
        lotteryPlayList3_2.add(new LotteryPlay(296, "中三半顺", "半顺", "banshun", "", "zhongsanbanshun", "banshun"));
        lotteryPlayList3_2.add(new LotteryPlay(297, "中三杂六", "杂六", "zaliu", "", "zhongsanzaliu", "zaliu"));

        LotteryPlayEnd playEnd3_3 = new LotteryPlayEnd();
        playEnd3_3.setTag("后三");
        playEnd3_3.setRemoteCode("housan");
        playEnd3_3.setSpanCount(4);
        playEnd3_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_3 = new ArrayList<>();
        playEnd3_3.setLotteryPlays(lotteryPlayList3_3);
        lotteryPlayList3_3.add(new LotteryPlay(298, "后三豹子", "豹子", "baozi", "", "housanbaozi", "baozi"));
        lotteryPlayList3_3.add(new LotteryPlay(299, "后三顺子", "顺子", "shunzi", "", "housanshunzi", "shunzi"));
        lotteryPlayList3_3.add(new LotteryPlay(300, "后三对子", "对子", "duizi", "", "housanduizi", "duizi"));
        lotteryPlayList3_3.add(new LotteryPlay(301, "后三半顺", "半顺", "banshun", "", "housanbanshun", "banshun"));
        lotteryPlayList3_3.add(new LotteryPlay(302, "后三杂六", "杂六", "zaliu", "", "housanzaliu", "zaliu"));


        playEndList1.add(playEnd1);
        playEndList1.add(playEnd2);
        playEndList1.add(playEnd3);
        playEndList1.add(playEnd4);
        playEndList1.add(playEnd5);
        playEndList1.add(playEnd6);
        playEndList2.add(playEnd2_1);
        playEndList2.add(playEnd2_2);
        playEndList2.add(playEnd2_3);
        playEndList2.add(playEnd2_4);
        playEndList2.add(playEnd2_5);
        playEndList3.add(playEnd3_1);
        playEndList3.add(playEnd3_2);
        playEndList3.add(playEnd3_3);

        playSubs1.add(playSub1);
        playSubs2.add(playSub2);
        playSubs3.add(playSub3);


        lotteryPlayStartList.add(playStart1);
        lotteryPlayStartList.add(playStart2);
        lotteryPlayStartList.add(playStart3);

        return lotteryPlayStartList;
    }

    private static List<LotteryPlayStart> createPlay_lhc() {
        String[] name_shengxiao = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        String[] code_shengxiao = new String[]{"shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"};
        String[] name_wuxing = new String[]{"金", "木", "水", "火", "土"};
        String[] code_wuxing_code = new String[]{"jin", "mu", "shui", "huo", "tu"};
        String[] code_wuxing_code_remote_code_up = new String[]{"wuxing_temajin", "wuxing_temamu", "wuxing_temashui", "wuxing_temahuo", "wuxing_tematu"};
        String[] name_lianwei = new String[]{"0尾", "1尾", "2尾", "3尾", "4尾", "5尾", "6尾", "7尾", "8尾", "9尾"};
        List<Integer> name_lianweiId = Arrays.asList(1523, 1524, 1525, 1526, 1527, 1528, 1529, 1530, 1531, 1532);
        String[] code_lianwei = new String[]{"lingwei", "yiwei", "erwei", "sanwei", "siwei", "wuwei", "liuwei", "qiwei", "bawei", "jiuwei"};


        String BIGSMALL_SINGDOUBLE = "大小单双";
        String HEDAXIAO = "合大小";
        String HEDANSHUANG = "合单双";
        String TIANDIXIAO = "天地肖";
        String QIANHOUXIAO = "前后肖";
        String JIAYEXIAO = "家野肖";
        String WEIDAXIAO = "尾大小";
        String TEMAGUOGUAN = "特码过关";

        String SEBO = "色波";
        String BANBO = "半波";
        String BANBANBO = "半半波";

        String TEXIAO = "特肖";
        String TEMAWEISHU = "特码尾数";
        String TEMATOUSHU = "特码头数";

        String ZHENGMA_1 = "正码一";
        String ZHENGMA_2 = "正码二";
        String ZHENGMA_3 = "正码三";
        String ZHENGMA_4 = "正码四";
        String ZHENGMA_5 = "正码五";
        String ZHENGMA_6 = "正码六";


        String ZHENGMA_ZHENGXIAO = "正肖";
        String ZHENGMA_QISEBO = "七色波";

        // 特码
        String[] levelOne = {"特码", "正码", "正码特", "连肖连尾", "连码", "一肖总肖平特尾数", "总和", "自选不中"};
        String[] levelOne_remoteCode = {"tema", "zhengma", "zhengmate", "lianxiaolianwei", "lianma", "yixiaozongxiaopingteweishu", "zonghe", "zixuanbuzhong"};

        String[] leveTwo_tema = {"双面", "号码", "色波半波", "特肖头尾数", "合肖", "五行"};
        String[] leveTwo_tema_remoteCode = {"liangmian", "haoma", "sebobanbo", "texiaotouweixiao", "hexiao", "wuxing"};
        String[] leveTwo_tema_chmLayout = {S_ColdHot, S_ColdHot_Omit, S_ColdHot, S_ColdHot_Omit, S_ColdHot_Omit_Odds_One, S_ColdHot_Omit};
        String[] leveTwo_zhengma = {"正码任选一", "正码1-6", "正肖七色波"};
        String[] leveTwo_zhengma_code = {"zhengmarenxuanyi", "zhengxiaoyidaoliu", "zhengxiaoqisebo"};
        String[] leveTwo_zhengma_chmLayout = {S_ColdHot_Omit, S_ColdHot, S_ColdHot_Omit};
        String[] leveTwo_zhengma_te = {"正一特", "正二特", "正三特", "正四特", "正五特", "正六特"};
        String[] leveTwo_zhengma_te_code = {"zhengyite", "zhengerte", "zhengsante", "zhengsite", "zhengwute", "zhengliute"};
        String[] leveTwo_lianxiao_lianwei = {"二连肖", "三连肖", "四连肖", "五连肖", "二连尾", "三连尾", "四连尾", "五连尾"};
        String[] leveTwo_lianxiao_lianwei_code = {"erlianxiao", "sanlianxiao", "silianxiao", "wulianxiao", "erlianwei", "sanlianwei", "silianwei", "wulianwei"};
        String[] leveTwo_lianma = {"三中二", "三全中", "二全中", "二中特", "特串", "四全中"};
        String[] leveTwo_lianma_code = {"sanzhonger", "sanquanzhong", "erquanzhong", "erzhongte", "techuang", "siquanzhong"};
        String[] leveTwo_lianma_chmLayout = {S_ColdHot_Omit_Odds_Two, S_ColdHot_Omit_Odds_One, S_ColdHot_Omit_Odds_One, S_ColdHot_Omit_Odds_Two, S_ColdHot_Omit_Odds_One, S_ColdHot_Omit_Odds_One};
        String[] leveTwo_yi_xiao_zong_ping = {"一肖", "总肖", "尾数"};
        String[] leveTwo_yi_xiao_zong_ping_code = {"yixiao", "zongxiao", "weishu"};
        String[] leveTwo_yi_xiao_zong_ping_chmLayout = {S_ColdHot_Omit, S_ColdHot, S_ColdHot_Omit};

        String[] leveThree_shuangmian = {BIGSMALL_SINGDOUBLE, HEDAXIAO, HEDANSHUANG, TIANDIXIAO, QIANHOUXIAO, JIAYEXIAO, WEIDAXIAO, TEMAGUOGUAN};
        String[] leveThree_sebobanbo = {SEBO, BANBO, BANBANBO};
        String[] leveThree_texiao_touweishu = {TEXIAO, TEMAWEISHU, TEMATOUSHU};
        String[] leveThree_zhengma_16 = {ZHENGMA_1, ZHENGMA_2, ZHENGMA_3, ZHENGMA_4, ZHENGMA_5, ZHENGMA_6};
        String[] leveThree_zhengma_zhengxiaoqisebo = {ZHENGMA_ZHENGXIAO, ZHENGMA_QISEBO};


        /*三级  特码*/
        List<List<LotteryPlayEnd>> playEndList_tema = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_shuangmain = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_haoma = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_sebobanbo = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_texiaotouweishu = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_hexiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_tema_wuxing = new ArrayList<>();

        playEndList_tema.add(playEndList_tema_shuangmain);
        playEndList_tema.add(playEndList_tema_haoma);
        playEndList_tema.add(playEndList_tema_sebobanbo);
        playEndList_tema.add(playEndList_tema_texiaotouweishu);
        playEndList_tema.add(playEndList_tema_hexiao);
        playEndList_tema.add(playEndList_tema_wuxing);

        /*三级正码*/
        List<List<LotteryPlayEnd>> playEndList_zhengma = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhegma_renxuanyi = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengma_zhengma16 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengma_zhengxiaoqisebo = new ArrayList<>();
        playEndList_zhengma.add(playEndList_zhegma_renxuanyi);
        playEndList_zhengma.add(playEndList_zhengma_zhengma16);
        playEndList_zhengma.add(playEndList_zhengma_zhengxiaoqisebo);



        /*三级 正码特*/
        List<List<LotteryPlayEnd>> playEndList_zhengma_te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng1te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng2te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng3te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng4te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng5te = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zhengmate_zheng6te = new ArrayList<>();
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng1te);
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng2te);
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng3te);
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng4te);
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng5te);
        playEndList_zhengma_te.add(playEndList_zhengmate_zheng6te);


        List<List<LotteryPlayEnd>> playEndList_lianxiaolianwei = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_2lianxiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_3lianxiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_4lianxiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_5lianxiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_2lianwei = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_3lianwei = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_4lianwei = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianxiaolianwei_5lianwei = new ArrayList<>();
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_2lianxiao);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_3lianxiao);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_4lianxiao);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_5lianxiao);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_2lianwei);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_3lianwei);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_4lianwei);
        playEndList_lianxiaolianwei.add(playEndList_lianxiaolianwei_5lianwei);

        List<List<LotteryPlayEnd>> playEndList_lianma = new ArrayList<>();

        List<LotteryPlayEnd> playEndList_lianma_3zhong2 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianma_3quanzhong = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianma_2quanzhong = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianma_2zhongte = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianma_techuan = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_lianma_4quanzhong = new ArrayList<>();

        playEndList_lianma.add(playEndList_lianma_3zhong2);
        playEndList_lianma.add(playEndList_lianma_3quanzhong);
        playEndList_lianma.add(playEndList_lianma_2quanzhong);
        playEndList_lianma.add(playEndList_lianma_2zhongte);
        playEndList_lianma.add(playEndList_lianma_techuan);
        playEndList_lianma.add(playEndList_lianma_4quanzhong);


        List<List<LotteryPlayEnd>> playEndList_yixiaozongping = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_yixiaozongping_yixiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_yixiaozongping_zongxiao = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_yixiaozongping_weishu = new ArrayList<>();
        playEndList_yixiaozongping.add(playEndList_yixiaozongping_yixiao);
        playEndList_yixiaozongping.add(playEndList_yixiaozongping_zongxiao);
        playEndList_yixiaozongping.add(playEndList_yixiaozongping_weishu);
        List<LotteryPlayEnd> playEndList_zonghe = new ArrayList<>();
        List<LotteryPlayEnd> playEndList_zexuan_buzhong = new ArrayList<>();


        /*三级 和四级 特码双面*/
        for (int x = 0; x < leveThree_shuangmian.length; x++) {
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = leveThree_shuangmian[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setCanExpand(true);
            if (title.equals(BIGSMALL_SINGDOUBLE)) {
                lotteryPlayEnd.setSpanCount(4);
                LotteryPlay lotteryPlay1 = new LotteryPlay(914, "特码大小", "大", "da", 914, "temadaxiao", "da");
                LotteryPlay lotteryPlay2 = new LotteryPlay(915, "特码大小", "小", "xiao", 915, "temadaxiao", "xiao");
                LotteryPlay lotteryPlay3 = new LotteryPlay(916, "特码单双", "单", "dan", 916, "temadanshuang", "dan");
                LotteryPlay lotteryPlay4 = new LotteryPlay(917, "特码单双", "双", "shuang", 917, "temadanshuang", "shuang");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
                lotteryPlays.add(lotteryPlay3);
                lotteryPlays.add(lotteryPlay4);
            } else if (title.equals(HEDAXIAO)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(918, "特码合大小", "合大", "heda", 918, "temahedaxiao", "heda");
                LotteryPlay lotteryPlay2 = new LotteryPlay(919, "特码合大小", "合小", "hexiao", 919, "temahedaxiao", "hexiao");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else if (title.equals(HEDANSHUANG)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(920, "特码合单双", "合单", "hedan", 920, "temahedanshuang", "hedan");
                LotteryPlay lotteryPlay2 = new LotteryPlay(921, "特码合单双", "合双", "heshuang", 921, "temahedanshuang", "heshuang");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else if (title.equals(TIANDIXIAO)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(922, "特码天地肖", "天肖", "tianxiao", 922, "tematiandixiao", "tianxiao");
                LotteryPlay lotteryPlay2 = new LotteryPlay(923, "特码天地肖", "地肖", "dixiao", 923, "tematiandixiao", "dixiao");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else if (title.equals(QIANHOUXIAO)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(924, "特码前后肖", "前肖", "qianxiao", 924, "temaqianhouxiao", "qianxiao");
                LotteryPlay lotteryPlay2 = new LotteryPlay(925, "特码前后肖", "后肖", "houxiao", 925, "temaqianhouxiao", "houxiao");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else if (title.equals(JIAYEXIAO)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(926, "特码家野肖", "家肖", "jiaxiao", 926, "temajiayexiao", "jiaxiao");
                LotteryPlay lotteryPlay2 = new LotteryPlay(927, "特码家野肖", "野肖", "yexiao", 927, "temajiayexiao", "yexiao");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else if (title.equals(WEIDAXIAO)) {
                lotteryPlayEnd.setSpanCount(2);
                LotteryPlay lotteryPlay1 = new LotteryPlay(928, "特码尾大小", "尾大", "weida", 928, "temaweidaxiao", "weida");
                LotteryPlay lotteryPlay2 = new LotteryPlay(929, "特码尾大小", "尾小", "weixiao", 929, "temaweidaxiao", "weixiao");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
            } else {
                /*特码过关*/
                lotteryPlayEnd.setSpanCount(4);
                LotteryPlay lotteryPlay1 = new LotteryPlay(930, "特码大小单双", "大单", "dadan", "", "temadaxiaodanshuang", "dadan");
                LotteryPlay lotteryPlay2 = new LotteryPlay(931, "特码大小单双", "小单", "xiaodan", "", "temadaxiaodanshuang", "xiaodan");
                LotteryPlay lotteryPlay3 = new LotteryPlay(932, "特码大小单双", "大双", "dashuang", "", "temadaxiaodanshuang", "dashuang");
                LotteryPlay lotteryPlay4 = new LotteryPlay(933, "特码大小单双", "小双", "xiaoshuang", "", "temadaxiaodanshuang", "xiaoshuang");
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
                lotteryPlays.add(lotteryPlay3);
                lotteryPlays.add(lotteryPlay4);
            }
            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            playEndList_tema_shuangmain.add(lotteryPlayEnd);
        }

        /*三级 特码 号码*/
        List<Integer> hoama_id = Arrays.asList(934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982);
        LotteryPlayEnd lotteryPlayEnd_hoama = new LotteryPlayEnd();
        lotteryPlayEnd_hoama.setTag("");
        lotteryPlayEnd_hoama.setSpanCount(4);
        lotteryPlayEnd_hoama.setCanExpand(false);
        lotteryPlayEnd_hoama.setDivider(2);
        List<LotteryPlay> lotteryPlays_haoma = new ArrayList<>();
        for (int x = 1; x < 50; x++) {
            LotteryPlay lotteryPlay = new LotteryPlay(hoama_id.get(x - 1), "特码定位胆", String.format("%02d", x), String.format("%02d", x), "", "temadingweidan", String.format("%02d", x), 3);
            lotteryPlays_haoma.add(lotteryPlay);
        }
        lotteryPlayEnd_hoama.setLotteryPlays(lotteryPlays_haoma);
        playEndList_tema_haoma.add(lotteryPlayEnd_hoama);


        /*三级 特码色波半波*/
        List<ArrayList<Integer>> leveThree_sebobanbo_id = new ArrayList<>();
        leveThree_sebobanbo_id.add(new ArrayList<>(Arrays.asList(983, 984, 985)));
        leveThree_sebobanbo_id.add(new ArrayList<>(Arrays.asList(986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997)));
        leveThree_sebobanbo_id.add(new ArrayList<>(Arrays.asList(998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009)));

        for (int x = 0; x < leveThree_sebobanbo.length; x++) {
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = leveThree_sebobanbo[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setCanExpand(true);
            ArrayList<Integer> sebobanbo_id = leveThree_sebobanbo_id.get(x);
            if (title.equals(SEBO)) {
                lotteryPlayEnd.setSpanCount(3);
                LotteryPlay lotteryPlay1 = new LotteryPlay(sebobanbo_id.get(0), "特码波色", "红", "hongbo", "", "bose_temahongbo", "hongbo", 0);
                LotteryPlay lotteryPlay2 = new LotteryPlay(sebobanbo_id.get(1), "特码波色", "蓝", "lanbo", "", "bose_temalanbo", "lanbo", 0);
                LotteryPlay lotteryPlay3 = new LotteryPlay(sebobanbo_id.get(2), "特码波色", "绿", "lvbo", "", "bose_temalvbo", "lvbo", 0);
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
                lotteryPlays.add(lotteryPlay3);
            } else if (title.equals(BANBO)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] name = new String[]{"红单", "蓝单", "绿单", "红双", "蓝双", "绿双", "红大", "蓝大", "绿大", "红小", "蓝小", "绿小"};
                String[] code = new String[]{"hongdan", "landan", "lvdan", "hongshuang", "lanshuang", "lvshuang", "hongda", "landa", "lvda", "hongxiao", "lanxiao", "lvxiao"};
                String[] code_remoteUp = new String[]{"banbo_temahongdan", "banbo_temalandan", "banbo_temalvdan", "banbo_temahongshuang", "banbo_temalanshuang",
                        "banbo_temalvshuang", "banbo_temahongda", "banbo_temalanda", "banbo_temalvda", "banbo_temahongxiao", "banbo_temalanxiao", "banbo_temalvxiao"};
                String[] odds = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(sebobanbo_id.get(y), "特码半波", name[y], code[y], odds[y], code_remoteUp[y], code[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }

            } else if (title.equals(BANBANBO)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] name = new String[]{"红大单", "蓝大单", "绿大单", "红大双", "蓝大双", "绿大双", "红小单", "蓝小单", "绿小单", "红小双", "蓝小双", "绿小双"};
                String[] code = new String[]{"hongdadan", "landadan", "lvdadan", "hongdashuang", "landashuang", "lvdashuang", "hongxiaodan",
                        "lanxiaodan", "lvxiaodan", "hongxiaoshuang", "lanxiaoshuang", "lvxiaoshuang"};
                String[] code_remoteUp = new String[]{"banbanbo_temahongdadan", "banbanbo_temalandadan", "banbanbo_temalvdadan", "banbanbo_temahongdashuang",
                        "banbanbo_temalandashuang", "banbanbo_temalvdashuang", "banbanbo_temahongxiaodan", "banbanbo_temalanxiaodan", "banbanbo_temalvxiaodan",
                        "banbanbo_temahongxiaoshuang", "banbanbo_temalanxiaoshuang", "banbanbo_temalvxiaoshuang"};
                String[] odds = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(sebobanbo_id.get(y), "特码半半波", name[y], code[y], odds[y], code_remoteUp[y], code[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            }
            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            playEndList_tema_sebobanbo.add(lotteryPlayEnd);
        }

        /*三级 特码头尾数*/
        List<ArrayList<Integer>> texiao_touweishu_id = new ArrayList<>();
        texiao_touweishu_id.add(new ArrayList<>(Arrays.asList(1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021)));
        texiao_touweishu_id.add(new ArrayList<>(Arrays.asList(1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031)));
        texiao_touweishu_id.add(new ArrayList<>(Arrays.asList(1032, 1033, 1034, 1035, 1036)));
        for (int x = 0; x < leveThree_texiao_touweishu.length; x++) {
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = leveThree_texiao_touweishu[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setCanExpand(true);
            ArrayList<Integer> touweishu_id = texiao_touweishu_id.get(x);
            if (title.equals(TEXIAO)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] odds = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_shengxiao.length; y++) {
                    String remoteCodeUp = "texiao_tematexiaofbmn";
                    if (0 == y) {
                        remoteCodeUp = "texiao_tematexiaobmn";
                    } else {
                        remoteCodeUp = "texiao_tematexiaofbmn";
                    }
                    LotteryPlay lotteryPlay = new LotteryPlay(touweishu_id.get(y), "特码特肖", name_shengxiao[y], code_shengxiao[y], odds[y],
                            remoteCodeUp, code_shengxiao[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            } else if (title.equals(TEMAWEISHU)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] odds = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_lianwei.length; y++) {
                    String remoteCodeUp = "touweishu_weishu_temayitojiuwei";
                    if (0 == y) {
                        remoteCodeUp = "touweishu_weishu_temalingwei";
                    } else {
                        remoteCodeUp = "touweishu_weishu_temayitojiuwei";
                    }
                    LotteryPlay lotteryPlay = new LotteryPlay(touweishu_id.get(y), "特码尾数", name_lianwei[y], code_lianwei[y], odds[y], remoteCodeUp, code_lianwei[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }

            } else if (title.equals(TEMATOUSHU)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] name = new String[]{"0头", "1头", "2头", "3头", "4头"};
                String[] code = new String[]{"lingtou", "yitou", "ertou", "santou", "sitou"};
                String[] odds = new String[]{"", "", "", "", ""};
                for (int y = 0; y < name.length; y++) {
                    String remoteCodeUp = "touweishu_toushu_temayitositou";
                    if (0 == y) {
                        remoteCodeUp = "touweishu_toushu_temalingtou";
                    } else {
                        remoteCodeUp = "touweishu_toushu_temayitositou";
                    }
                    LotteryPlay lotteryPlay = new LotteryPlay(touweishu_id.get(y), "特码头数", name[y], code[y], odds[y], remoteCodeUp, code[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            }
            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            playEndList_tema_texiaotouweishu.add(lotteryPlayEnd);
        }

        /*三级 特码 合肖*/
        List<Integer> name_hexiao_shengxiao_id = new ArrayList<>(Arrays.asList(1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046));
        LotteryPlayEnd lotteryPlayEnd_hexiao = new LotteryPlayEnd();
        lotteryPlayEnd_hexiao.setTag("");
        lotteryPlayEnd_hexiao.setDivider(2);
        lotteryPlayEnd_hexiao.setCanExpand(false);
        lotteryPlayEnd_hexiao.setSpanCount(3);
        String[] remoteCode = new String[]{"erxiao", "sanxiao", "sixiao", "wuxiao", "liuxiao", "qixiao", "baxiao", "jiuxiao"};
        List<LotteryPlay> lotteryPlays_hexiao = new ArrayList<>();
        for (int y = 0; y < name_shengxiao.length; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(1037, "特码合肖", name_shengxiao[y], code_shengxiao[y], "", "temahexiao", "erxiao", 0);
            lotteryPlays_hexiao.add(lotteryPlay);
        }
        lotteryPlayEnd_hexiao.setLotteryPlays(lotteryPlays_hexiao);
        playEndList_tema_hexiao.add(lotteryPlayEnd_hexiao);


        /*三级 特码 五行*/
        LotteryPlayEnd lotteryPlayEnd_wuxing = new LotteryPlayEnd();
        lotteryPlayEnd_wuxing.setTag("");
        lotteryPlayEnd_wuxing.setDivider(2);
        lotteryPlayEnd_wuxing.setCanExpand(false);
        lotteryPlayEnd_wuxing.setSpanCount(2);
        List<Integer> wuxing_id = Arrays.asList(1047, 1048, 1049, 1050, 1051);
        List<LotteryPlay> lotteryPlays_wuxing = new ArrayList<>();
        String[] odds = new String[]{"", "", "", "", ""};
        for (int y = 0; y < name_wuxing.length; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(wuxing_id.get(y), "特码五行", name_wuxing[y], code_wuxing_code[y], odds[y],
                    code_wuxing_code_remote_code_up[y], code_wuxing_code[y], 0);
            lotteryPlays_wuxing.add(lotteryPlay);
        }
        lotteryPlayEnd_wuxing.setLotteryPlays(lotteryPlays_wuxing);
        playEndList_tema_wuxing.add(lotteryPlayEnd_wuxing);



        /*三级 正码 正码任选一*/
        List<Integer> zhengma_renyuanyi_id = Arrays.asList(1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100);
        LotteryPlayEnd lotteryPlayEnd_zhengmarenxuanyi = new LotteryPlayEnd();
        lotteryPlayEnd_zhengmarenxuanyi.setTag("");
        lotteryPlayEnd_zhengmarenxuanyi.setDivider(2);
        lotteryPlayEnd_zhengmarenxuanyi.setCanExpand(false);
        lotteryPlayEnd_zhengmarenxuanyi.setSpanCount(4);
        List<LotteryPlay> lotteryPlays_zhengma_renyuanyi = new ArrayList<>();
        for (int y = 1; y < 50; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(zhengma_renyuanyi_id.get(y - 1), "正码任选一", String.format("%02d", y), String.format("%02d", y), "", "zhengmarenxuanyi", String.format("%02d", y), 3);
            lotteryPlays_zhengma_renyuanyi.add(lotteryPlay);
        }
        lotteryPlayEnd_zhengmarenxuanyi.setLotteryPlays(lotteryPlays_zhengma_renyuanyi);
        playEndList_zhegma_renxuanyi.add(lotteryPlayEnd_zhengmarenxuanyi);

        /*三级 正码1-6*/
        List<ArrayList<Integer>> name_zhengma_id = new ArrayList<ArrayList<Integer>>();
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113)));
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1126)));
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1127, 1128, 1129, 1130, 1131, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139)));
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152)));
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1153, 1154, 1155, 1156, 1157, 1158, 1159, 1160, 1161, 1162, 1163, 1164, 1165)));
        name_zhengma_id.add(new ArrayList<>(Arrays.asList(1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178)));


        String[] remoteCodeUp1 = new String[]{"zhengmayi_zhengmayidanshuang", "zhengmayi_zhengmayidanshuang", "zhengmayi_zhengmayidaxiao", "zhengmayi_zhengmayidaxiao",
                "zhengmayi_zhengmayihedanshuang", "zhengmayi_zhengmayihedanshuang", "zhengmayi_zhengmayihedaxiao", "zhengmayi_zhengmayihedaxiao",
                "zhengmayi_zhengmayiweidaxiao", "zhengmayi_zhengmayiweidaxiao", "zhengmayi_zhengmayihongbo", "zhengmayi_zhengmayilanbo", "zhengmayi_zhengmayilvbo"};

        String[] remoteCodeUp2 = new String[]{"zhengmaer_zhengmaerdanshuang", "zhengmaer_zhengmaerdanshuang", "zhengmaer_zhengmaerdaxiao", "zhengmaer_zhengmaerdaxiao",
                "zhengmaer_zhengmaerhedangshuang", "zhengmaer_zhengmaerhedangshuang", "zhengmaer_zhengmaerhedaxiao", "zhengmaer_zhengmaerhedaxiao",
                "zhengmaer_zhengmaerweidaxiao", "zhengmaer_zhengmaerweidaxiao", "zhengmaer_zhengmaerhongbo", "zhengmaer_zhengmaerlanbo", "zhengmaer_zhengmaerlvbo"};

        String[] remoteCodeUp3 = new String[]{"zhengmasan_zhengmasandanshuang", "zhengmasan_zhengmasandanshuang", "zhengmasan_zhengmasandaxiao", "zhengmasan_zhengmasandaxiao",
                "zhengmasan_zhengmasanhedanshuang", "zhengmasan_zhengmasanhedanshuang", "zhengmasan_zhengmasanhedaxiao", "zhengmasan_zhengmasanhedaxiao",
                "zhengmasan_zhengmasanweidaxiao", "zhengmasan_zhengmasanweidaxiao", "zhengmasan_zhengmasanhongbo", "zhengmasan_zhengmasanlanbo", "zhengmasan_zhengmasanlvbo"};

        String[] remoteCodeUp4 = new String[]{"zhengmasi_zhengmasidanshuang", "zhengmasi_zhengmasidanshuang", "zhengmasi_zhengmasidaxiao", "zhengmasi_zhengmasidaxiao",
                "zhengmasi_zhengmasihedanshuang", "zhengmasi_zhengmasihedanshuang", "zhengmasi_zhengmasihedaxiao", "zhengmasi_zhengmasihedaxiao",
                "zhengmasi_zhengmasiweidaxiao", "zhengmasi_zhengmasiweidaxiao", "zhengmasi_zhengmasihongbo", "zhengmasi_zhengmasilanbo", "zhengmasi_zhengmasilvbo"};

        String[] remoteCodeUp5 = new String[]{"zhengmawu_zhengmawudanshuang", "zhengmawu_zhengmawudanshuang", "zhengmawu_zhengmawudaxiao", "zhengmawu_zhengmawudaxiao",
                "zhengmawu_zhengmawuhedanshuang", "zhengmawu_zhengmawuhedanshuang", "zhengmawu_zhengmawuhedaxiao", "zhengmawu_zhengmawuhedaxiao",
                "zhengmawu_zhengmawuweidaxiao", "zhengmawu_zhengmawuweidaxiao", "zhengmawu_zhengmawuhongbo", "zhengmawu_zhengmawulanbo", "zhengmawu_zhengmawulvbo"};

        String[] remoteCodeUp6 = new String[]{"zhengmaliu_zhengmaliudanshuang", "zhengmaliu_zhengmaliudanshuang", "zhengmaliu_zhengmaliudaxiao", "zhengmaliu_zhengmaliudaxiao",
                "zhengmaliu_zhengmaliuhedanshuang", "zhengmaliu_zhengmaliuhedanshuang", "zhengmaliu_zhengmaliuhedaxiao", "zhengmaliu_zhengmaliuhedaxiao",
                "zhengmaliu_zhengmaliuweidaxiao", "zhengmaliu_zhengmaliuweidaxiao", "zhengmaliu_zhengmaliuhongbo", "zhengmaliu_zhengmaliulanbo", "zhengmaliu_zhengmaliulvbo"};

        List<String[]> remoteCodeUp1_6 = new ArrayList<>();
        remoteCodeUp1_6.add(remoteCodeUp1);
        remoteCodeUp1_6.add(remoteCodeUp2);
        remoteCodeUp1_6.add(remoteCodeUp3);
        remoteCodeUp1_6.add(remoteCodeUp4);
        remoteCodeUp1_6.add(remoteCodeUp5);
        remoteCodeUp1_6.add(remoteCodeUp6);

        for (int x = 0; x < leveThree_zhengma_16.length; x++) {
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = leveThree_zhengma_16[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setCanExpand(true);
            lotteryPlayEnd.setSpanCount(4);

            ArrayList<Integer> leveThree_zhengma_id = name_zhengma_id.get(x);
            String[] name = new String[]{"单", "双", "大", "小", "合单", "合双", "合大", "合小", "尾大", "尾小", "红波", "绿波", "蓝波"};
            String[] code = new String[]{"dan", "shuang", "da", "xiao", "hedan", "heshuang", "heda", "hexiao", "weida", "weixiao", "hongbo", "lvbo", "lanbo"};
            String[] odds_leveThree = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", ""};

            String[] remoteCodeUpArr = remoteCodeUp1_6.get(x);
            for (int y = 0; y < name.length; y++) {
                String tag = "";
                String s = name[y];
                switch (s) {
                    case "单":
                    case "双":
                        tag = title + "单双";
                        break;
                    case "大":
                    case "小":
                        tag = title + "大小";
                        break;
                    case "合单":
                    case "合双":
                        tag = title + "合单双";
                        break;
                    case "合大":
                    case "合小":
                        tag = title + "合大小";
                        break;
                    case "尾大":
                    case "尾小":
                        tag = title + "尾大小";
                        break;
                    case "红波":
                    case "绿波":
                    case "蓝波":
                        tag = title + "波色";
                        break;
                    default:
                        break;
                }
                LotteryPlay lotteryPlay = new LotteryPlay(leveThree_zhengma_id.get(y), tag, name[y], code[y], leveThree_zhengma_id.get(y), remoteCodeUpArr[y], code[y]);
                lotteryPlays.add(lotteryPlay);
            }

            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            playEndList_zhengma_zhengma16.add(lotteryPlayEnd);
        }

        /*三级 正肖七色波*/
        List<Integer> name_qisebo_shengxiao_id = Arrays.asList(1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190);
        List<Integer> name_qisebo_id = Arrays.asList(1191, 1192, 1193, 1194);
        for (int x = 0; x < leveThree_zhengma_zhengxiaoqisebo.length; x++) {
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = leveThree_zhengma_zhengxiaoqisebo[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setCanExpand(true);

            if (title.equals(ZHENGMA_ZHENGXIAO)) {
                lotteryPlayEnd.setSpanCount(4);
                String[] odds_qisebo = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_shengxiao.length; y++) {
                    String remoteCodeUp = "zhengxiao_zhengxiaofbm";
                    if (0 == y) {
                        remoteCodeUp = "zhengxiao_zhengxiaobm";
                    } else {
                        remoteCodeUp = "zhengxiao_zhengxiaofbm";
                    }
                    LotteryPlay lotteryPlay = new LotteryPlay(name_qisebo_shengxiao_id.get(y), "正肖", name_shengxiao[y], code_shengxiao[y], odds_qisebo[y],
                            remoteCodeUp, code_shengxiao[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }

            } else {
                lotteryPlayEnd.setSpanCount(2);
                String[] name = new String[]{"红波", "蓝波", "绿波", "和局"};
                String[] code = new String[]{"hongbo", "lanbo", "lvbo", "heju"};
                String[] remoteCodeUp = new String[]{"qisebo_zhengtemahongbo", "qisebo_zhengtemalanbo", "qisebo_zhengtemalvbo", "qisebo_zhengtemaheju"};
                String[] odds_qisebo = new String[]{"", "", "", ""};
                for (int y = 0; y < name.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(name_qisebo_id.get(y), "七色波", name[y], code[y], odds_qisebo[y], remoteCodeUp[y], code[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            }

            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            playEndList_zhengma_zhengxiaoqisebo.add(lotteryPlayEnd);
        }

        /*三级 正码 正特1-6 */
        List<ArrayList<Integer>> leveTwo_zhengma_te_id = new ArrayList<>();
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207, 1208, 1209, 1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243)));
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1288, 1289, 1290, 1291, 1292)));
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341)));
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390)));
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399, 1400, 1401, 1402, 1403, 1404, 1405, 1406, 1407, 1408, 1409, 1410, 1411, 1412, 1413, 1414, 1415, 1416, 1417, 1418, 1419, 1420, 1421, 1422, 1423, 1424, 1425, 1426, 1427, 1428, 1429, 1430, 1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439)));
        leveTwo_zhengma_te_id.add(new ArrayList<>(Arrays.asList(1440, 1441, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1451, 1452, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1465, 1466, 1467, 1468, 1469, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479, 1480, 1481, 1482, 1483, 1484, 1485, 1486, 1487, 1488)));
        String[] leveTwo_zhengma_te_code_remoteCodeUp = {"zhengmayidingweidan", "zhengmaerdingweidan", "zhengmasandingweidan",
                "zhengmasidingweidan", "zhengmawudingweidan", "zhengmaliudingweidan"};
        for (int x = 0; x < leveTwo_zhengma_te.length; x++) {
            LotteryPlayEnd lotteryPlayEnd_zhengmate_zhengte = new LotteryPlayEnd();
            lotteryPlayEnd_zhengmate_zhengte.setTag("");
            lotteryPlayEnd_zhengmate_zhengte.setDivider(2);
            lotteryPlayEnd_zhengmate_zhengte.setCanExpand(false);
            lotteryPlayEnd_zhengmate_zhengte.setSpanCount(4);
            List<LotteryPlay> plays_zhengte1_6 = createNumLhc(leveTwo_zhengma_te_id.get(x), leveTwo_zhengma_te[x], "", 3, true, true,
                    leveTwo_zhengma_te_code_remoteCodeUp[x]);
            lotteryPlayEnd_zhengmate_zhengte.setLotteryPlays(plays_zhengte1_6);
            playEndList_zhengma_te.get(x).add(lotteryPlayEnd_zhengmate_zhengte);

        }

        /*连肖连尾*/
        List<Integer> leveTwo_lianxiao_id = Arrays.asList(1489, 1490, 1491, 1492, 1493, 1494, 1495, 1496);

        String[] remoteCodeUp_lx_2 = new String[]{"zhengtemaerlianxiaohb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb",
                "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb",
                "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb", "zhengtemaerlianxiaobhb"};
        String[] remoteCode_lx_2 = new String[]{"renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao",
                "renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao", "renxuanerxiao"};

        String[] remoteCodeUp_lx_3 = new String[]{"zhengtemasanlianxiaohb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb",
                "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb",
                "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb", "zhengtemasanlianxiaobhb"};
        String[] remoteCode_lx_3 = new String[]{"rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao",
                "rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao", "rexuansanxiao"};

        String[] remoteCodeUp_lx_4 = new String[]{"zhengtemasilianxiaohb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb",
                "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb",
                "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb", "zhengtemasilianxiaobhb"};
        String[] remoteCode_lx_4 = new String[]{"renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao",
                "renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao", "renxuansixiao"};

        String[] remoteCodeUp_lx_5 = new String[]{"zhengtemawulianxiaohb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb",
                "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb",
                "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb", "zhengtemawulianxiaobhb"};
        String[] remoteCode_lx_5 = new String[]{"renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao",
                "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao", "renxuanwuxiao"};

        String[] remoteCodeUp_wx_2 = new String[]{"zhengtemaerlianweihl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl",
                "zhengtemaerlianweibhl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl",
                "zhengtemaerlianweibhl", "zhengtemaerlianweibhl", "zhengtemaerlianweibhl"};
        String[] remoteCode_wx_2 = new String[]{"renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei",
                "renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei", "renxuanerwei"};

        String[] remoteCodeUp_wx_3 = new String[]{"zhengtemasanlianweihl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl",
                "zhengtemasanlianweibhl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl",
                "zhengtemasanlianweibhl", "zhengtemasanlianweibhl", "zhengtemasanlianweibhl"};
        String[] remoteCode_wx_3 = new String[]{"renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei",
                "renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei", "renxuansanwei"};

        String[] remoteCodeUp_wx_4 = new String[]{"zhengtemasilianweihl", "zhengtemasilianweibhl", "zhengtemasilianweibhl", "zhengtemasilianweibhl",
                "zhengtemasilianweibhl", "zhengtemasilianweibhl", "zhengtemasilianweibhl", "zhengtemasilianweibhl", "zhengtemasilianweibhl",
                "zhengtemasilianweibhl", "zhengtemasilianweibhl", "zhengtemasilianweibhl"};
        String[] remoteCode_wx_4 = new String[]{"renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei",
                "renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei", "renxuansiwei"};

        String[] remoteCodeUp_wx_5 = new String[]{"zhengtemawulianweihl", "zhengtemawulianweibhl", "zhengtemawulianweibhl", "zhengtemawulianweibhl",
                "zhengtemawulianweibhl", "zhengtemawulianweibhl", "zhengtemawulianweibhl", "zhengtemawulianweibhl", "zhengtemawulianweibhl",
                "zhengtemawulianweibhl", "zhengtemawulianweibhl", "zhengtemawulianweibhl"};
        String[] remoteCode_wx_5 = new String[]{"renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei",
                "renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei", "renxuanwuwei"};

        List<String[]> remoteCodeUp_lx_wx_arr = new ArrayList<>();
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_lx_2);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_lx_3);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_lx_4);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_lx_5);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_wx_2);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_wx_3);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_wx_4);
        remoteCodeUp_lx_wx_arr.add(remoteCodeUp_wx_5);

        List<String[]> remoteCode_lx_wx_arr = new ArrayList<>();
        remoteCode_lx_wx_arr.add(remoteCode_lx_2);
        remoteCode_lx_wx_arr.add(remoteCode_lx_3);
        remoteCode_lx_wx_arr.add(remoteCode_lx_4);
        remoteCode_lx_wx_arr.add(remoteCode_lx_5);
        remoteCode_lx_wx_arr.add(remoteCode_wx_2);
        remoteCode_lx_wx_arr.add(remoteCode_wx_3);
        remoteCode_lx_wx_arr.add(remoteCode_wx_4);
        remoteCode_lx_wx_arr.add(remoteCode_wx_5);


        for (int x = 0; x < leveTwo_lianxiao_lianwei.length; x++) {
            LotteryPlayEnd lotteryPlayEnd_lianxiaolianwei = new LotteryPlayEnd();
            List<LotteryPlay> lotteryPlays = new ArrayList<>();

            lotteryPlayEnd_lianxiaolianwei.setTag("");
            lotteryPlayEnd_lianxiaolianwei.setDivider(2);
            lotteryPlayEnd_lianxiaolianwei.setCanExpand(false);
            lotteryPlayEnd_lianxiaolianwei.setSpanCount(3);
            lotteryPlayEnd_lianxiaolianwei.setLotteryPlays(lotteryPlays);
            String[] remoteCodeUp_lx_wx = remoteCodeUp_lx_wx_arr.get(x);
            String[] remoteCode_lx_wx = remoteCode_lx_wx_arr.get(x);
            if (x < 4) {
                /*连肖*/
                String[] odds_leveTwo = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_shengxiao.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(leveTwo_lianxiao_id.get(x), leveTwo_lianxiao_lianwei[x], name_shengxiao[y], code_shengxiao[y], odds_leveTwo[y],
                            remoteCodeUp_lx_wx[y], remoteCode_lx_wx[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }

            } else {
                /*连尾*/
                String[] odds_lianwei = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_lianwei.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(leveTwo_lianxiao_id.get(x), leveTwo_lianxiao_lianwei[x], name_lianwei[y], code_lianwei[y], odds_lianwei[y],
                            remoteCodeUp_lx_wx[y], remoteCode_lx_wx[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            }
            playEndList_lianxiaolianwei.get(x).add(lotteryPlayEnd_lianxiaolianwei);

        }
        /*连码*/
        List<Integer> leveTwo_lianma_id = Arrays.asList(1497, 1498, 1499, 1500, 1501, 1502);
        String[] odds_lianma = new String[]{"", "", "", "", "", ""};
        String[] liamma_remoteCodeUp = new String[]{"sanzhonger", "zhengmasanquanzhong", "erquanzhong", "erzhongte", "techuang", "siquanzhong"};
        String[] liamma_remoteCode = new String[]{"zhengmasanzhonger", "sanquanzhong", "zhengmaerquanzhong", "zhengtemaerzhongte", "zhengtematechuang", "zhengmasiquanzhong"};
        for (int x = 0; x < leveTwo_lianma.length; x++) {
            LotteryPlayEnd lotteryPlayEnd_lianma = new LotteryPlayEnd();
            lotteryPlayEnd_lianma.setTag("");
            lotteryPlayEnd_lianma.setDivider(2);
            lotteryPlayEnd_lianma.setCanExpand(false);
            lotteryPlayEnd_lianma.setSpanCount(4);
            List<LotteryPlay> numLhc = createNumLhc(Arrays.asList(leveTwo_lianma_id.get(x)), leveTwo_lianma[x], odds_lianma[x], 2, false, true,
                    liamma_remoteCodeUp[x], liamma_remoteCode[x]);
            lotteryPlayEnd_lianma.setLotteryPlays(numLhc);
            playEndList_lianma.get(x).add(lotteryPlayEnd_lianma);
        }

        /*一肖总肖平特尾数*/
        List<Integer> name_shengxiao_Id = Arrays.asList(1503, 1504, 1505, 1506, 1507, 1508, 1509, 1510, 1511, 1512, 1513, 1514);
        String[] remoteCodeUp_lianwei = new String[]{"xuanweishu_zhengtemalingwei", "xuanweishu_zhengtemayiwei", "xuanweishu_zhengtemaerwei", "xuanweishu_zhengtemasanwei", "xuanweishu_zhengtemasiwei", "xuanweishu_zhengtemawuwei",
                "xuanweishu_zhengtemaliuwei", "xuanweishu_zhengtemaqiwei", "xuanweishu_zhengtemabawei", "xuanweishu_zhengtemajiuwei"};
        for (int x = 0; x < leveTwo_yi_xiao_zong_ping.length; x++) {
            LotteryPlayEnd lotteryPlayEnd_lianma = new LotteryPlayEnd();
            lotteryPlayEnd_lianma.setTag("");
            lotteryPlayEnd_lianma.setDivider(2);
            lotteryPlayEnd_lianma.setCanExpand(false);
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            if (x == 0) {
                lotteryPlayEnd_lianma.setSpanCount(3);
                String[] odds_yi_xiao_zong_ping = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_shengxiao.length; y++) {
                    String remoteCodeUp = "xuanshengxiao_zhengtemaxuanshengxiaofbm";
                    if (0 == y) {
                        remoteCodeUp = "xuanshengxiao_zhengtemaxuanshengxiaobm";
                    }
                    LotteryPlay lotteryPlay = new LotteryPlay(name_shengxiao_Id.get(y), "正特一肖", name_shengxiao[y], code_shengxiao[y], odds_yi_xiao_zong_ping[y],
                            remoteCodeUp, code_shengxiao[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            } else if (x == 1) {
                lotteryPlayEnd_lianma.setSpanCount(2);
                LotteryPlay lotteryPlay0 = new LotteryPlay(1515, "正特肖数", "2肖", "erxiao", "", "zongxiao_zhengtemaerxiao", "erxiao", 0);
                LotteryPlay lotteryPlay1 = new LotteryPlay(1516, "正特肖数", "3肖", "sanxiao", "", "zongxiao_zhengtemasanxiao", "sanxiao", 0);
                LotteryPlay lotteryPlay2 = new LotteryPlay(1517, "正特肖数", "4肖", "sixiao", "", "zongxiao_zhengtemasixiao", "sixiao", 0);
                LotteryPlay lotteryPlay3 = new LotteryPlay(1518, "正特肖数", "5肖", "wuxiao", "", "zongxiao_zhengtemawuxiao", "wuxiao", 0);
                LotteryPlay lotteryPlay4 = new LotteryPlay(1519, "正特肖数", "6肖", "liuxiao", "", "zongxiao_zhengtemaliuxiao", "liuxiao", 0);
                LotteryPlay lotteryPlay5 = new LotteryPlay(1520, "正特肖数", "7肖", "qixiao", "", "zongxiao_zhengtemaqixiao", "qixiao", 0);
                LotteryPlay lotteryPlay6 = new LotteryPlay(1521, "总肖单双", "总肖单", "dan", 1521, "zongxiao_zongxiaodan", "dan");
                LotteryPlay lotteryPlay7 = new LotteryPlay(1522, "总肖单双", "总肖双", "shuang", 1522, "zongxiao_zongxiaoshuang", "shuang");
                lotteryPlays.add(lotteryPlay0);
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
                lotteryPlays.add(lotteryPlay3);
                lotteryPlays.add(lotteryPlay4);
                lotteryPlays.add(lotteryPlay5);
                lotteryPlays.add(lotteryPlay6);
                lotteryPlays.add(lotteryPlay7);


            } else {
                lotteryPlayEnd_lianma.setSpanCount(3);

                /*连肖*/
                String[] odds_yi_xiao_lianwei = new String[]{"", "", "", "", "", "", "", "", "", ""};
                for (int y = 0; y < name_lianwei.length; y++) {
                    LotteryPlay lotteryPlay = new LotteryPlay(name_lianweiId.get(y), "正特尾数", name_lianwei[y], code_lianwei[y], odds_yi_xiao_lianwei[y],
                            remoteCodeUp_lianwei[y], code_lianwei[y], 0);
                    lotteryPlays.add(lotteryPlay);
                }
            }

            lotteryPlayEnd_lianma.setLotteryPlays(lotteryPlays);
            playEndList_yixiaozongping.get(x).add(lotteryPlayEnd_lianma);
        }


        /*总和*/
        LotteryPlayEnd lotteryPlayEnd_zonghe = new LotteryPlayEnd();
        lotteryPlayEnd_zonghe.setTag("");
        lotteryPlayEnd_zonghe.setDivider(2);
        lotteryPlayEnd_zonghe.setCanExpand(false);
        lotteryPlayEnd_zonghe.setSpanCount(2);

        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        LotteryPlay lotteryPlay_da = new LotteryPlay(1533, "总和大小", "大", "da", 1533, "zonghedaxiao", "da");
        LotteryPlay lotteryPlay_xiao = new LotteryPlay(1534, "总和大小", "小", "xiao", 1534, "zonghedaxiao", "xiao");
        LotteryPlay lotteryPlay_dan = new LotteryPlay(1535, "总和单双", "单", "dan", 1535, "zonghedanshuang", "dan");
        LotteryPlay lotteryPlay_shuagn = new LotteryPlay(1536, "总和单双", "双", "shuang", 1536, "zonghedanshuang", "shuang");
        lotteryPlays.add(lotteryPlay_da);
        lotteryPlays.add(lotteryPlay_xiao);
        lotteryPlays.add(lotteryPlay_dan);
        lotteryPlays.add(lotteryPlay_shuagn);
        lotteryPlayEnd_zonghe.setLotteryPlays(lotteryPlays);
        playEndList_zonghe.add(lotteryPlayEnd_zonghe);

        /*自选不中*/
        LotteryPlayEnd lotteryPlayEnd_zixuan_buzhong = new LotteryPlayEnd();
        lotteryPlayEnd_zixuan_buzhong.setTag("");
        lotteryPlayEnd_zixuan_buzhong.setDivider(2);
        lotteryPlayEnd_zixuan_buzhong.setCanExpand(false);
        lotteryPlayEnd_zixuan_buzhong.setSpanCount(4);

        //自选不中ID
        List<Integer> ziXuanBuzhong = Arrays.asList(1537, 1538, 1539, 1540, 1541, 1542, 1543);
        List<LotteryPlay> zixuanbuzhongPlays = createNumLhc(Arrays.asList(1537), "自选不中", "", 2, false, true);
        lotteryPlayEnd_zixuan_buzhong.setLotteryPlays(zixuanbuzhongPlays);
        playEndList_zexuan_buzhong.add(lotteryPlayEnd_zixuan_buzhong);


        List<List<LotteryPlaySub>> playSubs = new ArrayList<>();
        /*二级  特码*/

        List<LotteryPlaySub> playSublist_tema = new ArrayList<>();
        playSubs.add(playSublist_tema);
        for (int x = 0; x < leveTwo_tema.length; x++) {
            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_tema[x], "", leveTwo_tema_remoteCode[x], leveTwo_tema_chmLayout[x]);
            if ("双面".equals(leveTwo_tema[x])) {
                playSub.setCode("liangmian");
            } else if ("号码".equals(leveTwo_tema[x])) {
                playSub.setCode("haoma");
            } else if ("色波半波".equals(leveTwo_tema[x])) {
                playSub.setCode("sebobanbo");
            } else if ("特肖头尾数".equals(leveTwo_tema[x])) {
                playSub.setCode("texiaotouweixiao");
            } else if ("合肖".equals(leveTwo_tema[x])) {
                playSub.setCode("hexiao");
            } else if ("五行".equals(leveTwo_tema[x])) {
                playSub.setCode("wuxing");
            }
            if (x == 0) {
                playSub.setSelected(true);
            }

            playSub.setLotteryPlayEnds(playEndList_tema.get(x));
            playSublist_tema.add(playSub);

        }



        /*二级  正码*/
        List<LotteryPlaySub> playSublist_zhengma = new ArrayList<>();
        playSubs.add(playSublist_zhengma);
        for (int x = 0; x < leveTwo_zhengma.length; x++) {
            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_zhengma[x], "", leveTwo_zhengma_code[x], leveTwo_zhengma_chmLayout[x]);
            if (x == 0) {
                playSub.setSelected(true);
            }

            playSub.setLotteryPlayEnds(playEndList_zhengma.get(x));
            playSublist_zhengma.add(playSub);
        }


        /*二级  正码特*/
        List<LotteryPlaySub> playSublist_zhengma_te = new ArrayList<>();
        playSubs.add(playSublist_zhengma_te);
        for (int x = 0; x < leveTwo_zhengma_te.length; x++) {
            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_zhengma_te[x], "", leveTwo_zhengma_te_code[x], S_ColdHot_Omit);
            if (x == 0) {
                playSub.setSelected(true);
            }
            playSub.setLotteryPlayEnds(playEndList_zhengma_te.get(x));
            playSublist_zhengma_te.add(playSub);
        }


        /*二级 连肖连尾*/
        List<LotteryPlaySub> playSublist_lianxiao_lianwei = new ArrayList<>();
        playSubs.add(playSublist_lianxiao_lianwei);
        for (int x = 0; x < leveTwo_lianxiao_lianwei.length; x++) {

            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_lianxiao_lianwei[x], "", leveTwo_lianxiao_lianwei_code[x], S_Omit);
            if (x == 0) {
                playSub.setSelected(true);
            }
            playSub.setLotteryPlayEnds(playEndList_lianxiaolianwei.get(x));
            playSublist_lianxiao_lianwei.add(playSub);
        }


        /*二级 连码*/
        List<LotteryPlaySub> playSublist_lianma = new ArrayList<>();
        playSubs.add(playSublist_lianma);
        for (int x = 0; x < leveTwo_lianma.length; x++) {
            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_lianma[x], "", leveTwo_lianma_code[x], leveTwo_lianma_chmLayout[x]);
            if ("三中二".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(0));
            } else if ("三全中".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(1));
            } else if ("二全中".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(2));
            } else if ("二中特".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(3));
            } else if ("特串".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(4));
            } else if ("四全中".equals(leveTwo_lianma[x])) {
                playSub.setPlayId(leveTwo_lianma_id.get(5));
            }
            if (x == 0) {
                playSub.setSelected(true);
            }
            playSub.setLotteryPlayEnds(playEndList_lianma.get(x));
            playSublist_lianma.add(playSub);
        }

        /*二级 一肖总肖平特尾数*/
        List<LotteryPlaySub> playSublist_yixiaozongping = new ArrayList<>();
        playSubs.add(playSublist_yixiaozongping);
        for (int x = 0; x < leveTwo_yi_xiao_zong_ping.length; x++) {
            LotteryPlaySub playSub = new LotteryPlaySub(leveTwo_yi_xiao_zong_ping[x], "", leveTwo_yi_xiao_zong_ping_code[x], leveTwo_yi_xiao_zong_ping_chmLayout[x]);
            if (x == 0) {
                playSub.setSelected(true);
            }
            playSub.setLotteryPlayEnds(playEndList_yixiaozongping.get(x));
            playSublist_yixiaozongping.add(playSub);
        }
        /*总和*/
        List<LotteryPlaySub> playSublist_zonghe = new ArrayList<>();
        playSubs.add(playSublist_zonghe);
        LotteryPlaySub playSub_zonghe = new LotteryPlaySub("总和", "", "zonghe", S_ColdHot);
        playSub_zonghe.setLotteryPlayEnds(playEndList_zonghe);
        playSub_zonghe.setSelected(true);
        playSublist_zonghe.add(playSub_zonghe);

        /*自选不中*/
        List<LotteryPlaySub> playSublist_zixuanbuzhong = new ArrayList<>();
        playSubs.add(playSublist_zixuanbuzhong);

        LotteryPlaySub playSub_zixuanbuzhong = new LotteryPlaySub("自选不中", "", "zixuanbuzhong", S_ColdHot_Omit_Odds_One);
        playSub_zixuanbuzhong.setPlayId(1537);
        playSub_zixuanbuzhong.setSelected(true);
        playSub_zixuanbuzhong.setLotteryPlayEnds(playEndList_zexuan_buzhong);
        playSublist_zixuanbuzhong.add(playSub_zixuanbuzhong);


        List<LotteryPlayStart> playStartList = new ArrayList<>();
        /*一级 左侧玩法*/
        for (int x = 0; x < levelOne.length; x++) {
            LotteryPlayStart playStart_tema = new LotteryPlayStart(levelOne[x], levelOne_remoteCode[x]);
            playStart_tema.setSubPlays(playSubs.get(x));
            playStartList.add(playStart_tema);
        }


        return playStartList;
    }

    private static List<LotteryPlayStart> createPlay_11x5() {
        //  11x5
        List<LotteryPlayStart> lotteryPlayStartList = new ArrayList<>();
        LotteryPlayStart playStart1 = new LotteryPlayStart("双面", "shuangmian");
        List<LotteryPlaySub> playSubs1 = new ArrayList<>();
        playStart1.setSubPlays(playSubs1);

        LotteryPlaySub playSub1 = new LotteryPlaySub("双面_副", "", "shuangmian", S_ColdHot);
        List<LotteryPlayEnd> playEndList1 = new ArrayList<>();
        playSub1.setLotteryPlayEnds(playEndList1);

        LotteryPlayEnd playEnd1 = new LotteryPlayEnd();
        playEnd1.setTag("总和");
        playEnd1.setRemoteCode("zonghe");
        playEnd1.setSpanCount(4);
        playEnd1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList1 = new ArrayList<>();
        playEnd1.setLotteryPlays(lotteryPlayList1);

        lotteryPlayList1.add(new LotteryPlay(4601010101L, "总和大小", "大", "da", 429, "zonghedaxiao", "da"));
        lotteryPlayList1.add(new LotteryPlay(4601010102L, "总和大小", "小", "xiao", 430, "zonghedaxiao", "xiao"));
        lotteryPlayList1.add(new LotteryPlay(4601010201L, "总和单双", "单", "dan", 431, "zonghedanshuang", "dan"));
        lotteryPlayList1.add(new LotteryPlay(4601010501L, "总和单双", "双", "shuang", 432, "zonghedanshuang", "shuang"));
        lotteryPlayList1.add(new LotteryPlay(4601010401L, "总和龙虎", "龙", "long", 433, "zonghelonghu", "long"));
        lotteryPlayList1.add(new LotteryPlay(4601010402L, "总和龙虎", "虎", "hu", 434, "zonghelonghu", "hu"));
        lotteryPlayList1.add(new LotteryPlay(4601010301L, "总和尾大小", "尾大", "weida", "", "zongheweidaxiao", "weida"));
        lotteryPlayList1.add(new LotteryPlay(4601010601L, "总和尾大小", "尾小", "weixiao", "", "zongheweidaxiao", "weixiao"));
        //--
        LotteryPlayEnd playEnd2 = new LotteryPlayEnd();
        playEnd2.setTag("第一球");
        playEnd2.setRemoteCode("diyiqiu");
        playEnd2.setSpanCount(4);
        playEnd2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2 = new ArrayList<>();
        playEnd2.setLotteryPlays(lotteryPlayList2);
        lotteryPlayList2.add(new LotteryPlay(4601020101L, "第一球大小", "大", "da", 435, "diyiqiudaxiao", "da"));
        lotteryPlayList2.add(new LotteryPlay(4601020102L, "第一球大小", "小", "xiao", 436, "diyiqiudaxiao", "xiao"));
        lotteryPlayList2.add(new LotteryPlay(4601020201L, "第一球单双", "单", "dan", 437, "diyiqiudanshuang", "dan"));
        lotteryPlayList2.add(new LotteryPlay(4601020202L, "第一球单双", "双", "shuang", 438, "diyiqiudanshuang", "shuang"));
        //-
        LotteryPlayEnd playEnd3 = new LotteryPlayEnd();
        playEnd3.setTag("第二球");
        playEnd3.setRemoteCode("dierqiu");
        playEnd3.setSpanCount(4);
        playEnd3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3 = new ArrayList<>();
        playEnd3.setLotteryPlays(lotteryPlayList3);
        lotteryPlayList3.add(new LotteryPlay(4601030101L, "第二球大小", "大", "da", 439, "dierqiudaxiao", "da"));
        lotteryPlayList3.add(new LotteryPlay(4601030102L, "第二球大小", "小", "xiao", 440, "dierqiudaxiao", "xiao"));
        lotteryPlayList3.add(new LotteryPlay(4601030201L, "第二球单双", "单", "dan", 441, "dierqiudanshuang", "dan"));
        lotteryPlayList3.add(new LotteryPlay(4601030202L, "第二球单双", "双", "shuang", 442, "dierqiudanshuang", "shuang"));
        //-
        LotteryPlayEnd playEnd4 = new LotteryPlayEnd();
        playEnd4.setTag("第三球");
        playEnd4.setRemoteCode("disanqiu");
        playEnd4.setSpanCount(4);
        playEnd4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4 = new ArrayList<>();
        playEnd4.setLotteryPlays(lotteryPlayList4);
        lotteryPlayList4.add(new LotteryPlay(4601040101L, "第三球大小", "大", "da", 443, "disanqiudaxiao", "da"));
        lotteryPlayList4.add(new LotteryPlay(4601040102L, "第三球大小", "小", "xiao", 444, "disanqiudaxiao", "xiao"));
        lotteryPlayList4.add(new LotteryPlay(4601040201L, "第三球单双", "单", "dan", 445, "disanqiudanshuang", "dan"));
        lotteryPlayList4.add(new LotteryPlay(4601040202L, "第三球单双", "双", "shuang", 446, "disanqiudanshuang", "shuang"));
        //-
        LotteryPlayEnd playEnd5 = new LotteryPlayEnd();
        playEnd5.setTag("第四球");
        playEnd5.setRemoteCode("disiqiu");
        playEnd5.setSpanCount(4);
        playEnd5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5 = new ArrayList<>();
        playEnd5.setLotteryPlays(lotteryPlayList5);
        lotteryPlayList5.add(new LotteryPlay(4601050101L, "第四球大小", "大", "da", 447, "disiqiudaxiao", "da"));
        lotteryPlayList5.add(new LotteryPlay(4601050102L, "第四球大小", "小", "xiao", 448, "disiqiudaxiao", "xiao"));
        lotteryPlayList5.add(new LotteryPlay(4601050201L, "第四球单双", "单", "dan", 449, "disiqiudanshuang", "dan"));
        lotteryPlayList5.add(new LotteryPlay(4601050202L, "第四球单双", "双", "shuang", 450, "disiqiudanshuang", "shuang"));
        //-
        LotteryPlayEnd playEnd6 = new LotteryPlayEnd();
        playEnd6.setTag("第五球");
        playEnd6.setRemoteCode("diwuqiu");
        playEnd6.setSpanCount(4);
        playEnd6.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList6 = new ArrayList<>();
        playEnd6.setLotteryPlays(lotteryPlayList6);
        lotteryPlayList6.add(new LotteryPlay(4601060101L, "第五球大小", "大", "da", 451, "diwuqiudaxiao", "da"));
        lotteryPlayList6.add(new LotteryPlay(4601060102L, "第五球大小", "小", "xiao", 452, "diwuqiudaxiao", "xiao"));
        lotteryPlayList6.add(new LotteryPlay(4601060201L, "第五球单双", "单", "dan", 453, "diwuqiudanshuang", "dan"));
        lotteryPlayList6.add(new LotteryPlay(4601060202L, "第五球单双", "双", "shuang", 454, "diwuqiudanshuang", "shuang"));

        playEndList1.add(playEnd1);
        playEndList1.add(playEnd2);
        playEndList1.add(playEnd3);
        playEndList1.add(playEnd4);
        playEndList1.add(playEnd5);
        playEndList1.add(playEnd6);
        playSubs1.add(playSub1);

        //---
        // left tab
        LotteryPlayStart playStart2 = new LotteryPlayStart("选号", "xuanhao");
        List<LotteryPlaySub> playSubs2 = new ArrayList<>();
        playStart2.setSubPlays(playSubs2);
        // tab
        LotteryPlaySub playSub2_1 = new LotteryPlaySub("选号_副", "", "xuanhao", S_ColdHot_Omit);
        List<LotteryPlayEnd> playEndList2_1 = new ArrayList<>();
        playSub2_1.setLotteryPlayEnds(playEndList2_1);

        LotteryPlayEnd playEnd2_1 = new LotteryPlayEnd();
        playEnd2_1.setTag("第一球");
        playEnd2_1.setRemoteCode("diyiqiu");
        playEnd2_1.setSpanCount(4);
        playEnd2_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_1 = new ArrayList<>();
        playEnd2_1.setLotteryPlays(lotteryPlayList2_1);
        lotteryPlayList2_1.addAll(createLP_No_11x5("第一球定位胆", "", true, false, BET_LAYOUT_TYPE_RECTANGLE, "diyiqiudingweidan", ""));
        //--
        LotteryPlayEnd playEnd2_2 = new LotteryPlayEnd();
        playEnd2_2.setTag("第二球");
        playEnd2_2.setRemoteCode("dierqiu");
        playEnd2_2.setSpanCount(4);
        playEnd2_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_2 = new ArrayList<>();
        playEnd2_2.setLotteryPlays(lotteryPlayList2_2);
        lotteryPlayList2_2.addAll(createLP_No_11x5("第二球定位胆", "", true, false, BET_LAYOUT_TYPE_RECTANGLE, "dierqiudingweidan", ""));
        //--
        LotteryPlayEnd playEnd2_3 = new LotteryPlayEnd();
        playEnd2_3.setTag("第三球");
        playEnd2_3.setRemoteCode("disanqiu");
        playEnd2_3.setSpanCount(4);
        playEnd2_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_3 = new ArrayList<>();
        playEnd2_3.setLotteryPlays(lotteryPlayList2_3);
        lotteryPlayList2_3.addAll(createLP_No_11x5("第三球定位胆", "", true, false, BET_LAYOUT_TYPE_RECTANGLE, "disanqiudingweidan", ""));
        //--
        LotteryPlayEnd playEnd2_4 = new LotteryPlayEnd();
        playEnd2_4.setTag("第四球");
        playEnd2_4.setRemoteCode("disiqiu");
        playEnd2_4.setSpanCount(4);
        playEnd2_4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_4 = new ArrayList<>();
        playEnd2_4.setLotteryPlays(lotteryPlayList2_4);
        lotteryPlayList2_4.addAll(createLP_No_11x5("第四球定位胆", "", true, false, BET_LAYOUT_TYPE_RECTANGLE, "disiqiudingweidan", ""));
        //--
        LotteryPlayEnd playEnd2_5 = new LotteryPlayEnd();
        playEnd2_5.setTag("第五球");
        playEnd2_5.setRemoteCode("diwuqiu");
        playEnd2_5.setSpanCount(4);
        playEnd2_5.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList2_5 = new ArrayList<>();
        playEnd2_5.setLotteryPlays(lotteryPlayList2_5);
        lotteryPlayList2_5.addAll(createLP_No_11x5("第五球定位胆", "", true, false, BET_LAYOUT_TYPE_RECTANGLE, "diwuqiudingweidan", ""));

        playEndList2_1.add(playEnd2_1);
        playEndList2_1.add(playEnd2_2);
        playEndList2_1.add(playEnd2_3);
        playEndList2_1.add(playEnd2_4);
        playEndList2_1.add(playEnd2_5);
        playSubs2.add(playSub2_1);

        //---
        // left tab
        LotteryPlayStart playStart3 = new LotteryPlayStart("任选", "renxuan");
        List<LotteryPlaySub> playSubs3 = new ArrayList<>();
        playStart3.setSubPlays(playSubs3);
        // tab
        LotteryPlaySub playSub3_1 = new LotteryPlaySub("任选一", "", "renxuanyi", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_2 = new LotteryPlaySub("任选二", "", "renxuaner", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_3 = new LotteryPlaySub("任选三", "", "renxuansan", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_4 = new LotteryPlaySub("任选四", "", "renxuansi", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_5 = new LotteryPlaySub("任选五", "", "renxuanwu", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_6 = new LotteryPlaySub("任选六中五", "", "renxuanliuzhongwu", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_7 = new LotteryPlaySub("任选七中五", "", "renxuanqizhongwu", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub3_8 = new LotteryPlaySub("任选八中五", "", "renxuanbazhongwu", S_ColdHot_Omit_Odds_One);
        List<LotteryPlayEnd> playEndList3_1 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_2 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_3 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_4 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_5 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_6 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_7 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList3_8 = new ArrayList<>();

        LotteryPlayEnd playEnd3_1 = new LotteryPlayEnd();
        playEnd3_1.setTag("");
        playEnd3_1.setRemoteCode("");
        playEnd3_1.setCanExpand(false);
        playEnd3_1.setSpanCount(5);
        playEnd3_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_1 = new ArrayList<>();
        lotteryPlayList3_1.addAll(createLP_No_11x5("任选一", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuanyi", "renxuanyi"));
        playEnd3_1.setLotteryPlays(lotteryPlayList3_1);
        playEndList3_1.add(playEnd3_1);
        playSub3_1.setLotteryPlayEnds(playEndList3_1);
        playSubs3.add(playSub3_1);

        LotteryPlayEnd playEnd3_2 = new LotteryPlayEnd();
        playEnd3_2.setTag("");
        playEnd3_2.setRemoteCode("");
        playEnd3_2.setCanExpand(false);
        playEnd3_2.setSpanCount(5);
        playEnd3_2.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_2 = new ArrayList<>();
        lotteryPlayList3_2.addAll(createLP_No_11x5("任选二", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuaner", "renxuaner"));
        playEnd3_2.setLotteryPlays(lotteryPlayList3_2);
        playEndList3_2.add(playEnd3_2);
        playSub3_2.setLotteryPlayEnds(playEndList3_2);
        playSubs3.add(playSub3_2);

        LotteryPlayEnd playEnd3_3 = new LotteryPlayEnd();
        playEnd3_3.setTag("");
        playEnd3_3.setRemoteCode("");
        playEnd3_3.setCanExpand(false);
        playEnd3_3.setSpanCount(5);
        playEnd3_3.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_3 = new ArrayList<>();
        lotteryPlayList3_3.addAll(createLP_No_11x5("任选三", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuansan", "renxuansan"));
        playEnd3_3.setLotteryPlays(lotteryPlayList3_3);
        playEndList3_3.add(playEnd3_3);
        playSub3_3.setLotteryPlayEnds(playEndList3_3);
        playSubs3.add(playSub3_3);

        LotteryPlayEnd playEnd3_4 = new LotteryPlayEnd();
        playEnd3_4.setTag("");
        playEnd3_4.setRemoteCode("");
        playEnd3_4.setCanExpand(false);
        playEnd3_4.setSpanCount(5);
        playEnd3_4.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_4 = new ArrayList<>();
        lotteryPlayList3_4.addAll(createLP_No_11x5("任选四", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuansi", "renxuansi"));
        playEnd3_4.setLotteryPlays(lotteryPlayList3_4);
        playEndList3_4.add(playEnd3_4);
        playSub3_4.setLotteryPlayEnds(playEndList3_4);
        playSubs3.add(playSub3_4);

        LotteryPlayEnd playEnd3_5 = new LotteryPlayEnd();
        playEnd3_5.setTag("");
        playEnd3_5.setRemoteCode("");
        playEnd3_5.setCanExpand(false);
        playEnd3_5.setSpanCount(5);
        playEnd3_5.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_5 = new ArrayList<>();
        lotteryPlayList3_5.addAll(createLP_No_11x5("任选五", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuanwu", "renxuanwu"));
        playEnd3_5.setLotteryPlays(lotteryPlayList3_5);
        playEndList3_5.add(playEnd3_5);
        playSub3_5.setLotteryPlayEnds(playEndList3_5);
        playSubs3.add(playSub3_5);

        LotteryPlayEnd playEnd3_6 = new LotteryPlayEnd();
        playEnd3_6.setTag("");
        playEnd3_6.setRemoteCode("");
        playEnd3_6.setCanExpand(false);
        playEnd3_6.setSpanCount(5);
        playEnd3_6.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_6 = new ArrayList<>();
        lotteryPlayList3_6.addAll(createLP_No_11x5("任选六中五", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuanliuzhongwu", "renxuanliuzhongwu"));
        playEnd3_6.setLotteryPlays(lotteryPlayList3_6);
        playEndList3_6.add(playEnd3_6);
        playSub3_6.setLotteryPlayEnds(playEndList3_6);
        playSubs3.add(playSub3_6);

        LotteryPlayEnd playEnd3_7 = new LotteryPlayEnd();
        playEnd3_7.setTag("");
        playEnd3_7.setRemoteCode("");
        playEnd3_7.setCanExpand(false);
        playEnd3_7.setSpanCount(5);
        playEnd3_7.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_7 = new ArrayList<>();
        lotteryPlayList3_7.addAll(createLP_No_11x5("任选七中五", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuanqizhongwu", "renxuanqizhongwu"));
        playEnd3_7.setLotteryPlays(lotteryPlayList3_7);
        playEndList3_7.add(playEnd3_7);
        playSub3_7.setLotteryPlayEnds(playEndList3_7);
        playSubs3.add(playSub3_7);

        LotteryPlayEnd playEnd3_8 = new LotteryPlayEnd();
        playEnd3_8.setTag("");
        playEnd3_8.setRemoteCode("");
        playEnd3_8.setCanExpand(false);
        playEnd3_8.setSpanCount(5);
        playEnd3_8.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList3_8 = new ArrayList<>();
        lotteryPlayList3_8.addAll(createLP_No_11x5("任选八中五", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "renxuanbazhongwu", "renxuanbazhongwu"));
        playEnd3_8.setLotteryPlays(lotteryPlayList3_8);
        playEndList3_8.add(playEnd3_8);
        playSub3_8.setLotteryPlayEnds(playEndList3_8);
        playSubs3.add(playSub3_8);


        //---
        // left tab
        LotteryPlayStart playStart4 = new LotteryPlayStart("组选", "zuxuan");
        List<LotteryPlaySub> playSubs4 = new ArrayList<>();
        playStart4.setSubPlays(playSubs4);
        // tab
        LotteryPlaySub playSub4_1 = new LotteryPlaySub("前二组选", "", "qianerzuxuan", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub4_2 = new LotteryPlaySub("前三组选", "", "qiansanzuxuan", S_ColdHot_Omit_Odds_One);
        List<LotteryPlayEnd> playEndList4_1 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList4_2 = new ArrayList<>();

        LotteryPlayEnd playEnd4_1 = new LotteryPlayEnd();
        playEnd4_1.setTag("");
        playEnd4_1.setRemoteCode("");
        playEnd4_1.setCanExpand(false);
        playEnd4_1.setSpanCount(5);
        playEnd4_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList4_1 = new ArrayList<>();
        lotteryPlayList4_1.addAll(createLP_No_11x5("前二组选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qianerzuxuan", "qianerzuxuan"));
        playEnd4_1.setLotteryPlays(lotteryPlayList4_1);
        playEndList4_1.add(playEnd4_1);

        LotteryPlayEnd playEnd4_2 = new LotteryPlayEnd();
        playEnd4_2.setTag("");
        playEnd4_2.setRemoteCode("");
        playEnd4_2.setCanExpand(false);
        playEnd4_2.setSpanCount(5);
        playEnd4_2.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList4_2 = new ArrayList<>();
        lotteryPlayList4_2.addAll(createLP_No_11x5("前三组选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qiansanzuxuan", "qiansanzuxuan"));
        playEnd4_2.setLotteryPlays(lotteryPlayList4_2);
        playEndList4_2.add(playEnd4_2);

        playSub4_1.setLotteryPlayEnds(playEndList4_1);
        playSub4_2.setLotteryPlayEnds(playEndList4_2);
        playSubs4.add(playSub4_1);
        playSubs4.add(playSub4_2);

        //---
        // left tab
        LotteryPlayStart playStart5 = new LotteryPlayStart("直选", "zhixuan");
        List<LotteryPlaySub> playSubs5 = new ArrayList<>();
        playStart5.setSubPlays(playSubs5);
        // tab
        LotteryPlaySub playSub5_1 = new LotteryPlaySub("前二直选", "", "qianerzhixuan", S_ColdHot_Omit_Odds_One);
        LotteryPlaySub playSub5_2 = new LotteryPlaySub("前三直选", "", "qiansanzhixuan", S_ColdHot_Omit_Odds_One);
        List<LotteryPlayEnd> playEndList5_1 = new ArrayList<>();
        List<LotteryPlayEnd> playEndList5_2 = new ArrayList<>();

        LotteryPlayEnd playEnd5_1 = new LotteryPlayEnd();
        playEnd5_1.setTag("第一球");
        playEnd5_1.setRemoteCode("");
        playEnd5_1.setCanExpand(false);
        playEnd5_1.setSpanCount(5);
        playEnd5_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5_1 = new ArrayList<>();
        lotteryPlayList5_1.addAll(createLP_No_11x5("前二直选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qianerzhixuan", "qianerzhixuan"));
        playEnd5_1.setLotteryPlays(lotteryPlayList5_1);

        LotteryPlayEnd playEnd5_12 = new LotteryPlayEnd();
        playEnd5_12.setTag("第二球");
        playEnd5_12.setRemoteCode("");
        playEnd5_12.setCanExpand(false);
        playEnd5_12.setSpanCount(5);
        playEnd5_12.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList5_12 = new ArrayList<>();
        lotteryPlayList5_12.addAll(createLP_No_11x5("前二直选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qianerzhixuan", "qianerzhixuan"));
        playEnd5_12.setLotteryPlays(lotteryPlayList5_12);

        playEndList5_1.add(playEnd5_1);
        playEndList5_1.add(playEnd5_12);
        playSub5_1.setLotteryPlayEnds(playEndList5_1);

        LotteryPlayEnd playEnd5_21 = new LotteryPlayEnd();
        playEnd5_21.setTag("第一球");
        playEnd5_21.setRemoteCode("");
        playEnd5_21.setCanExpand(false);
        playEnd5_21.setSpanCount(5);
        playEnd5_21.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5_21 = new ArrayList<>();
        lotteryPlayList5_21.addAll(createLP_No_11x5("前三直选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qiansanzhixuan", "qiansanzhixuan"));
        playEnd5_21.setLotteryPlays(lotteryPlayList5_21);

        LotteryPlayEnd playEnd5_22 = new LotteryPlayEnd();
        playEnd5_22.setTag("第二球");
        playEnd5_22.setRemoteCode("");
        playEnd5_22.setCanExpand(false);
        playEnd5_22.setSpanCount(5);
        playEnd5_22.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5_22 = new ArrayList<>();
        lotteryPlayList5_22.addAll(createLP_No_11x5("前三直选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qiansanzhixuan", "qiansanzhixuan"));
        playEnd5_22.setLotteryPlays(lotteryPlayList5_22);

        LotteryPlayEnd playEnd5_23 = new LotteryPlayEnd();
        playEnd5_23.setTag("第三球");
        playEnd5_23.setRemoteCode("");
        playEnd5_23.setCanExpand(false);
        playEnd5_23.setSpanCount(5);
        playEnd5_23.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList5_23 = new ArrayList<>();
        lotteryPlayList5_23.addAll(createLP_No_11x5("前三直选", "", true, true, BET_LAYOUT_TYPE_CIRCLE, "qiansanzhixuan", "qiansanzhixuan"));
        playEnd5_23.setLotteryPlays(lotteryPlayList5_23);

        playEndList5_2.add(playEnd5_21);
        playEndList5_2.add(playEnd5_22);
        playEndList5_2.add(playEnd5_23);
        playSub5_2.setLotteryPlayEnds(playEndList5_2);

        playSubs5.add(playSub5_1);
        playSubs5.add(playSub5_2);


        lotteryPlayStartList.add(playStart1);
        lotteryPlayStartList.add(playStart2);
        lotteryPlayStartList.add(playStart3);
        lotteryPlayStartList.add(playStart4);
        lotteryPlayStartList.add(playStart5);

        return lotteryPlayStartList;
    }

    private static List<LotteryPlayStart> createPlay_k3() {
        //  k3
        List<LotteryPlayStart> lotteryPlayStartList = new ArrayList<>();
        LotteryPlayStart playStart1 = new LotteryPlayStart("整合", "zhenghe");
        List<LotteryPlaySub> playSubs1 = new ArrayList<>();
        playStart1.setSubPlays(playSubs1);

        LotteryPlaySub playSub1 = new LotteryPlaySub("三军 大小", "", "sanjundaxiao", S_ColdHot_Omit);
        List<LotteryPlayEnd> playEndList1 = new ArrayList<>();
        playSub1.setLotteryPlayEnds(playEndList1);

        LotteryPlayEnd playEnd1 = new LotteryPlayEnd();
        playEnd1.setTag("三军");
        playEnd1.setSpanCount(2);
        playEnd1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList1 = new ArrayList<>();
        playEnd1.setLotteryPlays(lotteryPlayList1);
        lotteryPlayList1.add(new LotteryPlay(700, "三军", "1", "1", "", "sanjun", "1", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList1.add(new LotteryPlay(701, "三军", "2", "2", "", "sanjun", "2", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList1.add(new LotteryPlay(702, "三军", "3", "3", "", "sanjun", "3", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList1.add(new LotteryPlay(703, "三军", "4", "4", "", "sanjun", "4", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList1.add(new LotteryPlay(704, "三军", "5", "5", "", "sanjun", "5", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList1.add(new LotteryPlay(705, "三军", "6", "6", "", "sanjun", "6", BET_LAYOUT_TYPE_RECTANGLE_K3));
        //--
        LotteryPlayEnd playEnd2 = new LotteryPlayEnd();
        playEnd2.setTag("总和大小");
        playEnd2.setSpanCount(2);
        playEnd2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2 = new ArrayList<>();
        playEnd2.setLotteryPlays(lotteryPlayList2);
        lotteryPlayList2.add(new LotteryPlay(706, "总和大小", "大", "da", 706, BET_LAYOUT_TYPE_RECTANGLE_K3, "daxiao", "da"));
        lotteryPlayList2.add(new LotteryPlay(707, "总和大小", "小", "xiao", 707, BET_LAYOUT_TYPE_RECTANGLE_K3, "daxiao", "xiao"));

        //--
        LotteryPlaySub playSub2 = new LotteryPlaySub("围骰 全骰", "", "weitouquantou", "");
        List<LotteryPlayEnd> playEndList2 = new ArrayList<>();
        playSub2.setLotteryPlayEnds(playEndList2);

        LotteryPlayEnd playEnd2_1 = new LotteryPlayEnd();
        playEnd2_1.setTag("围骰 全骰");
        playEnd2_1.setSpanCount(2);
        playEnd2_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_1 = new ArrayList<>();
        playEnd2_1.setLotteryPlays(lotteryPlayList2_1);
        lotteryPlayList2_1.add(new LotteryPlay(708, "围骰", "111", "111", "", "weitou", "111", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(709, "围骰", "222", "222", "", "weitou", "222", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(710, "围骰", "333", "333", "", "weitou", "333", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(711, "围骰", "444", "444", "", "weitou", "444", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(712, "围骰", "555", "555", "", "weitou", "555", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(713, "围骰", "666", "666", "", "weitou", "666", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_1.add(new LotteryPlay(714, "全骰", "全骰", "quantou", "", "quantou", "quantou", BET_LAYOUT_TYPE_RECTANGLE_K3));

        //--
        LotteryPlaySub playSub3 = new LotteryPlaySub("点数", "", "dianshu", "");
        List<LotteryPlayEnd> playEndList3 = new ArrayList<>();
        playSub3.setLotteryPlayEnds(playEndList3);

        LotteryPlayEnd playEnd3_1 = new LotteryPlayEnd();
        playEnd3_1.setTag("点数");
        playEnd3_1.setSpanCount(4);
        playEnd3_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_1 = new ArrayList<>();
        playEnd3_1.setLotteryPlays(lotteryPlayList3_1);
        lotteryPlayList3_1.add(new LotteryPlay(717, "点数", "3", "3", "", "dianshusan", "3"));
        lotteryPlayList3_1.add(new LotteryPlay(718, "点数", "4", "4", "", "dianshusi", "4"));
        lotteryPlayList3_1.add(new LotteryPlay(719, "点数", "5", "5", "", "dianshuwu", "5"));
        lotteryPlayList3_1.add(new LotteryPlay(720, "点数", "6", "6", "", "dianshuliu", "6"));
        lotteryPlayList3_1.add(new LotteryPlay(721, "点数", "7", "7", "", "dianshuqi", "7"));
        lotteryPlayList3_1.add(new LotteryPlay(722, "点数", "8", "8", "", "dianshuba", "8"));
        lotteryPlayList3_1.add(new LotteryPlay(723, "点数", "9", "9", "", "dianshujiu", "9"));
        lotteryPlayList3_1.add(new LotteryPlay(724, "点数", "10", "10", "", "dianshushi", "10"));
        lotteryPlayList3_1.add(new LotteryPlay(725, "点数", "11", "11", "", "dianshushiyi", "11"));
        lotteryPlayList3_1.add(new LotteryPlay(726, "点数", "12", "12", "", "dianshushier", "12"));
        lotteryPlayList3_1.add(new LotteryPlay(727, "点数", "13", "13", "", "dianshushisan", "13"));
        lotteryPlayList3_1.add(new LotteryPlay(728, "点数", "14", "14", "", "dianshushisi", "14"));
        lotteryPlayList3_1.add(new LotteryPlay(729, "点数", "15", "15", "", "dianshushiwu", "15"));
        lotteryPlayList3_1.add(new LotteryPlay(730, "点数", "16", "16", "", "dianshushiliu", "16"));
        lotteryPlayList3_1.add(new LotteryPlay(731, "点数", "17", "17", "", "dianshushiqi", "17"));
        lotteryPlayList3_1.add(new LotteryPlay(732, "点数", "18", "18", "", "dianshushiba", "18"));

        //--
        LotteryPlaySub playSub4 = new LotteryPlaySub("长短牌", "", "", "");
        List<LotteryPlayEnd> playEndList4 = new ArrayList<>();
        playSub4.setLotteryPlayEnds(playEndList4);

        LotteryPlayEnd playEnd4_1 = new LotteryPlayEnd();
        playEnd4_1.setTag("长牌");
        playEnd4_1.setRemoteCode("changpai");
        playEnd4_1.setSpanCount(2);
        playEnd4_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_1 = new ArrayList<>();
        playEnd4_1.setLotteryPlays(lotteryPlayList4_1);
        lotteryPlayList4_1.add(new LotteryPlay(733, "长牌", "12", "12", "", "changpai", "12", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(734, "长牌", "13", "13", "", "changpai", "13", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(735, "长牌", "14", "14", "", "changpai", "14", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(736, "长牌", "15", "15", "", "changpai", "15", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(737, "长牌", "16", "16", "", "changpai", "16", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(738, "长牌", "23", "23", "", "changpai", "23", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(739, "长牌", "24", "24", "", "changpai", "24", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(740, "长牌", "25", "25", "", "changpai", "25", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(741, "长牌", "26", "26", "", "changpai", "26", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(742, "长牌", "34", "34", "", "changpai", "34", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(743, "长牌", "35", "35", "", "changpai", "35", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(744, "长牌", "36", "36", "", "changpai", "36", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(745, "长牌", "45", "45", "", "changpai", "45", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(746, "长牌", "46", "46", "", "changpai", "46", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_1.add(new LotteryPlay(747, "长牌", "56", "56", "", "changpai", "56", BET_LAYOUT_TYPE_RECTANGLE_K3));

        //--
        LotteryPlayEnd playEnd4_2 = new LotteryPlayEnd();
        playEnd4_2.setTag("短牌");
        playEnd4_2.setRemoteCode("duanpai");
        playEnd4_2.setSpanCount(2);
        playEnd4_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_2 = new ArrayList<>();
        playEnd4_2.setLotteryPlays(lotteryPlayList4_2);
        lotteryPlayList4_2.add(new LotteryPlay(748, "短牌", "11", "11", "", "duanpai", "11", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_2.add(new LotteryPlay(749, "短牌", "22", "22", "", "duanpai", "22", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_2.add(new LotteryPlay(750, "短牌", "33", "33", "", "duanpai", "33", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_2.add(new LotteryPlay(751, "短牌", "44", "44", "", "duanpai", "44", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_2.add(new LotteryPlay(752, "短牌", "55", "55", "", "duanpai", "55", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList4_2.add(new LotteryPlay(753, "短牌", "66", "66", "", "duanpai", "66", BET_LAYOUT_TYPE_RECTANGLE_K3));


        playEndList1.add(playEnd1);
        playEndList1.add(playEnd2);
        playEndList2.add(playEnd2_1);
        playEndList3.add(playEnd3_1);
        playEndList4.add(playEnd4_1);
        playEndList4.add(playEnd4_2);
        playSubs1.add(playSub1);
        playSubs1.add(playSub2);
        playSubs1.add(playSub3);
        playSubs1.add(playSub4);

        //---
        // left tab
        LotteryPlayStart playStart2 = new LotteryPlayStart("鱼虾蟹", "yuxiaxietoubao");
        List<LotteryPlaySub> playSubs2 = new ArrayList<>();
        playStart2.setSubPlays(playSubs2);

        LotteryPlaySub playSub2_1 = new LotteryPlaySub("鱼虾蟹_副", "", "yuxiaxietoubao", S_ColdHot_Omit_Odds_One);
        List<LotteryPlayEnd> playEndList2_1 = new ArrayList<>();
        playSub2_1.setLotteryPlayEnds(playEndList2_1);

        LotteryPlayEnd playEnd2_2 = new LotteryPlayEnd();
        playEnd2_2.setTag("鱼虾蟹");
        playEnd2_2.setSpanCount(2);
        playEnd2_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2_2 = new ArrayList<>();
        playEnd2_2.setLotteryPlays(lotteryPlayList2_2);
        lotteryPlayList2_2.add(new LotteryPlay(754, "鱼虾蟹", "1", "yu", "", "yuxiaxietoubao", "yu", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_2.add(new LotteryPlay(755, "鱼虾蟹", "2", "xia", "", "yuxiaxietoubao", "xia", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_2.add(new LotteryPlay(756, "鱼虾蟹", "3", "hulu", "", "yuxiaxietoubao", "hulu", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_2.add(new LotteryPlay(757, "鱼虾蟹", "4", "jinxian", "", "yuxiaxietoubao", "jinxian", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_2.add(new LotteryPlay(758, "鱼虾蟹", "5", "xie", "", "yuxiaxietoubao", "xie", BET_LAYOUT_TYPE_RECTANGLE_K3));
        lotteryPlayList2_2.add(new LotteryPlay(759, "鱼虾蟹", "6", "ji", "", "yuxiaxietoubao", "ji", BET_LAYOUT_TYPE_RECTANGLE_K3));

        playEndList2_1.add(playEnd2_2);
        playSubs2.add(playSub2_1);


        lotteryPlayStartList.add(playStart1);
        lotteryPlayStartList.add(playStart2);

        return lotteryPlayStartList;
    }

    private static List<LotteryPlayStart> createPlay_3d() {
        //  3d
        List<LotteryPlayStart> lotteryPlayStartList = new ArrayList<>();
        LotteryPlayStart playStart1 = new LotteryPlayStart("双面", "liangmian");
        List<LotteryPlaySub> playSubs1 = new ArrayList<>();
        playStart1.setSubPlays(playSubs1);

        LotteryPlaySub playSub1 = new LotteryPlaySub("双面_副", "", "liangmian", S_ColdHot);
        List<LotteryPlayEnd> playEndList1 = new ArrayList<>();
        playSub1.setLotteryPlayEnds(playEndList1);

        LotteryPlayEnd playEnd1 = new LotteryPlayEnd();
        playEnd1.setTag("百位");
        playEnd1.setRemoteCode("bai");
        playEnd1.setSpanCount(4);
        playEnd1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList1 = new ArrayList<>();
        playEnd1.setLotteryPlays(lotteryPlayList1);

        lotteryPlayList1.add(new LotteryPlay(1643, "百位大小", "大", "da", 1643, "baiweidaxiao", "da"));
        lotteryPlayList1.add(new LotteryPlay(1644, "百位大小", "小", "xiao", 1644, "baiweidaxiao", "xiao"));
        lotteryPlayList1.add(new LotteryPlay(1645, "百位单双", "单", "dan", 1645, "baiweidanshuang", "dan"));
        lotteryPlayList1.add(new LotteryPlay(1646, "百位单双", "双", "shuang", 1646, "baiweidanshuang", "shuang"));
        lotteryPlayList1.add(new LotteryPlay(1647, "百位质合", "质", "zhi", 1647, "baiweizhihe", "zhi"));
        lotteryPlayList1.add(new LotteryPlay(1648, "百位质合", "合", "hen", 1648, "baiweizhihe", "hen"));

        //--
        LotteryPlayEnd playEnd2 = new LotteryPlayEnd();
        playEnd2.setTag("十位");
        playEnd2.setRemoteCode("shi");
        playEnd2.setSpanCount(4);
        playEnd2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList2 = new ArrayList<>();
        playEnd2.setLotteryPlays(lotteryPlayList2);
        lotteryPlayList2.add(new LotteryPlay(1649, "十位大小", "大", "da", 1649, "shiweidaxiao", "da"));
        lotteryPlayList2.add(new LotteryPlay(1650, "十位大小", "小", "xiao", 1650, "shiweidaxiao", "xiao"));
        lotteryPlayList2.add(new LotteryPlay(1651, "十位单双", "单", "dan", 1651, "shiweidanshuang", "dan"));
        lotteryPlayList2.add(new LotteryPlay(1652, "十位单双", "双", "shuang", 1652, "shiweidanshuang", "shuang"));
        lotteryPlayList2.add(new LotteryPlay(1653, "十位质合", "质", "zhi", 1653, "shiweizhihe", "zhi"));
        lotteryPlayList2.add(new LotteryPlay(1654, "十位质合", "合", "hen", 1654, "shiweizhihe", "hen"));

        //--
        LotteryPlayEnd playEnd3 = new LotteryPlayEnd();
        playEnd3.setTag("个位");
        playEnd3.setRemoteCode("ge");
        playEnd3.setSpanCount(4);
        playEnd3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3 = new ArrayList<>();
        playEnd3.setLotteryPlays(lotteryPlayList3);
        lotteryPlayList3.add(new LotteryPlay(1655, "个位大小", "大", "da", 1655, "geweidaxiao", "da"));
        lotteryPlayList3.add(new LotteryPlay(1656, "个位大小", "小", "xiao", 1656, "geweidaxiao", "xiao"));
        lotteryPlayList3.add(new LotteryPlay(1657, "个位单双", "单", "dan", 1657, "geweidanshuang", "dan"));
        lotteryPlayList3.add(new LotteryPlay(1658, "个位单双", "双", "shuang", 1658, "geweidanshuang", "shuang"));
        lotteryPlayList3.add(new LotteryPlay(1659, "个位质合", "质", "zhi", 1659, "geweizhihe", "zhi"));
        lotteryPlayList3.add(new LotteryPlay(1660, "个位质合", "合", "hen", 1660, "geweizhihe", "hen"));

        //--
        LotteryPlayEnd playEnd4 = new LotteryPlayEnd();
        playEnd4.setTag("百十");
        playEnd4.setRemoteCode("baishi");
        playEnd4.setSpanCount(4);
        playEnd4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4 = new ArrayList<>();
        playEnd4.setLotteryPlays(lotteryPlayList4);
        lotteryPlayList4.add(new LotteryPlay(1661, "百十位和单双", "和单", "hedan", 1661, "baishiweihedanshuang", "hedan"));
        lotteryPlayList4.add(new LotteryPlay(1662, "百十位和单双", "和双", "heshuang", 1662, "baishiweihedanshuang", "heshuang"));
        lotteryPlayList4.add(new LotteryPlay(1663, "百十位和尾大小", "和尾大", "heweida", 1663, "baishiweiheweidaxiao", "heweida"));
        lotteryPlayList4.add(new LotteryPlay(1664, "百十位和尾大小", "和尾小", "heweixiao", 1664, "baishiweiheweidaxiao", "heweixiao"));
        lotteryPlayList4.add(new LotteryPlay(1665, "百十位和尾质合", "和尾质", "heweizhi", 1665, "baishiweiheweizhihe", "heweizhi"));
        lotteryPlayList4.add(new LotteryPlay(1666, "百十位和尾质合", "和尾合", "heweihe", 1666, "baishiweiheweizhihe", "heweihe"));

        //--
        LotteryPlayEnd playEnd5 = new LotteryPlayEnd();
        playEnd5.setTag("百个");
        playEnd5.setRemoteCode("baige");
        playEnd5.setSpanCount(4);
        playEnd5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5 = new ArrayList<>();
        playEnd5.setLotteryPlays(lotteryPlayList5);
        lotteryPlayList5.add(new LotteryPlay(1667, "百个位和单双", "和单", "hedan", 1667, "baigeweihedanshuang", "hedan"));
        lotteryPlayList5.add(new LotteryPlay(1668, "百个位和单双", "和双", "heshuang", 1668, "baigeweihedanshuang", "heshuang"));
        lotteryPlayList5.add(new LotteryPlay(1669, "百个位和尾大小", "和尾大", "heweida", 1669, "baigeweiheweidaxiao", "heweida"));
        lotteryPlayList5.add(new LotteryPlay(1670, "百个位和尾大小", "和尾小", "heweixiao", 1670, "baigeweiheweidaxiao", "heweixiao"));
        lotteryPlayList5.add(new LotteryPlay(1671, "百个位和尾质合", "和尾质", "heweizhi", 1671, "baigeweiheweizhihe", "heweizhi"));
        lotteryPlayList5.add(new LotteryPlay(1672, "百个位和尾质合", "和尾合", "heweihe", 1672, "baigeweiheweizhihe", "heweihe"));

        //--
        LotteryPlayEnd playEnd6 = new LotteryPlayEnd();
        playEnd6.setTag("十个");
        playEnd6.setRemoteCode("shige");
        playEnd6.setSpanCount(4);
        playEnd6.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList6 = new ArrayList<>();
        playEnd6.setLotteryPlays(lotteryPlayList6);
        lotteryPlayList6.add(new LotteryPlay(1673, "十个位和单双", "和单", "hedan", 1673, "shigeweihedanshuang", "hedan"));
        lotteryPlayList6.add(new LotteryPlay(1674, "十个位和单双", "和双", "heshuang", 1674, "shigeweihedanshuang", "heshuang"));
        lotteryPlayList6.add(new LotteryPlay(1675, "十个位和尾大小", "和尾大", "heweida", 1675, "shigeweiheweidaxiao", "heweida"));
        lotteryPlayList6.add(new LotteryPlay(1676, "十个位和尾大小", "和尾小", "heweixiao", 1676, "shigeweiheweidaxiao", "heweixiao"));
        lotteryPlayList6.add(new LotteryPlay(1677, "十个位和尾质合", "和尾质", "heweizhi", 1677, "shigeweiheweizhihe", "heweizhi"));
        lotteryPlayList6.add(new LotteryPlay(1678, "十个位和尾质合", "和尾合", "heweihe", 1678, "shigeweiheweizhihe", "heweihe"));

        //--
        LotteryPlayEnd playEnd7 = new LotteryPlayEnd();
        playEnd7.setTag("百十个");
        playEnd7.setRemoteCode("baishige");
        playEnd7.setSpanCount(4);
        playEnd7.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList7 = new ArrayList<>();
        playEnd7.setLotteryPlays(lotteryPlayList7);
        lotteryPlayList7.add(new LotteryPlay(2105, "百十个位和大小", "和大", "heda", 2105, "baishigeweihedaxiao", "heda"));
        lotteryPlayList7.add(new LotteryPlay(2106, "百十个位和大小", "和小", "hexiao", 2106, "baishigeweihedaxiao", "hexiao"));
        lotteryPlayList7.add(new LotteryPlay(1679, "百十个位和单双", "和单", "hedan", 1679, "baishigeweihedanshuang", "hedan"));
        lotteryPlayList7.add(new LotteryPlay(1680, "百十个位和单双", "和双", "heshuang", 1680, "baishigeweihedanshuang", "heshuang"));
        lotteryPlayList7.add(new LotteryPlay(1681, "百十个位和尾大小", "和尾大", "heweida", 1681, "baishigeweiheweidaxiao", "heweida"));
        lotteryPlayList7.add(new LotteryPlay(1682, "百十个位和尾大小", "和尾小", "heweixiao", 1682, "baishigeweiheweidaxiao", "heweixiao"));
        lotteryPlayList7.add(new LotteryPlay(1683, "百十个位和尾质合", "和尾质", "heweizhi", 1683, "baishigeweiheweizhihe", "heweizhi"));
        lotteryPlayList7.add(new LotteryPlay(1684, "百十个位和尾质合", "和尾合", "heweihe", 1684, "baishigeweiheweizhihe", "heweihe"));

        //--
        LotteryPlayStart playStart2 = new LotteryPlayStart("一字组合", "yizizuhe");
        List<LotteryPlaySub> playSubs2 = new ArrayList<>();
        playStart2.setSubPlays(playSubs2);

        LotteryPlaySub playSub2 = new LotteryPlaySub("一字组合_副", "", "yizizuhe", "");
        List<LotteryPlayEnd> playEndList2 = new ArrayList<>();
        playSub2.setLotteryPlayEnds(playEndList2);

        LotteryPlayEnd playEnd2_1 = new LotteryPlayEnd();
        playEnd2_1.setTag("一字组合");
        playEnd2_1.setRemoteCode("yizizuhe");
        playEnd2_1.setSpanCount(4);
        playEnd2_1.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList2_1 = new ArrayList<>();
        playEnd2_1.setLotteryPlays(lotteryPlayList2_1);
        lotteryPlayList2_1.add(new LotteryPlay(1685, "一字组合", "0", "0", "", "yizizuhe", "0"));
        lotteryPlayList2_1.add(new LotteryPlay(1686, "一字组合", "1", "1", "", "yizizuhe", "1"));
        lotteryPlayList2_1.add(new LotteryPlay(1687, "一字组合", "2", "2", "", "yizizuhe", "2"));
        lotteryPlayList2_1.add(new LotteryPlay(1688, "一字组合", "3", "3", "", "yizizuhe", "3"));
        lotteryPlayList2_1.add(new LotteryPlay(1689, "一字组合", "4", "4", "", "yizizuhe", "4"));
        lotteryPlayList2_1.add(new LotteryPlay(1690, "一字组合", "5", "5", "", "yizizuhe", "5"));
        lotteryPlayList2_1.add(new LotteryPlay(1691, "一字组合", "6", "6", "", "yizizuhe", "6"));
        lotteryPlayList2_1.add(new LotteryPlay(1692, "一字组合", "7", "7", "", "yizizuhe", "7"));
        lotteryPlayList2_1.add(new LotteryPlay(1693, "一字组合", "8", "8", "", "yizizuhe", "8"));
        lotteryPlayList2_1.add(new LotteryPlay(1694, "一字组合", "9", "9", "", "yizizuhe", "9"));

        //--
        LotteryPlayStart playStart3 = new LotteryPlayStart("二字组合", "liangzizuhe");
        List<LotteryPlaySub> playSubs3 = new ArrayList<>();
        playStart3.setSubPlays(playSubs3);

        LotteryPlaySub playSub3 = new LotteryPlaySub("二字组合_副", "", "erzizuhe", "");
        List<LotteryPlayEnd> playEndList3 = new ArrayList<>();
        playSub3.setLotteryPlayEnds(playEndList3);

        LotteryPlayEnd playEnd3_1 = new LotteryPlayEnd();
        playEnd3_1.setTag("0字头");
        playEnd3_1.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_1.setSpanCount(4);
        playEnd3_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_1 = new ArrayList<>();
        playEnd3_1.setLotteryPlays(lotteryPlayList3_1);
        lotteryPlayList3_1.add(new LotteryPlay(1749, "重号二字组合", "00", "00", "", "erzizuhe_zhonghaoerzizuhe", "00"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "01", "01", "", "erzizuhe_feizhonghaoerzizuhe", "01"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "02", "02", "", "erzizuhe_feizhonghaoerzizuhe", "02"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "03", "03", "", "erzizuhe_feizhonghaoerzizuhe", "03"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "04", "04", "", "erzizuhe_feizhonghaoerzizuhe", "04"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "05", "05", "", "erzizuhe_feizhonghaoerzizuhe", "05"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "06", "06", "", "erzizuhe_feizhonghaoerzizuhe", "06"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "07", "07", "", "erzizuhe_feizhonghaoerzizuhe", "07"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "08", "08", "", "erzizuhe_feizhonghaoerzizuhe", "08"));
        lotteryPlayList3_1.add(new LotteryPlay(1749, "二字组合", "09", "09", "", "erzizuhe_feizhonghaoerzizuhe", "09"));

        LotteryPlayEnd playEnd3_2 = new LotteryPlayEnd();
        playEnd3_2.setTag("1字头");
        playEnd3_2.setRemoteCode("");//todo  新code 未知  erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_2.setSpanCount(4);
        playEnd3_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_2 = new ArrayList<>();
        playEnd3_2.setLotteryPlays(lotteryPlayList3_2);
        lotteryPlayList3_2.add(new LotteryPlay(1749, "重号二字组合", "11", "11", "", "erzizuhe_zhonghaoerzizuhe", "11"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "12", "12", "", "erzizuhe_feizhonghaoerzizuhe", "12"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "13", "13", "", "erzizuhe_feizhonghaoerzizuhe", "13"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "14", "14", "", "erzizuhe_feizhonghaoerzizuhe", "14"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "15", "15", "", "erzizuhe_feizhonghaoerzizuhe", "15"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "16", "16", "", "erzizuhe_feizhonghaoerzizuhe", "16"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "17", "17", "", "erzizuhe_feizhonghaoerzizuhe", "17"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "18", "18", "", "erzizuhe_feizhonghaoerzizuhe", "18"));
        lotteryPlayList3_2.add(new LotteryPlay(1749, "二字组合", "19", "19", "", "erzizuhe_feizhonghaoerzizuhe", "19"));

        LotteryPlayEnd playEnd3_3 = new LotteryPlayEnd();
        playEnd3_3.setTag("2字头");
        playEnd3_3.setRemoteCode("");//todo  新code 未知  erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_3.setSpanCount(4);
        playEnd3_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_3 = new ArrayList<>();
        playEnd3_3.setLotteryPlays(lotteryPlayList3_3);
        lotteryPlayList3_3.add(new LotteryPlay(1749, "重号二字组合", "22", "22", "", "erzizuhe_zhonghaoerzizuhe", "22"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "23", "23", "", "erzizuhe_feizhonghaoerzizuhe", "23"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "24", "24", "", "erzizuhe_feizhonghaoerzizuhe", "24"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "25", "25", "", "erzizuhe_feizhonghaoerzizuhe", "25"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "26", "26", "", "erzizuhe_feizhonghaoerzizuhe", "26"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "27", "27", "", "erzizuhe_feizhonghaoerzizuhe", "27"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "28", "28", "", "erzizuhe_feizhonghaoerzizuhe", "28"));
        lotteryPlayList3_3.add(new LotteryPlay(1749, "二字组合", "29", "29", "", "erzizuhe_feizhonghaoerzizuhe", "29"));

        LotteryPlayEnd playEnd3_4 = new LotteryPlayEnd();
        playEnd3_4.setTag("3字头");
        playEnd3_4.setRemoteCode("");//todo  新code 未知  erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_4.setSpanCount(4);
        playEnd3_4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_4 = new ArrayList<>();
        playEnd3_4.setLotteryPlays(lotteryPlayList3_4);
        lotteryPlayList3_4.add(new LotteryPlay(1749, "重号二字组合", "33", "33", "", "erzizuhe_zhonghaoerzizuhe", "33"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "34", "34", "", "erzizuhe_feizhonghaoerzizuhe", "34"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "35", "35", "", "erzizuhe_feizhonghaoerzizuhe", "35"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "36", "36", "", "erzizuhe_feizhonghaoerzizuhe", "36"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "37", "37", "", "erzizuhe_feizhonghaoerzizuhe", "37"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "38", "38", "", "erzizuhe_feizhonghaoerzizuhe", "38"));
        lotteryPlayList3_4.add(new LotteryPlay(1749, "二字组合", "39", "39", "", "erzizuhe_feizhonghaoerzizuhe", "39"));

        LotteryPlayEnd playEnd3_5 = new LotteryPlayEnd();
        playEnd3_5.setTag("4字头");
        playEnd3_5.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_5.setSpanCount(4);
        playEnd3_5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_5 = new ArrayList<>();
        playEnd3_5.setLotteryPlays(lotteryPlayList3_5);
        lotteryPlayList3_5.add(new LotteryPlay(1749, "重号二字组合", "44", "44", "", "erzizuhe_zhonghaoerzizuhe", "44"));
        lotteryPlayList3_5.add(new LotteryPlay(1749, "二字组合", "45", "45", "", "erzizuhe_feizhonghaoerzizuhe", "45"));
        lotteryPlayList3_5.add(new LotteryPlay(1749, "二字组合", "46", "46", "", "erzizuhe_feizhonghaoerzizuhe", "46"));
        lotteryPlayList3_5.add(new LotteryPlay(1749, "二字组合", "47", "47", "", "erzizuhe_feizhonghaoerzizuhe", "47"));
        lotteryPlayList3_5.add(new LotteryPlay(1749, "二字组合", "48", "48", "", "erzizuhe_feizhonghaoerzizuhe", "48"));
        lotteryPlayList3_5.add(new LotteryPlay(1749, "二字组合", "49", "49", "", "erzizuhe_feizhonghaoerzizuhe", "49"));

        LotteryPlayEnd playEnd3_6 = new LotteryPlayEnd();
        playEnd3_6.setTag("5字头");
        playEnd3_6.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_6.setSpanCount(4);
        playEnd3_6.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_6 = new ArrayList<>();
        playEnd3_6.setLotteryPlays(lotteryPlayList3_6);
        lotteryPlayList3_6.add(new LotteryPlay(1749, "重号二字组合", "55", "55", "", "erzizuhe_zhonghaoerzizuhe", "55"));
        lotteryPlayList3_6.add(new LotteryPlay(1749, "二字组合", "56", "56", "", "erzizuhe_feizhonghaoerzizuhe", "56"));
        lotteryPlayList3_6.add(new LotteryPlay(1749, "二字组合", "57", "57", "", "erzizuhe_feizhonghaoerzizuhe", "57"));
        lotteryPlayList3_6.add(new LotteryPlay(1749, "二字组合", "58", "58", "", "erzizuhe_feizhonghaoerzizuhe", "58"));
        lotteryPlayList3_6.add(new LotteryPlay(1749, "二字组合", "59", "59", "", "erzizuhe_feizhonghaoerzizuhe", "59"));

        LotteryPlayEnd playEnd3_7 = new LotteryPlayEnd();
        playEnd3_7.setTag("6字头");
        playEnd3_7.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_7.setSpanCount(4);
        playEnd3_7.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_7 = new ArrayList<>();
        playEnd3_7.setLotteryPlays(lotteryPlayList3_7);
        lotteryPlayList3_7.add(new LotteryPlay(1749, "重号二字组合", "66", "66", "", "erzizuhe_zhonghaoerzizuhe", "66"));
        lotteryPlayList3_7.add(new LotteryPlay(1749, "二字组合", "67", "67", "", "erzizuhe_feizhonghaoerzizuhe", "67"));
        lotteryPlayList3_7.add(new LotteryPlay(1749, "二字组合", "68", "68", "", "erzizuhe_feizhonghaoerzizuhe", "68"));
        lotteryPlayList3_7.add(new LotteryPlay(1749, "二字组合", "69", "69", "", "erzizuhe_feizhonghaoerzizuhe", "69"));

        LotteryPlayEnd playEnd3_8 = new LotteryPlayEnd();
        playEnd3_8.setTag("7字头");
        playEnd3_8.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_8.setSpanCount(4);
        playEnd3_8.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_8 = new ArrayList<>();
        playEnd3_8.setLotteryPlays(lotteryPlayList3_8);
        lotteryPlayList3_8.add(new LotteryPlay(1749, "重号二字组合", "77", "77", "", "erzizuhe_zhonghaoerzizuhe", "77"));
        lotteryPlayList3_8.add(new LotteryPlay(1749, "二字组合", "78", "78", "", "erzizuhe_feizhonghaoerzizuhe", "78"));
        lotteryPlayList3_8.add(new LotteryPlay(1749, "二字组合", "79", "79", "", "erzizuhe_feizhonghaoerzizuhe", "79"));

        LotteryPlayEnd playEnd3_9 = new LotteryPlayEnd();
        playEnd3_9.setTag("8字头");
        playEnd3_9.setRemoteCode("");//todo  新code 未知   erzizuhe_zhonghaoerzizuhe 和 erzizuhe_feizhonghaoerzizuhe
        playEnd3_9.setSpanCount(4);
        playEnd3_9.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_9 = new ArrayList<>();
        playEnd3_9.setLotteryPlays(lotteryPlayList3_9);
        lotteryPlayList3_9.add(new LotteryPlay(1749, "重号二字组合", "88", "88", "", "erzizuhe_zhonghaoerzizuhe", "88"));
        lotteryPlayList3_9.add(new LotteryPlay(1749, "二字组合", "89", "89", "", "erzizuhe_feizhonghaoerzizuhe", "89"));

        LotteryPlayEnd playEnd3_10 = new LotteryPlayEnd();
        playEnd3_10.setTag("9字头");
        playEnd3_10.setRemoteCode("erzizuhe_zhonghaoerzizuhe");
        playEnd3_10.setSpanCount(4);
        playEnd3_10.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList3_10 = new ArrayList<>();
        playEnd3_10.setLotteryPlays(lotteryPlayList3_10);
        lotteryPlayList3_10.add(new LotteryPlay(1749, "重号二字组合", "99", "99", "", "erzizuhe_zhonghaoerzizuhe", "99"));

        //--
        LotteryPlayStart playStart4 = new LotteryPlayStart("三字组合", "sanzizuhe");
        List<LotteryPlaySub> playSubs4 = new ArrayList<>();
        playStart4.setSubPlays(playSubs4);

        LotteryPlaySub playSub4 = new LotteryPlaySub("三字组合_副", "", "sanzizuhe", "");
        List<LotteryPlayEnd> playEndList4 = new ArrayList<>();
        playSub4.setLotteryPlayEnds(playEndList4);

        LotteryPlayEnd playEnd4_1 = new LotteryPlayEnd();
        playEnd4_1.setTag("0字头");
        playEnd4_1.setRemoteCode("");//todo  新code 未知
        playEnd4_1.setSpanCount(4);
        playEnd4_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_1 = new ArrayList<>();
        playEnd4_1.setLotteryPlays(lotteryPlayList4_1);
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "000", "000", "", "sanzizuhe_baozisanzizuhe", "000"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "001", "001", "", "sanzizuhe_zusansanzizuhe", "001"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "002", "002", "", "sanzizuhe_zusansanzizuhe", "002"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "003", "003", "", "sanzizuhe_zusansanzizuhe", "003"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "004", "004", "", "sanzizuhe_zusansanzizuhe", "004"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "005", "005", "", "sanzizuhe_zusansanzizuhe", "005"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "006", "006", "", "sanzizuhe_zusansanzizuhe", "006"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "007", "007", "", "sanzizuhe_zusansanzizuhe", "007"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "008", "008", "", "sanzizuhe_zusansanzizuhe", "008"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "009", "009", "", "sanzizuhe_zusansanzizuhe", "009"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "011", "011", "", "sanzizuhe_zusansanzizuhe", "011"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "012", "012", "", "sanzizuhe_zuliusanzizuhe", "012"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "013", "013", "", "sanzizuhe_zuliusanzizuhe", "013"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "014", "014", "", "sanzizuhe_zuliusanzizuhe", "014"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "015", "015", "", "sanzizuhe_zuliusanzizuhe", "015"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "016", "016", "", "sanzizuhe_zuliusanzizuhe", "016"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "017", "017", "", "sanzizuhe_zuliusanzizuhe", "017"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "018", "018", "", "sanzizuhe_zuliusanzizuhe", "018"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "019", "019", "", "sanzizuhe_zuliusanzizuhe", "019"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "022", "022", "", "sanzizuhe_zusansanzizuhe", "022"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "023", "023", "", "sanzizuhe_zuliusanzizuhe", "023"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "024", "024", "", "sanzizuhe_zuliusanzizuhe", "024"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "025", "025", "", "sanzizuhe_zuliusanzizuhe", "025"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "026", "026", "", "sanzizuhe_zuliusanzizuhe", "026"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "027", "027", "", "sanzizuhe_zuliusanzizuhe", "027"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "028", "028", "", "sanzizuhe_zuliusanzizuhe", "028"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "029", "029", "", "sanzizuhe_zuliusanzizuhe", "029"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "033", "033", "", "sanzizuhe_zusansanzizuhe", "033"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "034", "034", "", "sanzizuhe_zuliusanzizuhe", "034"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "035", "035", "", "sanzizuhe_zuliusanzizuhe", "035"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "036", "036", "", "sanzizuhe_zuliusanzizuhe", "036"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "037", "037", "", "sanzizuhe_zuliusanzizuhe", "037"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "038", "038", "", "sanzizuhe_zuliusanzizuhe", "038"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "039", "039", "", "sanzizuhe_zuliusanzizuhe", "039"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "044", "044", "", "sanzizuhe_zusansanzizuhe", "044"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "045", "045", "", "sanzizuhe_zuliusanzizuhe", "045"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "046", "046", "", "sanzizuhe_zuliusanzizuhe", "046"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "047", "047", "", "sanzizuhe_zuliusanzizuhe", "047"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "048", "048", "", "sanzizuhe_zuliusanzizuhe", "048"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "049", "049", "", "sanzizuhe_zuliusanzizuhe", "049"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "055", "055", "", "sanzizuhe_zusansanzizuhe", "055"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "056", "056", "", "sanzizuhe_zuliusanzizuhe", "056"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "057", "057", "", "sanzizuhe_zuliusanzizuhe", "057"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "058", "058", "", "sanzizuhe_zuliusanzizuhe", "058"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "059", "059", "", "sanzizuhe_zuliusanzizuhe", "059"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "066", "066", "", "sanzizuhe_zusansanzizuhe", "066"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "067", "067", "", "sanzizuhe_zuliusanzizuhe", "067"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "068", "068", "", "sanzizuhe_zuliusanzizuhe", "068"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "069", "069", "", "sanzizuhe_zuliusanzizuhe", "069"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "077", "077", "", "sanzizuhe_zusansanzizuhe", "077"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "078", "078", "", "sanzizuhe_zuliusanzizuhe", "078"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "079", "079", "", "sanzizuhe_zuliusanzizuhe", "079"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "088", "088", "", "sanzizuhe_zusansanzizuhe", "088"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "089", "089", "", "sanzizuhe_zuliusanzizuhe", "089"));
        lotteryPlayList4_1.add(new LotteryPlay(1750, "三字组合", "099", "099", "", "sanzizuhe_zusansanzizuhe", "099"));

        LotteryPlayEnd playEnd4_2 = new LotteryPlayEnd();
        playEnd4_2.setTag("1字头");
        playEnd4_2.setRemoteCode("");//todo  新code 未知
        playEnd4_2.setSpanCount(4);
        playEnd4_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_2 = new ArrayList<>();
        playEnd4_2.setLotteryPlays(lotteryPlayList4_2);
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "111", "111", "", "sanzizuhe_baozisanzizuhe", "111"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "112", "112", "", "sanzizuhe_zusansanzizuhe", "112"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "113", "113", "", "sanzizuhe_zusansanzizuhe", "113"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "114", "114", "", "sanzizuhe_zusansanzizuhe", "114"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "115", "115", "", "sanzizuhe_zusansanzizuhe", "115"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "116", "116", "", "sanzizuhe_zusansanzizuhe", "116"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "117", "117", "", "sanzizuhe_zusansanzizuhe", "117"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "118", "118", "", "sanzizuhe_zusansanzizuhe", "118"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "119", "119", "", "sanzizuhe_zusansanzizuhe", "119"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "122", "122", "", "sanzizuhe_zusansanzizuhe", "122"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "123", "123", "", "sanzizuhe_zuliusanzizuhe", "123"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "124", "124", "", "sanzizuhe_zuliusanzizuhe", "124"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "125", "125", "", "sanzizuhe_zuliusanzizuhe", "125"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "126", "126", "", "sanzizuhe_zuliusanzizuhe", "126"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "127", "127", "", "sanzizuhe_zuliusanzizuhe", "127"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "128", "128", "", "sanzizuhe_zuliusanzizuhe", "128"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "129", "129", "", "sanzizuhe_zuliusanzizuhe", "129"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "133", "133", "", "sanzizuhe_zusansanzizuhe", "133"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "134", "134", "", "sanzizuhe_zuliusanzizuhe", "134"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "135", "135", "", "sanzizuhe_zuliusanzizuhe", "135"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "136", "136", "", "sanzizuhe_zuliusanzizuhe", "136"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "137", "137", "", "sanzizuhe_zuliusanzizuhe", "137"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "138", "138", "", "sanzizuhe_zuliusanzizuhe", "138"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "139", "139", "", "sanzizuhe_zuliusanzizuhe", "139"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "144", "144", "", "sanzizuhe_zusansanzizuhe", "144"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "145", "145", "", "sanzizuhe_zuliusanzizuhe", "145"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "146", "146", "", "sanzizuhe_zuliusanzizuhe", "146"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "147", "147", "", "sanzizuhe_zuliusanzizuhe", "147"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "148", "148", "", "sanzizuhe_zuliusanzizuhe", "148"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "149", "149", "", "sanzizuhe_zuliusanzizuhe", "149"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "155", "155", "", "sanzizuhe_zusansanzizuhe", "155"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "156", "156", "", "sanzizuhe_zuliusanzizuhe", "156"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "157", "157", "", "sanzizuhe_zuliusanzizuhe", "157"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "158", "158", "", "sanzizuhe_zuliusanzizuhe", "158"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "159", "159", "", "sanzizuhe_zuliusanzizuhe", "159"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "166", "166", "", "sanzizuhe_zusansanzizuhe", "166"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "167", "167", "", "sanzizuhe_zuliusanzizuhe", "167"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "168", "168", "", "sanzizuhe_zuliusanzizuhe", "168"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "169", "169", "", "sanzizuhe_zuliusanzizuhe", "169"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "177", "177", "", "sanzizuhe_zusansanzizuhe", "177"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "178", "178", "", "sanzizuhe_zuliusanzizuhe", "178"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "179", "179", "", "sanzizuhe_zuliusanzizuhe", "179"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "188", "188", "", "sanzizuhe_zusansanzizuhe", "188"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "189", "189", "", "sanzizuhe_zuliusanzizuhe", "189"));
        lotteryPlayList4_2.add(new LotteryPlay(1750, "三字组合", "199", "199", "", "sanzizuhe_zusansanzizuhe", "199"));

        LotteryPlayEnd playEnd4_3 = new LotteryPlayEnd();
        playEnd4_3.setTag("2字头");
        playEnd4_3.setRemoteCode("");//todo  新code 未知
        playEnd4_3.setSpanCount(4);
        playEnd4_3.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_3 = new ArrayList<>();
        playEnd4_3.setLotteryPlays(lotteryPlayList4_3);
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "222", "222", "", "sanzizuhe_baozisanzizuhe", "222"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "223", "223", "", "sanzizuhe_zusansanzizuhe", "223"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "224", "224", "", "sanzizuhe_zusansanzizuhe", "224"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "225", "225", "", "sanzizuhe_zusansanzizuhe", "225"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "226", "226", "", "sanzizuhe_zusansanzizuhe", "226"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "227", "227", "", "sanzizuhe_zusansanzizuhe", "227"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "228", "228", "", "sanzizuhe_zusansanzizuhe", "228"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "229", "229", "", "sanzizuhe_zusansanzizuhe", "229"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "233", "233", "", "sanzizuhe_zusansanzizuhe", "233"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "234", "234", "", "sanzizuhe_zuliusanzizuhe", "234"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "235", "235", "", "sanzizuhe_zuliusanzizuhe", "235"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "236", "236", "", "sanzizuhe_zuliusanzizuhe", "236"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "237", "237", "", "sanzizuhe_zuliusanzizuhe", "237"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "238", "238", "", "sanzizuhe_zuliusanzizuhe", "238"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "239", "239", "", "sanzizuhe_zuliusanzizuhe", "239"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "244", "244", "", "sanzizuhe_zusansanzizuhe", "244"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "245", "245", "", "sanzizuhe_zuliusanzizuhe", "245"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "246", "246", "", "sanzizuhe_zuliusanzizuhe", "246"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "247", "247", "", "sanzizuhe_zuliusanzizuhe", "247"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "248", "248", "", "sanzizuhe_zuliusanzizuhe", "248"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "249", "249", "", "sanzizuhe_zuliusanzizuhe", "249"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "255", "255", "", "sanzizuhe_zusansanzizuhe", "255"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "256", "256", "", "sanzizuhe_zuliusanzizuhe", "256"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "257", "257", "", "sanzizuhe_zuliusanzizuhe", "257"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "258", "258", "", "sanzizuhe_zuliusanzizuhe", "258"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "259", "259", "", "sanzizuhe_zuliusanzizuhe", "259"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "266", "266", "", "sanzizuhe_zusansanzizuhe", "266"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "267", "267", "", "sanzizuhe_zuliusanzizuhe", "267"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "268", "268", "", "sanzizuhe_zuliusanzizuhe", "268"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "269", "269", "", "sanzizuhe_zuliusanzizuhe", "269"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "277", "277", "", "sanzizuhe_zusansanzizuhe", "277"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "278", "278", "", "sanzizuhe_zuliusanzizuhe", "278"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "279", "279", "", "sanzizuhe_zuliusanzizuhe", "279"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "288", "288", "", "sanzizuhe_zusansanzizuhe", "288"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "289", "289", "", "sanzizuhe_zuliusanzizuhe", "289"));
        lotteryPlayList4_3.add(new LotteryPlay(1750, "三字组合", "299", "299", "", "sanzizuhe_zusansanzizuhe", "299"));

        LotteryPlayEnd playEnd4_4 = new LotteryPlayEnd();
        playEnd4_4.setTag("3字头");
        playEnd4_4.setRemoteCode("");//todo  新code 未知
        playEnd4_4.setSpanCount(4);
        playEnd4_4.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_4 = new ArrayList<>();
        playEnd4_4.setLotteryPlays(lotteryPlayList4_4);
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "333", "333", "", "sanzizuhe_baozisanzizuhe", "333"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "334", "334", "", "sanzizuhe_zusansanzizuhe", "334"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "335", "335", "", "sanzizuhe_zusansanzizuhe", "335"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "336", "336", "", "sanzizuhe_zusansanzizuhe", "336"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "337", "337", "", "sanzizuhe_zusansanzizuhe", "337"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "338", "338", "", "sanzizuhe_zusansanzizuhe", "338"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "339", "339", "", "sanzizuhe_zusansanzizuhe", "339"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "344", "344", "", "sanzizuhe_zusansanzizuhe", "344"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "345", "345", "", "sanzizuhe_zuliusanzizuhe", "345"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "346", "346", "", "sanzizuhe_zuliusanzizuhe", "346"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "347", "347", "", "sanzizuhe_zuliusanzizuhe", "347"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "348", "348", "", "sanzizuhe_zuliusanzizuhe", "348"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "349", "349", "", "sanzizuhe_zuliusanzizuhe", "349"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "355", "355", "", "sanzizuhe_zusansanzizuhe", "355"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "356", "356", "", "sanzizuhe_zuliusanzizuhe", "356"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "357", "357", "", "sanzizuhe_zuliusanzizuhe", "357"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "358", "358", "", "sanzizuhe_zuliusanzizuhe", "358"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "359", "359", "", "sanzizuhe_zuliusanzizuhe", "359"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "366", "366", "", "sanzizuhe_zusansanzizuhe", "366"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "367", "367", "", "sanzizuhe_zuliusanzizuhe", "367"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "368", "368", "", "sanzizuhe_zuliusanzizuhe", "368"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "369", "369", "", "sanzizuhe_zuliusanzizuhe", "369"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "377", "377", "", "sanzizuhe_zusansanzizuhe", "377"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "378", "378", "", "sanzizuhe_zuliusanzizuhe", "378"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "379", "379", "", "sanzizuhe_zuliusanzizuhe", "379"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "388", "388", "", "sanzizuhe_zusansanzizuhe", "388"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "389", "389", "", "sanzizuhe_zuliusanzizuhe", "389"));
        lotteryPlayList4_4.add(new LotteryPlay(1750, "三字组合", "399", "399", "", "sanzizuhe_zusansanzizuhe", "399"));

        LotteryPlayEnd playEnd4_5 = new LotteryPlayEnd();
        playEnd4_5.setTag("4字头");
        playEnd4_5.setRemoteCode("");//todo  新code 未知
        playEnd4_5.setSpanCount(4);
        playEnd4_5.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_5 = new ArrayList<>();
        playEnd4_5.setLotteryPlays(lotteryPlayList4_5);
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "444", "444", "", "sanzizuhe_baozisanzizuhe", "444"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "445", "445", "", "sanzizuhe_zusansanzizuhe", "445"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "446", "446", "", "sanzizuhe_zusansanzizuhe", "446"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "447", "447", "", "sanzizuhe_zusansanzizuhe", "447"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "448", "448", "", "sanzizuhe_zusansanzizuhe", "448"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "449", "449", "", "sanzizuhe_zusansanzizuhe", "449"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "455", "455", "", "sanzizuhe_zusansanzizuhe", "455"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "456", "456", "", "sanzizuhe_zuliusanzizuhe", "456"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "457", "457", "", "sanzizuhe_zuliusanzizuhe", "457"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "458", "458", "", "sanzizuhe_zuliusanzizuhe", "458"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "459", "459", "", "sanzizuhe_zuliusanzizuhe", "459"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "466", "466", "", "sanzizuhe_zusansanzizuhe", "466"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "467", "467", "", "sanzizuhe_zuliusanzizuhe", "467"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "468", "468", "", "sanzizuhe_zuliusanzizuhe", "468"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "469", "469", "", "sanzizuhe_zuliusanzizuhe", "469"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "477", "477", "", "sanzizuhe_zusansanzizuhe", "477"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "478", "478", "", "sanzizuhe_zuliusanzizuhe", "478"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "479", "479", "", "sanzizuhe_zuliusanzizuhe", "479"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "488", "488", "", "sanzizuhe_zusansanzizuhe", "488"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "489", "489", "", "sanzizuhe_zuliusanzizuhe", "489"));
        lotteryPlayList4_5.add(new LotteryPlay(1750, "三字组合", "499", "499", "", "sanzizuhe_zusansanzizuhe", "499"));

        LotteryPlayEnd playEnd4_6 = new LotteryPlayEnd();
        playEnd4_6.setTag("5字头");
        playEnd4_6.setRemoteCode("");//todo  新code 未知
        playEnd4_6.setSpanCount(4);
        playEnd4_6.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_6 = new ArrayList<>();
        playEnd4_6.setLotteryPlays(lotteryPlayList4_6);
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "555", "555", "", "sanzizuhe_baozisanzizuhe", "555"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "556", "556", "", "sanzizuhe_zusansanzizuhe", "556"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "557", "557", "", "sanzizuhe_zusansanzizuhe", "557"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "558", "558", "", "sanzizuhe_zusansanzizuhe", "558"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "559", "559", "", "sanzizuhe_zusansanzizuhe", "559"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "566", "566", "", "sanzizuhe_zusansanzizuhe", "566"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "567", "567", "", "sanzizuhe_zuliusanzizuhe", "567"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "568", "568", "", "sanzizuhe_zuliusanzizuhe", "568"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "569", "569", "", "sanzizuhe_zuliusanzizuhe", "569"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "577", "577", "", "sanzizuhe_zusansanzizuhe", "577"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "578", "578", "", "sanzizuhe_zuliusanzizuhe", "578"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "579", "579", "", "sanzizuhe_zuliusanzizuhe", "579"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "588", "588", "", "sanzizuhe_zusansanzizuhe", "588"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "589", "589", "", "sanzizuhe_zuliusanzizuhe", "589"));
        lotteryPlayList4_6.add(new LotteryPlay(1750, "三字组合", "599", "599", "", "sanzizuhe_zusansanzizuhe", "599"));

        LotteryPlayEnd playEnd4_7 = new LotteryPlayEnd();
        playEnd4_7.setTag("6字头");
        playEnd4_7.setRemoteCode("");//todo  新code 未知
        playEnd4_7.setSpanCount(4);
        playEnd4_7.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_7 = new ArrayList<>();
        playEnd4_7.setLotteryPlays(lotteryPlayList4_7);
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "666", "666", "", "sanzizuhe_baozisanzizuhe", "666"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "667", "667", "", "sanzizuhe_zusansanzizuhe", "667"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "668", "668", "", "sanzizuhe_zusansanzizuhe", "668"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "669", "669", "", "sanzizuhe_zusansanzizuhe", "669"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "677", "677", "", "sanzizuhe_zusansanzizuhe", "677"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "678", "678", "", "sanzizuhe_zuliusanzizuhe", "678"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "679", "679", "", "sanzizuhe_zuliusanzizuhe", "679"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "688", "688", "", "sanzizuhe_zusansanzizuhe", "688"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "689", "689", "", "sanzizuhe_zuliusanzizuhe", "689"));
        lotteryPlayList4_7.add(new LotteryPlay(1750, "三字组合", "699", "699", "", "sanzizuhe_zusansanzizuhe", "699"));

        LotteryPlayEnd playEnd4_8 = new LotteryPlayEnd();
        playEnd4_8.setTag("7字头");
        playEnd4_8.setRemoteCode("");//todo  新code 未知
        playEnd4_8.setSpanCount(4);
        playEnd4_8.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_8 = new ArrayList<>();
        playEnd4_8.setLotteryPlays(lotteryPlayList4_8);
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "777", "777", "", "sanzizuhe_baozisanzizuhe", "777"));
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "778", "778", "", "sanzizuhe_zusansanzizuhe", "778"));
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "779", "779", "", "sanzizuhe_zusansanzizuhe", "779"));
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "788", "788", "", "sanzizuhe_zusansanzizuhe", "788"));
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "789", "789", "", "sanzizuhe_zuliusanzizuhe", "789"));
        lotteryPlayList4_8.add(new LotteryPlay(1750, "三字组合", "799", "799", "", "sanzizuhe_zusansanzizuhe", "799"));

        LotteryPlayEnd playEnd4_9 = new LotteryPlayEnd();
        playEnd4_9.setTag("8字头");
        playEnd4_9.setRemoteCode("");//todo  新code 未知
        playEnd4_9.setSpanCount(4);
        playEnd4_9.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_9 = new ArrayList<>();
        playEnd4_9.setLotteryPlays(lotteryPlayList4_9);
        lotteryPlayList4_9.add(new LotteryPlay(1750, "三字组合", "888", "888", "", "sanzizuhe_baozisanzizuhe", "888"));
        lotteryPlayList4_9.add(new LotteryPlay(1750, "三字组合", "889", "889", "", "sanzizuhe_zusansanzizuhe", "889"));
        lotteryPlayList4_9.add(new LotteryPlay(1750, "三字组合", "899", "899", "", "sanzizuhe_zusansanzizuhe", "899"));

        LotteryPlayEnd playEnd4_10 = new LotteryPlayEnd();
        playEnd4_10.setTag("9字头");
        playEnd4_10.setRemoteCode("");//todo  新code 未知
        playEnd4_10.setSpanCount(4);
        playEnd4_10.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList4_10 = new ArrayList<>();
        playEnd4_10.setLotteryPlays(lotteryPlayList4_10);
        lotteryPlayList4_10.add(new LotteryPlay(1750, "三字组合", "999", "999", "", "sanzizuhe_baozisanzizuhe", "999"));

        //--
        LotteryPlayStart playStart5 = new LotteryPlayStart("一字定位", "yizidingwei");
        List<LotteryPlaySub> playSubs5 = new ArrayList<>();
        playStart5.setSubPlays(playSubs5);

        LotteryPlaySub playSub5 = new LotteryPlaySub("一字定位_副", "", "yizidingwei", "");
        List<LotteryPlayEnd> playEndList5 = new ArrayList<>();
        playSub5.setLotteryPlayEnds(playEndList5);

        LotteryPlayEnd playEnd5_1 = new LotteryPlayEnd();
        playEnd5_1.setTag("百位定位胆");
        playEnd5_1.setRemoteCode("bai");
        playEnd5_1.setSpanCount(4);
        playEnd5_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5_1 = new ArrayList<>();
        playEnd5_1.setLotteryPlays(lotteryPlayList5_1);
        lotteryPlayList5_1.add(new LotteryPlay(1969, "百位定位胆", "0", "0", "", "baiweidingweidan", "0"));
        lotteryPlayList5_1.add(new LotteryPlay(1970, "百位定位胆", "1", "1", "", "baiweidingweidan", "1"));
        lotteryPlayList5_1.add(new LotteryPlay(1971, "百位定位胆", "2", "2", "", "baiweidingweidan", "2"));
        lotteryPlayList5_1.add(new LotteryPlay(1972, "百位定位胆", "3", "3", "", "baiweidingweidan", "3"));
        lotteryPlayList5_1.add(new LotteryPlay(1973, "百位定位胆", "4", "4", "", "baiweidingweidan", "4"));
        lotteryPlayList5_1.add(new LotteryPlay(1974, "百位定位胆", "5", "5", "", "baiweidingweidan", "5"));
        lotteryPlayList5_1.add(new LotteryPlay(1975, "百位定位胆", "6", "6", "", "baiweidingweidan", "6"));
        lotteryPlayList5_1.add(new LotteryPlay(1976, "百位定位胆", "7", "7", "", "baiweidingweidan", "7"));
        lotteryPlayList5_1.add(new LotteryPlay(1977, "百位定位胆", "8", "8", "", "baiweidingweidan", "8"));
        lotteryPlayList5_1.add(new LotteryPlay(1978, "百位定位胆", "9", "9", "", "baiweidingweidan", "9"));

        LotteryPlayEnd playEnd5_2 = new LotteryPlayEnd();
        playEnd5_2.setTag("十位定位胆");
        playEnd5_2.setRemoteCode("shi");
        playEnd5_2.setSpanCount(4);
        playEnd5_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList5_2 = new ArrayList<>();
        playEnd5_2.setLotteryPlays(lotteryPlayList5_2);
        lotteryPlayList5_2.add(new LotteryPlay(1979, "十位定位胆", "0", "0", "", "shiweidingweidan", "0"));
        lotteryPlayList5_2.add(new LotteryPlay(1980, "十位定位胆", "1", "1", "", "shiweidingweidan", "1"));
        lotteryPlayList5_2.add(new LotteryPlay(1981, "十位定位胆", "2", "2", "", "shiweidingweidan", "2"));
        lotteryPlayList5_2.add(new LotteryPlay(1982, "十位定位胆", "3", "3", "", "shiweidingweidan", "3"));
        lotteryPlayList5_2.add(new LotteryPlay(1983, "十位定位胆", "4", "4", "", "shiweidingweidan", "4"));
        lotteryPlayList5_2.add(new LotteryPlay(1984, "十位定位胆", "5", "5", "", "shiweidingweidan", "5"));
        lotteryPlayList5_2.add(new LotteryPlay(1985, "十位定位胆", "6", "6", "", "shiweidingweidan", "6"));
        lotteryPlayList5_2.add(new LotteryPlay(1986, "十位定位胆", "7", "7", "", "shiweidingweidan", "7"));
        lotteryPlayList5_2.add(new LotteryPlay(1987, "十位定位胆", "8", "8", "", "shiweidingweidan", "8"));
        lotteryPlayList5_2.add(new LotteryPlay(1988, "十位定位胆", "9", "9", "", "shiweidingweidan", "9"));

        LotteryPlayEnd playEnd5_3 = new LotteryPlayEnd();
        playEnd5_3.setTag("个位定位胆");
        playEnd5_3.setRemoteCode("ge");
        playEnd5_3.setSpanCount(4);
        playEnd5_3.setDivider(DIVIDER_TYPE_BLOCK);
        List<LotteryPlay> lotteryPlayList5_3 = new ArrayList<>();
        playEnd5_3.setLotteryPlays(lotteryPlayList5_3);
        lotteryPlayList5_3.add(new LotteryPlay(1989, "个位定位胆", "0", "0", "", "geweidingweidan", "0"));
        lotteryPlayList5_3.add(new LotteryPlay(1990, "个位定位胆", "1", "1", "", "geweidingweidan", "1"));
        lotteryPlayList5_3.add(new LotteryPlay(1991, "个位定位胆", "2", "2", "", "geweidingweidan", "2"));
        lotteryPlayList5_3.add(new LotteryPlay(1992, "个位定位胆", "3", "3", "", "geweidingweidan", "3"));
        lotteryPlayList5_3.add(new LotteryPlay(1993, "个位定位胆", "4", "4", "", "geweidingweidan", "4"));
        lotteryPlayList5_3.add(new LotteryPlay(1994, "个位定位胆", "5", "5", "", "geweidingweidan", "5"));
        lotteryPlayList5_3.add(new LotteryPlay(1995, "个位定位胆", "6", "6", "", "geweidingweidan", "6"));
        lotteryPlayList5_3.add(new LotteryPlay(1996, "个位定位胆", "7", "7", "", "geweidingweidan", "7"));
        lotteryPlayList5_3.add(new LotteryPlay(1997, "个位定位胆", "8", "8", "", "geweidingweidan", "8"));
        lotteryPlayList5_3.add(new LotteryPlay(1998, "个位定位胆", "9", "9", "", "geweidingweidan", "9"));

        //--
        LotteryPlayStart playStart6 = new LotteryPlayStart("二字定位", "liangzidingwei");
        List<LotteryPlaySub> playSubs6 = new ArrayList<>();
        playStart6.setSubPlays(playSubs6);

        LotteryPlaySub playSub6 = new LotteryPlaySub("百十定位", "", "baishidingwei", S_Odds);
        List<LotteryPlayEnd> playEndList6 = new ArrayList<>();
        playSub6.setLotteryPlayEnds(playEndList6);

        LotteryPlayEnd playEnd6_1 = new LotteryPlayEnd();// todo   这里不知道怎么填写了？。。。。。。
        playEnd6_1.setTag("百位");
        playEnd6_1.setRemoteCode("");
        playEnd6_1.setCanExpand(false);
        playEnd6_1.setSpanCount(5);
        playEnd6_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_1 = new ArrayList<>();// 1999
        lotteryPlayList6_1.addAll(createLP_No_1(1999, "百十定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baishiweierzidingwei", "baishidingwei"));//todo
        playEnd6_1.setLotteryPlays(lotteryPlayList6_1);

        LotteryPlayEnd playEnd6_2 = new LotteryPlayEnd();
        playEnd6_2.setTag("十位");
        playEnd6_2.setRemoteCode("");
        playEnd6_2.setCanExpand(false);
        playEnd6_2.setSpanCount(5);
        playEnd6_2.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_2 = new ArrayList<>();
        lotteryPlayList6_2.addAll(createLP_No_1(1999, "百十定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baishiweierzidingwei", "baishidingwei"));//todo
        playEnd6_2.setLotteryPlays(lotteryPlayList6_2);


        LotteryPlaySub playSub6_2 = new LotteryPlaySub("百个定位", "", "baigedingwei", S_Odds);
        List<LotteryPlayEnd> playEndList6_2 = new ArrayList<>();
        playSub6_2.setLotteryPlayEnds(playEndList6_2);

        LotteryPlayEnd playEnd6_3 = new LotteryPlayEnd();
        playEnd6_3.setTag("百位");
        playEnd6_3.setRemoteCode("");
        playEnd6_3.setCanExpand(false);
        playEnd6_3.setSpanCount(5);
        playEnd6_3.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_3 = new ArrayList<>();//todo  2000
        lotteryPlayList6_3.addAll(createLP_No_1(2000, "百个定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baigeweierzidingwei", "baigedingwei"));
        playEnd6_3.setLotteryPlays(lotteryPlayList6_3);

        LotteryPlayEnd playEnd6_4 = new LotteryPlayEnd();
        playEnd6_4.setTag("个位");
        playEnd6_4.setRemoteCode("");
        playEnd6_4.setCanExpand(false);
        playEnd6_4.setSpanCount(5);
        playEnd6_4.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_4 = new ArrayList<>();//todo  2000
        lotteryPlayList6_4.addAll(createLP_No_1(2000, "百个定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baigeweierzidingwei", "baigedingwei"));//todo
        playEnd6_4.setLotteryPlays(lotteryPlayList6_4);


        LotteryPlaySub playSub6_3 = new LotteryPlaySub("十个定位", "", "shigedingwei", S_Odds);
        List<LotteryPlayEnd> playEndList6_3 = new ArrayList<>();
        playSub6_3.setLotteryPlayEnds(playEndList6_3);

        LotteryPlayEnd playEnd6_5 = new LotteryPlayEnd();
        playEnd6_5.setTag("十位");
        playEnd6_5.setRemoteCode("");
        playEnd6_5.setCanExpand(false);
        playEnd6_5.setSpanCount(5);
        playEnd6_5.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_5 = new ArrayList<>();//todo 2001
        lotteryPlayList6_5.addAll(createLP_No_1(2001, "十个定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "shigeweierzidingwei", "shigedingwei"));
        playEnd6_5.setLotteryPlays(lotteryPlayList6_5);

        LotteryPlayEnd playEnd6_6 = new LotteryPlayEnd();
        playEnd6_6.setTag("个位");
        playEnd6_6.setRemoteCode("");
        playEnd6_6.setCanExpand(false);
        playEnd6_6.setSpanCount(5);
        playEnd6_6.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList6_6 = new ArrayList<>();//todo 2001
        lotteryPlayList6_6.addAll(createLP_No_1(2001, "十个定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "shigeweierzidingwei", "shigedingwei"));//todo
        playEnd6_6.setLotteryPlays(lotteryPlayList6_6);
        //--
        LotteryPlayStart playStart7 = new LotteryPlayStart("三字定位", "sanzidingwei");
        List<LotteryPlaySub> playSubs7 = new ArrayList<>();
        playStart7.setSubPlays(playSubs7);

        LotteryPlaySub playSub7 = new LotteryPlaySub("三字定位", "", "sanzidingwei", S_Odds);
        List<LotteryPlayEnd> playEndList7 = new ArrayList<>();
        playSub7.setLotteryPlayEnds(playEndList7);

        LotteryPlayEnd playEnd7_1 = new LotteryPlayEnd();
        playEnd7_1.setTag("百位");
        playEnd7_1.setRemoteCode("");
        playEnd7_1.setCanExpand(false);
        playEnd7_1.setSpanCount(5);
        playEnd7_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList7_1 = new ArrayList<>();//todo 2002
        lotteryPlayList7_1.addAll(createLP_No_1(2002, "三字定位", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baishigeweisanzidingwei", "sanzidingwei"));
        playEnd7_1.setLotteryPlays(lotteryPlayList7_1);

        LotteryPlayEnd playEnd7_2 = new LotteryPlayEnd();
        playEnd7_2.setTag("十位");
        playEnd7_2.setRemoteCode("");
        playEnd7_2.setCanExpand(false);
        playEnd7_2.setSpanCount(5);
        playEnd7_2.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList7_2 = new ArrayList<>();//todo 2002
        lotteryPlayList7_2.addAll(createLP_No_1(2002, "", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baishigeweisanzidingwei", "sanzidingwei"));//todo
        playEnd7_2.setLotteryPlays(lotteryPlayList7_2);

        LotteryPlayEnd playEnd7_3 = new LotteryPlayEnd();
        playEnd7_3.setTag("个位");
        playEnd7_3.setRemoteCode("");
        playEnd7_3.setCanExpand(false);
        playEnd7_3.setSpanCount(5);
        playEnd7_3.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList7_3 = new ArrayList<>();//todo 2002
        lotteryPlayList7_3.addAll(createLP_No_1(2002, "", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "baishigeweisanzidingwei", "sanzidingwei"));//todo
        playEnd7_3.setLotteryPlays(lotteryPlayList7_3);

        //--
        LotteryPlayStart playStart8 = new LotteryPlayStart("二字和数", "liangziheshu");

        List<LotteryPlaySub> playSubs8 = new ArrayList<>();
        playStart8.setSubPlays(playSubs8);

        LotteryPlaySub playSub8_1 = new LotteryPlaySub("百十","","baishi",S_Odds);
        List<LotteryPlayEnd> playEndList8_1 = new ArrayList<>();
        playSub8_1.setLotteryPlayEnds(playEndList8_1);

        LotteryPlayEnd playEnd8_1 = new LotteryPlayEnd();
        playEnd8_1.setTag("百十和数");
        playEnd8_1.setRemoteCode("baishi");
        playEnd8_1.setSpanCount(4);
        playEnd8_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_1 = new ArrayList<>();
        playEnd8_1.setLotteryPlays(lotteryPlayList8_1);

        lotteryPlayList8_1.add(new LotteryPlay(2003, "百十和数", "0-4", "0-4", "", "baishiheshu_baishiheshulingdaosi", "0-4"));
        lotteryPlayList8_1.add(new LotteryPlay(2004, "百十和数", "5", "5", "", "baishiheshu_baishiheshuwu", "5"));
        lotteryPlayList8_1.add(new LotteryPlay(2005, "百十和数", "6", "6", "", "baishiheshu_baishiheshuliu", "6"));
        lotteryPlayList8_1.add(new LotteryPlay(2006, "百十和数", "7", "7", "", "baishiheshu_baishiheshuqi", "7"));
        lotteryPlayList8_1.add(new LotteryPlay(2007, "百十和数", "8", "8", "", "baishiheshu_baishiheshuba", "8"));
        lotteryPlayList8_1.add(new LotteryPlay(2008, "百十和数", "9", "9", "", "baishiheshu_baishiheshujiu", "9"));
        lotteryPlayList8_1.add(new LotteryPlay(2009, "百十和数", "10", "10", "", "baishiheshu_baishiheshuyiling", "10"));
        lotteryPlayList8_1.add(new LotteryPlay(2010, "百十和数", "11", "11", "", "baishiheshu_baishiheshuyiyi", "11"));
        lotteryPlayList8_1.add(new LotteryPlay(2011, "百十和数", "12", "12", "", "baishiheshu_baishiheshuyier", "12"));
        lotteryPlayList8_1.add(new LotteryPlay(2012, "百十和数", "13", "13", "", "baishiheshu_baishiheshuyisan", "13"));
        lotteryPlayList8_1.add(new LotteryPlay(2101, "百十和数", "14-18", "14-18", "", "baishiheshu_baishiheshuyisidaoyiba", "14-18"));


        LotteryPlayEnd playEnd8_12 = new LotteryPlayEnd();
        playEnd8_12.setTag("百十和数尾数");
        playEnd8_12.setRemoteCode("baishi");//todo json 这里和上面的同一级
        playEnd8_12.setSpanCount(4);
        playEnd8_12.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_12 = new ArrayList<>();
        playEnd8_12.setLotteryPlays(lotteryPlayList8_12);

        lotteryPlayList8_12.add(new LotteryPlay(2013, "百十和数尾数", "0", "0", "", "baishiheshuweishu_baishiheshuweishuling", "0"));
        lotteryPlayList8_12.add(new LotteryPlay(2014, "百十和数尾数", "1", "1", "", "baishiheshuweishu_baishiheshuweishuyi", "1"));
        lotteryPlayList8_12.add(new LotteryPlay(2015, "百十和数尾数", "2", "2", "", "baishiheshuweishu_baishiheshuweishuer", "2"));
        lotteryPlayList8_12.add(new LotteryPlay(2016, "百十和数尾数", "3", "3", "", "baishiheshuweishu_baishiheshuweishusan", "3"));
        lotteryPlayList8_12.add(new LotteryPlay(2017, "百十和数尾数", "4", "4", "", "baishiheshuweishu_baishiheshuweishusi", "4"));
        lotteryPlayList8_12.add(new LotteryPlay(2018, "百十和数尾数", "5", "5", "", "baishiheshuweishu_baishiheshuweishuwu", "5"));
        lotteryPlayList8_12.add(new LotteryPlay(2019, "百十和数尾数", "6", "6", "", "baishiheshuweishu_baishiheshuweishuliu", "6"));
        lotteryPlayList8_12.add(new LotteryPlay(2020, "百十和数尾数", "7", "7", "", "baishiheshuweishu_baishiheshuweishuqi", "7"));
        lotteryPlayList8_12.add(new LotteryPlay(2021, "百十和数尾数", "8", "8", "", "baishiheshuweishu_baishiheshuweishuba", "8"));
        lotteryPlayList8_12.add(new LotteryPlay(2022, "百十和数尾数", "9", "9", "", "baishiheshuweishu_baishiheshuweishujiu", "9"));


        LotteryPlaySub playSub8_2 = new LotteryPlaySub("百个","","baige",S_Odds);
        List<LotteryPlayEnd> playEndList8_2 = new ArrayList<>();
        playSub8_2.setLotteryPlayEnds(playEndList8_2);

        LotteryPlayEnd playEnd8_21 = new LotteryPlayEnd();
        playEnd8_21.setTag("百个和数");
        playEnd8_21.setRemoteCode("baige");
        playEnd8_21.setSpanCount(4);
        playEnd8_21.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_21 = new ArrayList<>();
        playEnd8_21.setLotteryPlays(lotteryPlayList8_21);

        lotteryPlayList8_21.add(new LotteryPlay(2023, "百个和数0-4", "0-4", "0-4", "", "baigeheshu_baigeheshulingdaosi", "0-4"));
        lotteryPlayList8_21.add(new LotteryPlay(2024, "百个和数5", "5", "5", "", "baigeheshu_baigeheshuwu", "5"));
        lotteryPlayList8_21.add(new LotteryPlay(2025, "百个和数", "6", "6", "", "baigeheshu_baigeheshuliu", "6"));
        lotteryPlayList8_21.add(new LotteryPlay(2026, "百个和数", "7", "7", "", "baigeheshu_baigeheshuqi", "7"));
        lotteryPlayList8_21.add(new LotteryPlay(2027, "百个和数", "8", "8", "", "baigeheshu_baigeheshuba", "8"));
        lotteryPlayList8_21.add(new LotteryPlay(2028, "百个和数", "9", "9", "", "baigeheshu_baigeheshujiu", "9"));
        lotteryPlayList8_21.add(new LotteryPlay(2029, "百个和数", "10", "10", "", "baigeheshu_baigeheshuyiling", "10"));
        lotteryPlayList8_21.add(new LotteryPlay(2030, "百个和数", "11", "11", "", "baigeheshu_baigeheshuyiyi", "11"));
        lotteryPlayList8_21.add(new LotteryPlay(2031, "百个和数", "12", "12", "", "baigeheshu_baigeheshuyier", "12"));
        lotteryPlayList8_21.add(new LotteryPlay(2032, "百个和数", "13", "13", "", "baigeheshu_baigeheshuyisan", "13"));
        lotteryPlayList8_21.add(new LotteryPlay(2102, "百个和数", "14-18", "14-18", "", "baigeheshu_baigeheshuyisidaoyiba", "14-18"));


        LotteryPlayEnd playEnd8_22 = new LotteryPlayEnd();
        playEnd8_22.setTag("百个和数尾数");
        playEnd8_22.setRemoteCode("baige");//todo  和上面的同一级
        playEnd8_22.setSpanCount(4);
        playEnd8_22.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_22 = new ArrayList<>();
        playEnd8_22.setLotteryPlays(lotteryPlayList8_22);

        lotteryPlayList8_22.add(new LotteryPlay(2033, "百个和数尾数", "0", "0", "", "baigeheshuweishu_baigeheshuweishuling", "0"));
        lotteryPlayList8_22.add(new LotteryPlay(2034, "百个和数尾数", "1", "1", "", "baigeheshuweishu_baigeheshuweishuyi", "1"));
        lotteryPlayList8_22.add(new LotteryPlay(2035, "百个和数尾数", "2", "2", "", "baigeheshuweishu_baigeheshuweishuer", "2"));
        lotteryPlayList8_22.add(new LotteryPlay(2036, "百个和数尾数", "3", "3", "", "baigeheshuweishu_baigeheshuweishusan", "3"));
        lotteryPlayList8_22.add(new LotteryPlay(2037, "百个和数尾数", "4", "4", "", "baigeheshuweishu_baigeheshuweishusi", "4"));
        lotteryPlayList8_22.add(new LotteryPlay(2038, "百个和数尾数", "5", "5", "", "baigeheshuweishu_baigeheshuweishuwu", "5"));
        lotteryPlayList8_22.add(new LotteryPlay(2039, "百个和数尾数", "6", "6", "", "baigeheshuweishu_baigeheshuweishuliu", "6"));
        lotteryPlayList8_22.add(new LotteryPlay(2040, "百个和数尾数", "7", "7", "", "baigeheshuweishu_baigeheshuweishuqi", "7"));
        lotteryPlayList8_22.add(new LotteryPlay(2041, "百个和数尾数", "8", "8", "", "baigeheshuweishu_baigeheshuweishuba", "8"));
        lotteryPlayList8_22.add(new LotteryPlay(2042, "百个和数尾数", "9", "9", "", "baigeheshuweishu_baigeheshuweishujiu", "9"));


        LotteryPlaySub playSub8_3 = new LotteryPlaySub("十个","","shige",S_Odds);
        List<LotteryPlayEnd> playEndList8_3 = new ArrayList<>();
        playSub8_3.setLotteryPlayEnds(playEndList8_3);

        LotteryPlayEnd playEnd8_31 = new LotteryPlayEnd();
        playEnd8_31.setTag("十个和数");
        playEnd8_31.setRemoteCode("shige");
        playEnd8_31.setSpanCount(4);
        playEnd8_31.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_31 = new ArrayList<>();
        playEnd8_31.setLotteryPlays(lotteryPlayList8_31);
        lotteryPlayList8_31.add(new LotteryPlay(2043, "十个和数", "0-4", "0-4", "", "shigeheshu_shigeheshulingdaosi", "0-4"));
        lotteryPlayList8_31.add(new LotteryPlay(2044, "十个和数", "5", "5", "", "shigeheshu_shigeheshuwu", "5"));
        lotteryPlayList8_31.add(new LotteryPlay(2045, "十个和数", "6", "6", "", "shigeheshu_shigeheshuliu", "6"));
        lotteryPlayList8_31.add(new LotteryPlay(2046, "十个和数", "7", "7", "", "shigeheshu_shigeheshuqi", "7"));
        lotteryPlayList8_31.add(new LotteryPlay(2047, "十个和数", "8", "8", "", "shigeheshu_shigeheshuba", "8"));
        lotteryPlayList8_31.add(new LotteryPlay(2048, "十个和数", "9", "9", "", "shigeheshu_shigeheshujiu", "9"));
        lotteryPlayList8_31.add(new LotteryPlay(2049, "十个和数", "10", "10", "", "shigeheshu_shigeheshuyiling", "10"));
        lotteryPlayList8_31.add(new LotteryPlay(2050, "十个和数", "11", "11", "", "shigeheshu_shigeheshuyiyi", "11"));
        lotteryPlayList8_31.add(new LotteryPlay(2051, "十个和数", "12", "12", "", "shigeheshu_shigeheshuyier", "12"));
        lotteryPlayList8_31.add(new LotteryPlay(2052, "十个和数", "13", "13", "", "shigeheshu_shigeheshuyisan", "13"));
        lotteryPlayList8_31.add(new LotteryPlay(2103, "十个和数", "14-18", "14-18", "", "shigeheshu_shigeheshuyisidaoyiba", "14-18"));

        LotteryPlayEnd playEnd8_32 = new LotteryPlayEnd();
        playEnd8_32.setTag("十个和数尾数");
        playEnd8_32.setRemoteCode("shige");
        playEnd8_32.setSpanCount(4);
        playEnd8_32.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList8_32 = new ArrayList<>();
        playEnd8_32.setLotteryPlays(lotteryPlayList8_32);
        lotteryPlayList8_32.add(new LotteryPlay(2053, "十个和数尾数", "0", "0", "", "shigeheshuweishu_shigeheshuweishuling", "0"));
        lotteryPlayList8_32.add(new LotteryPlay(2054, "十个和数尾数", "1", "1", "", "shigeheshuweishu_shigeheshuweishuyi", "1"));
        lotteryPlayList8_32.add(new LotteryPlay(2055, "十个和数尾数", "2", "2", "", "shigeheshuweishu_shigeheshuweishuer", "2"));
        lotteryPlayList8_32.add(new LotteryPlay(2056, "十个和数尾数", "3", "3", "", "shigeheshuweishu_shigeheshuweishusan", "3"));
        lotteryPlayList8_32.add(new LotteryPlay(2057, "十个和数尾数", "4", "4", "", "shigeheshuweishu_shigeheshuweishusi", "4"));
        lotteryPlayList8_32.add(new LotteryPlay(2058, "十个和数尾数", "5", "5", "", "shigeheshuweishu_shigeheshuweishuwu", "5"));
        lotteryPlayList8_32.add(new LotteryPlay(2059, "十个和数尾数", "6", "6", "", "shigeheshuweishu_shigeheshuweishuliu", "6"));
        lotteryPlayList8_32.add(new LotteryPlay(2060, "十个和数尾数", "7", "7", "", "shigeheshuweishu_shigeheshuweishuqi", "7"));
        lotteryPlayList8_32.add(new LotteryPlay(2061, "十个和数尾数", "8", "8", "", "shigeheshuweishu_shigeheshuweishuba", "8"));
        lotteryPlayList8_32.add(new LotteryPlay(2062, "十个和数尾数", "9", "9", "", "shigeheshuweishu_shigeheshuweishujiu", "9"));

        //--
        LotteryPlayStart playStart9 = new LotteryPlayStart("三字和数", "sanziheshu");
        List<LotteryPlaySub> playSubs9 = new ArrayList<>();
        playStart9.setSubPlays(playSubs9);

        LotteryPlaySub playSub9 = new LotteryPlaySub("三字和数_副", "", "sanziheshu", "");
        List<LotteryPlayEnd> playEndList9 = new ArrayList<>();
        playSub9.setLotteryPlayEnds(playEndList9);

        LotteryPlayEnd playEnd9_1 = new LotteryPlayEnd();
        playEnd9_1.setTag("三字和数");
        playEnd9_1.setRemoteCode("sanziheshu");
        playEnd9_1.setSpanCount(4);
        playEnd9_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList9_1 = new ArrayList<>();
        playEnd9_1.setLotteryPlays(lotteryPlayList9_1);
        lotteryPlayList9_1.add(new LotteryPlay(2063, "三字和数", "0-6", "0-6", "", "sanziheshulingdaoliu", "0-6"));
        lotteryPlayList9_1.add(new LotteryPlay(2064, "三字和数", "7", "7", "", "sanziheshuqi", "7"));
        lotteryPlayList9_1.add(new LotteryPlay(2065, "三字和数", "8", "8", "", "sanziheshuba", "8"));
        lotteryPlayList9_1.add(new LotteryPlay(2066, "三字和数", "9", "9", "", "sanziheshujiu", "9"));
        lotteryPlayList9_1.add(new LotteryPlay(2067, "三字和数", "10", "10", "", "sanziheshuyiling", "10"));
        lotteryPlayList9_1.add(new LotteryPlay(2068, "三字和数", "11", "11", "", "sanziheshuyiyi", "11"));
        lotteryPlayList9_1.add(new LotteryPlay(2069, "三字和数", "12", "12", "", "sanziheshuyier", "12"));
        lotteryPlayList9_1.add(new LotteryPlay(2070, "三字和数", "13", "13", "", "sanziheshuyisan", "13"));
        lotteryPlayList9_1.add(new LotteryPlay(2071, "三字和数", "14", "14", "", "sanziheshuyisi", "14"));
        lotteryPlayList9_1.add(new LotteryPlay(2072, "三字和数", "15", "15", "", "sanziheshuyiwu", "15"));
        lotteryPlayList9_1.add(new LotteryPlay(2073, "三字和数", "16", "16", "", "sanziheshuyiliu", "16"));
        lotteryPlayList9_1.add(new LotteryPlay(2074, "三字和数", "17", "17", "", "sanziheshuyiqi", "17"));
        lotteryPlayList9_1.add(new LotteryPlay(2075, "三字和数", "18", "18", "", "sanziheshuyiba", "18"));
        lotteryPlayList9_1.add(new LotteryPlay(2076, "三字和数", "19", "19", "", "sanziheshuyijiu", "19"));
        lotteryPlayList9_1.add(new LotteryPlay(2077, "三字和数", "20", "20", "", "sanziheshuerling", "20"));
        lotteryPlayList9_1.add(new LotteryPlay(2078, "三字和数", "21-27", "21-27", "", "sanziheshueryidaoerqi", "21-27"));

        LotteryPlayEnd playEnd9_2 = new LotteryPlayEnd();
        playEnd9_2.setTag("三字和数尾数");
        playEnd9_2.setRemoteCode("sanziheshuweishu");
        playEnd9_2.setSpanCount(4);
        playEnd9_2.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList9_2 = new ArrayList<>();
        playEnd9_2.setLotteryPlays(lotteryPlayList9_2);
        lotteryPlayList9_2.add(new LotteryPlay(2079, "三字和数尾数", "0", "0", "", "sanziheshuweishuling", "0"));
        lotteryPlayList9_2.add(new LotteryPlay(2080, "三字和数尾数", "1", "1", "", "sanziheshuweishuyi", "1"));
        lotteryPlayList9_2.add(new LotteryPlay(2081, "三字和数尾数", "2", "2", "", "sanziheshuweishuer", "2"));
        lotteryPlayList9_2.add(new LotteryPlay(2082, "三字和数尾数", "3", "3", "", "sanziheshuweishusan", "3"));
        lotteryPlayList9_2.add(new LotteryPlay(2083, "三字和数尾数", "4", "4", "", "sanziheshuweishusi", "4"));
        lotteryPlayList9_2.add(new LotteryPlay(2084, "三字和数尾数", "5", "5", "", "sanziheshuweishuwu", "5"));
        lotteryPlayList9_2.add(new LotteryPlay(2085, "三字和数尾数", "6", "6", "", "sanziheshuweishuliu", "6"));
        lotteryPlayList9_2.add(new LotteryPlay(2086, "三字和数尾数", "7", "7", "", "sanziheshuweishuqi", "7"));
        lotteryPlayList9_2.add(new LotteryPlay(2087, "三字和数尾数", "8", "8", "", "sanziheshuweishuba", "8"));
        lotteryPlayList9_2.add(new LotteryPlay(2088, "三字和数尾数", "9", "9", "", "sanziheshuweishujiu", "9"));

        //--
        LotteryPlayStart playStart10 = new LotteryPlayStart("组三", "zusan");
        List<LotteryPlaySub> playSubs10 = new ArrayList<>();
        playStart10.setSubPlays(playSubs10);

        LotteryPlaySub playSub10 = new LotteryPlaySub("组三_副", "", "zuxuansan", S_Odds);
        List<LotteryPlayEnd> playEndList10 = new ArrayList<>();
        playSub10.setLotteryPlayEnds(playEndList10);

        LotteryPlayEnd playEnd10_1 = new LotteryPlayEnd();
        playEnd10_1.setTag("重号");
        playEnd10_1.setRemoteCode("zusan");
        playEnd10_1.setCanExpand(false);
        playEnd10_1.setSpanCount(5);
        playEnd10_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList10_1 = new ArrayList<>();
        lotteryPlayList10_1.addAll(createLP_No_1(2089, "重号", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "zuxuansan", "zusan"));
        playEnd10_1.setLotteryPlays(lotteryPlayList10_1);

        LotteryPlayEnd playEnd10_2 = new LotteryPlayEnd();
        playEnd10_2.setTag("不重号");
        playEnd10_2.setRemoteCode("zusan");
        playEnd10_2.setCanExpand(false);
        playEnd10_2.setSpanCount(5);
        playEnd10_2.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList10_2 = new ArrayList<>();
        lotteryPlayList10_2.addAll(createLP_No_1(2089, "不重号", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "zuxuansan", "zusan"));
        playEnd10_2.setLotteryPlays(lotteryPlayList10_2);


        LotteryPlayStart playStart11 = new LotteryPlayStart("组六", "zuliu");
        List<LotteryPlaySub> playSubs11 = new ArrayList<>();
        playStart11.setSubPlays(playSubs11);

        LotteryPlaySub playSub11 = new LotteryPlaySub("组六_副", "", "zuliu", S_Odds);
        List<LotteryPlayEnd> playEndList11 = new ArrayList<>();
        playSub11.setLotteryPlayEnds(playEndList11);

        LotteryPlayEnd playEnd11_1 = new LotteryPlayEnd();
        playEnd11_1.setTag("");
        playEnd11_1.setRemoteCode("");
        playEnd11_1.setCanExpand(false);
        playEnd11_1.setSpanCount(5);
        playEnd11_1.setDivider(DIVIDER_TYPE_NONE);
        List<LotteryPlay> lotteryPlayList11_1 = new ArrayList<>();
        lotteryPlayList11_1.addAll(createLP_No_1(2090, "", 0, 9, "", false, BET_LAYOUT_TYPE_CIRCLE, "zuxuanliu", "zuliu"));
        playEnd11_1.setLotteryPlays(lotteryPlayList11_1);


        LotteryPlayStart playStart12 = new LotteryPlayStart("跨度", "kuadu");
        List<LotteryPlaySub> playSubs12 = new ArrayList<>();
        playStart12.setSubPlays(playSubs12);

        LotteryPlaySub playSub12 = new LotteryPlaySub("跨度_副", "", "kuadu", "");
        List<LotteryPlayEnd> playEndList12 = new ArrayList<>();
        playSub12.setLotteryPlayEnds(playEndList12);

        LotteryPlayEnd playEnd12_1 = new LotteryPlayEnd();
        playEnd12_1.setTag("跨度");
        playEnd12_1.setRemoteCode("");
        playEnd12_1.setSpanCount(4);
        playEnd12_1.setDivider(DIVIDER_TYPE_LINE);
        List<LotteryPlay> lotteryPlayList12_1 = new ArrayList<>();
        playEnd12_1.setLotteryPlays(lotteryPlayList12_1);
        lotteryPlayList12_1.add(new LotteryPlay(2091, "跨度", "0", "0", "", "kuadu_kuaduling", "0"));
        lotteryPlayList12_1.add(new LotteryPlay(2092, "跨度", "1", "1", "", "kuadu_kuaduyi", "1"));
        lotteryPlayList12_1.add(new LotteryPlay(2093, "跨度", "2", "2", "", "kuadu_kuaduer", "2"));
        lotteryPlayList12_1.add(new LotteryPlay(2094, "跨度", "3", "3", "", "kuadu_kuadusan", "3"));
        lotteryPlayList12_1.add(new LotteryPlay(2095, "跨度", "4", "4", "", "kuadu_kuadusi", "4"));
        lotteryPlayList12_1.add(new LotteryPlay(2096, "跨度", "5", "5", "", "kuadu_kuaduwu", "5"));
        lotteryPlayList12_1.add(new LotteryPlay(2097, "跨度", "6", "6", "", "kuadu_kuaduliu", "6"));
        lotteryPlayList12_1.add(new LotteryPlay(2098, "跨度", "7", "7", "", "kuadu_kuaduqi", "7"));
        lotteryPlayList12_1.add(new LotteryPlay(2099, "跨度", "8", "8", "", "kuadu_kuaduba", "8"));
        lotteryPlayList12_1.add(new LotteryPlay(2100, "跨度", "9", "9", "", "kuadu_kuadujiu", "9"));

        playEndList1.add(playEnd1);
        playEndList1.add(playEnd2);
        playEndList1.add(playEnd3);
        playEndList1.add(playEnd4);
        playEndList1.add(playEnd5);
        playEndList1.add(playEnd6);
        playEndList1.add(playEnd7);
        playEndList2.add(playEnd2_1);
        playEndList3.add(playEnd3_1);
        playEndList3.add(playEnd3_2);
        playEndList3.add(playEnd3_3);
        playEndList3.add(playEnd3_4);
        playEndList3.add(playEnd3_5);
        playEndList3.add(playEnd3_6);
        playEndList3.add(playEnd3_7);
        playEndList3.add(playEnd3_8);
        playEndList3.add(playEnd3_9);
        playEndList3.add(playEnd3_10);
        playEndList4.add(playEnd4_1);
        playEndList4.add(playEnd4_2);
        playEndList4.add(playEnd4_3);
        playEndList4.add(playEnd4_4);
        playEndList4.add(playEnd4_5);
        playEndList4.add(playEnd4_6);
        playEndList4.add(playEnd4_7);
        playEndList4.add(playEnd4_8);
        playEndList4.add(playEnd4_9);
        playEndList4.add(playEnd4_10);
        playEndList5.add(playEnd5_1);
        playEndList5.add(playEnd5_2);
        playEndList5.add(playEnd5_3);
        playEndList6.add(playEnd6_1);
        playEndList6.add(playEnd6_2);
        playEndList6_2.add(playEnd6_3);
        playEndList6_2.add(playEnd6_4);
        playEndList6_3.add(playEnd6_5);
        playEndList6_3.add(playEnd6_6);
        playEndList7.add(playEnd7_1);
        playEndList7.add(playEnd7_2);
        playEndList7.add(playEnd7_3);
        playEndList8_1.add(playEnd8_1);
        playEndList8_1.add(playEnd8_12);
        playEndList8_2.add(playEnd8_21);
        playEndList8_2.add(playEnd8_22);
        playEndList8_3.add(playEnd8_31);
        playEndList8_3.add(playEnd8_32);
        playEndList9.add(playEnd9_1);
        playEndList9.add(playEnd9_2);
        playEndList10.add(playEnd10_1);
        playEndList10.add(playEnd10_2);
        playEndList11.add(playEnd11_1);
        playEndList12.add(playEnd12_1);

        playSubs1.add(playSub1);
        playSubs2.add(playSub2);
        playSubs3.add(playSub3);
        playSubs4.add(playSub4);
        playSubs5.add(playSub5);
        playSubs6.add(playSub6);
        playSubs6.add(playSub6_2);
        playSubs6.add(playSub6_3);
        playSubs7.add(playSub7);
        playSubs8.add(playSub8_1);
        playSubs8.add(playSub8_2);
        playSubs8.add(playSub8_3);
        playSubs9.add(playSub9);
        playSubs10.add(playSub10);
        playSubs11.add(playSub11);
        playSubs12.add(playSub12);

        lotteryPlayStartList.add(playStart1);
        lotteryPlayStartList.add(playStart2);
        lotteryPlayStartList.add(playStart3);
        lotteryPlayStartList.add(playStart4);
        lotteryPlayStartList.add(playStart5);
        lotteryPlayStartList.add(playStart6);
        lotteryPlayStartList.add(playStart7);
        lotteryPlayStartList.add(playStart8);
        lotteryPlayStartList.add(playStart9);
        lotteryPlayStartList.add(playStart10);
        lotteryPlayStartList.add(playStart11);
        lotteryPlayStartList.add(playStart12);

        return lotteryPlayStartList;
    }

    private static List<LotteryPlayStart> createPlay_kl8() {
        String ZONGHE = "总和";
        String ZONGHEGUOGUAN = "总和过关";
        String QIANHOUDUO = "前后多";
        String DANSHUANGDUO = "单双多";
        String WUXING = "五行";
        String[] level_three = new String[]{ZONGHE, ZONGHEGUOGUAN, QIANHOUDUO, DANSHUANGDUO, WUXING};
        // 彩系 kl8
        List<LotteryPlayStart> lotteryPlayStarts = new ArrayList<>();
        LotteryPlayStart playStart_shuangmian = new LotteryPlayStart("双面", true, "shuangmian");
        lotteryPlayStarts.add(playStart_shuangmian);
        List<LotteryPlaySub> playSubs = new ArrayList<>();
        LotteryPlaySub playSub = new LotteryPlaySub("双面_副", "", "shuangmian", S_ColdHot);
        playSubs.add(playSub);
        List<LotteryPlayEnd> lotteryPlayEnds = new ArrayList<>();
        for (int x = 0; x < level_three.length; x++) {
            LotteryPlayEnd lotteryPlayEnd = new LotteryPlayEnd();
            String title = level_three[x];
            lotteryPlayEnd.setTag(title);
            lotteryPlayEnd.setSpanCount(4);
            List<LotteryPlay> lotteryPlays = new ArrayList<>();
            if (title.equals(ZONGHE)) {
                lotteryPlayEnd.setRemoteCode("zonghe");
                LotteryPlay lotteryPlay = new LotteryPlay(762, "总和大小", "大", "da", 762, "zonghedaxiao", "da");
                LotteryPlay lotteryPlay1 = new LotteryPlay(763, "总和大小", "小", "xiao", 763, "zonghedaxiao", "xiao");
                LotteryPlay lotteryPlay2 = new LotteryPlay(764, "总和单双", "单", "dan", 764, "zonghedanshuang", "dan");
                LotteryPlay lotteryPlay3 = new LotteryPlay(765, "总和单双", "双", "shuang", 765, "zonghedanshuang", "shuang");
                lotteryPlays.add(lotteryPlay);
                lotteryPlays.add(lotteryPlay1);
                lotteryPlays.add(lotteryPlay2);
                lotteryPlays.add(lotteryPlay3);
            } else if (title.equals(ZONGHEGUOGUAN)) {
                lotteryPlayEnd.setRemoteCode("zongheguoguan");
                LotteryPlay lotteryPlay4 = new LotteryPlay(766, "总和过关", "大单", "dadan", "", 0, "zongheguoguan", "dadan");
                LotteryPlay lotteryPlay5 = new LotteryPlay(767, "总和过关", "大双", "dashuang", "", 0, "zongheguoguan", "dashuang");
                LotteryPlay lotteryPlay6 = new LotteryPlay(768, "总和过关", "小单", "xiaodan", "", 0, "zongheguoguan", "xiaodan");
                LotteryPlay lotteryPlay7 = new LotteryPlay(769, "总和过关", "小双", "xiaoshuang", "", 0, "zongheguoguan", "xiaoshuang");
                lotteryPlays.add(lotteryPlay4);
                lotteryPlays.add(lotteryPlay5);
                lotteryPlays.add(lotteryPlay6);
                lotteryPlays.add(lotteryPlay7);
            } else if (title.equals(QIANHOUDUO)) {
                lotteryPlayEnd.setRemoteCode("qianhou");
                LotteryPlay lotteryPlay8 = new LotteryPlay(770, "前后多", "前多", "qianduo", 770, "qianhouduo", "qianduo");
                LotteryPlay lotteryPlay9 = new LotteryPlay(771, "前后多", "后多", "houduo", 771, "qianhouduo", "houduo");

                lotteryPlays.add(lotteryPlay8);

                lotteryPlays.add(lotteryPlay9);
            } else if (title.equals(DANSHUANGDUO)) {
                lotteryPlayEnd.setRemoteCode("danshuang");
                LotteryPlay lotteryPlay10 = new LotteryPlay(772, "单双多", "单多", "danduo", 772, "danshuangduo", "danduo");
                LotteryPlay lotteryPlay11 = new LotteryPlay(773, "单双多", "双多", "shuangduo", 773, "danshuangduo", "shuangduo");
                lotteryPlays.add(lotteryPlay10);
                lotteryPlays.add(lotteryPlay11);
            } else {
                lotteryPlayEnd.setRemoteCode("wuxing");
                String[] name_wuxing = new String[]{"金", "木", "水", "火", "土"};
                String[] code_wuxing = new String[]{"jin", "mu", "shui", "huo", "tu"};
                String[] remote_code_wuxing = new String[]{"zonghejin", "zonghemu", "zongheshui", "zonghehuo", "zonghetu"};
                int[] id_wuxing = new int[]{774, 775, 776, 777, 778};
                String[] odds_wuxing = new String[]{"", "", "", "", ""};
                for (int y = 0; y < name_wuxing.length; y++) {
                    LotteryPlay lotteryPlay12 = new LotteryPlay(id_wuxing[y], "五行", name_wuxing[y], code_wuxing[y], odds_wuxing[y], 0, remote_code_wuxing[y], code_wuxing[y]);
                    lotteryPlays.add(lotteryPlay12);
                }
            }

            lotteryPlayEnd.setLotteryPlays(lotteryPlays);
            lotteryPlayEnds.add(lotteryPlayEnd);
        }

        playSub.setLotteryPlayEnds(lotteryPlayEnds);
        playStart_shuangmian.setSubPlays(playSubs);


        LotteryPlayStart playStart_zhengma = new LotteryPlayStart("正码", false, "zhengma");
        lotteryPlayStarts.add(playStart_zhengma);

        List<LotteryPlaySub> lotteryPlaySubs_zhengma = new ArrayList<>();
        List<LotteryPlayEnd> lotteryPlayEnds_zhengma = new ArrayList<>();

        LotteryPlaySub playSub_zhengma = new LotteryPlaySub("正码_副", "", "zhengma", S_ColdHot_Omit);
        lotteryPlaySubs_zhengma.add(playSub_zhengma);

        LotteryPlayEnd lotteryPlayEnd_zhengma = new LotteryPlayEnd();
        lotteryPlayEnd_zhengma.setTag("正码");
        lotteryPlayEnd_zhengma.setRemoteCode("zhengma");
        lotteryPlayEnd_zhengma.setHideTag(true);
        lotteryPlayEnd_zhengma.setSpanCount(4);
        lotteryPlayEnds_zhengma.add(lotteryPlayEnd_zhengma);

        List<LotteryPlay> zhengmaPlayList = createLP_No_kl8("正码", 1, 80, "", true, 0, "zhengma");


        playStart_zhengma.setSubPlays(lotteryPlaySubs_zhengma);
        playSub_zhengma.setLotteryPlayEnds(lotteryPlayEnds_zhengma);
        lotteryPlayEnd_zhengma.setLotteryPlays(zhengmaPlayList);


        return lotteryPlayStarts;
    }


    /**
     * 创建玩法
     *
     * @param tag
     * @param odd
     * @param isZero
     * @return
     */
    private static List<LotteryPlay> createLP_No_11x5(String tag, String odd, boolean isZero, boolean codeIsZero, int itemType, String remoteCodeUp, String remoteCode) {
        odd = "";
        int[] arr = new int[0];
        if ("第一球定位胆".equals(tag)) {
            arr = new int[]{455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465};
        } else if ("第二球定位胆".equals(tag)) {
            arr = new int[]{466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476};
        } else if ("第三球定位胆".equals(tag)) {
            arr = new int[]{481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491};
        } else if ("第四球定位胆".equals(tag)) {
            arr = new int[]{492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502};
        } else if ("第五球定位胆".equals(tag)) {
            arr = new int[]{512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522};
        } else if ("任选一".equals(tag)) {
            arr = new int[]{527, 527, 527, 527, 527, 527, 527, 527, 527, 527, 527};
        } else if ("任选二".equals(tag)) {
            arr = new int[]{528, 528, 528, 528, 528, 528, 528, 528, 528, 528, 528};
        } else if ("任选三".equals(tag)) {
            arr = new int[]{529, 529, 529, 529, 529, 529, 529, 529, 529, 529, 529};
        } else if ("任选四".equals(tag)) {
            arr = new int[]{530, 530, 530, 530, 530, 530, 530, 530, 530, 530, 530};
        } else if ("任选五".equals(tag)) {
            arr = new int[]{531, 531, 531, 531, 531, 531, 531, 531, 531, 531, 531};
        } else if ("任选六中五".equals(tag)) {
            arr = new int[]{532, 532, 532, 532, 532, 532, 532, 532, 532, 532, 532};
        } else if ("任选七中五".equals(tag)) {
            arr = new int[]{533, 533, 533, 533, 533, 533, 533, 533, 533, 533, 533};
        } else if ("任选八中五".equals(tag)) {
            arr = new int[]{534, 534, 534, 534, 534, 534, 534, 534, 534, 534, 534};
        } else if ("前二组选".equals(tag)) {
            arr = new int[]{535, 535, 535, 535, 535, 535, 535, 535, 535, 535, 535};
        } else if ("前三组选".equals(tag)) {
            arr = new int[]{536, 536, 536, 536, 536, 536, 536, 536, 536, 536, 536};
        } else if ("前二直选".equals(tag)) {
            arr = new int[]{537, 537, 537, 537, 537, 537, 537, 537, 537, 537, 537};
        } else if ("前三直选".equals(tag)) {
            arr = new int[]{538, 538, 538, 538, 538, 538, 538, 538, 538, 538, 538};
        }

        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            String name = String.format(isZero ? "%02d" : "%d", i);
            String code = String.format(codeIsZero ? "%02d" : "%d", i);
            if (StringUtils.isBlank(remoteCode)) {
                lotteryPlays.add(new LotteryPlay(arr[i - 1], tag, name, code, odd, remoteCodeUp, code, itemType));
            } else {
                lotteryPlays.add(new LotteryPlay(arr[i - 1], tag, name, code, odd, remoteCodeUp, remoteCode, itemType));
            }
        }
        return lotteryPlays;
    }

    private static List<LotteryPlay> createLP_No_kl8(String tag, int start, int end, String odd, boolean isZero, int itemType, String remoteCodeUp) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        int id = 778;
        for (int i = start; i <= end; i++) {
            String name = String.valueOf(i);
            String code = String.valueOf(i);
            if (isZero) {
                if (i < 10) {
                    name = "0" + i;
                    code = "0" + i;
                }
            }
            lotteryPlays.add(new LotteryPlay(id + i, tag, name, code, odd, itemType, remoteCodeUp, code));
        }
        return lotteryPlays;
    }

    private static List<LotteryPlay> createLP_No_1(int id, String tag, int start, int end, String odd, boolean isZero, int itemType) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            String name = String.valueOf(i);
            String code = String.valueOf(i);
            if (isZero) {
                if (i < 10) {
                    name = "0" + i;
                    code = "0" + i;
                }
            }
            lotteryPlays.add(new LotteryPlay(id, tag, name, code, odd, itemType));
        }
        return lotteryPlays;
    }

    private static List<LotteryPlay> createLP_No_1(int id, String tag, int start, int end, String odd, boolean isZero, int itemType, String remoteCodeUp, String remoteCode) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            String name = String.valueOf(i);
            String code = String.valueOf(i);
            if (isZero) {
                if (i < 10) {
                    name = "0" + i;
                    code = "0" + i;
                }
            }
            lotteryPlays.add(new LotteryPlay(id, tag, name, code, odd, remoteCodeUp, remoteCode, itemType));
        }
        return lotteryPlays;
    }

    /**
     * 六合彩数字
     *
     * @param tag
     * @param itemType 2 球型 3 方型
     * @return
     */
    private static List<LotteryPlay> createNumLhc(List<Integer> id, String tag, String odds, int itemType, boolean type, boolean style) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int y = 1; y < 50; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(type ? id.get(y - 1) : id.get(0), tag, String.format("%02d", y), String.format(style ? "%02d" : "%d", y), odds, itemType);
            lotteryPlays.add(lotteryPlay);
        }
        return lotteryPlays;
    }

    private static List<LotteryPlay> createNumLhc(List<Integer> id, String tag, String odds, int itemType, boolean type, boolean style, String remoteCodeUp) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int y = 1; y < 50; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(type ? id.get(y - 1) : id.get(0), tag, String.format("%02d", y), String.format(style ? "%02d" : "%d", y), odds,
                    remoteCodeUp, String.format("%02d", y), itemType);
            lotteryPlays.add(lotteryPlay);
        }
        return lotteryPlays;
    }

    private static List<LotteryPlay> createNumLhc(List<Integer> id, String tag, String odds, int itemType, boolean type,
                                                  boolean style, String remoteCodeUp, String remoteCode) {
        List<LotteryPlay> lotteryPlays = new ArrayList<>();
        for (int y = 1; y < 50; y++) {
            LotteryPlay lotteryPlay = new LotteryPlay(type ? id.get(y - 1) : id.get(0), tag, String.format("%02d", y), String.format(style ? "%02d" : "%d", y), odds,
                    remoteCodeUp, remoteCode, itemType);
            lotteryPlays.add(lotteryPlay);
        }
        return lotteryPlays;
    }
}
