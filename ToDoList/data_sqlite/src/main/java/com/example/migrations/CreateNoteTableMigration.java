package com.example.migrations;

import android.database.sqlite.SQLiteDatabase;

import com.example.data.mapper.DateMapper;

import java.util.Date;
import java.util.UUID;

public class CreateNoteTableMigration implements Migration {

    @Override
    public int getTargetVersion() {
        return 1;
    }

    @Override
    public void execute(SQLiteDatabase database) {
        DateMapper dateMapper=new DateMapper();
        String currentDate = dateMapper.transformFromDate(new Date());

        database.execSQL("CREATE TABLE notes (id TEXT, title TEXT, description TEXT, priority INTEGER, editDate TEXT, endDate TEXT)");
        database.execSQL("INSERT INTO notes VALUES ('"+UUID.randomUUID().toString() +"', 'LowPriorityNote', 'Low Priority Note', 2,  '"+currentDate+"', '"+currentDate+"')");
        database.execSQL("INSERT INTO notes VALUES ('"+UUID.randomUUID().toString() +"', 'MiddlePriorityNote', 'Middle Priority Note', 1,  '"+currentDate+"', '"+currentDate+"')");
        database.execSQL("INSERT INTO notes VALUES ('"+UUID.randomUUID().toString() +"', 'HighPriorityNote', 'High Priority Note', 0,  '"+currentDate+"', '"+currentDate+"')");
    }
}
