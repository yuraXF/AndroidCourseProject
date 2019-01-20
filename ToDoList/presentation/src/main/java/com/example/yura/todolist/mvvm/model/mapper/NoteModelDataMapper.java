package com.example.yura.todolist.mvvm.model.mapper;

import com.example.domain.Note;
import com.example.yura.todolist.mvvm.model.NoteModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NoteModelDataMapper {

    public NoteModelDataMapper() {
    }

    public NoteModel transformTo(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final NoteModel noteModel = new NoteModel(note.getNoteId());
        noteModel.setPriority(note.getPriority());
        noteModel.setDescription(note.getDescription());
        noteModel.setTitle(note.getTitle());
        return noteModel;
    }

    public Collection<NoteModel> transformTo(Collection<Note> noteCollection) {
        Collection<NoteModel> noteModelCollection;
        if(noteCollection!=null && !noteCollection.isEmpty()){
            noteModelCollection=new ArrayList<>();
            for(Note note:noteCollection){
                noteModelCollection.add(transformTo(note));
            }
        }else{
            noteModelCollection=Collections.emptyList();
        }

        return noteModelCollection;
    }

    public Note transformFrom(NoteModel noteModel) {
        if (noteModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Note note = new Note(noteModel.getNoteId());
        note.setPriority(noteModel.getPriority());
        note.setDescription(noteModel.getDescription());
        note.setTitle(noteModel.getTitle());
        return note;
    }

    public Collection<Note> transformFrom(Collection<NoteModel> noteModelCollection) {
        Collection<Note> noteCollection;
        if(noteModelCollection!=null && !noteModelCollection.isEmpty()){
            noteCollection=new ArrayList<>();
            for(NoteModel noteModel:noteModelCollection){
                noteCollection.add(transformFrom(noteModel));
            }
        }else{
            noteCollection=Collections.emptyList();
        }

        return noteCollection;
    }

}
