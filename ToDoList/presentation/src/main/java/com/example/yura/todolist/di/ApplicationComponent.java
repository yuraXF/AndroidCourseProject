package com.example.yura.todolist.di;

import dagger.Component;
import dagger.android.AndroidInjector;


@Component(modules = {
        UIModule.class,
        RepositoryModule.class
}
)
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        Builder setRepositoryModule(RepositoryModule repositoryModule);

        ApplicationComponent build();

    }
}
