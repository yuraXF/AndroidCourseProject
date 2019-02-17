package com.example.yura.todolist.mvvm.viewmodel;

<<<<<<< HEAD
import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.model.SortType;
import com.example.domain.usecase.NoteInteractor;
=======
import com.example.domain.SortType;
import com.example.domain.usecase.NoteUseCase;
>>>>>>> 9620309a6d30928005ea243a7ae6927e5172252f
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainScreenViewModel extends ViewModel {

<<<<<<< HEAD
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
=======
    public final MutableLiveData<List<NoteModel>> notes = new MutableLiveData<>();

    NoteUseCase noteUseCase;
    private SortType sortTypeValue;


    @Inject
    public MainScreenViewModel(NoteUseCase noteUseCase) {
        this.noteUseCase = noteUseCase;
        sortTypeValue = SortType.PRIORITY;
        attachNotes();
    }

    public void attachNotes()  {
        try {
            notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(noteUseCase.getNotes(sortTypeValue)));
        } catch (ParseException e) {
>>>>>>> 9620309a6d30928005ea243a7ae6927e5172252f
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

<<<<<<< HEAD
    public void setSortTypeValue(SortType sortType) {
        this.sortTypeValue=sortType;
=======
    public void setSortTypeValue(SortType sortType) throws ParseException {
        this.sortTypeValue = sortType;
>>>>>>> 9620309a6d30928005ea243a7ae6927e5172252f
        attachNotes();
    }

}
