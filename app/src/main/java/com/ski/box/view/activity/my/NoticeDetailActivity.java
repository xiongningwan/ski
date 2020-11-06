package com.ski.box.view.activity.my;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.R;
import com.ski.box.bean.NoticeData;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseMVPActivity;

public class NoticeDetailActivity extends BaseMVPActivity<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {
    public static final String KEY_RECORD_BEAN = "key_notice_bean";
    private HeaderView mHeadView;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvTime;

    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_activity_notice_detail;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        tvTime = findViewById(R.id.tv_time);
        String title = "公告详情";
        mHeadView.setHeader(title, true);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        NoticeData.ListBean bean = getIntent().getParcelableExtra(KEY_RECORD_BEAN);
        setData(bean);
    }

    private void setData(NoticeData.ListBean bean) {

        tvTitle.setText(bean.getNoticeTitle());

        tvTime.setText(bean.getUpdateAt());
        String content = bean.getNoticeContent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            content = Html.fromHtml(content).toString();
        }
        tvContent.setText(content);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }


}
