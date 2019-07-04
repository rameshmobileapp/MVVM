
package com.ramesh.mvvm.di.component;



import com.ramesh.mvvm.di.PerService;
import com.ramesh.mvvm.di.module.ServiceModule;
import com.ramesh.mvvm.service.SyncService;

import dagger.Component;


@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
