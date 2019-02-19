package com.example.yura.todolist.mvvm.viewmodel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SingUpInViewModelFactory implements ViewModelProvider.Factory {

    private final SingUpInViewModel singUpInViewModel;

    @Inject
    public SingUpInViewModelFactory(SingUpInViewModel singUpInViewModel) {
        this.singUpInViewModel = singUpInViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) singUpInViewModel;
    }
}
