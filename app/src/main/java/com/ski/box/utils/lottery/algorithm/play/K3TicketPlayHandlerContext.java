package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3BSHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3CYGSHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3DXDSHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3EBTHDXHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3EBTHFXHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3ETHDXDSHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3ETHDXFSHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3ETHFXHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3HZHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3SBTHFXHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3SBTHHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3SLHHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3STHHandler;
import com.ski.box.utils.lottery.algorithm.play.kuai3.Kuai3ZLHandler;

import java.util.Map;

/**
 * 快三
 */
public class K3TicketPlayHandlerContext implements IAllPlayCode {

    public K3TicketPlayHandlerContext() {
    }

    public static void kuai3Handlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> playSingleHandles) {
        playHandlers.put( 和值大小单双, new Kuai3DXDSHandler());
        playHandlers.put( 和值, new Kuai3HZHandler());
        playHandlers.put( 三同号, new Kuai3STHHandler());
        playHandlers.put( 三不同号单选, new Kuai3SBTHHandler());
        playHandlers.put( 三不同号复选, new Kuai3SBTHFXHandler());
        playHandlers.put( 三连号, new Kuai3SLHHandler());
        playHandlers.put( 半顺, new Kuai3BSHandler());
        playHandlers.put( 杂六, new Kuai3ZLHandler());
        playHandlers.put( 二同号单选单式, new Kuai3ETHDXDSHandler());
        playHandlers.put( 二同号单选复式, new Kuai3ETHDXFSHandler());
        playHandlers.put( 二同号复选, new Kuai3ETHFXHandler());
        playHandlers.put( 二不同号单选, new Kuai3EBTHDXHandler());
        playHandlers.put( 二不同号复选, new Kuai3EBTHFXHandler());
        playHandlers.put( 猜一个号, new Kuai3CYGSHandler());
    }
}
