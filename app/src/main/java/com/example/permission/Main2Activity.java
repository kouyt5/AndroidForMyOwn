package com.example.permission;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
//import cn.jzvd.Jzvd;
//import cn.jzvd.JzvdStd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.permission.listener.VideoListener;
import com.example.permission.mydialog.MyDialog;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //防止弹出dialog后状态栏异常
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        loadGSVUVideo9Player();
        loadMultiPictureView();
    }


    public void YCVideoPlayerLibLoad() {
//        VideoPlayer videoPlayer1=findViewById(R.id.video_player);
//        //设置播放类型
//// IjkPlayer or MediaPlayer
//        videoPlayer1.setPlayerType(VideoPlayer.TYPE_NATIVE);
////网络视频地址
//        String videoUrl = "https://github.com/kouyt5/cc/blob/master/keaton.mp4?raw=true";
////设置视频地址和请求头部
//        videoPlayer1.setUp(videoUrl, null);
////是否从上一次的位置继续播放
//        videoPlayer1.continueFromLastPosition(true);
////设置播放速度
//        videoPlayer1.setSpeed(1.0f);
////创建视频控制器
//        VideoPlayerController controller = new VideoPlayerController(this);
//        controller.setTitle("办快来围观拉，自定义视频播放器可以播放视频拉");
////设置视频时长
//        controller.setLength(98000);
////设置5秒不操作后则隐藏头部和底部布局视图
//        controller.setHideTime(5000);
////controller.setImage(R.drawable.image_default);
//        //ImageUtil.loadImgByPicasso(this, R.drawable.image_default, R.drawable.image_default, controller.imageView());
////设置视频控制器
//        videoPlayer1.setController(controller);
    }

    public void loadJZVideoPlayer() {
//        String videoUrl2 = "https://github.com/kouyt5/cc/blob/master/200%20(195).avi?raw=true";
//        String videoUrl = "https://github.com/kouyt5/cc/blob/master/keaton.mp4?raw=true";
//        JzvdStd jzvdStd = (JzvdStd) findViewById(R.id.jz_video_player);
//        jzvdStd.setUp(videoUrl2
//                , "饺子闭眼睛", Jzvd.SCREEN_WINDOW_NORMAL);
    }

    public void loadMultiPictureView(){
        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
                Glide.with(imageView.getContext())
                        .load(uri)
                        .into(imageView);
            }
        });
        MultiPictureView multiPictureView = findViewById(R.id.mult_picture);
        final Uri uri = Uri.parse("https://upload-images.jianshu.io/upload_images/1428688-96ade0e9ca88a8de.png?imageMogr2/auto-orient/");
        multiPictureView.addItem(uri);
        multiPictureView.addItem(uri);
        multiPictureView.addItem(uri);

        multiPictureView.setItemClickCallback(new MultiPictureView.ItemClickCallback() {
            @Override
            public void onItemClicked(@NotNull View view, int i, @NotNull ArrayList<Uri> arrayList) {
               showMyDialog(arrayList,i);
            }
        });
    }

    public void showMyDialog(ArrayList<Uri> list,int position){
        MyDialog dialog = new MyDialog(Main2Activity.this, R.style.Plane_Dialog,list,position);
        dialog.show();
    }

    public void loadGSVUVideo9Player() {

        View view= LayoutInflater.from(this).inflate(R.layout.item_video,findViewById(R.id.activity_main2),true);

        String video2=" http://192.168.0.25:9000/bucket1/1234.avi?Expires=1555552384&AccessKeyId=XLfUoMQHqgABQZCa&" +
                "Signature=5fd96d339b3937b6ed6f097b2c4a060821abcee9&security-token=40852cb9744f4625873af950d0057af3";
        String video1 = "https://github.com/kouyt5/cc/blob/master/200%20(195).avi?raw=true";
        String video3 = "https://github.com/kouyt5/cc/blob/master/keaton.mp4?raw=true";
        videoPlayer =view.findViewById(R.id.gsyv_video_player);
        videoPlayer.setUp(video3, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        //选取当前第一帧作为图片
        Glide.with(this)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1)
                                .centerCrop()
                )
                .load(video3)
                .into(imageView);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("you click full screen");
                if (orientationUtils.getIsLand()!=1) {
                    videoPlayer.getBackButton().setVisibility(View.VISIBLE);
                    orientationUtils.resolveByClick();
                    Logger.d("全屏");
                }

                if(videoPlayer.getBackButton().isShown()){
                    Logger.d("back is exist");
                };
                videoPlayer.startWindowFullscreen(Main2Activity.this, true, true);
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Logger.d("click back");
                onBackPressed();
            }
        });

        //videoPlayer.startPlayLogic();
        videoPlayer.setVideoAllCallBack(new VideoListener() {

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                Logger.d("quit full");
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
                //videoPlayer.getBackButton().setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        Logger.d("on BackPress click");
        //先返回正常状态
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }
}