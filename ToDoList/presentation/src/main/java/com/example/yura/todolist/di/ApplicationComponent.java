package com.example.yura.todolist.di;

<<<<<<< HEAD
import android.content.Context;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {
        DaggerAndroidModule.class,
        RepositoryModule.class})
=======
import dagger.Component;
import dagger.android.AndroidInjector;


@Component(modules = {
        UIModule.class,
        RepositoryModule.class
}
)
>>>>>>> 9620309a6d30928005ea243a7ae6927e5172252f
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        ApplicationComponent build();

    }
}
