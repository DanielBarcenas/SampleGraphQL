package com.example.samplegraphql.di

import com.example.samplegraphql.data.repository.CharacterRepository
import com.example.samplegraphql.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repo: CharacterRepositoryImpl): CharacterRepository

}