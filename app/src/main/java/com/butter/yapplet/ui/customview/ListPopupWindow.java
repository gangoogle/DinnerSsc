package com.butter.yapplet.ui.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.butter.yapplet.R;
import com.butter.yapplet.model.PopListItem;
import com.butter.yapplet.utils.ComUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zgyi on 2017/2/17.
 */

public class ListPopupWindow {

    private PopupWindow mPopupWindow;
    private Context mContext;
    private List<PopListItem> mStringList;
    private String mCurrentSelectedText;
    private int mSelectedColor;
    private OnPopupItemClick onPopupItemClick;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private OnPopupWindowDismiss onPopupWindowDismiss;
    private ImageView mIvCenterArrow;
    private ImageView mIvRightArrow;
    private LinearLayout mLLContainer;
    //当前选中的下标 -1为没有使用下标做标记而是使用字符串做比对
    private int mSelectedPosition = -1;


    /**
     * @param aContext
     * @param aFocusable     点击popupwindow外可关闭popupwindow
     * @param aSelectedColor 设置选中item颜色 默认传 0
     * @param aArrowLocation 设置箭头位置 0居住 小于0左 大于0右
     */
    public ListPopupWindow(Context aContext, boolean aFocusable, int aSelectedColor, int aArrowLocation) {
        super();
        mContext = aContext;
        mPopupWindow = new PopupWindow(mContext);
        //默认选中颜色为蓝色
        if (aSelectedColor == 0) {
            mSelectedColor = mContext.getResources().getColor(R.color.colorMain);
        } else {
            mSelectedColor = aSelectedColor;
        }
        mCurrentSelectedText = "";
        View view = View.inflate(mContext, R.layout.view_popup_window, null);
        mPopupWindow.setContentView(view);
        //箭头位置
        mIvCenterArrow = (ImageView) view.findViewById(R.id.iv_popupwin_center);
        mIvRightArrow = (ImageView) view.findViewById(R.id.iv_popupwin_right);
        mLLContainer = (LinearLayout) view.findViewById(R.id.ll_container);
        //设置箭头位置
        if (aArrowLocation == 0) {
            mIvCenterArrow.setVisibility(View.VISIBLE);
        } else if (aArrowLocation == 1) {
            mIvRightArrow.setVisibility(View.VISIBLE);
            //箭头在右边的话，给窗口右边加一个padding
            mLLContainer.setPadding(0, 0, ComUtils.dip2px(aContext, 10f), 0);
        }
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(aFocusable);
//        设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
//        设置SelectPicPopupWindow弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.setAnimationStyle(R.style.PopupAnimDropdown);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
                if (onPopupWindowDismiss != null) {
                    onPopupWindowDismiss.onDismiss();
                }
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mStringList = new ArrayList<>();
        mAdapter = new Adapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置数据 show之前调用 可以再次调用修改数据
     *
     * @param aStringList
     */
    public void setData(List<PopListItem> aStringList) {
        if (aStringList != null) {
            mStringList.clear();
            mStringList.addAll(aStringList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public int[] getSize() {
        mPopupWindow.getContentView().measure(0, 0);
        int width = mPopupWindow.getContentView().getMeasuredWidth();
        int height = mPopupWindow.getContentView().getMeasuredHeight();
        return new int[]{width, height};
    }


    public void showAtLocation(String aCurrentSelectedText, View parent, int gravity, int x, int y) {
        this.mCurrentSelectedText = aCurrentSelectedText;
        this.mSelectedPosition = -1;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAtLocation(parent, gravity, x, y);
    }

    public void showAtLocation(int aSelectedPosition, View parent, int gravity, int x, int y) {
        this.mCurrentSelectedText = "";
        this.mSelectedPosition = aSelectedPosition;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAtLocation(parent, gravity, x, y);
    }

    public void showAsDropDown(String aCurrentSelectedText, View anchor, int xoff, int yoff) {
        this.mCurrentSelectedText = aCurrentSelectedText;
        this.mSelectedPosition = -1;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAsDropDown(int aSelectedPosition, View anchor, int xoff, int yoff) {
        this.mCurrentSelectedText = "";
        this.mSelectedPosition = aSelectedPosition;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAsDropDown(String aCurrentSelectedText, View anchor) {
        this.mCurrentSelectedText = aCurrentSelectedText;
        this.mSelectedPosition = -1;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor);
    }

    public void showAsDropDown(int aSelectedPosition, View anchor) {
        this.mCurrentSelectedText = "";
        this.mSelectedPosition = aSelectedPosition;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor);
    }

    /**
     * 显示在指定view下并居中
     *
     * @param aCurrentSelectedText 选中的文本
     * @param anchor
     */
    public void showAsDropDownCenter(String aCurrentSelectedText, View anchor, int offsetY) {
        this.mCurrentSelectedText = aCurrentSelectedText;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor, -(getSize()[0] / 2 - anchor.getWidth() / 2), offsetY);
    }

    /**
     * 显示在指定view下并居中
     *
     * @param aSelectedPosition 选中的下标
     * @param anchor
     */
    public void showAsDropDownCenter(int aSelectedPosition, View anchor, int offsetY) {
        this.mCurrentSelectedText = "";
        this.mSelectedPosition = aSelectedPosition;
        mAdapter.notifyDataSetChanged();
        backgroundAlpha(0.5f);
        mPopupWindow.showAsDropDown(anchor, -(getSize()[0] / 2 - anchor.getWidth() / 2), offsetY);
    }

    /**
     * 注册popupwindow的关闭事件监听
     *
     * @param aOnPopupWindowDismiss
     */
    public void addOnPopupWindowDismissListener(OnPopupWindowDismiss aOnPopupWindowDismiss) {
        onPopupWindowDismiss = aOnPopupWindowDismiss;
    }

    /**
     * 注册item点击事件监听
     *
     * @param aOnPopupItemClick
     */
    public void addOnPopupItemClickListener(OnPopupItemClick aOnPopupItemClick) {
        onPopupItemClick = aOnPopupItemClick;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            String string = mStringList.get(position).getName();
            holder.textView.setText(string);
            if (mSelectedPosition == -1) {
                if (mCurrentSelectedText.equals(string)) {
                    holder.textView.setTextColor(mSelectedColor);
                } else {
                    holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorBlackA));
                }
            } else {
                if (mSelectedPosition == position) {
                    holder.textView.setTextColor(mSelectedColor);
                } else {
                    holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorBlackA));
                }
            }
            if (position == 0) {
                holder.flDivide.setVisibility(View.GONE);
            } else {
                holder.flDivide.setVisibility(View.VISIBLE);
            }
            holder.ivIcon.setImageResource(mStringList.get(position).getIcon());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    Message message = new Message();
                    message.what = 0;
                    message.arg1 = position;
                    //延迟执行防止动画卡顿
                    handler.sendMessageDelayed(message, 155);
                }
            });
        }

        @Override
        public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout
                    .item_list_popup_window, parent, false));
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return mStringList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private FrameLayout flDivide;
            private ImageView ivIcon;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.textview);
                flDivide = (FrameLayout) itemView.findViewById(R.id.fl_divide);
                ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            }


        }
    }


    public interface OnPopupItemClick {
        void onItemClick(int position);
    }


    public interface OnPopupWindowDismiss {
        void onDismiss();
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (onPopupItemClick != null) {
                        onPopupItemClick.onItemClick(msg.arg1);
                    }
                    break;
            }
        }
    };
}
