package com.ski.box.adapter;

import android.os.Build;
import android.text.Html;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.NoticeData;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tom on 2020/10/26.
 */
public class NoticeAdapter extends BaseQuickAdapter<NoticeData.ListBean, BaseViewHolder> {
    public NoticeAdapter() {
        super(R.layout.ski_item_notice);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, NoticeData.ListBean bean) {
        // time.setText(bean.getCreateAt());
        TextView tvTitle = holder.getView(R.id.tv_title);
//        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTime = holder.getView(R.id.tv_time);
        tvTitle.setText(bean.getNoticeTitle());
        tvTime.setText(bean.getUpdateAt());
//        String content = bean.getNoticeContent();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            content = Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY).toString();
//        } else {
//            content = Html.fromHtml(content).toString();
//        }
//        tvContent.setText(content);
    }


}
