package com.ski.box.adapter.top;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.LotteryNumBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.lottery.LotteryUtil;
import com.ski.box.utils.MyCustomTarage;
import com.ski.box.utils.SystemUtil;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.ski.box.utils.lottery.shape.K3Util;
import com.ski.box.utils.lottery.shape.PK10Util;
import com.ski.box.utils.lottery.shape.SSCUtil;
import com.ski.box.utils.lottery.shape._11x5Util;
import com.yb.core.utils.AppUtil;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.LogUtils;
import com.yb.core.utils.ScreenUtils;

import org.eclipse.paho.client.mqttv3.util.Strings;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Created by tom on 2020/8/12.
 */
public class TopResultAdapter extends BaseMultiItemQuickAdapter<LotteryNumBean, BaseViewHolder> {
    private final int mViewHeight;
    private final Context mContext;
    private String systemModel;
    private Random mRandom;
    private Typeface mTf_DinABold;
    private int mode;

    public TopResultAdapter(Context context) {
        addItemType(LotteryConstant.SER_ID_PK10, R.layout.ski_item_top_result_type_pk10);
        addItemType(LotteryConstant.SER_ID_SSC, R.layout.ski_item_top_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_LHC, R.layout.ski_item_top_result_type_lhc);
        addItemType(LotteryConstant.SER_ID_11X5, R.layout.ski_item_top_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_K3, R.layout.ski_item_top_result_type_k3);
        addItemType(LotteryConstant.SER_ID_3D, R.layout.ski_item_top_result_type_3d);
        addItemType(LotteryConstant.SER_ID_PL35, R.layout.ski_item_top_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_KL8, R.layout.ski_item_top_result_type_kl8);
        addItemType(LotteryConstant.SER_ID_F1_JJS, R.layout.ski_item_top_result_type_pk10);
        mContext = context;
        systemModel = SystemUtil.getSystemModel();
        mViewHeight = ScreenUtils.dip2px(60);
        mRandom = new Random();
        //从asset 读取字体
        AssetManager mgr = context.getAssets();
        //根据路径得到Typeface
        mTf_DinABold = Typeface.createFromAsset(mgr, "fonts/DIN_Alternate_Bold.ttf");
    }

    public void setMode(int i) {
        mode = i;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, LotteryNumBean bean) {
        String code = bean.getCode();
        String[] arr_code = new String[0];
        if (!Strings.isEmpty(code) && code.contains(" ")) {
            arr_code = code.split(" ");
        }
        int type = holder.getItemViewType();
        switch (type) {
            case LotteryConstant.SER_ID_PK10:
            case LotteryConstant.SER_ID_F1_JJS:
                if (10 == arr_code.length) {
                    TextView tvNum1 = holder.getView(R.id.tv_num_1);
                    TextView tvNum2 = holder.getView(R.id.tv_num_2);
                    TextView tvNum3 = holder.getView(R.id.tv_num_3);
                    TextView tvNum4 = holder.getView(R.id.tv_num_4);
                    TextView tvNum5 = holder.getView(R.id.tv_num_5);
                    TextView tvNum6 = holder.getView(R.id.tv_num_6);
                    TextView tvNum7 = holder.getView(R.id.tv_num_7);
                    TextView tvNum8 = holder.getView(R.id.tv_num_8);
                    TextView tvNum9 = holder.getView(R.id.tv_num_9);
                    TextView tvNum10 = holder.getView(R.id.tv_num_10);

                    TextView tvXT1 = holder.getView(R.id.tv_xt_1);
                    TextView tvXT2 = holder.getView(R.id.tv_xt_2);
                    TextView tvXT3 = holder.getView(R.id.tv_xt_3);
                    TextView tvXT4 = holder.getView(R.id.tv_xt_4);
                    TextView tvXT5 = holder.getView(R.id.tv_xt_5);
                    TextView tvXT6 = holder.getView(R.id.tv_xt_6);
                    TextView tvXT7 = holder.getView(R.id.tv_xt_7);
                    TextView tvXT8 = holder.getView(R.id.tv_xt_8);

                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7, tvNum8, tvNum9, tvNum10};
                    TextView[] arrXt = {tvXT1, tvXT2, tvXT3, tvXT4, tvXT5, tvXT6, tvXT7, tvXT8};

                    for(TextView tv : arrXt) {
                        tv.setText("");
                    }
                    for (int i = 0; i < arr.length; i++) {
                        if (mode != 2) {
                            startAnimal(type, arr[i], i, arr_code[i], 120, 30, 10, arr_code, arrXt);
                        } else {
                            Integer bg = ConfigurationUiUtils.pk10bgMap.get(arr_code[i]);
                            arr[i].setBackgroundResource(bg);
                            arr[i].setText(arr_code[i]);
                        }
                    }
                    if (mode == 2) {
                        setXT_pk10(arr_code, arrXt);
                    }
                }
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_11X5:
            case LotteryConstant.SER_ID_PL35:
                if (5 == arr_code.length) {
                    TextView tvNum1 = holder.getView(R.id.tv_num_1);
                    TextView tvNum2 = holder.getView(R.id.tv_num_2);
                    TextView tvNum3 = holder.getView(R.id.tv_num_3);
                    TextView tvNum4 = holder.getView(R.id.tv_num_4);
                    TextView tvNum5 = holder.getView(R.id.tv_num_5);

                    TextView tvXT1 = holder.getView(R.id.tv_xt_1);
                    TextView tvXT2 = holder.getView(R.id.tv_xt_2);
                    TextView tvXT3 = holder.getView(R.id.tv_xt_3);
                    TextView tvXT4 = holder.getView(R.id.tv_xt_4);
//                    TextView tvXT5 = holder.getView(R.id.tv_xt_5);

                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5};
                    TextView[] arrXt = {tvXT1, tvXT2, tvXT3, tvXT4};

                    for(TextView tv : arrXt) {
                        tv.setText("");
                    }
                    for (int i = 0; i < arr.length; i++) {
                        if (mTf_DinABold != arr[i].getTypeface()) {
                            arr[i].setTypeface(mTf_DinABold);
                        }
                        if (mode != 2) {
//                            int r = mRandom.nextInt(arr_code.length);
                            startAnimal(type, arr[i], i, arr_code[i], 120, 15, 10, arr_code, arrXt);
                        } else {
                            arr[i].setText(arr_code[i]);
                        }
                    }
                    if (mode == 2) {
                        setXT_SSC_11x5(type, arr_code, arrXt);
                    }
                }
                break;
            case LotteryConstant.SER_ID_3D:
                if (3 == arr_code.length) {
                    TextView tvNum1 = holder.getView(R.id.tv_num_1);
                    TextView tvNum2 = holder.getView(R.id.tv_num_2);
                    TextView tvNum3 = holder.getView(R.id.tv_num_3);
                    TextView[] arr = {tvNum1, tvNum2, tvNum3};
                    for (int i = 0; i < arr.length; i++) {
                        if (mTf_DinABold != arr[i].getTypeface()) {
                            arr[i].setTypeface(mTf_DinABold);
                        }

                        if (mode != 2) {
                            int r = mRandom.nextInt(arr_code.length);
                            startAnimal(type, arr[i], i, arr_code[i], 120, 15, 10, arr_code, arr);
                        } else {
                            arr[i].setText(arr_code[i]);
                        }
                    }
                }
                break;
            case LotteryConstant.SER_ID_LHC:
                if (7 == arr_code.length) {
                    FrameLayout fl1 = holder.getView(R.id.fl_num_1);
                    FrameLayout fl2 = holder.getView(R.id.fl_num_2);
                    FrameLayout fl3 = holder.getView(R.id.fl_num_3);
                    FrameLayout fl4 = holder.getView(R.id.fl_num_4);
                    FrameLayout fl5 = holder.getView(R.id.fl_num_5);
                    FrameLayout fl6 = holder.getView(R.id.fl_num_6);
                    FrameLayout fl7 = holder.getView(R.id.fl_num_7);
                    TextView tvNum1 = holder.getView(R.id.tv_num_1);
                    TextView tvNum2 = holder.getView(R.id.tv_num_2);
                    TextView tvNum3 = holder.getView(R.id.tv_num_3);
                    TextView tvNum4 = holder.getView(R.id.tv_num_4);
                    TextView tvNum5 = holder.getView(R.id.tv_num_5);
                    TextView tvNum6 = holder.getView(R.id.tv_num_6);
                    TextView tvNum7 = holder.getView(R.id.tv_num_7);
                    TextView tvSx1 = holder.getView(R.id.tv_sx_1);
                    TextView tvSx2 = holder.getView(R.id.tv_sx_2);
                    TextView tvSx3 = holder.getView(R.id.tv_sx_3);
                    TextView tvSx4 = holder.getView(R.id.tv_sx_4);
                    TextView tvSx5 = holder.getView(R.id.tv_sx_5);
                    TextView tvSx6 = holder.getView(R.id.tv_sx_6);
                    TextView tvSx7 = holder.getView(R.id.tv_sx_7);

                    View[] arrFl = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};
                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7};
                    TextView[] arrSx = {tvSx1, tvSx2, tvSx3, tvSx4, tvSx5, tvSx6, tvSx7};
                    for (int i = 0; i < arr.length; i++) {
                        try {
                            if (mTf_DinABold != arr[i].getTypeface()) {
                                arr[i].setTypeface(mTf_DinABold);
                            }
                            if (2 == mode) {
                                int numInt = Integer.parseInt(arr_code[i]);
                                Integer bg = ConfigurationUiUtils.getLHCBg(numInt);
                                arr[i].setBackgroundResource(bg);
                                arr[i].setText(arr_code[i]);
                                arrSx[i].setVisibility(View.VISIBLE);

                                String sx = LotteryUtil.getLHCSX(numInt);
                                arrSx[i].setVisibility(View.VISIBLE);
                                arrSx[i].setText(LanguageUtil.getText(sx));
                            } else {
                                startAnimal_lhc(arrFl[i], i + 1, arr_code[i], arrSx[i], arr[i]);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case LotteryConstant.SER_ID_K3:
                if (3 == arr_code.length) {
                    ImageView ivNum1 = holder.getView(R.id.iv_num_1);
                    ImageView ivNum2 = holder.getView(R.id.iv_num_2);
                    ImageView ivNum3 = holder.getView(R.id.iv_num_3);

                    TextView tvXT1 = holder.getView(R.id.tv_xt_1);
                    TextView tvXT2 = holder.getView(R.id.tv_xt_2);
                    TextView tvXT3 = holder.getView(R.id.tv_xt_3);

                    ImageView[] arr = {ivNum1, ivNum2, ivNum3};
                    TextView[] arrXt = {tvXT1, tvXT2, tvXT3};
                    for(TextView tv : arrXt) {
                        tv.setText("");
                    }
                    for (int i = 0; i < arr.length; i++) {
                        startIVAni(arr[i], arr_code[i], arr_code, arrXt);
                    }
                    if (mode == 2) {
                        setXT_k3(arr_code, arrXt);
                    }
                }

                break;
            case LotteryConstant.SER_ID_KL8:
                if (20 == arr_code.length) {
                    TextView tvNum1 = holder.getView(R.id.tv_num_1);
                    TextView tvNum2 = holder.getView(R.id.tv_num_2);
                    TextView tvNum3 = holder.getView(R.id.tv_num_3);
                    TextView tvNum4 = holder.getView(R.id.tv_num_4);
                    TextView tvNum5 = holder.getView(R.id.tv_num_5);
                    TextView tvNum6 = holder.getView(R.id.tv_num_6);
                    TextView tvNum7 = holder.getView(R.id.tv_num_7);
                    TextView tvNum8 = holder.getView(R.id.tv_num_8);
                    TextView tvNum9 = holder.getView(R.id.tv_num_9);
                    TextView tvNum10 = holder.getView(R.id.tv_num_10);
                    TextView tvNum11 = holder.getView(R.id.tv_num_11);
                    TextView tvNum12 = holder.getView(R.id.tv_num_12);
                    TextView tvNum13 = holder.getView(R.id.tv_num_13);
                    TextView tvNum14 = holder.getView(R.id.tv_num_14);
                    TextView tvNum15 = holder.getView(R.id.tv_num_15);
                    TextView tvNum16 = holder.getView(R.id.tv_num_16);
                    TextView tvNum17 = holder.getView(R.id.tv_num_17);
                    TextView tvNum18 = holder.getView(R.id.tv_num_18);
                    TextView tvNum19 = holder.getView(R.id.tv_num_19);
                    TextView tvNum20 = holder.getView(R.id.tv_num_20);

                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7, tvNum8, tvNum9, tvNum10, tvNum11, tvNum12, tvNum13, tvNum14, tvNum15, tvNum16, tvNum17, tvNum18, tvNum19, tvNum20};
                    for (int i = 0; i < arr.length; i++) {
                        if (mTf_DinABold != arr[i].getTypeface()) {
                            arr[i].setTypeface(mTf_DinABold);
                        }
                        if (mode != 2) {
                            startAnimal(type, arr[i], i, arr_code[i], 150, 20, 20, arr_code, arr);
                        } else {
                            arr[i].setText(arr_code[i]);
                        }
                    }
                }
                break;
        }
    }

    private void setXT_k3(String[] arr_code, TextView[] arrXt) {
        String[] shapeArr = K3Util.getK3_ShapeData(arr_code);
        for (int i = 0; i < shapeArr.length; i++) {
            arrXt[i].setText(LanguageUtil.getText(shapeArr[i]));
        }
    }

    private void setXT_pk10(String[] arr_code, TextView[] arrXt) {
        String[] shapeArr = PK10Util.getPK10_ShapeData(arr_code);
        for (int i = 0; i < shapeArr.length; i++) {
            arrXt[i].setText(LanguageUtil.getText(shapeArr[i]));
        }
    }

    private void setXT_SSC_11x5(int serId, String[] arr_code, TextView[] arrXt) {
        String[] shapeArr = new String[0];
        if(serId == LotteryConstant.SER_ID_SSC || serId == LotteryConstant.SER_ID_PL35) {
            shapeArr = SSCUtil.getSSC_ShapeData(arr_code);
        } else if(serId == LotteryConstant.SER_ID_11X5){
            shapeArr  = _11x5Util.get11X5_ShapeData(arr_code);
        }
        for (int i = 0; i < shapeArr.length; i++) {
            arrXt[i].setText(LanguageUtil.getText(shapeArr[i]));
        }
    }

    private void startAnimal(int serId, View view, int index, String code, int duration, int delayP, int repeatCount, String[] arr_code, TextView[] arrXt) {
        if ("vivo Y67A".equalsIgnoreCase(systemModel)) {
            return;
        }
        if (2 == mode) {
            return;
        }

        int distance = mViewHeight + 10;
        float[] floats = {distance, -distance};
        ObjectAnimator outAnimator = ObjectAnimator.ofFloat(view, "translationY", floats);
        outAnimator.setRepeatCount(repeatCount);
        outAnimator.setInterpolator(new LinearInterpolator());
//        outAnimator.setStartDelay(mRandom.nextInt(index) * 20);
        outAnimator.setStartDelay(index * delayP);
        outAnimator.setDuration(duration);
        outAnimator.start();
        outAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                setAutoRandomValue(serId, view);
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                ObjectAnimator animators = (ObjectAnimator) animation;
                View view = (View) animators.getTarget();
                ObjectAnimator outAnimator = ObjectAnimator.ofFloat(view, "translationY", distance, 0);
                outAnimator.start();
                setFinishValue(serId, view, code);
                setFinishXT(serId, index, arr_code, arrXt);
            }
        });
    }


    private void startAnimal_lhc(View view, int index, String code, TextView tvSx, TextView tvValue) {
        if ("vivo Y67A".equalsIgnoreCase(systemModel)) {
            return;
        }
        if (2 == mode) {
            return;
        }
        tvSx.setVisibility(View.GONE);

        int distance = mViewHeight + 10;
        float[] floats = {distance, -distance};
        ObjectAnimator outAnimator = ObjectAnimator.ofFloat(view, "translationY", floats);
        outAnimator.setRepeatCount(10);
        outAnimator.setInterpolator(new LinearInterpolator());
        outAnimator.setStartDelay(mRandom.nextInt(index) * 10);
        outAnimator.setDuration(150);
        outAnimator.start();
        outAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                int r = 1 + mRandom.nextInt(49);
                Integer bg = ConfigurationUiUtils.getLHCBg(r);
                tvValue.setBackgroundResource(bg);
                tvValue.setText(String.valueOf(r));
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                ObjectAnimator animators = (ObjectAnimator) animation;
                View view = (View) animators.getTarget();
                ObjectAnimator outAnimator = ObjectAnimator.ofFloat(view, "translationY", distance, 0);
                outAnimator.start();

                int numInt = Integer.parseInt(code);
                Integer bg = ConfigurationUiUtils.getLHCBg(numInt);
                tvValue.setBackgroundResource(bg);
                tvValue.setText(code);

                String sx = LotteryUtil.getLHCSX(numInt);
                tvSx.setVisibility(View.VISIBLE);
                tvSx.setText(LanguageUtil.getText(sx));
            }
        });
    }


    private void startIVAni(ImageView iv, String code, String[] arr_code, TextView[] arrXt) {
        if (2 == mode) {
            Integer k3IconResId = ConfigurationUiUtils.kuaiSanMap.get(code);
            iv.setImageResource(k3IconResId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        MyCustomTarage myCustomTarage = new MyCustomTarage(iv, new MyCustomTarage.GifListener() {
            @Override
            public void gifPlayComplete(ImageView imageView) {
                imageView.clearAnimation();
                Integer k3IconResId = ConfigurationUiUtils.kuaiSanMap.get(code);
                if (null != k3IconResId) {
                    imageView.setImageResource(k3IconResId);
                } else {
                    imageView.setImageResource(R.mipmap.ski_touzi_default);
                }

                setXT_k3(arr_code, arrXt);
                LogUtils.e("gifPlayComplete:");
            }
        });
        Glide.with(AppUtil.getContext()).load(R.mipmap.ski_touzi_ani).into(myCustomTarage).onDestroy();
    }

    private void setAutoRandomValue(int serId, View view) {
        switch (serId) {
            case LotteryConstant.SER_ID_PK10: {
                // 1- 10
                int r = 1 + mRandom.nextInt(10);
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    Integer bg = ConfigurationUiUtils.pk10bgMap.get(String.valueOf(r));
                    tv.setBackgroundResource(bg);
                    tv.setText(String.valueOf(r));
                }
                break;
            }
            case LotteryConstant.SER_ID_3D:
            case LotteryConstant.SER_ID_PL35:
            case LotteryConstant.SER_ID_SSC: {
                // 0- 9
                int r = mRandom.nextInt(10);
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    tv.setText(String.valueOf(r));
                }
                break;
            }
            case LotteryConstant.SER_ID_11X5: {
                // 01- 11
                int r = 1 + mRandom.nextInt(11);
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    if (r < 10) {
                        tv.setText("0" + r);
                    } else {
                        tv.setText(String.valueOf(r));
                    }
                }
                break;
            }
            case LotteryConstant.SER_ID_KL8: {
                // 01- 80
                int r = 1 + mRandom.nextInt(80);
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    if (r < 10) {
                        tv.setText("0" + r);
                    } else {
                        tv.setText(String.valueOf(r));
                    }
                }
                break;
            }
        }
    }

    private void setFinishValue(int serId, View view, String code) {
        switch (serId) {
            case LotteryConstant.SER_ID_PK10:
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    Integer bg = ConfigurationUiUtils.pk10bgMap.get(code);
                    tv.setBackgroundResource(bg);
                    tv.setText(code);
                }
                break;
            case LotteryConstant.SER_ID_KL8:
            case LotteryConstant.SER_ID_11X5:
            case LotteryConstant.SER_ID_PL35:
            case LotteryConstant.SER_ID_3D:
            case LotteryConstant.SER_ID_SSC: {
                // 0 - 9
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    tv.setText(code);
                }
                break;
            }
        }

    }

    private void setFinishXT(int serId, int i, String[] arr_code, TextView[] arrXt) {
        switch (serId) {
            case LotteryConstant.SER_ID_PK10:
                if (9 == i) {
                    setXT_pk10(arr_code, arrXt);
                }
                break;
            case LotteryConstant.SER_ID_KL8:
                break;
            case LotteryConstant.SER_ID_11X5:
            case LotteryConstant.SER_ID_PL35:
            case LotteryConstant.SER_ID_SSC:
                if (4 == i) {
                    setXT_SSC_11x5(serId, arr_code, arrXt);
                }
                break;
            case LotteryConstant.SER_ID_3D:
                break;
        }

    }
}
