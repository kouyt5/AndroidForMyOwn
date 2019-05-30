package com.example.permission.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.permission.R;
import com.example.permission.mydialog.MyDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PictureRecycleAdapter extends RecyclerView.Adapter<PictureRecycleAdapter.PictureHolder> {
    private List<String> mlist;
    private Context mcontext;

    public PictureRecycleAdapter(Context context, List<String> list) {
        mlist = list;
        this.mcontext=context;
    }

    @NonNull
    @Override
    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_card_view, parent, false);
        return new PictureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureHolder holder, int position) {
        ArrayList<Uri> uriList=new ArrayList<Uri>();
        for (String s:mlist){
            uriList.add(Uri.parse(s));
        }

        holder.setImageView(mcontext,position,uriList);
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class PictureHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public PictureHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);

        }

        public void setImageView(Context context, int position,ArrayList<Uri> uriList) {
            Glide.with(context)
                    .load(uriList.get(position))
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MyDialog(context,R.style.Plane_Dialog,uriList,position).show();
                }
            });
        }


    }

    public void setOnShowMoreListener(OnShowMoreListener listener){
        this.onShowMoreListener=listener;
    };

    public interface OnShowMoreListener{
        int OnCount();
    }
    private OnShowMoreListener onShowMoreListener;
}
