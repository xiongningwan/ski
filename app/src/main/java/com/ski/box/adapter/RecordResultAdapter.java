package com.ski.box.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ski.box.R;
import com.ski.box.bean.RecordResultBean;
import com.ski.box.bean.lottery.LotteryConstant;
import com.ski.box.bean.record.RecordBet;
import com.ski.box.utils.SystemUtil;
import com.ski.box.utils.lottery.ConfigurationUiUtils;
import com.yb.core.utils.ScreenUtils;

import org.eclipse.paho.client.mqttv3.util.Strings;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Created by tom on 2020/10/27.
 */
public class RecordResultAdapter extends BaseMultiItemQuickAdapter<RecordResultBean, BaseViewHolder> {
    private final int mViewHeight;
    private final Context mContext;
    String systemModel;
    Random mRandom;
    Typeface mTf_DinABold;

    public RecordResultAdapter(Context context) {
        addItemType(LotteryConstant.SER_ID_PK10, R.layout.ski_item_record_result_type_pk10);
        addItemType(LotteryConstant.SER_ID_SSC, R.layout.ski_item_record_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_LHC, R.layout.ski_item_record_result_type_lhc);
        addItemType(LotteryConstant.SER_ID_11X5, R.layout.ski_item_record_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_K3, R.layout.ski_item_record_result_type_k3);
        addItemType(LotteryConstant.SER_ID_3D, R.layout.ski_item_record_result_type_3d);
        addItemType(LotteryConstant.SER_ID_PL35, R.layout.ski_item_record_result_type_ssc);
        addItemType(LotteryConstant.SER_ID_KL8, R.layout.ski_item_record_result_type_kl8);
        mContext = context;
        systemModel = SystemUtil.getSystemModel();
        mViewHeight = ScreenUtils.dip2px(60);
        mRandom = new Random();
        //从asset 读取字体
        AssetManager mgr = context.getAssets();
        //根据路径得到Typeface
        mTf_DinABold = Typeface.createFromAsset(mgr, "fonts/DIN_Alternate_Bold.ttf");
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, RecordResultBean  bean) {
        String code = bean.getCode();
        String[] arr_code = new String[0];
        if (!Strings.isEmpty(code) && code.contains(" ")) {
            arr_code = code.split(" ");
        }
        int type = holder.getItemViewType();
        switch (type) {
            case LotteryConstant.SER_ID_PK10:
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

                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7, tvNum8, tvNum9, tvNum10};
                    for (int i = 0; i < arr.length; i++) {
                       // startAnimal(arr[i], i + 1);
                        Integer bg = ConfigurationUiUtils.pk10bgMap.get(arr_code[i]);
                        arr[i].setBackgroundResource(bg);
                        arr[i].setText(arr_code[i]);
                    }
                }
                break;
            case LotteryConstant.SER_ID_SSC:
            case LotteryConstant.SER_ID_11X5:
            case LotteryConstant.SER_ID_PL35: {

                TextView tvNum1 = holder.getView(R.id.tv_num_1);
                TextView tvNum2 = holder.getView(R.id.tv_num_2);
                TextView tvNum3 = holder.getView(R.id.tv_num_3);
                TextView tvNum4 = holder.getView(R.id.tv_num_4);
                TextView tvNum5 = holder.getView(R.id.tv_num_5);
                TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5};
                if (5 == arr_code.length) {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].setVisibility(View.VISIBLE);
                        arr[i].setText(arr_code[i]);
                    }
                } else {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].setVisibility(View.GONE);
                    }
                }
            } break;
            case LotteryConstant.SER_ID_3D: {
                TextView tvNum1 = holder.getView(R.id.tv_num_1);
                TextView tvNum2 = holder.getView(R.id.tv_num_2);
                TextView tvNum3 = holder.getView(R.id.tv_num_3);
                TextView[] arr = {tvNum1, tvNum2, tvNum3};
                if (3 == arr_code.length) {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].setVisibility(View.VISIBLE);
                        arr[i].setText(arr_code[i]);
                    }
                } else {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].setVisibility(View.GONE);
                    }
                }
            }break;
            case LotteryConstant.SER_ID_LHC:
                AppCompatTextView tvJia = holder.getView(R.id.tv_num_jia);
                if (7 == arr_code.length) {
                    tvJia.setVisibility(View.VISIBLE);
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
//                    TextView tvSx1 = holder.getView(R.id.tv_sx_1);
//                    TextView tvSx2 = holder.getView(R.id.tv_sx_2);
//                    TextView tvSx3 = holder.getView(R.id.tv_sx_3);
//                    TextView tvSx4 = holder.getView(R.id.tv_sx_4);
//                    TextView tvSx5 = holder.getView(R.id.tv_sx_5);
//                    TextView tvSx6 = holder.getView(R.id.tv_sx_6);
//                    TextView tvSx7 = holder.getView(R.id.tv_sx_7);

                    View[] arrFl = {fl1, fl2, fl3, fl4, fl5, fl6, fl7};
                    TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7};
//                    TextView[] arrSx = {tvSx1, tvSx2, tvSx3, tvSx4, tvSx5, tvSx6, tvSx7};
                    for (int i = 0; i < arr.length; i++) {
                        try {
                            int numInt = Integer.parseInt(arr_code[i]);
                        //    startAnimal_lhc(arrFl[i], i + 1, numInt, arrSx[i]);
                            Integer bg = ConfigurationUiUtils.getLHCBg(numInt);
                            arr[i].setBackgroundResource(bg);
                            arr[i].setText(arr_code[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    tvJia.setVisibility(View.GONE);
                }

                break;
            case LotteryConstant.SER_ID_K3:
                if (3 == arr_code.length) {
                    ImageView ivNum1 = holder.getView(R.id.iv_num_1);
                    ImageView ivNum2 = holder.getView(R.id.iv_num_2);
                    ImageView ivNum3 = holder.getView(R.id.iv_num_3);
                    ImageView[] arr = {ivNum1, ivNum2, ivNum3};
                    for (int i = 0; i < arr.length; i++) {
                        Integer k3IconResId = ConfigurationUiUtils.kuaiSanMap.get(arr_code[i]);
                        arr[i].setImageResource(k3IconResId);
                    }
                }

                break;
            case LotteryConstant.SER_ID_KL8: {
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
                TextView[] arr = {tvNum1, tvNum2, tvNum3, tvNum4, tvNum5, tvNum6, tvNum7, tvNum8, tvNum9, tvNum10,
                        tvNum11, tvNum12, tvNum13, tvNum14, tvNum15, tvNum16, tvNum17, tvNum18, tvNum19, tvNum20};
                if (20 == arr_code.length) {
                    for (int i = 0; i < arr.length; i++) {
                        //  startAnimal(arr[i], i + 1);
                        arr[i].setText(arr_code[i]);
                    }
                } else {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i].setVisibility(View.GONE);
                    }
                }
            }  break;
        }
    }



}
