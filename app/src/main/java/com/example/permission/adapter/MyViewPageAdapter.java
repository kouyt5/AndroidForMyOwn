package com.example.permission.adapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.permission.R;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class MyViewPageAdapter extends PagerAdapter {
    private ArrayList<Uri> mlist;
    private Context mcontext;
    private View rootView;
    public MyViewPageAdapter(Context context, ArrayList<Uri> list,View rootView){
        this.mcontext=context;
        this.mlist=list;
        this.rootView=rootView;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LinearLayout view= (LinearLayout)LayoutInflater.from(mcontext).inflate(R.layout.photoview,null);
        PhotoView photoView=view.findViewById(R.id.photo_view);
        ProgressBar progressBar=rootView.findViewById(R.id.progress_bra);
        photoView.setOnPhotoTapListener((view1, x, y) -> onPhotoClickListener.onClick());
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher_background)
                .build();
        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(mlist.get(position).toString(), photoView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
                Logger.d("start loadding");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.INVISIBLE);
                Logger.d("load error");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.INVISIBLE);
                Logger.d("load complete");

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.INVISIBLE);
                Logger.d("load cancell");

            }
        });
        container.addView(view);
        return view;
    }

    public interface OnPhotoClickListener {
        void onClick();
    }

    private OnPhotoClickListener onPhotoClickListener;

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener){
        this.onPhotoClickListener=onPhotoClickListener;
    }
}
