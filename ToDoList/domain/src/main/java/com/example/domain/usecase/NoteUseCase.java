package com.example.domain.usecase;

import com.example.domain.Note;
import com.example.domain.SortOperations;
import com.example.domain.SortType;
import com.example.domain.repository.NotesRepository;

import java.text.ParseException;
import java.util.ArrayList;

import javax.inject.Inject;

public class NoteUseCase {

    private NotesRepository notesRepository;
    private SortOperations sortOperations;

    @Inject
    public NoteUseCase(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
        sortOperations=new SortOperations();
    }

    public ArrayList<Note> getNotes(SortType sortType) throws ParseException {
        ArrayList<Note> notes=(ArrayList<Note>) notesRepository.getNotes();
        return sortOperations.doSorting(notes ,sortType);
    }

    public void removeNote(String id){
        notesRepository.removeNote(id);
    }

}
