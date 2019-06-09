package com.qr.core.framework.mvvm.view.activity.main;

import android.os.Bundle;

import com.qr.core.framework.R;
import com.qr.core.framework.mvvm.view.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutRes() {
        return R.layout.main_activity;
    }
}
