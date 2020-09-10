package com.ski.box.view.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @CreateDate: 2019/12/19 12:22
 * @ClassName: GridItemDecoration
 * @Author: Jofear
 * @Description: java类作用描述
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    private boolean mIncludeEdge = true;

    public GridItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, RecyclerView parent, @NotNull RecyclerView.State state) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        //列数
        int spanCount = Objects.requireNonNull(gridLayoutManager).getSpanCount();
        int position = parent.getChildLayoutPosition(view);
        int column = (position) % spanCount;

        if (mIncludeEdge) {
            outRect.left = mSpace - column * mSpace / spanCount;
            outRect.right = (column + 1) * mSpace / spanCount;
            if (position < spanCount) {
                outRect.top = mSpace;
            }
            outRect.bottom = mSpace;
        } else {
            outRect.left = column * mSpace / spanCount;
            outRect.right = mSpace - (column + 1) * mSpace / spanCount;
            if (position >= spanCount) {
                outRect.top = mSpace;
            }
        }
    }



}
