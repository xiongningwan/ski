package com.ski.box.utils.lottery.algorithm.play;


import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.BDW115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.DWD115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.QWCZW115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.QWDDS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.RXDS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.RXDT115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.RXFS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZHXDS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZHXFS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZXDS115PlayHandler;
import com.ski.box.utils.lottery.algorithm.play.elevenfive.ZXFS115PlayHandler;

import java.util.Map;

/**
 * 十一选五
 */
public class ElevenFivePlayHandlerContext implements IAllPlayCode {
    public ElevenFivePlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> playSingleHandles) {
        gd11x5(playHandlers,playSingleHandles);
    }

    public static void gd11x5(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> single) {
        playHandlers.put( 前三直选复式_115, new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN,",", " "));
        playHandlers.put( 前三组选复式_115, new ZXFS115PlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN," "));

        playHandlers.put( 前二直选复式_115, new ZHXFS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANER,",", " "));
        playHandlers.put( 前二组选复式_115, new ZXFS115PlayHandler(2, ITicketPlayHandler.OFFSETS_QIANER," "));


        playHandlers.put( 不定胆_115, new BDW115PlayHandler());
        playHandlers.put( 定位胆_115, new DWD115PlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        playHandlers.put( 任选复式一中一, new RXFS115PlayHandler(1," "));
        playHandlers.put( 任选复式二中二, new RXFS115PlayHandler(2," "));
        playHandlers.put( 任选复式三中三, new RXFS115PlayHandler(3," "));
        playHandlers.put( 任选复式四中四, new RXFS115PlayHandler(4," "));
        playHandlers.put( 任选复式五中五, new RXFS115PlayHandler(5," "));
        playHandlers.put( 任选复式六中五, new RXFS115PlayHandler(6," "));
        playHandlers.put( 任选复式七中五, new RXFS115PlayHandler(7," "));
        playHandlers.put( 任选复式八中五, new RXFS115PlayHandler(8," "));
        playHandlers.put( 任选胆拖二中二, new RXDT115PlayHandler(2));
        playHandlers.put( 任选胆拖三中三, new RXDT115PlayHandler(3));
        playHandlers.put( 任选胆拖四中四, new RXDT115PlayHandler(4));
        playHandlers.put( 任选胆拖五中五, new RXDT115PlayHandler(5));
        playHandlers.put( 任选胆拖六中五, new RXDT115PlayHandler(6));
        playHandlers.put( 任选胆拖七中五, new RXDT115PlayHandler(7));
        playHandlers.put( 任选胆拖八中五, new RXDT115PlayHandler(8));
        playHandlers.put( 趣味定单双, new QWDDS115PlayHandler());
        playHandlers.put( 趣味猜中位, new QWCZW115PlayHandler());


        /*单式*/
        single.put( 前三直选单式_115, new ZHXDS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANSAN));
        single.put( 前三组选单式_115, new ZXDS115PlayHandler(3, ITicketPlayHandler.OFFSETS_QIANSAN));
        single.put( 前二直选单式_115, new ZHXDS115PlayHandler(ITicketPlayHandler.OFFSETS_QIANER));
        single.put( 前二组选单式_115, new ZXDS115PlayHandler(2, ITicketPlayHandler.OFFSETS_QIANER));
        single.put( 任选单式一中一, new RXDS115PlayHandler(1));
        single.put( 任选单式二中二, new RXDS115PlayHandler(2));
        single.put( 任选单式三中三, new RXDS115PlayHandler(3));
        single.put( 任选单式四中四, new RXDS115PlayHandler(4));
        single.put( 任选单式五中五, new RXDS115PlayHandler(5));
        single.put( 任选单式六中五, new RXDS115PlayHandler(6));
        single.put( 任选单式七中五, new RXDS115PlayHandler(7));
        single.put( 任选单式八中五, new RXDS115PlayHandler(8));















    }
}
