package com.example.yura.todolist.mvvm.viewmodel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StatisticViewModelFactory implements ViewModelProvider.Factory {

    private final StatisticViewModel statisticViewModel;

    @Inject
    public StatisticViewModelFactory(StatisticViewModel statisticViewModel) {
        this.statisticViewModel = statisticViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) statisticViewModel;
    }
}
