package com.example.yura.todolist.di;

import android.content.Context;

import com.example.data_sqlite.NoteRepositoryImpl;
import com.example.domain.repository.NotesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private Context context;

    RepositoryModule(Context context){
        this.context=context;
    }

    @Provides
    Context provideContext(){
        return context;
    }

    @Provides
    NotesRepository provideNotesRepository(Context context){
        return new NoteRepositoryImpl(context);
    }

}
