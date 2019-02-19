package com.example.repository;

import com.example.domain.repository.AuthRepository;

import javax.inject.Inject;

public class SqLiteAuthRepositoryImpl implements AuthRepository {

    @Inject
    public SqLiteAuthRepositoryImpl() {
    }

    @Override
    public Boolean SingUp(String userName, String password) {
        return true;
    }

    @Override
    public Boolean SingIn(String userName, String password) {
        return true;
    }

    @Override
    public Boolean isCurrentUser() {
        return true;
    }
}
