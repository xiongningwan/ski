package com.ski.box.view.view.cus;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ski.box.R;
import com.ski.box.utils.ActivityUtil;
import com.yb.core.utils.LanguageUtil;

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
//        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
//            setTypeface(ActivityUtil.getFontTNR());
//        }
        setTypeface(ActivityUtil.getFontTNR());
        setText(LanguageUtil.getText(text));
    }

    public void setCText(String text) {
        setText(LanguageUtil.getText(text));
    }

    public void setCText(SpannableStringBuilder ssb) {
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            setText(LanguageUtil.getText(ssb.toString()));
        } else {
            setText(ssb);
        }
    }
}
