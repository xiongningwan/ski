package com.yb.core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    //Fragment的View加载完毕的标记
    protected boolean isViewCreated;
    //Fragment对用户可见的标记
    protected boolean isUIVisible;
    protected View mFooter;
    protected CompositeDisposable mDisposable;
    private View root = null;
    private FragmentActivity activity;
    protected View rootView;
    protected boolean isFirstLoad = true; // 是否第一次加载

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initView(view);
        initData(savedInstanceState);
        processLogic();
//        lazyLoad();
    }

    /******************************lifecycle area*****************************************/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        int resId = getLayoutId();
        root = inflater.inflate(resId, container, false);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mDisposable != null) {
            mDisposable.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            loadData();
            isFirstLoad = false;
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 逻辑使用区
     */
    protected void processLogic() {
    }

    /*******************************init area*********************************/
    protected void addDisposable(Disposable d) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
//        if (isVisibleToUser) {
//            isUIVisible = true;
//            lazyLoad();
//        } else {
//            isUIVisible = false;
//        }
//    }
//
//    private void lazyLoad() {
//        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
//        if (isViewCreated && isUIVisible) {
//            loadData();
//            //数据加载完毕,恢复标记,防止重复加载
//            isViewCreated = false;
//            isUIVisible = false;
//        }
//    }


    protected abstract void loadData();


}
