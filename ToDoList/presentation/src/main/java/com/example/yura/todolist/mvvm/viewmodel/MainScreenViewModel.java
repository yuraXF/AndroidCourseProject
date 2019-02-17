package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.model.SortType;
import com.example.domain.usecase.NoteInteractor;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainScreenViewModel extends ViewModel {

    public final MutableLiveData<List<NoteModel>> notes=new MutableLiveData<>();
    public MutableLiveData<Event<Nothing>> showAddNoteFragmentEvent =new MutableLiveData<>();
    private NoteInteractor noteInteractor;
    private SortType sortTypeValue;

    @Inject
    public MainScreenViewModel(NoteInteractor noteInteractor) {
        this.noteInteractor = noteInteractor;
        sortTypeValue=SortType.PRIORITY;
        attachNotes();
    }

    public void navigateToEditScreen() {
        showAddNoteFragmentEvent.setValue(new Event<Nothing>(new Nothing()));
    }

    public void attachNotes() {
        try {
            notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(noteInteractor.getNotes(sortTypeValue)));
        } catch (DataUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void removeNote(String id) {
        noteInteractor.removeNote(id);
        attachNotes();
    }

    public MutableLiveData<List<NoteModel>> getNotes() {
        return notes;
    }

    public void onAddEditNote() {
        attachNotes();
    }

    public void setSortTypeValue(SortType sortType) {
        this.sortTypeValue=sortType;
        attachNotes();
    }

}
