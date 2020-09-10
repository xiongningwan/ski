package com.ski.box.view.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ski.box.R;
import com.ski.box.bean.AlertConfigurationBean;
import com.ski.box.bean.QuickSetMoneyBean;
import com.ski.box.utils.KeyBoardUtils;
import com.ski.box.view.view.dialog.CustomBottomDialog;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.ski.box.view.view.keyboard.KeyBoardBean;
import com.ski.box.view.view.keyboard.NumsKeyBoardView;
import com.yb.core.utils.SPUtils;
import com.yb.core.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST;


public class QuickMoneySettingView extends LinearLayout {
    private ImageView mClosePopSetquickamount;
    private androidx.recyclerview.widget.RecyclerView recycleview;
    private TextView mMarkedWords;
    private TextView mRestoreDefault;
    private TextView mSaveQuickAmount;
    private List<QuickSetMoneyBean> mDatas;
    private SettingMoneyRecyclerAdapter adapter;
    private CustomBottomDialog tDailog;
    private List<String> copyDates;
    private NumsKeyBoardView numKeyboard;
    StringBuffer stringBuffer = new StringBuffer();
    private EditText currentEditText;

    public QuickMoneySettingView(Context context) {
        super(context);
        initLayout(context);
    }


    public QuickMoneySettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public QuickMoneySettingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        View.inflate(getContext(), R.layout.ski_double_dialog_etquick_money, this);
        mClosePopSetquickamount = findViewById(R.id.close_pop_setquickamount);
        recycleview = findViewById(R.id.recycle_setting_quick_money);
        mMarkedWords = findViewById(R.id.marked_words);
        mRestoreDefault = findViewById(R.id.restore_default);
        mSaveQuickAmount = findViewById(R.id.save_quick_amount);
        numKeyboard = findViewById(R.id.num_keyboard);

        mDatas = new ArrayList<>();
        adapter = new SettingMoneyRecyclerAdapter(context);
        recycleview.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recycleview.setLayoutManager(gridLayoutManager);


        /*恢复默认值*/
        mRestoreDefault.setOnClickListener(v -> {
            mDatas.clear();
            mDatas.add(new QuickSetMoneyBean("10", false));
            mDatas.add(new QuickSetMoneyBean("50", false));
            mDatas.add(new QuickSetMoneyBean("100", false));
            mDatas.add(new QuickSetMoneyBean("500", false));
            adapter.notifyDataSetChanged();
          //  new LotteryDialog().showCenterRemind(getContext(),"已恢复默认值");
            ToastUtil.showInfo("已恢复默认值");
        });
        mSaveQuickAmount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = new Gson().toJson(mDatas);
                SPUtils.putString(QuickMoneySettingView.this.getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, s);
                if (tDailog != null) {
                    View childAt = recycleview.getChildAt(0);
                    EditText viewById = childAt.findViewById(R.id.set_amount);
                    KeyBoardUtils.closeKeybord(viewById, QuickMoneySettingView.this.getContext());
                    tDailog.dismiss();
                }
            }
        });

        /*监听键盘操作*/
        numKeyboard.setOnItemClickListener((position, bean) -> {
            int type = bean.getType();
            String num = bean.getNum();

            if (type == KeyBoardBean.DISMISS) {
                numKeyboard.collapse(false);

            } else if (type == KeyBoardBean.DELETE) {
                int length = stringBuffer.length();
                if (length - 1 >= 0) {
                    stringBuffer.deleteCharAt(length - 1);
                    currentEditText.setText(stringBuffer.toString());
                }
            } else if (type == KeyBoardBean.NUMBER) {
                int length = stringBuffer.length();
                if (length == 0) {
                    if (num.equals("0")) {
                        return;
                    }
                }

                stringBuffer.append(num);
                String totalnum = stringBuffer.toString();
                Integer integer = Integer.valueOf(totalnum);
                if (integer > 99999) {
                    integer = 99999;
                    stringBuffer.delete(0, stringBuffer.length());
                    stringBuffer.append(integer + "");
                }


                String s = stringBuffer.toString();
                currentEditText.setText(s);
            }
        });

    }

    public void configurationData(CustomBottomDialog bottomFramentDailog) {
        this.tDailog = bottomFramentDailog;
        mDatas.clear();
        String defaultMoney = SPUtils.getString(getContext(), SP_DOUBLE_QUICK_MONEY_DEFAULT_LIST, "");
        try {
            JSONArray jsonArray = new JSONArray(defaultMoney);
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject jsonObject = jsonArray.getJSONObject(x);
                String money = jsonObject.getString("money");
                boolean red = jsonObject.getBoolean("red");
                mDatas.add(new QuickSetMoneyBean(money, red));

            }
            copyDates = new ArrayList();

            for (int x = 0; x < mDatas.size(); x++) {
                QuickSetMoneyBean quickSetMoneyBean = mDatas.get(x);
                String money = quickSetMoneyBean.getMoney();
                copyDates.add(money);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*关闭弹框*/
        mClosePopSetquickamount.setOnClickListener(v -> {
            boolean b = hasChange();
            if (!b) {
               /* View childAt = recycleview.getChildAt(0);
                EditText viewById = childAt.findViewById(R.id.set_amount);
                KeyBoardUtils.closeKeybord(viewById, getContext());*/
                bottomFramentDailog.dismiss();
            }
        });
        tDailog.setOntouchoutclicklistener(new CustomBottomDialog.OnTouchOutClickListener() {
            @Override
            public void onTouchOutside() {
                boolean b = hasChange();
                if (!b) {
                    tDailog.dismiss();
                }

            }

        });
        adapter.notifyDataSetChanged();
    }

    private boolean hasChange() {
        numKeyboard.collapse(false);
        boolean showDialog = false;
        for (int x = 0; x < mDatas.size(); x++) {
            QuickSetMoneyBean quickSetMoneyBean = mDatas.get(x);
            String money = quickSetMoneyBean.getMoney();
            if (copyDates == null) {
                return false;
            }
            String s = copyDates.get(x);
            if (!money.equals(s)) {
                showDialog = true;
                break;
            }
        }
        if (showDialog) {
            AlertConfigurationBean bean = new AlertConfigurationBean();
            bean.setContent("有修改的内容未保存," + "\n" + "确认离开吗?");
            bean.setLeftButtonText("取消");
            bean.setRightButtonText("离开");
            new LotteryDialog().showAlertDialog(getContext(), bean, new LotteryDialog.OnSelectAlertDialogCallBack() {
                @Override
                public void rightButton() {
                    if (tDailog != null) {
                        tDailog.dismiss();
                    }
                }
                @Override
                public void leftButton() {

                }
            });
        }
        return showDialog;
    }

    private int lastPosition = -1;

    public class SettingMoneyRecyclerAdapter extends RecyclerView.Adapter<MoneyViewHolder> {
        private final Context mContext;

        public SettingMoneyRecyclerAdapter(Context context) {
            this.mContext = context;
        }

        @NonNull
        @Override
        public MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.ski_item_quick_money_setting, parent, false);
            return new MoneyViewHolder(inflate);
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(@NonNull MoneyViewHolder holder, int position) {
            QuickSetMoneyBean data = mDatas.get(position);
            String money = data.getMoney();
            holder.editText.setText(money);
            boolean red = data.isRed();
            if (red) {
                holder.editText.setBackgroundResource(R.mipmap.ski_back_kuai_jie_jin_e_edit_origin);

            } else {
                holder.editText.setBackgroundResource(R.mipmap.ski_back_kuai_jie_jin_e_edit);
            }
            holder.clearAmount.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.editText.setText("");
                    stringBuffer.delete(0, stringBuffer.length());
                }
            });

            KeyBoardUtils.dismissSystemkeyBoard((Activity) mContext, holder.editText);
            holder.editText.setOnTouchListener((v, event) -> {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    showCustomKeyBoard();
                    if (lastPosition != position) {
                        currentEditText = holder.editText;
                        stringBuffer.delete(0, stringBuffer.length());
                        stringBuffer.append(currentEditText.getText().toString());

                        lastPosition = position;
                    }
                }
                return false;
            });


            holder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String s1 = s.toString();
                    boolean matches = s1.matches("\\d+");
                    if (matches) {
                        if (holder.clearAmount.getVisibility() == View.GONE) {
                            holder.clearAmount.setVisibility(View.VISIBLE);

                        }

                    } else {
                        if (holder.clearAmount.getVisibility() == View.VISIBLE) {
                            holder.clearAmount.setVisibility(View.GONE);

                        }
                    }
                    mDatas.get(position).setMoney(s1);
                    boolean containSameValue;
                    for (int x = 0; x < mDatas.size(); x++) {
                        containSameValue = false;
                        for (int y = 0; y < mDatas.size(); y++) {
                            if (mDatas.get(x).getMoney().equals(mDatas.get(y).getMoney()) &&
                                    x != y) {
                                mDatas.get(x).setRed(true);
                                // 刷新操作
                                ((MoneyViewHolder) recycleview.getChildViewHolder(recycleview.getChildAt(x))).editText.setBackgroundResource(R.mipmap.ski_back_kuai_jie_jin_e_edit_origin);
                                containSameValue = true;
                            }
                        }
                        if (!containSameValue) {
                            mDatas.get(x).setRed(false);
                            View childAt = recycleview.getChildAt(x);
                            if (childAt != null) {
                                MoneyViewHolder childViewHolder = (MoneyViewHolder) recycleview.getChildViewHolder(childAt);
                                childViewHolder.editText.setBackgroundResource(R.mipmap.ski_back_kuai_jie_jin_e_edit);
                            }
                        }
                    }

                    holder.editText.setSelection(s.length());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    private void showCustomKeyBoard() {
        boolean expanded = numKeyboard.isExpanded();
        if (!expanded) {
            numKeyboard.expand(false);
        }

    }


    private class MoneyViewHolder extends RecyclerView.ViewHolder {
        public EditText editText;
        private ImageView clearAmount;

        public MoneyViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.set_amount);
            clearAmount = itemView.findViewById(R.id.clear_amount);
        }
    }
}
