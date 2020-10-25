package com.yb.core.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zy.core.R;


/**
 * @author Administrator 公共创建自定义对话框 方法 该方法主要负责创建 控件查找 及点击事件由自己方法实现
 */
public class BaseDialog {
    // 全局Toast
    private static Toast mToast = null;

    /**
     * 自定义 吐司
     *
     * @param context  最好用application的上下文
     * @param layoutId 资源id
     */
    public static void creatToast(Context context, int layoutId) {
        // 加载Toast布局
        View toastRoot = LayoutInflater.from(context).inflate(layoutId, null);
        // 非空判断防止重复创建多个
        if (null == mToast) {
            // Toast的初始化
            mToast = new Toast(context);
        }
        // 设置为全屏显示
        mToast.setGravity(Gravity.FILL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(toastRoot);
        mToast.show();
    }

    /**
     * 自定义Dialog
     *
     * @param context  上下文
     * @param layoutId 对话框布局
     * @return AlertDialogCommen 返回创建好的对话框实例 用alertDialog.getWindow(); 获取布局视图
     */
    public static AlertDialog creatDialog(Context context, int layoutId, int gravity) {
        switch (gravity) {
            case Gravity.TOP:
                gravity = Gravity.TOP;
                break;
            case Gravity.BOTTOM:
                gravity = Gravity.BOTTOM;
                break;
            case Gravity.CENTER:
                gravity = Gravity.CENTER;
                break;
            case Gravity.RIGHT:
                gravity = Gravity.RIGHT;
                break;
            default:
                // 默认居中
                gravity = Gravity.CENTER;
                break;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.ybcp_ActionSheetDialogStyle).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 设置是否点击外围解散
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        window.setContentView(layoutId);
        window.setGravity(gravity); // 此处可以设置dialog显示的位置
//		window.setWindowAnimations(R.style.AnimBottom); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.MATCH_PARENT; // 宽度
        lp.height = lp.WRAP_CONTENT; // 高度
        window.setAttributes(lp);
        return alertDialog;
    }

    /**
     * 自定义Dialog
     *
     * @param context 上下文
     * @return AlertDialogCommen 返回创建好的对话框实例 用alertDialog.getWindow(); 获取布局视图
     */
    public static AlertDialog createDialog(Context context, String title, String msg, DialogInterface.OnClickListener listener_negative, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setIcon(R.mipmap.app_logo)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton("取消", listener_negative)
                .setPositiveButton("去登录", listener).create();


        alertDialog.show();
        return alertDialog;
    }

    /**
     * 自定义Dialog(点击外围不解散)
     *
     * @param context  上下文
     * @param layoutId 对话框布局
     * @return AlertDialogCommen 返回创建好的对话框实例 用alertDialog.getWindow(); 获取布局视图
     */
    public static AlertDialog creatDialogCancelOutSide(Context context, int layoutId, int gravity, boolean isCancel) {
        switch (gravity) {
            case Gravity.TOP:
                gravity = Gravity.TOP;
                break;
            case Gravity.BOTTOM:
                gravity = Gravity.BOTTOM;
                break;
            case Gravity.CENTER:
                gravity = Gravity.CENTER;
                break;
            case Gravity.RIGHT:
                gravity = Gravity.RIGHT;
                break;
            default:
                // 默认居中
                gravity = Gravity.CENTER;
                break;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.ybcp_ActionSheetDialogStyle).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //设置Dialog是否允许物理返回键退出(true可以，false不可以)
        alertDialog.setCancelable(isCancel);
        // 设置是否点击外围解散
        alertDialog.setCanceledOnTouchOutside(isCancel);
        Window window = alertDialog.getWindow();
        window.setContentView(layoutId);
        window.setGravity(gravity); // 此处可以设置dialog显示的位置
//		window.setWindowAnimations(R.style.AnimBottom); // 添加动画
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = lp.MATCH_PARENT; // 宽度
        lp.height = lp.WRAP_CONTENT; // 高度
        window.setAttributes(lp);
        return alertDialog;
    }


    /**
     * 非全屏显示 自定义PopupWindow
     *
     * @param context  上下文
     * @param layoutId 对话框布局
     * @return PopupWindow 返回对话框实体 使用pw.getContentView(); 获取视图布局
     */
    public static PopupWindow creatPopupWindow(View v, int layoutId, Activity context, boolean isFull) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);

        //=====================产生背景变暗效果=============================
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        //=====================华丽丽的分割线=======================================================

        PopupWindow pw = new PopupWindow(mMenuView);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(null);
        if (isFull) {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        pw.showAsDropDown(v, 0, 0);
//        pw.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        /**
         * TODO 要记得在dismiss中恢复透明度哦
         */
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });

        return pw;
    }

    /**
     * 此方法是做了背景变暗处理的
     * (此处是适配了华为手机的)
     *
     * @param v
     * @param layoutId
     * @param context
     * @param isFull
     * @return
     */
    public static PopupWindow creatPopupWindowToBottom(View v, int layoutId, Activity context, boolean isFull) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);

        //=====================产生背景变暗效果=============================
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        //=====================华丽丽的分割线=======================================================

        PopupWindow pw = new PopupWindow(mMenuView);

        //点击外部不消失。
        pw.setFocusable(false);
        pw.setOutsideTouchable(false);

        pw.setBackgroundDrawable(null);


        if (isFull) {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        pw.showAtLocation(v, Gravity.BOTTOM, 0, 0);

        /**
         * TODO 要记得在dismiss中恢复透明度哦
         */
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });
        return pw;
    }

    /**
     * 此方法是做了背景变暗处理的
     * (此处是适配了华为手机的)
     *
     * @param v
     * @param layoutId
     * @param context
     * @param isFull
     * @return
     */
    public static PopupWindow creatPopupWindowToBottomCanCancle(View v, int layoutId, Activity context, boolean isFull) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);

        //=====================产生背景变暗效果=============================
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        //=====================华丽丽的分割线=======================================================

        PopupWindow pw = new PopupWindow(mMenuView);

        //点击外部不消失。
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);

        pw.setBackgroundDrawable(null);


        if (isFull) {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        pw.showAtLocation(v, Gravity.BOTTOM, 0, 0);

        /**
         * TODO 要记得在dismiss中恢复透明度哦
         */
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });
        return pw;
    }

    public static PopupWindow creatPopupWindowToCenter(View v, int layoutId, Activity context, boolean isFull) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(layoutId, null);

        //=====================产生背景变暗效果=============================
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        //=====================华丽丽的分割线=======================================================

        PopupWindow pw = new PopupWindow(mMenuView);

        //点击外部不消失。
        pw.setFocusable(false);
        pw.setOutsideTouchable(false);

        pw.setBackgroundDrawable(null);
        if (isFull) {
            pw.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        } else {
            pw.setWindowLayoutMode(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        pw.showAtLocation(v, Gravity.CENTER, 0, 0);

        /**
         * TODO 要记得在dismiss中恢复透明度哦
         */
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });
        return pw;
    }
}
