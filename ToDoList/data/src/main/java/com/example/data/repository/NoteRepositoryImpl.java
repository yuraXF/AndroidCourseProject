package com.example.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.data.entity.ToDoListContract;
import com.example.domain.Note;
import com.example.domain.repository.NotesRepository;

import java.util.ArrayList;

public class NoteRepositoryImpl implements NotesRepository {
    private SharedPreferences sharedPreferences;
    private static final String NOTE_LIST="NOTE_LIST";
    private Context context;

    public NoteRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Note> getNotes() {
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        SQLiteDatabase database=databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoListContract.SqliteNoteEntity.TABLE_NAME,null,null,null,null,null,ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        int idColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_ID);
        int titleColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE);
        int descriptionColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION);
        int priorityColumnIndex = cursor.getColumnIndex(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY);
        ArrayList<Note> notes=new ArrayList<Note>();
        while (cursor.moveToNext()) {
            Note note=new Note(cursor.getString(idColumnIndex));
            note.setTitle(cursor.getString(titleColumnIndex));
            note.setDescription(cursor.getString(descriptionColumnIndex));
            note.setPriority(cursor.getInt(priorityColumnIndex));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    @Override
    public void addNote(Note note) {
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_ID, note.getNoteId());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE, note.getTitle());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION, note.getDescription());
        values.put(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY, note.getPriority());
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
       DatabaseHelper databaseHelper = new DatabaseHelper(context);
       SQLiteDatabase database = databaseHelper.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_TITLE, note.getTitle());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_DESCRIPTION, note.getDescription());
       values.put(ToDoListContract.SqliteNoteEntity.COLUMN_PRIORITY, note.getPriority());
       database.update(ToDoListContract.SqliteNoteEntity.TABLE_NAME,values,ToDoListContract.SqliteNoteEntity.COLUMN_ID+"=?",new String[]{note.getNoteId()});
    }
}
