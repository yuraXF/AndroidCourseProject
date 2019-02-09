package com.example.yura.todolist.mvvm.model.mapper;

import com.example.data.entity.mapper.DateMapper;
import com.example.domain.Note;
import com.example.yura.todolist.mvvm.model.NoteModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class NoteModelDataMapper {

    private DateMapper dateMapper;

    public NoteModelDataMapper() {
        dateMapper=new DateMapper();
    }

    public NoteModel transformTo(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final NoteModel noteModel = new NoteModel(note.getNoteId());
        noteModel.setPriority(note.getPriority());
        noteModel.setDescription(note.getDescription());
        noteModel.setTitle(note.getTitle());
        noteModel.setEditDate(dateMapper.transformFromDate(note.getEditDate()));
        if (!note.getEndDate().equals(new Date(0))) {
            noteModel.setEndDate(dateMapper.transformFromDate(note.getEndDate()));
        }else
        {
            noteModel.setEndDate("");
        }
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

    public Note transformFrom(NoteModel noteModel) throws ParseException {
        if (noteModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Note note = new Note(noteModel.getNoteId());
        note.setPriority(noteModel.getPriority());
        note.setDescription(noteModel.getDescription());
        note.setTitle(noteModel.getTitle());
        note.setEditDate(dateMapper.transformToDate(noteModel.getEditDate()));
        if (!noteModel.getEndDate().isEmpty()) {
            note.setEndDate(dateMapper.transformToDate(noteModel.getEndDate()));
        }else{
            note.setEndDate(new Date(0));
        }
        return note;
    }

    public Collection<Note> transformFrom(Collection<NoteModel> noteModelCollection) throws ParseException {
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
