package com.ski.box.view.activity;

import android.content.Context;
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
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseActivity;


/**
 * Created by tom on 19-6-9.
 */
public class AgentWebViewActivity extends BaseActivity {
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_URL = "key_url";
    private LinearLayout llWeb;
    private HeaderView mHeadView;
    private View viewLine;
    private AgentWeb mAgentWeb;
    private String mUrl;
    private String mTitle;

    public static void startAgentWebView(Context context, String url) {
        Intent intent = new Intent(context, AgentWebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    public static void startAgentWebView(Context context, String title, String url) {
        Intent intent = new Intent(context, AgentWebViewActivity.class);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        return R.layout.ski_activity_agent_web_view;
    }

    @Override
    protected void initViews() {
        mHeadView = findViewById(R.id.head_view);
        llWeb = findViewById(R.id.ll_web);
        viewLine = findViewById(R.id.view_line);
        mTitle = getIntent().getStringExtra(KEY_TITLE);
        if (TextUtils.isEmpty(mTitle)) {
            mHeadView.setVisibility(View.GONE);
        } else {
            ImmersionBar.with(this).init();
            mHeadView.setHeader(mTitle, true);
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        mUrl = getIntent().getStringExtra(KEY_URL);
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
