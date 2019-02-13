package com.example.yura.todolist.mvvm.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yura.todolist.R;
import com.example.yura.todolist.mvvm.view.fragment.ShowNoteFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private ShowNoteFragment showNoteFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNoteFragment = new ShowNoteFragment();
        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_layout, showNoteFragment);
            fragmentTransaction.commit();
        }

    }

}
