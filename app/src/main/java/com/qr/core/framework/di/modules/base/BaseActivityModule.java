package com.qr.core.framework.di.modules.base;

import com.qr.core.framework.di.component.BaseActivityComponent;
import com.qr.core.framework.mv.view.activity.WelcomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class BaseActivityModule {

    @ContributesAndroidInjector
    abstract WelcomeActivity welcomeActivity();
}
