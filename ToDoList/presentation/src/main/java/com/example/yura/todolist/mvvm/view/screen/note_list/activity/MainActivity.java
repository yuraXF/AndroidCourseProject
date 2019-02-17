package com.example.yura.todolist.mvvm.view.screen.note_list.activity;

import android.os.Bundle;

import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.mvvm.view.screen.note_add_edit.fragment.AddEditFragment;
import com.example.yura.todolist.mvvm.view.screen.note_list.fragment.ShowNoteFragment;
import com.example.yura.todolist.mvvm.viewmodel.Event;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModel;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModelFactory;
import com.example.yura.todolist.mvvm.viewmodel.Nothing;

import javax.inject.Inject;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private ShowNoteFragment showNoteFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private AddEditFragment addEditFragment;

    @Inject
    public NotesRepository notesRepository;
    @Inject
    MainScreenViewModelFactory mainScreenViewModelFactory;
    private MainScreenViewModel mainScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainScreenViewModel = ViewModelProviders
                .of(this, mainScreenViewModelFactory)
                .get(MainScreenViewModel.class);
        fragmentManager = getSupportFragmentManager();
        mainScreenViewModel.showAddNoteFragmentEvent.observe(this, new Observer<Event<Nothing>>() {
            @Override
            public void onChanged(Event<Nothing> event) {
                if (event.peekContentIfNotHandled() != null) {
                    addEditFragment = AddEditFragment.newInstance(null);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout, addEditFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        setContentView(R.layout.activity_main);
        showNoteFragment = new ShowNoteFragment();
        if (savedInstanceState == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_layout, showNoteFragment);
            fragmentTransaction.commit();
        }
    }

}
