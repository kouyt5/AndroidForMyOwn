package com.example.permission.test;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class BaseHolder<T extends BaseViewComponet> extends RecyclerView.ViewHolder {

    public String type;
    public T t;

    public BaseHolder(@NonNull View itemView,T t) {
        super(itemView);
        this.t=t;
        t.bindAllView(itemView);
    }
}
