package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.fc3d.ZXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.BDWPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.BJLPlayerHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.DWDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.DXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.HZDXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.HZDXDSPlayHandler2;
import com.ski.box.utils.lottery.algorithm.play.ssc.HZWSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.LHHPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.NIUPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.QWTSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RX2ZXBDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZHDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZHFSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZHHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZX12PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZX24PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZX4PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZX6PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZXFSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZXHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZXZ3PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.RXZXZ6PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.SHAPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZHHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZHKDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZHXDSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZHXFSPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZJHPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZUHEPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZX6PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZXBDPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZXHZPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZXPlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZXZ3PlayHanlder;
import com.ski.box.utils.lottery.algorithm.play.ssc.ZXZ3Z6DSPlayHandler;

import java.util.Map;

/**
 * 时时彩
 */
public class SSCPlayHandlerContext implements IAllPlayCode {
    public SSCPlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> playSingleHandles) {
        cqsscHandlers(playHandlers, playSingleHandles);
    }

    public static void cqsscHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> single) {
        playHandlers.put( 五星直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_WUXIN));
        playHandlers.put( 五星直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_WUXIN));
        playHandlers.put( 五星组选120, new BDWPlayHandler(5, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 五星组选60, new ZXPlayHanlder(1, 2, 3, 1, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 五星组选30, new ZXPlayHanlder(2, 2, 1, 1, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 五星组选20, new ZXPlayHanlder(1, 3, 2, 1, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 五星组选10, new ZXPlayHanlder(1, 3, 1, 2, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 五星组选5, new ZXPlayHanlder(1, 4, 1, 1, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前四直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_QIANSI));
        playHandlers.put( 前四直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_QIANSI));
        playHandlers.put( 前四组选24, new BDWPlayHandler(4, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前四组选12, new ZXPlayHanlder(1, 2, 2, 1, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前四组选6, new ZX6PlayHanlder(ITicketPlayHandler.OFFSETS_QIANSI));
        playHandlers.put( 前四组选4, new ZXPlayHanlder(1, 3, 1, 1, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 后四直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSI));
        playHandlers.put( 后四直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_HOUSI));
        playHandlers.put( 后四组选24, new BDWPlayHandler(4, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后四组选12, new ZXPlayHanlder(1, 2, 2, 1, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 后四组选6, new ZX6PlayHanlder(ITicketPlayHandler.OFFSETS_HOUSI));
        playHandlers.put( 后四组选4, new ZXPlayHanlder(1, 3, 1, 1, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前三直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 前三直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 前三直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 前三直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 前三组选三, new ZXZ3PlayHanlder(ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前三组选六, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前三组选和值, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 前三组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前三和值尾数, new HZWSPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 中三直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        playHandlers.put( 中三直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        playHandlers.put( 中三直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        playHandlers.put( 中三直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        playHandlers.put( 中三组选三, new ZXZ3PlayHanlder(ITicketPlayHandler.OFFSETS_ZHONGSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 中三组选六, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_ZHONGSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 中三组选和值, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));

        playHandlers.put( 中三组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 中三和值尾数, new HZWSPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        playHandlers.put( 后三直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 后三直选组合, new ZUHEPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 后三直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 后三直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 后三组选三, new ZXZ3PlayHanlder(ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 后三组选六, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后三组选和值, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));

        playHandlers.put( 后三组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 后三和值尾数, new HZWSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 前二直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二组选复式, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_QIANER, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前二组选和值, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前二组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_QIANER, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 后二直选复式, new ZHXFSPlayHandler(ITicketPlayHandler.OFFSETS_HOUER));
        playHandlers.put( 后二直选和值, new ZHHZPlayHandler(ITicketPlayHandler.OFFSETS_HOUER));
        playHandlers.put( 后二直选跨度, new ZHKDPlayHandler(ITicketPlayHandler.OFFSETS_HOUER));
        playHandlers.put( 后二组选复式, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_HOUER, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后二组选和值, new ZXHZPlayHandler(ITicketPlayHandler.OFFSETS_HOUER));
        playHandlers.put( 后二组选包胆, new ZXBDPlayHandler(ITicketPlayHandler.OFFSETS_HOUER, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 定位胆, new DWDPlayHandler(ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 前三一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前三二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_QIANSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 中三一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_ZHONGSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 中三二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_ZHONGSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后三一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后三二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_HOUSAN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前四一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前四二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前四三码不定胆, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后四一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后四二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 后四三码不定胆, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_HOUSI, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 五星一码不定胆, new BDWPlayHandler(1, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 五星二码不定胆, new BDWPlayHandler(2, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 五星三码不定胆, new BDWPlayHandler(3, ITicketPlayHandler.OFFSETS_WUXIN, ITicketPlayHandler.NUMS_SSC," "));
        playHandlers.put( 前二大小单双组合, new DXDSPlayHandler(ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前三大小单双组合, new DXDSPlayHandler(ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 后二大小单双组合, new DXDSPlayHandler(ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_HOUER));
        playHandlers.put( 后三大小单双组合, new DXDSPlayHandler(ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_HOUSAN));
        playHandlers.put( 五星和值大小单双, new HZDXDSPlayHandler(ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_WUXIN));
        /*  playHandlers.put( 三星和值大小单双, new HZDXDSPlayHandler2(1100204, ITicketPlayHandler.BET_DXDS, ITicketPlayHandler.OFFSETS_HOUSAN));*/
        playHandlers.put( 三星和值大小单双, new HZDXDSPlayHandler2(三星和值大小单双, ITicketPlayHandler.BET_DXDS));
        playHandlers.put( 一帆风顺, new QWTSPlayHandler(ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 好事成双, new QWTSPlayHandler(ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 三星报喜, new QWTSPlayHandler(ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 四季发财, new QWTSPlayHandler(ITicketPlayHandler.NUMS_SSC));
        playHandlers.put( 任二直选复式, new RXZHFSPlayHandler(2));
        playHandlers.put( 任二直选和值, new RXZHHZPlayHandler(2));
        playHandlers.put( 任二组选复式, new RXZXFSPlayHandler(2));
        playHandlers.put( 任二组选和值, new RXZXHZPlayHandler(2));
        playHandlers.put( 任二组选包胆, new RX2ZXBDPlayHandler());
        playHandlers.put( 任三直选复式, new RXZHFSPlayHandler(3));
        playHandlers.put( 任三直选和值, new RXZHHZPlayHandler(3));
        playHandlers.put( 任三组选三复式, new RXZXZ3PlayHanlder(3));
        playHandlers.put( 任三组选六复式, new RXZXZ6PlayHanlder(3));
        playHandlers.put( 任三组选和值, new RXZXHZPlayHandler(3));
        playHandlers.put( 任四直选复式, new RXZHFSPlayHandler(4));
        playHandlers.put( 任四组选24, new RXZX24PlayHanlder(4));
        playHandlers.put( 任四组选12, new RXZX12PlayHanlder(4));
        playHandlers.put( 任四组选6, new RXZX6PlayHanlder(4));
        playHandlers.put( 任四组选4, new RXZX4PlayHanlder(4));
        playHandlers.put( 龙虎和, new LHHPlayHandler());
        playHandlers.put( 梭哈, new SHAPlayHandler(ITicketPlayHandler.SUOHA_BET_INDEX));
        playHandlers.put( 三星炸金花, new ZJHPlayHandler());
        playHandlers.put( 百家乐, new BJLPlayerHandler(ITicketPlayHandler.BJL_BET_IDNEX));
        playHandlers.put( 牛牛, new NIUPlayHandler(ITicketPlayHandler.NIUNIU_BET_INDEX));
        /*单式*/

        single.put( 五星直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_WUXIN));
        single.put( 前四直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_QIANSI));
        single.put( 后四直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSI));
        single.put( 前三直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        single.put( 前三混合组选, new ZXDSPlayHandler(3));
        single.put( 前三组三单式, new ZXZ3Z6DSPlayHandler(3));
        single.put( 前三组六单式, new ZXZ3Z6DSPlayHandler(6));
        single.put( 中三直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_ZHONGSAN));
        single.put( 中三混合组选, new ZXDSPlayHandler(3));
        single.put( 中三组三单式, new ZXZ3Z6DSPlayHandler(3));
        single.put( 中三组六单式, new ZXZ3Z6DSPlayHandler(6));
        single.put( 后三直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_HOUSAN));
        single.put( 后三混合组选, new ZXDSPlayHandler(3));
        single.put( 后三组三单式, new ZXZ3Z6DSPlayHandler(3));
        single.put( 后三组六单式, new ZXZ3Z6DSPlayHandler(6));
        single.put( 前二直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        single.put( 前二组选单式, new ZXDSPlayHandler(2));
        single.put( 后二直选单式, new ZHXDSPlayHandler(ITicketPlayHandler.OFFSETS_HOUER));
        single.put( 后二组选单式, new ZXDSPlayHandler(2));
        single.put( 任二直选单式, new RXZHDSPlayHandler(2));
        single.put(任二组选单式, new RXZXDSPlayHandler(2));
        single.put( 任三直选单式, new RXZHDSPlayHandler(3));
        single.put( 任三混合组选, new RXZXDSPlayHandler(3));
        single.put( 任四直选单式, new RXZHDSPlayHandler(4));

    }
}
