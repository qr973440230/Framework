package com.qr.core.framework.di.component;


import com.qr.core.framework.Application;
import com.qr.core.framework.di.modules.base.ApplicationModule;
import com.qr.core.framework.di.modules.base.BaseActivityModule;
import com.qr.core.framework.di.modules.base.BaseFragmentModule;
import com.qr.core.framework.di.modules.base.BaseServiceModule;
import com.qr.core.framework.di.modules.CacheModule;
import com.qr.core.framework.di.modules.WebServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        ApplicationModule.class,
        CacheModule.class,
        WebServiceModule.class,
        BaseActivityModule.class,
        BaseServiceModule.class,
        BaseFragmentModule.class})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(Application application);
}
