package com.ski.box.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ski.box.R;
import com.ski.box.utils.LanguageUtil;

/**
 * Created by tom on 2020/11/9.
 */
public class CusTextView extends androidx.appcompat.widget.AppCompatTextView {
    public CusTextView(@NonNull Context context) {
        super(context);
    }

    public CusTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CusTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ski_CsuTextView);
        String text = ta.getString(R.styleable.ski_CsuTextView_android_text);
        ta.recycle();
        setText(LanguageUtil.getText(text));
    }

    public void setCText(String text) {
        setText(LanguageUtil.getText(text));
    }

}
