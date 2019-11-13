package com.qr.core.framework.di.modules.base;

import com.qr.core.framework.di.annotations.ActivityScope;
import com.qr.core.framework.mvp.main.MainActivity;
import com.qr.core.framework.mvp.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/***
 *     @ActivityScope
 *     @ContributesAndroidInjector
 *     abstract SplashActivity splashActivity();
 */

@Module
public abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();
}
