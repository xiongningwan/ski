package com.ski.box.view.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ski.box.R;



public class HeaderView extends RelativeLayout implements View.OnClickListener {

    LinearLayout mLeftBtn;
    TextView mTitleName;
    TextView tvRight;
    ImageView ivRightAdd;
    ImageView ivRightMore;
    RelativeLayout rlRightImageBtn;
    private Context mConText;
    private OnRightClickListener mOnRightClickListener;

    public HeaderView(Context context) {
        super(context);
        initUI(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context) {
        mConText = context;
        View view = LayoutInflater.from(context).inflate(R.layout.ski_custom_header_view, this);
        mLeftBtn = view.findViewById(R.id.left_btn);
        mTitleName = view.findViewById(R.id.title_name);
        tvRight = view.findViewById(R.id.tv_right);
        ivRightAdd = view.findViewById(R.id.iv_right_add);
        ivRightMore = view.findViewById(R.id.iv_right_more);
        rlRightImageBtn = view.findViewById(R.id.rl_right_image_btn);

        mLeftBtn.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        rlRightImageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.left_btn) {
            if (mConText instanceof Activity) {
                ((Activity) mConText).finish();
            }
        } else if(id == R.id.tv_right) {
            if (mOnRightClickListener != null) {
                mOnRightClickListener.onRightClick();
            }
        }  else if(id == R.id.rl_right_image_btn) {
            if (mOnRightClickListener != null) {
                mOnRightClickListener.onRightClick();
            }
        }
    }


    public void setHeader(String name, boolean hasLeftBtn, boolean hasRightBtn, String rightStr) {
        mTitleName.setText(name);
        if (hasLeftBtn) {
            mLeftBtn.setVisibility(View.VISIBLE);
        } else {
            mLeftBtn.setVisibility(View.GONE);
        }
        if (hasRightBtn) {
            tvRight.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(rightStr)) {
                tvRight.setText(rightStr);
            } else {
                tvRight.setVisibility(View.GONE);
            }
        } else {
            tvRight.setVisibility(View.GONE);
        }
    }

    public void setHeader(String name, boolean hasLeftBtn) {
        mTitleName.setText(name);
        if (hasLeftBtn) {
            mLeftBtn.setVisibility(View.VISIBLE);
        } else {
            mLeftBtn.setVisibility(View.GONE);
        }
    }

    public void setRightImageBtn(boolean isAdd) {
        tvRight.setVisibility(View.GONE);
        rlRightImageBtn.setVisibility(VISIBLE);
        if (isAdd) {
            ivRightAdd.setVisibility(VISIBLE);
        } else {
            ivRightMore.setVisibility(VISIBLE);
        }
    }

    public void setRightAddVisiable() {
        ivRightAdd.setVisibility(VISIBLE);
    }

    public void setRightMoreVisiable() {
        ivRightMore.setVisibility(VISIBLE);
    }

    public View getHeadRightImgView() {
        return ivRightMore;
    }


    public void setmOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.mOnRightClickListener = onRightClickListener;
    }

    public interface OnRightClickListener {
        void onRightClick();
    }
}