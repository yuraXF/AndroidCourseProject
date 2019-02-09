package com.example.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortOperations {

    public SortOperations() {
    }

    public ArrayList<Note> doSorting(ArrayList<Note> notes, final SortType sortType) {
        switch (sortType) {
            case PRIORITY:
                Collections.sort(notes, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getPriority() < o2.getPriority() ? -1 : o1.getPriority() > o2.getPriority() ? 1 : 0;
                    }
                });
                break;
            case TITLE:
                Collections.sort(notes, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                });
                break;
            case EDIT_DATE:
                Collections.sort(notes, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getEditDate().compareTo(o2.getEditDate());
                    }
                });
                break;
            case END_DATE:
                Collections.sort(notes, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getEndDate().compareTo(o2.getEndDate());
                    }
                });
                break;
            default:
                Collections.sort(notes, new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o1.getPriority() < o2.getPriority() ? -1 : o1.getPriority() > o2.getPriority() ? 1 : 0;
                    }
                });
                break;
        }
        return notes;
    }
}
