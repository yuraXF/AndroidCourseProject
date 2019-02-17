package com.example.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.data.entity.NoteEntity;
import com.example.migrations.ToDoListContract;
import com.example.data.mapper.NoteEntityDataMapper;
import com.example.domain.model.Note;
import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.repository.NotesRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SqLiteNoteRepositoryImpl implements NotesRepository {
    private final NoteEntityDataMapper noteEntityDataMapper;
    private final DatabaseHelper databaseHelper;

    @Inject
    public SqLiteNoteRepositoryImpl(Context context) {
        noteEntityDataMapper=new NoteEntityDataMapper();
        databaseHelper=new DatabaseHelper(context);
    }

    @Override
    public List<Note> getNotes() throws DataUnavailableException {
        SQLiteDatabase database=databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoListContract.SqliteNoteEntity.TABLE_NAME,null,null,null,null,null,ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        int idColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_ID);
        int titleColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE);
        int descriptionColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION);
        int priorityColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        int editDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_EDIT_DATE);
        int endDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_END_DATE);
        List<NoteEntity> notes=new ArrayList<NoteEntity>();
        while (cursor.moveToNext()) {
            NoteEntity noteEntity=new NoteEntity();
            noteEntity.setNoteId(cursor.getString(idColumnIndex));
            noteEntity.setTitle(cursor.getString(titleColumnIndex));
            noteEntity.setDescription(cursor.getString(descriptionColumnIndex));
            noteEntity.setPriority(cursor.getInt(priorityColumnIndex));
            noteEntity.setEditDate(cursor.getString(editDateColumnIndex));
            noteEntity.setEndDate(cursor.getString(endDateColumnIndex));
            notes.add(noteEntity);
        }
        cursor.close();
        try {
            return noteEntityDataMapper.transformFrom(notes);
        } catch (ParseException e) {
            throw new DataUnavailableException();
        }
    }

    @Override
    public void addNote(Note note) {
        NoteEntity noteEntity=noteEntityDataMapper.transformTo(note);
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_ID, noteEntity.getNoteId());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE, noteEntity.getTitle());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION, noteEntity.getDescription());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY, noteEntity.getPriority());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_EDIT_DATE, noteEntity.getEditDate());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_END_DATE, noteEntity.getEndDate());
        database.insert(ToDoListContract.SqliteNoteEntity.TABLE_NAME,"",values);
    }

    @Override
    public void removeNote(String id) {
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        database.delete(ToDoListContract.SqliteNoteEntity.TABLE_NAME,ToDoListContract.SqliteNoteEntity.COLUMN_ID+"=?",new String[]{id});
    }

   @Override
   public void editNote(Note note) {
       NoteEntity noteEntity=noteEntityDataMapper.transformTo(note);
       SQLiteDatabase database = databaseHelper.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE, noteEntity.getTitle());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION, noteEntity.getDescription());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY, noteEntity.getPriority());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_EDIT_DATE, noteEntity.getEditDate());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_END_DATE, noteEntity.getEndDate());
       database.update(ToDoListContract.SqliteNoteEntity.TABLE_NAME,values,ToDoListContract.SqliteNoteEntity.COLUMN_ID+"=?",new String[]{noteEntity.getNoteId()});
    }

    @Override
    public Note getNoteById(String id) throws DataUnavailableException {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoListContract.SqliteNoteEntity.TABLE_NAME, null, ToDoListContract.SqliteNoteEntity.COLUMN_ID + "=?", new String[]{id}, null, null, null);
        if (cursor.moveToNext()) {
            int idColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_ID);
            int titleColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION);
            int priorityColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
            int editDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_EDIT_DATE);
            int endDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_END_DATE);
            NoteEntity noteEntity = new NoteEntity();
            noteEntity.setNoteId(cursor.getString(idColumnIndex));
            noteEntity.setTitle(cursor.getString(titleColumnIndex));
            noteEntity.setDescription(cursor.getString(descriptionColumnIndex));
            noteEntity.setPriority(cursor.getInt(priorityColumnIndex));
            noteEntity.setEditDate(cursor.getString(editDateColumnIndex));
            noteEntity.setEndDate(cursor.getString(endDateColumnIndex));
            try {
                return noteEntityDataMapper.transformFrom(noteEntity);
            } catch (ParseException e) {
                throw new DataUnavailableException();
            }
        }
        throw new DataUnavailableException();
    }
}
