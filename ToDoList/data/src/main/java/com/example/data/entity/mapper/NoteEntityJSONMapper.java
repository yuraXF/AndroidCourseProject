package com.example.data.entity.mapper;

import com.example.data.entity.NotesEntityList;
import com.example.data.entity.NoteEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NoteEntityJSONMapper {

    private final Gson gson;

    public NoteEntityJSONMapper() {
        this.gson = new Gson();
    }

    public List<NoteEntity> transformNoteEntityCollection(String noteJsonResponse) throws JsonSyntaxException {
        final Type noteEntityType = new TypeToken<List<NoteEntity>>() {}.getType();
        return this.gson.fromJson(noteJsonResponse, noteEntityType);
    }

    public String transformNoteEntityCollectionToString(List<NoteEntity> notes){
       return new Gson().toJson(NotesEntityList.notes);
    }
}
