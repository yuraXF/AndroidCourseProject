package com.example.yura.todolist.mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddEditViewModelFactory implements ViewModelProvider.Factory {

    private final AddEditNoteViewModel addEditNoteViewModel;

    public AddEditViewModelFactory(AddEditNoteViewModel addEditNoteViewModel) {
        this.addEditNoteViewModel = addEditNoteViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) addEditNoteViewModel;
    }
}
