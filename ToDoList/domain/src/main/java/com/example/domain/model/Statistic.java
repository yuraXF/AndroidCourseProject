package com.example.domain.model;

import javax.inject.Inject;

public class Statistic {
    private Integer lowPriorityStat;
    private Integer middlePriorityStat;
    private Integer highPriorityStat;
    private Integer endNoteStat;
    private Integer activNoteStat;

    @Inject
    public Statistic() {
    }

    public Integer getLowPriorityStat() {
        return lowPriorityStat;
    }

    public void setLowPriorityStat(Integer lowPriorityStat) {
        this.lowPriorityStat = lowPriorityStat;
    }

    public Integer getMiddlePriorityStat() {
        return middlePriorityStat;
    }

    public void setMiddlePriorityStat(Integer middlePriorityStat) {
        this.middlePriorityStat = middlePriorityStat;
    }

    public Integer getHighPriorityStat() {
        return highPriorityStat;
    }

    public void setHighPriorityStat(Integer highPriorityStat) {
        this.highPriorityStat = highPriorityStat;
    }

    public Integer getEndNoteStat() {
        return endNoteStat;
    }

    public void setEndNoteStat(Integer endNoteStat) {
        this.endNoteStat = endNoteStat;
    }

    public Integer getActivNoteStat() {
        return activNoteStat;
    }

    public void setActivNoteStat(Integer activNoteStat) {
        this.activNoteStat = activNoteStat;
    }
}
