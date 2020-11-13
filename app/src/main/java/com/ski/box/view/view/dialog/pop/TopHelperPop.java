package com.ski.box.view.view.dialog.pop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ski.box.R;
import com.ski.box.view.activity.ContainerActivity;
import com.ski.box.view.activity.my.GameSetActivity;
import com.ski.box.view.fragment.record.RecordBetFragment;
import com.ski.box.view.fragment.record.RecordMoneyFragment;
import com.zyyoona7.popup.BasePopup;


/**
 * 菜单栏
 */
public class TopHelperPop extends BasePopup<TopHelperPop> implements View.OnClickListener {
    Context mContext;
    private ConstraintLayout mCL1;
    private ConstraintLayout mCL2;
    private ConstraintLayout mCL3;

    public TopHelperPop(Context context) {
        setContext(context);
        mContext = context;
    }

    public static TopHelperPop create(Context context) {
        return new TopHelperPop(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.ski_bet_helper_menu, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(true);
        setBackgroundDimEnable(false);
    }

    @Override
    protected void initViews(View view, TopHelperPop topHelperPop) {
        mCL1 = view.findViewById(R.id.cl_1);
        mCL2 = view.findViewById(R.id.cl_2);
        mCL3 = view.findViewById(R.id.cl_3);

        mCL1.setOnClickListener(this);
        mCL2.setOnClickListener(this);
        mCL3.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.cl_1) {
            Intent intent =  new Intent(mContext, ContainerActivity.class);
            intent.putExtra(ContainerActivity.KEY_CLASS, RecordBetFragment.class.getSimpleName());
            mContext.startActivity(intent);
        } else if(id == R.id.cl_2) {
            Intent intent =  new Intent(mContext, ContainerActivity.class);
            intent.putExtra(ContainerActivity.KEY_CLASS, RecordMoneyFragment.class.getSimpleName());
            mContext.startActivity(intent);
        } else if(id == R.id.cl_3) {
            mContext.startActivity(new Intent(mContext, GameSetActivity.class));
        }
    }



}
