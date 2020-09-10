package com.ski.box.view.view.keyboard;

import android.graphics.drawable.Drawable;

public class KeyBoardBean {
    public static int NUMBER = 0;
    public static int DELETE = 1;
    public static int DISMISS = 2;
    /*分隔符*/
    public static int DELIMITER = 3;
    String num = "";
    int type = -1;
    int spanSize = 0;
    private int icon;
    private int iconWidth;
    private int iconHeight;
    float height;
    private Drawable mDrawable;
    private float textSize;
    private int backResource;

    public int getBackResource() {
        return backResource;
    }

    public void setBackResource(int backResource) {
        this.backResource = backResource;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public String getNum() {
        return num;
    }


    public void setNum(String num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setIcon(int arrow_down) {
        this.icon = arrow_down;
    }

    public int getIcon() {
        return icon;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBackDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }

    public Drawable getBackDrawable() {
        return mDrawable;
    }
}
