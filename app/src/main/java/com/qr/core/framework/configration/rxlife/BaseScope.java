package com.qr.core.framework.configration.rxlife;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.rxjava.rxlife.Scope;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseScope implements Scope, LifecycleEventObserver {
    private CompositeDisposable compositeDisposable;
    public BaseScope(LifecycleOwner owner){
        owner.getLifecycle().addObserver(this);
    }
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
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if(event == Lifecycle.Event.ON_DESTROY){
            source.getLifecycle().removeObserver(this);
            if(compositeDisposable != null){
                compositeDisposable.dispose();
            }
        }
    }
}
