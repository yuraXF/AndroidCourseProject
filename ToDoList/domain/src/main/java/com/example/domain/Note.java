package com.example.domain;

public class Note {
    private String noteId;
    private int priority;
    private String title;
    private String description;

    public Note(String noteId, int priority, String title, String description) {
        this.noteId = noteId;
        this.priority = priority;
        this.title = title;
        this.description = description;
    }

    public Note(String noteId) {
        this.noteId = noteId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
