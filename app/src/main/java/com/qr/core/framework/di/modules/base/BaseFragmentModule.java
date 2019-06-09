package com.qr.core.framework.di.modules.base;

import com.qr.core.framework.di.component.BaseFragmentComponent;
import com.qr.core.framework.mvvm.view.ui.welcome.WelcomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseFragmentComponent.class})
public abstract class BaseFragmentModule {

    @ContributesAndroidInjector
    abstract WelcomeFragment welcomeFragment();
}
