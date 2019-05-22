package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;

import com.orhanobut.logger.Logger;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Logger.d("Main3 task ID =:"+getTaskId());
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getPreferences(MODE_PRIVATE);
                Editor editor=sp.edit();
                editor.putString("chen","cong");
                editor.apply();
            }
        });
    }
}
