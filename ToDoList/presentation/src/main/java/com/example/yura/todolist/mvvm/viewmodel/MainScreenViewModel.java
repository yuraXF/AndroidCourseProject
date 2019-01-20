package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainScreenViewModel extends ViewModel {

    public final MutableLiveData<List<NoteModel>> notes=new MutableLiveData<>();
    private final NotesRepository notesRepository;

    public MainScreenViewModel(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
        notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(notesRepository.getNotes()));
    }

    public void removeNote(String id){
        notesRepository.removeNote(id);
        notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(notesRepository.getNotes()));
    }

    public MutableLiveData<List<NoteModel>> getNotes() {
        return notes;
    }

    public void onAddEditNote(){
        notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(notesRepository.getNotes()));
    }

}
