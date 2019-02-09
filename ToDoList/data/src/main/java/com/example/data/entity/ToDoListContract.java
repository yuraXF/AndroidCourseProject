package com.example.data.entity;

public final class ToDoListContract {

    private ToDoListContract(){

    }

    public static class SqliteNoteEntity{

        public static final String TABLE_NAME="notes";
        public static final String COLUMN_ID="id";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_DESCRIPTION="description";
        public static final String COLUMN_PRIORITY="priority";
        public static final String COLUMN_EDIT_DATE="editDate";
        public static final String COLUMN_END_DATE="endDate";

    }
}
