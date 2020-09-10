//package com.ski.box.bean.road;
//
//
//import com.ski.box.bean.LotteryNumBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * author : Paike
// * date : 2020/2/22 14:37
// * description :
// */
//public class RoadDataPk10 {
//    /*冠亚和值*/
//    List<RoadBean> bigRoad_list = new ArrayList<>();
//    List<RoadBean> single_list = new ArrayList<>();
//    List<RoadBean> longhu_list = new ArrayList<>();
//
//    public RoadDataPk10() {
//        super();
//    }
//
//    public RoadDataPk10 configData(String mleftPlay, List<LotteryNumBean> list) {
//        switch (mleftPlay) {
//            case "冠亚和":
//                for (int x = 0; x < list.size(); x++) {
//                    String code = list.get(x).getCode();
//                    String[] arr = code.split(" ");
//                    int guanyazhe = Integer.valueOf(arr[0]) + Integer.valueOf(arr[1]);
//                    SumBigSingleDetectBean sumBigSingleDetectBean = TrendFormatUtil.canculateSingleDoubleBig(guanyazhe, 12);
//                    String mBigOrSmall = sumBigSingleDetectBean.mBigOrSmall;
//                    String mSingle = sumBigSingleDetectBean.mSingle;
//                    RoadBean roadBeanBig = new RoadBean();
//                    RoadBean roadBeanSingle = new RoadBean();
//                    if (mBigOrSmall.equals("大")) {
//                        roadBeanBig.setName("大");
//                        roadBeanBig.setBp(RoadBean.Con.BANKER);
//                    } else {
//                        roadBeanBig.setName("小");
//                        roadBeanBig.setBp(RoadBean.Con.PLAYER);
//                    }
//                    bigRoad_list.add(roadBeanBig);
//                    if (mSingle.equals("单")) {
//                        roadBeanSingle.setName("单");
//                        roadBeanSingle.setBp(RoadBean.Con.BANKER);
//                    } else {
//                        roadBeanSingle.setName("双");
//                        roadBeanSingle.setBp(RoadBean.Con.PLAYER);
//                    }
//                    single_list.add(roadBeanSingle);
//
//                }
//                break;
//            case "冠军":
//                break;
//            case "亚军":
//                break;
//            case "第三名":
//                break;
//            case "第四名":
//                break;
//            case "第五名":
//                break;
//            case "第六名":
//                break;
//            case "第七名":
//                break;
//            case "第八名":
//                break;
//            case "第九名":
//                break;
//            case "第十名":
//                break;
//
//        }
//        return this;
//    }
//
//    public List<RoadBean> getRoadBeanByPlayName(String playName) {
//        switch (playName) {
//            case "大小":
//                return bigRoad_list;
//            case "单双":
//                return single_list;
//            case "龙虎":
//                return longhu_list;
//                default:
//                    break;
//
//        }
//        return bigRoad_list;
//    }
//
//    public List<RoadBean> getBigSmal() {
//        return bigRoad_list;
//    }
//
//    public List<RoadBean> getSingleDouble() {
//        return single_list;
//    }
//
//    public List<RoadBean> getLongHu() {
//        return longhu_list;
//    }
//}
