package com.example.permission.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.permission.R;
import com.example.permission.adapter.MyViewPageAdapter;
import com.example.permission.pinchimageview.PinchImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

public class MyDialog extends Dialog {
    private ProgressBar progressBar;
    private Context context;
    private View view;
    private ArrayList<Uri> list;
    private int position;
    private TextView textViewNumber;

    public MyDialog(Context context, int plane_Dialog, ArrayList<Uri> mlist, int position) {
        super(context,plane_Dialog);
        this.context = context;
        this.list=mlist;
        this.position=position;
    }

    public MyDialog getInstance(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_image_load, null);
        this.view = view;
        getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(view);
        final String uri="https://upload-images.jianshu.io/upload_images/1428688-96ade0e9ca88a8de.png?imageMogr2/auto-orient/";
        progressBar=view.findViewById(R.id.progress_bra);
        MyViewPageAdapter adapter=new MyViewPageAdapter(context,list,view);
        adapter.setOnPhotoClickListener(this::dismiss);
        ViewPager viewPager=view.findViewById(R.id.view_page);
        viewPager.setOffscreenPageLimit(list.size());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        textViewNumber=view.findViewById(R.id.view_page_number);
        textViewNumber.setText(position+1+"/"+list.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textViewNumber.setText(1+position+"/"+list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        PinchImageView imageView=view.findViewById(R.id.image_full);
//        loadImage(uri.toString(),imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getInstance().dismiss();
//            }
//        });


    }

    //防止全屏异常
    @Override
    protected void onStart() {
        super.onStart();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        this.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    public void loadImage(String url, ImageView imageView){
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher_background)
                .build();
        com.nostra13.universalimageloader.core.ImageLoader imageLoader= com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }
}
