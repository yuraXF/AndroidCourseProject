package com.example.yura.todolist.mvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.data.repository.NoteRepositoryImpl;
import com.example.domain.SortType;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.ShowNoteFragmentBinding;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.view.adapter.MyAdapter;
import com.example.yura.todolist.mvvm.viewmodel.AddEditNoteViewModel;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModel;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowNoteFragment extends Fragment implements MyAdapter.Callback {

    private RecyclerView recyclerView;
    private static MyAdapter myAdapter;
    private LinearLayoutManager llm;
    private NotesRepository notesRepository;
    private MainScreenViewModel mainScreenViewModel;
    private MainScreenViewModelFactory mainScreenViewModelFactory;

    private AddEditNoteViewModel addEditNoteViewModel;

    private AddEditFragment addEditFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = new NoteRepositoryImpl(getContext());
        try {
            mainScreenViewModel = new MainScreenViewModel(notesRepository);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mainScreenViewModelFactory = new MainScreenViewModelFactory(mainScreenViewModel);
        mainScreenViewModel = ViewModelProviders
                .of(this, mainScreenViewModelFactory)
                .get(MainScreenViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ShowNoteFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.show_note_fragment, container,false);
        binding.setLifecycleOwner(this);
        binding.setVm(mainScreenViewModel);
        View view = binding.getRoot();
        myAdapter = new MyAdapter();
        myAdapter.setCallback(this);
        binding.rvMain.setAdapter(myAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditFragment=AddEditFragment.newInstance(null);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, addEditFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("To Do List");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.sort_by_priority:
                        try {
                            mainScreenViewModel.setSortTypeValue(SortType.PRIORITY);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.sort_by_title:
                        try {
                            mainScreenViewModel.setSortTypeValue(SortType.TITLE);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.sort_by_edit_date:
                        try {
                            mainScreenViewModel.setSortTypeValue(SortType.EDIT_DATE);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.sort_by_end_date:
                        try {
                            mainScreenViewModel.setSortTypeValue(SortType.END_DATE);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.action_exit:
                        getActivity().finish();
                        break;
                    default: break;
                }

                return false;
            }
        });
        //((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mainScreenViewModel.onAddEditNote();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showEditFragment(NoteModel noteModel) {
        addEditFragment=AddEditFragment.newInstance(noteModel.getNoteId());
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, addEditFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void deleteCurrentNote(NoteModel noteModel) throws ParseException {
        mainScreenViewModel.removeNote(noteModel.getNoteId());
    }
}