package com.qr.core.framework.di.component;

import com.qr.core.framework.mvvm.view.base.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment>{
    }
}
