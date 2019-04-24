package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.permission.pinchimageview.PinchImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.Logger;

public class ImageLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        PinchImageView imageView=findViewById(R.id.image_full);

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(configuration);

        String url=getIntent().getStringExtra("url");
        Logger.d(url);

        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(url,imageView);
    }
}
