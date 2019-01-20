package com.example.domain.repository;

import com.example.domain.Note;

import java.util.ArrayList;
import java.util.List;

public interface NotesRepository {
    List<Note> getNotes();
    void addNote(Note note);
    void removeNote(String id);
    void editNote(Note note);
}
