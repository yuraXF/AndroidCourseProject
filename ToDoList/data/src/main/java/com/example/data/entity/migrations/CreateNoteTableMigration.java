package com.example.data.entity.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.example.data.entity.migrations.Migration;

public class CreateNoteTableMigration implements Migration {

    @Override
    public int getTargetVersion() {
        return 1;
    }

    @Override
    public void execute(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE notes (id TEXT, title TEXT, description TEXT, priority INTEGER)");
    }
}
