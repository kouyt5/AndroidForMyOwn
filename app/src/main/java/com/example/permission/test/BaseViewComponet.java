package com.example.permission.test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseViewComponet {

    public ImageView view;
    public TextView view2;
    public abstract void bindAllView(View itemview);
    public abstract void setData(String string);

}
