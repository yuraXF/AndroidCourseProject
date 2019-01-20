package com.example.data.entity;

import com.example.data.entity.mapper.NoteEntityJSONMapper;
import com.example.domain.Note;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class NotesEntityList {

    public static ArrayList<NoteEntity> notes;

    private NotesEntityList() {
    }

    public static ArrayList<NoteEntity> initsializeList(String notes_string){
        if (notes_string == "") {
            NotesEntityList.notes = new ArrayList<NoteEntity>();
        } else {
            NotesEntityList.notes = new ArrayList<NoteEntity>();
            NotesEntityList.notes.clear();
            NotesEntityList.notes = (ArrayList<NoteEntity>) new NoteEntityJSONMapper().transformNoteEntityCollection(notes_string);
        }
        sortNotesList();
        return NotesEntityList.notes;
    }

    public static void sortNotesList(){
        Collections.sort(NotesEntityList.notes, new Comparator<NoteEntity>() {
            @Override
            public int compare(NoteEntity o1, NoteEntity o2) {
                return o1.getPriority()<o2.getPriority()?-1:o1.getPriority()>o2.getPriority()?1:0;
            }
        });
    }

    public static NoteEntity findNoteEntityById(String id){
        for(NoteEntity noteEntity:NotesEntityList.notes){
            if(noteEntity.getNoteId().equals(id)){
                return noteEntity;
            }
        }
        return null;
    }

}
