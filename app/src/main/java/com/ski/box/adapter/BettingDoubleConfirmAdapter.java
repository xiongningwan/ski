package com.ski.box.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;
import com.ski.box.R;
import com.ski.box.bean.MkBetParamEntity;
import com.ski.box.bean.RedLimitBean;
import com.ski.box.utils.KeyBoardUtils;
import com.ski.box.utils.lottery.LotteryNoUtil;
import com.ski.box.view.view.dialog.LotteryDialog;
import com.yb.core.utils.LanguageUtil;
import com.yb.core.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.ski.box.ConstantValue.EVENT_CLEAN_XUAN_HAO_PAN;


public class BettingDoubleConfirmAdapter extends RecyclerView.Adapter<BettingDoubleConfirmAdapter.DoubleConfirmViewHolder> {
    private final Context mContext;
    private List<MkBetParamEntity.BetParamEntity> mDatas = new ArrayList<>();
    private int hasFocusePosition = -1;
    private EditText lastEditText;

    public BettingDoubleConfirmAdapter(Context context) {
        this.mContext = context;

    }

    @NotNull
    @Override
    public BettingDoubleConfirmAdapter.DoubleConfirmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ski_item_buy_confirm_layout, parent, false);

        return new DoubleConfirmViewHolder(view);
    }

    public int getItemHeight() {
        return ScreenUtils.dip2px(60);
    }

    @SuppressLint({"DefaultLocale", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull DoubleConfirmViewHolder viewHolder, final int position) {
        //绑定数据和监听事件.这里编写
        viewHolder.setIsRecyclable(false);
        if (mDatas == null) {
            return;
        }
        MkBetParamEntity.BetParamEntity doubleConfirmBean = mDatas.get(position);
        if (doubleConfirmBean != null) {
            String playName = doubleConfirmBean.getGroupName();
            boolean redLimit = doubleConfirmBean.isRedLimit();
            String playItemName = doubleConfirmBean.getContent();
            String s = String.valueOf(doubleConfirmBean.getBetAmount_d());
            viewHolder.mEditText.setText(s);
            viewHolder.mEditText.setSelection(s.length());
            viewHolder.tvPlayName.setText(LanguageUtil.getText(playName));
            viewHolder.tvPlayItemName.setText(playItemName);

            if (redLimit) {
                viewHolder.tvRemind.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvRemind.setVisibility(View.GONE);
            }
            setWinMoney(viewHolder, doubleConfirmBean);
        }
       // KeyBoardUtils.dismissSystemkeyBoard((Activity) mContext, viewHolder.mEditText);
        if (position == hasFocusePosition) {
            viewHolder.mEditText.setBackground(mContext.getResources().getDrawable(R.drawable.ski_edtext_bottom_focuse));
        } else {
            viewHolder.mEditText.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ski_edtext_bottom_unfocuse));
        }
        viewHolder.mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString();
                boolean matches = money.matches("\\d+");
                if (!matches) {
                    money = "0";
                }

                try {
                    viewHolder.mEditText.setSelection(money.length());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Long itemAmount = Long.valueOf(money);

                if (position < mDatas.size()) {
                    MkBetParamEntity.BetParamEntity bean = mDatas.get(position);
                    String playId = bean.getPlayId();
                    bean.setBetAmount_d(itemAmount);
                    mItemClickListener.changeMoney();
                    int visibility = viewHolder.tvRemind.getVisibility();
                    if (View.VISIBLE == visibility) {
                        viewHolder.tvRemind.setVisibility(View.GONE);
                        bean.setRedLimit(false);
                    }
                    setWinMoney(viewHolder, bean);
                }
            }
        });
        if (mItemClickListener != null) {
            viewHolder.itemRemove.setOnClickListener(v -> {
                if (position < mDatas.size()) {
                    MkBetParamEntity.BetParamEntity confirmBean = mDatas.get(position);
                    mDatas.remove(position);
                    mItemClickListener.onItemRemoveClick(confirmBean);
                }
                int size = mDatas.size();
                if (size == 0) {
                    if (onCloseDialogListener != null) {
                        RxBus.get().post(EVENT_CLEAN_XUAN_HAO_PAN,"clen");
                        onCloseDialogListener.closeDialog();
                    }

                }


            });
        }

        viewHolder.mEditText.setOnTouchListener((v, event) -> {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                if (onEditTextViewClickListener != null) {
                    if (lastEditText != null) {
                        lastEditText.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ski_edtext_bottom_unfocuse));
                    }
                    lastEditText = viewHolder.mEditText;
                    viewHolder.mEditText.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ski_edtext_bottom_focuse));
                    onEditTextViewClickListener.editTextViewClick(position);
                    hasFocusePosition = position;
                }
            }

            return false;
        });


    }

    private void setWinMoney(@NonNull DoubleConfirmViewHolder viewHolder, MkBetParamEntity.BetParamEntity betParamEntity) {
        String win = LotteryNoUtil.calculateWinMoney_d(betParamEntity);
        viewHolder.itemGain.setText(LanguageUtil.getText("可赢") + win );
    }


    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }


    private ItemClickListener mItemClickListener;

    public void refreshDatas(List<MkBetParamEntity.BetParamEntity> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    private OnCloseDialogListener onCloseDialogListener;

    public void setOnCloseDialogListener(OnCloseDialogListener listener) {
        onCloseDialogListener = listener;
    }


    public void showRedLimitReminder(ArrayList<RedLimitBean> redLimitBeans, View view) {
        for (int x = 0; x < mDatas.size(); x++) {
            MkBetParamEntity.BetParamEntity doubleConfirmBean = mDatas.get(x);
            String playId = doubleConfirmBean.getPlayId();
            for (int y = 0; y < redLimitBeans.size(); y++) {
                RedLimitBean redLimitBean = redLimitBeans.get(y);
                String playItemId1 = redLimitBean.getPlayItemId();
                if (playId.equals(playItemId1)) {
                    doubleConfirmBean.setRedLimit(true);
                }
            }
        }
        if (redLimitBeans.size() > 0) {
            new Handler().postDelayed(() -> {
                new LotteryDialog().showRedLimitBubbleLayout(view, redLimitBeans);
                notifyDataSetChanged();


            }, 1000);

        }

    }


    public interface OnCloseDialogListener {

        void closeDialog();

    }

    public interface ItemClickListener {
        /**
         * @param position 条目点击事件
         */
        void onItemRemoveClick(MkBetParamEntity.BetParamEntity position);

        void changeMoney();
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }

    public class DoubleConfirmViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPlayName;
        public TextView tvPlayItemName;
        public EditText mEditText;
        public TextView itemGain;
        public ImageView itemRemove;
        public TextView tvRemind;

        public DoubleConfirmViewHolder(View itemView) {
            super(itemView);
            tvPlayName = itemView.findViewById(R.id.tv_play_name);
            tvPlayItemName = itemView.findViewById(R.id.tv_play_item_name);
            mEditText = itemView.findViewById(R.id.et_item_amount);
            itemGain = itemView.findViewById(R.id.item_gain);
            itemRemove = itemView.findViewById(R.id.item_remove);
            tvRemind = itemView.findViewById(R.id.tv_remind);
        }
    }

    private OnEditTextViewClickListener onEditTextViewClickListener;

    public interface OnEditTextViewClickListener {
        void editTextViewClick(int position);
    }

    public void setOnEditTextViewClickListener(OnEditTextViewClickListener listener) {
        onEditTextViewClickListener = listener;
    }
}
