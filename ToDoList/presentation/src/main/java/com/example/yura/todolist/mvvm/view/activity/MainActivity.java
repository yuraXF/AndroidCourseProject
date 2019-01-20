package com.example.yura.todolist.mvvm.view.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.data.repository.NoteRepositoryImpl;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.databinding.ActivityMainBinding;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.view.component.ExtraTags;
import com.example.yura.todolist.mvvm.view.adapter.MyAdapter;
import com.example.yura.todolist.R;
import com.example.yura.todolist.mvvm.viewmodel.AddEditNoteViewModel;
import com.example.yura.todolist.mvvm.viewmodel.AddEditViewModelFactory;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModel;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyAdapter.Callback {

    RecyclerView recyclerView;
    public static MyAdapter myAdapter;
    LinearLayoutManager llm;
    private final String TAG_DIALOG = "my_dialog";
    NotesRepository notesRepository;
    MainScreenViewModel mainScreenViewModel;
    MainScreenViewModelFactory mainScreenViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = new NoteRepositoryImpl(this);
        mainScreenViewModel = new MainScreenViewModel(notesRepository);
        mainScreenViewModelFactory = new MainScreenViewModelFactory(mainScreenViewModel);
        mainScreenViewModel = ViewModelProviders
                .of(this, mainScreenViewModelFactory)
                .get(MainScreenViewModel.class);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setVm(mainScreenViewModel);
        myAdapter = new MyAdapter();
        myAdapter.setCallback(this);
        binding.rvMain.setAdapter(myAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("To Do List");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_edit = new Intent(view.getContext(), AddEditNoteActivity.class);
                startActivity(intent_edit);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mainScreenViewModel.onAddEditNote();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showEditActivity(NoteModel noteModel) {
        Intent intent_edit = new Intent(this, AddEditNoteActivity.class);
        intent_edit.putExtra(ExtraTags.ID, noteModel.getNoteId());
        intent_edit.putExtra(ExtraTags.TITLE, noteModel.getTitle());
        intent_edit.putExtra(ExtraTags.DESCRIPTION, noteModel.getDescription());
        intent_edit.putExtra(ExtraTags.PRIORITY, noteModel.getPriority());
        startActivity(intent_edit);
    }

    @Override
    public void deleteCurrentNote(NoteModel noteModel) {
        mainScreenViewModel.removeNote(noteModel.getNoteId());
    }

}
