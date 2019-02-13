package com.example.yura.todolist.di;

import com.example.yura.todolist.mvvm.view.activity.MainActivity;
import com.example.yura.todolist.mvvm.view.fragment.AddEditFragment;
import com.example.yura.todolist.mvvm.view.fragment.ShowNoteFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public interface UIModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();

    @ContributesAndroidInjector
    ShowNoteFragment ShowNoteFragment();

    @ContributesAndroidInjector
    AddEditFragment AddEditFragment();

}
