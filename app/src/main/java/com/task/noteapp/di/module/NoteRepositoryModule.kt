package com.task.noteapp.di.module

import com.task.noteapp.data.repository.DefaultNoteRepository
import com.task.noteapp.data.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class NoteRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindNoteRepository(repository: DefaultNoteRepository): NoteRepository

}
