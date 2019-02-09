package com.example.yura.todolist.mvvm.view.component;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("app:priority")
    public static void setPriority(MyView view, int priority) {
        view.setPriorityBackground(priority);
    }
}
