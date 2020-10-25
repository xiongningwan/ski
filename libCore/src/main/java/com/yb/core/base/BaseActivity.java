package com.yb.core.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.yb.core.utils.ActivityStackManager;
import com.yb.core.utils.StatusBarUtil;
import com.zy.core.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * activity的基本类
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected CompositeDisposable mDisposable;
    protected View mFooter;
    private int mSystemUiHeight;
    private View mToolBar;
    private boolean isCusStatus;
    private int toolbarBackgroundColor = R.drawable.ybcp_pub_status_bar_color;
    protected boolean isHasStatusBar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ActivityStackManager.getInstance().addActivity(this);
        if(isHasStatusBar) {
            addStatusBar();
        } else {
            View contentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            setContentView(contentView);
        }

        mFooter = View.inflate(this, R.layout.ybcp_item_no_more_data_footer, null);
        initViews();
        initData(savedInstanceState);
        processLogic();
    }

    private void addStatusBar() {
        View contentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        LinearLayout.LayoutParams params_ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout contentViewLayout = new LinearLayout(this);
        contentViewLayout.setOrientation(LinearLayout.VERTICAL);
        initToolBar();
        if (mSystemUiHeight == 0) {
            mSystemUiHeight = StatusBarUtil.getHeight(this);
        }
        contentViewLayout.addView(mToolBar, ViewGroup.LayoutParams.MATCH_PARENT, mSystemUiHeight);
        contentViewLayout.addView(contentView, params_ll);
        setContentView(contentViewLayout);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.transparencyBar(this, false);
        StatusBarUtil.StatusBarIconDark(this);
    }


    public void initToolBar() {
        if (mToolBar == null) {
            mToolBar = LayoutInflater.from(this).inflate(R.layout.ybcp_activity_tools_bar, null);
            mToolBar.setBackgroundResource(R.drawable.ybcp_pub_status_bar_color);
        }
    }

    public void setToolBackGroundColor(int colorValue) {
        toolbarBackgroundColor = colorValue;
        if (mToolBar != null) {
            mToolBar.setBackgroundResource(toolbarBackgroundColor);
        }
    }

    public void resetBackGroundColor() {
        toolbarBackgroundColor = R.drawable.ybcp_pub_status_bar_color;
        if (mToolBar != null) {
            mToolBar.setBackgroundResource(toolbarBackgroundColor);
        }
    }

    protected void setHasStatusBar(boolean isHasStatusBar) {
        this.isHasStatusBar =isHasStatusBar;
    }


    @LayoutRes
    protected abstract int getLayoutId();


    protected abstract void initViews();

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 逻辑使用区
     */
    protected void processLogic() {
    }

    /************************init area************************************/
    protected void addDisposable(Disposable d) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
      //  ActivityStackManager.getInstance().removeActivity(this);
      //  CleanLeakUtils.fixInputMethodManagerLeak(this);
    }

    /**
     * 从当前activity跳转到目标activity,<br>
     * 如果目标activity曾经打开过,就重新展现,<br>
     * 如果从来没打开过,就新建一个打开
     *
     * @param cls
     */
    public void gotoExistActivity(Class<?> cls) {
        Intent intent;
        intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }
}
