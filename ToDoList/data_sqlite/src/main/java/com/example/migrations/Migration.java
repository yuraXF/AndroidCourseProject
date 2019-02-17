package com.example.migrations;

import android.database.sqlite.SQLiteDatabase;

public interface Migration {

    int getTargetVersion();

    void execute(SQLiteDatabase database);

}
