package com.ski.box.adapter;

import com.chad.library.adapter.base.entity.JSectionEntity;

/**
 * author Afton
 * date 2020/2/29
 */
public class MySection extends JSectionEntity {
    private boolean isHeader;
    private Object object;

    public MySection(boolean isHeader, Object object) {
        this.isHeader = isHeader;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }
}
