package com.example.yura.todolist.mvvm.view.adapter;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = "items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        //noinspection unchecked
        ((ItemsAdapter<T>) recyclerView.getAdapter()).setItems(items);
    }

}
