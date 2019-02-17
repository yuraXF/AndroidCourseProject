package com.example.yura.todolist.mvvm.viewmodel;

import androidx.annotation.Nullable;

public class Event<T> {

    private boolean hasBeenHandled = false;
    private T data;

    public Event(T data) {
        this.data = data;
    }

    @Nullable
    public synchronized T peekContentIfNotHandled() {
        if (!hasBeenHandled) {
            hasBeenHandled = true;
            return data;
        } else {
            return null;
        }
    }
}