package com.example.yura.todolist.mvvm.view.screen.note_list.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.domain.model.SortType;
import com.example.domain.repository.NotesRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.ShowNoteFragmentBinding;
import com.example.yura.todolist.mvvm.model.NoteModel;
import com.example.yura.todolist.mvvm.view.screen.note_list.adapter.MyAdapter;
import com.example.yura.todolist.mvvm.view.screen.note_add_edit.fragment.AddEditFragment;
import com.example.yura.todolist.mvvm.view.screen.statistic.fragment.StatisticFragment;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModel;
import com.example.yura.todolist.mvvm.viewmodel.MainScreenViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class ShowNoteFragment extends DaggerFragment implements MyAdapter.Callback {

    private static MyAdapter myAdapter;
    @Inject
    public NotesRepository notesRepository;

    @Inject
    MainScreenViewModelFactory mainScreenViewModelFactory;

    private MainScreenViewModel mainScreenViewModel;

    private AddEditFragment addEditFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private StatisticFragment statisticFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainScreenViewModel = ViewModelProviders
                .of(getActivity(), mainScreenViewModelFactory)
                .get(MainScreenViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ShowNoteFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.show_note_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setVm(mainScreenViewModel);
        View view = binding.getRoot();
        myAdapter = new MyAdapter();
        myAdapter.setCallback(this);
        binding.rvMain.setAdapter(myAdapter);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.sort_by_priority:
                        sortByType(SortType.PRIORITY);
                        break;
                    case R.id.sort_by_title:
                        sortByType(SortType.TITLE);
                        break;
                    case R.id.sort_by_edit_date:
                        sortByType(SortType.EDIT_DATE);
                        break;
                    case R.id.sort_by_end_date:
                        sortByType(SortType.END_DATE);
                        break;
                    case R.id.action_statistic:
                        startStatisticFragment();
                        break;
                    case R.id.action_exit:
                        getActivity().finish();
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainScreenViewModel.onAddEditNote();
    }

    @Override
    public void showEditFragment(NoteModel noteModel) {
        addEditFragment = AddEditFragment.newInstance(noteModel.getNoteId());
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, addEditFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void deleteCurrentNote(NoteModel noteModel) {
        mainScreenViewModel.removeNote(noteModel.getNoteId());
    }

    private void sortByType(SortType sortType) {
        mainScreenViewModel.setSortTypeValue(sortType);
    }

    private void startStatisticFragment(){
        statisticFragment = new StatisticFragment();
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, statisticFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
