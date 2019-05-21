package com.example.permission.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.permission.R;
import com.example.permission.holder.BaseHolder2;
import com.example.permission.holder.FirstHolder2;

public class TestAdapter extends RecyclerView.Adapter<BaseHolder2> {

    public Context context;
    @NonNull
    @Override
    public BaseHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_recyclerd,parent,false);
        return new FirstHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder2 holder, int position) {

        if(holder.getItemViewType()==0){
            holder.BindView();
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }
}
