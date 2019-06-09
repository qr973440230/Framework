package com.qr.core.framework.mvvm.view.ui.welcome;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.qr.core.framework.R;
import com.qr.core.framework.configration.viewmodel.ViewModelFactory;
import com.qr.core.framework.mvvm.view.base.BaseFragment;

import javax.inject.Inject;

public class WelcomeFragment extends BaseFragment {
    private WelcomeViewModel mViewModel;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(WelcomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    protected int layoutRes() {
        return R.layout.welcome_fragment;
    }
}
