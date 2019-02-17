package com.example.domain.repository;

import com.example.domain.model.Note;
import com.example.domain.exceptions.DataUnavailableException;

import java.util.List;

public interface NotesRepository {
    List<Note> getNotes() throws DataUnavailableException;
    void addNote(Note note);
    void removeNote(String id);
    void editNote(Note note);
    Note getNoteById(String id) throws DataUnavailableException;
}
