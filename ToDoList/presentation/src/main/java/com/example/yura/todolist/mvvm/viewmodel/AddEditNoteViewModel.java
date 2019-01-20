package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.Note;
import com.example.domain.repository.NotesRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddEditNoteViewModel extends ViewModel {

    private final NotesRepository notesRepository;

    public MutableLiveData<String> title=new MutableLiveData<>();

    public AddEditNoteViewModel(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
        title.postValue("");
    }

    public void addNewNote(Note note){
        notesRepository.addNote(note);
    }

    public void editNote(Note note){
        notesRepository.editNote(note);
    }

    public void changeTitle(String newTitle){
        title.postValue(newTitle);
    }
}
