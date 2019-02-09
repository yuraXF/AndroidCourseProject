package com.example.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.data.entity.NoteEntity;
import com.example.data.entity.ToDoListContract;
import com.example.data.entity.mapper.DateMapper;
import com.example.data.entity.mapper.NoteEntityDataMapper;
import com.example.domain.Note;
import com.example.domain.repository.NotesRepository;

import java.text.ParseException;
import java.util.ArrayList;

public class NoteRepositoryImpl implements NotesRepository {
    private SharedPreferences sharedPreferences;
    private static final String NOTE_LIST="NOTE_LIST";
    private Context context;
    private NoteEntityDataMapper noteEntityDataMapper;

    public NoteRepositoryImpl(Context context) {
        this.context = context;
        noteEntityDataMapper=new NoteEntityDataMapper();
    }

    @Override
    public ArrayList<Note> getNotes() throws ParseException {
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoListContract.SqliteNoteEntity.TABLE_NAME,null,null,null,null,null,ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        int idColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_ID);
        int titleColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE);
        int descriptionColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION);
        int priorityColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        int editDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_EDIT_DATE);
        int endDateColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_END_DATE);
        ArrayList<NoteEntity> notes=new ArrayList<NoteEntity>();
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
        return (ArrayList<Note>) noteEntityDataMapper.transformFrom(notes);
    }

    @Override
    public void addNote(Note note) {
        NoteEntity noteEntity=noteEntityDataMapper.transformTo(note);
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
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
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        database.delete(ToDoListContract.SqliteNoteEntity.TABLE_NAME,ToDoListContract.SqliteNoteEntity.COLUMN_ID+"=?",new String[]{id});
    }

   @Override
   public void editNote(Note note) {
       NoteEntity noteEntity=noteEntityDataMapper.transformTo(note);
       DatabaseHelper databaseHelper = new DatabaseHelper(context);
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
    public Note getNoteById(String id) throws ParseException {
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoListContract.SqliteNoteEntity.TABLE_NAME,null,ToDoListContract.SqliteNoteEntity.COLUMN_ID+"=?",new String[]{id},null,null,null);
        cursor.moveToNext();
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
        return noteEntityDataMapper.transformFrom(noteEntity);
    }
}
