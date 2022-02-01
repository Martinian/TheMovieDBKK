package com.example.themoviedbkk.components;

import com.example.themoviedbkk.TheMovieDBKKApplication;
import com.example.themoviedbkk.modules.AppModule;
import com.example.themoviedbkk.modules.BuildersModule;
import com.example.themoviedbkk.modules.DatabaseModule;
import com.example.themoviedbkk.modules.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = { AndroidSupportInjectionModule.class, BuildersModule.class,AppModule.class, NetModule.class, DatabaseModule.class})
public interface TheMovieDBKKApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(TheMovieDBKKApplication application);
        Builder appModule(AppModule appModule);
        TheMovieDBKKApplicationComponent build();

    }
    void inject(TheMovieDBKKApplication app);


}
