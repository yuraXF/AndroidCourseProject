package com.example.yura.todolist.mvvm.viewmodel;

import com.example.domain.model.Nothing;
import com.example.domain.repository.AuthRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SingUpInViewModel extends ViewModel {

    private AuthRepository authRepository;
    public MutableLiveData<String> userName=new MutableLiveData<>();
    public MutableLiveData<String> userPassword=new MutableLiveData<>();
    public MutableLiveData<Event<Nothing>> showMainActivity=new MutableLiveData<>();
    public MutableLiveData<String> actionCaption=new MutableLiveData<>();
    private String currentMode;
    private String currentModeInfo;
    private final String REGISTRATION="singUp";
    private final String ENTRANCE="singIn";

    @Inject
    public SingUpInViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
        userName.postValue("");
        userPassword.postValue("");
        if(authRepository.isCurrentUser()==false){
            actionCaption.postValue("Зарегистрироваться");
            currentMode=REGISTRATION;
            currentModeInfo="регистрации";
        }else{
            actionCaption.postValue("Войти");
            currentMode=ENTRANCE;
            currentModeInfo="входа";
        }
    }

    public void navigateToMainActivity() {
        showMainActivity.postValue(new Event<Nothing>(new Nothing()));
    }

    public Boolean isSuccessEntrance() {
        if (currentMode == REGISTRATION) {
            return singUp();
        } else if (currentMode == ENTRANCE) {
            return singIn();
        }else{
            return false;
        }
    }

    private Boolean singUp(){
        return authRepository.SingUp(userName.getValue(),userPassword.getValue());
    }

    private Boolean singIn(){
        return authRepository.SingIn(userName.getValue(),userPassword.getValue());
    }

    public String getCurrentModeInfo() {
        return currentModeInfo;
    }
}
