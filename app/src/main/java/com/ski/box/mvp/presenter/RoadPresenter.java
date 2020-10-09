package com.ski.box.mvp.presenter;

import android.content.Context;

import com.ski.box.bean.DataCenter;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.road.RoadBean;
import com.ski.box.bean.road.RoadSub;
import com.ski.box.bean.road.RoadTitle;
import com.ski.box.mvp.contract.RoadContract;
import com.ski.box.mvp.remote.BetModel;
import com.ski.box.mvp.remote.LotteryModel;
import com.ski.box.mvp.remote.imodel.IBetModel;
import com.ski.box.mvp.remote.imodel.ILotteryModel;
import com.yb.core.base.BaseConsumer;
import com.yb.core.base.RxPresenter;
import com.yb.core.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RoadPresenter extends RxPresenter<RoadContract.View> implements RoadContract.Presenter {

    private final ILotteryModel mLotteryModel;
    private final IBetModel mBetModel;

    public RoadPresenter(Context context) {
        super(context);
        mLotteryModel = new LotteryModel();
        mBetModel = new BetModel();
    }

    @Override
    public void submitBet(int lotteryId, String planId) {
//        MkBetParamEntity entity = DataCenter.getInstance().getBetParamEntity();
//        entity.setTicketId(lotteryId);
//        entity.setPlanNo(planId);
//        Disposable disposable = mBetModel.doubleBet(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                String s = new Gson().toJson(o);
//            }
//        }, entity);
//        addDisposable(disposable);
    }

    Disposable mRDDisposable;

    /**
     * 刷新路子图数据
     *
     * @param list
     * @param roadTitles
     */
    @Override
    public void refreshHistoryData(List<LotteryNumBean> list, List<RoadTitle> roadTitles) {
        if (mRDDisposable != null && !mRDDisposable.isDisposed()) {
            mRDDisposable.dispose();
        }
        mRDDisposable = Observable.just(1).flatMap(new Function<Integer, Observable<String>>() {
            @Override
            public Observable<String> apply(Integer integer) throws Exception {
                convert(list, roadTitles);
                return Observable.just("1");
            }
        }).subscribeOn(Schedulers.newThread())
                .unsubscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mView.onRoadLoadSuccess(roadTitles);
                    }
                });
        addDisposable(mRDDisposable);
    }


    @Override
    public void getHistoryResult(int lotteryId, int num, List<RoadTitle> roadTitles) {
        try {
            Disposable disposable = mLotteryModel.getLotteryNumHistory(new Consumer<List<LotteryNumBean>>() {

                @Override
                public void accept(List<LotteryNumBean> list) throws Exception {
                    if (list != null && list.size() > 0) {
                        Disposable disposable2 = Single.create(new SingleOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                                convert(list, roadTitles);
                                emitter.onSuccess(1);
                            }
                        }).subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer integer) throws Exception {
                                        mView.onRoadLoadSuccess(roadTitles);
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {

                                    }
                                });
                        addDisposable(disposable2);
                    }
                }
            }, new BaseConsumer() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    super.accept(throwable);
                }
            }, String.valueOf(lotteryId), String.valueOf(num));
            addDisposable(disposable);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /*后台数据转换路子图*/
    private void convert(List<LotteryNumBean> list, List<RoadTitle> roadTitles) {
        // 清空数据
        for (int j = 0; j < roadTitles.size(); j++) {
            RoadTitle roadTitle = roadTitles.get(j);
            List<RoadSub> subList = roadTitle.getSubList();
            for (int k = 0; k < subList.size(); k++) {
                RoadSub roadSub = subList.get(k);
                List<RoadBean> list1 = roadSub.getAttList(); // 属性列表
                if (list1 == null) {
                    list1 = new ArrayList<>();
                    roadSub.setAttList(list1);
                } else {
                    list1.clear();
                }
            }
        }
        List<LotteryNumBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        Collections.reverse(tempList);

        for (int i = 0; i < tempList.size(); i++) {
            LotteryNumBean bean = tempList.get(i);
            String[] arr = bean.getCode().split(" ");

            int total = 0;//总和
            for (String s : arr) {
                total += Integer.parseInt(s);
            }

            //添加属性
            for (int j = 0; j < roadTitles.size(); j++) {
                RoadTitle roadTitle = roadTitles.get(j);
                List<RoadSub> subList = roadTitle.getSubList();
                for (int k = 0; k < subList.size(); k++) {
                    RoadSub roadSub = subList.get(k);
                    List<RoadBean> list1 = roadSub.getAttList(); // 属性列表
                    if (list1 == null) {
                        list1 = new ArrayList<>();
                        roadSub.setAttList(list1);
                    }
                    list1.add(new RoadBean(DataCenter.getInstance().getLotterySeriesId(),
                            arr,
                            total,
                            roadTitle.getTitle() + roadSub.getSubTitle()));
                }
            }
        }
        createMap(roadTitles);
    }

//    private void convert1(List<OpenResult> list, List<RoadTitle> roadTitles) {
//        Collections.reverse(list);
//        for (int i = 0; i < list.size(); i++) {
//            OpenResult bean = list.get(i);
//            String[] arr = bean.getWinNumber().split(",");
//
//            int total = 0;//总和
//            /*小于10，十位补零*/
//            for (int j = 0; j < arr.length; j++) {
//                int num = Integer.parseInt(arr[j]);
//                total += num;
//                if (num < 10 && arr[j].length() < 2) {
//                    arr[j] = "0" + arr[j];
//                }
//            }
//
//            //添加属性
//            for (int j = 0; j < roadTitles.size(); j++) {
//                RoadTitle roadTitle = roadTitles.get(j);
//                List<RoadSub> subList = roadTitle.getSubList();
//                for (int k = 0; k < subList.size(); k++) {
//                    RoadSub roadSub = subList.get(k);
//                    List<RoadBean> list1 = roadSub.getAttList(); // 属性列表
//                    if (list1 == null) {
//                        list1 = new ArrayList<>();
//                        roadSub.setAttList(list1);
//                    }
//                    list1.add(new RoadBean(DataCenter.getInstance().getLotterySeriesId(),
//                            arr,
//                            total,
//                            roadTitle.getTitle() + roadSub.getSubTitle()));
//                }
//            }
//        }
//
//        createMap(roadTitles);
//    }

    private void createMap(List<RoadTitle> roadTitles) {
        int screenWidth = ScreenUtils.getScreenWidth(mContext);
        int defaultSize = (screenWidth - ScreenUtils.dip2px(78)) / ScreenUtils.dip2px(15) + 1;

        for (int j = 0; j < roadTitles.size(); j++) {
            RoadTitle roadTitle = roadTitles.get(j);
            List<RoadSub> subList = roadTitle.getSubList();
            for (int k = 0; k < subList.size(); k++) {
                RoadSub roadSub = subList.get(k);
                List<RoadBean> attList = roadSub.getAttList(); // 属性列表
                if (attList == null) {
                    attList = new ArrayList<>();
                }
                /**数据分组**/
                List<List<RoadBean>> groupBPList = getGroupBP(attList, false);
                List<List<RoadBean>> groupBPList_xia3Lu = getGroupBP(attList, true);
                int daluSize = groupBPList.size();
                for (int i = 0; i < groupBPList.size(); i++) {
                    List<RoadBean> itemBpList = groupBPList.get(i);
                    if (itemBpList.size() > 6) {
                        int daluSize1 = itemBpList.size() - 6 + i;
                        daluSize = Math.max(daluSize, daluSize1);
                    }
                }
                daluSize = Math.max(daluSize, defaultSize);

                daluSize = daluSize * 6;
                List<RoadBean> daluInitMapList = getInitDaluMapList(daluSize);
                //  List<RoadBean> daluMapList = getRoadBeansWithoutTie(attList);
                List<RoadBean> list1 = fillMapRule(daluInitMapList, attList, false);

                List<RoadBean> initList2 = getInitDayanzaiMapList(daluSize);
                List<RoadBean> down3List2 = getMapDown3(groupBPList_xia3Lu, 2);
                List<RoadBean> list2 = fillMapRule(initList2, down3List2, true);

                List<RoadBean> initList3 = getInitDayanzaiMapList(daluSize);
                List<RoadBean> down3List3 = getMapDown3(groupBPList_xia3Lu, 3);
                List<RoadBean> list3 = fillMapRule(initList3, down3List3, true);

                List<RoadBean> initList4 = getInitDayanzaiMapList(daluSize);
                List<RoadBean> down3List4 = getMapDown3(groupBPList_xia3Lu, 4);
                List<RoadBean> list4 = fillMapRule(initList4, down3List4, true);

                roadSub.setList1(list1);
                roadSub.setList2(list2);
                roadSub.setList3(list3);
                roadSub.setList4(list4);
            }
        }
    }

    /**
     * 获取初始化珠路图列表数据
     */
    public List<RoadBean> getInitZhuPanluMapList(int size) {
        List<RoadBean> zhupanluList = new ArrayList();
        for (int i = 0; i < size; i++) {
            zhupanluList.add(new RoadBean());
        }
        return zhupanluList;
    }

    /**
     * 初始化大路图列表数据
     */
    public List<RoadBean> getInitDaluMapList(int size) {
        List<RoadBean> mapList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            setBaseInit(mapList, i);
        }
        return mapList;
    }

    public List<RoadBean> getInitDayanzaiMapList(int size) {
        List<RoadBean> mapList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            RoadBean bean = new RoadBean();
            mapList.add(bean);
        }
        return mapList;
    }

    private void setBaseInit(List<RoadBean> mapList, int i) {
        RoadBean bean = new RoadBean();
        bean.setLine(i / 6 + 1);
        mapList.add(bean);
    }

    public List<RoadBean> getRoadBeansWithoutTie(List<RoadBean> zhupanluMapList) {
        List<RoadBean> daluMapList = new ArrayList<>();

        RoadBean newBean;
        int tieCount = 0;
        RoadBean tempOld = new RoadBean();
        int tempOldInex = 0;

        int zplSize = zhupanluMapList.size();
        for (int i = 0; i < zplSize; i++) {
            newBean = zhupanluMapList.get(i);
            int newBP = newBean.getBp();
            if (3 == newBP) {
                tieCount++;
            } else {
                if (tieCount != 0) {
                    // 插入更新和值
                    if (daluMapList.size() != 0) {
                        tempOld.setTieCount(tieCount);
                        daluMapList.set(tempOldInex, tempOld);
                        tieCount = 0;
                    }
                    // 添加新值
                    newBean.setBp(newBean.getBp());
                    newBean.setPair(newBean.getPair());
                    daluMapList.add(newBean);
                    tempOld = newBean;
                    tempOldInex = daluMapList.indexOf(newBean);
                } else {
                    newBean.setBp(newBean.getBp());
                    newBean.setPair(newBean.getPair());
                    daluMapList.add(newBean);
                    tempOld = newBean;
                    tempOldInex = daluMapList.indexOf(newBean);
                }
            }
        }
//        LogUtils.d(daluMapList.toString());
        return daluMapList;
    }

    //根据没有和的列表分组
    //分为不同红蓝组
    public List<List<RoadBean>> getGroupBP(List<RoadBean> daluMapList, boolean isXiaSanlu) {
        List<List<RoadBean>> roadBeanListList = new ArrayList<>();
        List<RoadBean> list = new ArrayList<>();

        int size = daluMapList.size();
        int oldBp = 0;
        int bp = 0;
        for (int i = 0; i < size; i++) {
            RoadBean bean = daluMapList.get(i);
            if (bean != null) {
                bp = bean.getBp();
            }
            if (0 == bp) {
                continue;
            }
//            if (isXiaSanlu) {   //去掉和
            if (3 == bp) {
                continue;
            }
//            }
            if (bp != oldBp) {
                list = new ArrayList<>();
                roadBeanListList.add(list);
            }
            list.add(bean);
            oldBp = bp;
        }
        return roadBeanListList;
    }

    //根据分组内容，生成下三路
    public List<RoadBean> getMapDown3(List<List<RoadBean>> roadBeanListList, int startLine) {
        List<RoadBean> down3List = new ArrayList<>();
        int size = roadBeanListList.size();
        if (size < startLine) {
            return new ArrayList<>();
        }
        if (size == startLine) {
            int lineBegin = roadBeanListList.get(startLine - 1).size();
            if (lineBegin < 2) {
                return new ArrayList<>();
            }
        }
        List<Integer> oldSizeList = new ArrayList<>();
        List<Integer> oldFakeBpList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<RoadBean> list = roadBeanListList.get(i);
            int sizej = list.size();
            if (i > startLine - 2) {
                for (int j = 0; j < sizej + 1; j++) {
                    RoadBean bean = new RoadBean();
                    if (i < startLine - 1) {
                        continue;
                    }
                    if (i == startLine - 1 && j == 0) {
                        continue;
                    }
                    if (0 == j) {
                        int oldFakeBp = oldFakeBpList.get(i - startLine);
                        if (oldFakeBp == 1) {
                            bean.setBp(2);
                        } else if (oldFakeBp == 2) {
                            bean.setBp(1);
                        } else {
                            bean.setBp(0);
                        }
                        down3List.add(bean);
                        continue;
                    }

                    int oldSize = oldSizeList.get(i - startLine + 1);
                    if (j + 1 - oldSize == 1) { //多出刚好1个
                        bean.setBp(2);
                    } else if (j + 1 - oldSize > 1) { //多出大于1个
                        bean.setBp(1);
                    } else {
                        bean.setBp(1);
                    }

                    if (j == sizej) {
                        oldFakeBpList.add(bean.getBp());
                    } else {
                        down3List.add(bean);
                    }

                }
            }
            oldSizeList.add(sizej);
        }

        return down3List;
    }

    /**
     * 根据珠盘路图列表和大路规则生成大路数据列表
     * 填写大路，下三路
     *
     * @return
     */
    public List<RoadBean> fillMapRule(List<RoadBean> initMap, List<RoadBean> roadBeanList, boolean isXia3Lu) {
        int initSize = initMap.size();
        int daluzMapSize = roadBeanList.size();
        int index = 0;
        int count = 0;
        int line = 1;
        int afterOne = 0;
        int tempIndex = 0;
        for (int i = 0; i < daluzMapSize; i++) {
//            if (i > daluzMapSize - 1) {
//                break;
//            }
            RoadBean bean = roadBeanList.get(i);
            if (bean != null) {

                if (bean.getBp() == 0) continue;
                if (isXia3Lu) { // 是大路，有和性质的，不填充
                    if (bean.getBp() == 3 || bean.getBp() == 4) continue;
                }
                bean.setLine(line);
            }
            int isStartNewRow = isStartNewRow(roadBeanList, i);
            if (0 == isStartNewRow) {
                // 不开新的一行
                // 是否有下一个
                count++;
                if (0 == afterOne) {
                    // 没有下一个，往下排列
                    // 往下排列看个数是否超过六个，超过后往右边排列
                    if (count > 6) {
                        if (index > tempIndex) {
                            tempIndex = index;
                        }
                        tempIndex = tempIndex + 6;
                        isNeedAddMapLenth(initMap, tempIndex);
                        initMap.set(tempIndex, bean);
                    } else {
                        if (0 == i) {
                            initMap.set(index, bean);
                        } else {
                            index++;
                            isNeedAddMapLenth(initMap, index);
                            initMap.set(index, bean);
                        }
                    }
                } else {
                    // 有下一个往右边排列
                    if (index > tempIndex) {
                        tempIndex = index;
                    }
                    tempIndex = tempIndex + 6;
                    isNeedAddMapLenth(initMap, tempIndex);
                    initMap.set(tempIndex, bean);
                }
            } else {
                tempIndex = 0;
                count = 1;
                // 新的一行
                index = 6 * line;
                line++;
                bean.setLine(line);
                isNeedAddMapLenth(initMap, index);
                initMap.set(index, bean);
            }
            afterOne = getAfterOne(initMap, index);
        }
        return makeUp(initMap, initSize);
    }

    /*去多&补齐 空出两列*/
    private List<RoadBean> makeUp(List<RoadBean> list, int initSize) {
        int cur = list.size();
        int nonEmpty = cur;
        List<RoadBean> noBeans = new ArrayList<>();
        /*最后一个不为空的位置*/
        for (int i = cur - 1; i > 0; i--) {
            RoadBean bean = list.get(i);
            if (bean.getBp() != 0) {
                nonEmpty = i;
                break;
            }
            noBeans.add(bean);
        }
        list.removeAll(noBeans);
        // 补足
        int size = 12 + 6 - list.size() % 6;
        for (int i = 0; i < size; i++) {
            list.add(new RoadBean());
        }


        /*超过3列空白需要删除多余*/
//        if (cur - nonEmpty > 18) {
//            int delete = 6 - nonEmpty % 6 + nonEmpty + 11;
//            /*要保证画满一屏*/
//            for (int i = cur - 1; i > delete && i > initSize - 13; i--) {
//                RoadBean bean = list.get(i);
//                list.remove(bean);
//            }
//        } else if (cur - nonEmpty <= 12) {
//            int add = 0;
//            int temp = cur % 6 == 0 ? 6 : cur % 6;
//            temp += cur - nonEmpty < 6 ? 0 : 6;
//            int base = (cur - nonEmpty) <= 6 ? 12 : 6;
//            RoadBean bean = list.get(cur - temp);
//            add = cur % 6 == 0 ? base : (base - cur % 6 + (bean.getBp() == 0 ? 0 : 6));
//            for (int i = 0; i < add; i++) {
//                list.add(new RoadBean());
//            }
//        }

        return list;
    }

    /**
     * 添加列表长度
     */
    private void isNeedAddMapLenth(List<RoadBean> initMap, int tempIndex) {
        if (initMap.size() - 1 > tempIndex) {
            return;
        }
        for (int i = 0; i < 7; i++) {
            setBaseInit(initMap, i);
        }
    }

    /**
     * 获取不是和的前一个
     *
     * @param list
     * @param index
     * @return
     */
    private RoadBean getBeforeOneNotTie(List<RoadBean> list, int index) {
        RoadBean RoadBean = getBeforeOneObject(list, index);
        if (null == RoadBean) {
            RoadBean = new RoadBean();
        }
        if (3 == RoadBean.getBp()) {
            RoadBean = getBeforeOneObject(list, index - 1);
        }
        return RoadBean;
    }

    private RoadBean getBeforeOneObject(List<RoadBean> list, int index) {
        if (index - 1 >= 0) {
            return list.get(index - 1);
        } else {
            return new RoadBean();
        }
    }

    /**
     * 当前下标前一个
     */
    private int getBeforeOne(List<RoadBean> list, int index) {
        if (index - 1 >= 0) {
            return list.get(index - 1).getBp();
        } else {
            return 0;
        }
    }

    /**
     * 当前下标后面一个
     *
     * @param index
     * @return
     */
    private int getAfterOne(List<RoadBean> list, int index) {
        if (index + 1 < list.size() - 1) {
            return list.get(index + 1).getBp();
        } else {
            return 0;
        }
    }

    /**
     * 当前下标左边一个
     *
     * @param index
     * @return
     */
    private int getLeftOne(List<RoadBean> list, int index) {
        if (index - 6 >= 0) {
            return list.get(index - 6).getBp();
        } else {
            return 0;
        }
    }

    /**
     * 当前下标右边一个
     *
     * @param index
     * @return
     */
    private int getRightOne(List<RoadBean> list, int index) {
        if (index + 6 < list.size() - 1) {
            return list.get(index + 6).getBp();
        } else {
            return 0;
        }
    }

    /**
     * 是否需要开启新一行
     *
     * @param index
     * @return
     */
    private int isStartNewRow(List<RoadBean> list, int index) {
        if (0 == index) {
            return 0;
        }

//        int daluBpOld = getBeforeOne(list, index);
        int daluBpOld = getBeforeOneNotTie(list, index).getBp();
        int daluBpNew = list.get(index).getBp();

        if (0 == daluBpOld) {
            return 0;
        }

        if (4 == daluBpOld) {
            return 1;
        } else if (1 == daluBpOld) {
            if (2 == daluBpNew || 4 == daluBpNew) {
                return 1;
            } else {
                return 0;
            }
        } else if (2 == daluBpOld) {
            if (1 == daluBpNew || 4 == daluBpNew) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private void getDefaultFormats() {

    }
}
