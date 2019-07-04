package com.ramesh.mvvm;

import android.app.Application;
import android.content.res.Configuration;

import com.ramesh.mvvm.di.component.ApplicationComponent;
import com.ramesh.mvvm.di.component.DaggerApplicationComponent;
import com.ramesh.mvvm.di.module.ApplicationModule;
import com.ramesh.mvvm.model.data.DataManager;

import javax.inject.Inject;

public class App  extends Application {


    @Inject
    DataManager mDataManager;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}
