package com.example.data.mapper;

import com.example.data.entity.NoteEntity;
import com.example.domain.model.Note;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class NoteEntityDataMapper {

    private DateMapper dateMapper;

    public NoteEntityDataMapper() {
        dateMapper=new DateMapper();
    }

    public Note transformFrom(NoteEntity noteEntity) throws ParseException {
        Note note=null;
        if (noteEntity != null) {
            note = new Note(noteEntity.getNoteId());
            note.setPriority(noteEntity.getPriority());
            note.setDescription(noteEntity.getDescription());
            note.setTitle(noteEntity.getTitle());
            note.setEditDate(dateMapper.transformToDate(noteEntity.getEditDate()));
            if(!noteEntity.getEndDate().isEmpty()) {
                note.setEndDate(dateMapper.transformToDate(noteEntity.getEndDate()));
            }else {
                note.setEndDate(new Date(0));
            }
        }
        return note;
    }

    public List<Note> transformFrom(Collection<NoteEntity> noteEntityCollection) throws ParseException {
        final List<Note> noteList = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntityCollection) {
            final Note note = transformFrom(noteEntity);
            if (note != null) {
                noteList.add(note);
            }
        }
        return noteList;
    }

    public NoteEntity transformTo(Note note) {
        NoteEntity noteEntity=null;
        if (note != null) {
            noteEntity = new NoteEntity();
            noteEntity.setNoteId(note.getNoteId());
            noteEntity.setPriority(note.getPriority());
            noteEntity.setDescription(note.getDescription());
            noteEntity.setTitle(note.getTitle());
            noteEntity.setEditDate(dateMapper.transformFromDate(note.getEditDate()));
            if (!note.getEndDate().equals(new Date(0))) {
                noteEntity.setEndDate(dateMapper.transformFromDate(note.getEndDate()));
            }else{
                noteEntity.setEndDate("");
            }
        }
        return noteEntity;
    }

    public List<NoteEntity> transformTo(Collection<Note> noteCollection) {
        final List<NoteEntity> noteEntityList = new ArrayList<>();
        for (Note note : noteCollection) {
            final NoteEntity noteEntity = transformTo(note);
            if (noteEntity != null) {
                noteEntityList.add(noteEntity);
            }
        }
        return noteEntityList;
    }

}
