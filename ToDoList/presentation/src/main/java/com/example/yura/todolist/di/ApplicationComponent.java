package com.example.yura.todolist.di;

import android.content.Context;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {
        DaggerAndroidModule.class,
        RepositoryModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        ApplicationComponent build();

    }
}
