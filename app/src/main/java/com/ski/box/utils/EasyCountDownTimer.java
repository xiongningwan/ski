package com.ski.box.utils;

import android.os.CountDownTimer;

import com.ski.box.view.view.cus.CusTextView;

/**
 * Created by alex on 18-3-27.
 */

public class EasyCountDownTimer extends CountDownTimer {

    private CusTextView mTextView;


    public EasyCountDownTimer(CusTextView textView, long millisInFuture, long countDownInterval) {
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
        mTextView.setCText("获取验证码");
        mTextView.setClickable(true);

    }
}
