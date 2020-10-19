package com.ski.box.view.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.ski.box.R;
import com.yb.core.base.BaseActivity;


/**
 * Created by tom on 19-6-9.
 */
public class AgentWebViewActivity extends BaseActivity {
    LinearLayout llWeb;
    View viewStatus;
    View viewLine;
    private AgentWeb mAgentWeb;
    private String mUrl;
    private String mTitle;


    @Override
    protected int getLayoutId() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        return R.layout.ski_activity_agent_web_view;
    }

    @Override
    protected void initViews() {
        llWeb = findViewById(R.id.ll_web);
        viewStatus = findViewById(R.id.view_status);
        viewLine = findViewById(R.id.view_line);
    }

    @Override
    protected void initData(Bundle bundle) {
//        mUrl = "https://uat-wap.bobcp.vip/?token=d0ae55b28fe3455083604c18edbd67961602925615496";
        mUrl = "https://h5.tanovale.com/?token=bc7a31426e84443981557ea22c59ffd41602928119541#/home";
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) llWeb, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mUrl);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


//    @Override
////    protected void initImmersionBar() {
//        // super.initImmersionBar();
//        ImmersionBar.with(this)
//                .titleBar(viewStatus)
////                .statusBarView(mWebviewFL)
////                .titleBarMarginTop(mWebviewFL)
//                .statusBarDarkFont(true)
//                //解决软键盘与底部输入框冲突问题
//                .keyboardEnable(true)
//                .init();
//    }


}
