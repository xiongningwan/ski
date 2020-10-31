package com.ski.box.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.ski.box.R;
import com.ski.box.view.fragment.RechargeFragment;
import com.ski.box.view.fragment.record.RecordBetFragment;
import com.ski.box.view.fragment.record.RecordMoneyFragment;
import com.ski.box.view.view.HeaderView;
import com.yb.core.base.BaseActivity;
import com.yb.core.utils.FragmentUtils;

public class ContainerActivity extends BaseActivity implements View.OnClickListener {
    public static String KEY_CLASS = "key_class";
    private HeaderView mHeadView;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_layout_container;
    }

    @Override
    protected void initViews() {
        ImmersionBar.with(this).init();
        mHeadView = findViewById(R.id.head_view);
    }

    @Override
    protected void initData(Bundle bundle) {
        Intent intent = getIntent();
        String key = intent.getStringExtra(KEY_CLASS);
        Fragment fragment = new Fragment();
        if (RecordBetFragment.class.getSimpleName().equals(key)) {
            mHeadView.setHeader(getString(R.string.ski_record_bet), true);
            fragment = RecordBetFragment.newInstance();
        } else if (RecordMoneyFragment.class.getSimpleName().equals(key)) {
            mHeadView.setHeader(getString(R.string.ski_record_money), true);
            fragment = RecordMoneyFragment.newInstance();
        }  else if (RechargeFragment.class.getSimpleName().equals(key)) {
            mHeadView.setHeader(getString(R.string.ski_money_recharge), true);
            fragment = RechargeFragment.newInstance(1);
        }
        FragmentUtils.add(getSupportFragmentManager(), fragment, R.id.f_container, false);
    }

    @Override
    public void onClick(View v) {

    }


}
