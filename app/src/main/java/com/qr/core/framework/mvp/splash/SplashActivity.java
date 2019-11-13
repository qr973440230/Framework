package com.qr.core.framework.mvp.splash;

import android.content.Intent;
import android.os.Bundle;

import com.qr.core.framework.R;
import com.qr.core.framework.mvp.main.MainActivity;
import com.rxjava.rxlife.RxLife;

import java.util.concurrent.TimeUnit;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashActivity extends DaggerAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.timer(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).as(RxLife.as(this)).subscribe(aLong -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
