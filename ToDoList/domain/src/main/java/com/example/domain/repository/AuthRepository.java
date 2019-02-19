package com.example.domain.repository;

public interface AuthRepository {

    Boolean SingUp(String userName, String password);

    Boolean SingIn(String userName, String password);

    Boolean isCurrentUser();

}
