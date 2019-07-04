
package com.ramesh.mvvm.di.component;

import android.app.Application;
import android.content.Context;


import com.ramesh.mvvm.App;
import com.ramesh.mvvm.di.ApplicationContext;
import com.ramesh.mvvm.di.module.ApplicationModule;
import com.ramesh.mvvm.model.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}