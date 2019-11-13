package com.qr.core.framework.mvp.main;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.qr.core.framework.R;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
