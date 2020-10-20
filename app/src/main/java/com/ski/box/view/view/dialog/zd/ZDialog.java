package com.ski.box.view.view.dialog.zd;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



/**
 * Author:zx on 2019/9/2520:41
 */
public class ZDialog extends BaseDialogFragment implements DialogInterface.OnKeyListener {

    private static final String KEY_TCONTROLLER = "ZController";
    protected ZController zController;

    public ZDialog() {
        zController = new ZController();
    }

    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            zController = (ZController) savedInstanceState.getSerializable(KEY_TCONTROLLER);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setOnKeyListener(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY_TCONTROLLER, zController);
        super.onSaveInstanceState(outState);
    }

    /**
     * 弹窗消失时回调方法
     */
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = zController.getOnDismissListener();
        if (null != onDismissListener) {
            onDismissListener.onDismiss(dialog);
        }
    }

    /**
     * 弹窗消失时回调方法
     */


    @Override
    protected int getLayoutRes() {
        return zController.getLayoutRes();
    }


    @Override
    protected View getDialogView() {
        return zController.getDialogView();
    }

    @Override
    protected void bindView(View view) {
        //控件点击事件处理
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (zController.getIds() != null && zController.getIds().length > 0) {
            for (int id : zController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        //回调方法获取到布局，进行处理
        if (zController.getOnBindViewListener() != null) {
            zController.getOnBindViewListener().bindView(viewHolder);
        }
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public int getGravity() {
        return zController.getGravity();
    }

    @Override
    public int getDialogX() {
        return zController.getDialogX();
    }

    @Override
    public int getDialogY() {
        return zController.getDialogY();
    }

    @Override
    public float getDimAmount() {
        return zController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return zController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return zController.getWidth();
    }

    @Override
    protected boolean isCancelableOutside() {
        return zController.isCancelableOutside();
    }

    @Override
    protected int getDialogAnimationRes() {
        return zController.getDialogAnimationRes();
    }

    @Override
    protected DialogInterface.OnKeyListener getOnKeyListener() {
        return zController.getOnKeyListener();
    }

    public OnViewClickListener getOnViewClickListener() {
        return zController.getOnViewClickListener();
    }

    public ZDialog show() {
        try {
            FragmentTransaction ft = zController.getFragmentManager().beginTransaction();
            ft.add(this, zController.getTag());
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    /*监听点击返回键*/
    private OnkeyBackListener onkeyBackListener;
    public  interface OnkeyBackListener {
        boolean backListener();
    }
    public void setOnkeyBackListener(OnkeyBackListener listener) {
        onkeyBackListener = listener;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int action = event.getAction();
            if (action == KeyEvent.ACTION_UP) {
                if (onkeyBackListener != null) {
                    boolean b = onkeyBackListener.backListener();
                    return b;
                }
            }

        }
        return false;
    }

    /*********************************************************************?
     *使用Builder模式实现
     */
    public static class Builder {
        ZController.ZParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new ZController.ZParams();
            params.mFragmentManager = fragmentManager;
            params.mIsInstance = true;
        }

        //传入弹窗xml布局文件
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        //直接传入控件
        public Builder setDialogView(View view) {
            params.mDialogView = view;
            return this;
        }

        //设置弹窗宽度
        public Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        //设置弹窗高度
        public Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        //设置弹窗宽度是屏幕宽度的比例：0-1
        public Builder setScreenWidthAspect(Context context, float widthAspect) {
            params.mWidth = (int) (getScreenWidth(context) * widthAspect);
            return this;
        }

        //设置弹窗高度是屏幕高度的比例：0-1
        public Builder setScreenHeightAspect(Context context, float heightAspect) {
            params.mHeight = (int) (getScreenHeight(context) * heightAspect);
            return this;
        }

        //设置弹窗在屏幕种显示是位置
        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public Builder setDialogX(int x) {
            params.mDialogX = x;
            return this;
        }

        public Builder setDialogY(int y) {
            params.mDialogY = y;
            return this;
        }

        //设置弹窗在弹窗区域外是否可以取消
        public Builder setCancelableOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        //弹窗dismiss时监听回调方法
        public Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListenre = dismissListener;
            return this;
        }

        //设置弹窗背景透明度
        public Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        //通过回调拿到弹窗布局控件对象（*）
        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.mBindViewListerner = listener;
            return this;
        }

        //添加弹窗控件的点击事件(**)
        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        //弹窗控件点击回调
        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        //设置弹窗动画
        public Builder setDialogAnimationRes(int animationRes) {
            params.mDialogAnimationRes = animationRes;
            return this;
        }

        //监听弹窗后，返回键点击事件
        public Builder setOnKeyListener(DialogInterface.OnKeyListener keyListener) {
            params.mKeyListener = keyListener;
            return this;
        }

        public Builder setIsInstance(boolean isInstance) {
            params.mIsInstance = isInstance;
            return this;
        }

        //真正创建ZDialog对象实例
        public ZDialog create() {
            ZDialog dialog = new ZDialog();
            //将数据从Builder的Params种传递到Dialog中
            params.apply(dialog.zController);
            return dialog;
        }

    }
}

