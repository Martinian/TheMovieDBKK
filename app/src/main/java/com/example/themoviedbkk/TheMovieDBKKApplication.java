package com.example.themoviedbkk;

import android.app.Activity;
import android.app.Application;

import com.example.themoviedbkk.components.DaggerTheMovieDBKKApplicationComponent;
import com.example.themoviedbkk.components.TheMovieDBKKApplicationComponent;
import com.example.themoviedbkk.modules.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class TheMovieDBKKApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerTheMovieDBKKApplicationComponent
                .builder().appModule(new AppModule(this))
                .application(this)
                .build()
                .inject(this);

    }



    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
