package com.example.yura.todolist.di;

import com.example.yura.todolist.mvvm.view.activity.MainActivity;
import com.example.yura.todolist.mvvm.view.fragment.AddEditFragment;
import com.example.yura.todolist.mvvm.view.fragment.ShowNoteFragment;

import dagger.Component;
import dagger.android.AndroidInjector;


@Component(modules = {UIModule.class, RepositoryModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

    void inject(MainActivity mainActivity);

    void inject(ShowNoteFragment showNoteFragment);

    void inject(AddEditFragment addEditFragment);

    @Component.Builder
    interface Builder {

        Builder setRepositoryModule(RepositoryModule repositoryModule);

        ApplicationComponent build();

    }
}
