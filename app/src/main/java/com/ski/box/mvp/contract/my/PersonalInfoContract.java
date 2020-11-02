package com.ski.box.mvp.contract.my;


import com.ski.box.bean.user.MemberInfo;
import com.yb.core.base.BaseContract;

public interface PersonalInfoContract {
    interface View extends BaseContract.BaseView {
        void onMemberInfoResult(MemberInfo memberInfo);
        void onMemberInfoFailResult(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getMemberInfo();
    }
}
