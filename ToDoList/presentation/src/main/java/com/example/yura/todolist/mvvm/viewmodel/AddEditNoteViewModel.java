package com.example.yura.todolist.mvvm.viewmodel;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;

import com.example.data.entity.mapper.DateMapper;
import com.example.domain.Note;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.PriorityTypeEnum;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class AddEditNoteViewModel extends ViewModel {

    private final NotesRepository notesRepository;

    public MutableLiveData<String> caption=new MutableLiveData<>();
    public MutableLiveData<String> title=new MutableLiveData<>();
    public MutableLiveData<String> description=new MutableLiveData<>();
    public MutableLiveData<Integer> priority=new MutableLiveData<>();
    public MutableLiveData<Boolean> enableClickable=new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onLowProirityClickListener=new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onMiddleProirityClickListener=new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onHighProirityClickListener=new MutableLiveData<>();
    public MutableLiveData<View.OnClickListener> onChangeEndDateClickListener=new MutableLiveData<>();
    public MutableLiveData<String> edit_date=new MutableLiveData<>();
    public MutableLiveData<String> end_date=new MutableLiveData<>();
    private NoteModelDataMapper noteModelDataMapper;
    private NoteModel noteModel;

    public AddEditNoteViewModel(NotesRepository notesRepository, String noteId) throws ParseException {
        this.notesRepository = notesRepository;
        noteModelDataMapper=new NoteModelDataMapper();
        if (noteId == null) {
            noteModel=null;
            caption.postValue("Добавление заметки");
            title.postValue("");
            description.postValue("");
            edit_date.postValue("");
            end_date.postValue("");
            priority.postValue(PriorityTypeEnum.LOW.ordinal());
        } else {
            caption.postValue("Изменение заметки");
            noteModel=noteModelDataMapper.transformTo(notesRepository.getNoteById(noteId));
            title.postValue(noteModel.getTitle());
            description.postValue(noteModel.getDescription());
            edit_date.postValue(noteModel.getEditDate());
            end_date.postValue(noteModel.getEndDate());
            priority.postValue(noteModel.getPriority());
        }
        enableClickable.postValue(false);
        title.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(title.getValue().isEmpty()){
                    enableClickable.postValue(false);
                }else{
                    enableClickable.postValue(true);
                }
            }
        });
        onLowProirityClickListener.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority.postValue(PriorityTypeEnum.LOW.ordinal());
            }
        });
        onMiddleProirityClickListener.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority.postValue(PriorityTypeEnum.MIDDLE.ordinal());
            }
        });
        onHighProirityClickListener.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priority.postValue(PriorityTypeEnum.HIGH.ordinal());
            }
        });
        onChangeEndDateClickListener.postValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog(v);
            }
        });
    }

    public void doNoteOperation() throws ParseException {
        DateMapper dateMapper = new DateMapper();
        String current_date=dateMapper.transformFromDate(new Date());
        if (noteModel==null){
            NoteModel addNote=new NoteModel(UUID.randomUUID().toString());
            addNote.setTitle(title.getValue());
            addNote.setDescription(description.getValue());
            addNote.setPriority(priority.getValue());
            edit_date.postValue(current_date);
            addNote.setEditDate(current_date);
            addNote.setEndDate(end_date.getValue());
            addNewNote(addNote);
        }else{
            NoteModel editNote = new NoteModel(noteModel.getNoteId());
            editNote.setTitle(title.getValue());
            editNote.setDescription(description.getValue());
            editNote.setPriority(priority.getValue());
            edit_date.postValue(current_date);
            editNote.setEditDate(current_date);
            editNote.setEndDate(end_date.getValue());
            editNote(editNote);
        }
    }

    public void addNewNote(NoteModel noteModel) throws ParseException {
        notesRepository.addNote(noteModelDataMapper.transformFrom(noteModel));
    }

    public void editNote(NoteModel noteModel) throws ParseException {
        notesRepository.editNote(noteModelDataMapper.transformFrom(noteModel));
    }

    public void changeTitle(String newTitle){
        title.postValue(newTitle);
    }

    public void DateDialog(View view) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                end_date.postValue((dayOfMonth<10?"0"+dayOfMonth:dayOfMonth) + "-" + (monthOfYear<9?"0"+(monthOfYear+1):(monthOfYear+1)) + "-" + year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        DatePickerDialog dpDialog = new DatePickerDialog(view.getContext(), listener, year, month, day);
        dpDialog.show();
    }

}
