package com.example.domain.repository;

import com.example.domain.Note;

import java.text.ParseException;
import java.util.List;

public interface NotesRepository {
    List<Note> getNotes() throws ParseException;
    void addNote(Note note);
    void removeNote(String id);
    void editNote(Note note);
    Note getNoteById(String id) throws ParseException;
}
