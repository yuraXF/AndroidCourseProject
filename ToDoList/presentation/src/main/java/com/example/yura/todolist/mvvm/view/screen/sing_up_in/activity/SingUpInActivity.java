package com.example.yura.todolist.mvvm.view.screen.sing_up_in.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.domain.model.Nothing;
import com.example.domain.repository.AuthRepository;
import com.example.yura.todolist.R;
import com.example.yura.todolist.databinding.SingUpInActivityBinding;
import com.example.yura.todolist.mvvm.view.screen.note_list.activity.MainActivity;
import com.example.yura.todolist.mvvm.viewmodel.Event;
import com.example.yura.todolist.mvvm.viewmodel.SingUpInViewModel;
import com.example.yura.todolist.mvvm.viewmodel.SingUpInViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class SingUpInActivity extends DaggerAppCompatActivity {

    @Inject
    AuthRepository authRepository;
    @Inject
    SingUpInViewModelFactory singUpInViewModelFactory;
    private SingUpInViewModel singUpInViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singUpInViewModel = ViewModelProviders
                .of(this, singUpInViewModelFactory)
                .get(SingUpInViewModel.class);

        singUpInViewModel.showMainActivity.observe(this, new Observer<Event<Nothing>>() {
            @Override
            public void onChanged(Event<Nothing> event) {
                if (event.peekContentIfNotHandled() != null) {
                    if (singUpInViewModel.isSuccessEntrance()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка " + singUpInViewModel.getCurrentModeInfo() + "!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        SingUpInActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.sing_up_in_activity);
        binding.setLifecycleOwner(this);
        binding.setVm(singUpInViewModel);


    }
}
