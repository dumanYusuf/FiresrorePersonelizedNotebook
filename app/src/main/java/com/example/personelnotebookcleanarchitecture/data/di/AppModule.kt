package com.example.personelnotebookcleanarchitecture.data.di

import com.example.personelnotebookcleanarchitecture.data.repo.AuthRepoImpl
import com.example.personelnotebookcleanarchitecture.domain.repo.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebseAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepo(auth:FirebaseAuth):AuthRepo{
        return AuthRepoImpl(auth)
    }

}