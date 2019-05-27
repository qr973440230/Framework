package com.qr.core.framework.di.modules.base;

import androidx.lifecycle.ViewModelProvider;

import com.qr.core.framework.configration.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
