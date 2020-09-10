package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.fc3d.BDWPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.DWDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.LHHPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZHHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZHKDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZHXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZHXFSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXBDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXZ3PlayHanlder;

import java.util.Map;

/**
 * 3D系列
 */
public class FC3dPlayHandlerContext implements IAllPlayCode {
    public FC3dPlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> single) {
        playHandlers.put( 三星直选复式, new ZHXFSPlayHandler( ITicketPlayHandler.OFFSETS_QIANSAN,",", " "));
        playHandlers.put( 三星直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 三星直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 三星组选三复式, new ZXZ3PlayHanlder( ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 三星组选六复式, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 三星组选和值, new ZXHZPlayHandler( ITicketPlayHandler.OFFSETS_QIANSAN, true));
        playHandlers.put( 三星组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_FC3D, true));
        playHandlers.put( 前二直选复式_3d, new ZHXFSPlayHandler( ITicketPlayHandler.OFFSETS_QIANER,",", " "));
        playHandlers.put( 前二直选和值_3d, new ZHHZPlayHandler( ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二直选跨度_3d, new ZHKDPlayHandler( ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二组选复式_3d, new BDWPlayHandler( 2, ITicketPlayHandler.OFFSETS_QIANER, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 前二组选和值_3d, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANER, false));
        playHandlers.put( 前二组选包胆_3d, new ZXBDPlayHandler( ITicketPlayHandler.OFFSETS_QIANER, ITicketPlayHandler.NUMS_FC3D, false));
        playHandlers.put( 后二直选复式_3d, new ZHXFSPlayHandler( ITicketPlayHandler.OFFSETS_ERSAN,",", " "));
        playHandlers.put( 后二直选和值_3d, new ZHHZPlayHandler( ITicketPlayHandler.OFFSETS_ERSAN));
        playHandlers.put( 后二直选跨度_3d, new ZHKDPlayHandler( ITicketPlayHandler.OFFSETS_ERSAN));
        playHandlers.put( 后二组选复式_3d, new BDWPlayHandler( 2, ITicketPlayHandler.OFFSETS_ERSAN, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 后二组选和值_3d, new ZXHZPlayHandler( ITicketPlayHandler.OFFSETS_ERSAN, false));
        playHandlers.put( 后二组选包胆_3d, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_ERSAN, ITicketPlayHandler.NUMS_FC3D, false));
        playHandlers.put( 定位胆_3d, new DWDPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 三星一码不定胆, new BDWPlayHandler( 1, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 三星二码不定胆, new BDWPlayHandler( 2, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_FC3D));
        playHandlers.put( 龙虎和_3d, new LHHPlayHandler());


        single.put( 三星直选单式, new ZHXDSPlayHandler( ITicketPlayHandler.OFFSETS_QIANSAN));
        single.put( 三星混合组选, new ZXDSPlayHandler( 3));
        single.put( 前二直选单式_3d, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        single.put( 前二组选单式_3d, new ZXDSPlayHandler( 2));
        single.put( 后二组选单式_3d, new ZXDSPlayHandler( 2));

        single.put( 后二直选单式_3d, new ZHXDSPlayHandler( ITicketPlayHandler.OFFSETS_ERSAN));



    }


}
