package com.ski.box.utils.lottery.algorithm.play;


import com.ski.box.bean.lottery.LotteryConstant;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticHandler {
    private static ArithmeticHandler mSingleton = null;
    private static Map<String, ITicketPlayHandler> playHandlers = new HashMap();
    private static Map<String, ITicketSinglePlayHanler> playSingleHandles = new HashMap();
    private static int lastSeriseId = -1;

    public static ArithmeticHandler getInstance(int seriesId) {
        if (lastSeriseId != seriesId) {
            mSingleton = new ArithmeticHandler(seriesId);
            lastSeriseId = seriesId;
        }
        return mSingleton;
    }

    public ArithmeticHandler(int seriesId) {
        onResetAlgorithm(seriesId);
    }

    public void onResetAlgorithm(int seriesId) {
        playHandlers.clear();
        playSingleHandles.clear();
        switch (seriesId) {
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_PL35:
                SSCPlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_11X5:
                ElevenFivePlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_PK10:
                Pk10PlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_LHC:
                LhcPlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_K3:
                K3TicketPlayHandlerContext.kuai3Handlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_3D:
                FC3dPlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
            case LotteryConstant.SER_ID_KL8:
                KuaiLe8PlayHandlerContext.fillPlayHandlers(playHandlers,playSingleHandles);
                break;
        }
    }

    public ITicketPlayHandler getHandler(String unionId) {
        return playHandlers.get(unionId);
    }

    public ITicketSinglePlayHanler getSingleHandler(String unionId) {
        return playSingleHandles.get(unionId);
    }
}
