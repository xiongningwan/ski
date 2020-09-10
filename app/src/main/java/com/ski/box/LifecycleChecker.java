package com.ski.box;




import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.yb.core.utils.LogUtils;
import com.yb.core.utils.ToastUtil;

/**
 * Created by tom on 2020/5/8.
 */
public class LifecycleChecker implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackground() {
        // 应用进入后台
        LogUtils.e("LifecycleChecker onAppBackground ON_STOP");
        ToastUtil.setIsForeground(false);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForeground() {
        // 应用进入前台
        LogUtils.e("LifecycleChecker onAppForeground ON_START");
        ToastUtil.setIsForeground(true);
    }
}
