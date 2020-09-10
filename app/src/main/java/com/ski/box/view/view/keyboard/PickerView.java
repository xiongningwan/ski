package com.ski.box.view.view.keyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ski.box.R;
import com.ski.box.utils.KeyBoardUtils;


/**
 * Author        Hule  ski_hu.le@cesgroup.com.cn
 * Date          2016/12/22 15:52
 * Description:  TODO: 自定义商城点击+或-实现数字框的数字增加或减少
 */

public class PickerView extends LinearLayout implements View.OnClickListener, TextWatcher {


    //默认字体的大小
    private int textDefaultSize = 14;

    public ImageView subText;
    public ImageView addText;
    // 中间输入框的‘输入值
    private EditText mNumEditText;
    private TextView mEndText;
    private boolean bringUpKeyboard = true;

    private int focusBack= R.mipmap.pickeview_select_new;
    private int unFocusBack=R.mipmap.pickview_unselect;
    public ConstraintLayout mRoot;
    public LinearLayout llChange;
    private int maxNum=1000000000;
    private long minNum;
    private PickerView pickerView;
    private float mheight;
    private float mwidth;
    private String endText;
    private int textSize;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mNumEditText.selectAll();
        }
    };

    public PickerView(Context context) {
        super(context);
        pickerView = this;
    }

    public PickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initNumberPickerView(context, attrs);
        pickerView = this;
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initNumberPickerView(final Context context, AttributeSet attrs) {
        //加载定义好的布局文件
        LayoutInflater.from(context).inflate(R.layout.ski_number_button_new, this);
        mRoot = findViewById(R.id.root);
        subText = findViewById(R.id.button_sub);
        addText = findViewById(R.id.button_add);
        mNumEditText = findViewById(R.id.middle_count);
        mEndText = findViewById(R.id.count_end_text);
        llChange = findViewById(R.id.ll_change);

        //添加监听事件
        addText.setOnClickListener(this);
        subText.setOnClickListener(this);
        mNumEditText.addTextChangedListener(this);

        mNumEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)}); //最大输入长

        //获取自定义属性的相关内容
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.pickerView);

        if (typedArray != null) {
             mheight = typedArray.getDimension(R.styleable.pickerView_num_height, 0);
             mwidth = typedArray.getDimension(R.styleable.pickerView_num_width, 0);
             endText = typedArray.getString(R.styleable.pickerView_num_endText);
             textSize = typedArray.getDimensionPixelSize(R.styleable.pickerView_num_textSize, -1);

        }

        typedArray.recycle();

        mEndText.setText(endText);

        if (mheight != 0 || mwidth != 0) {
            LayoutParams layoutParams = (LayoutParams) mRoot.getLayoutParams();
            layoutParams.width = (int) mwidth;
            layoutParams.height = (int) mheight;
            mRoot.setLayoutParams(layoutParams);

        }


        //初始化字体,注意默认的是px单位，要转换
        if (textSize > 0) {
            mNumEditText.setTextSize(px2sp(context, textSize));
        } else {
            mNumEditText.setTextSize(textDefaultSize);
        }


        /*根据失去或者得到焦点自己变颜色*/
        mNumEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                     mRoot.setBackgroundResource(focusBack);
                     addText.setImageResource(R.mipmap.ski_tv_back_zhuihao_add_peroid);
                     subText.setImageResource(R.mipmap.ski_tv_back_zhuihao_less_peroid);

                    /*在此会掉里 显示或者隐藏自己的键盘*/
                    if (onPopUpCustomKeyboard != null) {
                        onPopUpCustomKeyboard.popUpKeyBoard(pickerView, bringUpKeyboard);
                    }
                    if (onselectlistener != null) {
                        onselectlistener.onSelect(true);
                    }
                    mEndText.setVisibility(GONE);
                } else {
                    mRoot.setBackgroundResource(unFocusBack);
                    addText.setImageResource(R.mipmap.ski_nor_jiahao);
                    subText.setImageResource(R.mipmap.ski_nor_jianhao);
                    if (onselectlistener != null) {
                        onselectlistener.onSelect(false);
                    }
                    mEndText.setVisibility(VISIBLE);
                }

            }
        });
        mNumEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mNumEditText.setFocusable(true);
                    mNumEditText.setFocusableInTouchMode(true);
                    mNumEditText.requestFocus();
                    if (CUSTOM_KEYBOARD) {
                        /*隐藏系统键盘*/
                        KeyBoardUtils.dismissSystemkeyBoard((Activity) getContext(), mNumEditText);
                        //在此会掉里 显示或者隐藏自己的键盘
                        if (onPopUpCustomKeyboard != null) {
                            /*当获取焦点的 时候  bringUp=true 调起 bringUp=false 不调起*/
                            bringUpKeyboard = true;
                            onPopUpCustomKeyboard.popUpKeyBoard(pickerView, bringUpKeyboard);
                        }

                    } else {
                        /*调用系统键盘*/
                        mNumEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        Activity activity = (Activity) getContext();
                        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        KeyBoardUtils.openKeybord(mNumEditText, getContext());

                    }
                    handler.sendEmptyMessageDelayed(1, 300);

                }
                return false;
            }
        });

        llChange.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mNumEditText.setFocusable(true);
                mNumEditText.setFocusableInTouchMode(true);
                mNumEditText.requestFocus();
                handler.sendEmptyMessageDelayed(1, 300);
                if (CUSTOM_KEYBOARD) {
//                    隐藏系统键盘
                    KeyBoardUtils.dismissSystemkeyBoard((Activity) getContext(), mNumEditText);
                } else {
//                    调用系统键盘
                    mNumEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    Activity activity = (Activity) getContext();
                    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    KeyBoardUtils.openKeybord(mNumEditText, getContext());

                }
//                在此会掉里 显示或者隐藏自己的键盘
                if (onPopUpCustomKeyboard != null) {
                    /*当获取焦点的 时候  bringUp=true 调起 bringUp=false 不调起*/
                    bringUpKeyboard = true;
                    onPopUpCustomKeyboard.popUpKeyBoard(pickerView, bringUpKeyboard);
                }

            }
        });

   /*     mNumEditText.setFocusable(false);
        mNumEditText.setFocusableInTouchMode(false);*/


    }


    /**
     * @return 获取输入框的最终数字值
     */
    public int getCurrentNumText() {
        String textNum = mNumEditText.getText().toString().trim();
        return Integer.parseInt(textNum);
    }


    /**
     * @return NumPickerView
     */
    public PickerView setCurrentNum(long i) {
            if (i < minNum) {
                i = minNum;
            }
            if (i > maxNum) {
                i = maxNum;
            }

        mNumEditText.setText(i + "");



        return this;
    }

    public PickerView setMaxNum(int i) {
        this.maxNum = i;
        return this;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public long getMinNum() {
        return minNum;
    }

    public PickerView setMinNum(int i) {
        this.minNum = i;
        return this;
    }

    @Override
    public void onClick(View view) {
        /*不调期自己键盘*/
        bringUpKeyboard = false;
        int widgetId = view.getId();
        int numText = getCurrentNumText();
        mNumEditText.setFocusable(true);
        mNumEditText.setFocusableInTouchMode(true);
        mNumEditText.requestFocus();
//        恢复到默认调起状态
        bringUpKeyboard = true;

        if (widgetId == R.id.button_sub) {
            setCurrentNum(numText - 1);
        } else if (widgetId == R.id.button_add) {
            setCurrentNum(numText +1);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String s = editable.toString();
        mNumEditText.setSelection(s.length());
        if (onTextNumChangeListener != null) {
            onTextNumChangeListener.afterTextChagne(s);
        }

    }

    public void setSelect(boolean b) {
        if (b) {
            /*不掉期键盘*/
            bringUpKeyboard = false;
            mNumEditText.setFocusable(true);
            mNumEditText.setFocusableInTouchMode(true);
            mNumEditText.requestFocus();
        } else {
            mNumEditText.setFocusable(false);
            mNumEditText.setFocusableInTouchMode(false);
        }
    }


    private boolean CUSTOM_KEYBOARD = true;
    public void setCustomKeyBoards(boolean customKeyBoard, OnPopUpCustomKeyboard popUpCustomKeyboard) {
        CUSTOM_KEYBOARD = customKeyBoard;
        this.onPopUpCustomKeyboard = popUpCustomKeyboard;

    }

    public EditText getmNumEditText() {
        return mNumEditText;
    }


    /*监听数据改变*/
    public void addOnTextChangeListener(OnTextNumChangeListener listener) {
        this.onTextNumChangeListener = listener;
    }

    private OnTextNumChangeListener onTextNumChangeListener;


    public interface OnTextNumChangeListener {
        void afterTextChagne(String text);

    }

    private OnSelectListener onselectlistener;
    public interface  OnSelectListener{
        void onSelect(boolean select);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        onselectlistener = listener;
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    private OnPopUpCustomKeyboard onPopUpCustomKeyboard;

    public interface OnPopUpCustomKeyboard {
        /*当获取焦点的 时候  bringUp=true 调起键盘 bringUp=false 不调起键盘*/
        void popUpKeyBoard(PickerView pickerView, boolean bringUp);
    }


    public void focusBack(int back) {
        focusBack = back;
    }

    public void setUnFocusBack(int back) {
        unFocusBack = back;
    }



}