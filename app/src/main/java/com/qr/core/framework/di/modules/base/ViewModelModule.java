package com.qr.core.framework.di.modules.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.qr.core.framework.configration.viewmodel.ViewModelFactory;
import com.qr.core.framework.di.annotation.ViewModelKey;
import com.qr.core.framework.mvvm.view.ui.welcome.WelcomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel.class)
    abstract ViewModel welcomeViewModel(WelcomeViewModel welcomeViewModel);

    @Binds
    abstract ViewModelProvider.Factory viewModelFactory(ViewModelFactory viewModelFactory);
}
