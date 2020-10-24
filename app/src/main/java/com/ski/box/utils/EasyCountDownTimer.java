package com.ski.box.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by alex on 18-3-27.
 */

public class EasyCountDownTimer extends CountDownTimer {

    private TextView mTextView;


    public EasyCountDownTimer(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        mTextView.setText(millisUntilFinished / 1000 + "S");
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setClickable(true);

    }
}
