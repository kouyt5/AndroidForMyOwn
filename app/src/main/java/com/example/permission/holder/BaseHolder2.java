package com.example.permission.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseHolder2 extends RecyclerView.ViewHolder {
    public BaseHolder2(@NonNull View itemView) {
        super(itemView);
    }
    public abstract void BindView();
    public abstract void BindData();
}
