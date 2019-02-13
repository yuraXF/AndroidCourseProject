package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.SortType;
import com.example.domain.repository.NotesRepository;
import com.example.domain.usecase.NoteUseCase;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainScreenViewModel extends ViewModel {

    public final MutableLiveData<List<NoteModel>> notes=new MutableLiveData<>();
    @Inject
    NoteUseCase noteUseCase;
    private SortType sortTypeValue;

    public MainScreenViewModel(NotesRepository notesRepository) throws ParseException {
        //noteUseCase = new NoteUseCase(notesRepository);
        sortTypeValue=SortType.PRIORITY;
        attachNotes();
    }

    public void attachNotes() throws ParseException {
        notes.postValue((List<NoteModel>) new NoteModelDataMapper().transformTo(noteUseCase.getNotes(sortTypeValue)));
    }

    public void removeNote(String id) throws ParseException {
        noteUseCase.removeNote(id);
        attachNotes();
    }

    public MutableLiveData<List<NoteModel>> getNotes() {
        return notes;
    }

    public void onAddEditNote() throws ParseException {
        attachNotes();
    }

    public void setSortTypeValue(SortType sortType) throws ParseException {
        this.sortTypeValue=sortType;
        attachNotes();
    }

}
