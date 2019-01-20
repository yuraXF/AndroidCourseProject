package com.example.data.entity.mapper;

import com.example.data.entity.NoteEntity;
import com.example.domain.Note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoteEntityDataMapper {

    public NoteEntityDataMapper() {
    }

    public Note transformFrom(NoteEntity noteEntity) {
        Note note=null;
        if (noteEntity != null) {
            note = new Note(noteEntity.getNoteId());
            note.setPriority(noteEntity.getPriority());
            note.setDescription(noteEntity.getDescription());
            note.setTitle(noteEntity.getTitle());
        }
        return note;
    }

    public List<Note> transformFrom(Collection<NoteEntity> noteEntityCollection) {
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
