package com.qr.core.framework.di.component;


import com.qr.core.framework.Application;
import com.qr.core.framework.di.modules.base.ActivityModule;
import com.qr.core.framework.di.modules.base.GlobalModule;
import com.qr.core.framework.di.modules.base.FragmentModule;
import com.qr.core.framework.di.modules.base.ServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        GlobalModule.class,
        ActivityModule.class,
        FragmentModule.class,
        ServiceModule.class,
})
public interface GlobalComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        GlobalComponent build();
    }

    void inject(Application application);
}
