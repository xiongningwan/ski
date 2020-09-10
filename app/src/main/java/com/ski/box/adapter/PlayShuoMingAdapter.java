package com.ski.box.adapter;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.ShuoMingDoubleBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * author Afton
 * date 2020/2/28
 */
public class PlayShuoMingAdapter extends BaseQuickAdapter<ShuoMingDoubleBean.DescriptionBean, BaseViewHolder> {
    public PlayShuoMingAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable ShuoMingDoubleBean.DescriptionBean data) {
        String text = data.getText();
        baseViewHolder.setText(R.id.tv_shuoming_title, data.getTitle());
        TextView mTvShuoming = baseViewHolder.itemView.findViewById(R.id.tv_shuoming);
        CharSequence charSequence;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            charSequence = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            charSequence = Html.fromHtml(text);
        }

        mTvShuoming.setText(charSequence);
        /*设置超链接生效*/
        mTvShuoming.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
