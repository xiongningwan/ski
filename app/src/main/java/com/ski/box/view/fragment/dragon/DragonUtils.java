package com.ski.box.view.fragment.dragon;

import java.util.TreeMap;

/**
 * @CreateDate: 2020/6/6 19:10
 * @ClassName: DragonUtils
 * @Description:
 */
public class DragonUtils {

   private static  int timeInterval = 700;
   private static TreeMap<Integer,Long>  ticketMap= new TreeMap<>();

    public static  boolean   isRequestCd(int ticketId){
        if (!ticketMap.containsKey(ticketId)) {
            ticketMap.put(ticketId,System.currentTimeMillis());
            return true;
        }else{

            long lastTime = ticketMap.get(ticketId);
//            Log.e("---times----前",String.valueOf(lastTime));
            long nowTime = System.currentTimeMillis();
            long times = nowTime - lastTime;
            if (times > timeInterval) {
                ticketMap.put(ticketId,nowTime);
//                Log.e("---times----后",String.valueOf(nowTime));
                return true;
            }
        }

        return false;
    }

}
