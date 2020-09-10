package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.pk10.DWDPk10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.DXDSPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.HZDXDSPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.HZPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.LHPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.RXDSPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.RXFSPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.ZHX1PK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.ZHXDSPK10PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.pk10.ZHXFSPK10PlayHandler;

import java.util.Map;

import static com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler.NUMS_PK10_old;

/**
 * PK10
 */
public class Pk10PlayHandlerContext implements IAllPlayCode {
    public Pk10PlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> single) {
        playHandlers.put( 猜冠军直选复式, new ZHX1PK10PlayHandler());
        playHandlers.put( 猜前二直选复式, new ZHXFSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANER, NUMS_PK10_old));
        playHandlers.put( 猜前三直选复式, new ZHXFSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANSAN, NUMS_PK10_old));
        playHandlers.put( 猜前四直选复式, new ZHXFSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANSI, NUMS_PK10_old));
        playHandlers.put( 猜前五直选复式, new ZHXFSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANWU, NUMS_PK10_old));
        playHandlers.put( 前五定位胆, new DWDPk10PlayHandler( ITicketPlayHandler.OFFSETS_PK10_TOP5, NUMS_PK10_old));
        playHandlers.put( 后五定位胆, new DWDPk10PlayHandler(ITicketPlayHandler.OFFSETS_PK10_LAST5, NUMS_PK10_old));
        playHandlers.put( 龙虎, new LHPK10PlayHandler( ));
        playHandlers.put( 任二直选复式_PK10, new RXFSPK10PlayHandler( 2));
        playHandlers.put( 任三直选复式_PK10, new RXFSPK10PlayHandler( 3));
        playHandlers.put( 前五大小单双, new DXDSPK10PlayHandler());
        playHandlers.put( 冠亚和值大小单双, new HZDXDSPK10PlayHandler());
        playHandlers.put( 冠亚和值, new HZPK10PlayHandler( 1, ITicketPlayHandler.OFFSETS_QIANER));
        playHandlers.put( 前三和值, new HZPK10PlayHandler( 2, ITicketPlayHandler.OFFSETS_QIANSAN));


        single.put( 猜前二直选单式, new ZHXDSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANER));
        single.put( 猜前三直选单式, new ZHXDSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANSAN));
        single.put( 猜前四直选单式, new ZHXDSPK10PlayHandler(ITicketPlayHandler.OFFSETS_QIANSI));
        single.put( 猜前五直选单式, new ZHXDSPK10PlayHandler( ITicketPlayHandler.OFFSETS_QIANWU));
        single.put( 任二直选单式_PK10, new RXDSPK10PlayHandler( 2));
        single.put( 任三直选单式_PK10, new RXDSPK10PlayHandler( 3));






    }
}
