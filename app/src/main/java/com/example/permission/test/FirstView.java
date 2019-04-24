package com.example.permission.test;

import android.view.View;

import com.example.permission.R;

public class FirstView extends BaseViewComponet {
    @Override
    public void bindAllView(View itemview) {
        view2=itemview.findViewById(R.id.text);
    }

    @Override
    public void setData(String string) {
        this.view2.setText(string);
    }

}
