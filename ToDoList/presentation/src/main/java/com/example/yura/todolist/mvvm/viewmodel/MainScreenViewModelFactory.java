package com.example.yura.todolist.mvvm.viewmodel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainScreenViewModelFactory implements ViewModelProvider.Factory{

    private final MainScreenViewModel mainScreenViewModel;

    @Inject
    public MainScreenViewModelFactory(MainScreenViewModel mainScreenViewModel) {
        this.mainScreenViewModel = mainScreenViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) mainScreenViewModel;
    }

}
