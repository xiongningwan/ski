package com.ski.box.bean.road;

import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置路子图顶部条目
 */
public class RoadFactory {
    public static String ROAD_POSITION_PREFIX_TITLE = "road_position_prefix_title";
    public static String ROAD_POSITION_PREFIX_SUB = "road_position_prefix_sub";
    private static String[] arr1 = new String[]{"大小", "单双", "龙虎"};
    private static String[] arr2 = new String[]{"大小", "单双"};
    private static String[] arr3 = new String[]{"大小", "单双", "质合"};
    /*k3*/
    private static String[] arr4 = new String[]{"大小"};
    /*3d*/
    private static String[] arr5 = new String[]{"单双", "尾大小", "尾质合"};
    private static String[] arr6 = new String[]{"大小", "单双", "尾大小", "尾质合"};
    private static String[] arr7 = new String[]{"大小", "单双", "前后多", "单双多"};
    /*lhc*/
    private static String[] arr8 = new String[]{"大小", "单双", "合大小", "合单双", "天地肖", "前后肖", "家野肖", "尾大小"};
    private static String[] arr9 = new String[]{"大小", "单双", "合大小", "合单双", "尾大小"};
    private static String[] arr10 = new String[]{"单双"};

    /*f1 jjs*/
    private static String[] arr11 = new String[]{"红黑"};

    public static List<RoadTitle> createPlay(int gameId) {
        List<RoadTitle> titles = new ArrayList<>();
        int serId = LotteryUtil.getSerIdByLotteryId(gameId);
        switch (serId) {
            case LotteryConstant.SER_ID_PK10:
                titles = create_pk10(gameId);
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_PL35:
                titles = create_ssc(gameId);
                break;
            case LotteryConstant.SER_ID_LHC:
                titles = create_lhc(gameId);
                break;
            case LotteryConstant.SER_ID_11X5:
                titles = create_11x5(gameId);
                break;
            case LotteryConstant.SER_ID_K3:
                titles = create_k3(gameId);
                break;
            case LotteryConstant.SER_ID_3D:
                titles = create_3d(gameId);
                break;
            case LotteryConstant.SER_ID_KL8:
                titles = create_klc(gameId);
                break;
            case LotteryConstant.SER_ID_F1_JJS:
                titles = create_f1_jjs(gameId);
                break;
        }
        return titles;
    }

    private static List<RoadTitle> create_lhc(int gameId) {
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        Integer[][] arr1_ids_tm = new Integer[][]{new Integer[]{914, 915}, new Integer[]{916, 917}, new Integer[]{918, 919}, new Integer[]{920, 921}, new Integer[]{922, 923}, new Integer[]{924, 925}, new Integer[]{926, 927}, new Integer[]{928, 929}};
        //todo 可能本地基础数据有误 大小和单双
        Integer[][] arr1_ids_z1 = new Integer[][]{new Integer[]{1103, 1104}, new Integer[]{1101, 1102}, new Integer[]{1107, 1108}, new Integer[]{1105, 1106}, new Integer[]{1109, 1110}};
        Integer[][] arr1_ids_z2 = new Integer[][]{new Integer[]{1116, 1117}, new Integer[]{1114, 1115}, new Integer[]{1120, 1121}, new Integer[]{1118, 1119}, new Integer[]{1122, 1123}};
        Integer[][] arr1_ids_z3 = new Integer[][]{new Integer[]{1129, 1130}, new Integer[]{1127, 1128}, new Integer[]{1133, 1134}, new Integer[]{1131, 1132}, new Integer[]{1135, 1136}};
        Integer[][] arr1_ids_z4 = new Integer[][]{new Integer[]{1142, 1143}, new Integer[]{1140, 1141}, new Integer[]{1146, 1147}, new Integer[]{1144, 1145}, new Integer[]{1148, 1149}};
        Integer[][] arr1_ids_z5 = new Integer[][]{new Integer[]{1155, 1156}, new Integer[]{1153, 1154}, new Integer[]{1159, 1160}, new Integer[]{1157, 1158}, new Integer[]{1161, 1162}};
        Integer[][] arr1_ids_z6 = new Integer[][]{new Integer[]{1168, 1169}, new Integer[]{1166, 1167}, new Integer[]{1172, 1173}, new Integer[]{1170, 1171}, new Integer[]{1174, 1175}};
        Integer[][] arr1_ids_zh = new Integer[][]{new Integer[]{1533, 1534}, new Integer[]{1535, 1536}};
        Integer[][] arr1_ids_zx = new Integer[][]{new Integer[]{1521, 1522}};
        titles.add(new RoadTitle("特码", arr8, arr1_ids_tm, position == 0 ? true : false, gameId));
        titles.add(new RoadTitle("正码一", arr9, arr1_ids_z1, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("正码二", arr9, arr1_ids_z2, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("正码三", arr9, arr1_ids_z3, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("正码四", arr9, arr1_ids_z4, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("正码五", arr9, arr1_ids_z5, position == 5 ? true : false, gameId));
        titles.add(new RoadTitle("正码六", arr9, arr1_ids_z6, position == 6 ? true : false, gameId));
        titles.add(new RoadTitle("总和", arr2, arr1_ids_zh, position == 7 ? true : false, gameId));
        titles.add(new RoadTitle("总肖", arr10, arr1_ids_zx, position == 8 ? true : false, gameId));


        return titles;
    }

    private static List<RoadTitle> create_klc(int gameId) {
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        Integer[][] arr1_ids = new Integer[][]{new Integer[]{762, 763}, new Integer[]{764, 765}, new Integer[]{770, 771}, new Integer[]{772, 773}};
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        titles.add(new RoadTitle("", arr7, arr1_ids, position == 0 ? true : false, gameId));
        return titles;
    }

    private static List<RoadTitle> create_3d(int gameId) {
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        Integer[][] arr1_ids_bai = new Integer[][]{new Integer[]{1643, 1644}, new Integer[]{1645, 1646}, new Integer[]{1647, 1648}};
        Integer[][] arr1_ids_shi = new Integer[][]{new Integer[]{1649, 1650}, new Integer[]{1651, 1652}, new Integer[]{1653, 1654}};
        Integer[][] arr1_ids_ge = new Integer[][]{new Integer[]{1655, 1656}, new Integer[]{1657, 1658}, new Integer[]{1659, 1660}};
        Integer[][] arr1_ids_baishiheshu = new Integer[][]{new Integer[]{1661, 1662}, new Integer[]{1663, 1664}, new Integer[]{1665, 1666}};
        Integer[][] arr1_ids_baigeheshu = new Integer[][]{new Integer[]{1667, 1668}, new Integer[]{1669, 1670}, new Integer[]{1671, 1672}};
        Integer[][] arr1_ids_shigeheshu = new Integer[][]{new Integer[]{1673, 1674}, new Integer[]{1675, 1676}, new Integer[]{1677, 1678}};
        Integer[][] arr1_ids_baishige = new Integer[][]{new Integer[]{2105, 2106}, new Integer[]{1679, 1680}, new Integer[]{1681, 1682}, new Integer[]{1683, 1684}};
        titles.add(new RoadTitle("百位", arr3, arr1_ids_bai, position == 0 ? true : false, gameId));
        titles.add(new RoadTitle("十位", arr3, arr1_ids_shi, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("个位", arr3, arr1_ids_ge, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("百十和数", arr5, arr1_ids_baishiheshu, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("百个和数", arr5, arr1_ids_baigeheshu, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("十个和数", arr5, arr1_ids_shigeheshu, position == 5 ? true : false, gameId));
        titles.add(new RoadTitle("百十个和数", arr6, arr1_ids_baishige, position == 6 ? true : false, gameId));


        return titles;
    }

    private static List<RoadTitle> create_k3(int gameId) {
        Integer[][] arr1_ids = new Integer[][]{new Integer[]{706, 707}};
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        titles.add(new RoadTitle("", arr4, arr1_ids, position == 0 ? true : false, gameId));
        return titles;
    }

    private static List<RoadTitle> create_f1_jjs(int gameId) {
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        Integer[][] arr1_ids_he = new Integer[][]{new Integer[]{545, 546}, new Integer[]{547, 548}};
        Integer[][] arr1_ids_guan = new Integer[][]{new Integer[]{549, 550}, new Integer[]{551, 552}, new Integer[]{553, 554}};
        Integer[][] arr1_ids_ya = new Integer[][]{new Integer[]{555, 556}, new Integer[]{557, 558}, new Integer[]{559, 560}};
        Integer[][] arr1_ids3 = new Integer[][]{new Integer[]{561, 562}, new Integer[]{563, 564}, new Integer[]{565, 566}};
        Integer[][] arr1_ids4 = new Integer[][]{new Integer[]{567, 568}, new Integer[]{569, 570}, new Integer[]{571, 572}};
        Integer[][] arr1_ids5 = new Integer[][]{new Integer[]{573, 574}, new Integer[]{575, 576}, new Integer[]{577, 578}};
        Integer[][] arr1_ids6 = new Integer[][]{new Integer[]{679, 680}, new Integer[]{859, 860}};
        Integer[][] arr1_ids7 = new Integer[][]{new Integer[]{861, 862}, new Integer[]{863, 864}};
        Integer[][] arr1_ids8 = new Integer[][]{new Integer[]{865, 866}, new Integer[]{867, 868}};
        Integer[][] arr1_ids9 = new Integer[][]{new Integer[]{869, 870}, new Integer[]{871, 872}};
        Integer[][] arr1_ids10 = new Integer[][]{new Integer[]{873, 874}, new Integer[]{875, 876}};
        titles.add(new RoadTitle("冠军", arr11, arr1_ids_guan, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("亚军", arr11, arr1_ids_ya, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("第三名", arr11, arr1_ids3, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("第四名", arr11, arr1_ids4, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("第五名", arr11, arr1_ids5, position == 5 ? true : false, gameId));
        titles.add(new RoadTitle("第六名", arr11, arr1_ids6, position == 6 ? true : false, gameId));
        titles.add(new RoadTitle("第七名", arr11, arr1_ids7, position == 7 ? true : false, gameId));
        titles.add(new RoadTitle("第八名", arr11, arr1_ids8, position == 8 ? true : false, gameId));
        titles.add(new RoadTitle("第九名", arr11, arr1_ids9, position == 9 ? true : false, gameId));
        titles.add(new RoadTitle("第十名", arr11, arr1_ids10, position == 10 ? true : false, gameId));
        return titles;
    }

    public static List<RoadTitle> create_11x5(int gameId) {
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        Integer[][] arr1_ids = new Integer[][]{new Integer[]{429, 430}, new Integer[]{431, 432}, new Integer[]{433, 434}};
        Integer[][] arr1_ids1 = new Integer[][]{new Integer[]{435, 436}, new Integer[]{437, 438}};
        Integer[][] arr1_ids2 = new Integer[][]{new Integer[]{439, 440}, new Integer[]{441, 442}};
        Integer[][] arr1_ids3 = new Integer[][]{new Integer[]{443, 444}, new Integer[]{445, 446}};
        Integer[][] arr1_ids4 = new Integer[][]{new Integer[]{447, 448}, new Integer[]{449, 450}};
        Integer[][] arr1_ids5 = new Integer[][]{new Integer[]{451, 452}, new Integer[]{453, 454}};

        List<RoadTitle> titles = new ArrayList<>();
        titles.add(new RoadTitle("总和", arr1, arr1_ids, position == 0 ? true : false, gameId));
        titles.add(new RoadTitle("第一球", arr2, arr1_ids1, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("第二球", arr2, arr1_ids2, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("第三球", arr2, arr1_ids3, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("第四球", arr2, arr1_ids4, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("第五球", arr2, arr1_ids5, position == 5 ? true : false, gameId));
        return titles;
    }

    public static List<RoadTitle> create_ssc(int gameId) {
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        Integer[][] arr1_ids = new Integer[][]{new Integer[]{303, 304}, new Integer[]{305, 306}, new Integer[]{307, 308, 309}};
        Integer[][] arr1_ids1 = new Integer[][]{new Integer[]{310, 311}, new Integer[]{312, 313}, new Integer[]{314, 315}};
        Integer[][] arr1_ids2 = new Integer[][]{new Integer[]{326, 327}, new Integer[]{328, 329}, new Integer[]{330, 331}};
        Integer[][] arr1_ids3 = new Integer[][]{new Integer[]{342, 343}, new Integer[]{344, 345}, new Integer[]{346, 347}};
        Integer[][] arr1_ids4 = new Integer[][]{new Integer[]{358, 359}, new Integer[]{360, 361}, new Integer[]{362, 363}};
        Integer[][] arr1_ids5 = new Integer[][]{new Integer[]{374, 375}, new Integer[]{376, 377}, new Integer[]{378, 379}};
        titles.add(new RoadTitle("总和", arr1, arr1_ids, position == 0 ? true : false, gameId));
        titles.add(new RoadTitle("第一球", arr3, arr1_ids1, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("第二球", arr3, arr1_ids2, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("第三球", arr3, arr1_ids3, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("第四球", arr3, arr1_ids4, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("第五球", arr3, arr1_ids5, position == 5 ? true : false, gameId));
        return titles;
    }

    public static List<RoadTitle> create_pk10(int gameId) {
        // 选中位置
        String key = ROAD_POSITION_PREFIX_TITLE + gameId;
        int position = SPUtils.getInt(AppUtil.getContext(), key, 0);
        List<RoadTitle> titles = new ArrayList<>();
        Integer[][] arr1_ids_he = new Integer[][]{new Integer[]{545, 546}, new Integer[]{547, 548}};
        Integer[][] arr1_ids_guan = new Integer[][]{new Integer[]{549, 550}, new Integer[]{551, 552}, new Integer[]{553, 554}};
        Integer[][] arr1_ids_ya = new Integer[][]{new Integer[]{555, 556}, new Integer[]{557, 558}, new Integer[]{559, 560}};
        Integer[][] arr1_ids3 = new Integer[][]{new Integer[]{561, 562}, new Integer[]{563, 564}, new Integer[]{565, 566}};
        Integer[][] arr1_ids4 = new Integer[][]{new Integer[]{567, 568}, new Integer[]{569, 570}, new Integer[]{571, 572}};
        Integer[][] arr1_ids5 = new Integer[][]{new Integer[]{573, 574}, new Integer[]{575, 576}, new Integer[]{577, 578}};
        Integer[][] arr1_ids6 = new Integer[][]{new Integer[]{679, 680}, new Integer[]{859, 860}};
        Integer[][] arr1_ids7 = new Integer[][]{new Integer[]{861, 862}, new Integer[]{863, 864}};
        Integer[][] arr1_ids8 = new Integer[][]{new Integer[]{865, 866}, new Integer[]{867, 868}};
        Integer[][] arr1_ids9 = new Integer[][]{new Integer[]{869, 870}, new Integer[]{871, 872}};
        Integer[][] arr1_ids10 = new Integer[][]{new Integer[]{873, 874}, new Integer[]{875, 876}};
        titles.add(new RoadTitle("冠亚和", arr2, arr1_ids_he, position == 0 ? true : false, gameId));
        titles.add(new RoadTitle("冠军", arr1, arr1_ids_guan, position == 1 ? true : false, gameId));
        titles.add(new RoadTitle("亚军", arr1, arr1_ids_ya, position == 2 ? true : false, gameId));
        titles.add(new RoadTitle("第三名", arr1, arr1_ids3, position == 3 ? true : false, gameId));
        titles.add(new RoadTitle("第四名", arr1, arr1_ids4, position == 4 ? true : false, gameId));
        titles.add(new RoadTitle("第五名", arr1, arr1_ids5, position == 5 ? true : false, gameId));
        titles.add(new RoadTitle("第六名", arr2, arr1_ids6, position == 6 ? true : false, gameId));
        titles.add(new RoadTitle("第七名", arr2, arr1_ids7, position == 7 ? true : false, gameId));
        titles.add(new RoadTitle("第八名", arr2, arr1_ids8, position == 8 ? true : false, gameId));
        titles.add(new RoadTitle("第九名", arr2, arr1_ids9, position == 9 ? true : false, gameId));
        titles.add(new RoadTitle("第十名", arr2, arr1_ids10, position == 10 ? true : false, gameId));
        return titles;
    }

}
