package com.example.themoviedbkk.modules;

import android.app.Application;

import com.example.themoviedbkk.database.DatabaseFilmsMemory;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Inject
    Application application;

    @Singleton
    @Provides
    DatabaseFilmsMemory provideDatabase(Application application) {
        // otwiera albo tworzy nową bazę danych
        return (new DatabaseFilmsMemory(application.getApplicationContext()).open());
    }
}
