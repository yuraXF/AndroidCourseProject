package com.example.yura.todolist.di;

import com.example.yura.todolist.mvvm.view.screen.note_list.activity.MainActivity;
import com.example.yura.todolist.mvvm.view.screen.note_add_edit.fragment.AddEditFragment;
import com.example.yura.todolist.mvvm.view.screen.note_list.fragment.ShowNoteFragment;
import com.example.yura.todolist.mvvm.view.screen.statistic.fragment.StatisticFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public interface DaggerAndroidModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();

    @ContributesAndroidInjector
    ShowNoteFragment showNoteFragment();

    @ContributesAndroidInjector
    AddEditFragment addEditFragment();

    @ContributesAndroidInjector
    StatisticFragment statisticFragment();

}
