package com.example.yura.todolist.di;

import android.content.Context;

import com.example.domain.repository.AuthRepository;
import com.example.repository.SqLiteAuthRepositoryImpl;
import com.example.repository.SqLiteNoteRepositoryImpl;
import com.example.domain.repository.NotesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    NotesRepository provideNotesRepository(Context context) {
        return new SqLiteNoteRepositoryImpl(context);
    }

    @Provides
    AuthRepository provideAuthRepository() {
        return new SqLiteAuthRepositoryImpl();
    }

}
