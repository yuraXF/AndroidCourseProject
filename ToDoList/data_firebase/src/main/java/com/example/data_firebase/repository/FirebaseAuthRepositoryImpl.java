package com.example.data_firebase.repository;

import com.example.domain.repository.AuthRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;


public class FirebaseAuthRepositoryImpl implements AuthRepository {
    private FirebaseAuth mAuth;

    @Inject
    public FirebaseAuthRepositoryImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Boolean SingUp(String userName, String password) {

        Task<AuthResult> task=mAuth.createUserWithEmailAndPassword(userName, password);
        if (task.isSuccessful()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean SingIn(String userName, String password) {

        Task<AuthResult> task=mAuth.signInWithEmailAndPassword(userName, password);
        if (task.isSuccessful()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean isCurrentUser() {
        return mAuth.getCurrentUser()!=null;
    }

}
