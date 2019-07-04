
package com.ramesh.mvvm.di.component;



import com.ramesh.mvvm.di.PerActivity;
import com.ramesh.mvvm.di.module.ActivityModule;
import com.ramesh.mvvm.view.moviedetails.MovieDetailsActivity;
import com.ramesh.mvvm.view.recentmovie.MovieActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(MovieActivity activity);

    void inject(MovieDetailsActivity activity);
}
