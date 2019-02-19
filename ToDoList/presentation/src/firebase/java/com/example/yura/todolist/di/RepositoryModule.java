package com.example.yura.todolist.di;

import com.example.data_firebase.repository.FirebaseAuthRepositoryImpl;
import com.example.data_firebase.repository.FirebaseNoteRepositoryImpl;
import com.example.domain.repository.AuthRepository;
import com.example.domain.repository.NotesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    NotesRepository provideNotesRepository() {
        return new FirebaseNoteRepositoryImpl();
    }

    @Provides
    AuthRepository provideAuthRepository(){
        return new FirebaseAuthRepositoryImpl();
    }

}
