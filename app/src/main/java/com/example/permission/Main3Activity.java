package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.permission.adapter.PictureRecycleAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {
    private volatile static Boolean piture_text_down = false;
    private List<String> mlist;//完整list
    private List<String> mlessList;//小于等于4个的list
    private PictureRecycleAdapter adapter;

    @BindView(R.id.recycle)
    RecyclerView recyclerView;
    @BindView(R.id.more)
    TextView textView;

    @OnClick(R.id.more)
    void clickMore() {
        if (piture_text_down) {
            int size=mlessList.size();
            for (int i = 4; i < size; i++)
                mlessList.remove(i);
            textView.setText("更多");
            piture_text_down = false;
        } else {
            if (mlist.size()<4) {
                textView.setText("");
                return;
            }
            mlessList.addAll(mlist.subList(4, mlist.size()));
            textView.setText("更少");
            piture_text_down = true;
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        Logger.d("Main3 task ID =:" + getTaskId());
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                Editor editor = sp.edit();
                editor.putString("chen", "cong");
                editor.apply();
                v.setVisibility(View.INVISIBLE);
            }
        });

        String url = "https://upload-images.jianshu.io/upload_images/654237-38c0eda0d2e3c348.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/690/format/webp";
        mlist = new ArrayList<String>(Arrays.asList(url, url, url, url, url));
        mlessList = new ArrayList<String>();
        for (int i = 0; i < (mlist.size() >= 4 ? 4 : mlist.size()); i++) {
            mlessList.add(mlist.get(i));
        }
        LinearLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new PictureRecycleAdapter(this, mlessList);
        recyclerView.setAdapter(adapter);
    }
}
