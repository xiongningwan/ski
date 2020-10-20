package com.ski.box.view.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * Author:zx on 2019/10/220:03
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int leftSpace;
    private int rightSpace;
    private int topSpace;
    private int bottomSpace;


    public SpaceItemDecoration(int space) {
        this.leftSpace = space;
        this.rightSpace = space;
        this.topSpace = space;
        this.bottomSpace = space;
    }

    public SpaceItemDecoration(int leftSpace, int topSpace, int rightSpace, int bottomSpace) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NotNull View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {

        outRect.left = leftSpace;
        outRect.top = topSpace;
        outRect.bottom = bottomSpace;
        outRect.right = rightSpace;
        //第一个的格子不设置头部
//        if (parent.getChildLayoutPosition(view) == 0) {
//            outRect.top = 0;
//        }
    }

}
