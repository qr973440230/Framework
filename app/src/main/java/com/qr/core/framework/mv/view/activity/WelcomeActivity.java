package com.qr.core.framework.mv.view.activity;

import android.os.Bundle;

import com.qr.core.framework.R;
import com.qr.core.framework.mv.view.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_welcome;
    }
}
