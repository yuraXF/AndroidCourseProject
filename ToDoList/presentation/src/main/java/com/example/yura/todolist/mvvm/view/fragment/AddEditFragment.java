package com.example.yura.todolist.mvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.data_sqlite.NoteRepositoryImpl;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.AddEditNoteFragmentBinding;
import com.example.yura.todolist.mvvm.viewmodel.AddEditNoteViewModel;
import com.example.yura.todolist.mvvm.viewmodel.AddEditViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class AddEditFragment extends DaggerFragment {

    @Inject
    private NotesRepository notesRepository;
    private AddEditNoteViewModel addEditNoteViewModel;
    private AddEditViewModelFactory addEditViewModelFactory;

    private final static String NOTE_ID ="NOTE_ID";
    private String current_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        current_id = getArguments().getString(NOTE_ID);
        //notesRepository = new NoteRepositoryImpl(getContext());
        try {
            addEditNoteViewModel = new AddEditNoteViewModel(notesRepository,current_id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        addEditViewModelFactory = new AddEditViewModelFactory(addEditNoteViewModel);
        addEditNoteViewModel = ViewModelProviders
                .of(this, addEditViewModelFactory)
                .get(AddEditNoteViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddEditNoteFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.add_edit_note_fragment, container,false);
        binding.setLifecycleOwner(this);
        binding.setVm(addEditNoteViewModel);
        View view = binding.getRoot();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addEditNoteViewModel.doNoteOperation();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    public static AddEditFragment newInstance(String noteId){
        AddEditFragment addEditFragment=new AddEditFragment();
        Bundle arguments=new Bundle();
        arguments.putString(NOTE_ID,noteId);
        addEditFragment.setArguments(arguments);
        return addEditFragment;
    }
}
