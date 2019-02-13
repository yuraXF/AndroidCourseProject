package com.example.yura.todolist.di;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class MyApplication extends DaggerApplication {

    private ApplicationComponent applicationComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .setRepositoryModule(new RepositoryModule(this))
                .build();
        return applicationComponent;
    }
}