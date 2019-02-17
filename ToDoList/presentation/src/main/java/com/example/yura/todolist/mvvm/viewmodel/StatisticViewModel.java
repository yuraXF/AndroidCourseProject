package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.model.Statistic;
import com.example.domain.usecase.StatisticUseCase;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StatisticViewModel extends ViewModel {
    private Statistic notesStatistic;
    private StatisticUseCase statisticUseCase;
    public MutableLiveData<String> lowPriorityStat=new MutableLiveData<>();
    public MutableLiveData<String> middlePriorityStat=new MutableLiveData<>();
    public MutableLiveData<String> highPriorityStat=new MutableLiveData<>();
    public MutableLiveData<String> endNoteStat=new MutableLiveData<>();
    public MutableLiveData<String> activNoteStat=new MutableLiveData<>();

    @Inject
    public StatisticViewModel(StatisticUseCase statisticUseCase) {
        this.statisticUseCase = statisticUseCase;
        notesStatistic=statisticUseCase.getStatistic();
        lowPriorityStat.postValue(notesStatistic.getLowPriorityStat().toString());
        middlePriorityStat.postValue(notesStatistic.getMiddlePriorityStat().toString());
        highPriorityStat.postValue(notesStatistic.getHighPriorityStat().toString());
        endNoteStat.postValue(notesStatistic.getEndNoteStat().toString());
        activNoteStat.postValue(notesStatistic.getActivNoteStat().toString());
    }

}
