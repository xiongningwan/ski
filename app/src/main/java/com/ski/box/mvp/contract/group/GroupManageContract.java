package com.ski.box.mvp.contract.group;


import com.ski.box.bean.group.GroupMemberData;
import com.ski.box.bean.group.InviteData;
import com.yb.core.base.BaseContract;

public interface GroupManageContract {
    interface View extends BaseContract.BaseView {
        void onSuccessResult(GroupMemberData o);
        void onFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTeamMemberList(String memberAccount, int pageSize, int pageNum);
    }
}
