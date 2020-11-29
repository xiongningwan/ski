package com.ski.box.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.user.BankCard;
import com.ski.box.utils.ActivityUtil;
import com.ski.box.view.view.CusButton;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.view.CusTextView;

import org.jetbrains.annotations.NotNull;

/**
 * Created by tom on 2020/10/26.
 */
public class BankCardAdapter extends BaseMultiItemQuickAdapter<BankCard, BaseViewHolder> {
    public BankCardAdapter() {
        addItemType(0, R.layout.ski_item_bank_card_list);
        addItemType(1, R.layout.ski_layout_bank_card_list_footer);
        addChildClickViewIds(R.id.btn_sure);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, BankCard bean) {
        // time.setText(bean.getCreateAt());
        switch (holder.getItemViewType()) {
            case 0:
                TextView name = holder.getView(R.id.tv_bank_name);
                TextView no = holder.getView(R.id.tv_bank_no);
                TextView owner = holder.getView(R.id.tv_value_bank_owner);
                TextView time = holder.getView(R.id.tv_value_bank_time);
                name.setText(bean.getBankName());
                no.setText(bean.getCardNo());
                owner.setText(bean.getCardName());
                break;
            case 1:
                CusTextView mTvTip1 = holder.getView(R.id.tv_tip_1);
                setRedTip(getContext(), mTvTip1);
                break;
        }
    }

    private void setRedTip(Context context, CusTextView mTvTip2) {
        String tipTop = "<p>1.银行卡添加成功后，平台任何区域都不会出现您完整的银行卡账号，开户名等信息。<br /><br />\n" +
                "2.每个平台账号最多可以添加<span style=\"color:#e74c3c\">5张银行卡</span>。<br /><br />\n" +
                "3.每个账号只能添加<span style=\"color:#e74c3c\">同一个持卡人姓名</span>的银行卡。<br /><br />\n" +
                "4.添加银行卡前，必须先设置<span style=\"color:#e74c3c\">资金密码</span>。<br /><br />\n" +
                "5.添加银行卡后，方可提现。</p>\n";
        if(LanguageUtil.VI.equals(LanguageUtil.getLanguage())) {
            tipTop = "<p>1.Sau khi th&ecirc;m thẻ th&agrave;nh c&ocirc;ng , nh&agrave; c&aacute;i đảm bảo mọi th&ocirc;ng tin của hội vi&ecirc;n sẽ được ho&agrave;n to&agrave;n bảo mật.&nbsp;<br /><br />\n" +
                    "2.Q&uacute;y hội vi&ecirc;n chỉ được th&ecirc;m nhiều nhất <span style=\"color:#e74c3c\">5</span> thẻ ng&acirc;n h&agrave;ng .<br /><br />\n" +
                    "3.Mỗi thẻ th&ecirc;m v&agrave;o , <span style=\"color:#e74c3c\">cần phải tr&ugrave;ng t&ecirc;n với c&aacute;c thẻ c&ograve;n lại </span>.<br /><br />\n" +
                    "4.Trước khi th&ecirc;m thẻ ng&acirc;n h&agrave;ng , <span style=\"color:#e74c3c\">cần phải c&agrave;i đặt mật khẩu Qũy&nbsp;</span><br /><br />\n" +
                    "5. Sau khi th&ecirc;m thẻ ng&acirc;n h&agrave;ng , hội vi&ecirc;n c&oacute; thể tiến h&agrave;nh r&uacute;t tiền .&nbsp;</p>";
        }
        Spanned sdpTop ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sdpTop = Html.fromHtml(tipTop,Html.FROM_HTML_MODE_LEGACY);
        } else {
            sdpTop = Html.fromHtml(tipTop);
        }

        mTvTip2.setText(sdpTop);
    }

}
