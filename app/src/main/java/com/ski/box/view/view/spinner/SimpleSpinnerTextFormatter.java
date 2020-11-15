package com.ski.box.view.view.spinner;

import android.text.Spannable;
import android.text.SpannableString;


public class SimpleSpinnerTextFormatter implements SpinnerTextFormatter {

    @Override
    public Spannable format(Object item) {
        return new SpannableString(item.toString());
    }
}
