package com.example.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.data.entity.migrations.CreateNoteTableMigration;
import com.example.data.entity.migrations.Migration;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static Migration[] migrations = new Migration[]{
            new CreateNoteTableMigration()
    };

    public DatabaseHelper(@Nullable Context context) {
        super(context, "database.sql3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Migration migration : migrations) {
            migration.execute(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Migration migration : migrations) {
            if (migration.getTargetVersion() > oldVersion
                    && migration.getTargetVersion() <= newVersion) {
                migration.execute(db);
            }
        }
    }

}
