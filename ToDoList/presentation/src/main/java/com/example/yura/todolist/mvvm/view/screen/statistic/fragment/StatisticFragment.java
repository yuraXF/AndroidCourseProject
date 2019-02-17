package com.example.yura.todolist.mvvm.view.screen.statistic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.StatisticFragmentBinding;
import com.example.yura.todolist.mvvm.viewmodel.StatisticViewModel;
import com.example.yura.todolist.mvvm.viewmodel.StatisticViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class StatisticFragment extends DaggerFragment {

    @Inject
    public NotesRepository notesRepository;

    @Inject
    public StatisticViewModelFactory statisticViewModelFactory;

    private StatisticViewModel statisticViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        statisticViewModel = ViewModelProviders
                .of(this, statisticViewModelFactory)
                .get(StatisticViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        StatisticFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.statistic_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setVm(statisticViewModel);
        View view = binding.getRoot();

        return view;

    }
}
