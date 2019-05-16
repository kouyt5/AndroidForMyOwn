package com.example.permission.powindows;

import android.app.ActionBar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.core.content.ContextCompat;

import com.example.permission.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MyPop extends PopupWindow implements View.OnClickListener {

    private View popView;
    private OnItemClickListener mListener;

    private Button cancel;
    private Button out;

    public MyPop(Context context) {
        super(context);
        popView = LayoutInflater.from(context).inflate(R.layout.popwindows, null);
        ButterKnife.bind(popView,(Activity)context);
        setPopupWindow();
        cancel=popView.findViewById(R.id.popwindows_button_cancel);
        out=popView.findViewById(R.id.popwindows_button_out);
        cancel.setOnClickListener(this);
        out.setOnClickListener(this);
    }

    private void setPopupWindow() {
        this.setContentView(popView);// 设置View
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.popwindows_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        popView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = popView.findViewById(R.id.popwindows_button_out).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (mListener != null) {
            mListener.setOnItemClick(v);
        }
    }
}
