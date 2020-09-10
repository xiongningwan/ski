package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcErZhongTePlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcLianWeiPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcLianXiaoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcPtwsPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcQiSeBoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcTeChuanPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcTmTouWeiShuPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcZhongFenPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcZhongXiaoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.LhcZiXuanBuZhongPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.SeBoPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.TmPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.TxPlayhandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.ZbPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.ZmPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.ZtxPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.marksix.ZxPlayHandler;

import java.util.Map;

import static com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler.SHENG_XIAO;
import static com.ski.box.utils.lottery.algorithm.play.ITicketPlayHandler.WEI_SHU_NUMBER;

/**
 * 六合彩
 */
public class LhcPlayHandlerContext implements IAllPlayCode {
    public LhcPlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> playSingleHandles) {
        lhcHandlers(playHandlers);
    }

    public static void lhcHandlers(Map<String, ITicketPlayHandler> playHandlers) {
        playHandlers.put( 特码, new TmPlayHandler(特码));
        playHandlers.put( 特码头尾数, new LhcTmTouWeiShuPlayHandler(特码头尾数));
        playHandlers.put( 合肖, new TxPlayhandler(合肖));
        playHandlers.put( 特肖, new TxPlayhandler(特肖));
        playHandlers.put( 特肖五行, new TxPlayhandler(特肖五行));
        playHandlers.put( 特码形态, new TxPlayhandler(特码形态));
        playHandlers.put( 特和形态, new TxPlayhandler(特和形态));
        playHandlers.put( 特尾形态, new TxPlayhandler(特尾形态));
        playHandlers.put( 特肖形态, new TxPlayhandler(特肖形态));
        playHandlers.put( 色波, new SeBoPlayHandler(色波));
        playHandlers.put( 半波, new SeBoPlayHandler(半波));
        playHandlers.put( 正码, new ZmPlayHandler(正码,","));
        playHandlers.put( 正码1, new ZmPlayHandler(正码1,","));
        playHandlers.put( 正码2, new ZmPlayHandler(正码2,","));
        playHandlers.put( 正码3, new ZmPlayHandler(正码3,","));
        playHandlers.put( 正码4, new ZmPlayHandler(正码4,","));
        playHandlers.put( 正码5, new ZmPlayHandler(正码5,","));
        playHandlers.put( 正码6, new ZmPlayHandler(正码6,","));
        playHandlers.put( 三中二, new ZmPlayHandler(三中二,","));
        playHandlers.put( 三全中, new ZmPlayHandler(三全中,","));
        playHandlers.put( 二全中, new ZmPlayHandler(二全中,","));
        playHandlers.put( 四全中, new ZmPlayHandler(四全中,","));
        playHandlers.put( 正肖, new ZxPlayHandler(正肖));
        playHandlers.put( 正波, new ZbPlayHandler(正波));
        playHandlers.put( 总分, new LhcZhongFenPlayHandler(总分));
        playHandlers.put( 平特尾数, new LhcPtwsPlayHandler(平特尾数));
        playHandlers.put( 自选不中, new LhcZiXuanBuZhongPlayHandler(","));
        playHandlers.put( 二连尾, new LhcLianWeiPlayHandler(WEI_SHU_NUMBER,",",2));
        playHandlers.put( 三连尾, new LhcLianWeiPlayHandler(WEI_SHU_NUMBER,",",3));
        playHandlers.put( 四连尾, new LhcLianWeiPlayHandler(WEI_SHU_NUMBER,",",4));
        playHandlers.put( 五连尾, new LhcLianWeiPlayHandler(WEI_SHU_NUMBER,",",5));
        playHandlers.put( 二中特, new LhcErZhongTePlayHandler(","));
        playHandlers.put( 特串, new LhcTeChuanPlayHandler(","));
        playHandlers.put( 正特一肖, new ZtxPlayHandler(正特一肖));
        playHandlers.put( 二连肖, new LhcLianXiaoPlayHandler(SHENG_XIAO,",",2));
        playHandlers.put( 三连肖, new LhcLianXiaoPlayHandler(SHENG_XIAO,",",3));
        playHandlers.put( 四连肖, new LhcLianXiaoPlayHandler(SHENG_XIAO,",",4));
        playHandlers.put( 五连肖, new LhcLianXiaoPlayHandler(SHENG_XIAO,",",5));
        playHandlers.put( 正特肖数, new LhcZhongXiaoPlayHandler(正特肖数));
        playHandlers.put( 总肖单双, new ZtxPlayHandler(总肖单双));
        playHandlers.put( 七色波, new LhcQiSeBoPlayHandler(七色波));
    }
}
