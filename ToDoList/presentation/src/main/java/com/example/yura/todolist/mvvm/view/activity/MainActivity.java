package com.example.yura.todolist.mvvm.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yura.todolist.R;
import com.example.yura.todolist.mvvm.view.fragment.ShowNoteFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

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

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("To Do List");
        setSupportActionBar(toolbar);*/

    }

    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.sort_by_priority:
                finish();
                break;
            case R.id.action_exit:
                finish();
                break;
            default: break;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
