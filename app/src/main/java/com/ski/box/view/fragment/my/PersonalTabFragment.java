package com.ski.box.view.fragment.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.BuildConfig;
import com.ski.box.R;
import com.ski.box.bean.PTabBean;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.ski.box.view.activity.MainActivity;
import com.ski.box.view.view.HallTabLayout;
import com.yb.core.utils.LanguageUtil;
import com.ski.box.view.activity.AgentWebViewActivity;
import com.ski.box.view.activity.ContainerActivity;
import com.ski.box.view.activity.group.GroupAddActivity;
import com.ski.box.view.activity.group.GroupInviteUrlActivity;
import com.ski.box.view.activity.group.GroupManageActivity;
import com.ski.box.view.activity.group.GroupRecordBetActivity;
import com.ski.box.view.activity.group.GroupRecordMoneyActivity;
import com.ski.box.view.activity.money.MoneyProgressActivity;
import com.ski.box.view.activity.money.WithdrawActivity;
import com.ski.box.view.activity.my.BankCardActivity;
import com.ski.box.view.activity.my.NoticeActivity;
import com.ski.box.view.activity.my.PersonalInfoActivity;
import com.ski.box.view.fragment.RechargeFragment;
import com.ski.box.view.fragment.record.RecordBetFragment;
import com.ski.box.view.fragment.record.RecordMoneyFragment;
import com.yb.core.base.BaseMVPFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PersonalTabFragment extends BaseMVPFragment<EmptyContract.Presenter> implements EmptyContract.View, View.OnClickListener {

    public static final String KEY_P_TAB_ID = "key_p_tab_id";
    private RecyclerView mRv;
    private TabListAdapter mTabListAdapter;
    private int  mId;

    public PersonalTabFragment() {
    }

    public static PersonalTabFragment newInstance(int id) {
        PersonalTabFragment fragment = new PersonalTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_P_TAB_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ski_fragment_personal_tab;
    }

    @Override
    protected void initView(View view) {
        RxBus.get().register(this);
        mRv =  view.findViewById(R.id.rv_tab);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mId = bundle.getInt(KEY_P_TAB_ID, 0);
        }

        mRv.setLayoutManager(new LinearLayoutManager(requireActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mTabListAdapter = new TabListAdapter();
        mRv.setAdapter(mTabListAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<PTabBean>  list = getData(mId);
        mTabListAdapter.setNewInstance(list);
        mTabListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                List<PTabBean>  list = (List<PTabBean>) adapter.getData();
                PTabBean bean = list.get(position);
                dispatch(bean);
            }

        });
    }



    //这个是一个懒加载
    @Override
    protected void loadData() {
    }

    @Override
    protected void processLogic() {
        super.processLogic();
    }


    @Override
    protected EmptyContract.Presenter bindPresenter() {
        return new EmptyPresenter(mContext);
    }


    @Override
    public void onClick(View view) {

    }


   public static class TabListAdapter extends BaseQuickAdapter<PTabBean, BaseViewHolder> {

       public TabListAdapter() {
           super(R.layout.ski_item_persnal_tab_rv_item);
       }

       @Override
       protected void convert(@NotNull BaseViewHolder holder, PTabBean bean) {
           if(45 == bean.getId()) {
               holder.setText(R.id.tv_name, LanguageUtil.getText(bean.getName()) + ": " + BuildConfig.VERSION_NAME);
           } else {
               holder.setText(R.id.tv_name, LanguageUtil.getText(bean.getName()));
           }

       }
   }

   private List<PTabBean> getData(int id){
       List<PTabBean> list = new ArrayList<>();
        switch (id){
            case 1:
                list.add(new PTabBean(11,R.mipmap.icon_personal_tab_my_acc, "基本信息"));
                list.add(new PTabBean(12,R.mipmap.icon_personal_tab_baobiao, "银行卡管理"));
                list.add(new PTabBean(13,R.mipmap.icon_personal_tab_tuandui, "充值"));
                list.add(new PTabBean(14,R.mipmap.icon_personal_tab_setting, "提现"));
                list.add(new PTabBean(15,R.mipmap.icon_personal_tab_setting, "资金进度查询"));
                break;
            case 2:
                list.add(new PTabBean(21,R.mipmap.icon_personal_tab_my_acc, "投注记录"));
                list.add(new PTabBean(22,R.mipmap.icon_personal_tab_baobiao, "账变记录"));
//                list.add(new PTabBean(23,R.mipmap.icon_personal_tab_tuandui, "钱包资金记录"));
                break;
            case 3:
                list.add(new PTabBean(31,R.mipmap.icon_personal_tab_my_acc, "开立下级账户"));
                list.add(new PTabBean(32,R.mipmap.icon_personal_tab_baobiao, "推广链接"));
//                list.add(new PTabBean(33,R.mipmap.icon_personal_tab_tuandui, "团队统计分析"));
                list.add(new PTabBean(34,R.mipmap.icon_personal_tab_setting, "团队会员管理"));
                list.add(new PTabBean(35,R.mipmap.icon_personal_tab_setting, "团队投注记录"));
                list.add(new PTabBean(36,R.mipmap.icon_personal_tab_setting, "团队账变记录"));
//                list.add(new PTabBean(37,R.mipmap.icon_personal_tab_setting, "团队钱包金流"));
//                list.add(new PTabBean(38,R.mipmap.icon_personal_tab_setting, "团队盈亏报表"));
                break;
            case 4:
                list.add(new PTabBean(41,R.mipmap.icon_personal_tab_my_acc, "活动优惠"));
                list.add(new PTabBean(42,R.mipmap.icon_personal_tab_baobiao, "客服中心"));
                list.add(new PTabBean(43,R.mipmap.icon_personal_tab_tuandui, "公告中心"));
//                list.add(new PTabBean(44,R.mipmap.icon_personal_tab_setting, "帮助中心"));
                list.add(new PTabBean(45,R.mipmap.icon_personal_tab_setting, "当前版本号"));
                break;
        }
        return list;
   }

    private void dispatch(PTabBean bean) {
        switch (bean.getId()){
            case 11: // 基本信息
                startActivity(new Intent(requireActivity(), PersonalInfoActivity.class));
                break;
            case 12: // 银行卡管理
                startActivity(new Intent(requireActivity(), BankCardActivity.class));
                break;
            case 21: {// 投注记录
                Intent intent =  new Intent(requireActivity(), ContainerActivity.class);
                intent.putExtra(ContainerActivity.KEY_CLASS, RecordBetFragment.class.getSimpleName());
                startActivity(intent);
                break;}
            case 22: {// 账变记录
                Intent intent =  new Intent(requireActivity(), ContainerActivity.class);
                intent.putExtra(ContainerActivity.KEY_CLASS, RecordMoneyFragment.class.getSimpleName());
                startActivity(intent);
                break;}
            case 31: // 开立下级账户
                startActivity(new Intent(requireActivity(), GroupAddActivity.class));
                break;
            case 32: // 推广链接
                startActivity(new Intent(requireActivity(), GroupInviteUrlActivity.class));
                break;
            case 34: // 团队会员管理
                startActivity(new Intent(requireActivity(), GroupManageActivity.class));
                break;
            case 35: // 团队投注记录
                startActivity(new Intent(requireActivity(), GroupRecordBetActivity.class));
                break;
            case 36: // 团队账变记录
                startActivity(new Intent(requireActivity(), GroupRecordMoneyActivity.class));
                break;
            case 13: {// 充值
                Intent intent =  new Intent(requireActivity(), ContainerActivity.class);
                intent.putExtra(ContainerActivity.KEY_CLASS, RechargeFragment.class.getSimpleName());
                startActivity(intent);
//                startActivity(new Intent(requireActivity(), RechargeActivity.class));
                break;}
            case 14: // 提现
                startActivity(new Intent(requireActivity(), WithdrawActivity.class));
                break;
            case 15: // 资金进度查询
                startActivity(new Intent(requireActivity(), MoneyProgressActivity.class));
                break;
            case 41: {// 活动优惠
                ((MainActivity) requireActivity()).gotoPage(HallTabLayout.TAB_INDEX_RECORD);
                break;}
            case 42: // 客服中心
                AgentWebViewActivity.startAgentWebView(requireActivity(), LanguageUtil.getText("客服中心"), "https://www.google.com");
                break;
            case 43: // 公告中心
                startActivity(new Intent(requireActivity(), NoticeActivity.class));
                break;
        }
    }
}
