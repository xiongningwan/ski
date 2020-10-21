package com.ski.box.view.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.ski.box.R;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.utils.lottery.SettingManager;
import com.xujiaji.happybubble.BubbleDialog;
import com.yb.core.utils.ScreenUtils;

import java.util.List;


public class LotteryDialog {
    private AlertDialog leavelDailog;
    private TextView tv_right;

    public AlertDialog showAlertDialog(Context context, AlertConfigurationBean bean, OnSelectAlertDialogCallBack callBack) {
        if (leavelDailog != null) {
            leavelDailog.dismiss();
        }

        View inflate = LayoutInflater.from(context).inflate(R.layout.ski_alert_confirm_dialog, null);
        leavelDailog = new AlertDialog.Builder(context,R.style.alert_dialog_back_cor10)
                .setView(inflate)
                .show();


        //设置默认宽度和高度
        int dialogWidth = bean.getDialogWidth();
        int dialogHeight = bean.getDialogHeight();
        String title = bean.getTitle();
        String content = bean.getContent();
        String leftButtonText = bean.getLeftButtonText();
        String rightButtonText = bean.getRightButtonText();
        int titleLeftIcon = bean.getTitleLeftIcon();
        SpannableString spannableString = bean.getSpannableString();


        if (dialogWidth == 0) {
            dialogWidth = 250;
        }

        if (dialogHeight == 0) {
            dialogHeight = 150;
        }
        dialogWidth = ScreenUtils.dip2px(dialogWidth);
        dialogHeight = ScreenUtils.dip2px(dialogHeight);

        leavelDailog.getWindow().setLayout(dialogWidth, dialogHeight);
        TextView tv_title = (TextView) leavelDailog.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) leavelDailog.findViewById(R.id.tv_content);
        TextView tv_left = (TextView) leavelDailog.findViewById(R.id.tv_left);
        tv_right = (TextView) leavelDailog.findViewById(R.id.tv_right);
        if (TextUtils.isEmpty(title)) {
            tv_title.setVisibility(View.GONE);
        } else {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }

        if (TextUtils.isEmpty(leftButtonText)) {
            tv_left.setVisibility(View.GONE);
        } else {
            tv_left.setVisibility(View.VISIBLE);
            tv_left.setText(leftButtonText);
        }
        if (TextUtils.isEmpty(rightButtonText)) {
            tv_right.setVisibility(View.GONE);
        } else {
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(rightButtonText);
        }
        if (!TextUtils.isEmpty(content) || spannableString != null) {
            tv_content.setVisibility(View.VISIBLE);
            if (spannableString != null) {
                tv_content.setText(spannableString);
            } else {
                tv_content.setText(content);
            }
        } else {
            tv_content.setVisibility(View.GONE);
        }

        if (titleLeftIcon != 0) {
            Drawable drawable = context.getResources().getDrawable(titleLeftIcon);
            /// 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_title.setCompoundDrawables(drawable, null, null, null);
        }

        tv_left.setOnClickListener(v -> {
            leavelDailog.dismiss();
            callBack.leftButton();
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leavelDailog.dismiss();
                callBack.rightButton();
            }
        });
        return leavelDailog;
    }

    public void showRedLimitBubbleLayout(View view, List<RedLimitBean> limitBeans) {

        //获取区间投注金额
        String rang = SettingManager.getDoubleBetAmountRange();
        StringBuffer stringBuffer = new StringBuffer();
        for (int x = 0; x < limitBeans.size(); x++) {
            if (x > 2) {
                break;
            }
            RedLimitBean bean = limitBeans.get(x);
            String playName = bean.getPlayName();
            String playItemName = bean.getPlayItemName();
            stringBuffer.append(playName + "-" + playItemName + ",");

        }
        int size = limitBeans.size();
        if (size == 1) {
            stringBuffer.append("不符合区间金额，单期可投注金额区间为" + rang + "<a href=\"\">右上角菜单—设置</a>" + "修改金额区间");

        } else {
            stringBuffer.append("...不符合区间金额，单期可投注金额区间为" + rang + "<a href=\"\">右上角菜单—设置</a>" + "修改金额区间");

        }

        /*不满足条件 弹框*/
      /*  BubbleLayout bubbleLayout = new BubbleLayout(view.getContext());
        bubbleLayout.setBubbleColor(ContextCompat.getColor(view.getContext(), R.color.ybcp_transparent_grey));
        bubbleLayout.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
        View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.ybcp_dialog_bet_qujian, null, false);
        BubbleDialog bubbleDialog = new BubbleDialog(view.getContext());
        bubbleDialog.addContentView(inflate)
                .setClickedView(view)
                .setBubbleLayout(bubbleLayout)
                .setTransParentBackground()
                .calBar(true)
                .show();
        TextView tv_bet_qujian = inflate.findViewById(R.id.tv_bet_qujian);*/
//        quJianRemind(tv_bet_qujian, stringBuffer, view, bubbleDialog);





        View inflateView = LayoutInflater.from(view.getContext()).inflate(R.layout.ski_pub_light_remind, null, false);
        TextView tv_content = inflateView.findViewById(R.id.tv_content);
        quJianRemind(tv_content, stringBuffer, view, null);

        new LightReminder(view, inflateView).show();

    }

    private void quJianRemind(TextView tv_bet_qujian, StringBuffer stringBuffer, View view, BubbleDialog bubbleDialog) {
        Spanned charSequence;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(stringBuffer.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(stringBuffer.toString());
        }
        tv_bet_qujian.setText(charSequence);
        tv_bet_qujian.setMovementMethod(LinkMovementMethod.getInstance());


        CharSequence str = tv_bet_qujian.getText();
        if (str instanceof Spannable) {
            int end = str.length();
            Spannable sp = (Spannable) tv_bet_qujian.getText();  //构建Spannable对象、继承Spanned、Spanned对象继承CharSequener
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);  //找出text中的a标签
            //SpannableStringBuilder、SpannableString对象跟String对象差不多、只是比String对象多setSpan，
            //可以给字符串设置样式、大小、背景色...而 SpannableStringBuilder跟SpannableString的关系就跟String跟StringBuffer关系一样
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.clearSpans();//should clear old spans
            for (URLSpan url : urls) {
                MyClickSpan myURLSpan = new MyClickSpan(view, bubbleDialog);

                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.ybcp_white));
                //设置样式其中参数what是具体样式的实现对象，start则是该样式开始的位置，end对应的是样式结束的位置，
                // 参数 flags，定义在Spannable中的常量
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(foregroundColorSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            tv_bet_qujian.setText(style);
        }
    }

    private class MyClickSpan extends ClickableSpan {


        private final View mView;


        public MyClickSpan(View view, BubbleDialog bubbleDialog) {
            this.mView = view;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            super.updateDrawState(ds);
        }

        @Override
        public void onClick(View widget) {
            /*跳转到设置中心*/
//            RxBus.get().post(EVENT_GO_SETTINGFRAGMENT,"");
//            Intent intent = new Intent(mView.getContext(), ContainerActivity.class);
//            intent.putExtra(ContainerActivity.KEY_CLASS, SettingFragment.class.getSimpleName());
//            intent.putExtra("tag", 6);
//            mView.getContext().startActivity(intent);
        }
    }

    public interface OnSelectAlertDialogCallBack {
        void rightButton();

        void leftButton();
    }

    public LightReminder lightReminder;
    public void showLightReminder(Context context, View targeView, String text) {
        View inflateView = LayoutInflater.from(targeView.getContext()).inflate(R.layout.ski_pub_light_remind, null, false);
        TextView tv_content = inflateView.findViewById(R.id.tv_content);
        tv_content.setText(text);
        lightReminder = new LightReminder(targeView, inflateView).show();
    }


    Toast toastReminder;

    public void showCenterRemind(Context context, String text) {
        if (toastReminder != null) {
            toastReminder.cancel();
        }
        View v = LayoutInflater.from(context).inflate(R.layout.ski_eplay_toast, null);
        TextView textView = v.findViewById(R.id.textView1);
        textView.setText(text);
        toastReminder = new Toast(context);
        toastReminder.setDuration(Toast.LENGTH_SHORT);
        toastReminder.setView(v);
        toastReminder.setGravity(Gravity.CENTER, 0, 0);
        toastReminder.show();

    }
}
