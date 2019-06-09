package com.qr.core.framework.configration.rxlife;

import androidx.lifecycle.ViewModel;

import com.rxjava.rxlife.Scope;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ViewModelScope extends ViewModel implements Scope {
    private CompositeDisposable compositeDisposable;

    @Override
    public void onScopeStart(Disposable d) {
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(d);
    }

    @Override
    public void onScopeEnd() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }
}
