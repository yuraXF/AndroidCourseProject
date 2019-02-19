package com.example.data_firebase.repository;

import com.example.data.entity.NoteEntity;
import com.example.data.mapper.NoteEntityDataMapper;
import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.model.Note;
import com.example.domain.repository.NotesRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import javax.inject.Inject;

public class FirebaseNoteRepositoryImpl implements NotesRepository {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private final NoteEntityDataMapper noteEntityDataMapper;

    @Inject
    public FirebaseNoteRepositoryImpl() {
        database = FirebaseDatabase.getInstance();
        myRef=database.getReference();
        noteEntityDataMapper = new NoteEntityDataMapper();
    }

    @Override
    public List<Note> getNotes() throws DataUnavailableException {

        return null;
    }

    @Override
    public void addNote(Note note) {
        NoteEntity noteEntity=noteEntityDataMapper.transformTo(note);
        myRef.setValue(noteEntity.getNoteId());
        myRef.child(noteEntity.getNoteId()).setValue(noteEntity);
    }

    @Override
    public void removeNote(String id) {

    }

    @Override
    public void editNote(Note note) {

    }

    @Override
    public Note getNoteById(String id) throws DataUnavailableException {
        return null;
    }

}
