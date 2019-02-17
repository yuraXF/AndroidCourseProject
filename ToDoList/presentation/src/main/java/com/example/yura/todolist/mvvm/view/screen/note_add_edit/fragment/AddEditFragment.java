package com.example.yura.todolist.mvvm.view.screen.note_add_edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.domain.exceptions.DataUnavailableException;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.AddEditNoteFragmentBinding;
import com.example.yura.todolist.mvvm.viewmodel.AddEditNoteViewModel;
import com.example.yura.todolist.mvvm.viewmodel.AddEditViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class AddEditFragment extends DaggerFragment {

    @Inject
    public NotesRepository notesRepository;
    private AddEditNoteViewModel addEditNoteViewModel;
    private AddEditViewModelFactory addEditViewModelFactory;

    private final static String NOTE_ID = "NOTE_ID";
    private String current_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        current_id = getArguments().getString(NOTE_ID);
        try {
            addEditNoteViewModel = new AddEditNoteViewModel(notesRepository, current_id);
        } catch (DataUnavailableException e) {
            e.printStackTrace();
        }
        addEditViewModelFactory = new AddEditViewModelFactory(addEditNoteViewModel);
        addEditNoteViewModel = ViewModelProviders
                .of(this, addEditViewModelFactory)
                .get(AddEditNoteViewModel.class);
        addEditNoteViewModel.saveChangeClickListener.setValue(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditNoteViewModel.doNoteOperation();
                getActivity().onBackPressed();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddEditNoteFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.add_edit_note_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setVm(addEditNoteViewModel);
        View view = binding.getRoot();
        return view;
    }

    public static AddEditFragment newInstance(String noteId) {
        AddEditFragment addEditFragment = new AddEditFragment();
        Bundle arguments = new Bundle();
        arguments.putString(NOTE_ID, noteId);
        addEditFragment.setArguments(arguments);
        return addEditFragment;
    }
}
