package com.ski.box.view.view.dialog.zd;

import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ski.box.R;
import com.yb.core.base.BaseFragment;

import java.io.Serializable;
import java.util.List;

/**
 * Author:zx on 2019/9/2520:42
 */
public class ZController<A extends ZBaseAdapter> implements Parcelable, Serializable {

    private FragmentManager fragmentManager;
    private int layoutRes;
    private int height;
    private int width;
    private float dimAmount;
    private int gravity;
    private int dialogX;
    private int dialogY;
    private String tag;
    private int[] ids;
    private boolean isCancelableOutside;
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private A adapter;
    private ZBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
    private int orientation;
    private int dialogAnimationRes;
    private View dialogView;
    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnKeyListener onKeyListener;

    private List<BaseFragment> fragments;
    private boolean isInstance;


    //////////////////////////////////////////Parcelable持久化//////////////////////////////////////////////////////
    public ZController() {

    }

    protected ZController(Parcel in) {
        layoutRes = in.readInt();
        height = in.readInt();
        width = in.readInt();
        dimAmount = in.readFloat();
        gravity = in.readInt();
        dialogX = in.readInt();
        dialogY = in.readInt();
        tag = in.readString();
        ids = in.createIntArray();
        isCancelableOutside = in.readByte() != 0;
        isInstance = in.readByte() != 0;
        orientation = in.readInt();
    }

    public static final Creator<ZController> CREATOR = new Creator<ZController>() {
        @Override
        public ZController createFromParcel(Parcel in) {
            return new ZController(in);
        }

        @Override
        public ZController[] newArray(int size) {
            return new ZController[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(layoutRes);
        parcel.writeInt(height);
        parcel.writeInt(width);
        parcel.writeFloat(dimAmount);
        parcel.writeInt(gravity);
        parcel.writeInt(dialogX);
        parcel.writeInt(dialogY);
        parcel.writeString(tag);
        parcel.writeIntArray(ids);
        parcel.writeByte((byte) (isCancelableOutside ? 1 : 0));
        parcel.writeByte((byte) (isInstance ? 1 : 0));
        parcel.writeInt(orientation);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //get set
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public int getGravity() {
        return gravity;
    }
    public int getDialogX() {
        return dialogX;
    }

    public int getDialogY() {
        return dialogY;
    }

    public String getTag() {
        return tag;
    }

    public int[] getIds() {
        return ids;
    }

    public boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getDialogAnimationRes() {
        return dialogAnimationRes;
    }

    public View getDialogView() {
        return dialogView;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public DialogInterface.OnKeyListener getOnKeyListener() {
        return onKeyListener;
    }

    /*列表*/
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
    }

    public ZBaseAdapter.OnAdapterItemClickListener getAdapterItemClickListener() {
        return adapterItemClickListener;
    }

    public void setAdapterItemClickListener(ZBaseAdapter.OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    public List<BaseFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public boolean isInstance() {
        return isInstance;
    }

    public void setInstance(boolean instance) {
        isInstance = instance;
    }

    /***************************************************************************/

    public static class ZParams<A extends ZBaseAdapter> {
        public FragmentManager mFragmentManager;
        public int mLayoutRes;
        public int mHeight;
        public int mWidth;
        public float mDimAmount = 0.2f;
        public int mGravity = Gravity.CENTER;
        public int mDialogX;
        public int mDialogY;
        public String mTag = "ZDialog";
        public int[] ids;
        public boolean mIsCancelableOutside = true;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener mBindViewListerner;
        public int mDialogAnimationRes = 0;

        public A adapter;
        public ZBaseAdapter.OnAdapterItemClickListener mAdapterItemClickListener;
        public int listLayoutRes;//列表用的布局
        public int orientation = LinearLayoutManager.VERTICAL;
        public View mDialogView;//直接传入进来的View，不需要XML解析
        public DialogInterface.OnDismissListener mOnDismissListenre;
        public DialogInterface.OnKeyListener mKeyListener;
        public List<Fragment> fragments;
        public boolean mIsInstance;


        public void apply(ZController zController) {
            zController.fragmentManager = mFragmentManager;
            if (mLayoutRes > 0) {
                zController.layoutRes = mLayoutRes;
            }
            if (null != mDialogView) {
                zController.dialogView = mDialogView;
            }
            if (mWidth > 0) {
                zController.width = mWidth;
            }
            if (mHeight > 0) {
                zController.height = mHeight;
            }
            zController.dimAmount = mDimAmount;
            zController.gravity = mGravity;
            if (mDialogX > 0) {
                zController.dialogX = mDialogX;
            }
            if (mDialogY > 0) {
                zController.dialogY = mDialogY;
            }
            zController.tag = mTag;
            if (null != ids) {
                zController.ids = ids;
            }
            zController.isCancelableOutside = mIsCancelableOutside;
            zController.onViewClickListener = mOnViewClickListener;
            zController.onBindViewListener = mBindViewListerner;
            zController.onDismissListener = mOnDismissListenre;
            zController.dialogAnimationRes = mDialogAnimationRes;
            zController.onKeyListener = mKeyListener;
            zController.isInstance = mIsInstance;

            if (null != adapter) {
                zController.setAdapter(adapter);
                if (listLayoutRes <= 0) {
                    zController.setLayoutRes(R.layout.ski_td_dialog_recycler);
                } else {
                    zController.setLayoutRes(listLayoutRes);
                }
                zController.orientation = orientation;
            } else {
                if (zController.getLayoutRes() <= 0 && zController.getDialogView() == null) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的布局");
                }
            }
            if (null != mAdapterItemClickListener) {
                zController.setAdapterItemClickListener(mAdapterItemClickListener);
            }
            //如果宽高都没有设置,则默认给弹窗提供宽度为600
            if (zController.width <= 0 && zController.height <= 0) {
                zController.width = 600;
            }
            if (null != fragments) {
                zController.setFragments(fragments);
            } else {
                if (zController.getLayoutRes() <= 0 && zController.getDialogView() == null) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的布局");
                }
            }
        }
    }

}

