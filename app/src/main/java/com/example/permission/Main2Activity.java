package com.example.permission;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
//import cn.jzvd.Jzvd;
//import cn.jzvd.JzvdStd;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.permission.listener.VideoListener;
import com.example.permission.mydialog.MyDialog;
import com.example.permission.mylistener.MyVideoAllCallBack;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */

public class Main2Activity extends AppCompatActivity {
    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 200);
        }
        //防止弹出dialog后状态栏异常
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        loadGSYVideoPlayer();
        String versionName=null;
        try {
            versionName=getPackageManager().getPackageInfo("com.example.permission",PackageManager.GET_ACTIVITIES).versionName;
        }catch (Exception e){

        }
        Toast.makeText(this,versionName,Toast.LENGTH_LONG).show();
        //loadGSVUVideo9Player();
        loadMultiPictureView();
        showDialogSex();
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

    public void loadMultiPictureView() {
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

        multiPictureView.setItemClickCallback((view, i, arrayList) -> showMyDialog(arrayList, i));
    }

    public void showMyDialog(ArrayList<Uri> list, int position) {
        MyDialog dialog = new MyDialog(Main2Activity.this, R.style.Plane_Dialog, list, position);
        dialog.show();
    }

    public void loadGSVUVideo9Player() {

        View view = LayoutInflater.from(this).inflate(R.layout.item_video, findViewById(R.id.activity_main2), true);

        String video2 = " http://192.168.0.25:9000/bucket1/1234.avi?Expires=1555552384&AccessKeyId=XLfUoMQHqgABQZCa&" +
                "Signature=5fd96d339b3937b6ed6f097b2c4a060821abcee9&security-token=40852cb9744f4625873af950d0057af3";
        String video1 = "https://github.com/kouyt5/cc/blob/master/200%20(195).avi?raw=true";
        String video3 = "https://github.com/kouyt5/cc/blob/master/keaton.mp4?raw=true";
        videoPlayer = view.findViewById(R.id.gsyv_video_player);
        videoPlayer.setUp(video3, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        //选取当前第一帧作为图片
        Glide.with(this)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(3000)
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
        orientationUtils.setEnable(false);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(v -> {
            Logger.d("you click full screen");
            if (orientationUtils.getIsLand() != 1) {
                videoPlayer.getBackButton().setVisibility(View.VISIBLE);
                orientationUtils.resolveByClick();
                Logger.d("全屏");
            }
            videoPlayer.startWindowFullscreen(Main2Activity.this, true, true);
            if (videoPlayer.getBackButton().isShown()) {
                Logger.d("back is exist");
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
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

    public void loadGSYVideoPlayer() {
        String url = "https://github.com/kouyt5/cc/blob/master/keaton.mp4?raw=true";
        String localUrl = Environment.getExternalStorageDirectory().getPath() + "/Download/1234.avi";
        if (verfryFileIsExit(localUrl)) {
            url = localUrl;
            Logger.d("exit");
        }
        View view = LayoutInflater.from(this).inflate(R.layout.item_video, findViewById(R.id.activity_main2), true);
        videoPlayer = view.findViewById(R.id.gsyv_video_player);
        videoPlayer.setVideoAllCallBack(new MyVideoAllCallBack() {
            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });
        ImageView imageView = new ImageView(this);
        loadCover(imageView, url);
        GSYVideoOptionBuilder builder = new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)//打开动画
                .setNeedLockFull(true)
                .setSeekRatio(1);
        initVideo();
        builder.build(videoPlayer);

    }

    public void initVideo() {
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        if (videoPlayer.getFullscreenButton() != null) {
            videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orientationUtils.getIsLand() != 1) {
                        //直接横屏
                        orientationUtils.resolveByClick();
                    }
                    //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                    videoPlayer.startWindowFullscreen(Main2Activity.this, true, true);

                }
            });
        }
    }

    private void loadCover(ImageView imageView, String url) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        Glide.with(this.getApplicationContext())
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1)
                                .centerCrop()
                                .error(R.mipmap.ic_launcher)
                                .placeholder(R.mipmap.ic_launcher))
                .load(url)
                .into(imageView);
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils, true, true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
        Logger.d("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
        Logger.d("OnResume");
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

    private boolean verfryFileIsExit(String s) {

        File file = new File(s);
        String c = getExternalFilesDir(null).getAbsolutePath();
        File files = new File(c + "/chen");
        if (!files.exists())
            files.mkdir();
        String u = getExternalCacheDir().getAbsolutePath();
        String i = getFilesDir().getAbsolutePath();
        Logger.d(c + "/n" + u + "/n" + i);
        return file.exists();

    }

    public void showDialogSex(){
        String[] sexs=new String[]{"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(Main2Activity.this,sexs[which],Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
