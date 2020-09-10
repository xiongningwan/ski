package com.ski.box.view.view.keyboard;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:zx on 2019/10/220:03
 */
public class KeyboardItemDecoration extends RecyclerView.ItemDecoration  {
    private int space;
    public KeyboardItemDecoration(int space) {
        this.space = space;
    }
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int i = position % 3;
        if (position == 0 && i == 0) {
            outRect.top = 1;
            outRect.bottom = 1;
        } else {
            if (i == 0) {
                outRect.set(space/2,space/2,space*2,space/2);
            } else if (i == 1) {
                outRect.set(space*2,space/2,space/2,space/2);
            } else {
                outRect.set(space/2,space/2,space/2,space/2);
            }
            if (position>0&&position<4) {
                outRect.top = space;
            } else if (position > 9) {
                outRect.bottom = space;
            }

        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
