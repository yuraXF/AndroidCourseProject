package com.example.data.entity.migrations;

import android.database.sqlite.SQLiteDatabase;

public interface Migration {

    int getTargetVersion();

    void execute(SQLiteDatabase database);

}
