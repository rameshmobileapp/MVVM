package com.ramesh.mvvm.view.base;

import androidx.lifecycle.ViewModel;


import com.ramesh.mvvm.model.data.DataManager;
import com.ramesh.mvvm.util.NetworkUtils;
import com.ramesh.mvvm.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    private DataManager dataManager;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private NetworkUtils networkUtils;


    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, NetworkUtils networkUtils) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.networkUtils = networkUtils;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }
}
