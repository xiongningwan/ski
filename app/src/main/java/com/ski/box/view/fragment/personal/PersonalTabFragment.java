package com.ski.box.view.fragment.personal;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.PTabBean;
import com.ski.box.bean.lottery.LotteryBean;
import com.ski.box.mvp.contract.EmptyContract;
import com.ski.box.mvp.presenter.EmptyPresenter;
import com.yb.core.base.BaseMVPFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        mRv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mTabListAdapter = new TabListAdapter();
        mRv.setAdapter(mTabListAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<PTabBean>  list = getData(mId);
        mTabListAdapter.setNewInstance(list);
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
            holder.setText(R.id.tv_name, bean.getName());
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
                list.add(new PTabBean(22,R.mipmap.icon_personal_tab_baobiao, "游戏资金记录"));
                list.add(new PTabBean(23,R.mipmap.icon_personal_tab_tuandui, "钱包资金记录"));
                break;
            case 3:
                list.add(new PTabBean(31,R.mipmap.icon_personal_tab_my_acc, "开立下级账户"));
                list.add(new PTabBean(32,R.mipmap.icon_personal_tab_baobiao, "推广链接"));
                list.add(new PTabBean(33,R.mipmap.icon_personal_tab_tuandui, "团队统计分析"));
                list.add(new PTabBean(34,R.mipmap.icon_personal_tab_setting, "团队会员管理"));
                list.add(new PTabBean(35,R.mipmap.icon_personal_tab_setting, "团队投注记录"));
                list.add(new PTabBean(36,R.mipmap.icon_personal_tab_setting, "团队游戏金流"));
                list.add(new PTabBean(37,R.mipmap.icon_personal_tab_setting, "团队钱包金流"));
                list.add(new PTabBean(38,R.mipmap.icon_personal_tab_setting, "团队盈亏报表"));
                break;
            case 4:
                list.add(new PTabBean(41,R.mipmap.icon_personal_tab_my_acc, "活动优惠"));
                list.add(new PTabBean(42,R.mipmap.icon_personal_tab_baobiao, "客服中心"));
                list.add(new PTabBean(43,R.mipmap.icon_personal_tab_tuandui, "公告中心"));
                list.add(new PTabBean(44,R.mipmap.icon_personal_tab_setting, "帮助中心"));
                list.add(new PTabBean(45,R.mipmap.icon_personal_tab_setting, "当前版本号:v1.6.2"));
                break;
        }
        return list;
   }
}