package com.example.yura.todolist.mvvm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.data.repository.NoteRepositoryImpl;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.databinding.ActivityEditNoteBinding;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.model.mapper.NoteModelDataMapper;
import com.example.yura.todolist.mvvm.view.component.ExtraTags;
import com.example.yura.todolist.mvvm.view.component.MyView;
import com.example.yura.todolist.PriorityTypeEnum;
import com.example.yura.todolist.R;
import com.example.yura.todolist.mvvm.viewmodel.AddEditNoteViewModel;
import com.example.yura.todolist.mvvm.viewmodel.AddEditViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class AddEditNoteActivity extends AppCompatActivity {

    private EditText et_title;
    private EditText et_description;
    private MyView myView_priority;
    private Button btn_low;
    private Button btn_middle;
    private Button btn_high;
    private int priority;
    private String current_id;

    private Intent intent;

    private AddEditNoteViewModel addEditNoteViewModel;
    private AddEditViewModelFactory addEditViewModelFactory;

    private NotesRepository notesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesRepository=new NoteRepositoryImpl(this);
        addEditNoteViewModel=new AddEditNoteViewModel(notesRepository);
        addEditViewModelFactory = new AddEditViewModelFactory(addEditNoteViewModel);
        addEditNoteViewModel = ViewModelProviders
                .of(this, addEditViewModelFactory)
                .get(AddEditNoteViewModel.class);
        ActivityEditNoteBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note);
        binding.setLifecycleOwner(this);
        binding.setVm(addEditNoteViewModel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_title=(EditText) findViewById(R.id.et_title);
        et_description=(EditText) findViewById(R.id.et_description);
        myView_priority=(MyView) findViewById(R.id.myview_priority);

        intent=getIntent();
        current_id=intent.getStringExtra(ExtraTags.ID);
        addEditNoteViewModel.title.postValue(intent.getStringExtra(ExtraTags.TITLE));   //Вот эта кака что мне портит картину!
        et_title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                addEditNoteViewModel.title.postValue(et_title.getText().toString());
            }
        });
        //et_title.setText(intent.getStringExtra(ExtraTags.TITLE));
        et_description.setText(intent.getStringExtra(ExtraTags.DESCRIPTION));
        priority=intent.getIntExtra(ExtraTags.PRIORITY,  0);
        if (current_id==null){
            setTitle("Добавление заметки");
            priority=PriorityTypeEnum.LOW.ordinal();
        }else {
            setTitle("Редактирование заметки");
        }
        myView_priority.setPriorityBackground(priority);

        btn_low=(Button) findViewById(R.id.btn_low);
        btn_middle=(Button) findViewById(R.id.btn_middle);
        btn_high=(Button) findViewById(R.id.btn_high);
        btn_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView_priority.setPriorityBackground(PriorityTypeEnum.LOW.ordinal());
                priority=PriorityTypeEnum.LOW.ordinal();
            }
        });

        btn_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView_priority.setPriorityBackground(PriorityTypeEnum.MIDDLE.ordinal());
                priority=PriorityTypeEnum.MIDDLE.ordinal();
            }
        });

        btn_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView_priority.setPriorityBackground(PriorityTypeEnum.HIGH.ordinal());
                priority=PriorityTypeEnum.HIGH.ordinal();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_id==null){
                    NoteModel noteModel=new NoteModel(UUID.randomUUID().toString());
                    noteModel.setTitle(et_title.getText().toString());
                    noteModel.setDescription(et_description.getText().toString());
                    noteModel.setPriority(priority);
                    addEditNoteViewModel.addNewNote(new NoteModelDataMapper().transformFrom(noteModel));
                    finish();
                }else {
                    NoteModel noteModel = new NoteModel(current_id);
                    noteModel.setTitle(et_title.getText().toString());
                    noteModel.setDescription(et_description.getText().toString());
                    noteModel.setPriority(priority);
                    addEditNoteViewModel.editNote(new NoteModelDataMapper().transformFrom(noteModel));
                    finish();
                }
            }
        });
    }

}
