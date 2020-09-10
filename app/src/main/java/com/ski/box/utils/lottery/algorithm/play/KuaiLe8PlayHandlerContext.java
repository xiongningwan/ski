package com.ski.box.utils.lottery.algorithm.play;

import com.ski.box.bean.lottery.IAllPlayCode;
import com.ski.box.utils.lottery.algorithm.play.kuaile8.QWPlayHandler;
import com.ski.box.utils.lottery.algorithm.play.kuaile8.ZXPlayHandler;

import java.util.Map;

/**
 * 快乐彩
 */
public class KuaiLe8PlayHandlerContext implements IAllPlayCode {
    public KuaiLe8PlayHandlerContext() {
    }

    public static void fillPlayHandlers(Map<String, ITicketPlayHandler> playHandlers, Map<String, ITicketSinglePlayHanler> playSingleHandles) {
        playHandlers.put( 任选一, new ZXPlayHandler(1, 任选一));
        playHandlers.put( 任选二, new ZXPlayHandler(2, 任选二));
        playHandlers.put( 任选三, new ZXPlayHandler(3, 任选三));
        playHandlers.put( 任选四, new ZXPlayHandler(4, 任选四));
        playHandlers.put( 任选五, new ZXPlayHandler(5, 任选五));
        playHandlers.put( 任选六, new ZXPlayHandler(6, 任选六));
        playHandlers.put( 任选七, new ZXPlayHandler(7, 任选七));
        playHandlers.put( 上下盘, new QWPlayHandler(1001));
        playHandlers.put( 奇偶盘, new QWPlayHandler(2001));
        playHandlers.put( 趣味和值大小单双, new QWPlayHandler(3001));
    }
}
