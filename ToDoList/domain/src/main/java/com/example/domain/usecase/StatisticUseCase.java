package com.example.domain.usecase;

import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.model.Note;
import com.example.domain.model.Statistic;
import com.example.domain.repository.NotesRepository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class StatisticUseCase {

    private NotesRepository notesRepository;
    private Statistic statistic;

    @Inject
    public StatisticUseCase(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Statistic getStatistic() {
        try {
            List<Note> notes = notesRepository.getNotes();

            Integer lowPriorityStat = 0;
            Integer middlePriorityStat = 0;
            Integer highPriorityStat = 0;
            Integer endNoteStat = 0;
            Integer activNoteStat = 0;
            Date current_date = new Date();
            for (Note note : notes) {
                switch (note.getPriority()) {
                    case 0:
                        highPriorityStat++;
                        break;
                    case 1:
                        middlePriorityStat++;
                        break;
                    case 2:
                        lowPriorityStat++;
                        break;
                    default:
                        break;
                }

                if (note.getEndDate().compareTo(current_date) == -1) {
                    endNoteStat++;
                } else {
                    activNoteStat++;
                }
            }
            statistic = new Statistic();
            statistic.setLowPriorityStat(lowPriorityStat);
            statistic.setMiddlePriorityStat(middlePriorityStat);
            statistic.setHighPriorityStat(highPriorityStat);
            statistic.setEndNoteStat(endNoteStat);
            statistic.setActivNoteStat(activNoteStat);
            return statistic;
        } catch (DataUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    ;
}
