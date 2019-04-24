package com.example.permission.test;

import android.view.View;

import com.example.permission.R;

public class SeondView extends BaseViewComponet {
    @Override
    public void bindAllView(View view) {
        this.view = view.findViewById(R.id.image);
        this.view2 = view.findViewById(R.id.text_image);
    }
    public void setData(String string){
        this.view.setImageResource(R.mipmap.ic_launcher);
        this.view2.setText(string);
    }
}
