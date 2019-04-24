package com.example.permission.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.permission.R;
import com.example.permission.test.BaseHolder;
import com.example.permission.test.FirstView;
import com.example.permission.test.SeondView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class RecyclerdAdapter extends RecyclerView.Adapter<BaseHolder> {
    private Context context;
    private ArrayList<String> titles;

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i%2==1){
            Logger.d(i);
            return new BaseHolder<>(LayoutInflater.from(context)
                    .inflate(R.layout.item_layout, viewGroup, false),new FirstView());
        }else {
            Logger.d(i);
            return new BaseHolder<>(LayoutInflater.from(context)
                    .inflate(R.layout.item_second_layout, viewGroup, false),new SeondView());

        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder viewHolder, int i) {

        if (viewHolder.getClass().getName().equals("FirstHolder")) {
            viewHolder.type="1";
            viewHolder.t.setData("FirstHolder");
        }else {
            viewHolder.type="0";
            viewHolder.t.setData("secondHolder");
        }
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public RecyclerdAdapter(Context context, ArrayList<String> titles) {
        this.context = context;
        this.titles = titles;
    }


    public interface OnItemClickListenser {

        void OnItemClick(int position);
    }

    public OnItemClickListenser onItemClickListenser;

    public void setOnItemClickListenser(OnItemClickListenser onItemClickListenser) {
        this.onItemClickListenser = onItemClickListenser;
    }
}
