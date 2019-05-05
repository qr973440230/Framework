package com.qr.core.framework.di.component;

import com.qr.core.framework.mv.view.base.BaseService;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseServiceComponent extends AndroidInjector<BaseService> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseService>{
    }
}
