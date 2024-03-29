package com.ramesh.mvvm.util.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {


    Scheduler computation();

    Scheduler io();

    Scheduler ui();

}
