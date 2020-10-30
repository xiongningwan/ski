package com.ski.box.mvp.contract.group;


import com.ski.box.bean.group.InviteData;
import com.ski.box.bean.group.InviteUrl;
import com.ski.box.bean.group.RebateKV;
import com.yb.core.base.BaseContract;

import java.util.List;

public interface GroupInviteUrlContract {
    interface View extends BaseContract.BaseView {
        void onRebateScopeResult(List<RebateKV> list);
        void onRebateScopeFailResult(String s);

        void onSuccessResult(InviteData o);
        void onFailResult(String s);

        void onDeleteResult(InviteData o);
        void onDeleteResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getRebateScope();
        void getInviteUrlList(int pageSize, int pageNum);
        void inviteDelete(String inviteCode);
    }
}
