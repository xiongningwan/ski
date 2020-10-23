package com.ski.box.mvp.contract;


import com.ski.box.bean.user.MemberInfo;
import com.ski.box.bean.user.User;
import com.yb.core.base.BaseContract;

public interface PersonalContract {
    interface View extends BaseContract.BaseView {
        void onMemberInfoResult(MemberInfo memberInfo);
        void onMemberInfoFailResult(String s);

        void onLogoutResult(Object o);
        void onLogoutFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getMemberInfo();
        void logout();
    }
}
