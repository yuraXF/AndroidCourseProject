package com.example.domain.usecase;

import com.example.domain.model.Note;
import com.example.domain.model.SortOperations;
import com.example.domain.model.SortType;
import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.repository.NotesRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class NoteInteractor {

    private NotesRepository notesRepository;
    private SortOperations sortOperations;

    @Inject
    public NoteInteractor(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
        sortOperations=new SortOperations();
    }

    public ArrayList<Note> getNotes(SortType sortType) throws DataUnavailableException {
        ArrayList<Note> notes=(ArrayList<Note>) notesRepository.getNotes();
        return sortOperations.doSorting(notes ,sortType);
    }

    public void removeNote(String id){
        notesRepository.removeNote(id);
    }


}
